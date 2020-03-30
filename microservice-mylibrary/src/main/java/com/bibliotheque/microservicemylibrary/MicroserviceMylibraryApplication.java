package com.bibliotheque.microservicemylibrary;

import com.bibliotheque.microservicemylibrary.dao.IEmailDao;
import com.bibliotheque.microservicemylibrary.model.Copie;
import com.bibliotheque.microservicemylibrary.model.Email;
import com.bibliotheque.microservicemylibrary.model.Livre;
import com.bibliotheque.microservicemylibrary.model.Reservation;
import com.bibliotheque.microservicemylibrary.service.copie.ICopieService;
import com.bibliotheque.microservicemylibrary.service.livre.ILivreService;
import com.bibliotheque.microservicemylibrary.service.reservation.IReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
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
	IReservationService iReservationService;

	@Autowired
	IEmailDao iEmailDao;

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
		copieLivre.setDisponible(true);
		iCopieService.save(copieLivre);

		Copie copieLivreLivre = new Copie();
		copieLivreLivre.setLivre(livre);
		copieLivreLivre.setIsbn(5424);
		copieLivreLivre.setDisponible(true);
		iCopieService.save(copieLivreLivre);

		Copie copie1 = new Copie();
		copie1.setLivre(livre1);
		copie1.setIsbn(5528);
		copie1.setDisponible(false);
		iCopieService.save(copie1);

		Copie copieLivre1 = new Copie();
		copieLivre1.setLivre(livre1);
		copieLivre1.setIsbn(1958);
		copieLivre1.setDisponible(true);
		iCopieService.save(copieLivre1);

		Reservation reservation = new Reservation();
		reservation.setCopie(copie);
		reservation.setIdUtilisateur(1L);
		reservation.setDateDeDebutPret(new Date());
		reservation.setDateDeFinDuPret(iReservationService.add4Weeks(reservation.getDateDeDebutPret()));
		reservation.setProlongerPret(false);
		reservation.setRendu(false);
		iReservationService.save(reservation);

		Reservation reservation1 = new Reservation();
		reservation1.setCopie(copie1);
		reservation1.setIdUtilisateur(1L);
		reservation1.setDateDeDebutPret(new Date());
		reservation1.setDateDeFinDuPret(new GregorianCalendar(2020, Calendar.FEBRUARY, 24).getTime());
		reservation1.setProlongerPret(false);
		reservation.setRendu(false);
		iReservationService.save(reservation1);

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
