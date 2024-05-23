package model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "GENRE")
public class Genre {
    @Id
    @Column(name = "Nom")
    private String nom;

    @ManyToMany(mappedBy = "genreList")
    private List<Film> films = new ArrayList<>();

    public Genre() {
    }

    /**
     * Méthode toString pour afficher les informations du genre.
     *
     * @return Une chaîne représentant le genre.
     */
    @Override
    public String toString() {
        return nom;
    }


    //Getters & Setters

    /**
     * Récupère le nom du genre.
     *
     * @return Le nom du genre.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Définit le nom du genre.
     *
     * @param nom Le nom du genre.
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Récupère la liste des films associés à ce genre.
     *
     * @return La liste des films associés à ce genre.
     */
    public List<Film> getFilms() {
        return films;
    }

    /**
     * Définit la liste des films associés à ce genre.
     *
     * @param films La liste des films associés à ce genre.
     */
    public void setFilms(List<Film> films) {
        this.films = films;
    }
}
