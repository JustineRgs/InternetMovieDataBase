package model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "LANGUE")
public class Langue {
    @Id
    @Column(name = "Label")
    private String label;

    @OneToMany(mappedBy = "langue")
    private List<Film> films;

    public Langue() {
    }

    /**
     * Constructeur avec un paramètre pour initialiser le libellé de la langue.
     *
     * @param label Le libellé de la langue.
     */
    public Langue(String label) {
        this.label = label;
    }

    /**
     * Retourne une représentation sous forme de chaîne de caractères de l'objet Langue,
     * contenant le libellé de la langue et la liste des films associés.
     *
     * @return Une représentation sous forme de chaîne de caractères de l'objet Langue.
     */
    @Override
    public String toString() {
        return label;
    }


    // Getters and setters

    /**
     * Retourne le libellé de la langue.
     *
     * @return Le libellé de la langue.
     */
    public String getLabel() {
        return label;
    }

    /**
     * Modifie le libellé de la langue.
     *
     * @param label Le nouveau libellé de la langue.
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * Retourne la liste des films associés à cette langue.
     *
     * @return La liste des films associés à cette langue.
     */
    public List<Film> getFilms() {
        return films;
    }

    /**
     * Modifie la liste des films associés à cette langue.
     *
     * @param films La nouvelle liste des films associés à cette langue.
     */
    public void setFilms(List<Film> films) {
        this.films = films;
    }
}
