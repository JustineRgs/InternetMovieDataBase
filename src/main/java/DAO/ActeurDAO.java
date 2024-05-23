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
}
