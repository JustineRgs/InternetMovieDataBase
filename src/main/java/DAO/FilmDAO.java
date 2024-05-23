package DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import model.Acteur;
import model.Film;
import model.Role;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Cette classe contient les méthodes pour accéder aux données des films dans la base de données.
 */
public class FilmDAO {
    /**
     * Récupère une liste de films par leur nom.
     *
     * @param name Nom du film à rechercher.
     * @param em   EntityManager pour accéder à la base de données.
     * @return Liste des films correspondant au nom donné.
     */
    public static List<Film> getFilmByName(String name, EntityManager em) {
        TypedQuery<Film> namedQueryFilm = em.createNamedQuery("Film.findByFilmName", Film.class);
        namedQueryFilm.setParameter("film", name);
        return namedQueryFilm.getResultList();
    }

    /**
     * Récupère une liste de films entre deux années données.
     *
     * @param anneeDebut Année de début de la période.
     * @param anneeFin   Année de fin de la période.
     * @param em         EntityManager pour accéder à la base de données.
     * @return Liste des films entre les deux années données.
     */
    public static List<Film> getFilmBetweenDates(int anneeDebut, int anneeFin, EntityManager em) {
        TypedQuery<Film> namedQueryFilm = em.createNamedQuery("Film.findByDate", Film.class);
        namedQueryFilm.setParameter("before", anneeDebut);
        namedQueryFilm.setParameter("after", anneeFin);
        return namedQueryFilm.getResultList();
    }

    /**
     * Récupère une liste de films communs à deux acteurs spécifiques.
     *
     * @param listActeurs Liste des acteurs à rechercher.
     * @param em          EntityManager pour accéder à la base de données.
     * @return Liste des films partageant les acteurs spécifiés.
     */
    public static List<Film> getFilmsSharedActors(List<Acteur> listActeurs, EntityManager em) {
        List<String> listIdFilm = new ArrayList<>();
        List<Film> listFilm = new ArrayList<>();
        boolean isFirst = true;

        for (int i = 0; i < listActeurs.size(); i++) {
            List<Role> listRole = RoleDAO.getRolesByActeurId(listActeurs.get(i).getId(), em);

            for (int j = 0; j < listRole.size(); j++) {

                if (isFirst) {
                    listIdFilm.add(listRole.get(j).getFilm().getId());
                } else {
                    for (int k = 0; k < listIdFilm.size(); k++) {
                        if (listRole.get(j).getFilm().getId() == listIdFilm.get(k)) {
                            listFilm.add(listRole.get(j).getFilm());
                        }
                    }
                }
            }
            isFirst = false;
        }
        return listFilm;
    }

    /**
     * Récupère une liste de films par deux noms de film.
     *
     * @param name  Nom du premier film.
     * @param name2 Nom du deuxième film.
     * @param em    EntityManager pour accéder à la base de données.
     * @return Liste des films correspondant aux deux noms donnés.
     */
    public static List<Film> getFilmsByNames(String name, String name2, EntityManager em) {
        TypedQuery<Film> namedQueryFilm = em.createNamedQuery("Film.findByFilmName2", Film.class);
        namedQueryFilm.setParameter("film", name);
        namedQueryFilm.setParameter("film2", name2);
        return namedQueryFilm.getResultList();
    }

    /**
     * Récupère une liste de films entre deux dates pour un acteur spécifique.
     *
     * @param actor      Nom de l'acteur à rechercher.
     * @param anneeDebut Année de début de la période.
     * @param anneeFin   Année de fin de la période.
     * @param em         EntityManager pour accéder à la base de données.
     * @return Liste des films dans lesquels l'acteur a joué pendant la période spécifiée.
     */
    public static List<Film> findFilmsByActorAndDateRange(String actor, int anneeDebut, int anneeFin, EntityManager em) {
        Calendar startCal = Calendar.getInstance();
        startCal.set(Calendar.YEAR, anneeDebut);
        startCal.set(Calendar.MONTH, Calendar.JANUARY);
        startCal.set(Calendar.DAY_OF_MONTH, 1);
        Date startDate = startCal.getTime();

        Calendar endCal = Calendar.getInstance();
        endCal.set(Calendar.YEAR, anneeFin);
        endCal.set(Calendar.MONTH, Calendar.DECEMBER);
        endCal.set(Calendar.DAY_OF_MONTH, 31);
        Date endDate = endCal.getTime();

        TypedQuery<Film> query = em.createNamedQuery("Film.findByActorAndDateRange", Film.class);
        query.setParameter("actor", actor);
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        return query.getResultList();
    }
}
