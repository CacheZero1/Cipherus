package euorg.nuvoprojects.cachezero1.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.FileSystems;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import euorg.nuvoprojects.cachezero1.SaveHandler;
import euorg.nuvoprojects.cachezero1.ciphers.cryptor.Cryptor;

public class CryptorPanel extends JPanel implements ActionListener {

    // Settings
    private static SaveHandler saveHandler;

    // Components (Positioning)
    JPanel topPanel;
    JPanel centerPanel;
    JPanel bottomPanel;

    JPanel preciseTL;
    JPanel preciseTR;
    JPanel preciseBL;
    JPanel preicseBR;

    JPanel leftCenterPanel;
    JPanel rightCenterPanel;

    // Components (Functional)
    private JLabel formulaLabel;
    private JLabel normalTextLabel;
    private JLabel cipheredTextLabel;

    private JTextField formulaField;
    JTextArea normalTextArea;
    JTextArea cipheredTextArea;
    private JButton startButton;

    private JRadioButton encryptRadioButton;
    private JRadioButton decryptRadioButton;
    private ButtonGroup radioButtonOptionGroup;

    // File path seperator
    String filePathSep = FileSystems.getDefault().getSeparator();


    public CryptorPanel(SaveHandler handler) {

        // Settings
        this.setLayout(new BorderLayout());
        saveHandler = handler;

        // Populate Panel
        createPositioningComponents();
        try {
            createFunctionalComponents();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "A graphical error has occured", "Minor Exception", JOptionPane.ERROR_MESSAGE);
        }
        applyFonts();
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
        preicseBR.setLayout(new BorderLayout());


