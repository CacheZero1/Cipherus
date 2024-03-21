package euorg.nuvoprojects.cachezero1.gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.FileSystems;
import java.awt.BorderLayout;

//import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
//import javax.swing.JMenu; // TODO: add menu
//import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class MainWindow extends JFrame implements ActionListener {

    // Settings
    // private Font unitedFont;

    // Components (JMenu)
    // private JMenuBar menuBar;
    // private JMenu menu;

    // Components (Positioning)
    JPanel leftPanel;
    JPanel rightPanel;
    JPanel centerPanel;
    JScrollPane leftScrollPane;
    GridBagConstraints gbc;

    // Components (Functional)
    JButton cryptorButton;
    JButton tartarusButton;

    // Instances
    CryptorPanel cryptorPanel;
    TartarusPanel tartarusPanel;

    // File path seperator
    String filePathSep = FileSystems.getDefault().getSeparator();
    

    public MainWindow(String version, Font font, boolean darkMode) {

        // Globals
        //this.unitedFont = font; // TODO: add handle

        // Normal settings
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setMinimumSize(new Dimension(900, 600));
        this.setTitle("Cipherus - (" + version + ")");
        this.setLayout(new BorderLayout());

        // Settings with exception chance
        try {
            //this.setIconImage(ImageIO.read(getClass().getResource("/images/"))); // TODO: add icon
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, "A graphical error has occured", "Minor Exception", JOptionPane.ERROR_MESSAGE);
        }

        // Instances
        cryptorPanel = new CryptorPanel(font, darkMode);
        tartarusPanel = new TartarusPanel();

        // Populate GUI
        createMenu();
        createPositioningComponents();
        createFunctionalComponents();
        addGUIComponents();

    }


    private void createMenu() {}

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
        cryptorButton.setFocusable(false);
        cryptorButton.setPreferredSize(new Dimension(cryptorButton.getWidth(), 50));
        cryptorButton.addActionListener(this);

        tartarusButton = new JButton("Tartarus");
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


    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == cryptorButton) {

            centerPanel.remove(centerPanel.getComponent(0));
            centerPanel.add(cryptorPanel, BorderLayout.CENTER);
            this.repaint();

        }

        if (e.getSource() == tartarusButton) {

            centerPanel.remove(centerPanel.getComponent(0));
            centerPanel.add(tartarusPanel, BorderLayout.CENTER);
            this.repaint();

        }

    }
    
}
