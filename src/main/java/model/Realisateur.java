package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import index.JsonUtils;
import jakarta.persistence.*;

@Entity
@Table(name = "REALISATEUR")
public class Realisateur {
    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "Identite")
    private String identite;

    @Column(name = "DateNaissance")
    @Temporal(TemporalType.DATE)
    private Date dateNaissance;

    @Column(name = "URL")
    private String url;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "LieuNaissance")
    private LieuNaissance lieuNaissance;

    @ManyToMany(mappedBy = "realisateurs")
    private List<Film> films = new ArrayList<>();


    /**
     * Méthode privée utilisée pour désérialiser les données JSON relatives à la date de naissance et au lieu de naissance.
     *
     * @param naissance La carte contenant les informations sur la date de naissance et le lieu de naissance.
     */
    @JsonProperty("naissance")
    private void unpackNested(Map<String, String> naissance) {
        // Utilise la méthode utilitaire JsonUtils.unpackNestedDate pour extraire et traiter la date de naissance,
        // puis utilise un Consumer pour définir la date extraite.
        JsonUtils.unpackNestedDate(naissance, this::setDateNaissance);

        // Crée un nouvel objet LieuNaissance pour traiter le lieu de naissance.
        LieuNaissance lieuNaissance = new LieuNaissance();

        // Vérifie si le lieu de naissance est présent dans la carte JSON et le définit s'il existe.
        if (naissance.containsKey("lieuNaissance") && naissance.get("lieuNaissance") != null && !naissance.get("lieuNaissance").isEmpty()) {
            lieuNaissance.setLieu(naissance.get("lieuNaissance"));
            this.setLieuNaissance(lieuNaissance);
        }
    }

    /**
     * Retourne une représentation sous forme de chaîne de caractères de l'objet Réalisateur.
     * La représentation contient l'identité du réalisateur, son URL, sa date de naissance, son lieu de naissance et ses films.
     *
     * @return Une représentation sous forme de chaîne de caractères de l'objet Réalisateur.
     */
    @Override
    public String toString() {
        return "Réalisateur : " + identite +
                ", URL : '" + url +
                ", Date de naissance : " + dateNaissance +
                ", Lieu de naissance : " + lieuNaissance +
                ", Films : " + films;

    }


    // Getters et setters

    /**
     * Obtient l'identifiant du réalisateur.
     *
     * @return L'identifiant du réalisateur.
     */
    public String getId() {
        return id;
    }

    /**
     * Définit l'identifiant du réalisateur.
     *
     * @param id Le nouvel identifiant du réalisateur.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Obtient le nom ou l'identité du réalisateur.
     *
     * @return Le nom ou l'identité du réalisateur.
     */
    public String getIdentite() {
        return identite;
    }


    /**
     * Obtient la date de naissance du réalisateur.
     *
     * @return La date de naissance du réalisateur.
     */
    public Date getDateNaissance() {
        return dateNaissance;
    }

    /**
     * Définit la date de naissance du réalisateur.
     *
     * @param dateNaissance La nouvelle date de naissance du réalisateur.
     */
    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    /**
     * Obtient le lieu de naissance du réalisateur.
     *
     * @return Le lieu de naissance du réalisateur.
     */
    public LieuNaissance getLieuNaissance() {
        return lieuNaissance;
    }

    /**
     * Définit le lieu de naissance du réalisateur.
     *
     * @param lieuNaissance Le nouveau lieu de naissance du réalisateur.
     */
    public void setLieuNaissance(LieuNaissance lieuNaissance) {
        this.lieuNaissance = lieuNaissance;
    }

    /**
     * Obtient la liste des films réalisés par ce réalisateur.
     *
     * @return La liste des films réalisés par ce réalisateur.
     */
    public List<Film> getFilms() {
        return films;
    }

    /**
     * Définit la liste des films réalisés par ce réalisateur.
     *
     * @param films La nouvelle liste des films réalisés par ce réalisateur.
     */
    public void setFilms(List<Film> films) {
        this.films = films;
    }

    /**
     * Obtient l'URL associée au réalisateur.
     *
     * @return L'URL associée au réalisateur.
     */
    public String getUrl() {
        return url;
    }

    /**
     * Définit l'URL associée au réalisateur.
     *
     * @param url La nouvelle URL associée au réalisateur.
     */
    public void setUrl(String url) {
        this.url = url;
    }
}
