package fr.diginamic.banque.entite;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "ASSURANCE_VIE")
public class AssuranceVie extends Compte {
    @Column(name = "DATE_FIN")
    private LocalDate dateFin;
    @Column(name = "TX")
    private double taux;

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public double getTaux() {
        return taux;
    }

    public void setTaux(double taux) {
        this.taux = taux;
    }
}
