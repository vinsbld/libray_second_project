package com.bibliotheque.microservicemyclient.controller;

import com.bibliotheque.microservicemyclient.proxies.MicroserviceMyUsersProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ClientController {

    @Autowired
    MicroserviceMyUsersProxy microserviceMyUsersProxy;

    @RequestMapping("/")
    public String acceuil(Model model){

        return "Acceuil";

    }
}
