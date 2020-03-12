package com.bibliotheque.microservicemylibrary;

import com.bibliotheque.microservicemylibrary.dao.ICopieDao;
import com.bibliotheque.microservicemylibrary.dao.ILivreDao;
import com.bibliotheque.microservicemylibrary.dao.IReservationDao;
import com.bibliotheque.microservicemylibrary.model.Copie;
import com.bibliotheque.microservicemylibrary.model.Livre;
import com.bibliotheque.microservicemylibrary.model.Reservation;
import com.bibliotheque.microservicemylibrary.service.reservation.IReservationServiceImpl;
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
		ILivreDao ILivreDao;

		@Autowired
        ICopieDao ICopieDao;

		@Autowired
		IReservationDao IReservationDao;

		@Autowired
		IReservationServiceImpl reservationService;

		@PostConstruct
		private void postConstruct(){

			Livre livre = new Livre();
			livre.setTitre("1984");
			livre.setNomAuteur("Orwel");
			livre.setPrenomAuteur("George");
			livre.setDateEdition(new Date(04/04/2020));
			ILivreDao.save(livre);

			Livre livre1 = new Livre();
			livre1.setTitre("Le meilleur des mondes");
			livre1.setPrenomAuteur("Aldous");
			livre1.setNomAuteur("Huxley");
			livre1.setDateEdition(new Date(03/03/2020));
			ILivreDao.save(livre1);

			Copie copie = new Copie();
			copie.setLivre(livre);
			ICopieDao.save(copie);

			Copie copie1 = new Copie();
			copie1.setLivre(livre1);
			ICopieDao.save(copie1);

			Reservation reservation = new Reservation();
			reservation.setCopie(copie);
			reservation.setIdUtilisateur(1L);
			reservation.setDateDeDebutPret(new Date());
			reservation.setDateDeFinDuPret(reservationService.add4Weeks(reservation.getDateDeDebutPret()));
			reservation.setProlongerPret(false);
			IReservationDao.save(reservation);

			Reservation reservation1 = new Reservation();
			reservation1.setCopie(copie1);
			reservation1.setIdUtilisateur(1L);
			reservation1.setDateDeDebutPret(new Date());
			reservation1.setDateDeFinDuPret(reservationService.add4Weeks(reservation.getDateDeDebutPret()));
			reservation1.setProlongerPret(false);
			IReservationDao.save(reservation1);

		}


}
