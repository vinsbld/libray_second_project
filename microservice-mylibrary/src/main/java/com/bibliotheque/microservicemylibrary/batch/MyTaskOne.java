package com.bibliotheque.microservicemylibrary.batch;

import com.bibliotheque.microservicemylibrary.dao.IReservationDao;
import com.bibliotheque.microservicemylibrary.service.reservation.IReservationService;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class MyTaskOne implements Tasklet {


    private final IReservationDao iReservationDao;

    public MyTaskOne(IReservationDao iReservationDao) {
        this.iReservationDao = iReservationDao;
    }


    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {

        System.out.println("debut du batch");

        Date date = new Date();
        iReservationDao.findAllByDateRetourIsNullAndDateDeFinDuPretBefore(date);

        System.out.println("fin du batch");

        return RepeatStatus.FINISHED;
    }
}
