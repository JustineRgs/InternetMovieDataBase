package index;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 * La classe Utils fournit une instance unique d'EntityManager pour interagir avec la base de données.
 */
public class Utils {
    private final static EntityManagerFactory EMF = Persistence.createEntityManagerFactory("InternetMovieDataBase");
    private final static EntityManager EM = EMF.createEntityManager();
    private static Utils INSTANCE = new Utils();

    private Utils() {
    }

    /**
     * Méthode statique pour obtenir l'instance unique de la classe Utils.
     *
     * @return L'instance unique de la classe Utils.
     */
    public static Utils getInstance() {
        return INSTANCE;
    }

    /**
     * Méthode pour obtenir l'EntityManager pour interagir avec la base de données.
     *
     * @return L'EntityManager associé à la base de données.
     */
    public EntityManager getEntityManager() {
        return EM;
    }
}
