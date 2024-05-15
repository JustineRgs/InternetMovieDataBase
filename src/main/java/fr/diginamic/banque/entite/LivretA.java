package fr.diginamic.banque.entite;

import jakarta.persistence.*;

@Entity
@Table(name = "LIVRET_A")
public class LivretA extends Compte {

    @Column(name = "TAUX")
    private double taux;

    public double getTaux() {
        return taux;
    }

    public void setTaux(double taux) {
        this.taux = taux;
    }
}
