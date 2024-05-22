package model;

import jakarta.persistence.*;

@Entity
@Table(name = "LIEU_TOURNAGE")
public class LieuTournage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "Ville")
    private String ville;

    @Column(name = "Etat/Dept")
    private String etatDept;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "Pays")
    private Pays pays;

    public LieuTournage() {
    }

    /**
     * Constructeur avec paramètres pour initialiser la ville, l'état/département et le pays du lieu de tournage.
     *
     * @param ville    Le nom de la ville du lieu de tournage.
     * @param etatDept Le nom de l'état ou du département du lieu de tournage.
     * @param pays     Le pays associé au lieu de tournage.
     */
    public LieuTournage(String ville, String etatDept, Pays pays) {
        this.ville = ville;
        this.etatDept = etatDept;
        this.pays = pays;
    }

    /**
     * Retourne une représentation sous forme de chaîne de caractères du lieu de tournage,
     * contenant la ville, l'état/département et le pays.
     *
     * @return Une représentation sous forme de chaîne de caractères du lieu de tournage.
     */
    @Override
    public String toString() {
        return "Ville : " + ville + ", Etat/Departement : " + etatDept + ", Pays : " + pays;
    }


    // Getters & Setters

    /**
     * Retourne l'identifiant du lieu de tournage.
     *
     * @return L'identifiant du lieu de tournage.
     */
    public Long getId() {
        return id;
    }

    /**
     * Modifie l'identifiant du lieu de tournage.
     *
     * @param id Le nouvel identifiant du lieu de tournage.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Retourne le nom de la ville du lieu de tournage.
     *
     * @return Le nom de la ville du lieu de tournage.
     */
    public String getVille() {
        return ville;
    }

    /**
     * Modifie le nom de la ville du lieu de tournage.
     *
     * @param ville Le nouveau nom de la ville du lieu de tournage.
     */
    public void setVille(String ville) {
        this.ville = ville;
    }

    /**
     * Retourne le nom de l'état ou du département du lieu de tournage.
     *
     * @return Le nom de l'état ou du département du lieu de tournage.
     */
    public String getEtatDept() {
        return etatDept;
    }

    /**
     * Modifie le nom de l'état ou du département du lieu de tournage.
     *
     * @param etatDept Le nouveau nom de l'état ou du département du lieu de tournage.
     */
    public void setEtatDept(String etatDept) {
        this.etatDept = etatDept;
    }

    /**
     * Retourne le pays associé au lieu de tournage.
     *
     * @return Le pays associé au lieu de tournage.
     */
    public Pays getPays() {
        return pays;
    }

    /**
     * Modifie le pays associé au lieu de tournage.
     *
     * @param pays Le nouveau pays associé au lieu de tournage.
     */
    public void setPays(Pays pays) {
        this.pays = pays;
    }
}