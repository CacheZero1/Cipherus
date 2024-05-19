package euorg.nuvoprojects.cachezero1.menugui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JViewport;
import javax.swing.plaf.basic.BasicScrollBarUI;

import euorg.nuvoprojects.cachezero1.Utility;
import euorg.nuvoprojects.cachezero1.gui.CustomFileChooser;



public class SaveImageMenu extends JOptionPane implements ActionListener {

    // Dark mode
    private static Boolean isDarkMode;

    // Lang map
    private static ArrayList<String> stringMap;

    // Components (positioning)
    private JPanel mainPanel;

    // Components (functional)
    private JLabel imagePreviewLabel;
    private JButton saveButton;
    private JButton closeButton;

    // Text
    private BufferedImage parsedImage;


    public SaveImageMenu(Component parent, Boolean darkMode, BufferedImage enImage, ArrayList<String> stringList) {

        stringMap = stringList;
        this.parsedImage = enImage;
        isDarkMode = darkMode;

        createGUI();

        this.createDialog(parent, stringList.get(0)).setVisible(true);

    }

    private void createGUI() {

        // Main panel
        mainPanel = new JPanel(new BorderLayout());

        // ComboBox
        imagePreviewLabel = new JLabel(new ImageIcon(parsedImage));
        imagePreviewLabel.setFocusable(true);

        // Button
        saveButton = new JButton(stringMap.get(1));
        saveButton.setFocusable(false);
        saveButton.addActionListener(this);

        closeButton = new JButton(stringMap.get(4));
        closeButton.setFocusable(false);
        closeButton.addActionListener(event -> {
            this.setValue(JOptionPane.CLOSED_OPTION);
        });

        // Apply theme
        applyTheme();

        // Add all
        mainPanel.add(imagePreviewLabel, BorderLayout.CENTER);
        mainPanel.add(saveButton, BorderLayout.SOUTH);

        this.setMessage(mainPanel);
        this.setOptions(new Object[]{closeButton});

        if (isDarkMode) {
            ((JButton) this.getOptions()[0]).getParent().setBackground(Utility.optionBackgroundDark);
        }

    }

    private void applyTheme() {

        if (isDarkMode) {
            // ------- <Set backgrounds> -------
            // General & Panels
            mainPanel.setOpaque(true);
            this.setBackground(Utility.optionBackgroundDark);
            mainPanel.setBackground(Utility.optionBackgroundDark);

            // Buttons
            saveButton.setBackground(Utility.buttonDark);
            saveButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));

            closeButton.setBackground(Utility.buttonDark);
            closeButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            // ------- </Set backgrounds> -------

