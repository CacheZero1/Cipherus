package euorg.nuvoprojects.cachezero1.menugui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.HashMap;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import euorg.nuvoprojects.cachezero1.Utility;
import euorg.nuvoprojects.cachezero1.literates.SaveHandler;

public class ColourMenu {

    // Main panel
    private JPanel mainPanel = new JPanel();

    // RadioButtons
    JRadioButton lightModeRadioButton;
    JRadioButton darkModeRadioButton;

    ButtonGroup buttonGroup = new ButtonGroup();

    public ColourMenu(Component parent, SaveHandler saveHandler, String title, String isDarkMode) {

        // Setup Panel
        setupPanel(isDarkMode);

        // Show popup
        int chosenOption = LanguageMenu.showConfirmDialog(
            parent, 
            mainPanel, 
            title, 
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.PLAIN_MESSAGE
        );

        // Apply new fonts
        if (chosenOption == JOptionPane.OK_OPTION) {

            HashMap<String, String> newLangMap = new HashMap<String, String>();

            newLangMap.put(Utility.langName, saveHandler.getDataMapLang());

            if (darkModeRadioButton.isSelected()) {
                newLangMap.put(Utility.modeName, Utility.darkMode);
            } else {
                newLangMap.put(Utility.modeName, Utility.lightMode);
            }
            
            saveHandler.applyChanges(saveHandler.getFontMap(), newLangMap);

        }

    }

    private void setupPanel(String isDarkMode) {

        // Main panel
        mainPanel.setLayout(new GridLayout(1, 2));
        mainPanel.setPreferredSize(new Dimension(250, 170));

        // RadioButtons
        darkModeRadioButton = new JRadioButton();
        darkModeRadioButton.setHorizontalAlignment(JRadioButton.CENTER);
        darkModeRadioButton.setFocusPainted(false);
        lightModeRadioButton = new JRadioButton();
        lightModeRadioButton.setHorizontalAlignment(JRadioButton.CENTER);
        lightModeRadioButton.setFocusPainted(false);

        switch (isDarkMode) {
            case Utility.darkMode:
                darkModeRadioButton.setIcon(new ImageIcon(this.getClass().getResource("/images/moon_w.png")));
                darkModeRadioButton.setSelectedIcon(new ImageIcon(this.getClass().getResource("/images/moon_s.png")));
                darkModeRadioButton.setSelected(true);
                lightModeRadioButton.setIcon(new ImageIcon(this.getClass().getResource("/images/sun_w.png")));
                lightModeRadioButton.setSelectedIcon(new ImageIcon(this.getClass().getResource("/images/sun_s.png")));
                break;
        
            default:
                lightModeRadioButton.setIcon(new ImageIcon(this.getClass().getResource("/images/sun_b.png")));
                lightModeRadioButton.setSelectedIcon(new ImageIcon(this.getClass().getResource("/images/sun_s.png")));
                lightModeRadioButton.setSelected(true);
                darkModeRadioButton.setIcon(new ImageIcon(this.getClass().getResource("/images/moon_b.png")));
                darkModeRadioButton.setSelectedIcon(new ImageIcon(this.getClass().getResource("/images/moon_s.png")));
                break;
        }

        // Adding
        buttonGroup.add(lightModeRadioButton);
        buttonGroup.add(darkModeRadioButton);

        mainPanel.add(lightModeRadioButton);
        mainPanel.add(darkModeRadioButton);

    }
    
}
