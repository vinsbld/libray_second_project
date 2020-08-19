package com.bibliotheque.microservicemylibrary.batch;

import com.bibliotheque.microservicemylibrary.beans.UtilisateurBean;
import com.bibliotheque.microservicemylibrary.dao.IEmailDao;
import com.bibliotheque.microservicemylibrary.dao.IEmpruntDao;
import com.bibliotheque.microservicemylibrary.model.Email;
import com.bibliotheque.microservicemylibrary.model.Emprunt;
import com.bibliotheque.microservicemylibrary.outils.EmailType;
import com.bibliotheque.microservicemylibrary.proxies.IMicroserviceMyUsersProxy;
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

public class MyTaskOne implements Tasklet {


    private final IEmpruntDao iEmpruntDao;
    private final IEmailDao iEmailDao;
    private final IMicroserviceMyUsersProxy iMicroserviceMyUsersProxy;
    private final JavaMailSenderImpl sender;

    /**
     * Tache par batch permettant de relancer les utilisateurs qui n'ont pas rendu leurs livres
     */
    public MyTaskOne(IEmpruntDao iEmpruntDao, IEmailDao iEmailDao, IMicroserviceMyUsersProxy iMicroserviceMyUsersProxy, JavaMailSenderImpl sender) {
        this.iEmpruntDao = iEmpruntDao;
        this.iEmailDao = iEmailDao;
        this.iMicroserviceMyUsersProxy = iMicroserviceMyUsersProxy;
        this.sender = sender;
    }


    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {

        System.out.println("debut du batch de relance");

        SimpleDateFormat oldFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
       List<Emprunt> empruntList = iEmpruntDao.findAllByDateRetourIsNullAndAndDateDeFinEmpruntBefore(date);

        ArrayList<EmailType> emailType = new ArrayList<>();

        if (empruntList.size() > 0)
            for (Emprunt res : empruntList) {
                UtilisateurBean utilisateurBean = iMicroserviceMyUsersProxy.findById(res.getIdUtilisateur());
                emailType.add(new EmailType(utilisateurBean.getEmail(), res.getCopie().getLivre().getTitre(), oldFormat.format(res.getDateDeFinEmprunt())));
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
                            .replace("[DATE_FIN]", e.getDateDeFinEmprunt());
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
