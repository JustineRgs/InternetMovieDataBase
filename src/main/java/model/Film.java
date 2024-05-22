package model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;

@Entity
@Table(name = "FILM")
@JsonIgnoreProperties(value = {"castingPrincipal"})
public class Film {
    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "Nom")
    private String nom;

    @Column(name = "URL")
    private String url;

    @Column(name = "Plot", length = 60000)
    private String plot;

    @Column(name = "Sortie")
    private Date sortie;

    @Column(name = "Rating")
    private String rating;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "Pays")
    private Pays pays;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_Lieu")
    private LieuTournage lieuTournage;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "Realisateur_Film_ASSO",
            joinColumns = @JoinColumn(name = "ID_Film"),
            inverseJoinColumns = @JoinColumn(name = "Realisateur"))
    private List<Realisateur> realisateurs = new ArrayList<>();

    @OneToMany(targetEntity = Role.class, mappedBy = "film", cascade = CascadeType.ALL)
    private List<Role> roles = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "Genre_Film_ASSO",
            joinColumns = @JoinColumn(name = "ID_Film"),
            inverseJoinColumns = @JoinColumn(name = "ID_Genre"))
    private List<Genre> genreList = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "Langue")
    private Langue langue;

    public Film() {
    }

    /**
     * Méthode utilisée pour transformer une liste de noms de genres en une liste de genres.
     * Utilisée lors de la désérialisation à partir de JSON.
     *
     * @param noms Liste des noms de genres à transformer.
     */
    @JsonProperty("genres")
    private void transformListToGenreList(List<String> noms) {
        if (noms != null) {
            for (String nom : noms) {
                Genre genre = new Genre();
                genre.setNom(nom);
                genre.getFilms().add(this);
                genreList.add(genre);
            }
        }
    }

    @Override
    public String toString() {
        return "Film ID : " + id +
                ", Nom : '" + nom +
                ", URL : " + url +
                ", Plot : " + plot +
                ", Année de sortie : " + sortie +
                ", Rating : " + rating +
                ", Pays : " + pays +
                ", Lieu de tournage : " + lieuTournage +
                ", Réalisateur : " + realisateurs +
                ", Genre : " + genreList +
                ", Langue : " + langue;
    }


    //Getters & Setters

    /**
     * Récupère l'identifiant unique du film.
     *
     * @return L'identifiant unique du film.
     */
    public String getId() {
        return id;
    }

    /**
     * Définit l'identifiant unique du film.
     *
     * @param id L'identifiant unique du film.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Récupère le nom du film.
     *
     * @return Le nom du film.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Définit le nom du film.
     *
     * @param nom Le nom du film.
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Récupère l'URL associée au film.
     *
     * @return L'URL associée au film.
     */
    public String getUrl() {
        return url;
    }

    /**
     * Définit l'URL associée au film.
     *
     * @param url L'URL associée au film.
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Récupère le résumé du film.
     *
     * @return Le résumé du film.
     */
    public String getPlot() {
        return plot;
    }

    /**
     * Définit le résumé du film.
     *
     * @param plot Le résumé du film.
     */
    public void setPlot(String plot) {
        this.plot = plot;
    }

    /**
     * Récupère la date de sortie du film.
     *
     * @return La date de sortie du film.
     */
    @JsonProperty("anneeSortie")
    public Date getSortie() {
        return sortie;
    }

    /**
     * Définit la date de sortie du film à partir d'une chaîne de caractères représentant une année.
     * Si la chaîne n'est pas vide et n'est pas nulle, elle est analysée pour extraire l'année.
     * Cette année est ensuite utilisée pour créer une instance de Date représentant la sortie du film.
     * Si la chaîne est vide ou nulle, la sortie du film est définie sur null.
     *
     * @param sortie Chaîne de caractères représentant la date de sortie du film (au format "yyyy" ou "yyyy-yyyy").
     */
    public void setSortie(String sortie) {
        if (sortie != null && !sortie.isEmpty()) {
            // Séparation de la chaîne en parties en fonction du délimiteur "–"
            String[] parts = sortie.split("–");

            // Extraction de la première partie, qui doit contenir l'année de sortie
            String yearString = parts[0].trim();

            // Conversion de l'année en entier
            int year = Integer.parseInt(yearString);

            // Création d'une instance de Calendar pour manipuler la date
            Calendar cal = Calendar.getInstance();
            // Réinitialisation de toutes les valeurs de la date
            cal.clear();
            // Définition de l'année dans le calendrier
            cal.set(Calendar.YEAR, year);

            // Récupération de la date résultante du calendrier
            Date date = cal.getTime();

            // Définition de la date de sortie du film
            this.sortie = date;
        } else {
            // Si la chaîne est vide ou nulle, la sortie du film est définie sur null
            this.sortie = null;
        }
    }

    /**
     * Récupère la note attribuée au film.
     *
     * @return La note attribuée au film.
     */
    public String getRating() {
        return rating;
    }

    /**
     * Définit la note attribuée au film.
     *
     * @param rating La note attribuée au film.
     */
    public void setRating(String rating) {
        this.rating = rating;
    }

    /**
     * Récupère le pays d'origine du film.
     *
     * @return Le pays d'origine du film.
     */
    public Pays getPays() {
        return pays;
    }

    /**
     * Définit le pays d'origine du film.
     *
     * @param pays Le pays d'origine du film.
     */
    public void setPays(Pays pays) {
        this.pays = pays;
    }

    /**
     * Récupère le lieu de tournage du film.
     *
     * @return Le lieu de tournage du film.
     */
    @JsonProperty("lieuTournage")
    public LieuTournage getLieu() {
        return lieuTournage;
    }

    /**
     * Définit le lieu de tournage du film.
     *
     * @param lieuTournage Le lieu de tournage du film.
     */
    public void setLieu(LieuTournage lieuTournage) {
        this.lieuTournage = lieuTournage;
    }

    /**
     * Récupère la langue du film.
     *
     * @return La langue du film.
     */
    public Langue getLangue() {
        return langue;
    }

    /**
     * Définit la langue du film.
     *
     * @param langue La langue du film.
     */
    public void setLangue(Langue langue) {
        this.langue = langue;
    }

    /**
     * Récupère la liste des réalisateurs du film.
     *
     * @return La liste des réalisateurs du film.
     */
    public List<Realisateur> getRealisateurs() {
        return realisateurs;
    }

    /**
     * Définit la liste des réalisateurs du film.
     *
     * @param realisateurs La liste des réalisateurs du film.
     */
    public void setRealisateurs(List<Realisateur> realisateurs) {
        this.realisateurs = realisateurs;
    }

    /**
     * Récupère la liste des rôles dans le film.
     *
     * @return La liste des rôles dans le film.
     */
    public List<Role> getRoles() {
        return roles;
    }

    /**
     * Définit la liste des rôles dans le film.
     *
     * @param roles La liste des rôles dans le film.
     */
    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    /**
     * Récupère la liste des genres associés au film.
     *
     * @return La liste des genres associés au film.
     */
    public List<Genre> getGenreList() {
        return genreList;
    }

    /**
     * Définit la liste des genres associés au film.
     *
     * @param genreList La liste des genres associés au film.
     */
    public void setGenreList(List<Genre> genreList) {
        this.genreList = genreList;
    }
}