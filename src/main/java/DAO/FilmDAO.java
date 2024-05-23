package DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import model.Acteur;
import model.Film;
import model.Role;

import java.util.ArrayList;
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
}
