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

    public static List<Film> getFilmBetweenDates(int anneeDebut, int anneeFin, EntityManager em) {
        TypedQuery<Film> namedQueryFilm = em.createNamedQuery("Film.findByDate", Film.class);
        namedQueryFilm.setParameter("before", anneeDebut);
        namedQueryFilm.setParameter("after", anneeFin);
        return namedQueryFilm.getResultList();
    }
}
