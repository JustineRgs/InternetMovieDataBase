package fr.diginamic;

public class TestLivre {

	public static void main(String[] args) {
		int idLivreRecherche = 5;

		Livre livreTrouve = Livre.getLivreById(idLivreRecherche);

		if (livreTrouve != null) {
			System.out.println("Détails du livre :");
			System.out.println("ID : " + livreTrouve.getId());
			System.out.println("Auteur : " + livreTrouve.getAuteur());
			System.out.println("Titre : " + livreTrouve.getTitre());
		} else {
			System.out.println("Aucun livre trouvé avec l'ID : " + idLivreRecherche);
		}
	}
}
