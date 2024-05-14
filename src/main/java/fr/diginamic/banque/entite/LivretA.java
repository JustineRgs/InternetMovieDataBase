package fr.diginamic.banque.entite;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "LivretA")
public class LivretA {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double taux;

    @OneToMany(mappedBy = "livretA")
    private List<Compte> comptes;
}
