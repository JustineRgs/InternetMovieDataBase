package DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import model.Film;
import model.Role;

import java.util.List;

public class FilmDAO {
    public static List<Film> getFilmByName(String name, EntityManager em) {
        TypedQuery<Film> namedQueryFilm = em.createNamedQuery("Film.findByFilmName", Film.class);
        namedQueryFilm.setParameter("film", name);
        return namedQueryFilm.getResultList();
    }
}
