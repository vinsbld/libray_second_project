package com.bibliotheque.microservicemylibrary;

import com.bibliotheque.microservicemylibrary.dao.CopieDao;
import com.bibliotheque.microservicemylibrary.dao.LivreDao;
import com.bibliotheque.microservicemylibrary.dao.ReservationDao;
import com.bibliotheque.microservicemylibrary.model.Copie;
import com.bibliotheque.microservicemylibrary.model.Livre;
import com.bibliotheque.microservicemylibrary.model.Reservation;
import com.bibliotheque.microservicemylibrary.service.ReservationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import javax.annotation.PostConstruct;
import java.util.Date;

@SpringBootApplication
@EnableDiscoveryClient
public class MicroserviceMylibraryApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceMylibraryApplication.class, args);}

		@Autowired
		LivreDao livreDao;

		@Autowired
		CopieDao copieDao;

		@Autowired
		ReservationDao reservationDao;

		@Autowired
		ReservationServiceImpl reservationService;

		@PostConstruct
		private void postConstruct(){

			Livre livre = new Livre();
			livre.setTitre("1984");
			livre.setNomAuteur("Orwel");
			livre.setPrenomAuteur("George");
			livre.setDateEdition(new Date());
			livreDao.save(livre);

			Livre livre1 = new Livre();
			livre1.setTitre("Le meilleur des mondes");
			livre1.setPrenomAuteur("Aldous");
			livre1.setNomAuteur("Huxley");
			livre1.setDateEdition(new Date(2019,03,28));
			livreDao.save(livre1);

			Copie copie = new Copie();
			copie.setLivre(livre);
			copie.setNbCopies(4);
			copieDao.save(copie);

			Copie copie1 = new Copie();
			copie1.setLivre(livre1);
			copie1.setNbCopies(2);
			copieDao.save(copie1);

			Reservation reservation = new Reservation();
			reservation.setCopie(copie);
			reservation.setIdUtilisateur(1L);
			reservation.setDateDeDebutPret(new Date());
			reservation.setDateDeFinDuPret(reservationService.add4Weeks(reservation.getDateDeDebutPret()));
			reservation.setProlongerPret(false);
			reservationDao.save(reservation);

			Reservation reservation1 = new Reservation();
			reservation1.setCopie(copie1);
			reservation1.setIdUtilisateur(1L);
			reservation1.setDateDeDebutPret(new Date());
			reservation1.setDateDeFinDuPret(reservationService.add4Weeks(reservation.getDateDeDebutPret()));
			reservation1.setProlongerPret(false);
			reservationDao.save(reservation1);

		}


}
