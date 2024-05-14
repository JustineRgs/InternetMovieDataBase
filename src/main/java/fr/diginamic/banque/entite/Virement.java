package fr.diginamic.banque.entite;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Virement")
public class Virement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String beneficiaire;

    @OneToMany(mappedBy = "virement")
    private List<Compte> comptes;
}
