package com.bibliotheque.microservicemylibrary.service.reservation;

import com.bibliotheque.microservicemylibrary.dao.IReservationDao;
import com.bibliotheque.microservicemylibrary.model.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Optional<Reservation> findById(Long id){
        return iReservationDao.findById(id);
    }

    @Override
    public void save(Reservation reservation){
        iReservationDao.save(reservation);
    }
}

