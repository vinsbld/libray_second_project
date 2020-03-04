package com.bibliotheque.microservicemylibrary.service;

import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.GregorianCalendar;

@Service
public class ReservationServiceImpl implements ReservationService {

    @Override
    public Date add4Weeks(Date date) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setGregorianChange(date);
        gregorianCalendar.add(GregorianCalendar.MONTH, 1 );
        return gregorianCalendar.getTime();
    }
}
