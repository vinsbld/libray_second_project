package com.bibliotheque.microservicemyclient.proxies;

import com.bibliotheque.microservicemyclient.bean.UtilisateurBean;
import com.bibliotheque.microservicemyclient.configurations.FeignConfig;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "microservice-myusers")
@FeignClient(name = "zuul-server", contextId = "IMicroserviceMyUsersProxy", configuration = FeignConfig.class, url = "http://localhost:9006")
@RibbonClient(name = "microservice-myusers")
public interface IMicroserviceMyUsersProxy {

    @GetMapping("/{pseudo}/connexion")
    UtilisateurBean connexionUtilisateur(@PathVariable String pseudo);

    @GetMapping("/connexion/{id}")
    UtilisateurBean findById(@PathVariable("id") Long id);
}
