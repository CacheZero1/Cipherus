package euorg.nuvoprojects.cachezero1.menugui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import euorg.nuvoprojects.cachezero1.Utility;

public class AboutMenu extends JOptionPane {

    // Components
    private JButton okButton;

    public AboutMenu(Component parent, Boolean darkMode, ArrayList<String> stringList) {
        

        createGUI(stringList, new ImageIcon(getClass().getResource("/images/icon_64px.png")), darkMode);

        JDialog dialog = this.createDialog(parent, stringList.get(3));
        if (darkMode) {
            recursive(dialog);
            dialog.getContentPane().setBackground(Utility.optionBackgroundDark);
        }
        dialog.setVisible(true);

    }


    private void createGUI(ArrayList<String> stringList, ImageIcon icon, Boolean isDarkMode) {

        // Button
        okButton = new JButton(stringList.get(4));
        okButton.setFocusable(false);
        okButton.addActionListener(event -> {
            this.setValue(JOptionPane.OK_OPTION);
        });

        JLabel label = new JLabel("<html>" + stringList.get(0) + "<br/>" + stringList.get(1) + "<br/><br/>" + stringList.get(2) + "</html>");
        

        // Apply theme
        applyTheme(isDarkMode);

        // Add all
        this.setIcon(icon);
        this.setOptions(new Object[]{okButton});

        if (isDarkMode) {
            label.setForeground(Utility.textColourDarkmode);
            ((JButton) this.getOptions()[0]).getParent().setBackground(Utility.optionBackgroundDark);
        }

        this.setMessage(label);

    }

    private void applyTheme(Boolean isDarkMode) {

        if (isDarkMode) {
            // ------- <Set backgrounds> -------
            // General & Panels
            this.setBackground(Utility.optionBackgroundDark);
            this.setForeground(Utility.textColourDarkmode);

            // Buttons
            okButton.setBackground(Utility.buttonDark);
            okButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            // ------- </Set backgrounds> -------

            // ------- <Set text colour> -------
            // Buttons
            okButton.setForeground(Utility.textColourDarkmode);
            // ------- </Set text colour> -------
        }

    }

    private static void recursive(Container c) {
        Component[] containerList = c.getComponents();
        for(int i = 0; i < containerList.length; i++)
        {
            if(containerList[i] instanceof JPanel)
                ((JPanel)containerList[i]).setBackground(Utility.optionBackgroundDark);
                
            recursive((Container)containerList[i]);
        }
    }
    
}
