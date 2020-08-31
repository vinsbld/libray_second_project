package com.bibliotheque.microservicemylibrary.servicesTestUnitaire;

import com.bibliotheque.microservicemylibrary.dao.ILivreDao;
import com.bibliotheque.microservicemylibrary.exeptions.LivresNotFoundException;
import com.bibliotheque.microservicemylibrary.model.Copie;
import com.bibliotheque.microservicemylibrary.model.Emprunt;
import com.bibliotheque.microservicemylibrary.model.Livre;
import com.bibliotheque.microservicemylibrary.service.copie.ICopieService;
import com.bibliotheque.microservicemylibrary.service.emprunt.IEmpruntService;
import com.bibliotheque.microservicemylibrary.service.livre.ILivreServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class LivreServiceUnitTest {

    @Mock
    private ICopieService iCopieServiceMock;

    @Mock
    private IEmpruntService iEmpruntServiceMock;

    @Mock
    private ILivreDao iLivreDao;


    @Autowired
    @InjectMocks
    private ILivreServiceImpl iLivreServiceMock;


    @Test
    public void testEmptyLivres(){
        List<Livre> lvrs = new ArrayList<>();

        Mockito.when(iLivreDao.findAll()).thenReturn(lvrs);
        try {
            iLivreServiceMock.livres();
        }catch (LivresNotFoundException e){
            assertThat(e.getMessage()).isEqualTo("Il n'y a pas de livres");
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
