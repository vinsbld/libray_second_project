package com.bibliotheque.microservicemylibrary.servicesTestUnitaire;

import com.bibliotheque.microservicemylibrary.beans.UtilisateurBean;
import com.bibliotheque.microservicemylibrary.dao.IEmpruntDao;
import com.bibliotheque.microservicemylibrary.exeptions.CannotAddBorrowingException;
import com.bibliotheque.microservicemylibrary.model.Copie;
import com.bibliotheque.microservicemylibrary.model.Emprunt;
import com.bibliotheque.microservicemylibrary.model.Livre;
import com.bibliotheque.microservicemylibrary.service.copie.ICopieService;
import com.bibliotheque.microservicemylibrary.service.emprunt.IEmpruntServiceImpl;
import com.bibliotheque.microservicemylibrary.service.livre.ILivreService;
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

    private List<Emprunt> empruntListing = new ArrayList<>();
    private List<Emprunt> user_1 = new ArrayList<>();
    private List<Emprunt> user_2 = new ArrayList<>();

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
        copie_4.setId(4L);

        Emprunt emprunt_1 = new Emprunt();
        emprunt_1.setId(1L);
        emprunt_1.setCopie(copie_1);
        emprunt_1.setIdUtilisateur(1L);
        emprunt_1.setDateDeFinEmprunt(new GregorianCalendar(2019,4,20).getTime());
        emprunt_1.setDateRetour(null);
        empruntListing.add(emprunt_1);
        user_1.add(emprunt_1);

        Emprunt emprunt_2 = new Emprunt();
        emprunt_2.setId(2L);
        emprunt_2.setCopie(copie_2);
        emprunt_2.setIdUtilisateur(1L);
        emprunt_2.setDateDeFinEmprunt(new GregorianCalendar(2019,5,20).getTime());
        emprunt_2.setDateRetour(null);
        empruntListing.add(emprunt_2);
        user_1.add(emprunt_2);

        Emprunt emprunt_3 = new Emprunt();
        emprunt_3.setId(3L);
        emprunt_3.setCopie(copie_3);
        emprunt_3.setIdUtilisateur(2L);
        emprunt_3.setDateDeFinEmprunt(new GregorianCalendar(2019,6,05).getTime());
        emprunt_3.setDateRetour(null);
        empruntListing.add(emprunt_3);
        user_2.add(emprunt_3);

        Emprunt emprunt_4 = new Emprunt();
        emprunt_4.setId(4L);
        emprunt_4.setCopie(copie_4);
        emprunt_4.setIdUtilisateur(2L);
        emprunt_4.setDateRetour(new Date());
        empruntListing.add(emprunt_4);
        user_2.add(emprunt_4);

        Mockito.when(iEmpruntDao.findAllByIdUtilisateur(utilisateur_1.getId())).thenReturn(user_1);
        Mockito.when(iEmpruntDao.findById(copie_2.getId())).thenReturn(Optional.of(emprunt_2));
        Mockito.when(iEmpruntDao.findAllByDateRetourIsNullAndAndDateDeFinEmpruntBefore(new GregorianCalendar(2019, 07, 04).getTime())).thenReturn(empruntListing);

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
        List<Emprunt> lst = iEmpruntService.relance(new GregorianCalendar(2019, 07, 04).getTime());
        assertThat(lst.size()).isEqualTo(3);

    }

    @Test(expected = Exception.class)
    public void testUtilisateurAdejaUnEmpruntEnCoursPourCeLivre(){
        
        try {
            Date date = new Date();
            List<Copie> copieList = new ArrayList<>();
            List<Emprunt> empruntList = new ArrayList<>();

            UtilisateurBean user = new UtilisateurBean();
            user.setId(4L);

            Livre livre = new Livre();
            livre.setCopies(copieList);

            Copie copie = new Copie();
            copie.setLivre(livre);
            copie.setEmprunts(empruntList);
            copieList.add(copie);

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

            iEmpruntService.emprunter(user.getId(), copie1.getId());

        }catch (CannotAddBorrowingException e){
            assertThat(e.getMessage()).isEqualTo("cannotBorrowException01");
        }
    }

}
