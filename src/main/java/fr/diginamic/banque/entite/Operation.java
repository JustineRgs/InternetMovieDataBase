package fr.diginamic.banque.entite;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "OPERATION")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@DiscriminatorColumn(name = "TYPE_COMPTE")
public class Operation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "DATE")
    private LocalDateTime date;
    @Column(name = "MONTANT")
    private double montant;
    @Column(name = "MOTIF")
    private String motif;
    @ManyToOne
    @JoinColumn(name = "ID_COMPTE")
    private Compte compte;

    public Operation() {
        super();
    }

    public Operation(LocalDateTime date, double montant, String motif, Compte compte) {
        super();
        this.date = date;
        this.montant = montant;
        this.motif = motif;
        this.compte = compte;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public Compte getCompte() {
        return compte;
    }

    public void setCompte(Compte compte) {
        this.compte = compte;
    }

    @Override
    public String toString() {
        return "Operation [id=" + id + ", date=" + date + ", montant=" + montant + ", motif=" + motif + ", compte="
                + compte + "]";
    }

}