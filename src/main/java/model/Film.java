package model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;

@Entity
@JsonIgnoreProperties(value = {"castingPrincipal"})
public class Film {
    @Id
    private String id;

    @ManyToOne(cascade = CascadeType.MERGE)
    private Pays pays;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_lieu")
    private LieuTournage lieuTournage;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "T_Film_Realisateur_Associations",
            joinColumns = @JoinColumn(name = "id_Film"),
            inverseJoinColumns = @JoinColumn(name = "realisateur"))
    private List<Realisateur> realisateurs = new ArrayList<>();

    @OneToMany(targetEntity = Role.class, mappedBy = "film", cascade = CascadeType.ALL)
    private List<Role> roles = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "T_Film_Genre_Associations",
            joinColumns = @JoinColumn(name = "id_Film"),
            inverseJoinColumns = @JoinColumn(name = "id_Genre"))
    private List<Genre> genreList = new ArrayList<>();

    private String nom;
    private String url;
    @Column(length = 60000)
    private String plot;

    private Date sortie;
    private String rating;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "langue")
    private Langue langue;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public Langue getLangue() {
        return langue;
    }

    public void setLangue(Langue langue) {
        this.langue = langue;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @JsonProperty("anneeSortie")
    public Date getSortie() {
        return sortie;
    }

    @JsonProperty("anneeSortie")
    public void setSortie(String sortie) {
        if (sortie != null && !sortie.isEmpty()) {
            // Split the string based on the dash ("-") to handle ranges
            String[] parts = sortie.split("–"); // Ensure this is an en-dash, not a hyphen

            // Extract the first part, which should contain the year
            String yearString = parts[0].trim(); // Trim to remove leading/trailing whitespace

            // Parse the year string to an integer
            int year = Integer.parseInt(yearString);

            // Create a Calendar instance and set the year
            Calendar cal = Calendar.getInstance();
            cal.clear();
            cal.set(Calendar.YEAR, year);

            // Get the date from the Calendar instance
            Date date = cal.getTime();

            // Set the sortie field to the parsed date
            this.sortie = date;
        } else {
            // If the input string is empty or null, set sortie to null
            this.sortie = null;
        }
    }


    public Pays getPays() {
        return pays;
    }

    public void setPays(Pays pays) {
        this.pays = pays;
    }

    @JsonProperty("lieuTournage")
    public LieuTournage getLieu() {
        return lieuTournage;
    }

    public void setLieu(LieuTournage lieuTournage) {
        this.lieuTournage = lieuTournage;
    }

    public List<Realisateur> getRealisateurs() {
        return realisateurs;
    }

    public void setRealisateurs(List<Realisateur> realisateurs) {
        this.realisateurs = realisateurs;
    }

    public List<Genre> getGenreList() {
        return genreList;
    }

    public void setGenreList(List<Genre> genreList) {
        this.genreList = genreList;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Film{" +
                "id='" + id + '\'' +
                ", pays=" + pays +
                ", lieu=" + lieuTournage +
                ", realisateurs=" + realisateurs +
                ", roles=" + roles +
                ", genreList=" + genreList +
                ", nom='" + nom + '\'' +
                ", url='" + url + '\'' +
                ", plot='" + plot + '\'' +
                ", langue='" + langue + '\'' +
                ", sortie=" + sortie +
                ", rating=" + rating +
                '}';
    }
}
