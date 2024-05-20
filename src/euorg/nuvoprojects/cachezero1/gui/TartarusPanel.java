package euorg.nuvoprojects.cachezero1.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.plaf.basic.BasicScrollBarUI;

import euorg.nuvoprojects.cachezero1.Utility;
import euorg.nuvoprojects.cachezero1.ciphers.tartarus.Tartarus;
import euorg.nuvoprojects.cachezero1.literates.LanguageHandler;
import euorg.nuvoprojects.cachezero1.literates.SaveHandler;

public class TartarusPanel extends JPanel implements ActionListener {

    // Globals
    private static Boolean darkMode;
    public BufferedImage image = new BufferedImage(256, 144, BufferedImage.TYPE_INT_RGB);

    // Settings
    private static SaveHandler saveHandler;
    private static LanguageHandler languageHandler;

    // Components (Positioning)
    private JPanel topPanel;
    private JPanel centerPanel;
    private JPanel bottomPanel;

    private JPanel preciseTL;
    private JPanel preciseTR;
    private JPanel preciseBL;
    private JPanel preicseBR;

    private JPanel topCenterPanel;

    // Components (Functional)
    private JLabel colourRGBLabel;

    private JRadioButton redRadioButton;
    private JRadioButton greenRadioButton;
    private JRadioButton blueRadioButton;
    private ButtonGroup colourRGBButtonGroup;
    private static JLabel imagePreviewLabel;
    private JButton imageChooserButton;

    private JScrollPane inOutScrollPane;
    JTextArea inOutTextArea;
    private JButton startButton;

    private JRadioButton encryptRadioButton;
    private JRadioButton decryptRadioButton;
    private ButtonGroup radioButtonOptionGroup;

    public TartarusPanel(SaveHandler handler, LanguageHandler langHandler, Boolean isDarkmode) {

        // Settings
        this.setLayout(new BorderLayout());
        saveHandler = handler;
        languageHandler = langHandler;
        darkMode = isDarkmode;

        // Populate Panel
        createPositioningComponents();
        try {
            createFunctionalComponents();
        } catch (Exception e) {
            HashMap<String, String> langMap = languageHandler.getLangMap(saveHandler.getDataMapLang());
            new ErrorPane(isDarkmode, new ArrayList<String>(Arrays.asList(langMap.get(Utility.minErr), langMap.get(Utility.graErr), langMap.get(Utility.accept))));
        }
        applyTexts();
        applyFonts();
        applyTheme(isDarkmode);
        addGUIComponents();

    }

