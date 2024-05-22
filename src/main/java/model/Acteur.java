package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;

@Entity
@JsonIgnoreProperties(value = {"height", "roles"})
@NamedQueries({
        @NamedQuery(name = "Acteur.findByName", query = "SELECT a FROM Acteur a WHERE a.identite = :name"),
        @NamedQuery(name = "Acteur.findById", query = "SELECT a FROM Acteur a WHERE a.id = :id"),
        @NamedQuery(name = "Acteur.findById2", query = "SELECT a FROM Acteur a WHERE a.identite = :name or a.identite = :name2")
})
public class Acteur {
    @Id
    private String id;

    @OneToMany(targetEntity = Role.class, mappedBy = "acteur")
    private List<Role> roles = new ArrayList<>();

    private String identite;
    private String dateNaissance;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "lieuNaissance_nom")
    private LieuNaissance lieuNaissance;

    private String url;

    // Getters and setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdentite() {
        return identite;
    }

    public void setIdentite(String identite) {
        this.identite = identite;
    }

    public String getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public LieuNaissance getLieuNaissance() {
        return lieuNaissance;
    }

    public void setLieuNaissance(LieuNaissance lieuNaissance) {
        this.lieuNaissance = lieuNaissance;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @JsonProperty("naissance")
    private void unpackNested(Map<String, String> naissance) {
        if (naissance != null) {
            this.dateNaissance = naissance.get("dateNaissance");
            LieuNaissance lieuNaissance = new LieuNaissance();
            if (naissance.get("lieuNaissance") != "") {
                lieuNaissance.setLieu(naissance.get("lieuNaissance"));
                this.setLieuNaissance(lieuNaissance);
            }
        }
    }

    @Override
    public String toString() {
        return "\n Acteur [id=" + id + ", identite=" + identite + ", dateNaissance=" + dateNaissance + ", lieuNaissance=" + lieuNaissance + ", url=" + url + "] \n";
    }
}
