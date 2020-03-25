package com.bibliotheque.microservicemylibrary.proxies;


import com.bibliotheque.microservicemylibrary.beans.UtilisateurBean;
import com.bibliotheque.microservicemylibrary.configurations.FeignConfig;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value = "microservice-myusers")
@FeignClient(name = "zuul-server", contextId = "IMicroserviceMyUsersProxy", configuration = FeignConfig.class, url = "http://localhost:9006")
@RibbonClient(name = "microservice-myusers")
public interface IMicroserviceMyUsersProxy {

    @GetMapping("/{pseudo}/connexion")
    UtilisateurBean connexionUtilisateur(@PathVariable String pseudo);

    @GetMapping("/connexion/{id}")
    UtilisateurBean findById(@PathVariable("id") Long id);
}