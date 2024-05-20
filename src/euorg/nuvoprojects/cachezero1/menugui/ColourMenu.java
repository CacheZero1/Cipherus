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

public class ColourMenu extends JOptionPane {

    // Main panel
    private JPanel mainPanel = new JPanel();

    // RadioButtons
    JRadioButton lightModeRadioButton;
    JRadioButton darkModeRadioButton;

    ButtonGroup buttonGroup = new ButtonGroup();

    // Buttons
    private JButton okButton;
    private JButton cancelButton;

    public ColourMenu(Component parent, Boolean isDarkMode, SaveHandler saveHandler, ArrayList<String> stringList) {

        // Setup Panel
        setupPanel(isDarkMode, stringList);

        // Show popup
        this.createDialog(parent, stringList.get(0)).setVisible(true);

        // Apply new fonts
        if ((int) this.getValue() == JOptionPane.OK_OPTION) {

            HashMap<String, String> newLangMap = new HashMap<String, String>();

            newLangMap.put(Utility.langName, saveHandler.getDataMapLang());

            if (darkModeRadioButton.isSelected()) {
                newLangMap.put(Utility.modeName, Utility.darkMode);
            } else {
                newLangMap.put(Utility.modeName, Utility.lightMode);
            }
            
            saveHandler.applyChanges(saveHandler.getFontMap(), newLangMap);

        } // TODO: JOPtion pane restart information message

    }

    private void setupPanel(Boolean isDarkMode, ArrayList<String> stringMap) {

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
        lightModeRadioButton.setEnabled(true);

        // Buttons
        okButton = new JButton(stringMap.get(1));
        okButton.setFocusable(false);
        okButton.addActionListener(event -> {
            this.setValue(JOptionPane.OK_OPTION);
        });

        cancelButton = new JButton(stringMap.get(2));
        cancelButton.setFocusable(false);
        cancelButton.addActionListener(event -> {
            this.setValue(JOptionPane.CANCEL_OPTION);
        });

        // RadioButtons
        lightModeRadioButton.setIcon(new ImageIcon(this.getClass().getResource("/images/sun_b.png")));
        lightModeRadioButton.setSelectedIcon(new ImageIcon(this.getClass().getResource("/images/sun_s.png")));

        darkModeRadioButton.setIcon(new ImageIcon(this.getClass().getResource("/images/moon_b.png")));
        darkModeRadioButton.setSelectedIcon(new ImageIcon(this.getClass().getResource("/images/moon_s.png")));

        buttonGroup.add(lightModeRadioButton);
        buttonGroup.add(darkModeRadioButton);

        lightModeRadioButton.setSelected(true);

        // Dark mode
        applyTheme(isDarkMode);

        // Adding
        

        mainPanel.add(lightModeRadioButton);
        mainPanel.add(darkModeRadioButton);

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
            

            // Buttons
            lightModeRadioButton.setIcon(new ImageIcon(this.getClass().getResource("/images/sun_w.png")));
            lightModeRadioButton.setSelectedIcon(new ImageIcon(this.getClass().getResource("/images/sun_s.png")));
            lightModeRadioButton.setBackground(Utility.optionBackgroundDark);

            darkModeRadioButton.setIcon(new ImageIcon(this.getClass().getResource("/images/moon_w.png")));
            darkModeRadioButton.setSelectedIcon(new ImageIcon(this.getClass().getResource("/images/moon_s.png")));
            darkModeRadioButton.setSelected(true);
            darkModeRadioButton.setBackground(Utility.optionBackgroundDark);

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
