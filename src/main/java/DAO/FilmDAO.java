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

    public static List<Film> getFilmsSharedActors(List<Acteur> listPremierActeur, EntityManager em) {
        List<String> listIdFilm = new ArrayList<>();
        List<Film> listFilm = new ArrayList<>();
        boolean isFirst = true;

        for (int i = 0; i < listPremierActeur.size(); i++) {
            List<Role> listRole = RoleDAO.getRolesByActeurId(listPremierActeur.get(i).getId(), em);

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

    public static List<Film> getFilmsByNames(String name, String name2, EntityManager em) {
        TypedQuery<Film> namedQueryFilm = em.createNamedQuery("Film.findByFilmName2", Film.class);
        namedQueryFilm.setParameter("film", name);
        namedQueryFilm.setParameter("film2", name2);
        return namedQueryFilm.getResultList();
    }

    public static List<Film> findFilmsByActorAndDateRange(String actor, int startYear, int endYear, EntityManager em) {
        Calendar startCal = Calendar.getInstance();
        startCal.set(Calendar.YEAR, startYear);
        startCal.set(Calendar.MONTH, Calendar.JANUARY);
        startCal.set(Calendar.DAY_OF_MONTH, 1);
        Date startDate = startCal.getTime();

        Calendar endCal = Calendar.getInstance();
        endCal.set(Calendar.YEAR, endYear);
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
