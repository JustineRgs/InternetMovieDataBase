package fr.diginamic.banque;

import fr.diginamic.banque.entite.Banque;
import fr.diginamic.banque.entite.Client;
import fr.diginamic.banque.entite.Compte;
import fr.diginamic.banque.entite.LivretA;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class App {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence
                .createEntityManagerFactory("banque");

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        System.out.println(entityManager);
        if (entityManager != null && entityManager.isOpen()) {
            System.out.println("Connexion à la base de données établie avec succès !");

            //Creation de la banque
            Banque banque = new Banque();
            banque.setNom("Credit Agricole");

            entityManager.getTransaction().begin();
            entityManager.persist(banque);
            entityManager.getTransaction().commit();

            //Création du compte
            Compte compte = new Compte();
            compte.setNumero("123456789");
            compte.setSolde(2000);

            //Création du client 1
            Client client1 = new Client();
            client1.setNom("RAGUES");
            client1.setPrenom("Justine");
            client1.setDateNaissance(LocalDate.of(1996, 10, 10));
            client1.setAdresse(new Adresse(1, "Rue de la Paix", 34070, "Montpellier"));
            client1.setBanque(banque);

            //Création du client 2
            Client client2 = new Client();
            client2.setNom("PIERRE");
            client2.setPrenom("Jean");
            client2.setDateNaissance(LocalDate.of(1990, 2, 2));
            client2.setAdresse(new Adresse(3, "Rue de la Pouet", 34090, "Montpellier"));
            client2.setBanque(banque);

            //Association du compte au 2 clients
            Set<Client> clients = new HashSet<>();
            clients.add(client1);
            clients.add(client2);
            compte.setClients(clients);

            Set<Compte> comptes = new HashSet<>();
            comptes.add(compte);
            client1.setComptes(comptes);
            client2.setComptes(comptes);

            entityManager.getTransaction().begin();
            entityManager.persist(compte);
            entityManager.persist(client1);
            entityManager.persist(client2);
            entityManager.getTransaction().commit();

            LivretA livretA = new LivretA();
            livretA.setTaux(0.1);


        } else {
            System.err.println("Erreur lors de la connexion à la base de données.");
        }

        entityManager.close();

        entityManagerFactory.close();
    }
}



