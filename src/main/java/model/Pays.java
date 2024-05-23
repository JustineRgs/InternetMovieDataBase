package model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "PAYS")
public class Pays {

    @Id
    @Column(name = "Nom")
    private String nom;

    @Column(name = "URL")
    private String url;

    public Pays() {
    }

    /**
     * Constructeur avec un paramètre pour initialiser le nom du pays.
     *
     * @param nom Le nom du pays.
     */
    public Pays(String nom) {
        this.nom = nom;
    }

    /**
     * Retourne une représentation sous forme de chaîne de caractères du pays,
     * contenant le nom du pays et son URL.
     *
     * @return Une représentation sous forme de chaîne de caractères du pays.
     */
    @Override
    public String toString() {
        return nom;
    }


    //Getters & Setters

    /**
     * Retourne le nom du pays.
     *
     * @return Le nom du pays.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Modifie le nom du pays.
     *
     * @param nom Le nouveau nom du pays.
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Retourne l'URL associée au pays.
     *
     * @return L'URL associée au pays.
     */
    public String getUrl() {
        return url;
    }

    /**
     * Modifie l'URL associée au pays.
     *
     * @param url La nouvelle URL associée au pays.
     */
    public void setUrl(String url) {
        this.url = url;
    }
}