            // ------- <Set text colour> -------
            // Buttons
            saveButton.setForeground(Utility.textColourDarkmode);
            closeButton.setForeground(Utility.textColourDarkmode);
            // ------- </Set text colour> -------

        }

    }


    @Override @SuppressWarnings("unchecked")
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == saveButton) {

            JFileChooser fileChooser = new CustomFileChooser();

            if (isDarkMode) {
                // General
                fileChooser.getComponent(0).setBackground(Utility.optionBackgroundDark);
                fileChooser.getComponent(1).setBackground(Utility.optionBackgroundDark);
                fileChooser.getComponent(2).setBackground(Utility.optionBackgroundDark);
                fileChooser.getComponent(3).setBackground(Utility.optionBackgroundDark);

                ((JPanel) fileChooser.getComponent(0)).getComponent(0).setBackground(Utility.optionBackgroundDark);
                ((JPanel) fileChooser.getComponent(2)).getComponent(0).setBackground(Utility.optionBackgroundDark);
                ((JPanel) fileChooser.getComponent(3)).getComponent(0).setBackground(Utility.optionBackgroundDark);
                ((JPanel) fileChooser.getComponent(3)).getComponent(2).setBackground(Utility.optionBackgroundDark);
                ((JPanel) fileChooser.getComponent(3)).getComponent(3).setBackground(Utility.optionBackgroundDark);

                // Top Bar
                ((JLabel)((JPanel)fileChooser.getComponent(0)).getComponent(1)).setForeground(Utility.textColourDarkmode); // JLabel: Save in

                ((JComboBox<Object>)(((JPanel)fileChooser.getComponent(0)).getComponent(2))).setBackground(Utility.comboboxDark); // ComboBox: fileDir
                ((JComboBox<Object>)(((JPanel)fileChooser.getComponent(0)).getComponent(2))).setForeground(Utility.textColourDarkmode); // ComboBox: fileDir

                ((JPanel)((JPanel)fileChooser.getComponent(0)).getComponent(0)).getComponent(0).setBackground(Utility.buttonDark); // JButton: Up one dir
                ((JPanel)((JPanel)fileChooser.getComponent(0)).getComponent(0)).getComponent(2).setBackground(Utility.buttonDark); // JButton: Home
                ((JPanel)((JPanel)fileChooser.getComponent(0)).getComponent(0)).getComponent(4).setBackground(Utility.buttonDark); // JButton: create new folder
                ((JPanel)((JPanel)fileChooser.getComponent(0)).getComponent(0)).getComponent(6).setBackground(Utility.buttonDark); // JToggle: List
                ((JPanel)((JPanel)fileChooser.getComponent(0)).getComponent(0)).getComponent(7).setBackground(Utility.buttonDark); // JToggle: Details

                ((JPanel)((JPanel)fileChooser.getComponent(0)).getComponent(0)).getComponent(7).setEnabled(false);

                // Middle Bar
                ((JViewport)((JScrollPane)((JPanel)((JPanel)fileChooser.getComponent(2)).getComponent(0)).getComponent(0)).getComponent(0)).getComponent(0).setForeground(Utility.textColourDarkmode); // File panel
                ((JViewport)((JScrollPane)((JPanel)((JPanel)fileChooser.getComponent(2)).getComponent(0)).getComponent(0)).getComponent(0)).getComponent(0).setBackground(Utility.optionBackgroundDark); // File panel

                ((JScrollPane)((JPanel)((JPanel)fileChooser.getComponent(2)).getComponent(0)).getComponent(0)).getHorizontalScrollBar().setUI(new BasicScrollBarUI() {
                    @Override 
                    protected void configureScrollBarColors(){
                        this.thumbColor = Utility.sliderKnobDark;
                        this.trackColor = Utility.sliderBarDark;
                    }
                    @Override
                    protected JButton createDecreaseButton(int orientation) {
                        JButton button = super.createDecreaseButton(orientation);
                        button.setBackground(Utility.buttonDark);
                        button.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
                        button.setContentAreaFilled(false);
                        return button;
                    }
                    @Override
                    protected JButton createIncreaseButton(int orientation) {
                        JButton button = super.createIncreaseButton(orientation);
                        button.setBackground(Utility.buttonDark);
                        button.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
                        button.setContentAreaFilled(false);
                        return button;
                    }
                }); // ScrollPane for files
                    
                // Bottom Bar
                ((JPanel)((JPanel)fileChooser.getComponent(3)).getComponent(0)).getComponent(0).setForeground(Utility.textColourDarkmode); // File name label
                ((JPanel)((JPanel)fileChooser.getComponent(3)).getComponent(0)).getComponent(0).setBackground(Utility.optionBackgroundDark); // File name label

                ((JPanel)((JPanel)fileChooser.getComponent(3)).getComponent(0)).getComponent(1).setForeground(Utility.textColourDarkmode); // File name textfield
                ((JPanel)((JPanel)fileChooser.getComponent(3)).getComponent(0)).getComponent(1).setBackground(Utility.optionBackgroundDark); // File name textfield
                ((JTextField)((JPanel)((JPanel)fileChooser.getComponent(3)).getComponent(0)).getComponent(1)).setCaretColor(Utility.textColourDarkmode); // File name textfield
                ((JTextField)((JPanel)((JPanel)fileChooser.getComponent(3)).getComponent(0)).getComponent(1)).setBorder(BorderFactory.createLineBorder(Color.BLACK)); // File name textfield

                ((JPanel)((JPanel)fileChooser.getComponent(3)).getComponent(2)).getComponent(0).setForeground(Utility.textColourDarkmode); // JLabel: Files of Type

                ((JPanel)((JPanel)fileChooser.getComponent(3)).getComponent(2)).getComponent(1).setForeground(Utility.textColourDarkmode); // JComboBox: Files of Type
                ((JPanel)((JPanel)fileChooser.getComponent(3)).getComponent(2)).getComponent(1).setBackground(Utility.comboboxDark); // JComboBox: Files of Type
                ((JPanel)((JPanel)fileChooser.getComponent(3)).getComponent(2)).getComponent(1).setFocusable(false); // JComboBox: Files of Type

                ((JPanel)((JPanel)fileChooser.getComponent(3)).getComponent(3)).getComponent(0).setBackground(Utility.buttonDark); // JButton: Save
                ((JPanel)((JPanel)fileChooser.getComponent(3)).getComponent(3)).getComponent(0).setForeground(Utility.textColourDarkmode); // JButton: Save
                ((JPanel)((JPanel)fileChooser.getComponent(3)).getComponent(3)).getComponent(0).setFocusable(false); // JButton: Save

                ((JPanel)((JPanel)fileChooser.getComponent(3)).getComponent(3)).getComponent(1).setBackground(Utility.buttonDark); // JButton: Cancel
                ((JPanel)((JPanel)fileChooser.getComponent(3)).getComponent(3)).getComponent(1).setForeground(Utility.textColourDarkmode); // JButton: Cancel
                ((JPanel)((JPanel)fileChooser.getComponent(3)).getComponent(3)).getComponent(1).setFocusable(false); // JButton: Cancel
            }


            fileChooser.setCurrentDirectory(new File("."));

            if (fileChooser.showSaveDialog(mainPanel) == JFileChooser.APPROVE_OPTION) {

                File file = new File(fileChooser.getSelectedFile().getAbsolutePath() + ".png");

                try {

                    ImageIO.write(parsedImage, "png", file);
                    
                } catch (Exception writeException) {
                    
                    JOptionPane.showMessageDialog(null, stringMap.get(2), stringMap.get(3), JOptionPane.ERROR_MESSAGE);

                }
            }
        }
    };
    
}
