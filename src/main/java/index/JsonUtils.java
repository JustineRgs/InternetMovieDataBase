package index;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.function.Consumer;

/**
 * Cette classe fournit des méthodes utilitaires pour le traitement des données JSON.
 */
public class JsonUtils {

    /**
     * Extrait et traite une date à partir d'une carte JSON et utilise un Consumer pour définir la date extraite.
     *
     * @param naissance  La carte contenant les informations sur la date de naissance.
     * @param dateSetter Le Consumer qui définit la date extraite.
     */
    public static void unpackNestedDate(Map<String, String> naissance, Consumer<Date> dateSetter) {
        if (naissance != null && naissance.containsKey("dateNaissance") && naissance.get("dateNaissance") != null && !naissance.get("dateNaissance").isEmpty()) {
            String dateString = naissance.get("dateNaissance").trim();
            if (!dateString.isEmpty()) {
                SimpleDateFormat format = new SimpleDateFormat("MMMM dd yyyy", Locale.ENGLISH);
                try {
                    Date parsedDate = format.parse(dateString);
                    dateSetter.accept(new Date(parsedDate.getTime()));
                } catch (ParseException e) {
                    dateSetter.accept(null);
                }
            } else {
                dateSetter.accept(null);
            }
        } else {
            dateSetter.accept(null);
        }
    }
}
