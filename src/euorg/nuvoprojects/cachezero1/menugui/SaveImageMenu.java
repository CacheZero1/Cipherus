package euorg.nuvoprojects.cachezero1.menugui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class SaveImageMenu extends JOptionPane implements ActionListener {

    // Lang map
    private static ArrayList<String> stringMap;

    // Components (positioning)
    JPanel mainPanel;

    // Components (functional)
    JLabel imagePreviewLabel;
    JButton saveButton;

    // Text
    BufferedImage parsedImage;


    public SaveImageMenu(Component parent, BufferedImage enImage, ArrayList<String> stringList) {

        stringMap = stringList;
        this.parsedImage = enImage;

        createGUI();

        SaveTextMenu.showConfirmDialog(
            parent, 
            mainPanel, 
            stringList.get(0), 
            JOptionPane.CANCEL_OPTION,
            JOptionPane.PLAIN_MESSAGE
        );

    }

    private void createGUI() {

        // Main panel
        mainPanel = new JPanel(new BorderLayout());

        // ComboBox
        imagePreviewLabel = new JLabel(new ImageIcon(parsedImage));
        imagePreviewLabel.setFocusable(true);

        // Save button
        saveButton = new JButton(stringMap.get(1));
        saveButton.addActionListener(this);

        // Add all
        mainPanel.add(imagePreviewLabel, BorderLayout.CENTER);
        mainPanel.add(saveButton, BorderLayout.SOUTH);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == saveButton) {

            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File("."));

            if (fileChooser.showSaveDialog(mainPanel) == JFileChooser.APPROVE_OPTION) {

                File file = new File(fileChooser.getSelectedFile().getAbsolutePath() + ".png");

                try {

                    ImageIO.write(parsedImage, "png", file);
                    
                } catch (Exception writeException) {
                    
                    JOptionPane.showMessageDialog(null, stringMap.get(2), stringMap.get(3), JOptionPane.ERROR_MESSAGE);

                }
            }
        }
    };
    
}
