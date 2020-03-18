####		ProjetEtablissement		####


********************* Contexte ************************
Réalisation d'une application Spring Web (back-end uniquement) 

Délai : Application à réaliser pour le 16/03/2020 20h00.

Description : ProjetEtablissement est une application de gestion à destination du personnel d'un établissement scolaire.
	      Elle permet aux utilisateurs (professeurs, directeurs) de visualiser les informations gérées et de les modifier.
	      

I. Rapport Technique
	1.Cahier des charges :
		a.Fonctionnalités

Utilisateur :
- Consulter les informations pratiques (Etudiant, Classe, Niveau, Absence, Note, Examen, Matiere, Module)
- Gérer ces informations (ajouter, supprimer, modifier)
- Consulter les informations regroupées (Examens de chaque matière, notes par étudiant, matières par module, classes par niveaux)

Il n'y a pas de module authentification (car non précisé dans la commande), toute personne ayant accès à cette application est considéré comme un utilisateur.

		b. Matériels et choix des technologies
Les technologies utilisées pendant ce sont projets ont été :
- Concepts Objets - UML 
- Java 8
- Spring Boot
- Spring Web
- Spring Data JPA
- Maven
- Lombok


	2.Recette
Recette fonctionnelle : 

Recette technique : 


	3. Difficultés rencontrées et solutions apportées
La principale difficulté que nous avons rencontrée a résidé dans la répartition des tâches étant une équipe de 4 personnes au lieu de 5.
En effet, nous avions estimé judicieux de se séparer les tâches en fonction des couches (DAO / Controller / Service ...) et non selon les entités à gérer.
Cela a créé de l'attente entre les collaborateurs afin de pouvoir intégrer leur partie. Cela associé à une communication insuffisante et une nomenclature non stricte a engendré des retouches pour uniformiser et donc des retards.
Le temps n'a été suffisant afin de régler notre problème sur IClasseDao, étant un DAO avec une méthode custom, ainsi nous n'avons pas pu charger le contexte et lancer les tests unitaires (réalisés en majorité)

  


	4. Conclusion

Améliorations à faire : 
	- Implémenter des "custom méthods" de Spring JPA, qui fonctionnent.
	- Séparer les interfaces de leur implémentations dans des packages différents.


II. Rapport de gestion du projet

1.Découpage du projet

Conception :	définition des fonctionnalités du site web.
		réalisation d'un diagramme de classe, d'un diagramme UseCase et d'un diagramme de package.

Réalisation : 
Le référent technique a créé un projet sur Git, auquel l'équipe a été invité. 
L'architecture a été effectuée par le référent technique.

Back-End : 5 personnes se sont concentrées sur le backend

Bilan : 

2. Tâches - Réalisation


3.Organisation de l'équipe
L'équipe s'est divisée selon les couches à réaliser : Service+Controller (2personnes) / Dto +Converters (1personne) / Entity + Dao (1personne)
Puis les personnes se sont réparties par entités afin de suivre toute la chaine de traitement. 
Les taches ont été réparties arbitrairement.



Developpeurs : 
Flavien Gomila
Maxime Rembert
Isaline Millet

Référent Technique :
Yohann Bachelier

Chef de projet :
Clara Cadet
