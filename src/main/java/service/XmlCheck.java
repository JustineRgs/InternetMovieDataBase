package service;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import jakarta.persistence.EntityManager;
import parser.Json;

import java.io.File;

/**
 * Cette classe vérifie les configurations XML et remplit la base de données avec des données JSON si nécessaire.
 */
public class XmlCheck {

    /**
     * Constructeur de la classe XmlCheck.
     *
     * @param em EntityManager pour interagir avec la base de données.
     */
    public XmlCheck(EntityManager em) {
        try {
            // Chargement du fichier XML de configuration
            File file = new File("src/main/resources/META-INF/persistence.xml");

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName("property");

            // Vérification de la configuration pour décider s'il faut remplir la base de données avec des données JSON
            if (!nodeList.item(4).getAttributes().getNamedItem("value").getNodeValue().equals("none")) {
                System.out.println("\nCREATION ET REMPLISSAGE DE LA BDD DEPUIS LE JSON");
                em.getTransaction().begin();

                // Remplissage de la base de données avec les données JSON
                new Json("films.json", em);

                em.getTransaction().commit();
                System.out.println("\n** BASE DE DONNEES REMPLIE AVEC SUCCES **");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
