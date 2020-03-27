package com.bibliotheque.microservicemylibrary.batch;

import com.bibliotheque.microservicemylibrary.beans.UtilisateurBean;
import com.bibliotheque.microservicemylibrary.dao.IEmailDao;
import com.bibliotheque.microservicemylibrary.dao.IReservationDao;
import com.bibliotheque.microservicemylibrary.model.Email;
import com.bibliotheque.microservicemylibrary.model.Reservation;
import com.bibliotheque.microservicemylibrary.outils.EmailType;
import com.bibliotheque.microservicemylibrary.proxies.IMicroserviceMyUsersProxy;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MyTaskOne implements Tasklet {


    private final IReservationDao iReservationDao;
    private final IEmailDao iEmailDao;
    private final IMicroserviceMyUsersProxy iMicroserviceMyUsersProxy;
    private final JavaMailSenderImpl sender;

    /**
     * Tache par batch permettant de relancer les utilisateurs qui n'ont pas rendu leurs livres
     */
    public MyTaskOne(IReservationDao iReservationDao, IEmailDao iEmailDao, IMicroserviceMyUsersProxy iMicroserviceMyUsersProxy, JavaMailSenderImpl sender) {
        this.iReservationDao = iReservationDao;
        this.iEmailDao = iEmailDao;
        this.iMicroserviceMyUsersProxy = iMicroserviceMyUsersProxy;
        this.sender = sender;
    }


    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {

        System.out.println("debut du batch de relance");

        Date date = new Date();
       List<Reservation>reservationList = iReservationDao.findAllByDateRetourIsNullAndDateDeFinDuPretBefore(date);

        ArrayList<EmailType> emailType = new ArrayList<>();

        if (reservationList.size() > 0)
            for (Reservation res : reservationList) {
                UtilisateurBean utilisateurBean = iMicroserviceMyUsersProxy.findById(res.getIdUtilisateur());
                emailType.add(new EmailType(utilisateurBean.getEmail(), res.getCopie().getLivre().getTitre(), res.getDateDeFinDuPret().toString()));

            }

        List<EmailType> emailList = new ArrayList<>(emailType);
        this.sendRevival(emailList);

        System.out.println("fin du batch de relance");

        return RepeatStatus.FINISHED;
    }

    public void sendRevival(List<EmailType> emailList){

        Email email = iEmailDao.findByName("relance");

        for (EmailType e: emailList) {
            String text = email.getContenu()
                    .replace("[LIVRE_TITRE]", e.getTitre())
                            .replace("[DATE_FIN]", e.getDateDeFinDuPret());
            this.sendSimpleMessage(e.getEmail(),email.getObjet(),text);
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
