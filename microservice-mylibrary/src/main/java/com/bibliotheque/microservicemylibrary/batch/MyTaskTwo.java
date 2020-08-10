package com.bibliotheque.microservicemylibrary.batch;


import com.bibliotheque.microservicemylibrary.dao.IEmailDao;
import com.bibliotheque.microservicemylibrary.dao.IReservationDao;
import com.bibliotheque.microservicemylibrary.model.Reservation;
import com.bibliotheque.microservicemylibrary.model.StateEnum;
import com.bibliotheque.microservicemylibrary.proxies.IMicroserviceMyUsersProxy;
import com.bibliotheque.microservicemylibrary.service.reservation.IReservationService;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.text.SimpleDateFormat;
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

        System.out.println("debut du batch de réservation");
        SimpleDateFormat oldFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date dateDuJour = new Date();

        List<Reservation> reservationList = iReservationDao.findByEmailEnvoyerAndStateEnums(true, StateEnum.enCours);

        if (reservationList.size() > 0){
            for (Reservation r : reservationList) {
                Date deadLine = DateUtils.addDays(r.getDateEmail(), 2);
                if (dateDuJour.after(deadLine)){
                    r.setStateEnums(StateEnum.annuler);

                    List<Reservation> reservations = iReservationDao.findByLivreAndStateEnumsOrderByDateDeReservationAsc(r.getLivre(), StateEnum.enCours);
                    if (reservations.size() > 0){
                        Reservation envoiMailReservation = reservations.get(0);
                        envoiMailReservation.setDateEmail(dateDuJour);
                    }
                }
            }
        }

        return null;
    }
}

