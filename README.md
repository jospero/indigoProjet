# indigoProjet
-importez le dossier dans votre projet "sous eclipse: File -> Open Projet From file system....")
-ouvrir le dossier indigoprojet .
-ouvrir projet.control.metier->ouvrir le fichier Connection_Base.java
-modifier connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/indigo","+specifer le nom d'utilisateur d'acces a votre  base de donnée+","+specifier le mot de passe d'acces a votre base de donnée+");
-ouvrir  projet.control.
-ouvrir le fichier Main.java puis executer.
-mot de passe:elvis
-nom d'utilisateur:marius.
le dossier Agence contient les class de notre service web {
-le package service contient notre service web
-le package default contient notre serveur web(changer le localhos dans l'instruction du Endpublish par votre adresse ip pour tester sur deux hosts differents)
}

participation:

Badolo et Tia: la couche vue
Badolo:fenetre de connection et son controlleur 
TIA:couche entitée
Tia : couche controlleur
Tia:service web