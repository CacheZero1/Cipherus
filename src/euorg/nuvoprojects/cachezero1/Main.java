package euorg.nuvoprojects.cachezero1;

import java.awt.Font;
import java.util.HashMap;
import java.util.Locale;

import euorg.nuvoprojects.cachezero1.gui.MainWindow;
import euorg.nuvoprojects.cachezero1.menugui.FontMenu;

public class Main {

    private static HashMap<String, Font> fontmap = new HashMap<String, Font>();
    private static HashMap<String, String> dataMap = new HashMap<String, String>();
    private static SaveHandler handler = new SaveHandler(".");

    public static void main(String[] args) {

        /*
         * Date of creation: 22.02.2024
         * Developers: CacheZero1
         * License: CC-BY
        */

        final String version = "v0.4"; // TODO: update version number


        setupSave();


        MainWindow mainWindow = new MainWindow(version, fontmap, Boolean.parseBoolean(dataMap.get("mode")));
        mainWindow.setVisible(true);

        

        //FontMenu fontMenu = new FontMenu(null);

    }


    private static void setupSave() {

        if (!handler.saveExists()) {

            final Font defaultFont = new Font(null, Font.PLAIN, 1);

            // Create File
            handler.createSaveFile();

            // Create mappings
            HashMap<String, String> infoMap = new HashMap<String, String>();

            // Language and brightness mode
            setLanguage(infoMap);
            infoMap.put("mode", "False");

            // ------- Font -------
            // Title
            infoMap.put("titleFamily", defaultFont.getFamily());
            infoMap.put("titleStyle", String.valueOf(Font.PLAIN));
            infoMap.put("titleSize", String.valueOf(20));

            // Subtitle
            infoMap.put("subtitleFamily", defaultFont.getFamily());
            infoMap.put("subtitleStyle", String.valueOf(Font.PLAIN));
            infoMap.put("subtitleSize", String.valueOf(20));

            // Selection
            infoMap.put("selectionFamily", defaultFont.getFamily());
            infoMap.put("selectionStyle", String.valueOf(Font.PLAIN));
            infoMap.put("selectionSize", String.valueOf(13));

            // Input
            infoMap.put("inputFamily", defaultFont.getFamily());
            infoMap.put("inputStyle", String.valueOf(Font.PLAIN));
            infoMap.put("inputSize", String.valueOf(18));

            handler.writeXML(infoMap);

            // Set map
            dataMap.put("mode", "False");
            dataMap.put("lang", infoMap.get("lang"));

            fontmap.put("title", new Font(defaultFont.getFamily(), Font.PLAIN, 20));
            fontmap.put("subtitle", new Font(defaultFont.getFamily(), Font.PLAIN, 20));
            fontmap.put("selection", new Font(defaultFont.getFamily(), Font.PLAIN, 13));
            fontmap.put("input", new Font(defaultFont.getFamily(), Font.PLAIN, 18));

        } else {

            try {

                HashMap<String, String> stored = handler.readXML();

                dataMap.put("mode", stored.get("mode"));
                dataMap.put("lang", stored.get("lang"));

                fontmap.put("title", new Font(stored.get("titleFamily"), Integer.valueOf(stored.get("titleStyle")), Integer.valueOf(stored.get("titleSize"))));
                fontmap.put("subtitle", new Font(stored.get("subtitleFamily"), Integer.valueOf(stored.get("subtitleStyle")), Integer.valueOf(stored.get("subtitleSize"))));
                fontmap.put("selection", new Font(stored.get("selectionFamily"), Integer.valueOf(stored.get("selectionStyle")), Integer.valueOf(stored.get("selectionSize"))));
                fontmap.put("input", new Font(stored.get("inputFamily"), Integer.valueOf(stored.get("inputStyle")), Integer.valueOf(stored.get("inputSize"))));
                
            } catch (Exception e) {
                e.printStackTrace(); //TODO: add errorpane
            }

        }

    }

    private static void setLanguage(HashMap<String, String> map) {
        try {
            String langString = Locale.getDefault().getLanguage();

            if (langString.isBlank()) {
                map.put("lang", "en");
            } else {
                map.put("lang", langString);
            }
            
        } catch (Exception e) {
            map.put("lang", "en");
        }
    }

}