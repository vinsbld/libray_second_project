package com.bibliotheque.microservicemylibrary.servicesTestUnitaire;

import com.bibliotheque.microservicemylibrary.beans.UtilisateurBean;
import com.bibliotheque.microservicemylibrary.dao.IEmpruntDao;
import com.bibliotheque.microservicemylibrary.exeptions.CannotAddBorrowingException;
import com.bibliotheque.microservicemylibrary.exeptions.CannotExtendBorrowingException;
import com.bibliotheque.microservicemylibrary.model.*;
import com.bibliotheque.microservicemylibrary.service.copie.ICopieService;
import com.bibliotheque.microservicemylibrary.service.emprunt.IEmpruntServiceImpl;
import com.bibliotheque.microservicemylibrary.service.livre.ILivreService;
import com.bibliotheque.microservicemylibrary.service.reservation.IReservationService;
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
public class EmpruntServiceTest {

    @Mock
    private IEmpruntDao iEmpruntDao;

    @Mock
    ICopieService iCopieService;

    @Mock
    ILivreService iLivreService;

    @Mock
    IUserbeanService iUserbeanService;

    @Mock
    IReservationService iReservationService;

    @Autowired
    @InjectMocks
    private IEmpruntServiceImpl iEmpruntService;


    @Test
    public void testUtilisateurAdejaUnEmpruntEnCoursPourCeLivre(){

        List<Emprunt> empruntList = new ArrayList<>();
        List<Copie> copieList = new ArrayList<>();
        List<Reservation> reservationList = new ArrayList<>();

        UtilisateurBean user = new UtilisateurBean();
        user.setId(4L);

        Livre livre = new Livre();
        livre.setId(2L);
        livre.setCopies(copieList);
        livre.setReservations(reservationList);

        Copie copie = new Copie();
        copie.setLivre(livre);
        copie.setId(4L);
        copie.setDisponible(false);
        copie.setEmprunts(empruntList);
        copieList.add(copie);

        Copie copie1 = new Copie();
        copie1.setLivre(livre);
        copie1.setId(1L);
        copieList.add(copie1);

        Emprunt emprunt = new Emprunt();
        emprunt.setId(8L);
        emprunt.setIdUtilisateur(user.getId());
        emprunt.setCopie(copie);
        emprunt.setDateRetour(null);
        empruntList.add(emprunt);

        Mockito.when(iUserbeanService.findById(user.getId())).thenReturn(user);
        Mockito.when(iEmpruntDao.findAllByIdUtilisateurAndDateRetourIsNull(user.getId())).thenReturn(empruntList);
        Mockito.when(iCopieService.findById(copie1.getId())).thenReturn(Optional.of(copie1));
        Mockito.when(iCopieService.findById(copie.getId())).thenReturn(Optional.of(copie));
        Mockito.when(iLivreService.findById(livre.getId())).thenReturn(Optional.of(livre));
        Mockito.when(iReservationService.findByLivreAndStateEnumsOrderByDateDeReservationAsc(copie1.getLivre(), StateEnum.enCours)).thenReturn(reservationList);

        assertThat(empruntList.size()).isEqualTo(1);
        try {
            iEmpruntService.emprunter(copie1.getId(), user.getId());
        }catch (CannotAddBorrowingException e){
            assertThat(e.getMessage()).isEqualTo("cannotBorrowException01");
        }
    }

    @Test
    public void testProlongerEmpruntDateButoirPassee(){

        UtilisateurBean utilisateurBean = new UtilisateurBean();
        utilisateurBean.setId(2L);

        Emprunt emprunt = new Emprunt();
        emprunt.setId(4L);
        emprunt.setDateDeFinEmprunt(new GregorianCalendar(2018,04,02).getTime());

        Mockito.when(iUserbeanService.findById(utilisateurBean.getId())).thenReturn(utilisateurBean);
        Mockito.when(iEmpruntDao.findById(emprunt.getId())).thenReturn(Optional.of(emprunt));

        try {
            iEmpruntService.prolongerEmprunt(emprunt.getId(), utilisateurBean.getId());
        }catch (CannotExtendBorrowingException e){
            assertThat(e.getMessage()).isEqualTo("CannotExtendBorrowingException01");
        }
    }

    @Test
    public void testProlongerEmpruntDejaProlonge(){

        UtilisateurBean utilisateurBean = new UtilisateurBean();
        utilisateurBean.setId(2L);

        Emprunt emprunt = new Emprunt();
        emprunt.setId(4L);
        emprunt.setDateDeFinEmprunt(new GregorianCalendar(2020,9,02).getTime());
        emprunt.setProlongerEmprunt(true);

        Mockito.when(iUserbeanService.findById(utilisateurBean.getId())).thenReturn(utilisateurBean);
        Mockito.when(iEmpruntDao.findById(emprunt.getId())).thenReturn(Optional.of(emprunt));

        try {
            iEmpruntService.prolongerEmprunt(emprunt.getId(), utilisateurBean.getId());
        }catch (CannotExtendBorrowingException e){
            assertThat(e.getMessage()).isEqualTo("CannotExtendBorrowingException02");
        }
    }

    @Test
    public void testAdd4Weeks(){
        Date date = new GregorianCalendar(2020,04,02).getTime();
        iEmpruntService.add4Weeks(date);
        assertThat(date).isEqualTo("2020-05-02");
    }

}
