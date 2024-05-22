package model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class LieuNaissance {
    @Id
    private String lieu;

    @OneToMany(mappedBy = "lieuNaissance")
    private List<Acteur> acteurs;

    @OneToMany(mappedBy = "lieuNaissance")
    private List<Realisateur> realisateurs;


    public LieuNaissance(String lieu) {
        this.lieu = lieu;
    }

    public LieuNaissance() {

    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    @Override
    public String toString() {
        return "LieuNaissance{" +
                "lieu='" + lieu + '\'' +
                '}';
    }
}
