package parser;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.EntityManager;
import model.Film;
import model.Role;

/**
 * La classe Json est utilisée pour lire des données à partir du JSON (films.json) et les mapper vers des entités de base de données via JPA
 */
public class Json {
    // Mappage des données JSON en objets Java
    private static ObjectMapper MAPPER = getMapper();

    /**
     * Constructeur de la classe Json.
     *
     * @param src Chemin vers le fichier JSON à lire.
     * @param em  EntityManager pour interagir avec la base de données.
     */
    public Json(String src, EntityManager em) {
        try {
            // Lecture des données JSON et mapping vers des objets Film
            Film[] films = MAPPER.readValue(new File(src), Film[].class);
            // Parcours de chaque film pour configuration et fusion avec la base de données
            for (Film film : films) {
                // Configuration des rôles pour référencer le film associé
                for (Role role : film.getRoles()) {
                    role.setFilm(film);
                }
                em.merge(film);
            }
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Méthode privée pour obtenir une instance d'ObjectMapper.
     *
     * @return Une instance d'ObjectMapper pour mapper les données JSON en objets Java.
     */
    private static ObjectMapper getMapper() {
        return new ObjectMapper();
    }
}