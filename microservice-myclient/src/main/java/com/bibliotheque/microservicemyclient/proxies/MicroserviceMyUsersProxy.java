package com.bibliotheque.microservicemyclient.proxies;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@FeignClient(name = "microservice-myusers", url = "localhost:9002")
public interface MicroserviceMyUsersProxy {



    @GetMapping("/profil")
    String afficherUnProfilUtilisateur();


}
