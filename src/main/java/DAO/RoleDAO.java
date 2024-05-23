package DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import model.Role;

import java.util.List;

/**
 * Cette classe contient les méthodes pour accéder aux données des rôles dans la base de données.
 */
public class RoleDAO {
    /**
     * Récupère une liste de rôles par l'identifiant de l'acteur.
     *
     * @param idActeur Identifiant de l'acteur pour lequel récupérer les rôles.
     * @param em       EntityManager pour accéder à la base de données.
     * @return Liste des rôles de l'acteur donné.
     */
    public static List<Role> getRolesByActeurId(String idActeur, EntityManager em) {
        TypedQuery<Role> namedQueryRole = em.createNamedQuery("Role.findByActorId", Role.class);

        namedQueryRole.setParameter("acteur", idActeur);
        return namedQueryRole.getResultList();
    }

    /**
     * Récupère une liste de rôles par l'identifiant du film.
     *
     * @param idFilm Identifiant du film pour lequel récupérer les rôles.
     * @param em     EntityManager pour accéder à la base de données.
     * @return Liste des rôles du film donné.
     */
    public static List<Role> getRolesFromFilmId(String idFilm, EntityManager em) {
        TypedQuery<Role> namedQueryRole = em.createNamedQuery("Role.findByFilmId", Role.class);

        namedQueryRole.setParameter("film", idFilm);
        List<Role> listRole = namedQueryRole.getResultList();
        return listRole;
    }
}
