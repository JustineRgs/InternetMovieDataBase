package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
public class Realisateur {
    @Id
    private String id;
    private String identite;
    private String dateNaissance;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "lieuNaissance_nom")
    private LieuNaissance lieuNaissance;

    @ManyToMany(mappedBy = "realisateurs")
    private List<Film> films = new ArrayList<>();
    private String url;

    public String getIdentite() {
        return identite;
    }

    public void setIdentite(String identite) {
        this.identite = identite;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Film> getFilms() {
        return films;
    }

    public void setFilms(List<Film> films) {
        this.films = films;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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


    public LieuNaissance getLieuNaissance() {
        return lieuNaissance;
    }

    public void setLieuNaissance(LieuNaissance lieuNaissance) {
        this.lieuNaissance = lieuNaissance;
    }

    @Override
    public String toString() {
        return "Realisateur{" +
                "id='" + id + '\'' +
                ", identite='" + identite +
                ", lieuNaissance='" + lieuNaissance + '\'' +
                ", films=" + films +
                ", url='" + url + '\'' +
                '}';
    }
}
