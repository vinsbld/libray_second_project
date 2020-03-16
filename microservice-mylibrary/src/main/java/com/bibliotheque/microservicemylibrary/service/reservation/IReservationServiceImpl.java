package com.bibliotheque.microservicemylibrary.service.reservation;

import com.bibliotheque.microservicemylibrary.dao.IReservationDao;
import com.bibliotheque.microservicemylibrary.model.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@Service
public class IReservationServiceImpl implements IReservationService {

    @Autowired
    private IReservationDao iReservationDao;

    @Override
    public Date add4Weeks(Date date) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setGregorianChange(date);
        gregorianCalendar.add(GregorianCalendar.WEEK_OF_MONTH, 4);
        return gregorianCalendar.getTime();
    }

    @Override
    public List<Reservation> findAllByIdUtilisateur(Long id) {

        return iReservationDao.findAllByIdUtilisateur(id);
    }

    @Override
    public void addReservation(Long id, Reservation reservation){
        iReservationDao.save(id, reservation);
    }
}

