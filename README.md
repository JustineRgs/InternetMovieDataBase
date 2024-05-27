# InternetMovieDataBase

Projet JPA Diginamic Justine RAGUES.

InternetMovieDataBase : Base de données qui stocke des informations sur les films, les acteurs, les réalisateurs, les
rôles et les pays d'origine.

## Objectifs

1. Réaliser un document de conception : démarche Merise avec modèle Conceptuel de Données et un modèle Physique de
   Données (Visible dans le dossier conception à la racine du projet).
2. Mettre en place une base de données depuis un fichier Json.
3. Mettre au point une application permettant d'initialiser la base de données avec les données existantes avec JPA.
4. Mettre en place une application dotée d'un menu permettant à l'utilisateur de réaliser des recherches dans les
   données.

## Installation

Pour installer ce projet, suivez ces étapes :

1. Clonez ce dépôt.
2. Créez une base de données MySQL nommée : InternetMovieDataBase.
3. Ajoutez un utilisateur nommé "InternetMovieDataBase" avec le mot de passe : "InternetMovieDataBase".
4. Si vous préférez ne pas suivre les étapes 2 et 3, vous pouvez également modifier la configuration de la base de
   données dans le fichier src/main/resources/META-INF/persistence.xml.
5. Une fois la base de données configurée, exécutez le fichier App.java.

Après ces étapes, la base de données se remplira automatiquement grâce au fichier film.json et au parsing mis en place.
Ensuite, laissez-vous guider par le menu qui s'affichera dans votre console pour effectuer différentes requêtes.