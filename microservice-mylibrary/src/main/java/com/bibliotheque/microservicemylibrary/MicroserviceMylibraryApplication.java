package com.bibliotheque.microservicemylibrary;

import com.bibliotheque.microservicemylibrary.dao.IEmailDao;
import com.bibliotheque.microservicemylibrary.model.*;
import com.bibliotheque.microservicemylibrary.service.copie.ICopieService;
import com.bibliotheque.microservicemylibrary.service.livre.ILivreService;
import com.bibliotheque.microservicemylibrary.service.emprunt.IEmpruntService;
import com.bibliotheque.microservicemylibrary.service.reservation.IReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.PostConstruct;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


@SpringBootApplication
@EnableFeignClients("com.bibliotheque.microservicemylibrary")
@EnableDiscoveryClient
@EnableScheduling
public class MicroserviceMylibraryApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceMylibraryApplication.class, args);}

	@Autowired
	ILivreService iLivreService;

	@Autowired
	ICopieService iCopieService;

	@Autowired
	IEmpruntService iEmpruntService;

	@Autowired
	IEmailDao iEmailDao;

	@Autowired
	IReservationService iReservationService;

	@PostConstruct
	private void postConstruct(){

		Livre livre1984 = new Livre();
		livre1984.setTitre("1984");
		livre1984.setNomAuteur("Orwel");
		livre1984.setPrenomAuteur("George");
		livre1984.setDateEdition(new GregorianCalendar(2020,04,02).getTime());
		livre1984.setEditeur("FOLIO");
		iLivreService.save(livre1984);

		Copie copie1984c1 = new Copie();
		copie1984c1.setLivre(livre1984);
		copie1984c1.setIsbn(3458);
		copie1984c1.setDisponible(false);
		iCopieService.save(copie1984c1);

		Emprunt emprunt1984c1 = new Emprunt();
		emprunt1984c1.setCopie(copie1984c1);
		emprunt1984c1.setIdUtilisateur(1L);
		emprunt1984c1.setDateDeDebutEmprunt(new Date());
		emprunt1984c1.setDateDeFinEmprunt(iEmpruntService.add4Weeks(emprunt1984c1.getDateDeDebutEmprunt()));
		emprunt1984c1.setProlongerEmprunt(false);
		emprunt1984c1.setRendu(false);
		iEmpruntService.save(emprunt1984c1);


		Copie copie1984c2 = new Copie();
		copie1984c2.setLivre(livre1984);
		copie1984c2.setIsbn(5422);
		copie1984c2.setDisponible(false);
		iCopieService.save(copie1984c2);

		Emprunt emprunt1984c2 = new Emprunt();
		emprunt1984c2.setCopie(copie1984c2);
		emprunt1984c2.setIdUtilisateur(3L);
		emprunt1984c2.setDateDeDebutEmprunt(new GregorianCalendar(2020, Calendar.JULY, 25).getTime());
		emprunt1984c2.setDateDeFinEmprunt(iEmpruntService.add4Weeks(emprunt1984c2.getDateDeDebutEmprunt()));
		emprunt1984c2.setProlongerEmprunt(false);
		emprunt1984c2.setRendu(false);
		iEmpruntService.save(emprunt1984c2);


		Copie copie1984c3 = new Copie();
		copie1984c3.setLivre(livre1984);
		copie1984c3.setIsbn(5424);
		copie1984c3.setDisponible(true);
		iCopieService.save(copie1984c3);


		Livre livreLmdMondes = new Livre();
		livreLmdMondes.setTitre("Le meilleur des mondes");
		livreLmdMondes.setPrenomAuteur("Aldous");
		livreLmdMondes.setNomAuteur("Huxley");
		livreLmdMondes.setDateEdition(new GregorianCalendar(2017,10,04).getTime());
		livreLmdMondes.setEditeur("POCHE");
		iLivreService.save(livreLmdMondes);

		Copie copieLmdMondesCp_1 = new Copie();
		copieLmdMondesCp_1.setLivre(livreLmdMondes);
		copieLmdMondesCp_1.setIsbn(5528);
		copieLmdMondesCp_1.setDisponible(false);
		iCopieService.save(copieLmdMondesCp_1);

		Emprunt emprunt_copieLmdMondesCp_1 = new Emprunt();
		emprunt_copieLmdMondesCp_1.setCopie(copieLmdMondesCp_1);
		emprunt_copieLmdMondesCp_1.setIdUtilisateur(1L);
		emprunt_copieLmdMondesCp_1.setDateDeDebutEmprunt(new GregorianCalendar(2020,8,04).getTime());
		emprunt_copieLmdMondesCp_1.setDateDeFinEmprunt(iEmpruntService.add4Weeks(emprunt_copieLmdMondesCp_1.getDateDeDebutEmprunt()));
		emprunt_copieLmdMondesCp_1.setProlongerEmprunt(false);
		iEmpruntService.save(emprunt_copieLmdMondesCp_1);

		Copie copieLmdMondesCp_2 = new Copie();
		copieLmdMondesCp_2.setLivre(livreLmdMondes);
		copieLmdMondesCp_2.setIsbn(1958);
		copieLmdMondesCp_2.setDisponible(false);
		iCopieService.save(copieLmdMondesCp_2);

		Emprunt emprunt_copieLmdMondesCp_2 = new Emprunt();
		emprunt_copieLmdMondesCp_2.setCopie(copieLmdMondesCp_2);
		emprunt_copieLmdMondesCp_2.setIdUtilisateur(2L);
		emprunt_copieLmdMondesCp_2.setDateDeDebutEmprunt(new Date());
		emprunt_copieLmdMondesCp_2.setDateDeFinEmprunt(iEmpruntService.add4Weeks(emprunt_copieLmdMondesCp_2.getDateDeDebutEmprunt()));
		emprunt_copieLmdMondesCp_2.setProlongerEmprunt(false);
		iEmpruntService.save(emprunt_copieLmdMondesCp_2);

		Reservation reservationLivreLmdMondesR_1 = new Reservation();
		reservationLivreLmdMondesR_1.setLivre(livreLmdMondes);
		reservationLivreLmdMondesR_1.setDateDeReservation(new Date());
		reservationLivreLmdMondesR_1.setStateEnums(StateEnum.enCours);
		reservationLivreLmdMondesR_1.setIdUtilisateur(3L);
		reservationLivreLmdMondesR_1.setPosition(3);
		iReservationService.save(reservationLivreLmdMondesR_1);

		Reservation reservationLivreLmdMondesR_2 = new Reservation();
		reservationLivreLmdMondesR_2.setLivre(livreLmdMondes);
		reservationLivreLmdMondesR_2.setDateDeReservation(new GregorianCalendar(2020,7,05).getTime());
		reservationLivreLmdMondesR_2.setStateEnums(StateEnum.enCours);
		reservationLivreLmdMondesR_2.setIdUtilisateur(4L);
		reservationLivreLmdMondesR_2.setPosition(2);
		iReservationService.save(reservationLivreLmdMondesR_2);

		Reservation reservationLivreLmdMondesR_3 = new Reservation();
		reservationLivreLmdMondesR_3.setLivre(livreLmdMondes);
		reservationLivreLmdMondesR_3.setDateDeReservation(new GregorianCalendar(2020,7,07).getTime());
		reservationLivreLmdMondesR_3.setStateEnums(StateEnum.enCours);
		reservationLivreLmdMondesR_3.setIdUtilisateur(5L);
		reservationLivreLmdMondesR_3.setPosition(1);
		reservationLivreLmdMondesR_3.setDateEnvoiEmail(new GregorianCalendar(2020,5,07).getTime());
		reservationLivreLmdMondesR_3.setEmailEnvoyer(true);
		iReservationService.save(reservationLivreLmdMondesR_3);




		Email email = new Email();
		email.setName("relance");
		email.setObjet("relance pour livre non rendu");
		email.setContenu("Bonjour, \n "+
				"\n"+
				"\tVous deviez rendre le livre [LIVRE_TITRE] à la blibliothèque au plus tard à la date : [DATE_FIN].\n" +
				"à ce jour nous n'avons toujours pas enregistré le retour de ce livre.\n" +
				"Nous vous invitons à régulariser la situation dès à présent.\n" +
				"\n"+
				"Cordialement.");
		iEmailDao.save(email);


		Email emailReservation = new Email();
		emailReservation.setName("reservation");
		emailReservation.setObjet("reservation d'ouvrage");
		emailReservation.setContenu("Bonjour, \n "+
				"\n"+
				"\tL'ouvrage [LIVRE_TITRE] est de nouveau disponible à la blibliothèque. \n" +
				"Vous pouvez venir emprunter l'ouvrage jusqu'au : [DEADLINE] \n" +
				"passer cette date l'ouvrage sera remis en circulation."+
				"\n" +
				"Cordialement.");
		iEmailDao.save(emailReservation);


		Email emailAnnulationReservation = new Email();
		emailAnnulationReservation.setName("annulationReservation");
		emailAnnulationReservation.setObjet("annulation de reservation d'ouvrage");
		emailAnnulationReservation.setContenu("Bonjour, \n "+
				"\n"+
				"\tSauf erreur de notre part, \n" +
				"Vous n'êtes pas venu emprunter l'ouvrage [LIVRE_TITRE] à la blibliothèque dans le délai qui vous était imparti  \n" +
				"Vous aviez jusqu'au : [DEADLINE]  pour venir l'emprunter." +
				"L'ouvrage a donc été remis en circulation  \n" +
				"\n" +
				"Cordialement.");
		iEmailDao.save(emailAnnulationReservation);

	}

}
