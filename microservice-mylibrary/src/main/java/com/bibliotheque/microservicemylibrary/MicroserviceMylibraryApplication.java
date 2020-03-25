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
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.PostConstruct;
import java.util.Date;


@SpringBootApplication
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
		livre.setDateEdition(new Date(2020,04,02));
		livre.setEditeur("FOLIO");
		iLivreService.save(livre);

		Livre livre1 = new Livre();
		livre1.setTitre("Le meilleur des mondes");
		livre1.setPrenomAuteur("Aldous");
		livre1.setNomAuteur("Huxley");
		livre1.setDateEdition(new Date(2017,10,04));
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
		iReservationService.save(reservation);

		Reservation reservation1 = new Reservation();
		reservation1.setCopie(copie1);
		reservation1.setIdUtilisateur(1L);
		reservation1.setDateDeDebutPret(new Date());
		reservation1.setDateDeFinDuPret(iReservationService.add4Weeks(reservation.getDateDeDebutPret()));
		reservation1.setProlongerPret(false);
		iReservationService.save(reservation1);

		Email email = new Email();
		email.setName("relance");
		email.setObjet("relance pour rendu");
		email.setContenu("\n" +
				"\tVous devriez rendre le livre [LIVRE_TITRE] à la blibliothèque au plus tard à la date : [DATE_FIN].\n" +
				"à ce jour nous n'avons toujours pas enregistré ce retour.\n" +
				"Nous vous demandons de régulariser la situation dès à présent.\n" +
				"Cordialement.'),");
		iEmailDao.save(email);

	}

}
