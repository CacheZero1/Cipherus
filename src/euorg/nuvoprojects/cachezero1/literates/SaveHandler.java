package euorg.nuvoprojects.cachezero1.literates;

import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Properties;

import euorg.nuvoprojects.cachezero1.Utility;
import euorg.nuvoprojects.cachezero1.gui.ErrorPane;

public class SaveHandler {

    // Important
    private static LanguageHandler languageHandler;
    private final String savePath;
    private File saveFile;
    private Properties properties;

    // Maps
    private static HashMap<String, String> dataMap;
    private static HashMap<String, Font> fontMap;
    
    
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
            HashMap<String, String> langMap = languageHandler.getLangMap(this.getDataMapLang());
            new ErrorPane(false, new ArrayList<String>(Arrays.asList(langMap.get(Utility.majErr), langMap.get(Utility.wriFilErr), langMap.get(Utility.accept))));
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
            HashMap<String, String> langMap = languageHandler.getLangMap(this.getDataMapLang());
            new ErrorPane(false, new ArrayList<String>(Arrays.asList(langMap.get(Utility.majErr), langMap.get(Utility.reaFilErr), langMap.get(Utility.accept))));
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
        infoMap.put(Utility.langName, getLanguage());
        infoMap.put(Utility.modeName, Utility.lightMode);

        // ------- <Font> -------
        // Title
        infoMap.put(Utility.titleFamilyName, Utility.fontDefaultFamily);
        infoMap.put(Utility.titleStyleName, String.valueOf(Font.PLAIN));
        infoMap.put(Utility.titleSizeName, String.valueOf(20));

        // Subtitle
        infoMap.put(Utility.subtitleFamilyName, Utility.fontDefaultFamily);
        infoMap.put(Utility.subtitleStyleName, String.valueOf(Font.PLAIN));
        infoMap.put(Utility.subtitleSizeName, String.valueOf(20));

        // Selection
        infoMap.put(Utility.selectionFamilyName, Utility.fontDefaultFamily);
        infoMap.put(Utility.selectionStyleName, String.valueOf(Font.PLAIN));
        infoMap.put(Utility.selectionSizeName, String.valueOf(13));

        // Input
        infoMap.put(Utility.inputFamilyName, Utility.fontDefaultFamily);
        infoMap.put(Utility.inputStyleName, String.valueOf(Font.PLAIN));
        infoMap.put(Utility.inputSizeName, String.valueOf(18));
        // ------- </Font> -------

        // Save
        writeXML(infoMap);

        // ------- <Mappers> -------
        // Data
        dataMap.put(Utility.modeName, Utility.lightMode);
        dataMap.put(Utility.langName, infoMap.get(Utility.modeName));

        // Font
        fontMap.put(Utility.titleFontName, new Font(Utility.fontDefaultFamily, Font.PLAIN, 20));
        fontMap.put(Utility.subtitleFontName, new Font(Utility.fontDefaultFamily, Font.PLAIN, 20));
        fontMap.put(Utility.selectionFontName, new Font(Utility.fontDefaultFamily, Font.PLAIN, 13));
        fontMap.put(Utility.inputFontName, new Font(Utility.fontDefaultFamily, Font.PLAIN, 18));
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

    // Get lang in dataMap
    public String getDataMapLang() {
        return dataMap.get(Utility.langName);
    }

    // Initiate process
    public void setupSave() {

        if (!saveFile.exists()) {

            // Create File
            try {
                saveFile.createNewFile();
            } catch (Exception e) {
                HashMap<String, String> langMap = languageHandler.getLangMap(this.getDataMapLang());
                new ErrorPane(false, new ArrayList<String>(Arrays.asList(langMap.get(Utility.majErr), langMap.get(Utility.wriFilErr), langMap.get(Utility.accept))));
            }

            // Create default mappings
            createDefaultMappings();

        } else {

            try {

                HashMap<String, String> stored = readXML();

                dataMap = new HashMap<String, String>();
                fontMap = new HashMap<String, Font>();

                dataMap.put(Utility.modeName, stored.get(Utility.modeName));
                dataMap.put(Utility.langName, stored.get(Utility.langName));

                fontMap.put(Utility.titleFontName, new Font(stored.get(Utility.titleFamilyName), Integer.valueOf(stored.get(Utility.titleStyleName)), Integer.valueOf(stored.get(Utility.titleSizeName))));
                fontMap.put(Utility.subtitleFontName, new Font(stored.get(Utility.subtitleFamilyName), Integer.valueOf(stored.get(Utility.subtitleStyleName)), Integer.valueOf(stored.get(Utility.subtitleSizeName))));
                fontMap.put(Utility.selectionFontName, new Font(stored.get(Utility.selectionFamilyName), Integer.valueOf(stored.get(Utility.selectionStyleName)), Integer.valueOf(stored.get(Utility.selectionSizeName))));
                fontMap.put(Utility.inputFontName, new Font(stored.get(Utility.inputFamilyName), Integer.valueOf(stored.get(Utility.inputStyleName)), Integer.valueOf(stored.get(Utility.inputSizeName))));
                
            } catch (Exception e) {
                HashMap<String, String> langMap = languageHandler.getLangMap(this.getDataMapLang());
                new ErrorPane(false, new ArrayList<String>(Arrays.asList(langMap.get(Utility.majErr), langMap.get(Utility.reaFilErr), langMap.get(Utility.accept))));
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
        toBeSaved.put(Utility.langName, dataMap.get(Utility.langName));
        toBeSaved.put(Utility.modeName, dataMap.get(Utility.modeName));

        // ------- <FontMap transcription> -------
        // Title
        toBeSaved.put(Utility.titleFamilyName, fontMap.get(Utility.titleFontName).getFamily());
        toBeSaved.put(Utility.titleStyleName, String.valueOf(fontMap.get(Utility.titleFontName).getStyle()));
        toBeSaved.put(Utility.titleSizeName, String.valueOf(fontMap.get(Utility.titleFontName).getSize()));

        // Subtitle
        toBeSaved.put(Utility.subtitleFamilyName, fontMap.get(Utility.subtitleFontName).getFamily());
        toBeSaved.put(Utility.subtitleStyleName, String.valueOf(fontMap.get(Utility.subtitleFontName).getStyle()));
        toBeSaved.put(Utility.subtitleSizeName, String.valueOf(fontMap.get(Utility.subtitleFontName).getSize()));

        // Selection
        toBeSaved.put(Utility.selectionFamilyName, fontMap.get(Utility.selectionFontName).getFamily());
        toBeSaved.put(Utility.selectionStyleName, String.valueOf(fontMap.get(Utility.selectionFontName).getStyle()));
        toBeSaved.put(Utility.selectionSizeName, String.valueOf(fontMap.get(Utility.selectionFontName).getSize()));

        // Input
        toBeSaved.put(Utility.inputFamilyName, fontMap.get(Utility.inputFontName).getFamily());
        toBeSaved.put(Utility.inputStyleName, String.valueOf(fontMap.get(Utility.inputFontName).getStyle()));
        toBeSaved.put(Utility.inputSizeName, String.valueOf(fontMap.get(Utility.inputFontName).getSize()));
        // ------- </FontMap transcription> -------
        // ------- </Save> -------

        // Save
        writeXML(toBeSaved);

    }

}
