package euorg.nuvoprojects.cachezero1;

import euorg.nuvoprojects.cachezero1.gui.MainWindow;

public class Main {

    private static SaveHandler handler = new SaveHandler(".");

    public static void main(String[] args) {

        /*
         * Date of creation: 22.02.2024
         * Developers: CacheZero1
         * License: CC-BY
        */

        final String version = "v0.5";


        // Create save
        handler.setupSave();

        // Main window
        MainWindow mainWindow = new MainWindow(version, handler);
        mainWindow.setVisible(true);

    }    

}