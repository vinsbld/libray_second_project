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

		Livre livre = new Livre();
		livre.setTitre("1984");
		livre.setNomAuteur("Orwel");
		livre.setPrenomAuteur("George");
		livre.setDateEdition(new GregorianCalendar(2020,04,02).getTime());
		livre.setEditeur("FOLIO");
		iLivreService.save(livre);

		Livre livre1 = new Livre();
		livre1.setTitre("Le meilleur des mondes");
		livre1.setPrenomAuteur("Aldous");
		livre1.setNomAuteur("Huxley");
		livre1.setDateEdition(new GregorianCalendar(2017,10,04).getTime());
		livre1.setEditeur("POCHE");
		iLivreService.save(livre1);

		Copie copie = new Copie();
		copie.setLivre(livre);
		copie.setIsbn(3458);
		copie.setDisponible(false);
		iCopieService.save(copie);

		Copie copieLivre = new Copie();
		copieLivre.setLivre(livre);
		copieLivre.setIsbn(5422);
		copieLivre.setDisponible(false);
		iCopieService.save(copieLivre);

		Copie copieLivreLivre = new Copie();
		copieLivreLivre.setLivre(livre);
		copieLivreLivre.setIsbn(5424);
		copieLivreLivre.setDisponible(false);
		iCopieService.save(copieLivreLivre);

		Emprunt emprunt = new Emprunt();
		emprunt.setCopie(copie);
		emprunt.setIdUtilisateur(1L);
		emprunt.setDateDeDebutEmprunt(new Date());
		emprunt.setDateDeFinEmprunt(iEmpruntService.add4Weeks(emprunt.getDateDeDebutEmprunt()));
		emprunt.setProlongerEmprunt(false);
		emprunt.setRendu(false);
		iEmpruntService.save(emprunt);

		Emprunt emprunt3 = new Emprunt();
		emprunt3.setCopie(copieLivre);
		emprunt3.setIdUtilisateur(3L);
		emprunt3.setDateDeDebutEmprunt(new GregorianCalendar(2020, Calendar.JULY, 25).getTime());
		emprunt3.setDateDeFinEmprunt(iEmpruntService.add4Weeks(emprunt3.getDateDeDebutEmprunt()));
		emprunt3.setProlongerEmprunt(false);
		emprunt3.setRendu(false);
		iEmpruntService.save(emprunt3);

		Emprunt emprunt4 = new Emprunt();
		emprunt4.setCopie(copieLivreLivre);
		emprunt4.setIdUtilisateur(2L);
		emprunt4.setDateDeDebutEmprunt(new GregorianCalendar(2020, Calendar.JULY, 31).getTime());
		emprunt4.setDateDeFinEmprunt(iEmpruntService.add4Weeks(emprunt4.getDateDeDebutEmprunt()));
		emprunt4.setProlongerEmprunt(false);
		emprunt4.setRendu(false);
		iEmpruntService.save(emprunt4);

		Reservation reservation = new Reservation();
		reservation.setIdUtilisateur(1L);
		reservation.setDateDeReservation(new Date());
		reservation.setCopie(copieLivre);
		iReservationService.save(reservation);

		Reservation reservation1 = new Reservation();
		reservation1.setIdUtilisateur(2L);
		reservation1.setCopie(copieLivre);
		reservation1.setDateDeReservation(new GregorianCalendar(2020, Calendar.AUGUST, 1).getTime());
		iReservationService.save(reservation1);

		Reservation reservation2 = new Reservation();
		reservation2.setIdUtilisateur(3L);
		reservation2.setCopie(copieLivre);
		reservation2.setDateDeReservation(new GregorianCalendar(2020, Calendar.AUGUST, 2).getTime());
		iReservationService.save(reservation2);

		Copie copie1 = new Copie();
		copie1.setLivre(livre1);
		copie1.setIsbn(5528);
		copie1.setDisponible(true);
		iCopieService.save(copie1);

		Copie copieLivre1 = new Copie();
		copieLivre1.setLivre(livre1);
		copieLivre1.setIsbn(1958);
		copieLivre1.setDisponible(false);
		iCopieService.save(copieLivre1);

		Emprunt emprunt2 = new Emprunt();
		emprunt2.setCopie(copieLivre1);
		emprunt2.setIdUtilisateur(2L);
		emprunt2.setDateDeDebutEmprunt(new Date());
		emprunt2.setDateDeFinEmprunt(iEmpruntService.add4Weeks(emprunt2.getDateDeDebutEmprunt()));
		emprunt2.setProlongerEmprunt(false);
		iEmpruntService.save(emprunt2);


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

	}

}
