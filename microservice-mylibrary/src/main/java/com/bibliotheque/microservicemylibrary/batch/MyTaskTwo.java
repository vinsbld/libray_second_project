package com.bibliotheque.microservicemylibrary.batch;


import com.bibliotheque.microservicemylibrary.beans.UtilisateurBean;
import com.bibliotheque.microservicemylibrary.dao.IEmailDao;
import com.bibliotheque.microservicemylibrary.dao.IReservationDao;
import com.bibliotheque.microservicemylibrary.model.Email;
import com.bibliotheque.microservicemylibrary.model.Reservation;
import com.bibliotheque.microservicemylibrary.model.StateEnum;
import com.bibliotheque.microservicemylibrary.outils.EmailTypeReservation;
import com.bibliotheque.microservicemylibrary.proxies.IMicroserviceMyUsersProxy;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MyTaskTwo implements Tasklet {


    private final IReservationDao iReservationDao;
    private final IEmailDao iEmailDao;
    private final IMicroserviceMyUsersProxy iMicroserviceMyUsersProxy;
    private final JavaMailSenderImpl sender;

    public MyTaskTwo(IReservationDao iReservationDao, IEmailDao iEmailDao, IMicroserviceMyUsersProxy iMicroserviceMyUsersProxy, JavaMailSenderImpl sender) {
        this.iReservationDao = iReservationDao;
        this.iEmailDao = iEmailDao;
        this.iMicroserviceMyUsersProxy = iMicroserviceMyUsersProxy;
        this.sender = sender;
    }


    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {

        SimpleDateFormat oldFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date dateDuJour = new Date();

        List<Reservation> reservationList = iReservationDao.findByEmailEnvoyerAndStateEnums(true, StateEnum.enCours);
        if (reservationList.size() > 0) {
            for (Reservation r : reservationList) {
                Date deadLine = DateUtils.addDays(r.getDateEnvoiEmail(), 2);
                if (dateDuJour.after(deadLine)) {
                    System.out.println("debut du batch annulation réservation");
                    ArrayList<EmailTypeReservation> emailTypeReservations = new ArrayList<>();
                    UtilisateurBean utilisateurBean = iMicroserviceMyUsersProxy.findById(r.getIdUtilisateur());
                    emailTypeReservations.add(new EmailTypeReservation(utilisateurBean.getEmail(), r.getLivre().getTitre(), oldFormat.format(deadLine)));
                    r.setStateEnums(StateEnum.annuler);
                    iReservationDao.save(r);
                    List<EmailTypeReservation> emailTypeReservationList = new ArrayList<>(emailTypeReservations);
                    this.sendCancelRevival(emailTypeReservationList);
                    System.out.println("fin du batch annulation réservation");
                }
            }
        }


       List<Reservation> reservations = iReservationDao.findByLivreAndStateEnumsOrderByDateDeReservationAsc(reservationList.get(0).getLivre(), StateEnum.enCours);
        if (reservations.size() > 0){
            System.out.println("debut du batch réservation");
            Reservation reservation = reservations.get(0);
            reservation.setDateEnvoiEmail(dateDuJour);
            reservation.setEmailEnvoyer(true);
            Date dateOfDeadLine = DateUtils.addDays(reservation.getDateEnvoiEmail(), 2);
            ArrayList<EmailTypeReservation> emailTypeReservations1 = new ArrayList<>();
            UtilisateurBean utilisateurBean = iMicroserviceMyUsersProxy.findById(reservation.getIdUtilisateur());
            emailTypeReservations1.add(new EmailTypeReservation(utilisateurBean.getEmail(), reservation.getLivre().getTitre(), oldFormat.format(dateOfDeadLine)));
            iReservationDao.save(reservation);
            List<EmailTypeReservation> emailTypeReservationList1 = new ArrayList<>(emailTypeReservations1);
            this.sendRevival(emailTypeReservationList1);
            System.out.println("fin du batch de réservation");
        }

        return RepeatStatus.FINISHED;
    }


    private void sendCancelRevival(List<EmailTypeReservation> emailTypeReservations){

        Email email = iEmailDao.findByName("annulationReservation");

        for (EmailTypeReservation e : emailTypeReservations) {
            String text = email.getContenu()
                    .replace("[LIVRE_TITRE]", e.getTitre())
                    .replace("[DEADLINE]", e.getDeadLine());
            this.sendSimpleMessage(e.getEmail(), email.getObjet(), text);
        }

    }

    private void sendRevival(List<EmailTypeReservation> emailTypeReservations){

        Email email = iEmailDao.findByName("reservation");

        for (EmailTypeReservation e : emailTypeReservations) {
            String text = email.getContenu()
                    .replace("[LIVRE_TITRE]", e.getTitre())
                    .replace("[DEADLINE]", e.getDeadLine());
            this.sendSimpleMessage(e.getEmail(), email.getObjet(), text);
        }

    }

    private void sendSimpleMessage(String email, String objet, String contenu) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject(objet);
        message.setText(contenu);
        sender.send(message);
    }
}

