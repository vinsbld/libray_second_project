package com.bibliotheque.microservicemylibrary.servicesTest;


import com.bibliotheque.microservicemylibrary.beans.UtilisateurBean;
import com.bibliotheque.microservicemylibrary.dao.IReservationDao;
import com.bibliotheque.microservicemylibrary.model.Livre;
import com.bibliotheque.microservicemylibrary.model.Reservation;
import com.bibliotheque.microservicemylibrary.model.StateEnum;
import com.bibliotheque.microservicemylibrary.service.reservation.IReservationServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class ReservationServiceUnitTest {

    @Mock
    private IReservationDao iReservationDao;

    @Autowired
    @InjectMocks
    private IReservationServiceImpl iReservationService;

    private List<Reservation> reservationList_Livre_1 = new ArrayList<>();
    private List<Reservation> reservationList_Livre_2 = new ArrayList<>();
    private List<Reservation> reservations = new ArrayList<>();
    private List<Livre> livreList = new ArrayList<>();

    @Before
    public void setUp(){

        Livre livre_1 = new Livre();
        livre_1.setId(1L);
        livre_1.setTitre("a");
        livreList.add(livre_1);

        Livre livre_2 = new Livre();
        livre_2.setId(2L);
        livre_2.setTitre("b");
        livreList.add(livre_2);

        UtilisateurBean utilisateurBean_1 = new UtilisateurBean();
        utilisateurBean_1.setId(4L);

        UtilisateurBean utilisateurBean_2 = new UtilisateurBean();
        utilisateurBean_2.setId(5L);

        Reservation reservation_1 = new Reservation();
        reservation_1.setId(1L);
        reservation_1.setIdUtilisateur(4L);
        reservation_1.setLivre(livre_2);
        reservation_1.setStateEnums(StateEnum.enCours);
        reservation_1.setDateDeReservation(new Date());
        reservation_1.setEmailEnvoyer(true);
        reservationList_Livre_1.add(reservation_1);
        reservations.add(reservation_1);

        Reservation reservation_2 = new Reservation();
        reservation_2.setId(2L);
        reservation_2.setIdUtilisateur(5L);
        reservation_2.setLivre(livre_1);
        reservation_2.setStateEnums(StateEnum.enCours);
        reservation_2.setDateDeReservation(new GregorianCalendar(2020,7,05).getTime());
        reservation_2.setEmailEnvoyer(true);
        reservationList_Livre_2.add(reservation_2);
        reservations.add(reservation_2);

        Mockito.when(iReservationDao.findAll()).thenReturn(reservations);

        Mockito.when(iReservationDao.findById(reservation_1.getId())).thenReturn(Optional.of(reservation_1));
        Mockito.when(iReservationDao.findById(reservation_2.getId())).thenReturn(Optional.of(reservation_2));

        Mockito.when(iReservationDao.findAllByIdUtilisateurAndStateEnumsOrderByDateDeReservationAsc(4L, StateEnum.enCours)).thenReturn(reservationList_Livre_1);
        Mockito.when(iReservationDao.findAllByIdUtilisateurAndStateEnumsOrderByDateDeReservationAsc(5L, StateEnum.enCours)).thenReturn(reservationList_Livre_2);

        Mockito.when(iReservationDao.findAllByLivreAndStateEnumsOrderByDateDeReservationAsc(livre_1, StateEnum.enCours)).thenReturn(reservationList_Livre_1);
        Mockito.when(iReservationDao.findAllByLivreAndStateEnumsOrderByDateDeReservationAsc(livre_2, StateEnum.enCours)).thenReturn(reservationList_Livre_2);

        Mockito.when(iReservationDao.findByEmailEnvoyerAndStateEnums(true, StateEnum.enCours)).thenReturn(reservations);

    }

    @Test
    public void findById(){
        Optional<Reservation> reservation_1 = iReservationService.findById(1L);
        assertThat(reservation_1.get().getLivre().getTitre()).isEqualTo("b");
        assertThat(reservation_1.get().getLivre().getTitre()).isNotEqualTo("a");

        Optional<Reservation> reservation_2 = iReservationService.findById(2L);
        assertThat(reservation_2.get().getLivre().getTitre()).isEqualTo("a");
        assertThat(reservation_2.get().getLivre().getTitre()).isNotEqualTo("b");
    }

    @Test
    public void findAllByIdUtilisateurAndStateEnumsOrderByDateDeReservationAsc(){
        List<Reservation> reservations = iReservationService.findAllByIdUtilisateurAndStateEnumsOrderByDateDeReservationAsc(4L, StateEnum.enCours);
        assertThat(reservations.size()).isEqualTo(1);
        assertThat(reservations.size()).isNotNull();

        List<Reservation> reservation_2 = iReservationService.findAllByIdUtilisateurAndStateEnumsOrderByDateDeReservationAsc(5L, StateEnum.enCours);
        assertThat(reservation_2.size()).isEqualTo(1);
        assertThat(reservation_2.size()).isNotNull();
    }

    @Test
    public void findAllByLivreAndStateEnumsOrderByDateDeReservationAsc(){
        List<Reservation> reservations = iReservationService.findAllByLivreAndStateEnumsOrderByDateDeReservationAsc(livreList.get(0), StateEnum.enCours);
        assertThat(reservations.size()).isNotNull();
        assertThat(reservations.size()).isEqualTo(1);

        List<Reservation> reservation_2 = iReservationService.findAllByLivreAndStateEnumsOrderByDateDeReservationAsc(livreList.get(1), StateEnum.enCours);
        assertThat(reservation_2.size()).isNotNull();
        assertThat(reservation_2.size()).isEqualTo(1);
    }

    @Test
    public void findByEmailEnvoyerAndStateEnums(){
        List<Reservation> reservations = iReservationService.findByEmailEnvoyerAndStateEnums(true,StateEnum.enCours);
        assertThat(reservations.size()).isEqualTo(2);
        assertThat(reservations.size()).isNotNull();
    }

    @Test
    public void findAll(){
        List<Reservation> reservationList = iReservationDao.findAll();
        assertThat(reservationList.size()).isEqualTo(2);
    }

}
