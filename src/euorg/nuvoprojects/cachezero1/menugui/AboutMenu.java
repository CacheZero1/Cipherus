package euorg.nuvoprojects.cachezero1.menugui;

import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class AboutMenu extends JOptionPane {

    public AboutMenu(Component parent) {

        AboutMenu.showOptionDialog(
            parent, 
            "Encrypt and decrypt messages, be it in Text-format, or in Image-format.\n" +
            "All within a GUI, made to (hopefully) impress.\n" +
            "\n" +
            "Cipherus © 2024 by CacheZero1 is licensed under Attribution 4.0 International", 
            "About Cipherus", 
            JOptionPane.OK_CANCEL_OPTION, 
            JOptionPane.INFORMATION_MESSAGE, 
            new ImageIcon(getClass().getResource("/images/icon_64px.png")), 
            new String[]{"Ok"}, 
            0
        );

    }
    
}
