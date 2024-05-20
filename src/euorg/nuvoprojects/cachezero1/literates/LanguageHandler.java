package euorg.nuvoprojects.cachezero1.literates;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Locale;
import java.util.Properties;

import javax.swing.JOptionPane;

public class LanguageHandler {

    // Important
    private final String germanLangPath = "/languages/german.xml";
    private final String frenchLangPath = "/languages/french.xml";
    private final String englishLangPath = "/languages/english.xml";

    private String germanFile;
    private String frenchFile;
    private String englishFile;

    private Properties properties;

    // Initiate class
    public LanguageHandler() {
        this.germanFile = this.germanLangPath;
        this.frenchFile = this.frenchLangPath;
        this.englishFile = this.englishLangPath;
    }

    // Read from the file
    private HashMap<String, String> readXML(String file) {

        HashMap<String, String> map = new HashMap<>();

        try {

            properties = new Properties();
            InputStream inputStream = getClass().getResourceAsStream(file);
            properties.loadFromXML(inputStream);

            properties.forEach((key, value) -> {
                map.put(String.valueOf(key), String.valueOf(value));
            });
            
        } catch (Exception e) {

            switch (getLanguage()) {
                case "de":
                    JOptionPane.showMessageDialog(null, "Während des Versuchs eine Datei zu lesen, trat ein Fehler auf", "Gravierender Fehler", JOptionPane.ERROR_MESSAGE);
                    break;

                case "fr":
                    JOptionPane.showMessageDialog(null, "Une erreur s'est produit, lors de la tentative de lire un fichier", "Exception majeure", JOptionPane.ERROR_MESSAGE);
                    break;
            
                default:
                    JOptionPane.showMessageDialog(null, "An error occured, whilst trying to read a file", "Major Exception", JOptionPane.ERROR_MESSAGE);
                    break;
            }

            properties = new Properties();
        }

        return map;

    }

    // Return lang String
    private static String getLanguage() {
        try {
            String lang = Locale.getDefault().getLanguage();

            if (lang.isBlank()) {
                return "en";
            }

            return lang;
            
        } catch (Exception e) {
            return "en";
        }
    }

    // Get langMap
    public HashMap<String, String> getLangMap(String lang) {

        HashMap<String, String> langMap;

        switch (lang) {
            case "fr":
                langMap = readXML(frenchFile);
                break;

            case "de":
                langMap = readXML(germanFile);
                break;
        
            default:
                langMap = readXML(englishFile);
                break;
        }

        return langMap;

    }
    
}
