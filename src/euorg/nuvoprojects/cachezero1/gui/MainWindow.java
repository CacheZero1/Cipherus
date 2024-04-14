package euorg.nuvoprojects.cachezero1.gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.FileSystems;
import java.util.Arrays;
import java.awt.BorderLayout;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import euorg.nuvoprojects.cachezero1.LanguageHandler;
import euorg.nuvoprojects.cachezero1.SaveHandler;
import euorg.nuvoprojects.cachezero1.menugui.AboutMenu;
import euorg.nuvoprojects.cachezero1.menugui.FontMenu;
import euorg.nuvoprojects.cachezero1.menugui.SaveImageMenu;
import euorg.nuvoprojects.cachezero1.menugui.SaveTextMenu;

public class MainWindow extends JFrame implements ActionListener {

    // Settings
    private static SaveHandler saveHandler;
    private static LanguageHandler languageHandler;

    private String cipherName;
    private final String cryptorName = "cryptor";
    private final String tartarusName = "tartarus";

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

    // File path seperator
    String filePathSep = FileSystems.getDefault().getSeparator();
    

    public MainWindow(String version, SaveHandler handler, LanguageHandler langHandler) {

        // Globals
        saveHandler = handler;
        languageHandler = langHandler;

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
            JOptionPane.showMessageDialog(null, langHandler.getLangMap(handler.getDataMap().get(handler.langName)).get(langHandler.graErr), langHandler.getLangMap(handler.getDataMap().get(handler.langName)).get(langHandler.minErr), JOptionPane.ERROR_MESSAGE);
        }

        // Instances
        cryptorPanel = new CryptorPanel(handler);
        tartarusPanel = new TartarusPanel();

        // Populate GUI
        createJMenu();
        createPositioningComponents();
        createFunctionalComponents();
        addGUIComponents();
        applyFont();

    }


    // Create menu
    private void createJMenu() {

        // Main Menus
        menuBar = new JMenuBar();

        cipherusMenu = new JMenu("Cipherus");
        settingsMenu = new JMenu(languageHandler.getLangMap(saveHandler.getDataMap().get(saveHandler.langName)).get(languageHandler.menSet));
        helpMenu = new JMenu(languageHandler.getLangMap(saveHandler.getDataMap().get(saveHandler.langName)).get(languageHandler.menHel));

        // Cipherus Menu
        saveTextMenuItem = new JMenuItem(languageHandler.getLangMap(saveHandler.getDataMap().get(saveHandler.langName)).get(languageHandler.menSavTex));
        saveImageMenuItem = new JMenuItem(languageHandler.getLangMap(saveHandler.getDataMap().get(saveHandler.langName)).get(languageHandler.menSavIma));
        aboutMenuItem = new JMenuItem(languageHandler.getLangMap(saveHandler.getDataMap().get(saveHandler.langName)).get(languageHandler.menAbo));
        exitMenuItem = new JMenuItem(languageHandler.getLangMap(saveHandler.getDataMap().get(saveHandler.langName)).get(languageHandler.menExi));
        
        // Settings Menu
        fontMenuItem = new JMenuItem(languageHandler.getLangMap(saveHandler.getDataMap().get(saveHandler.langName)).get(languageHandler.menFon));
        colourMenuItem = new JMenuItem(languageHandler.getLangMap(saveHandler.getDataMap().get(saveHandler.langName)).get(languageHandler.menCol));
        languageMenuItem = new JMenuItem(languageHandler.getLangMap(saveHandler.getDataMap().get(saveHandler.langName)).get(languageHandler.menLan));

        // Help
        encodersHelpMenuItem = new JMenuItem(languageHandler.getLangMap(saveHandler.getDataMap().get(saveHandler.langName)).get(languageHandler.menCip));
        applicationHelpMenuItem = new JMenuItem(languageHandler.getLangMap(saveHandler.getDataMap().get(saveHandler.langName)).get(languageHandler.menNav));


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


        // Addition & visibility
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

    // Apply fonts
    private void applyFont() {

        Font selectionFont = saveHandler.getFontMap().get(saveHandler.subtitleFontName);

        cryptorButton.setFont(selectionFont);
        tartarusButton.setFont(selectionFont);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        
        // ------- <Cipher selection> -------
        // Cryptor button
        if (e.getSource() == cryptorButton) {
            centerPanel.remove(centerPanel.getComponent(0));
            centerPanel.add(cryptorPanel, BorderLayout.CENTER);
            this.repaint();

            cipherName = cryptorName;
        }

        // Tartarus button
        if (e.getSource() == tartarusButton) {
            centerPanel.remove(centerPanel.getComponent(0));
            centerPanel.add(tartarusPanel, BorderLayout.CENTER);
            this.repaint();

            cipherName = tartarusName;
        }
        // ------- </Cipher selection> -------

        // ------- <Menu clicks> -------
        // Save text
        if (e.getSource() == saveTextMenuItem) {
            switch(cipherName) {
                case tartarusName:
                    break; //TODO: add tartarus

                case cryptorName:
                    saveTextMenu = new SaveTextMenu(this, Arrays.asList(cryptorPanel.normalTextArea.getText(), cryptorPanel.cipheredTextArea.getText()));
                    break;

                default:
                    break;
            }
        }

        // Save image
        if (e.getSource() == saveImageMenuItem) {
            new SaveImageMenu();
        }

        // About page
        if (e.getSource() == aboutMenuItem) {
            new AboutMenu(this);
        }

        // Font settings
        if (e.getSource() == fontMenuItem) {

            new FontMenu(this, saveHandler);
            applyFont();
            cryptorPanel.applyFonts();

        }

        // Colour settings
        if (e.getSource() == colourMenuItem) {

        }

        // Language settings
        if (e.getSource() == languageMenuItem) {
            
        }
        // ------- </Menu clicks> -------

    }
    
}
