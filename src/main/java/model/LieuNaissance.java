package model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "LIEU_NAISSANCE")
public class LieuNaissance {
    @Id
    @Column(name = "Lieu")
    private String lieu;

    @OneToMany(mappedBy = "lieuNaissance")
    private List<Acteur> acteurs;

    @OneToMany(mappedBy = "lieuNaissance")
    private List<Realisateur> realisateurs;

    public LieuNaissance() {
    }

    /**
     * Constructeur avec un paramètre pour initialiser le nom du lieu de naissance.
     *
     * @param lieu Le nom du lieu de naissance.
     */
    public LieuNaissance(String lieu) {
        this.lieu = lieu;
    }

    /**
     * Retourne une représentation sous forme de chaîne de caractères du lieu de naissance.
     *
     * @return Une représentation sous forme de chaîne de caractères du lieu de naissance.
     */
    @Override
    public String toString() {
        return "'" + lieu + "'";
    }

    /**
     * Retourne le nom du lieu de naissance.
     *
     * @return Le nom du lieu de naissance.
     */
    public String getLieu() {
        return lieu;
    }

    /**
     * Modifie le nom du lieu de naissance.
     *
     * @param lieu Le nouveau nom du lieu de naissance.
     */
    public void setLieu(String lieu) {
        this.lieu = lieu;
    }
}