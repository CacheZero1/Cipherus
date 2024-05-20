package euorg.nuvoprojects.cachezero1.menugui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import euorg.nuvoprojects.cachezero1.Utility;
import euorg.nuvoprojects.cachezero1.literates.SaveHandler;

public class FontMenu extends JOptionPane implements ActionListener, ChangeListener {

    // Globals
    ArrayList<String> stringMap;
    private Font globalFont = new Font(null, Font.PLAIN, 20);
    String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
    String[] styles;

    private Font titleFont;
    private Font subtitleFont;
    private Font selectionFont;
    private Font inputFont;

    // Main panel
    private JPanel mainPanel;

    // Components (positioning & decoration)
    private JPanel panel00 = new JPanel();
    private JPanel familyPanel = new JPanel();
    private JPanel stylePanel = new JPanel();
    private JPanel sizePanel = new JPanel();

    private JPanel titlePanel = new JPanel();
    private JPanel panel11 = new JPanel();
    private JPanel panel21 = new JPanel();
    private JPanel panel31 = new JPanel();

    private JPanel subtitlePanel = new JPanel();
    private JPanel panel12 = new JPanel();
    private JPanel panel22 = new JPanel();
    private JPanel panel32 = new JPanel();

    private JPanel selectionPanel = new JPanel();
    private JPanel panel13 = new JPanel();
    private JPanel panel23 = new JPanel();
    private JPanel panel33 = new JPanel();

    private JPanel inputPanel = new JPanel();
    private JPanel panel14 = new JPanel();
    private JPanel panel24 = new JPanel();
    private JPanel panel34 = new JPanel();

    // ------- <Functional components> -------
    // Buttons
    private JButton okButton;
    private JButton cancelButton;

    // Labels
    JLabel familyLabel;
    JLabel styleLabel;
    JLabel sizeLabel;

    JLabel titleLabel;
    JLabel subtitleLabel;
    JLabel selectionLabel;
    JLabel inputLabel;

    // ComboBoxes
    JComboBox<String> titleFamilyBox = new JComboBox<String>(fonts);
    JComboBox<String> subtitleFamilyBox = new JComboBox<String>(fonts);
    JComboBox<String> selectionFamilyBox = new JComboBox<String>(fonts);
    JComboBox<String> inputFamilyBox = new JComboBox<String>(fonts);

    JComboBox<String> titleStyleBox;
    JComboBox<String> subtitleStyleBox;
    JComboBox<String> selectionStyleBox;
    JComboBox<String> inputStyleBox;

    // Sliders
    JSlider titleSizeSlider = new JSlider(JSlider.HORIZONTAL, 1, 36, 20);
    JSlider subtitleSizeSlider = new JSlider(JSlider.HORIZONTAL, 1, 36, 20);
    JSlider selectionSizeSlider = new JSlider(JSlider.HORIZONTAL, 1, 36, 20);
    JSlider inputSizeSlider = new JSlider(JSlider.HORIZONTAL, 1, 36, 20);
    // ------- </Functional components> -------

    // Start
    public FontMenu(Component parent, Boolean isDarkMode, SaveHandler saveHandler, ArrayList<Font> fontList, ArrayList<String> stringList) {

        stringMap = stringList;

        // Set text
        applyTexts();

        // Set fonts
        titleFont = fontList.get(0);
        subtitleFont = fontList.get(1);
        selectionFont = fontList.get(2);
        inputFont = fontList.get(3);

        // Create panel
        createPositioning();
        createFunctionals();
        applyTheme(isDarkMode);
        addPanels(isDarkMode);

        // Show popup
        this.createDialog(
            parent,
            stringList.get(0)
        ).setVisible(true);
        

        // Apply new fonts
        if ((int) this.getValue() == JOptionPane.OK_OPTION) {

            HashMap<String, Font> newFontMap = new HashMap<String, Font>();

            newFontMap.put(Utility.titleFontName, new Font((String) titleFamilyBox.getSelectedItem(), determineStyle((String) titleStyleBox.getSelectedItem()), titleSizeSlider.getValue()));
            newFontMap.put(Utility.subtitleFontName, new Font((String) subtitleFamilyBox.getSelectedItem(), determineStyle((String) subtitleStyleBox.getSelectedItem()), subtitleSizeSlider.getValue()));
            newFontMap.put(Utility.selectionFontName, new Font((String) selectionFamilyBox.getSelectedItem(), determineStyle((String) selectionStyleBox.getSelectedItem()), selectionSizeSlider.getValue()));
            newFontMap.put(Utility.inputFontName, new Font((String) inputFamilyBox.getSelectedItem(), determineStyle((String) inputStyleBox.getSelectedItem()), inputSizeSlider.getValue()));

            saveHandler.applyChanges(newFontMap, saveHandler.getDataMap());

        }

    }


