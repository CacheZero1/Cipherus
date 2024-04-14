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
    private static LanguageHandler languageHandler;
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

    // Font fragments
    public final String titleFamilyName = "titleFamily";
    public final String titleStyleName = "titleStyle";
    public final String titleSizeName = "titleSize";

    public final String subtitleFamilyName = "subtitleFamily";
    public final String subtitleStyleName = "subtitleStyle";
    public final String subtitleSizeName = "subtitleSize";

    public final String selectionFamilyName = "selectionFamily";
    public final String selectionStyleName = "selectionStyle";
    public final String selectionSizeName = "selectionSize";

    public final String inputFamilyName = "inputFamily";
    public final String inputStyleName = "inputStyle";
    public final String inputSizeName = "inputSize";

    // Data
    public final String langName = "lang";
    public final String modeName = "mode";
    // ------- </Getters> -------
    
    // Initiate class
    public SaveHandler(String desiredPath, LanguageHandler langHandler) {
        languageHandler = langHandler;
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
            JOptionPane.showMessageDialog(null, languageHandler.getLangMap(entryMap.get(langName)).get(languageHandler.wriFilErr), languageHandler.getLangMap(entryMap.get(langName)).get(languageHandler.majErr), JOptionPane.ERROR_MESSAGE);
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
            JOptionPane.showMessageDialog(null, languageHandler.getLangMap(getLanguage()).get(languageHandler.reaFilErr), languageHandler.getLangMap(getLanguage()).get(languageHandler.majErr), JOptionPane.ERROR_MESSAGE);
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
        infoMap.put(titleFamilyName, fontDefaultFamily);
        infoMap.put(titleStyleName, String.valueOf(Font.PLAIN));
        infoMap.put(titleSizeName, String.valueOf(20));

        // Subtitle
        infoMap.put(subtitleFamilyName, fontDefaultFamily);
        infoMap.put(subtitleStyleName, String.valueOf(Font.PLAIN));
        infoMap.put(subtitleSizeName, String.valueOf(20));

        // Selection
        infoMap.put(selectionFamilyName, fontDefaultFamily);
        infoMap.put(selectionStyleName, String.valueOf(Font.PLAIN));
        infoMap.put(selectionSizeName, String.valueOf(13));

        // Input
        infoMap.put(inputFamilyName, fontDefaultFamily);
        infoMap.put(inputStyleName, String.valueOf(Font.PLAIN));
        infoMap.put(inputSizeName, String.valueOf(18));
        // ------- </Font> -------

        // Save
        writeXML(infoMap);

        // ------- <Mappers> -------
        // Data
        dataMap.put(modeName, "False");
        dataMap.put(langName, infoMap.get(modeName));

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
                JOptionPane.showMessageDialog(null, languageHandler.getLangMap(getLanguage()).get(languageHandler.wriFilErr), languageHandler.getLangMap(getLanguage()).get(languageHandler.majErr), JOptionPane.ERROR_MESSAGE);
            }

            // Create default mappings
            createDefaultMappings();

        } else {

            try {

                HashMap<String, String> stored = readXML();

                dataMap = new HashMap<String, String>();
                fontMap = new HashMap<String, Font>();

                dataMap.put(modeName, stored.get(modeName));
                dataMap.put(langName, stored.get(langName));

                fontMap.put(titleFontName, new Font(stored.get(titleFamilyName), Integer.valueOf(stored.get(titleStyleName)), Integer.valueOf(stored.get(titleSizeName))));
                fontMap.put(subtitleFontName, new Font(stored.get(subtitleFamilyName), Integer.valueOf(stored.get(subtitleStyleName)), Integer.valueOf(stored.get(subtitleSizeName))));
                fontMap.put(selectionFontName, new Font(stored.get(selectionFamilyName), Integer.valueOf(stored.get(selectionStyleName)), Integer.valueOf(stored.get(selectionSizeName))));
                fontMap.put(inputFontName, new Font(stored.get(inputFamilyName), Integer.valueOf(stored.get(inputStyleName)), Integer.valueOf(stored.get(inputSizeName))));
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, languageHandler.getLangMap(getLanguage()).get(languageHandler.reaFilErr), languageHandler.getLangMap(getLanguage()).get(languageHandler.majErr), JOptionPane.ERROR_MESSAGE);
            }

        }

    }

    // Apply changes
    public void applyChanges(HashMap<String, Font> newFontMap, HashMap<String, String> newDataMap) {

        // Apply changes in current instance
        fontMap = newFontMap;
        dataMap = newDataMap;

        // ------- <Save> -------
        // New map
        HashMap<String, String> toBeSaved = new HashMap<String, String>();

        // DataMap transcription
        toBeSaved.put(langName, dataMap.get(langName));
        toBeSaved.put(modeName, dataMap.get(modeName));

        // ------- <FontMap transcription> -------
        // Title
        toBeSaved.put(titleFamilyName, fontMap.get(titleFontName).getFamily());
        toBeSaved.put(titleStyleName, String.valueOf(fontMap.get(titleFontName).getStyle()));
        toBeSaved.put(titleSizeName, String.valueOf(fontMap.get(titleFontName).getSize()));

        // Subtitle
        toBeSaved.put(subtitleFamilyName, fontMap.get(subtitleFontName).getFamily());
        toBeSaved.put(subtitleStyleName, String.valueOf(fontMap.get(subtitleFontName).getStyle()));
        toBeSaved.put(subtitleSizeName, String.valueOf(fontMap.get(subtitleFontName).getSize()));

        // Selection
        toBeSaved.put(selectionFamilyName, fontMap.get(selectionFontName).getFamily());
        toBeSaved.put(selectionStyleName, String.valueOf(fontMap.get(selectionFontName).getStyle()));
        toBeSaved.put(selectionSizeName, String.valueOf(fontMap.get(selectionFontName).getSize()));

        // Input
        toBeSaved.put(inputFamilyName, fontMap.get(inputFontName).getFamily());
        toBeSaved.put(inputStyleName, String.valueOf(fontMap.get(inputFontName).getStyle()));
        toBeSaved.put(inputSizeName, String.valueOf(fontMap.get(inputFontName).getSize()));
        // ------- </FontMap transcription> -------
        // ------- </Save> -------

        // Save
        writeXML(toBeSaved);

    }

}
