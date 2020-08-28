package com.bibliotheque.microservicemylibrary.servicesTestUnitaire;

import com.bibliotheque.microservicemylibrary.dao.ILivreDao;
import com.bibliotheque.microservicemylibrary.exeptions.LivresNotFoundException;
import com.bibliotheque.microservicemylibrary.model.Copie;
import com.bibliotheque.microservicemylibrary.model.Emprunt;
import com.bibliotheque.microservicemylibrary.model.Livre;
import com.bibliotheque.microservicemylibrary.service.copie.ICopieService;
import com.bibliotheque.microservicemylibrary.service.emprunt.IEmpruntService;
import com.bibliotheque.microservicemylibrary.service.livre.ILivreServiceImpl;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.time.LocalDate;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class LivreServiceUnitTest {

    @Mock
    private ILivreDao iLivreDaoMock;

    @Mock
    private ICopieService iCopieServiceMock;

    @Mock
    private IEmpruntService iEmpruntServiceMock;


    @Autowired
    @InjectMocks
    private ILivreServiceImpl iLivreServiceMock;

    private List<Livre> livreList = new ArrayList<>();
    private List<Copie> copieList = new ArrayList<>();

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

        Copie copie_1 = new Copie();
        copie_1.setLivre(livre_1);
        copie_1.setId(1L);
        copie_1.setDisponible(false);
        copieList.add(copie_1);

        Copie copie_2 = new Copie();
        copie_2.setLivre(livre_1);
        copie_2.setId(2L);
        copie_2.setDisponible(true);
        copieList.add(copie_2);

        Copie copie_3 = new Copie();
        copie_3.setLivre(livre_2);
        copie_3.setId(3L);
        copie_3.setDisponible(false);
        copieList.add(copie_3);

        Copie copie_4 = new Copie();
        copie_4.setLivre(livre_2);
        copie_4.setId(4L);
        copie_4.setDisponible(true);
        copieList.add(copie_4);

        Mockito.when(iLivreDaoMock.findAll()).thenReturn(livreList);

        Mockito.when(iLivreDaoMock.findById(livre_1.getId())).thenReturn(Optional.of(livre_1));
        Mockito.when(iLivreDaoMock.findById(livre_2.getId())).thenReturn(Optional.of(livre_2));

        Mockito.when(iLivreDaoMock.findByCopiesId(copie_1.getId())).thenReturn(Optional.of(livre_1));
        Mockito.when(iLivreDaoMock.findByCopiesId(copie_2.getId())).thenReturn(Optional.of(livre_1));
        Mockito.when(iLivreDaoMock.findByCopiesId(copie_3.getId())).thenReturn(Optional.of(livre_2));
        Mockito.when(iLivreDaoMock.findByCopiesId(copie_4.getId())).thenReturn(Optional.of(livre_2));
    }


    @Test
    public void findAll(){
        List<Livre> listLivres = iLivreServiceMock.findAll();
        assertThat(listLivres.size()).isEqualTo(2);
        assertThat(listLivres.size()).isNotNull();
    }

    @Test
    public void findById(){
        Optional<Livre> livre = iLivreServiceMock.findById(1L);
        assertThat(livre.get().getTitre()).isEqualTo("a");
        assertThat(livre.get().getTitre()).isNotEqualTo("b");

        Optional<Livre> lvr = iLivreServiceMock.findById(2L);
        assertThat(lvr.get().getTitre()).isEqualTo("b");
        assertThat(lvr.get().getTitre()).isNotEqualTo("a");
    }

    @Test
    public void findByCopiesId(){

        Optional<Livre> livre = iLivreServiceMock.findByCopiesId(1L);
        assertThat(livre.get().getTitre()).isEqualTo("a");
        assertThat(livre.get().getTitre()).isNotEqualTo("b");

        Optional<Livre> lvr = iLivreServiceMock.findById(4L);
        assertThat(lvr.get().getTitre()).isEqualTo("b");
        assertThat(lvr.get().getTitre()).isNotEqualTo("a");
    }

    @Test(expected = Exception.class)
    public void testEmptyLivres(){
        List<Livre> lvrs = new ArrayList<>();
        try {
            lvrs.get(0);
        }catch (LivresNotFoundException e){
            assertThat(e.getMessage()).isEqualTo(("Il n'y a pas de livres"));
        }
    }

    @Test
    public void testDateDeRetourLaplusProche(){

        List<Emprunt> empruntList = new ArrayList<>();
        List<Copie> copieList = new ArrayList<>();
        Date date = new Date();

        Livre livre = new Livre();
        livre.setId(1L);

        Copie copie = new Copie();
        copie.setId(4L);
        copie.setEmprunts(empruntList);
        copieList.add(copie);

        Emprunt emprunt = new Emprunt();
        emprunt.setDateDeFinEmprunt(date);
        empruntList.add(emprunt);

        Mockito.when(iCopieServiceMock.findAllByLivreId(livre.getId())).thenReturn(copieList);
        Mockito.when(iEmpruntServiceMock.findAllByCopie_IdAndDateRetourIsNull(copie.getId())).thenReturn(empruntList);

        iLivreServiceMock.dateDeRetourLaplusProche(livre);
        assertThat(livre.getDateRetourLaPlusProche()).isEqualTo(date);
    }

}
