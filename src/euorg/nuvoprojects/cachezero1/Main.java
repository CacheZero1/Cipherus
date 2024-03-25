package euorg.nuvoprojects.cachezero1;

import java.awt.Font;
import java.util.HashMap;
import java.util.Locale;

import euorg.nuvoprojects.cachezero1.gui.MainWindow;
import euorg.nuvoprojects.cachezero1.menugui.FontMenu;

public class Main {

    private static SaveHandler handler = new SaveHandler(".");

    public static void main(String[] args) {

        /*
         * Date of creation: 22.02.2024
         * Developers: CacheZero1
         * License: CC-BY
        */

        String version = "v0.4"; // TODO: update version number


        //MainWindow mainWindow = new MainWindow(version, new Font(null, Font.PLAIN, 20), false);
        //mainWindow.setVisible(true);

        //setupSave();

        FontMenu fontMenu = new FontMenu(null);

    }


    private static void setupSave() {

        if (!handler.saveExists()) {

            // Create File
            handler.createSaveFile();

            // Create mappings
            HashMap<String, String> infoMap = new HashMap<String, String>();

            setLanguage(infoMap);
            infoMap.put("mode", "0");

            handler.writeXML(infoMap);

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
