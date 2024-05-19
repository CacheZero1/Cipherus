package euorg.nuvoprojects.cachezero1.menugui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JViewport;
import javax.swing.plaf.basic.BasicScrollBarUI;

import euorg.nuvoprojects.cachezero1.Utility;
import euorg.nuvoprojects.cachezero1.gui.CustomFileChooser;

public class SaveTextMenu extends JOptionPane implements ActionListener {

    // Dark mode
    private static Boolean isDarkMode;

    // Lang map
    private static ArrayList<String> stringMap;

    // Components (positioning)
    private JPanel mainPanel;

    // Components (functional)
    private JComboBox<String> comboBox;
    private JTextArea textArea;
    private JButton saveButton;
    private JButton closeButton;

    // Text
    private List<String> parsedText;


    public SaveTextMenu(Component parent, Boolean darkMode, List<String> text, ArrayList<String> stringList) {

        stringMap = stringList;
        this.parsedText = text;
        isDarkMode = darkMode;

        switch (text.size()) {
            case 1:
                createMonoGUI();
                break;

            case 2:
                createDiGUI();
                break;
        
            default:
                break;
        }

        this.createDialog(parent, stringList.get(0)).setVisible(true);

    }

    private void createMonoGUI() {

        // Main panel
        mainPanel = new JPanel(new BorderLayout());

        // TextArea
        textArea = new JTextArea();
        textArea.setPreferredSize(new Dimension(150, 200));
        textArea.setText(parsedText.get(0));
        textArea.setEditable(false);
        textArea.setFocusable(false);

        // Buttons
        saveButton = new JButton(stringMap.get(1));
        saveButton.setFocusable(false);
        saveButton.addActionListener(this);

        closeButton = new JButton(stringMap.get(6));
        closeButton.setFocusable(false);
        closeButton.addActionListener(event -> {
            this.setValue(JOptionPane.CLOSED_OPTION);
        });

        // Apply theme
        applyTheme();

        // Add all
        mainPanel.add(textArea, BorderLayout.CENTER);
        mainPanel.add(saveButton, BorderLayout.SOUTH);

        this.setMessage(mainPanel);
        this.setOptions(new Object[]{closeButton});

        if (isDarkMode) {
            ((JButton) this.getOptions()[0]).getParent().setBackground(Utility.optionBackgroundDark);
        }

    };

    private void createDiGUI() {

        // Main panel
        mainPanel = new JPanel(new BorderLayout());

        // ComboBox
        comboBox = new JComboBox<String>(new String[]{stringMap.get(2), stringMap.get(3)});
        comboBox.addActionListener(this);

        // TextArea
        textArea = new JTextArea();
        textArea.setPreferredSize(new Dimension(150, 200));
        textArea.setText(parsedText.get(0));
        textArea.setEditable(false);
        textArea.setFocusable(false);

        // Buttons
        saveButton = new JButton(stringMap.get(1));
        saveButton.setFocusable(false);
        saveButton.addActionListener(this);

        closeButton = new JButton(stringMap.get(6));
        closeButton.setFocusable(false);
        closeButton.addActionListener(event -> {
            this.setValue(JOptionPane.CLOSED_OPTION);
        });

        // Apply theme
        applyTheme();

        // Add all
        mainPanel.add(comboBox, BorderLayout.NORTH);
        mainPanel.add(textArea, BorderLayout.CENTER);
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

            // Text areas
            textArea.setBackground(Utility.optionBackgroundDark);
            textArea.setCaretColor(Utility.textColourDarkmode);
            textArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));

            // Buttons
            saveButton.setBackground(Utility.buttonDark);
            saveButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));

            closeButton.setBackground(Utility.buttonDark);
            closeButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            // ------- </Set backgrounds> -------

            // ------- <Set text colour> -------
            // Text areas
            textArea.setForeground(Utility.textColourDarkmode);

            // Buttons
            saveButton.setForeground(Utility.textColourDarkmode);
            closeButton.setForeground(Utility.textColourDarkmode);
            // ------- </Set text colour> -------

            // ComboBox
            if (comboBox != null) {
                comboBox.setBackground(Utility.comboboxDark);
                comboBox.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                comboBox.setForeground(Utility.textColourDarkmode);
                comboBox.setFocusable(false);
            }

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

                PrintWriter writer = null;
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath() + ".txt");

                try {

                    writer = new PrintWriter(file);
                    writer.println(textArea.getText());
                    
                } catch (Exception writeException) {
                    
                    JOptionPane.showMessageDialog(null, stringMap.get(4), stringMap.get(5), JOptionPane.ERROR_MESSAGE);

                } finally {

                    writer.close();

                }

            }

        }

        if (e.getSource() == comboBox) {

            textArea.setEditable(true);

            if (comboBox.getSelectedItem().equals(stringMap.get(2))) {

                textArea.setText(parsedText.get(0));

            } else if (comboBox.getSelectedItem().equals(stringMap.get(3))) {

                textArea.setText(parsedText.get(1));
                
            }

        }

    };
    
}