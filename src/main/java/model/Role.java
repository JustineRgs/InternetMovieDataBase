package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;

@Entity
@Table(name = "ROLE")
@JsonIgnoreProperties({"film"})
@NamedQueries({
        @NamedQuery(name = "Role.findByActorId", query = "SELECT r FROM Role r WHERE r.acteur.id = :acteur"),
        @NamedQuery(name = "Role.findByFilmId", query = "SELECT r from Role r WHERE r.film.id = :film")

})
public class Role {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Nom")
    private String nom;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_Film")
    private Film film;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_Acteur")
    private Acteur acteur;

    public Role() {
    }

    /**
     * Méthode toString pour afficher les informations du rôle.
     *
     * @return Une chaîne représentant le rôle.
     */
    @Override
    public String toString() {
        return "Role : " + nom + ", Film : " + film + ", Acteur : " + acteur;
    }

    /**
     * Récupère l'identifiant unique du rôle.
     *
     * @return L'identifiant unique du rôle.
     */
    public Long getId() {
        return id;
    }

    /**
     * Définit l'identifiant unique du rôle.
     *
     * @param id L'identifiant unique du rôle.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Récupère le nom du personnage joué par l'acteur.
     *
     * @return Le nom du personnage.
     */
    @JsonProperty("characterName")
    public String getNom() {
        return nom;
    }

    /**
     * Définit le nom du personnage joué par l'acteur.
     *
     * @param nom Le nom du personnage.
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Récupère le film dans lequel le rôle est joué.
     *
     * @return Le film associé au rôle.
     */
    public Film getFilm() {
        return film;
    }

    /**
     * Définit le film dans lequel le rôle est joué.
     *
     * @param film Le film associé au rôle.
     */
    public void setFilm(Film film) {
        this.film = film;
    }

    /**
     * Récupère l'acteur jouant le rôle.
     *
     * @return L'acteur associé au rôle.
     */
    public Acteur getActeur() {
        return acteur;
    }

    /**
     * Définit l'acteur jouant le rôle.
     *
     * @param acteur L'acteur associé au rôle.
     */
    public void setActeur(Acteur acteur) {
        this.acteur = acteur;
    }
}