package euorg.nuvoprojects.cachezero1;

import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Locale;
import java.util.Properties;


import javax.swing.JOptionPane;

public class SaveHandler {

    // Important
    private final String savePath;
    private File saveFile;
    private Properties properties;

    // Maps
    private static HashMap<String, String> dataMap;
    private static HashMap<String, Font> fontMap;

    // ------- <Getters> -------
    // Font default
    public final String fontDefaultFamily = "Default";

    // Fonts
    public final String titleFontName = "title";
    public final String subtitleFontName = "subtitle";
    public final String selectionFontName = "selection";
    public final String inputFontName = "input";

    // Data
    public final String langName = "lang";
    public final String modeName = "mode";
    // ------- </Getters> -------
    
    // Initiate class
    public SaveHandler(String desiredPath) {
        this.savePath = desiredPath + "/cipherusSave.xml";
        saveFile = new File(savePath);
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

    // Write to the file
    private void writeXML(HashMap<String, String> entryMap) {
        try {

            properties = new Properties();

            for (String key : entryMap.keySet()) {

                properties.setProperty(key, entryMap.get(key));
                OutputStream outputStream = new FileOutputStream(savePath);
                properties.storeToXML(outputStream, null);

            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "An error occured, whilst trying to write a file", "Major Exception", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Read from the file
    private HashMap<String, String> readXML() {

        HashMap<String, String> map = new HashMap<>();

        try {

            properties = new Properties();
            InputStream inputStream = new FileInputStream(savePath);
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

    // Create the file with default mappings
    public void createDefaultMappings() {

        // Create mappings
        HashMap<String, String> infoMap = new HashMap<String, String>();
        dataMap = new HashMap<String, String>();
        fontMap = new HashMap<String, Font>();

        // Language and brightness mode
        infoMap.put(langName, getLanguage());
        infoMap.put(modeName, "False");

        // ------- <Font> -------
        // Title
        infoMap.put("titleFamily", fontDefaultFamily);
        infoMap.put("titleStyle", String.valueOf(Font.PLAIN));
        infoMap.put("titleSize", String.valueOf(20));

        // Subtitle
        infoMap.put("subtitleFamily", fontDefaultFamily);
        infoMap.put("subtitleStyle", String.valueOf(Font.PLAIN));
        infoMap.put("subtitleSize", String.valueOf(20));

        // Selection
        infoMap.put("selectionFamily", fontDefaultFamily);
        infoMap.put("selectionStyle", String.valueOf(Font.PLAIN));
        infoMap.put("selectionSize", String.valueOf(13));

        // Input
        infoMap.put("inputFamily", fontDefaultFamily);
        infoMap.put("inputStyle", String.valueOf(Font.PLAIN));
        infoMap.put("inputSize", String.valueOf(18));
        // ------- </Font> -------

        // Save
        writeXML(infoMap);

        // ------- <Mappers> -------
        // Data
        dataMap.put("mode", "False");
        dataMap.put("lang", infoMap.get("lang"));

        // Font
        fontMap.put(titleFontName, new Font(fontDefaultFamily, Font.PLAIN, 20));
        fontMap.put(subtitleFontName, new Font(fontDefaultFamily, Font.PLAIN, 20));
        fontMap.put(selectionFontName, new Font(fontDefaultFamily, Font.PLAIN, 13));
        fontMap.put(inputFontName, new Font(fontDefaultFamily, Font.PLAIN, 18));
        // ------- </Mappers> -------

    }

    // Get fontMap
    public HashMap<String, Font> getFontMap() {
        return fontMap;
    }

    // Get dataMap
    public HashMap<String, String> getDataMap() {
        return dataMap;
    }

    // Initiate process
    public void setupSave() {

        if (!saveFile.exists()) {

            // Create File
            try {
                saveFile.createNewFile();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "An error occured, whilst trying to write a file", "Major Exception", JOptionPane.ERROR_MESSAGE);
            }

            // Create default mappings
            createDefaultMappings();

        } else {

            try {

                HashMap<String, String> stored = readXML();

                dataMap.put(modeName, stored.get("mode"));
                dataMap.put(langName, stored.get("lang"));

                fontMap.put(titleFontName, new Font(stored.get("titleFamily"), Integer.valueOf(stored.get("titleStyle")), Integer.valueOf(stored.get("titleSize"))));
                fontMap.put(subtitleFontName, new Font(stored.get("subtitleFamily"), Integer.valueOf(stored.get("subtitleStyle")), Integer.valueOf(stored.get("subtitleSize"))));
                fontMap.put(selectionFontName, new Font(stored.get("selectionFamily"), Integer.valueOf(stored.get("selectionStyle")), Integer.valueOf(stored.get("selectionSize"))));
                fontMap.put(inputFontName, new Font(stored.get("inputFamily"), Integer.valueOf(stored.get("inputStyle")), Integer.valueOf(stored.get("inputSize"))));
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "An error occured, whilst trying to read a file", "Major Exception", JOptionPane.ERROR_MESSAGE);
            }

        }

    }

}
