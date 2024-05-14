package fr.diginamic.banque.entite;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "AssuranceVie")
public class AssuranceVie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate dateFin;
    private double taux;

    @OneToMany(mappedBy = "AssuranceVie")
    private List<Compte> comptes;

    public AssuranceVie(LocalDate dateFin, double taux, List<Compte> comptes, Long id) {
        this.dateFin = dateFin;
        this.taux = taux;
        this.comptes = comptes;
        this.id = id;
    }

    public AssuranceVie() {

    }

    @Override
    public String toString() {
        return "AssuranceVie numéro : " + id +
                " Date de fin : " + dateFin +
                ", Taux : " + taux +
                ", Compte : " + comptes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public List<Compte> getComptes() {
        return comptes;
    }

    public void setComptes(List<Compte> comptes) {
        this.comptes = comptes;
    }
}
