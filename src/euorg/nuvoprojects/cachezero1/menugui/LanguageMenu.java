package euorg.nuvoprojects.cachezero1.menugui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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

    // Buttons
    private JButton okButton;
    private JButton cancelButton;

    public LanguageMenu(Component parent, Boolean isDarkMode, SaveHandler saveHandler, ArrayList<String> stringList) {

        // Lang
        final String language = saveHandler.getDataMapLang();

        // Setup Panel
        setupPanel(language, stringList.get(1), stringList.get(2), isDarkMode);

        // Show popup
        this.createDialog(parent, stringList.get(0)).setVisible(true);

        // Apply new fonts
        if ((int) this.getValue() == JOptionPane.OK_OPTION) {

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

    private void setupPanel(String langString, String okString, String cancelString, Boolean isDarkMode) {

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

        // Buttons
        okButton = new JButton(okString);
        okButton.setFocusable(false);
        okButton.addActionListener(event -> {
            this.setValue(JOptionPane.OK_OPTION);
        });

        cancelButton = new JButton(cancelString);
        cancelButton.setFocusable(false);
        cancelButton.addActionListener(event -> {
            this.setValue(JOptionPane.CANCEL_OPTION);
        });

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

        // Dark mode
        applyTheme(isDarkMode);

        // Adding
        buttonGroup.add(englishRadioButton);
        buttonGroup.add(frenchRadioButton);
        buttonGroup.add(germanRadioButton);

        mainPanel.add(englishRadioButton);
        mainPanel.add(frenchRadioButton);
        mainPanel.add(germanRadioButton);

        this.setMessage(mainPanel);
        this.setOptions(new Object[]{okButton, cancelButton});

        if (isDarkMode) {
            ((JButton) this.getOptions()[0]).getParent().setBackground(Utility.optionBackgroundDark);
        }

    }

    // Dark mode
    public void applyTheme(Boolean isDarkMode) {

        if (isDarkMode) {
            // ------- <Set backgrounds> -------
            // General & Panels
            mainPanel.setBackground(Utility.optionBackgroundDark);
            this.setBackground(Utility.optionBackgroundDark);
            
            // Radio Buttons
            englishRadioButton.setBackground(Utility.optionBackgroundDark);
            frenchRadioButton.setBackground(Utility.optionBackgroundDark);
            germanRadioButton.setBackground(Utility.optionBackgroundDark);

            // Buttons
            okButton.setBackground(Utility.buttonDark);
            okButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));

            cancelButton.setBackground(Utility.buttonDark);
            cancelButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            // ------- </Set backgrounds> -------

            // ------- <Set text colour> -------
            // Buttons
            okButton.setForeground(Utility.textColourDarkmode);
            cancelButton.setForeground(Utility.textColourDarkmode);
            // ------- </Set text colour> -------
        }

    }
    
}
