package com.bibliotheque.microservicemylibrary.service;

import com.bibliotheque.microservicemylibrary.dao.ReservationDao;
import com.bibliotheque.microservicemylibrary.model.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private ReservationDao reservationDao;

    @Override
    public Date add4Weeks(Date date) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setGregorianChange(date);
        gregorianCalendar.add(GregorianCalendar.WEEK_OF_MONTH, 4);
        return gregorianCalendar.getTime();
    }

    @Override
    public List<Reservation> findAllByIdUtilisateur(Long id) {
        return reservationDao.findAllByIdUtilisateur(id);
    }
}