        // CENTER
        centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(1, 2, 10, 10));
        centerPanel.setBorder(BorderFactory.createMatteBorder(2, 0, 2, 0, Color.BLACK));

        leftCenterPanel = new JPanel();
        leftCenterPanel.setLayout(new BorderLayout(10, 10));
        leftCenterPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.BLACK));

        rightCenterPanel = new JPanel();
        rightCenterPanel.setLayout(new BorderLayout(10, 10));
        rightCenterPanel.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, Color.BLACK));


        // SOUTH
        bottomPanel = new JPanel();
        bottomPanel.setPreferredSize(new Dimension(bottomPanel.getWidth(), 70));
        bottomPanel.setLayout(new BorderLayout());

    }

    // Functionals
    private void createFunctionalComponents() throws IOException {

        // JLabels
        formulaLabel = new JLabel("Formula:");
        formulaLabel.setFocusable(false);
        formulaLabel.setHorizontalAlignment(JLabel.RIGHT);

        normalTextLabel = new JLabel("Normal Text:");
        normalTextLabel.setFocusable(false);

        cipheredTextLabel = new JLabel("Encrypted Text:");
        cipheredTextLabel.setFocusable(false);

        // Main components
        formulaField = new JTextField();
        formulaField.setHorizontalAlignment(JTextField.LEFT);

        normalTextArea = new JTextArea();
        normalTextArea.setLineWrap(true);

        cipheredTextArea = new JTextArea();
        cipheredTextArea.setLineWrap(true);

        startButton = new JButton("Start");
        startButton.setHorizontalAlignment(JButton.CENTER);
        startButton.addActionListener(this);

        // Action selectors
        encryptRadioButton = new JRadioButton("Encrypt");
        encryptRadioButton.setIcon(new ImageIcon(this.getClass().getResource("/images/encrypt_b.png")));
        encryptRadioButton.setHorizontalAlignment(JRadioButton.CENTER);
        encryptRadioButton.setFocusPainted(false);
        encryptRadioButton.addActionListener(this);
        encryptRadioButton.setSelected(true);

        decryptRadioButton = new JRadioButton("Decrypt");
        decryptRadioButton.setIcon(new ImageIcon(this.getClass().getResource("/images/decrypt_b.png")));
        decryptRadioButton.setHorizontalAlignment(JRadioButton.CENTER);
        decryptRadioButton.setFocusPainted(false);
        decryptRadioButton.addActionListener(this);

        radioButtonOptionGroup = new ButtonGroup();
        radioButtonOptionGroup.add(encryptRadioButton);
        radioButtonOptionGroup.add(decryptRadioButton);

    }

    // Font setting
    public void applyFonts() {

        // Title
        encryptRadioButton.setFont(saveHandler.getFontMap().get(saveHandler.titleFontName));
        decryptRadioButton.setFont(saveHandler.getFontMap().get(saveHandler.titleFontName));
        startButton.setFont(saveHandler.getFontMap().get(saveHandler.titleFontName));

        // Subtitle
        formulaLabel.setFont(saveHandler.getFontMap().get(saveHandler.subtitleFontName));
        normalTextLabel.setFont(saveHandler.getFontMap().get(saveHandler.subtitleFontName));
        cipheredTextLabel.setFont(saveHandler.getFontMap().get(saveHandler.subtitleFontName));

        // Input
        formulaField.setFont(saveHandler.getFontMap().get(saveHandler.inputFontName));
        normalTextArea.setFont(saveHandler.getFontMap().get(saveHandler.inputFontName));
        cipheredTextArea.setFont(saveHandler.getFontMap().get(saveHandler.inputFontName));

    }

    // Addition
    private void addGUIComponents() {

        // NORTH
        preciseTL.add(encryptRadioButton, BorderLayout.CENTER);
        preciseTR.add(decryptRadioButton, BorderLayout.CENTER);
        preciseBL.add(formulaLabel, BorderLayout.CENTER);
        preicseBR.add(formulaField, BorderLayout.CENTER);

        topPanel.add(preciseTL);
        topPanel.add(preciseTR);
        topPanel.add(preciseBL);
        topPanel.add(preicseBR);


        // Center
        leftCenterPanel.add(normalTextLabel, BorderLayout.NORTH);
        leftCenterPanel.add(normalTextArea, BorderLayout.CENTER);

        rightCenterPanel.add(cipheredTextLabel, BorderLayout.NORTH);
        rightCenterPanel.add(cipheredTextArea, BorderLayout.CENTER);

        centerPanel.add(leftCenterPanel);
        centerPanel.add(rightCenterPanel);


        // SOUTH
        bottomPanel.add(startButton, BorderLayout.CENTER);


        // Add to this
        this.add(topPanel, BorderLayout.NORTH);
        this.add(centerPanel, BorderLayout.CENTER);
        this.add(bottomPanel, BorderLayout.SOUTH);

    }


    @Override
    public void actionPerformed(ActionEvent e) {

        // Encrypt label swap
        if (e.getSource() == encryptRadioButton) {

            preciseTL.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2, true));
            preciseTR.setBorder(null);

            normalTextLabel.setText("Normal Text:");
            cipheredTextLabel.setText("Encrypted Text:");

        }

        // Decrypt label swap
        if (e.getSource() == decryptRadioButton) {

            preciseTL.setBorder(null);
            preciseTR.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2, true));

            normalTextLabel.setText("Encrypted Text:");
            cipheredTextLabel.setText("Normal Text:");

        }

        // Start process
        if (e.getSource() == startButton) {

            // Encrypt
            if (encryptRadioButton.isSelected()) {

                try {

                    Cryptor cryptor = new Cryptor();

                    String encrypted = cryptor.encrypt(normalTextArea.getText(), cryptor.algorithm(formulaField.getText()));
                    cipheredTextArea.setText(encrypted);
                    
                } catch (Exception encryptionError) {
                    encryptionError.printStackTrace();
                    JOptionPane.showMessageDialog(null, "A calculation error has occured", "Minor Exception", JOptionPane.ERROR_MESSAGE);

                }
                
            // Decrypt
            } else if (decryptRadioButton.isSelected()) {

                try {

                    Cryptor cryptor = new Cryptor();

                    String decrypted = cryptor.decrypt(normalTextArea.getText(), cryptor.algorithm(formulaField.getText()));
                    cipheredTextArea.setText(decrypted);

                } catch (Exception decryptionError) {
                    decryptionError.printStackTrace();
                    JOptionPane.showMessageDialog(null, "A calculation error has occured", "Minor Exception", JOptionPane.ERROR_MESSAGE);

                }

            }

        }
    }
    
}
