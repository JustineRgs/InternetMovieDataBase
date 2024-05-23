package DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import model.Acteur;
import model.Film;
import model.Role;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Cette classe contient les méthodes pour accéder aux données des acteurs dans la base de données.
 */
public class ActeurDAO {

    /**
     * Récupère une liste d'acteurs par leur nom.
     *
     * @param name Nom de l'acteur à rechercher.
     * @param em   EntityManager pour accéder à la base de données.
     * @return Liste des acteurs correspondant au nom donné.
     */
    public static List<Acteur> getActeursByName(String name, EntityManager em) {
        TypedQuery<Acteur> namedQueryActeur = em.createNamedQuery("Acteur.findByName", Acteur.class);
        namedQueryActeur.setParameter("name", name);
        return namedQueryActeur.getResultList();
    }

    /**
     * Récupère une liste de films à partir d'une liste d'acteurs.
     *
     * @param listActeurs Liste des acteurs pour lesquels rechercher les films.
     * @param em          EntityManager pour accéder à la base de données.
     * @return Liste des films dans lesquels les acteurs ont joué.
     */
    public static List<Film> getFilmsFromActeurs(List<Acteur> listActeurs, EntityManager em) {
        List<Film> listFilms = new ArrayList<>();
        for (Acteur acteur : listActeurs) {

            List<Role> listRole = RoleDAO.getRolesByActeurId(acteur.getId(), em);
            for (Role role : listRole) {
                listFilms.add(role.getFilm());
            }
        }
        return listFilms;
    }

    /**
     * Récupère une liste d'acteurs à partir d'une liste de films.
     *
     * @param listFilms Liste des films pour lesquels rechercher les acteurs.
     * @param em        EntityManager pour accéder à la base de données.
     * @return Liste des acteurs ayant joué dans les films donnés.
     */
    public static List<Acteur> getActeursFromFilm(List<Film> listFilms, EntityManager em) {
        List<Acteur> acteurList = new ArrayList<>();

        for (Film film : listFilms) {

            List<Role> listRole = RoleDAO.getRolesFromFilmId(film.getId(), em);
            for (Role role : listRole) {
                acteurList.add(role.getActeur());
            }
        }
        return acteurList;
    }

    /**
     * Récupère une liste d'acteurs par deux noms d'acteurs.
     *
     * @param premierActeur  Nom du premier acteur.
     * @param deuxiemeActeur Nom du deuxième acteur.
     * @param em             EntityManager pour accéder à la base de données.
     * @return Liste des acteurs correspondant aux deux noms donnés.
     */
    public static List<Acteur> getActeursFromNames(String premierActeur, String deuxiemeActeur, EntityManager em) {
        TypedQuery<Acteur> namedQueryActeur = em.createNamedQuery("Acteur.findById", Acteur.class);
        namedQueryActeur.setParameter("name1", premierActeur);
        namedQueryActeur.setParameter("name2", deuxiemeActeur);
        return namedQueryActeur.getResultList();
    }

    /**
     * Récupère une liste d'acteurs communs à une liste de films.
     *
     * @param listFilms Liste des films pour lesquels rechercher les acteurs communs.
     * @return Liste des acteurs communs aux films donnés.
     */
    public static List<Acteur> getActeursCommunsFromFilms(List<Film> listFilms) {
        Set<Acteur> commonActors = new HashSet<>();
        boolean first = true;

        for (Film film : listFilms) {
            List<Role> roles = film.getRoles();
            Set<Acteur> currentActors = new HashSet<>();

            for (Role role : roles) {
                currentActors.add(role.getActeur());
            }

            if (first) {
                commonActors.addAll(currentActors);
                first = false;
            } else {
                commonActors.retainAll(currentActors);
            }
        }

        return new ArrayList<>(commonActors);
    }
}
