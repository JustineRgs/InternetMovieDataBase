package DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import model.Role;

import java.util.List;

public class RoleDAO {
    public static List<Role> getRolesByActeurId(String idActeur, EntityManager em) {
        TypedQuery<Role> namedQueryRole = em.createNamedQuery("Role.findByActorId", Role.class);

        namedQueryRole.setParameter("acteur", idActeur);
        return namedQueryRole.getResultList();
    }

    public static List<Role> getRolesFromFilmId(String idFilm, EntityManager em) {
        TypedQuery<Role> namedQueryRole = em.createNamedQuery("Role.findByFilmId", Role.class);

        namedQueryRole.setParameter("film", idFilm);
        List<Role> listRole = namedQueryRole.getResultList();
        return listRole;
    }
}
