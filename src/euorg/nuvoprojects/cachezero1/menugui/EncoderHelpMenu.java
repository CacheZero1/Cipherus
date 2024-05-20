package euorg.nuvoprojects.cachezero1.menugui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.plaf.basic.BasicScrollBarUI;

import euorg.nuvoprojects.cachezero1.Utility;

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

    private JButton closeButton;
    
    // Constructor
    public EncoderHelpMenu(Component parent, Boolean isDarkMode, ArrayList<String> stringList) {

        langString = stringList.get(0);

        setup(isDarkMode, stringList);

        this.createDialog(parent, stringList.get(1)).setVisible(true);

    }
    
    private void setup(Boolean isDarkmode, ArrayList<String> stringMap) {

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

        // Button
        closeButton = new JButton(stringMap.get(2));
        closeButton.setFocusable(false);
        closeButton.addActionListener(event -> {
            this.setValue(JOptionPane.CANCEL_OPTION);
        });

        // Dark mode
        applyTheme(isDarkmode);

        // Addition
        leftPanel.add(cryptorButton, gbc);
        leftPanel.add(tartarusButton, gbc);

        mainPanel.add(leftScrollPane);

        this.setMessage(mainPanel);
        this.setOptions(new Object[]{closeButton});

        if (isDarkmode) {
            ((JButton) this.getOptions()[0]).getParent().setBackground(Utility.optionBackgroundDark);
        }

    }

    // Dark mode
    public void applyTheme(Boolean isDarkMode) {
        if (isDarkMode) {
            // ------- <Set backgrounds> -------
            // General & Panels
            mainPanel.setBackground(Utility.optionBackgroundDark);
            this.setBackground(Utility.optionBackgroundDark);
            leftPanel.setBackground(Utility.optionBackgroundDark);

            leftScrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK));
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
            leftScrollPane.getVerticalScrollBar().getComponent(0).getParent().setBackground(Utility.backgroundDark); // Down button
            leftScrollPane.getVerticalScrollBar().getComponent(1).getParent().setBackground(Utility.backgroundDark); // Up button
            
            // Main Buttons
            cryptorButton.setBackground(Utility.buttonDark);
            tartarusButton.setBackground(Utility.buttonDark);

            // Buttons
            closeButton.setBackground(Utility.buttonDark);
            closeButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            // ------- </Set backgrounds> -------

            // ------- <Set text colour> -------
            // Buttons
            cryptorButton.setForeground(Utility.textColourDarkmode);
            tartarusButton.setForeground(Utility.textColourDarkmode);

            closeButton.setForeground(Utility.textColourDarkmode);
            // ------- </Set text colour> -------
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cryptorButton) {
            try {
                java.awt.Desktop.getDesktop().browse(new URI("https://github.com/CacheZero1/Cipherus/blob/master/assets/cryptor_docu_" + langString + ".pdf"));
            } catch (Exception error) {}
        }
        if (e.getSource() == tartarusButton) {
            try {
                java.awt.Desktop.getDesktop().browse(new URI("https://github.com/CacheZero1/Cipherus/blob/master/assets/tartarus_docu_" + langString + ".pdf"));
            } catch (Exception error) {}
        }
    }

}
