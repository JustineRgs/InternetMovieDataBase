package fr.diginamic.banque.entite;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Operation")
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime date;
    private double montant;
    private String motif;

    @ManyToOne
    @JoinColumn(name = "ID_VIREMENT")
    private Virement virement;
}
