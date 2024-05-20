package euorg.nuvoprojects.cachezero1.gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicScrollBarUI;

import euorg.nuvoprojects.cachezero1.Utility;
import euorg.nuvoprojects.cachezero1.literates.LanguageHandler;
import euorg.nuvoprojects.cachezero1.literates.SaveHandler;
import euorg.nuvoprojects.cachezero1.menugui.AboutMenu;
import euorg.nuvoprojects.cachezero1.menugui.ColourMenu;
import euorg.nuvoprojects.cachezero1.menugui.EncoderHelpMenu;
import euorg.nuvoprojects.cachezero1.menugui.FontMenu;
import euorg.nuvoprojects.cachezero1.menugui.LanguageMenu;
import euorg.nuvoprojects.cachezero1.menugui.SaveImageMenu;
import euorg.nuvoprojects.cachezero1.menugui.SaveTextMenu;

public class MainWindow extends JFrame implements ActionListener {

    // Settings
    private static SaveHandler saveHandler;
    private static LanguageHandler languageHandler;

    private String cipherName;
    private final String cryptorName = "cryptor";
    private final String tartarusName = "tartarus";

    private static Boolean isDarkMode;

    // Components (JMenu)
    private JMenuBar menuBar;
    //
    private JMenu cipherusMenu;
    private JMenuItem saveTextMenuItem;
    private JMenuItem saveImageMenuItem;
    private JMenuItem aboutMenuItem;
    private JMenuItem exitMenuItem;
    //
    private JMenu settingsMenu;
    private JMenuItem fontMenuItem;
    private JMenuItem colourMenuItem;
    private JMenuItem languageMenuItem;
    //
    private JMenu helpMenu;
    private JMenuItem encodersHelpMenuItem;
    private JMenuItem applicationHelpMenuItem;

    // Components (Positioning)
    JPanel leftPanel;
    JPanel rightPanel;
    JPanel centerPanel;
    JScrollPane leftScrollPane;
    GridBagConstraints gbc;

    // Components (Functional)
    JButton cryptorButton;
    JButton tartarusButton;

    // Instances (Ciphers)
    CryptorPanel cryptorPanel;
    TartarusPanel tartarusPanel;

    // Instances (Menus)
    SaveTextMenu saveTextMenu;
    SaveImageMenu saveImageMenu;
    

    public MainWindow(String version, SaveHandler handler, LanguageHandler langHandler, Boolean darkMode) {

        // Globals
        saveHandler = handler;
        languageHandler = langHandler;

        isDarkMode = darkMode;

        // Normal settings
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setMinimumSize(new Dimension(900, 600));
        this.setTitle("Cipherus - (" + version + ")");
        this.setLayout(new BorderLayout());

        this.cipherName = cryptorName;

        // Settings with exception chance
        try {
            this.setIconImage(ImageIO.read(getClass().getResource("/images/icon_128px.png")));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, langHandler.getLangMap(handler.getDataMapLang()).get(Utility.graErr), langHandler.getLangMap(handler.getDataMapLang()).get(Utility.minErr), JOptionPane.ERROR_MESSAGE);
        }

        // Instances
        cryptorPanel = new CryptorPanel(handler, langHandler, darkMode);
        tartarusPanel = new TartarusPanel(handler, langHandler, darkMode);

