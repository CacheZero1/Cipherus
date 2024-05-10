package euorg.nuvoprojects.cachezero1.menugui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class EncoderHelpMenu extends JOptionPane implements ActionListener {

    // Main panel
    private JPanel mainPanel = new JPanel();
    private String langString;

    // Center panel
    private GridBagConstraints gbc = new GridBagConstraints();
    private JPanel leftPanel = new JPanel();
    private JScrollPane leftScrollPane = new JScrollPane(leftPanel);

    // Button
    private JButton cryptorButton = new JButton("Cryptor");
    private JButton tartarusButton = new JButton("Tartarus");
    
    // Constructor
    public EncoderHelpMenu(Component parent, ArrayList<String> stringList) {

        langString = stringList.get(0);

        setup();

        LanguageMenu.showOptionDialog(
            parent, 
            mainPanel, 
            stringList.get(1), 
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.PLAIN_MESSAGE,
            null,
            new String[]{stringList.get(2)},
            0
        );

    }
    
    private void setup() {

        // Cryptor activator
        
        cryptorButton.setFocusable(false);
        cryptorButton.setPreferredSize(new Dimension(cryptorButton.getWidth(), 50));
        cryptorButton.addActionListener(this);

        // Tartarus activator
        tartarusButton.setFocusable(false);
        tartarusButton.setPreferredSize(new Dimension(tartarusButton.getWidth(), 50));
        tartarusButton.addActionListener(this);

        // Constraints
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = GridBagConstraints.REMAINDER;

        // Panel
        leftPanel.setLayout(new GridBagLayout());
        
        leftScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        leftScrollPane.setPreferredSize(new Dimension(150, 300));

        // Addition
        leftPanel.add(cryptorButton, gbc);
        leftPanel.add(tartarusButton, gbc);

        mainPanel.add(leftScrollPane);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cryptorButton) {
            try {
                java.awt.Desktop.getDesktop().browse(new URI("https://github.com/CacheZero1/Cipherus/blob/master/assets/cryptor_docu_" + langString + ".pdf"));
            } catch (Exception error) {
                
            }
        }
    }

}
