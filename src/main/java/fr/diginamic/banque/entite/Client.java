package fr.diginamic.banque.entite;

import fr.diginamic.banque.Adresse;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String prenom;
    private LocalDate dateNaissance;

    @Embedded
    private Adresse adresse;

    @OneToMany
    @JoinColumn(name = "ID_BANQUE")
    private Banque banque;

    @ManyToMany
    @JoinTable(
            name = "Client",
            joinColumns = @JoinColumn(name = "client_id"),
            inverseJoinColumns = @JoinColumn(name = "numero")
    )
    private List<Compte> comptes;
}
