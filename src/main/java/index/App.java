package index;

import jakarta.persistence.EntityManager;
import service.Start;

/**
 * La classe App est la classe principale de l'application. Elle initialise les composants nécessaires pour démarrer l'application.
 */
public class App {

    public static void main(String[] args) {

        // Obtention d'une instance d'EntityManager à partir de Utils
        EntityManager em = Utils.getInstance().getEntityManager();

        // Initialisation des composants de l'application
        new Start(em);

        // Fermeture de l'EntityManager
        em.close();
    }
}

