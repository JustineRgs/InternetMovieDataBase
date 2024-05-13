package fr.diginamic;

public class TestLivre {

	public static void main(String[] args) {
		//Create
		Livre nouveauLivre = new Livre("Le Seigneur des Anneaux", "J.R.R. Tolkien");
		Livre.addLivre(nouveauLivre);

		int idLivre = 5;

		//Read
		Livre livreTrouve = Livre.getLivreById(idLivre);

		if (livreTrouve != null) {
			System.out.println("Détails du livre :");
			System.out.println("ID : " + livreTrouve.getId());
			System.out.println("Auteur : " + livreTrouve.getAuteur());
			System.out.println("Titre : " + livreTrouve.getTitre());
		} else {
			System.out.println("Aucun livre trouvé avec l'ID : " + idLivre);
		}

		//Update
		Livre livreAModifier = Livre.getLivreById(1);
		if (livreAModifier != null) {
			livreAModifier.setTitre("Nouveau titre");
			Livre.updateLivre(livreAModifier);
		} else {
			System.out.println("Livre non trouvé avec l'ID : 10");
		}

		//Delete
		Livre.deleteLivre(2);
	}
}
