package com.bibliotheque.microservicemyusers;

import com.bibliotheque.microservicemyusers.dao.UtilisateurDao;
import com.bibliotheque.microservicemyusers.model.RoleEnum;
import com.bibliotheque.microservicemyusers.model.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@SpringBootApplication
@EnableSwagger2
public class MicroserviceMyusersApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceMyusersApplication.class, args);
	}

	@Autowired
	UtilisateurDao utilisateurDao;


	@PostConstruct
	private void postConstruct(){
		utilisateurDao.save(new Utilisateur("martin", "martin", Arrays.asList(RoleEnum.USER), "martin@gmail.com"));
	}

}
