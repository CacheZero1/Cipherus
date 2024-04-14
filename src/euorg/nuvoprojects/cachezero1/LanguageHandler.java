package euorg.nuvoprojects.cachezero1;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

import javax.swing.JOptionPane;

public class LanguageHandler {

    // Important
    private final String germanLangPath = "/languages/german.xml";
    private final String frenchLangPath = "/languages/french.xml";
    private final String englishLangPath = "/languages/english.xml";

    private File germanFile;
    private File frenchFile;
    private File englishFile;

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
    // ------- </Getters> -------

    // Initiate class
    public LanguageHandler() {
        this.germanFile = new File(getClass().getResource(this.germanLangPath).getFile());
        this.frenchFile = new File(getClass().getResource(this.frenchLangPath).getFile());
        this.englishFile = new File(getClass().getResource(this.englishLangPath).getFile());
    }

    // Read from the file
    private HashMap<String, String> readXML(File file) {

        HashMap<String, String> map = new HashMap<>();

        try {

            properties = new Properties();
            InputStream inputStream = new FileInputStream(file);
            properties.loadFromXML(inputStream);

            properties.forEach((key, value) -> {
                map.put(String.valueOf(key), String.valueOf(value));
            });
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "An error occured, whilst trying to read a file", "Major Exception", JOptionPane.ERROR_MESSAGE);
            properties = new Properties();
        }

        return map;

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
