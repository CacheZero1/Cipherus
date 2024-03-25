package euorg.nuvoprojects.cachezero1.gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.FileSystems;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
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

import euorg.nuvoprojects.cachezero1.SaveHandler;
import euorg.nuvoprojects.cachezero1.menugui.AboutMenu;
import euorg.nuvoprojects.cachezero1.menugui.FontMenu;
import euorg.nuvoprojects.cachezero1.menugui.SaveImageMenu;
import euorg.nuvoprojects.cachezero1.menugui.SaveTextMenu;

public class MainWindow extends JFrame implements ActionListener {

    // Settings
    private static SaveHandler saveHandler;
    private static Font selectionFont;

    private static HashMap<String, Font> mappedFonts;

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
    

    public MainWindow(String version, HashMap<String, Font> fontMap, boolean darkMode, SaveHandler handler) {

        // Globals
        saveHandler = handler;

        selectionFont = fontMap.get("selection");
        mappedFonts = fontMap;

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
            JOptionPane.showMessageDialog(null, "A graphical error has occured", "Minor Exception", JOptionPane.ERROR_MESSAGE);
        }

        // Instances
        cryptorPanel = new CryptorPanel(fontMap, darkMode);
        tartarusPanel = new TartarusPanel();

        // Populate GUI
        createMenu();
        createPositioningComponents();
        createFunctionalComponents();
        addGUIComponents();

        System.out.println();
        System.out.println();

    }


    private void createMenu() {

        // Main Menus
        menuBar = new JMenuBar();

        cipherusMenu = new JMenu("Cipherus");
        settingsMenu = new JMenu("Settings");
        helpMenu = new JMenu("Help");

        // Cipherus Menu
        saveTextMenuItem = new JMenuItem("Save Text");
        saveImageMenuItem = new JMenuItem("Save Image");
        aboutMenuItem = new JMenuItem("About");
        exitMenuItem = new JMenuItem("Exit");
        
        // Settings Menu
        fontMenuItem = new JMenuItem("Font");
        colourMenuItem = new JMenuItem("Colour");
        languageMenuItem = new JMenuItem("Language");

        // Help
        encodersHelpMenuItem = new JMenuItem("Ciphers");
        applicationHelpMenuItem = new JMenuItem("Navigation");


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

    private void createFunctionalComponents() {

        cryptorButton = new JButton("Cryptor");
        cryptorButton.setFont(selectionFont);
        cryptorButton.setFocusable(false);
        cryptorButton.setPreferredSize(new Dimension(cryptorButton.getWidth(), 50));
        cryptorButton.addActionListener(this);

        tartarusButton = new JButton("Tartarus");
        tartarusButton.setFont(selectionFont);
        tartarusButton.setFocusable(false);
        tartarusButton.setPreferredSize(new Dimension(tartarusButton.getWidth(), 50));
        tartarusButton.addActionListener(this);

    }

    private void addGUIComponents() {

        leftPanel.add(cryptorButton, gbc);
        leftPanel.add(tartarusButton, gbc);

        centerPanel.add(cryptorPanel, BorderLayout.CENTER);

        this.add(rightPanel, BorderLayout.EAST);
        this.add(leftScrollPane, BorderLayout.WEST);
        this.add(centerPanel, BorderLayout.CENTER);

    }

    private void applyChanges(LinkedList<Font> fontList) {

        cryptorButton.setFont(fontList.get(2));
        tartarusButton.setFont(fontList.get(2));

        cryptorPanel.setNewFonts(fontList.get(0), fontList.get(1), fontList.get(3));

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        
        // Cipher selection

        if (e.getSource() == cryptorButton) {

            centerPanel.remove(centerPanel.getComponent(0));
            centerPanel.add(cryptorPanel, BorderLayout.CENTER);
            this.repaint();

            cipherName = cryptorName;

        }

        if (e.getSource() == tartarusButton) {

            centerPanel.remove(centerPanel.getComponent(0));
            centerPanel.add(tartarusPanel, BorderLayout.CENTER);
            this.repaint();

            cipherName = tartarusName;

        }

        // Menu clicks

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
        if (e.getSource() == saveImageMenuItem) {
            new SaveImageMenu();
        }
        if (e.getSource() == aboutMenuItem) {
            new AboutMenu(this);
        }

        if (e.getSource() == fontMenuItem) {

            FontMenu fontMenu = new FontMenu();
            LinkedList<Object> returnedList = fontMenu.start(this, mappedFonts);

            if (((Integer) returnedList.get(0)) == JOptionPane.OK_OPTION) {
                LinkedList<Font> fontList = new LinkedList<Font>();
                fontList.add((Font) returnedList.get(1));
                fontList.add((Font) returnedList.get(2));
                fontList.add((Font) returnedList.get(3));
                fontList.add((Font) returnedList.get(4));
                applyChanges(fontList);


            }

        }
        if (e.getSource() == colourMenuItem) {

        }
        if (e.getSource() == languageMenuItem) {
            
        }

    }
    
}
