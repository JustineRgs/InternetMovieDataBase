package fr.diginamic.banque;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class TestConnexion {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence
                .createEntityManagerFactory("banque");

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        System.out.println(entityManager);
        if (entityManager != null && entityManager.isOpen()) {
            System.out.println("Connexion à la base de données établie avec succès !");
        } else {
            System.err.println("Erreur lors de la connexion à la base de données.");
        }

        entityManager.close();

        entityManagerFactory.close();
    }
}