    // Positioners
    private void createPositioningComponents() {

        // NORTH
        topPanel = new JPanel();
        topPanel.setPreferredSize(new Dimension(topPanel.getWidth(), 150));
        topPanel.setLayout(new GridLayout(2, 2, 10, 10));

        preciseTL = new JPanel();
        preciseTL.setLayout(new BorderLayout());
        preciseTL.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2, true));

        preciseTR = new JPanel();
        preciseTR.setLayout(new BorderLayout());

        preciseBL = new JPanel();
        preciseBL.setLayout(new BorderLayout());

        preicseBR = new JPanel();
        preicseBR.setLayout(new GridLayout(1, 3));


        // CENTER
        centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(2, 1, 10, 10));
        centerPanel.setBorder(BorderFactory.createMatteBorder(2, 0, 2, 0, Color.BLACK));

        topCenterPanel = new JPanel();
        topCenterPanel.setLayout(new BorderLayout(10, 10));

        // SOUTH
        bottomPanel = new JPanel();
        bottomPanel.setPreferredSize(new Dimension(bottomPanel.getWidth(), 70));
        bottomPanel.setLayout(new BorderLayout());

    }

    // Functionals
    private void createFunctionalComponents() {

        // JLabels
        colourRGBLabel = new JLabel();
        colourRGBLabel.setFocusable(false);
        colourRGBLabel.setHorizontalAlignment(JLabel.RIGHT);

        imagePreviewLabel = new JLabel();
        imagePreviewLabel.setFocusable(false);
        imagePreviewLabel.setHorizontalAlignment(JLabel.CENTER);

        // Main components
        redRadioButton = new JRadioButton();
        redRadioButton.setFocusable(false);
        redRadioButton.setSelected(true);
        greenRadioButton = new JRadioButton();
        greenRadioButton.setFocusable(false);
        blueRadioButton = new JRadioButton();
        blueRadioButton.setFocusable(false);

        colourRGBButtonGroup = new ButtonGroup();
        colourRGBButtonGroup.add(redRadioButton);
        colourRGBButtonGroup.add(greenRadioButton);
        colourRGBButtonGroup.add(blueRadioButton);

        inOutTextArea = new JTextArea();
        inOutTextArea.setLineWrap(true);

        inOutScrollPane = new JScrollPane(inOutTextArea);
        inOutScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        imageChooserButton = new JButton();
        imageChooserButton.setFocusable(false);
        imageChooserButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        imageChooserButton.setHorizontalAlignment(JButton.CENTER);
        imageChooserButton.addActionListener(this);

        startButton = new JButton();
        startButton.setFocusable(false);
        startButton.setHorizontalAlignment(JButton.CENTER);
        startButton.addActionListener(this);

        // Action selectors
        encryptRadioButton = new JRadioButton();
        encryptRadioButton.setIcon(new ImageIcon(this.getClass().getResource("/images/encrypt_b.png")));
        encryptRadioButton.setHorizontalAlignment(JRadioButton.CENTER);
        encryptRadioButton.setFocusPainted(false);
        encryptRadioButton.addActionListener(this);
        encryptRadioButton.setSelected(true);

        decryptRadioButton = new JRadioButton();
        decryptRadioButton.setIcon(new ImageIcon(this.getClass().getResource("/images/decrypt_b.png")));
        decryptRadioButton.setHorizontalAlignment(JRadioButton.CENTER);
        decryptRadioButton.setFocusPainted(false);
        decryptRadioButton.addActionListener(this);

        radioButtonOptionGroup = new ButtonGroup();
        radioButtonOptionGroup.add(encryptRadioButton);
        radioButtonOptionGroup.add(decryptRadioButton);

    }

    // Set texts
    public void applyTexts() {

        HashMap<String, String> textMap = languageHandler.getLangMap(saveHandler.getDataMapLang());

        // Labels
        colourRGBLabel.setText(textMap.get(Utility.tarPanRGBFor));

        // Buttons
        imageChooserButton.setText(textMap.get(Utility.tarPanChooseImage));
        startButton.setText(textMap.get(Utility.cryPanSta));

        // RadioButtons
        encryptRadioButton.setText(textMap.get(Utility.cryPanEnc));
        decryptRadioButton.setText(textMap.get(Utility.cryPanDec));

        redRadioButton.setText(textMap.get(Utility.tarPanRGBRed));
        greenRadioButton.setText(textMap.get(Utility.tarPanRGBGreen));
        blueRadioButton.setText(textMap.get(Utility.tarPanRGBBlue));

    }

    // Font setting
    public void applyFonts() {

        // Title
        encryptRadioButton.setFont(saveHandler.getFontMap().get(Utility.titleFontName));
        decryptRadioButton.setFont(saveHandler.getFontMap().get(Utility.titleFontName));
        startButton.setFont(saveHandler.getFontMap().get(Utility.titleFontName));

        // Subtitle
        colourRGBLabel.setFont(saveHandler.getFontMap().get(Utility.subtitleFontName));
        imageChooserButton.setFont(saveHandler.getFontMap().get(Utility.subtitleFontName));
        redRadioButton.setFont(saveHandler.getFontMap().get(Utility.subtitleFontName));
        greenRadioButton.setFont(saveHandler.getFontMap().get(Utility.subtitleFontName));
        blueRadioButton.setFont(saveHandler.getFontMap().get(Utility.subtitleFontName));

        // Input
        inOutTextArea.setFont(saveHandler.getFontMap().get(Utility.inputFontName));

    }

    // Addition
    private void addGUIComponents() {

        // NORTH
        preciseTL.add(encryptRadioButton, BorderLayout.CENTER);
        preciseTR.add(decryptRadioButton, BorderLayout.CENTER);
        preciseBL.add(colourRGBLabel, BorderLayout.CENTER);
        preicseBR.add(redRadioButton);
        preicseBR.add(greenRadioButton);
        preicseBR.add(blueRadioButton);

        topPanel.add(preciseTL);
        topPanel.add(preciseTR);
        topPanel.add(preciseBL);
        topPanel.add(preicseBR);


        // Center
        //topCenterPanel.add(imageChooserButton, BorderLayout.NORTH);
        topCenterPanel.add(imagePreviewLabel, BorderLayout.CENTER);

        centerPanel.add(topCenterPanel);
        centerPanel.add(inOutScrollPane);


        // SOUTH
        bottomPanel.add(startButton, BorderLayout.CENTER);


        // Add to this
        this.add(topPanel, BorderLayout.NORTH);
        this.add(centerPanel, BorderLayout.CENTER);
        this.add(bottomPanel, BorderLayout.SOUTH);

    }

    // Dark mode
    private void applyTheme(Boolean isDarkMode) {

        if (isDarkMode) {
            // ------- <Set backgrounds> -------
            // Panels
            topPanel.setBackground(Utility.backgroundDark);
            centerPanel.setBackground(Utility.backgroundDark);
            bottomPanel.setBackground(Utility.backgroundDark);

            preciseTL.setBackground(Utility.backgroundDark);
            preciseTR.setBackground(Utility.backgroundDark);
            preciseBL.setBackground(Utility.backgroundDark);
            preicseBR.setBackground(Utility.backgroundDark);

            topCenterPanel.setBackground(Utility.buttonDark);

            // Text fields & areas
            inOutTextArea.setBackground(Utility.optionBackgroundDark);
            inOutTextArea.setCaretColor(Utility.textColourDarkmode);
            inOutTextArea.setBorder(null);
            inOutScrollPane.setBorder(null);
            inOutScrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
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
            });
            inOutScrollPane.getVerticalScrollBar().getComponent(0).getParent().setBackground(Utility.backgroundDark); // Down button
            inOutScrollPane.getVerticalScrollBar().getComponent(1).getParent().setBackground(Utility.backgroundDark); // Up button

            // Buttons
            redRadioButton.setBackground(Utility.backgroundDark);
            greenRadioButton.setBackground(Utility.backgroundDark);
            blueRadioButton.setBackground(Utility.backgroundDark);

            imageChooserButton.setBackground(Utility.buttonDark);

            startButton.setBackground(Utility.buttonDark);
            startButton.setOpaque(true);
            encryptRadioButton.setBackground(Utility.buttonDark);
            decryptRadioButton.setBackground(Utility.buttonDark);
            // ------- </Set backgrounds> -------

            // ------- <Set text colour> -------
            // Labels
            colourRGBLabel.setForeground(Utility.textColourDarkmode);
            redRadioButton.setForeground(Utility.textColourDarkmode);
            greenRadioButton.setForeground(Utility.textColourDarkmode);
            blueRadioButton.setForeground(Utility.textColourDarkmode);

            // Text fields & areas
            inOutTextArea.setForeground(Utility.textColourDarkmode);

            // Buttons
            startButton.setForeground(Utility.textColourDarkmode);
            startButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));

            encryptRadioButton.setForeground(Utility.textColourDarkmode);
            encryptRadioButton.setIcon(new ImageIcon(this.getClass().getResource("/images/encrypt_w.png")));

            decryptRadioButton.setForeground(Utility.textColourDarkmode);
            decryptRadioButton.setIcon(new ImageIcon(this.getClass().getResource("/images/decrypt_w.png")));
            // ------- </Set text colour> -------
        }

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        
        // Encrypt label swap
        if (e.getSource() == encryptRadioButton) {

            preciseTL.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2, true));
            preciseTR.setBorder(null);

            topCenterPanel.removeAll();
            topCenterPanel.add(imagePreviewLabel, BorderLayout.CENTER);
            topCenterPanel.revalidate();
            topCenterPanel.repaint();

        }

        // Decrypt label swap
        if (e.getSource() == decryptRadioButton) {

            preciseTL.setBorder(null);
            preciseTR.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2, true));

            topCenterPanel.removeAll();
            topCenterPanel.add(imageChooserButton, BorderLayout.NORTH);
            topCenterPanel.add(imagePreviewLabel, BorderLayout.CENTER);

        }

        // Image chooser button
        if (e.getSource() == imageChooserButton) {
            try {
                JFileChooser fileChooser = new JFileChooser(new File("."));
                if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                    BufferedImage selectedImage = ImageIO.read(fileChooser.getSelectedFile());
                    imagePreviewLabel.setIcon(new ImageIcon(selectedImage));
                    image = selectedImage;
                } 
            } catch (Exception error) {
                HashMap<String, String> langMap = languageHandler.getLangMap(saveHandler.getDataMapLang());
                new ErrorPane(darkMode, new ArrayList<String>(Arrays.asList(langMap.get(Utility.minErr), langMap.get(Utility.reaFilErr), langMap.get(Utility.accept))));
            }   
        }

        // Start process
        if (e.getSource() == startButton) {

            String colourscheme;

            if (colourRGBButtonGroup.getSelection().equals(redRadioButton.getModel())) {
                colourscheme = Utility.red;
            } else if (colourRGBButtonGroup.getSelection().equals(greenRadioButton.getModel())) {
                colourscheme = Utility.green;
            } else {
                colourscheme = Utility.blue;
            }

            // Encrypt
            if (encryptRadioButton.isSelected()) {

                try {

                    Tartarus tartarus = new Tartarus();

                    image = tartarus.createImage(inOutTextArea.getText(), colourscheme); // ADD RGB CHOOSER
                    imagePreviewLabel.setIcon(new ImageIcon(image));
                    imagePreviewLabel.repaint();
                    
                } catch (Exception encryptionError) {
                    HashMap<String, String> langMap = languageHandler.getLangMap(saveHandler.getDataMapLang());
                    new ErrorPane(darkMode, new ArrayList<String>(Arrays.asList(langMap.get(Utility.minErr), langMap.get(Utility.calErr), langMap.get(Utility.accept))));
                }
                
            // Decrypt
            } else if (decryptRadioButton.isSelected()) {

                try {

                    Tartarus tartarus = new Tartarus();

                    String decrypted = tartarus.readImage(image, colourscheme);
                    inOutTextArea.setText(decrypted.strip());

                } catch (Exception decryptionError) {
                    HashMap<String, String> langMap = languageHandler.getLangMap(saveHandler.getDataMapLang());
                    new ErrorPane(darkMode, new ArrayList<String>(Arrays.asList(langMap.get(Utility.minErr), langMap.get(Utility.calErr), langMap.get(Utility.accept))));
                }

            }

        }

    }
    
}
