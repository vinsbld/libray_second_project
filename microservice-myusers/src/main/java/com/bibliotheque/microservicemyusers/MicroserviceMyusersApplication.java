package com.bibliotheque.microservicemyusers;

import com.bibliotheque.microservicemyusers.dao.IUtilisateurDao;
import com.bibliotheque.microservicemyusers.model.RoleEnum;
import com.bibliotheque.microservicemyusers.model.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;
import java.util.Collections;

@SpringBootApplication
@EnableDiscoveryClient
public class MicroserviceMyusersApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceMyusersApplication.class, args);
	}

	@Autowired
	IUtilisateurDao iUtilisateurDao;

	PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@PostConstruct
	private void postConstruct(){
		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setPseudo("martin");
		utilisateur.setMotDePasse(passwordEncoder.encode("martin"));
		utilisateur.setRoleEnums(Collections.singletonList(RoleEnum.USER));
		utilisateur.setEmail("martin@gmail.com");
		iUtilisateurDao.save(utilisateur);

	}

}
