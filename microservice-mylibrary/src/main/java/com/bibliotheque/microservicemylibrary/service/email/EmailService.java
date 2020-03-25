package com.bibliotheque.microservicemylibrary.service.email;


import com.bibliotheque.microservicemylibrary.model.Email;
import com.bibliotheque.microservicemylibrary.outils.EmailType;

import javax.mail.MessagingException;
import java.util.List;

public interface EmailService {

    Email findByName(String name);


    void sendSimpleMessage(String email, String objet, String contenu) throws MessagingException;

    void sendRevival(List<EmailType> emailTypeList) throws MessagingException;
}
