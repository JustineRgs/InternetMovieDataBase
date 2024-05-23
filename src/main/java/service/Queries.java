package service;

import DAO.ActeurDAO;
import DAO.FilmDAO;
import jakarta.persistence.EntityManager;
import model.Acteur;
import model.Film;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * Cette classe fournit différentes requêtes pour interagir avec une base de données de films et d'acteurs.
 */
public class Queries {

    /**
     * Constructeur de la classe Queries.
     *
     * @param em EntityManager pour interagir avec la base de données.
     */
    public Queries(EntityManager em) {
        Scanner scanner = new Scanner(System.in);
        int choix = 0;

        do {
            afficherMenu();
            choix = scanner.nextInt();
            scanner.nextLine();

            try {
                switch (choix) {
                    case 1:
                        afficherFilmographieActeur(em, scanner);
                        break;
                    case 2:
                        afficherCastingFilm(em, scanner);
                        break;
                    case 3:
                        afficherFilmsEntreAnnees(em, scanner);
                        break;
                    case 4:
                        afficherFilmsCommunsActeurs(em, scanner);
                        break;
                    case 5:
                        afficherActeursCommunsFilms(em, scanner);
                        break;
                    case 6:
                        afficherFilmsActeurEntreAnnees(em, scanner);
                        break;
                    case 7:
                        System.out.println("Fin de l'application.");
                        break;
                    default:
                        System.out.println("Choix invalide. Veuillez entrer un numéro valide.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Veuillez entrer un nombre entier correspondant à l'une des options du menu.");
                scanner.nextLine(); // Pour vider le scanner après l'erreur
            } catch (Exception e) {
                System.out.println("Une erreur s'est produite : " + e.getMessage());
            }
        } while (choix != 7);

        scanner.close();
    }

    /**
     * Affiche le menu des options disponibles.
     */
    private static void afficherMenu() {
        System.out.println("Menu :");
        System.out.println("1. Affichage de la filmographie d'un acteur donné");
        System.out.println("2. Affichage du casting d'un film donné");
        System.out.println("3. Affichage des films sortis entre 2 années données");
        System.out.println("4. Affichage des films communs à 2 acteurs/actrices donnés");
        System.out.println("5. Affichage des acteurs communs à 2 films donnés");
        System.out.println("6. Affichage des films sortis entre 2 années données et qui ont un acteur/actrice donné au casting");
        System.out.println("7. Fin de l'application");
        System.out.println("Choisissez une option : ");
    }

    /**
     * Affiche la filmographie d'un acteur donné.
     *
     * @param em      EntityManager pour interagir avec la base de données.
     * @param scanner Scanner pour lire l'entrée de l'utilisateur.
     */
    private void afficherFilmographieActeur(EntityManager em, Scanner scanner) {
        System.out.println("Entrez le nom de l'acteur : ");
        String nomActeur = scanner.nextLine();
        
        List<Acteur> listActeurs = ActeurDAO.getActeursByName(nomActeur, em);
        List<Film> listFilm = ActeurDAO.getFilmsFromActeurs(listActeurs, em);
        if (listFilm.isEmpty()) {
            System.out.println();
            System.out.println("Aucun film trouvé pour cet acteur : " + nomActeur);
            System.out.println();
        } else {
            System.out.println();
            System.out.println("****************************************************************************");
            System.out.println("Filmographie de : " + nomActeur);
            System.out.println("****************************************************************************");
            System.out.println();
            for (Film film : listFilm) {
                System.out.println(film);
                System.out.println();
            }
        }
    }

    /**
     * Affiche le casting d'un film donné.
     *
     * @param em      EntityManager pour interagir avec la base de données.
     * @param scanner Scanner pour lire l'entrée de l'utilisateur.
     */
    private void afficherCastingFilm(EntityManager em, Scanner scanner) {
        System.out.println("Entrez le titre du film : ");
        String titreFilm = scanner.nextLine();

        List<Film> listFilms = FilmDAO.getFilmByName(titreFilm, em);
        List<Acteur> castingFilms = ActeurDAO.getActeursFromFilm(listFilms, em);
        if (castingFilms.isEmpty()) {
            System.out.println();
            System.out.println("Aucun film trouvé");
            System.out.println();
        } else {
            System.out.println("****************************************************************************");
            System.out.println("Casting du film : " + titreFilm);
            System.out.println("****************************************************************************");
            System.out.println();
            for (Acteur acteur : castingFilms) {
                System.out.println(acteur);
                System.out.println();
            }
        }
    }

    /**
     * Affiche les films sortis entre deux années données.
     *
     * @param em      EntityManager pour interagir avec la base de données.
     * @param scanner Scanner pour lire l'entrée de l'utilisateur.
     */
    private void afficherFilmsEntreAnnees(EntityManager em, Scanner scanner) {
        try {
            System.out.println("Entrez l'année de début : ");
            int anneeDebut = scanner.nextInt();

            System.out.println("Entrez l'année de fin : ");
            int anneeFin = scanner.nextInt();

            if (anneeDebut > anneeFin) {
                System.out.println("L'année de début ne peut pas être supérieure à l'année de fin.");
                return;
            }
            scanner.nextLine();
            List<Film> listFilmsDate = FilmDAO.getFilmBetweenDates(anneeDebut, anneeFin, em);
            if (listFilmsDate.isEmpty()) {
                System.out.println();
                System.out.println("Aucun film trouvé entre ces deux années. ");
                System.out.println();
            } else {
                System.out.println("****************************************************************************");
                System.out.println("Films tournés entre : " + anneeDebut + " et " + anneeFin);
                System.out.println("****************************************************************************");
                System.out.println();
                for (Film film : listFilmsDate) {
                    System.out.println(film);
                    System.out.println();
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Veuillez entrer des années valides.");
            System.out.println();
            scanner.nextLine();
        }
    }

    /**
     * Affiche les films communs à deux acteurs donnés.
     *
     * @param em      EntityManager pour interagir avec la base de données.
     * @param scanner Scanner pour lire l'entrée de l'utilisateur.
     */
    private void afficherFilmsCommunsActeurs(EntityManager em, Scanner scanner) {
        System.out.println("Entrez le nom du premier acteur : ");
        String premierActeur = scanner.nextLine();

        System.out.println("Entrez le nom du deuxième acteur : ");
        String deuxiemeActeur = scanner.nextLine();

        List<Acteur> listPremierActeur = ActeurDAO.getActeursFromNames(premierActeur, deuxiemeActeur, em);
        List<Film> listFilmsCommuns = FilmDAO.getFilmsSharedActors(listPremierActeur, em);

        if (listFilmsCommuns.isEmpty()) {
            System.out.println();
            System.out.println("Aucun film trouvé entre ces deux années. ");
            System.out.println();
        } else {
            System.out.println("****************************************************************************");
            System.out.println("Films tournés communs à : " + premierActeur + " et " + deuxiemeActeur);
            System.out.println("****************************************************************************");
            System.out.println();
            for (Film film : listFilmsCommuns) {
                System.out.println(film);
                System.out.println();
            }
        }
    }

    /**
     * Affiche les acteurs communs à deux films donnés.
     *
     * @param em      EntityManager pour interagir avec la base de données.
     * @param scanner Scanner pour lire l'entrée de l'utilisateur.
     */
    private void afficherActeursCommunsFilms(EntityManager em, Scanner scanner) {
        System.out.println("Entrez le titre du premier film : ");
        String premierFilm = scanner.nextLine();

        System.out.println("Entrez le titre du deuxième film : ");
        String deuxiemeFilm = scanner.nextLine();

        List<Film> listFilms2 = FilmDAO.getFilmsByNames(premierFilm, deuxiemeFilm, em);

        List<Acteur> listActeursCommuns = ActeurDAO.getActeursCommunsFromFilms(listFilms2);
        if (listActeursCommuns.isEmpty()) {
            System.out.println();
            System.out.println("Aucun acteurs communs entre ces deux films. ");
            System.out.println();
        } else {
            System.out.println("****************************************************************************");
            System.out.println("Acteurs communs au film : " + premierFilm + " et " + deuxiemeFilm);
            System.out.println("****************************************************************************");
            System.out.println();
            for (Acteur acteur : listActeursCommuns) {
                System.out.println(acteur);
                System.out.println();
            }
        }
    }

    /**
     * Affiche les films sortis entre deux années données avec un acteur donné au casting.
     *
     * @param em      EntityManager pour interagir avec la base de données.
     * @param scanner Scanner pour lire l'entrée de l'utilisateur.
     */
    private void afficherFilmsActeurEntreAnnees(EntityManager em, Scanner scanner) {
        System.out.println("Entrez l'année de début : ");
        int anneeDebut = scanner.nextInt();

        System.out.println("Entrez l'année de fin : ");
        int anneeFin = scanner.nextInt();

        System.out.println("Entrez le nom de l'acteur/actrice : ");
        scanner.nextLine();
        String nomActeur = scanner.nextLine();

        if (anneeDebut > anneeFin) {
            System.out.println("L'année de début ne peut pas être supérieure à l'année de fin.");
            return;
        }

        List<Film> listFilmsBetweenDate = FilmDAO.findFilmsByActorAndDateRange(nomActeur, anneeDebut, anneeFin, em);
        if (listFilmsBetweenDate.isEmpty()) {
            System.out.println();
            System.out.println("Aucun film trouvé pour cet acteur entre les deux dates. ");
            System.out.println();
        } else {
            System.out.println("***************************************************************************************************");
            System.out.println("Film(s) sorti(s) entre : " + anneeDebut + " et " + anneeFin + " avec l'acteur : " + nomActeur + " : ");
            System.out.println("***************************************************************************************************");
            System.out.println();
            for (Film film : listFilmsBetweenDate) {
                System.out.println(film);
                System.out.println();
            }
        }
    }
}
