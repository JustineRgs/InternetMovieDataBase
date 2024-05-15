package fr.diginamic.banque.entite;

import fr.diginamic.banque.Adresse;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "CLIENT")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;

    @Column(name = "NOM")
    private String nom;

    @Column(name = "PRENOM")
    private String prenom;

    @Column(name = "DATE_NAISSANCE")
    private LocalDate dateNaissance;

    @Embedded
    private Adresse adresse;

    @ManyToOne
    @JoinColumn(name = "ID_BANQUE")
    private Banque banque;

    @ManyToMany
    @JoinTable(name = "COMPTE_CLIENT",
            joinColumns = @JoinColumn(name = "ID_CLIENT", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "ID_COMPTE", referencedColumnName = "ID"))
    private Set<Compte> comptes;

    public Client() {
    }

    public Client(String nom, String prenom, LocalDate dateNaissance, Banque banque, Adresse adresse) {
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.banque = banque;
        this.adresse = adresse;
    }

    public Integer getId() {
        return ID;
    }

    public void setId(Integer ID) {
        this.ID = ID;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Banque getBanque() {
        return banque;
    }

    public void setBanque(Banque banque) {
        this.banque = banque;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public Set<Compte> getComptes() {
        return comptes;
    }

    public void setComptes(Set<Compte> comptes) {
        this.comptes = comptes;
    }
}
