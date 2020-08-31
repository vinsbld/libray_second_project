package com.bibliotheque.microservicemylibrary.servicesTestUnitaire;

import com.bibliotheque.microservicemylibrary.beans.UtilisateurBean;
import com.bibliotheque.microservicemylibrary.dao.IEmpruntDao;
import com.bibliotheque.microservicemylibrary.exeptions.CannotAddBorrowingException;
import com.bibliotheque.microservicemylibrary.exeptions.CannotExtendBorrowingException;
import com.bibliotheque.microservicemylibrary.model.Copie;
import com.bibliotheque.microservicemylibrary.model.Emprunt;
import com.bibliotheque.microservicemylibrary.model.Livre;
import com.bibliotheque.microservicemylibrary.service.copie.ICopieService;
import com.bibliotheque.microservicemylibrary.service.emprunt.IEmpruntServiceImpl;
import com.bibliotheque.microservicemylibrary.service.livre.ILivreService;
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

    @Autowired
    @InjectMocks
    private IEmpruntServiceImpl iEmpruntService;


    @Test
    public void testUtilisateurAdejaUnEmpruntEnCoursPourCeLivre(){

        List<Emprunt> empruntList = new ArrayList<>();

        UtilisateurBean user = new UtilisateurBean();
        user.setId(4L);

        Livre livre = new Livre();

        Copie copie = new Copie();
        copie.setLivre(livre);
        copie.setEmprunts(empruntList);

        Copie copie1 = new Copie();
        copie.setLivre(livre);
        copie1.setId(2L);

        Emprunt emprunt = new Emprunt();
        emprunt.setIdUtilisateur(user.getId());
        emprunt.setCopie(copie);
        empruntList.add(emprunt);

        Mockito.when(iEmpruntDao.findAllByIdUtilisateurAndDateRetourIsNull(user.getId())).thenReturn(empruntList);
        Mockito.when(iCopieService.findById(copie1.getId())).thenReturn(Optional.of(copie1));
        Mockito.when(iLivreService.findById(livre.getId())).thenReturn(Optional.of(livre));

        try {
            iEmpruntService.emprunter(user.getId(), copie1.getId());
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
        emprunt.setProlongerEmprunt(true);

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
