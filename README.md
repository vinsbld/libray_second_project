# mybibliotheque

# Projet 7 OC Gestion d'une bibliothèque d'une grande ville

Description :
  
  myBibliotheque est composé de 3 modules :  
  
                    Un Module : microservice-myclient
                    Un Module : microservice-myLibrary
                    Un Module : microservice-myusers
                    
  Api Gateway :
 
                    Zuul : zuul-server

  Edge services utilisés : 
                    
                    Spring-Cloud-Config : config-server
                    Eureka : Eureka-server
                    Zipkin
                    Spring-admin/Acuator

Lancer l'application : 
                    
                    mvn spring-boot:run

Etapes : 

      Ordre de déploiement

Etape 1 : 
      
       1 _ Zipkin-server
      
Etape 2 : 
      
       1 _ config-server
       2 _ eureka-server
       
Etape 3 : 
      
       1 _ zuul-server
       
Etape 4 : 
      
       1 _ microservice-myusers
       2 _ microservice-myLibrary
       3 _ microservice-myclient
       
      
