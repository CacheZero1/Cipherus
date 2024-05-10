package euorg.nuvoprojects.cachezero1.menugui;

import java.awt.BorderLayout;
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

public class LanguageMenu extends JOptionPane {

    // Main panel
    private JPanel mainPanel = new JPanel();

    // RadioButtons
    JRadioButton englishRadioButton;
    JRadioButton frenchRadioButton;
    JRadioButton germanRadioButton;

    ButtonGroup buttonGroup = new ButtonGroup();

    public LanguageMenu(Component parent, SaveHandler saveHandler, String title) {

        // Lang
        final String language = saveHandler.getDataMapLang();

        // Setup Panel
        setupPanel(language);

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

            newLangMap.put(Utility.modeName, saveHandler.getDataMap().get(Utility.modeName));

            if (frenchRadioButton.isSelected()) {
                newLangMap.put(Utility.langName, "fr");
            } else if (germanRadioButton.isSelected()) {
                newLangMap.put(Utility.langName, "de");
            } else {
                newLangMap.put(Utility.langName, "en");
            }
            
            saveHandler.applyChanges(saveHandler.getFontMap(), newLangMap);

        }

    }

    private void setupPanel(String langString) {

        // Main panel
        mainPanel.setLayout(new GridLayout(1, 3));
        mainPanel.setPreferredSize(new Dimension(300, 300));

        // RadioButtons
        englishRadioButton = new JRadioButton(new ImageIcon(this.getClass().getResource("/images/british.png")));
        englishRadioButton.setSelectedIcon(new ImageIcon(this.getClass().getResource("/images/british_s.png")));
        frenchRadioButton = new JRadioButton(new ImageIcon(this.getClass().getResource("/images/french.png")));
        frenchRadioButton.setSelectedIcon(new ImageIcon(this.getClass().getResource("/images/french_s.png")));
        germanRadioButton = new JRadioButton(new ImageIcon(this.getClass().getResource("/images/german.png")));
        germanRadioButton.setSelectedIcon(new ImageIcon(this.getClass().getResource("/images/german_s.png")));

        switch (langString) {
            case "fr":
                frenchRadioButton.setSelected(true);
                break;

            case "de":
                germanRadioButton.setSelected(true);
                break;
        
            default:
                englishRadioButton.setSelected(true);
                break;
        }

        // Adding
        buttonGroup.add(englishRadioButton);
        buttonGroup.add(frenchRadioButton);
        buttonGroup.add(germanRadioButton);

        mainPanel.add(englishRadioButton, BorderLayout.WEST);
        mainPanel.add(frenchRadioButton, BorderLayout.CENTER);
        mainPanel.add(germanRadioButton, BorderLayout.EAST);

    }
    
}
