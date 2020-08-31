package com.bibliotheque.microservicemylibrary.servicesTestUnitaire;


import com.bibliotheque.microservicemylibrary.beans.UtilisateurBean;
import com.bibliotheque.microservicemylibrary.dao.IReservationDao;
import com.bibliotheque.microservicemylibrary.exeptions.CannotAddBookingException;
import com.bibliotheque.microservicemylibrary.model.*;
import com.bibliotheque.microservicemylibrary.service.emprunt.IEmpruntService;
import com.bibliotheque.microservicemylibrary.service.livre.ILivreService;
import com.bibliotheque.microservicemylibrary.service.reservation.IReservationServiceImpl;
import com.bibliotheque.microservicemylibrary.service.userbean.IUserbeanService;
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

    @Mock
    private IEmpruntService iEmpruntService;

    @Mock
    private ILivreService iLivreService;

    @Mock
    private IUserbeanService iUserbeanService;

    @Autowired
    @InjectMocks
    private IReservationServiceImpl iReservationService;

    @Test
    public void testreserver(){

        List<Reservation> reservationList = new ArrayList<>();
        List<Copie> copieList = new ArrayList<>();

        Livre livre = new Livre();
        livre.setId(4L);
        livre.setReservations(reservationList);
        livre.setCopies(copieList);

        Copie copie = new Copie();
        copie.setDisponible(false);
        copieList.add(copie);

        Reservation reservation = new Reservation();
        reservation.setLivre(livre);
        reservationList.add(reservation);

        UtilisateurBean user_1 = new UtilisateurBean();
        user_1.setId(1L);

        Mockito.when(iLivreService.findById(livre.getId())).thenReturn(Optional.of(livre));
        Mockito.when(iUserbeanService.findById(user_1.getId())).thenReturn(user_1);

        iReservationService.reserver(livre.getId(), user_1.getId());

        assertThat(livre.getReservations().size()).isEqualTo(1);

    }

    @Test
    public void testUtilisateurAdejaUneReservationEnCours(){
        List<Reservation> reservations = new ArrayList<>();
        List<Copie> copieList = new ArrayList<>();

        UtilisateurBean user = new UtilisateurBean();
        user.setId(2L);

        Livre livre = new Livre();
        livre.setId(4L);
        livre.setCopies(copieList);

        Copie copie = new Copie();
        copie.setDisponible(false);
        copieList.add(copie);

        Reservation reservation = new Reservation();
        reservation.setIdUtilisateur(user.getId());
        reservation.setLivre(livre);
        reservation.setStateEnums(StateEnum.enCours);
        reservations.add(reservation);

        Mockito.when(iLivreService.findById(livre.getId())).thenReturn(Optional.of(livre));
        Mockito.when(iUserbeanService.findById(user.getId())).thenReturn(user);
        Mockito.when(iReservationDao.findAllByIdUtilisateurAndStateEnumsOrderByDateDeReservationAsc(user.getId(), StateEnum.enCours)).thenReturn(reservations);

        try {
            iReservationService.reserver(livre.getId(), user.getId());
        }catch (CannotAddBookingException e){
            assertThat(e.getMessage()).isEqualTo("cannotBookingException01");
        }
    }

    @Test
    public void testUtilisateurAdejaUnEmpruntEnCours(){

        List<Emprunt> empruntList = new ArrayList<>();
        List<Copie> copieList = new ArrayList<>();
        List<Reservation> reservationList = new ArrayList<>();

        UtilisateurBean user = new UtilisateurBean();
        user.setId(2L);

        Livre livre = new Livre();
        livre.setId(4L);
        livre.setCopies(copieList);

        Copie copie = new Copie();
        copie.setLivre(livre);
        copie.setId(1L);
        copie.setDisponible(false);
        copieList.add(copie);

        Reservation reservation = new Reservation();
        reservation.setLivre(livre);
        reservation.setId(4L);
        reservationList.add(reservation);

        Emprunt emprunt = new Emprunt();
        emprunt.setId(4L);
        emprunt.setIdUtilisateur(user.getId());
        emprunt.setCopie(copie);
        empruntList.add(emprunt);

        Mockito.when(iUserbeanService.findById(user.getId())).thenReturn(user);
        Mockito.when(iReservationService.findById(reservation.getId())).thenReturn(Optional.of(reservation));
        Mockito.when(iLivreService.findById(livre.getId())).thenReturn(Optional.of(livre));
        Mockito.when(iReservationService.findAllByLivreAndStateEnumsOrderByDateDeReservationAsc(livre, StateEnum.enCours)).thenReturn(reservationList);
        Mockito.when(iEmpruntService.findAllByIdUtilisateurAndDateRetourIsNull(user.getId())).thenReturn(empruntList);

        try {
            iReservationService.reserver(livre.getId(), user.getId());
        }catch (CannotAddBookingException e){
            assertThat(e.getMessage()).isEqualTo("cannotBookingException02");
        }

    }

    @Test
    public void testLaListeDeReservationEstComplete(){

        List<Reservation> reservationArrayList = new ArrayList<>();
        List<Copie> copieList = new ArrayList<>();


        UtilisateurBean user_1 = new UtilisateurBean();
        user_1.setId(1L);
        UtilisateurBean user_2 = new UtilisateurBean();
        user_2.setId(2L);
        UtilisateurBean user_4 = new UtilisateurBean();
        user_4.setId(5L);

        Livre livre = new Livre();
        livre.setId(1L);
        livre.setCopies(copieList);
        livre.setReservations(reservationArrayList);

        Copie copie = new Copie();
        copie.setId(2L);
        copie.setDisponible(false);
        copie.setLivre(livre);
        copieList.add(copie);

        Reservation reservation_1 = new Reservation();
        reservation_1.setId(4L);
        reservation_1.setLivre(livre);
        reservation_1.setIdUtilisateur(user_1.getId());
        reservation_1.setStateEnums(StateEnum.enCours);
        reservationArrayList.add(reservation_1);

        Reservation reservation_2 = new Reservation();
        reservation_2.setId(2L);
        reservation_1.setLivre(livre);
        reservation_2.setIdUtilisateur(user_2.getId());
        reservation_2.setStateEnums(StateEnum.enCours);
        reservationArrayList.add(reservation_2);


        Mockito.when(iUserbeanService.findById(user_4.getId())).thenReturn(user_4);
        Mockito.when(iUserbeanService.findById(user_2.getId())).thenReturn(user_2);
        Mockito.when(iUserbeanService.findById(user_1.getId())).thenReturn(user_1);
        Mockito.when(iLivreService.findById(livre.getId())).thenReturn(Optional.of(livre));
        Mockito.when(iReservationService.findByLivreAndStateEnumsOrderByDateDeReservationAsc(livre, StateEnum.enCours)).thenReturn(reservationArrayList);

        assertThat(reservationArrayList.size()).isEqualTo(2);
        try {
            iReservationService.reserver(livre.getId(), user_4.getId());
        }catch (CannotAddBookingException e){
            assertThat(e.getMessage()).isEqualTo("cannotBookingException03");
        }
    }

}
