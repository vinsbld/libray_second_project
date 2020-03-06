package com.bibliotheque.microservicemyclient.proxies;

import com.bibliotheque.microservicemyclient.bean.UtilisateurBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;


@FeignClient(name = "microservice-myusers", url = "localhost:9002")
public interface MicroserviceMyUsersProxy {

    @GetMapping("/{pseudo}/connexion")
    UtilisateurBean connexionUtilisateur(@PathVariable String pseudo);


}
