package com.bibliotheque.microservicemylibrary.servicesTestUnitaire;

import com.bibliotheque.microservicemylibrary.dao.ICopieDao;
import com.bibliotheque.microservicemylibrary.model.Copie;
import com.bibliotheque.microservicemylibrary.model.Livre;
import com.bibliotheque.microservicemylibrary.service.copie.ICopieServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class CopieServiceUnitTest {

    @Mock
    private ICopieDao iCopieDaoMock;

    @Autowired
    @InjectMocks
    private ICopieServiceImpl iCopieServiceMock;


    private List<Copie> copiesLivre_1 = new ArrayList<>();
    private List<Copie> copiesLivre_2 = new ArrayList<>();
    private List<Livre> livreList = new ArrayList<>();

    @Before
    public void setUp(){

        Livre livre1 = new Livre();
        livre1.setId(1L);
        livre1.setCopies(copiesLivre_1);
        livre1.setTitre("a");
        livreList.add(livre1);

        Livre livre2 = new Livre();
        livre2.setId(2L);
        livre2.setCopies(copiesLivre_2);
        livre2.setTitre("b");
        livreList.add(livre2);

        Copie copie1 = new Copie();
        copie1.setLivre(livre1);
        copie1.setId(1L);
        copie1.setDisponible(true);
        copiesLivre_1.add(copie1);

        Copie copie2 = new Copie();
        copie2.setLivre(livre1);
        copie2.setId(2L);
        copie2.setDisponible(false);
        copiesLivre_1.add(copie2);

        Copie copie3 = new Copie();
        copie3.setLivre(livre2);
        copie3.setId(3L);
        copie3.setDisponible(false);
        copiesLivre_2.add(copie3);

        Copie copie4 = new Copie();
        copie4.setLivre(livre2);
        copie4.setId(4L);
        copie4.setDisponible(true);
        copiesLivre_2.add(copie4);


        Mockito.when(iCopieDaoMock.findAllByLivreId(livre1.getId())).thenReturn(livre1.getCopies());
        Mockito.when(iCopieDaoMock.findAllByLivreId(livre2.getId())).thenReturn(livre2.getCopies());

        Mockito.when(iCopieDaoMock.findById(copie1.getId())).thenReturn(Optional.of(copie1));
        Mockito.when(iCopieDaoMock.findById(copie2.getId())).thenReturn(Optional.of(copie2));
        Mockito.when(iCopieDaoMock.findById(copie3.getId())).thenReturn(Optional.of(copie3));
        Mockito.when(iCopieDaoMock.findById(copie4.getId())).thenReturn(Optional.of(copie4));

        Mockito.when(iCopieDaoMock.getCopieLivresDisponibles(livre1.getId())).thenReturn(copiesLivre_1);
        Mockito.when(iCopieDaoMock.getCopieLivresDisponibles(livre2.getId())).thenReturn(copiesLivre_2);

        Mockito.when(iCopieDaoMock.getCopieLivresIndisponibles(livre1.getId())).thenReturn(copiesLivre_1);
        Mockito.when(iCopieDaoMock.getCopieLivresIndisponibles(livre2.getId())).thenReturn(copiesLivre_2);
    }

    @Test
    public void findAllByLivreId(){
        List<Copie> copies_1 = iCopieServiceMock.findAllByLivreId(1L);
        assertThat(copies_1.size()).isEqualTo(2);
        assertThat(copies_1.size()).isNotEqualTo(1);

        List<Copie> copies_2 = iCopieServiceMock.findAllByLivreId(2L);
        assertThat(copies_2.size()).isEqualTo(2);
        assertThat(copies_2.size()).isNotZero();
    }

    @Test
    public void findById(){
        Optional<Copie> copie_1 = iCopieServiceMock.findById(2L);
        assertThat(copie_1.get().getLivre().getTitre()).isEqualTo("a");
        assertThat(copie_1.get().getLivre().getTitre()).isNotEqualTo("b");
        assertThat(copie_1.get().isDisponible()).isEqualTo(false);

        Optional<Copie> copie_2 = iCopieServiceMock.findById(4L);
        assertThat(copie_2.get().getLivre().getTitre()).isEqualTo("b");
        assertThat(copie_2.get().getLivre().getTitre()).isNotEqualTo("a");
        assertThat(copie_2.get().isDisponible()).isEqualTo(true);
    }


}
