package euorg.nuvoprojects.cachezero1;

import java.awt.Font;
import euorg.nuvoprojects.cachezero1.gui.MainWindow;

public class Main {
    public static void main(String[] args) {

        /*
         * Date of creation: 22.02.2024
         * Developers: CacheZero1
         * License: CC-BY
        */

        String version = "v0.3";


        MainWindow mainWindow = new MainWindow(version, new Font(null, Font.PLAIN, 20), false);
        mainWindow.setVisible(true);
        
    }
}
