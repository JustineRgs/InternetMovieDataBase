package fr.diginamic.banque.entite;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Compte")
public class Compte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String numero;
    private double solde;

    @ManyToMany(mappedBy = "comptes")
    private List<Client> clients;

    @ManyToOne
    @JoinColumn(name = "ID_OPERATION")
    private Operation operation;

    @ManyToOne
    @JoinColumn(name = "ID_LIVRETA")
    private LivretA livretA;

    @ManyToOne
    @JoinColumn(name = "ID_ASSURANCEVIE")
    private AssuranceVie assuranceVie;
}