        // Populate GUI
        createJMenu();
        createPositioningComponents();
        createFunctionalComponents();
        applyTheme();
        addGUIComponents();
        applyTexts();
        applyFont();

    }


    // Create menu
    private void createJMenu() {

        // Main Menus
        menuBar = new JMenuBar();

        cipherusMenu = new JMenu("Cipherus");
        settingsMenu = new JMenu();
        helpMenu = new JMenu();

        // Cipherus Menu
        saveTextMenuItem = new JMenuItem();
        saveImageMenuItem = new JMenuItem();
        aboutMenuItem = new JMenuItem();
        exitMenuItem = new JMenuItem();
        
        // Settings Menu
        fontMenuItem = new JMenuItem();
        colourMenuItem = new JMenuItem();
        languageMenuItem = new JMenuItem();

        // Help
        encodersHelpMenuItem = new JMenuItem();
        applicationHelpMenuItem = new JMenuItem();


        // ActionListeners //TODO: add functionalities to other menus
        saveTextMenuItem.addActionListener(this);
        saveImageMenuItem.addActionListener(this);
        aboutMenuItem.addActionListener(this);
        exitMenuItem.addActionListener(exitAction -> { System.exit(0); });

        fontMenuItem.addActionListener(this);
        colourMenuItem.addActionListener(this);
        languageMenuItem.addActionListener(this);

        encodersHelpMenuItem.addActionListener(this);
        applicationHelpMenuItem.addActionListener(this);

    }

    // Create positioners
    private void createPositioningComponents() {

        gbc = new GridBagConstraints();
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = GridBagConstraints.REMAINDER;

        leftPanel = new JPanel();
        leftPanel.setLayout(new GridBagLayout());

        rightPanel = new JPanel();
        rightPanel.setPreferredSize(new Dimension(170, rightPanel.getHeight()));

        centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());

        leftScrollPane = new JScrollPane(leftPanel);
        leftScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        leftScrollPane.setPreferredSize(new Dimension(170, leftPanel.getHeight()));

    }

    // Create functionals
    private void createFunctionalComponents() {

        // Cryptor activator
        cryptorButton = new JButton("Cryptor");
        cryptorButton.setFocusable(false);
        cryptorButton.setPreferredSize(new Dimension(cryptorButton.getWidth(), 50));
        cryptorButton.addActionListener(this);

        // Tartarus activator
        tartarusButton = new JButton("Tartarus");
        tartarusButton.setFocusable(false);
        tartarusButton.setPreferredSize(new Dimension(tartarusButton.getWidth(), 50));
        tartarusButton.addActionListener(this);

    }

    // Add all to JFrame
    private void addGUIComponents() {

        // JMenu
        cipherusMenu.add(saveTextMenuItem);
        cipherusMenu.add(saveImageMenuItem);
        cipherusMenu.add(aboutMenuItem);
        cipherusMenu.add(exitMenuItem);

        settingsMenu.add(fontMenuItem);
        settingsMenu.add(colourMenuItem);
        settingsMenu.add(languageMenuItem);

        helpMenu.add(encodersHelpMenuItem);
        helpMenu.add(applicationHelpMenuItem);

        menuBar.add(cipherusMenu);
        menuBar.add(settingsMenu);
        menuBar.add(helpMenu);

        this.setJMenuBar(menuBar);

        // Activators
        leftPanel.add(cryptorButton, gbc);
        leftPanel.add(tartarusButton, gbc);

        // Main view
        centerPanel.add(cryptorPanel, BorderLayout.CENTER);

        // Addition
        this.add(rightPanel, BorderLayout.EAST);
        this.add(leftScrollPane, BorderLayout.WEST);
        this.add(centerPanel, BorderLayout.CENTER);

    }

    // Set texts
    private void applyTexts() {

        HashMap<String, String> textMap = languageHandler.getLangMap(saveHandler.getDataMapLang());

        // Menu
        settingsMenu.setText(textMap.get(Utility.menSet));
        helpMenu.setText(textMap.get(Utility.menHel));

        saveTextMenuItem.setText(textMap.get(Utility.menSavTex));
        saveImageMenuItem.setText(textMap.get(Utility.menSavIma));
        aboutMenuItem.setText(textMap.get(Utility.menAbo));
        exitMenuItem.setText(textMap.get(Utility.menExi));
        
        fontMenuItem.setText(textMap.get(Utility.menFon));
        colourMenuItem.setText(textMap.get(Utility.menCol));
        languageMenuItem.setText(textMap.get(Utility.menLan));

        encodersHelpMenuItem.setText(textMap.get(Utility.menCip));
        applicationHelpMenuItem.setText(textMap.get(Utility.menNav));

    }

    // Apply fonts
    private void applyFont() {

        Font selectionFont = saveHandler.getFontMap().get(Utility.selectionFontName);

        cryptorButton.setFont(selectionFont);
        tartarusButton.setFont(selectionFont);

    }

    // Set theme
    private void applyTheme() {

        if (isDarkMode) {
            // ------- <Set backgrounds> -------
            // Menubar
            menuBar.setBackground(Utility.titlebarDark);

            cipherusMenu.getPopupMenu().setBorder(new LineBorder(Utility.backgroundDark));
            settingsMenu.getPopupMenu().setBorder(new LineBorder(Utility.backgroundDark));
            helpMenu.getPopupMenu().setBorder(new LineBorder(Utility.backgroundDark));

            saveTextMenuItem.setBackground(Utility.titlebarDark);
            saveImageMenuItem.setBackground(Utility.titlebarDark);
            aboutMenuItem.setBackground(Utility.titlebarDark);
            exitMenuItem.setBackground(Utility.titlebarDark);

            fontMenuItem.setBackground(Utility.titlebarDark);
            colourMenuItem.setBackground(Utility.titlebarDark);
            languageMenuItem.setBackground(Utility.titlebarDark);

            encodersHelpMenuItem.setBackground(Utility.titlebarDark);
            applicationHelpMenuItem.setBackground(Utility.titlebarDark);
            
            // General & Panels
            this.getRootPane().setBackground(Utility.backgroundDark);
            leftScrollPane.setBorder(null);
            leftScrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
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
            leftScrollPane.getVerticalScrollBar().getComponent(0).getParent().setBackground(Utility.buttonDark); // Down button
            leftScrollPane.getVerticalScrollBar().getComponent(1).getParent().setBackground(Utility.buttonDark); // Up button
            leftPanel.setBackground(Utility.backgroundDark);
            rightPanel.setBackground(Utility.backgroundDark);
            centerPanel.setBackground(Utility.backgroundDark);

            // Buttons
            cryptorButton.setBackground(Utility.buttonDark);
            cryptorButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            tartarusButton.setBackground(Utility.buttonDark);
            tartarusButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            // ------- </Set backgrounds> -------

            // ------- <Set text colour> -------
            // Menu & MenuItems
            menuBar.setForeground(Utility.textColourDarkmode);

            cipherusMenu.setForeground(Utility.textColourDarkmode);
            settingsMenu.setForeground(Utility.textColourDarkmode);
            helpMenu.setForeground(Utility.textColourDarkmode);

            saveTextMenuItem.setForeground(Utility.textColourDarkmode);
            saveImageMenuItem.setForeground(Utility.textColourDarkmode);
            aboutMenuItem.setForeground(Utility.textColourDarkmode);
            exitMenuItem.setForeground(Utility.textColourDarkmode);

            fontMenuItem.setForeground(Utility.textColourDarkmode);
            colourMenuItem.setForeground(Utility.textColourDarkmode);
            languageMenuItem.setForeground(Utility.textColourDarkmode);

            encodersHelpMenuItem.setForeground(Utility.textColourDarkmode);
            applicationHelpMenuItem.setForeground(Utility.textColourDarkmode);

            // Buttons
            tartarusButton.setForeground(Utility.textColourDarkmode);
            cryptorButton.setForeground(Utility.textColourDarkmode);
            // ------- </Set text colour> -------
        }

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        
        // ------- <Cipher selection> -------
        // Cryptor button
        if (e.getSource() == cryptorButton) {
            centerPanel.remove(centerPanel.getComponent(0));
            centerPanel.add(cryptorPanel, BorderLayout.CENTER);
            centerPanel.revalidate();
            centerPanel.repaint();

            cipherName = cryptorName;
        }

        // Tartarus button
        if (e.getSource() == tartarusButton) {
            centerPanel.remove(centerPanel.getComponent(0));
            centerPanel.add(tartarusPanel, BorderLayout.CENTER);
            centerPanel.revalidate();
            centerPanel.repaint();

            cipherName = tartarusName;
        }
        // ------- </Cipher selection> -------

        // ------- <Menu clicks> -------
        // Save text
        if (e.getSource() == saveTextMenuItem) {

            HashMap<String, String> textMap = languageHandler.getLangMap(saveHandler.getDataMapLang());

            switch(cipherName) {
                case tartarusName:
                    saveTextMenu = new SaveTextMenu(this, isDarkMode, Arrays.asList(tartarusPanel.inOutTextArea.getText()), new ArrayList<String>(Arrays.asList(
                        textMap.get(Utility.savTexSavTex),
                        textMap.get(Utility.savTexSav),
                        textMap.get(Utility.savTexLef),
                        textMap.get(Utility.savTexRig),
                        textMap.get(Utility.savFilErr),
                        textMap.get(Utility.majErr),
                        textMap.get(Utility.close)
                    )));
                    break;

                case cryptorName:
                    saveTextMenu = new SaveTextMenu(this, isDarkMode, Arrays.asList(cryptorPanel.normalTextArea.getText(), cryptorPanel.cipheredTextArea.getText()), new ArrayList<String>(Arrays.asList(
                        textMap.get(Utility.savTexSavTex),
                        textMap.get(Utility.savTexSav),
                        textMap.get(Utility.savTexLef),
                        textMap.get(Utility.savTexRig),
                        textMap.get(Utility.savFilErr),
                        textMap.get(Utility.majErr),
                        textMap.get(Utility.close)
                    )));
                    break;

                default:
                    break;
            }
        }

        // Save image
        if (e.getSource() == saveImageMenuItem) {
            HashMap<String, String> textMap = languageHandler.getLangMap(saveHandler.getDataMapLang());
            new SaveImageMenu(this, isDarkMode, tartarusPanel.image, new ArrayList<String>(Arrays.asList(
                textMap.get(Utility.savImgSavImg),
                textMap.get(Utility.savTexSav),
                textMap.get(Utility.savFilErr),
                textMap.get(Utility.majErr),
                textMap.get(Utility.close)
            )));
        }

        // About page
        if (e.getSource() == aboutMenuItem) {
            HashMap<String, String> langMap = languageHandler.getLangMap(saveHandler.getDataMapLang());
            new AboutMenu(this, isDarkMode, new ArrayList<String>(Arrays.asList(
                langMap.get(Utility.aboMenDes1),
                langMap.get(Utility.aboMenDes2),
                langMap.get(Utility.aboMenDes3),
                langMap.get(Utility.aboMenTit),
                langMap.get(Utility.accept)
            )));
        }

        // Font settings
        if (e.getSource() == fontMenuItem) {
            HashMap<String, Font> fontMap = saveHandler.getFontMap();
            HashMap<String, String> langMap = languageHandler.getLangMap(saveHandler.getDataMapLang());
            new FontMenu(this, isDarkMode, saveHandler, new ArrayList<Font>(Arrays.asList(
                fontMap.get(Utility.titleFontName), 
                fontMap.get(Utility.subtitleFontName), 
                fontMap.get(Utility.selectionFontName), 
                fontMap.get(Utility.inputFontName)
            )), new ArrayList<String>(Arrays.asList(
                langMap.get(Utility.fonMenFonSet),
                langMap.get(Utility.fonMenStyPla),
                langMap.get(Utility.fonMenStyBol),
                langMap.get(Utility.fonMenStyIta),
                langMap.get(Utility.fonMenFam),
                langMap.get(Utility.fonMenSty),
                langMap.get(Utility.fonMenSiz),
                langMap.get(Utility.fonMenTit),
                langMap.get(Utility.fonMenSub),
                langMap.get(Utility.fonMenSel),
                langMap.get(Utility.fonMenInp),
                langMap.get(Utility.accept),
                langMap.get(Utility.cancel)
            )));
            applyFont();
            cryptorPanel.applyFonts();
            tartarusPanel.applyFonts();
        }

        // Colour settings
        if (e.getSource() == colourMenuItem) {
            HashMap<String, String> langMap = languageHandler.getLangMap(saveHandler.getDataMapLang());
            new ColourMenu(this, isDarkMode, saveHandler, new ArrayList<String>(Arrays.asList(langMap.get(Utility.colMenTit), langMap.get(Utility.accept), langMap.get(Utility.cancel))));
        }

        // Language settings
        if (e.getSource() == languageMenuItem) {
            new LanguageMenu(this, saveHandler, languageHandler.getLangMap(saveHandler.getDataMapLang()).get(Utility.lanMenTit));
            applyTexts();
            cryptorPanel.applyTexts();
            tartarusPanel.applyTexts();
        }

        // Encoders help menu
        if (e.getSource() == encodersHelpMenuItem) {
            HashMap<String, String> langMap = languageHandler.getLangMap(saveHandler.getDataMapLang());
            String tempLang = saveHandler.getDataMapLang();
            new EncoderHelpMenu(this, new ArrayList<String>(Arrays.asList(
                tempLang,
                langMap.get(Utility.help), 
                langMap.get(Utility.close)
            )));
        }
        // ------- </Menu clicks> -------

    }
    
}
