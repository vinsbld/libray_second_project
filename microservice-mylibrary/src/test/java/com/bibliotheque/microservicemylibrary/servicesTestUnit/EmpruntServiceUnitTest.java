package com.bibliotheque.microservicemylibrary.servicesTestUnit;

import com.bibliotheque.microservicemylibrary.beans.UtilisateurBean;
import com.bibliotheque.microservicemylibrary.dao.IEmpruntDao;
import com.bibliotheque.microservicemylibrary.model.Copie;
import com.bibliotheque.microservicemylibrary.model.Emprunt;
import com.bibliotheque.microservicemylibrary.service.emprunt.IEmpruntServiceImpl;
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
public class EmpruntServiceUnitTest {

    @Mock
    private IEmpruntDao iEmpruntDao;

    @Autowired
    @InjectMocks
    private IEmpruntServiceImpl iEmpruntService;

    private List<Emprunt> empruntListing = new ArrayList<>();
    private List<Emprunt> user_1_liste = new ArrayList<>();
    private List<Emprunt> user_2_liste = new ArrayList<>();
    private List<Emprunt> copie_IdAndDateRisNull = new ArrayList<>();
    private List<Emprunt> relance = new ArrayList<>();
    private List<Emprunt> iDUserAndDateRisNull = new ArrayList<>();
    private Date date = new Date();

    @Before
    public void setUp(){

        UtilisateurBean utilisateur_1 = new UtilisateurBean();
        utilisateur_1.setId(1L);

        UtilisateurBean utilisateur_2 = new UtilisateurBean();
        utilisateur_2.setId(2L);

        Copie copie_1 = new Copie();
        copie_1.setDisponible(false);
        copie_1.setId(1L);
        copie_1.setIsbn(2459);

        Copie copie_2 = new Copie();
        copie_2.setDisponible(false);
        copie_2.setId(2L);
        copie_2.setIsbn(4221);

        Copie copie_3 = new Copie();
        copie_3.setDisponible(false);
        copie_3.setId(3L);

        Copie copie_4 = new Copie();
        copie_4.setDisponible(false);
        copie_4.setIsbn(6433);
        copie_4.setId(4L);

        Emprunt emprunt_1 = new Emprunt();
        emprunt_1.setId(1L);
        emprunt_1.setCopie(copie_1);
        emprunt_1.setIdUtilisateur(1L);
        emprunt_1.setDateDeFinEmprunt(new GregorianCalendar(2019,4,20).getTime());
        emprunt_1.setDateRetour(null);
        empruntListing.add(emprunt_1);
        user_1_liste.add(emprunt_1);
        copie_IdAndDateRisNull.add(emprunt_1);
        relance.add(emprunt_1);
        iDUserAndDateRisNull.add(emprunt_1);

        Emprunt emprunt_2 = new Emprunt();
        emprunt_2.setId(2L);
        emprunt_2.setCopie(copie_2);
        emprunt_2.setIdUtilisateur(1L);
        emprunt_2.setDateDeFinEmprunt(new GregorianCalendar(2019,5,20).getTime());
        emprunt_2.setDateRetour(null);
        empruntListing.add(emprunt_2);
        user_1_liste.add(emprunt_2);
        relance.add(emprunt_2);
        iDUserAndDateRisNull.add(emprunt_2);

        Emprunt emprunt_3 = new Emprunt();
        emprunt_3.setId(3L);
        emprunt_3.setCopie(copie_3);
        emprunt_3.setIdUtilisateur(2L);
        emprunt_3.setDateDeFinEmprunt(new GregorianCalendar(2019,6,05).getTime());
        emprunt_3.setDateRetour(null);
        empruntListing.add(emprunt_3);
        user_2_liste.add(emprunt_3);
        relance.add(emprunt_3);

        Emprunt emprunt_4 = new Emprunt();
        emprunt_4.setId(4L);
        emprunt_4.setCopie(copie_4);
        emprunt_4.setIdUtilisateur(2L);
        emprunt_4.setDateRetour(date);
        empruntListing.add(emprunt_4);
        user_2_liste.add(emprunt_4);


        Mockito.when(iEmpruntDao.findAllByIdUtilisateur(utilisateur_1.getId())).thenReturn(user_1_liste);

        Mockito.when(iEmpruntDao.findById(copie_2.getId())).thenReturn(Optional.of(emprunt_2));
        //relance
        Mockito.when(iEmpruntDao.findAllByDateRetourIsNullAndAndDateDeFinEmpruntBefore(date)).thenReturn(empruntListing);

        Mockito.when(iEmpruntDao.findByCopie_Id(copie_4.getId())).thenReturn(emprunt_4);

        Mockito.when(iEmpruntDao.findAllByCopie_IdAndDateRetourIsNull(copie_1.getId())).thenReturn(copie_IdAndDateRisNull);

        Mockito.when(iEmpruntDao.findAllByIdUtilisateurAndDateRetourIsNull(utilisateur_1.getId())).thenReturn(user_1_liste);

    }

    @Test
    public void findAllByIdUtilisateur(){
        List<Emprunt> list = iEmpruntService.findAllByIdUtilisateur(1L);
        assertThat(list.get(0).getCopie().getIsbn()).isEqualTo(2459);
        assertThat(list.size()).isEqualTo(2);
        assertThat(list.size()).isNotEqualTo(4);
    }

    @Test
    public void findById(){
        Optional<Emprunt> emprunt = iEmpruntService.findById(2L);
        assertThat(emprunt.get().getCopie().getIsbn()).isEqualTo(4221);
        assertThat(emprunt.get().getIdUtilisateur()).isEqualTo(1L);
    }

    @Test
    public void relance(){
        List<Emprunt> lst = iEmpruntService.relance(date);
        assertThat(lst.size()).isEqualTo(3);

    }

    @Test
    public void findByCopie_Id(){
    Emprunt emprunt = iEmpruntService.findByCopie_Id(4L);
    assertThat(emprunt.getCopie().getIsbn()).isEqualTo(6433);
    assertThat(emprunt.getCopie().isDisponible()).isEqualTo(false);
    }

    @Test
    public void findAllByCopie_IdAndDateRetourIsNull(){
        List<Emprunt> empts = iEmpruntService.findAllByCopie_IdAndDateRetourIsNull(1L);
        assertThat(empts.size()).isEqualTo(1);
        assertThat(empts.size()).isNotEqualTo(4);

    }

    @Test
    public void findAllByIdUtilisateurAndDateRetourIsNull(){
        List<Emprunt> emp = iEmpruntService.findAllByIdUtilisateurAndDateRetourIsNull(1L);
        //assertThat(emp.size()).isEqualTo(2);
        assertThat(emp.get(0).getDateRetour()).isEqualTo(null);
        assertThat(emp.get(1).getDateRetour()).isEqualTo(null);


    }


}
