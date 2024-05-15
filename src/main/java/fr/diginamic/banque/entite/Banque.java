package fr.diginamic.banque.entite;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "BANQUE")
public class Banque {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "NOM")
    private String nom;

    @OneToMany(mappedBy = "banque")
    private Set<Client> clients;

    public Banque() {
    }

    public Banque(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "ID : " + id + ", nom : " + nom;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Set<Client> getClients() {
        return clients;
    }

    public void setClients(Set<Client> clients) {
        this.clients = clients;
    }
}
