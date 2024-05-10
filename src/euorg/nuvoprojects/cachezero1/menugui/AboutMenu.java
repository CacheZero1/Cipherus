package euorg.nuvoprojects.cachezero1.menugui;

import java.awt.Component;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class AboutMenu extends JOptionPane {

    public AboutMenu(Component parent, ArrayList<String> stringList) {

        AboutMenu.showOptionDialog(
            parent, 
            stringList.get(0) + "\n" + stringList.get(1) + "\n\n" + stringList.get(2),
            stringList.get(3), 
            JOptionPane.OK_CANCEL_OPTION, 
            JOptionPane.INFORMATION_MESSAGE, 
            new ImageIcon(getClass().getResource("/images/icon_64px.png")), 
            new String[]{stringList.get(4)},
            0
        );

    }
    
}
