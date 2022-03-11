# ProjetJavaRentManager
The result of a java project made in february 2022 to make a system to manage the rent of vehicles. 

Dans l'état actuel du projet, les boutons avec fonctionnalités :
  - Ajouter : client, vehicule, réservation
  - Supprimer : client, vehicule, réservation
  - Détails : client, vehicule (non nécessaire pour réservation je pense)
  - Modifier : client, vehicule, réservation

Les contraintes :
  - Un utilisateur doit avoir plus de 18 ans (text affiché si on essaye)
  - Un email ne peut pas etre utilisé deux fois (text affiché si on essaye)

Les tests :
  - isLegal_should_return_true_when_age_is_over_18()
  - isLegal_should_return_false_when_age_is_under_18()
  - isEmailAvailable_should_return_true_if_the_email_is_not_used()
  - isEmailAvailable_should_return_false_if_the_email_is_used()

Les plus grosses difficultés rencontrés sont également mes plus grosses fieretés car le temps passé dessus a été valorisé :
  - Lors de l'update, je voulais que les anciennes valeurs, y compris celles de date, soient affichés. Puis une fois la fonction modifié implémenté, elle créait une nouvelle entrée et laissait l'ancienne au lieu de la modifier
  - La création de réservation ne fonctionnait pas avec les clients et vehicules non présent dans la base de donnée initiale
  - Les détails ont été complexe a implémenter, surtout le fait d'afficher le nom du véhicule de réservation dans les détails client et vice versa

Ce qui ne fonctionne pas / les problemes existant toujours:
  - Les contraintes non mentionnées 
  - Dans le GitHub je ne comprend pas comment mettre le code de la branche master (aka la version finale) en temps que main
  - Il y a des problemes de temps en temps pour delete et la seule solution trouvé a été de rerun le fillDatabase


Runner le code avec mvn tomcat7:run
