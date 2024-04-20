package euorg.nuvoprojects.cachezero1;

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

    // ------- <Getters> -------
    // Exception messages
    public final String graErr = "err1";
    public final String calErr = "err2";
    public final String wriFilErr = "err3";
    public final String reaFilErr = "err4";
    public final String savFilErr = "err5";
    public final String minErr = "exc1";
    public final String majErr = "exc2";

    // Main Window
    public final String menSet = "maiWinMenSet";
    public final String menHel = "maiWinMenHel";
    public final String menSavTex = "maiWinMenSavTex";
    public final String menSavIma = "maiWinMenSavIma";
    public final String menAbo = "maiWinMenAbo";
    public final String menExi = "maiWinMenExi";
    public final String menFon = "maiWinMenFon";
    public final String menCol = "maiWinMenCol";
    public final String menLan = "maiWinMenLan";
    public final String menCip = "maiWinMenCip";
    public final String menNav = "maiWinMenNav";

    // Cryptor Panel
    public final String cryPanFor = "cryPanFor";
    public final String cryPanNorTex = "cryPanNorTex";
    public final String cryPanEncTex = "cryPanEncTex";
    public final String cryPanSta = "cryPanSta";
    public final String cryPanEnc = "cryPanEnc";
    public final String cryPanDec = "cryPanDec";
    // ------- </Getters> -------

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

            e.printStackTrace();
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
            case "en":
                langMap = readXML(englishFile);
                break;

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
