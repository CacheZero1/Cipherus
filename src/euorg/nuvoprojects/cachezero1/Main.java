package euorg.nuvoprojects.cachezero1;

import euorg.nuvoprojects.cachezero1.gui.MainWindow;
import euorg.nuvoprojects.cachezero1.literates.LanguageHandler;
import euorg.nuvoprojects.cachezero1.literates.SaveHandler;

public class Main {

    private static LanguageHandler langHandler = new LanguageHandler();
    private static SaveHandler handler = new SaveHandler(".", langHandler);

    public static void main(String[] args) {

        /*
         * Date of creation: 22.02.2024
         * Developers: CacheZero1
         * License: CC-BY
        */

        final String version = "v0.7";


        // Create save
        handler.setupSave();

        // Main window
        MainWindow mainWindow = new MainWindow(version, handler, langHandler);
        mainWindow.setVisible(true);       

    }    

}