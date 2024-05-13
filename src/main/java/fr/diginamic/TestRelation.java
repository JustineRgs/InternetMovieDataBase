package fr.diginamic;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class TestRelation {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("bibliotheque");
        EntityManager em = emf.createEntityManager();

        // Extraire un emprunt en fonction de son ID avec tous les livres associés
        Long empruntId = 3L;
        Emprunt emprunt = em.find(Emprunt.class, empruntId);
        if (emprunt != null) {
            System.out.println("Emprunt trouvé : " + emprunt.getId());
            System.out.println("Livres associés : ");
            for (Livre livre : emprunt.getLivres()) {
                System.out.println("- " + livre.getTitre() + " par " + livre.getAuteur());
            }
        } else {
            System.out.println("Aucun emprunt trouvé avec l'ID : " + empruntId);
        }
        System.out.println();
        // Extraire tous les emprunts d'un client donné
        Long clientId = 2L;
        TypedQuery<Emprunt> query = em.createQuery("SELECT e FROM Emprunt e WHERE e.client.id = :clientId", Emprunt.class);
        query.setParameter("clientId", clientId);
        List<Emprunt> empruntsClient = query.getResultList();
        if (!empruntsClient.isEmpty()) {
            System.out.println("Emprunts du client " + clientId + " : ");
            for (Emprunt e : empruntsClient) {
                System.out.println("- ID : " + e.getId() + ", Date début : " + e.getDateDebut() + ", Date fin : " + e.getDateFin() + ", Délai : " + e.getDelai());
            }
        } else {
            System.out.println("Ce client n'a aucun emprunt.");
        }

        em.close();
        emf.close();
    }

}
