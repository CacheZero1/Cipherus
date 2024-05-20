package euorg.nuvoprojects.cachezero1.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import euorg.nuvoprojects.cachezero1.Utility;

public class ErrorPane extends JOptionPane {

    // Components
    private JButton okButton;
    private JLabel label;

    public ErrorPane(Boolean isDarkmode, ArrayList<String> stringList) {

        // Create interface
        setup(isDarkmode, stringList);

        JDialog dialog = this.createDialog(null, stringList.get(0));
        if (isDarkmode) {
            recursive(dialog);
            dialog.getContentPane().setBackground(Utility.optionBackgroundDark);
        }
        dialog.setVisible(true);

    }


    // Create GUI
    private void setup(Boolean isDarkMode, ArrayList<String> stringMap) {

        // Button
        okButton = new JButton(stringMap.get(2));
        okButton.setFocusable(false);
        okButton.addActionListener(event -> {
            this.setValue(JOptionPane.OK_OPTION);
        });

        // Message
        label = new JLabel(stringMap.get(1));

        // Apply theme
        applyTheme(isDarkMode);

        // Add all
        this.setMessageType(JOptionPane.ERROR_MESSAGE);
        this.setOptions(new Object[]{okButton});

        if (isDarkMode) {
            label.setForeground(Utility.textColourDarkmode);
            ((JButton) this.getOptions()[0]).getParent().setBackground(Utility.optionBackgroundDark);
        }

        this.setMessage(label);

    }

    // Dark mode
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
