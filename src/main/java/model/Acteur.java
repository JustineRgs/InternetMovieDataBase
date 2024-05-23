package model;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import index.JsonUtils;
import jakarta.persistence.*;

@Entity
@Table(name = "ACTEUR")
@JsonIgnoreProperties(value = {"height", "roles"})
@NamedQueries({
        @NamedQuery(name = "Acteur.findByName", query = "SELECT a FROM Acteur a WHERE a.identite = :name")
})
public class Acteur {
    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "Identite")
    private String identite;

    @Column(name = "URL")
    private String url;

    @Column(name = "DateNaissance")
    @Temporal(TemporalType.DATE)
    private Date dateNaissance;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "LieuNaissance")
    private LieuNaissance lieuNaissance;

    @OneToMany(targetEntity = Role.class, mappedBy = "acteur")
    private List<Role> roles = new ArrayList<>();

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
     * Retourne une représentation sous forme de chaîne de caractères de l'objet Acteur.
     * La représentation contient l'identité de l'acteur, son URL, sa date de naissance, son lieu de naissance et ses roles.
     *
     * @return Une représentation sous forme de chaîne de caractères de l'objet Acteur.
     */
    @Override
    public String toString() {
        return "Acteur : " + identite +
                ", URL : '" + url +
                ", Date de naissance : " + dateNaissance +
                ", Lieu de naissance : " + lieuNaissance +
                ", Roles : " + roles;
    }


    // Getters and setters

    /**
     * Obtient l'identifiant unique de l'acteur.
     *
     * @return L'identifiant unique de l'acteur.
     */
    public String getId() {
        return id;
    }

    /**
     * Définit l'identifiant unique de l'acteur.
     *
     * @param id L'identifiant unique de l'acteur.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Obtient le nom ou l'identité de l'acteur.
     *
     * @return Le nom ou l'identité de l'acteur.
     */
    public String getIdentite() {
        return identite;
    }

    /**
     * Définit le nom ou l'identité de l'acteur.
     *
     * @param identite Le nom ou l'identité de l'acteur.
     */
    public void setIdentite(String identite) {
        this.identite = identite;
    }

    /**
     * Obtient l'URL associée à l'acteur.
     *
     * @return L'URL associée à l'acteur.
     */
    public String getUrl() {
        return url;
    }

    /**
     * Définit l'URL associée à l'acteur.
     *
     * @param url L'URL associée à l'acteur.
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Obtient la date de naissance de l'acteur.
     *
     * @return La date de naissance de l'acteur.
     */
    public Date getDateNaissance() {
        return dateNaissance;
    }

    /**
     * Définit la date de naissance de l'acteur.
     *
     * @param dateNaissance La date de naissance de l'acteur.
     */
    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    /**
     * Obtient le lieu de naissance de l'acteur.
     *
     * @return Le lieu de naissance de l'acteur.
     */
    public LieuNaissance getLieuNaissance() {
        return lieuNaissance;
    }

    /**
     * Définit le lieu de naissance de l'acteur.
     *
     * @param lieuNaissance Le lieu de naissance de l'acteur.
     */
    public void setLieuNaissance(LieuNaissance lieuNaissance) {
        this.lieuNaissance = lieuNaissance;
    }

    /**
     * Obtient la liste des rôles joués par l'acteur.
     *
     * @return La liste des rôles joués par l'acteur.
     */
    public List<Role> getRoles() {
        return roles;
    }

    /**
     * Définit la liste des rôles joués par l'acteur.
     *
     * @param roles La liste des rôles joués par l'acteur.
     */
    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
