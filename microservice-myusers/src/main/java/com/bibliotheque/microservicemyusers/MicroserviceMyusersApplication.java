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
		utilisateur.setEmail("oc.projet07@gmail.com");
		iUtilisateurDao.save(utilisateur);

		Utilisateur utilisateur1 =new Utilisateur();
		utilisateur1.setPseudo("pierre");
		utilisateur1.setMotDePasse(passwordEncoder.encode("pierre"));
		utilisateur1.setRoleEnums(Collections.singletonList(RoleEnum.USER));
		utilisateur1.setEmail("oc.projet07@gmail.com");
		iUtilisateurDao.save(utilisateur1);

		Utilisateur utilisateur2 =new Utilisateur();
		utilisateur2.setPseudo("marc");
		utilisateur2.setMotDePasse(passwordEncoder.encode("marc"));
		utilisateur2.setRoleEnums(Collections.singletonList(RoleEnum.USER));
		utilisateur2.setEmail("oc.projet07@gmail.com");
		iUtilisateurDao.save(utilisateur2);

		Utilisateur utilisateur3 =new Utilisateur();
		utilisateur3.setPseudo("joel");
		utilisateur3.setMotDePasse(passwordEncoder.encode("joel"));
		utilisateur3.setRoleEnums(Collections.singletonList(RoleEnum.USER));
		utilisateur3.setEmail("oc.projet07@gmail.com");
		iUtilisateurDao.save(utilisateur3);

		Utilisateur utilisateur4 =new Utilisateur();
		utilisateur4.setPseudo("pierik");
		utilisateur4.setMotDePasse(passwordEncoder.encode("pierik"));
		utilisateur4.setRoleEnums(Collections.singletonList(RoleEnum.USER));
		utilisateur4.setEmail("oc.projet07@gmail.com");
		iUtilisateurDao.save(utilisateur4);

		Utilisateur utilisateur5 =new Utilisateur();
		utilisateur5.setPseudo("paul");
		utilisateur5.setMotDePasse(passwordEncoder.encode("paul"));
		utilisateur5.setRoleEnums(Collections.singletonList(RoleEnum.USER));
		utilisateur5.setEmail("oc.projet07@gmail.com");
		iUtilisateurDao.save(utilisateur5);

		Utilisateur utilisateur6 =new Utilisateur();
		utilisateur6.setPseudo("leo");
		utilisateur6.setMotDePasse(passwordEncoder.encode("leo"));
		utilisateur6.setRoleEnums(Collections.singletonList(RoleEnum.USER));
		utilisateur6.setEmail("oc.projet07@gmail.com");
		iUtilisateurDao.save(utilisateur6);
	}

}
