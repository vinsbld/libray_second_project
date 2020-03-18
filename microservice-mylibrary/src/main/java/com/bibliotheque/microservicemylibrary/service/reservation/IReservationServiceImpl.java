package com.bibliotheque.microservicemylibrary.service.reservation;

import com.bibliotheque.microservicemylibrary.dao.IReservationDao;
import com.bibliotheque.microservicemylibrary.model.Reservation;
import com.bibliotheque.microservicemylibrary.service.copie.ICopieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.*;

@Service
public class IReservationServiceImpl implements IReservationService {

    @Autowired
    private IReservationDao iReservationDao;

    @Override
    public Date add4Weeks(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.WEEK_OF_MONTH, 4);
    return calendar.getTime();}


    @Override
    public List<Reservation> findAllByIdUtilisateur(Long id) {
        return iReservationDao.findAllByIdUtilisateur(id);
    }

    @Override
    public void addReservation(Reservation reservation){
        iReservationDao.save(reservation);
    }

    @Override
    public void prolongerPret(Long id, Reservation reservation){
        Date date = new Date();
        reservation.setDateDeDebutPret(date);
        reservation.setDateDeFinDuPret(add4Weeks(date));
        iReservationDao.save(reservation);
    }
}

