package fr.diginamic;

import org.hibernate.annotations.Columns;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Persistence;
import jakarta.persistence.Table;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Entity
@Table(name = "livre")
public class Livre {
	@Id
	private int id;

	private String titre;
	private String auteur;

	@PersistenceContext
	private static EntityManager entityManager;
	static {
		entityManager = Persistence.createEntityManagerFactory("bibliotheque").createEntityManager();
	}

	public static Livre getLivreById(int id) {
		return entityManager.find(Livre.class, id);
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
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

	/**
	 * @return the entityManager
	 */
	public static EntityManager getEntityManager() {
		return entityManager;
	}

	/**
	 * @param entityManager the entityManager to set
	 */
	public static void setEntityManager(EntityManager entityManager) {
		Livre.entityManager = entityManager;
	}

}
