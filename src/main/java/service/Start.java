package service;

import jakarta.persistence.EntityManager;

/**
 * La classe Start initialise les composants nécessaires pour démarrer l'application.
 */
public class Start {

    /**
     * Constructeur de la classe Start.
     *
     * @param em EntityManager pour interagir avec la base de données.
     */
    public Start(EntityManager em) {
        // Vérification de la configuration XML et remplissage de la base de données si nécessaire
        new XmlCheck(em);

        // Intéraction avec l'utilisateur depuis le Scanner
        new Queries(em);
    }
}
