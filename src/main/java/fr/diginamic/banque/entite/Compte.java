package fr.diginamic.banque.entite;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "COMPTE")
public class Compte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "NUMERO")
    private String numero;

    @Column(name = "SOLDE")
    private double solde;

    @OneToMany(mappedBy = "compte")
    private Set<Operation> operations;

    @ManyToMany(mappedBy = "comptes")
    private Set<Client> clients;

    public Compte() {
    }

    public Compte(Integer id, String numero, double solde, Set<Operation> operations) {
        this.id = id;
        this.numero = numero;
        this.solde = solde;
        this.operations = operations;
    }

    @Override
    public String toString() {
        return "Compte n°" + numero + " Solde : " + solde;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public double getSolde() {
        return solde;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }

    public Set<Operation> getOperations() {
        return operations;
    }

    public void setOperations(Set<Operation> operations) {
        this.operations = operations;
    }

    public Set<Client> getClients() {
        return clients;
    }

    public void setClients(Set<Client> clients) {
        this.clients = clients;
    }
}
