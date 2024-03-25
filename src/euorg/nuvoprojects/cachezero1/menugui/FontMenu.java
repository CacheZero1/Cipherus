package euorg.nuvoprojects.cachezero1.menugui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class FontMenu extends JOptionPane implements ActionListener, ChangeListener {

    // Global
    private Font globalFont = new Font(null, Font.PLAIN, 20);
    String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
    String[] styles = new String[]{"Plain", "Bold", "Italic"};

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

    private JPanel menuPanel = new JPanel();
    private JPanel panel15 = new JPanel();
    private JPanel panel25 = new JPanel();
    private JPanel panel35 = new JPanel();

    // Components (functional)
    // Labels
    JLabel familyLabel = new JLabel("Family");
    JLabel styleLabel = new JLabel("Style");
    JLabel sizeLabel = new JLabel("Size");

    JLabel titleLabel = new JLabel("Title");
    JLabel subtitleLabel = new JLabel("Subtitle");
    JLabel selectionLabel = new JLabel("Selection");
    JLabel inputLabel = new JLabel("Input");
    JLabel menuLabel = new JLabel("Menu");

    // ComboBoxes
    JComboBox<String> titleFamilyBox = new JComboBox<String>(fonts);
    JComboBox<String> subtitleFamilyBox = new JComboBox<String>(fonts);
    JComboBox<String> selectionFamilyBox = new JComboBox<String>(fonts);
    JComboBox<String> inputFamilyBox = new JComboBox<String>(fonts);
    JComboBox<String> menuFamilyBox = new JComboBox<String>(fonts);

    JComboBox<String> titleStyleBox = new JComboBox<String>(styles);
    JComboBox<String> subtitleStyleBox = new JComboBox<String>(styles);
    JComboBox<String> selectionStyleBox = new JComboBox<String>(styles);
    JComboBox<String> inputStyleBox = new JComboBox<String>(styles);
    JComboBox<String> menuStyleBox = new JComboBox<String>(styles);

    // Sliders
    JSlider titleSizeSlider = new JSlider(JSlider.HORIZONTAL, 1, 36, 20);
    JSlider subtitleSizeSlider = new JSlider(JSlider.HORIZONTAL, 1, 36, 20);
    JSlider selectionSizeSlider = new JSlider(JSlider.HORIZONTAL, 1, 36, 20);
    JSlider inputSizeSlider = new JSlider(JSlider.HORIZONTAL, 1, 36, 20);

    public FontMenu(Component parent) {

        // Create panel
        createPositioning();
        createFunctionals();
        addPanels();
        
        int chosenOption = FontMenu.showConfirmDialog(
            parent, 
            mainPanel, 
            "Font settings", 
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.PLAIN_MESSAGE
        );

        if (chosenOption == JOptionPane.OK_OPTION) {

            System.out.println("ok");

        } else if (chosenOption == JOptionPane.CANCEL_OPTION) {

            System.out.println("cancel");

        }

    }


    // Create components
    private void createPositioning() {

        // Main panel
        mainPanel = new JPanel(new GridLayout(6, 4));
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
        menuPanel.setBorder(rightBorder);

    }

    private void createFunctionals() {

        // Labels
        familyLabel.setFont(globalFont);
        styleLabel.setFont(globalFont);
        sizeLabel.setFont(globalFont);

        titleLabel.setFont(globalFont);
        subtitleLabel.setFont(globalFont);
        selectionLabel.setFont(globalFont);
        inputLabel.setFont(globalFont);
        menuLabel.setFont(globalFont);

        // ComboBoxes
        titleFamilyBox.addActionListener(this);
        titleStyleBox.addActionListener(this);

        subtitleFamilyBox.addActionListener(this);
        subtitleStyleBox.addActionListener(this);

        selectionFamilyBox.addActionListener(this);
        selectionStyleBox.addActionListener(this);

        inputFamilyBox.addActionListener(this);
        inputStyleBox.addActionListener(this);

        menuFamilyBox.addActionListener(this);
        menuStyleBox.addActionListener(this);

        // Sliders
        titleSizeSlider.setPaintTicks(true);
        titleSizeSlider.setMinorTickSpacing(5);
        titleSizeSlider.addChangeListener(this);

        subtitleSizeSlider.setPaintTicks(true);
        subtitleSizeSlider.setMinorTickSpacing(5);
        subtitleSizeSlider.addChangeListener(this);

        selectionSizeSlider.setPaintTicks(true);
        selectionSizeSlider.setMinorTickSpacing(5);
        selectionSizeSlider.addChangeListener(this);

        inputSizeSlider.setPaintTicks(true);
        inputSizeSlider.setMinorTickSpacing(5);
        inputSizeSlider.addChangeListener(this);
        
    }

    private void addPanels() {

        // Add to panels
        // Labels
        familyPanel.add(familyLabel);
        stylePanel.add(styleLabel);
        sizePanel.add(sizeLabel);

        titlePanel.add(titleLabel);
        subtitlePanel.add(subtitleLabel);
        selectionPanel.add(selectionLabel);
        inputPanel.add(inputLabel);
        menuPanel.add(menuLabel);

        // ComboBoxes
        panel11.add(titleFamilyBox);
        panel12.add(subtitleFamilyBox);
        panel13.add(selectionFamilyBox);
        panel14.add(inputFamilyBox);
        panel15.add(menuFamilyBox);

        panel21.add(titleStyleBox);
        panel22.add(subtitleStyleBox);
        panel23.add(selectionStyleBox);
        panel24.add(inputStyleBox);
        panel25.add(menuStyleBox);

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

        mainPanel.add(menuPanel);
        mainPanel.add(panel15);
        mainPanel.add(panel25);
        mainPanel.add(panel35);

        // Resize
        titleFamilyBox.setPreferredSize(new Dimension(200, ((Double) titleFamilyBox.getPreferredSize().getHeight()).intValue()));
        subtitleFamilyBox.setPreferredSize(new Dimension(200, ((Double) subtitleFamilyBox.getPreferredSize().getHeight()).intValue()));
        selectionFamilyBox.setPreferredSize(new Dimension(200, ((Double) selectionFamilyBox.getPreferredSize().getHeight()).intValue()));
        inputFamilyBox.setPreferredSize(new Dimension(200, ((Double) inputFamilyBox.getPreferredSize().getHeight()).intValue()));
        menuFamilyBox.setPreferredSize(new Dimension(200, ((Double) menuFamilyBox.getPreferredSize().getHeight()).intValue()));

    }

    private int determineStyle(String styleString) {
        switch (styleString) {
            case "Plain":
                return Font.PLAIN;
            
            case "Bold":
                return Font.BOLD;

            case "Italic":
                return Font.ITALIC;
        
            default:
                return Font.PLAIN;
        }

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
        if (e.getSource() == menuFamilyBox) {
            menuLabel.setFont(new Font(
                (String) menuFamilyBox.getSelectedItem(), 
                menuLabel.getFont().getStyle(), 
                menuLabel.getFont().getSize()
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
        if (e.getSource() == menuStyleBox) {
            Integer style = determineStyle((String) menuStyleBox.getSelectedItem());
            menuLabel.setFont(new Font(
                menuLabel.getFont().getFamily(), 
                style, 
                menuLabel.getFont().getSize()
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