package fr.diginamic;

import jakarta.persistence.*;
import org.hibernate.annotations.Columns;

import java.util.List;

@Entity
@Table(name = "livre")
public class Livre {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "TITRE")
	private String titre;

	@Column(name = "AUTEUR")
	private String auteur;

	@ManyToMany(mappedBy = "livres")
	private List<Emprunt> emprunts;

	@PersistenceContext
	private static EntityManager entityManager;
	static {
		entityManager = Persistence.createEntityManagerFactory("bibliotheque").createEntityManager();
	}

	// Constructeur sans argument
	public Livre() {
	}

	// Votre constructeur existant
	public Livre(String titre, String auteur) {
		this.titre = titre;
		this.auteur = auteur;
	}

	public static Livre getLivreById(int id) {
		return entityManager.find(Livre.class, id);
	}

	public static void addLivre(Livre livre) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.persist(livre);
		transaction.commit();
	}

	public static void updateLivre(Livre livre) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.merge(livre);
		transaction.commit();
	}

	public static void deleteLivre(int id) {
		Livre livre = entityManager.find(Livre.class, id);
		if (livre != null) {
			EntityTransaction transaction = entityManager.getTransaction();
			transaction.begin();
			entityManager.remove(livre);
			transaction.commit();
		} else {
			System.out.println("Livre non trouvé avec l'ID : " + id);
		}
	}



	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}


	/**
	 * @return the titre
	 */
	public String getTitre() {
		return titre;
	}

	/**
	 * @param titre the titre to set
	 */
	public void setTitre(String titre) {
		this.titre = titre;
	}

	/**
	 * @return the auteur
	 */
	public String getAuteur() {
		return auteur;
	}

	/**
	 * @param auteur the auteur to set
	 */
	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}
}