    // Create components
    private void createPositioning() {

        // Main panel
        mainPanel = new JPanel(new GridLayout(5, 4));
        mainPanel.setPreferredSize(new Dimension(800, 300));

        // Other panels
        panel00.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 2, Color.black));

        Border bottomBorder = BorderFactory.createMatteBorder(0, 0, 2, 0, Color.black);
        familyPanel.setBorder(bottomBorder);
        stylePanel.setBorder(bottomBorder);
        sizePanel.setBorder(bottomBorder);

        Border rightBorder = BorderFactory.createMatteBorder(0, 0, 0, 2, Color.black);
        titlePanel.setBorder(rightBorder);
        subtitlePanel.setBorder(rightBorder);
        selectionPanel.setBorder(rightBorder);
        inputPanel.setBorder(rightBorder);

    }

    // Create functionals & assign fonts
    private void createFunctionals() {

        // Buttons
        okButton = new JButton(stringMap.get(11));
        okButton.setFocusable(false);
        okButton.addActionListener(event -> {
            this.setValue(JOptionPane.OK_OPTION);
        });

        cancelButton = new JButton(stringMap.get(12));
        cancelButton.setFocusable(false);
        cancelButton.addActionListener(event -> {
            this.setValue(JOptionPane.CANCEL_OPTION);
        });

        // Labels
        familyLabel.setFont(globalFont);
        styleLabel.setFont(globalFont);
        sizeLabel.setFont(globalFont);

        titleLabel.setFont(titleFont);
        subtitleLabel.setFont(subtitleFont);
        selectionLabel.setFont(selectionFont);
        inputLabel.setFont(inputFont);

        // ComboBoxes
        titleFamilyBox.setSelectedItem(titleFont.getFamily());
        titleFamilyBox.setFocusable(false);
        titleFamilyBox.addActionListener(this);

        titleStyleBox.setSelectedItem(determineStyleName(titleFont.getStyle()));
        titleStyleBox.setFocusable(false);
        titleStyleBox.addActionListener(this);

        subtitleFamilyBox.setSelectedItem(subtitleFont.getFamily());
        subtitleFamilyBox.setFocusable(false);
        subtitleFamilyBox.addActionListener(this);

        subtitleStyleBox.setSelectedItem(determineStyleName(subtitleFont.getStyle()));
        subtitleStyleBox.setFocusable(false);
        subtitleStyleBox.addActionListener(this);

        selectionFamilyBox.setSelectedItem(selectionFont.getFamily());
        selectionFamilyBox.setFocusable(false);
        selectionFamilyBox.addActionListener(this);

        selectionStyleBox.setSelectedItem(determineStyleName(selectionFont.getStyle()));
        selectionStyleBox.setFocusable(false);
        selectionStyleBox.addActionListener(this);

        inputFamilyBox.setSelectedItem(inputFont.getFamily());
        inputFamilyBox.setFocusable(false);
        inputFamilyBox.addActionListener(this);

        inputStyleBox.setSelectedItem(determineStyleName(inputFont.getStyle()));
        inputStyleBox.setFocusable(false);
        inputStyleBox.addActionListener(this);

        // Sliders
        titleSizeSlider.setValue(titleFont.getSize());
        titleSizeSlider.setPaintTicks(true);
        titleSizeSlider.setMinorTickSpacing(5);
        titleSizeSlider.addChangeListener(this);

        subtitleSizeSlider.setValue(subtitleFont.getSize());
        subtitleSizeSlider.setPaintTicks(true);
        subtitleSizeSlider.setMinorTickSpacing(5);
        subtitleSizeSlider.addChangeListener(this);

        selectionSizeSlider.setValue(selectionFont.getSize());
        selectionSizeSlider.setPaintTicks(true);
        selectionSizeSlider.setMinorTickSpacing(5);
        selectionSizeSlider.addChangeListener(this);

        inputSizeSlider.setValue(inputFont.getSize());
        inputSizeSlider.setPaintTicks(true);
        inputSizeSlider.setMinorTickSpacing(5);
        inputSizeSlider.addChangeListener(this);
        
    }

    // Dark mode
    private void applyTheme(Boolean isDarkMode) {
        if (isDarkMode) {
            // ------- <Set backgrounds> -------
            // General & Panels
            mainPanel.setOpaque(true);
            this.setBackground(Utility.optionBackgroundDark);
            mainPanel.setBackground(Utility.optionBackgroundDark);

            panel00.setBackground(Utility.optionBackgroundDark);
            familyPanel.setBackground(Utility.optionBackgroundDark);
            stylePanel.setBackground(Utility.optionBackgroundDark);
            sizePanel.setBackground(Utility.optionBackgroundDark);

            titlePanel.setBackground(Utility.optionBackgroundDark);
            panel11.setBackground(Utility.optionBackgroundDark);
            panel21.setBackground(Utility.optionBackgroundDark);
            panel31.setBackground(Utility.optionBackgroundDark);

            subtitlePanel.setBackground(Utility.optionBackgroundDark);
            panel12.setBackground(Utility.optionBackgroundDark);
            panel22.setBackground(Utility.optionBackgroundDark);
            panel32.setBackground(Utility.optionBackgroundDark);

            selectionPanel.setBackground(Utility.optionBackgroundDark);
            panel13.setBackground(Utility.optionBackgroundDark);
            panel23.setBackground(Utility.optionBackgroundDark);
            panel33.setBackground(Utility.optionBackgroundDark);

            inputPanel.setBackground(Utility.optionBackgroundDark);
            panel14.setBackground(Utility.optionBackgroundDark);
            panel24.setBackground(Utility.optionBackgroundDark);
            panel34.setBackground(Utility.optionBackgroundDark);

            // ComboBox
            titleFamilyBox.setBackground(Utility.comboboxDark);
            titleFamilyBox.setBorder(BorderFactory.createLineBorder(Color.BLACK));

            subtitleFamilyBox.setBackground(Utility.comboboxDark);
            subtitleFamilyBox.setBorder(BorderFactory.createLineBorder(Color.BLACK));

            selectionFamilyBox.setBackground(Utility.comboboxDark);
            selectionFamilyBox.setBorder(BorderFactory.createLineBorder(Color.BLACK));

            inputFamilyBox.setBackground(Utility.comboboxDark);
            inputFamilyBox.setBorder(BorderFactory.createLineBorder(Color.BLACK));

            titleStyleBox.setBackground(Utility.comboboxDark);
            titleStyleBox.setBorder(BorderFactory.createLineBorder(Color.BLACK));

            subtitleStyleBox.setBackground(Utility.comboboxDark);
            subtitleStyleBox.setBorder(BorderFactory.createLineBorder(Color.BLACK));

            selectionStyleBox.setBackground(Utility.comboboxDark);
            subtitleStyleBox.setBorder(BorderFactory.createLineBorder(Color.BLACK));

            inputStyleBox.setBackground(Utility.comboboxDark);
            subtitleStyleBox.setBorder(BorderFactory.createLineBorder(Color.BLACK));

            // Sliders
            titleSizeSlider.setBackground(Utility.optionBackgroundDark);
            subtitleSizeSlider.setBackground(Utility.optionBackgroundDark);
            selectionSizeSlider.setBackground(Utility.optionBackgroundDark);
            inputSizeSlider.setBackground(Utility.optionBackgroundDark);

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

            // Labels
            familyLabel.setForeground(Utility.textColourDarkmode);
            styleLabel.setForeground(Utility.textColourDarkmode);
            sizeLabel.setForeground(Utility.textColourDarkmode);

            titleLabel.setForeground(Utility.textColourDarkmode);
            subtitleLabel.setForeground(Utility.textColourDarkmode);
            selectionLabel.setForeground(Utility.textColourDarkmode);
            inputLabel.setForeground(Utility.textColourDarkmode);

            // ComboBoxes
            titleFamilyBox.setForeground(Utility.textColourDarkmode);
            subtitleFamilyBox.setForeground(Utility.textColourDarkmode);
            selectionFamilyBox.setForeground(Utility.textColourDarkmode);
            inputFamilyBox.setForeground(Utility.textColourDarkmode);

            titleStyleBox.setForeground(Utility.textColourDarkmode);
            subtitleStyleBox.setForeground(Utility.textColourDarkmode);
            selectionStyleBox.setForeground(Utility.textColourDarkmode);
            inputStyleBox.setForeground(Utility.textColourDarkmode);
            // ------- </Set text colour> -------

        }
    }

    // Addition
    private void addPanels(Boolean isDarkMode) {

        // ------- <Add to panels> -------
        // Labels
        familyPanel.add(familyLabel);
        stylePanel.add(styleLabel);
        sizePanel.add(sizeLabel);

        titlePanel.add(titleLabel);
        subtitlePanel.add(subtitleLabel);
        selectionPanel.add(selectionLabel);
        inputPanel.add(inputLabel);

        // ComboBoxes
        panel11.add(titleFamilyBox);
        panel12.add(subtitleFamilyBox);
        panel13.add(selectionFamilyBox);
        panel14.add(inputFamilyBox);

        panel21.add(titleStyleBox);
        panel22.add(subtitleStyleBox);
        panel23.add(selectionStyleBox);
        panel24.add(inputStyleBox);

        // Sliders
        panel31.add(titleSizeSlider);
        panel32.add(subtitleSizeSlider);
        panel33.add(selectionSizeSlider);
        panel34.add(inputSizeSlider);

        // Add to main panel
        mainPanel.add(panel00);
        mainPanel.add(familyPanel);
        mainPanel.add(stylePanel);
        mainPanel.add(sizePanel);

        mainPanel.add(titlePanel);
        mainPanel.add(panel11);
        mainPanel.add(panel21);
        mainPanel.add(panel31);
        
        mainPanel.add(subtitlePanel);
        mainPanel.add(panel12);
        mainPanel.add(panel22);
        mainPanel.add(panel32);

        mainPanel.add(selectionPanel);
        mainPanel.add(panel13);
        mainPanel.add(panel23);
        mainPanel.add(panel33);

        mainPanel.add(inputPanel);
        mainPanel.add(panel14);
        mainPanel.add(panel24);
        mainPanel.add(panel34);

        // Resize
        titleFamilyBox.setPreferredSize(new Dimension(200, ((Double) titleFamilyBox.getPreferredSize().getHeight()).intValue()));
        subtitleFamilyBox.setPreferredSize(new Dimension(200, ((Double) subtitleFamilyBox.getPreferredSize().getHeight()).intValue()));
        selectionFamilyBox.setPreferredSize(new Dimension(200, ((Double) selectionFamilyBox.getPreferredSize().getHeight()).intValue()));
        inputFamilyBox.setPreferredSize(new Dimension(200, ((Double) inputFamilyBox.getPreferredSize().getHeight()).intValue()));
        // ------- </Add to panels> -------

        this.setMessage(mainPanel);
        this.setOptions(new Object[]{okButton, cancelButton});

        if (isDarkMode) {
            ((JButton) this.getOptions()[0]).getParent().setBackground(Utility.optionBackgroundDark);
        }

    }

    // Apply the language
    public void applyTexts() {

        styles = new String[]{stringMap.get(1), stringMap.get(2), stringMap.get(3)};

        familyLabel = new JLabel(stringMap.get(4));
        styleLabel = new JLabel(stringMap.get(5));
        sizeLabel = new JLabel(stringMap.get(6));

        titleLabel = new JLabel(stringMap.get(7));
        subtitleLabel = new JLabel(stringMap.get(8));
        selectionLabel = new JLabel(stringMap.get(9));
        inputLabel = new JLabel(stringMap.get(10));

        titleStyleBox = new JComboBox<String>(styles);
        subtitleStyleBox = new JComboBox<String>(styles);
        selectionStyleBox = new JComboBox<String>(styles);
        inputStyleBox = new JComboBox<String>(styles);

    }


    private int determineStyle(String styleString) {

        if (styleString.equals(stringMap.get(2))) {

            return Font.BOLD;

        } else if (styleString.equals(stringMap.get(3))) {

            return Font.ITALIC;

        } else { return Font.PLAIN; }

    }

    private String determineStyleName(Integer styleInt) {

        if (styleInt.equals(Font.BOLD)) {

            return stringMap.get(2);

        } else if (styleInt.equals(Font.ITALIC)) {

            return stringMap.get(3);

        } else { return stringMap.get(1); }
        
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        
        // Family Boxes
        if (e.getSource() == titleFamilyBox) {
            titleLabel.setFont(new Font(
                (String) titleFamilyBox.getSelectedItem(), 
                titleLabel.getFont().getStyle(), 
                titleLabel.getFont().getSize()
            ));
        }
        if (e.getSource() == subtitleFamilyBox) {
            subtitleLabel.setFont(new Font(
                (String) subtitleFamilyBox.getSelectedItem(), 
                subtitleLabel.getFont().getStyle(), 
                subtitleLabel.getFont().getSize()
            ));
        }
        if (e.getSource() == selectionFamilyBox) {
            selectionLabel.setFont(new Font(
                (String) selectionFamilyBox.getSelectedItem(), 
                selectionLabel.getFont().getStyle(), 
                selectionLabel.getFont().getSize()
            ));
        }
        if (e.getSource() == inputFamilyBox) {
            inputLabel.setFont(new Font(
                (String) inputFamilyBox.getSelectedItem(), 
                inputLabel.getFont().getStyle(), 
                inputLabel.getFont().getSize()
            ));
        }



        // Style Boxes
        if (e.getSource() == titleStyleBox) {
            Integer style = determineStyle((String) titleStyleBox.getSelectedItem());
            titleLabel.setFont(new Font(
                titleLabel.getFont().getFamily(), 
                style, 
                titleLabel.getFont().getSize()
            ));
        }
        if (e.getSource() == subtitleStyleBox) {
            Integer style = determineStyle((String) subtitleStyleBox.getSelectedItem());
            subtitleLabel.setFont(new Font(
                subtitleLabel.getFont().getFamily(),
                style, 
                subtitleLabel.getFont().getSize()
            ));
        }
        if (e.getSource() == selectionStyleBox) {
            Integer style = determineStyle((String) selectionStyleBox.getSelectedItem());
            selectionLabel.setFont(new Font(
                selectionLabel.getFont().getFamily(), 
                style, 
                selectionLabel.getFont().getSize()
            ));
        }
        if (e.getSource() == inputStyleBox) {
            Integer style = determineStyle((String) inputStyleBox.getSelectedItem());
            inputLabel.setFont(new Font(
                inputLabel.getFont().getFamily(), 
                style, 
                inputLabel.getFont().getSize()
            ));
        }

    }


    @Override
    public void stateChanged(ChangeEvent e) {
        
        if (e.getSource() == titleSizeSlider) {
            titleLabel.setFont(new Font(
                titleLabel.getFont().getFamily(), 
                titleLabel.getFont().getStyle(), 
                titleSizeSlider.getValue()
            ));
        }
        if (e.getSource() == subtitleSizeSlider) {
            subtitleLabel.setFont(new Font(
                subtitleLabel.getFont().getFamily(), 
                subtitleLabel.getFont().getStyle(), 
                subtitleSizeSlider.getValue()
            ));
        }
        if (e.getSource() == selectionSizeSlider) {
            selectionLabel.setFont(new Font(
                selectionLabel.getFont().getFamily(), 
                selectionLabel.getFont().getStyle(), 
                selectionSizeSlider.getValue()
            ));
        }
        if (e.getSource() == inputSizeSlider) {
            inputLabel.setFont(new Font(
                inputLabel.getFont().getFamily(), 
                inputLabel.getFont().getStyle(), 
                inputSizeSlider.getValue()
            ));
        }

    }
    
}