package DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import model.Acteur;
import model.Film;
import model.Role;

import java.util.ArrayList;
import java.util.List;

public class ActeurDAO {
    public static List<Acteur> getActeursByName(String name, EntityManager em) {
        TypedQuery<Acteur> namedQueryActeur = em.createNamedQuery("Acteur.findByName", Acteur.class);
        namedQueryActeur.setParameter("name", name);
        return namedQueryActeur.getResultList();
    }

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

    public static List<Acteur> getActeursFromNames(String premierActeur, String deuxiemeActeur, EntityManager em) {
        TypedQuery<Acteur> namedQueryActeur = em.createNamedQuery("Acteur.findById", Acteur.class);
        namedQueryActeur.setParameter("name1", premierActeur);
        namedQueryActeur.setParameter("name2", deuxiemeActeur);
        return namedQueryActeur.getResultList();
    }
}
