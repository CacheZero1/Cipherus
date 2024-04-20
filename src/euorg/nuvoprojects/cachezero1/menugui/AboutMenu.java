package euorg.nuvoprojects.cachezero1.menugui;

import java.awt.Component;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class AboutMenu extends JOptionPane {

    private final String aboMenDes1 = "aboMenDes1";
    private final String aboMenDes2 = "aboMenDes2";
    private final String aboMenDes3 = "aboMenDes3";
    private final String aboMenTit = "aboMenTit";
    private final String aboMenOk = "aboMenOk";

    public AboutMenu(Component parent, HashMap<String, String> langMap) {

        AboutMenu.showOptionDialog(
            parent, 
            langMap.get(aboMenDes1) + "\n" + langMap.get(aboMenDes2) + "\n\n" + langMap.get(aboMenDes3),
            langMap.get(aboMenTit), 
            JOptionPane.OK_CANCEL_OPTION, 
            JOptionPane.INFORMATION_MESSAGE, 
            new ImageIcon(getClass().getResource("/images/icon_64px.png")), 
            new String[]{langMap.get(aboMenOk)},
            0
        );

    }
    
}
