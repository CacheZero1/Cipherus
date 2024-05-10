package euorg.nuvoprojects.cachezero1.menugui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class SaveTextMenu extends JOptionPane implements ActionListener {

    // Lang map
    private static ArrayList<String> stringMap;

    // Components (positioning)
    JPanel mainPanel;

    // Components (functional)
    JComboBox<String> comboBox;
    JTextArea textArea;
    JButton saveButton;

    // Text
    List<String> parsedText;


    public SaveTextMenu(Component parent, List<String> text, ArrayList<String> stringList) {

        stringMap = stringList;
        this.parsedText = text;

        switch (text.size()) {
            case 1:
                createMonoGUI();
                break;

            case 2:
                createDiGUI();
                break;
        
            default:
                break;
        }

        SaveTextMenu.showConfirmDialog(
            parent, 
            mainPanel, 
            stringList.get(0), 
            JOptionPane.CANCEL_OPTION,
            JOptionPane.PLAIN_MESSAGE
        );

    }

    private void createMonoGUI() {

        // Main panel
        mainPanel = new JPanel(new BorderLayout());

        // TextArea
        textArea = new JTextArea();
        textArea.setPreferredSize(new Dimension(150, 200));
        textArea.setText(parsedText.get(0));
        textArea.setEditable(false);
        textArea.setFocusable(false);

        // Save button
        saveButton = new JButton(stringMap.get(1));
        saveButton.addActionListener(this);

        // Add all
        mainPanel.add(textArea, BorderLayout.CENTER);
        mainPanel.add(saveButton, BorderLayout.SOUTH);

    };

    private void createDiGUI() {

        // Main panel
        mainPanel = new JPanel(new BorderLayout());

        // ComboBox
        comboBox = new JComboBox<String>(new String[]{stringMap.get(2), stringMap.get(3)});
        comboBox.addActionListener(this);

        // TextArea
        textArea = new JTextArea();
        textArea.setPreferredSize(new Dimension(150, 200));
        textArea.setText(parsedText.get(0));
        textArea.setEditable(false);
        textArea.setFocusable(false);

        // Save button
        saveButton = new JButton(stringMap.get(1));
        saveButton.addActionListener(this);

        // Add all
        mainPanel.add(comboBox, BorderLayout.NORTH);
        mainPanel.add(textArea, BorderLayout.CENTER);
        mainPanel.add(saveButton, BorderLayout.SOUTH);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == saveButton) {

            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File("."));

            if (fileChooser.showSaveDialog(mainPanel) == JFileChooser.APPROVE_OPTION) {

                PrintWriter writer = null;
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath() + ".txt");

                try {

                    writer = new PrintWriter(file);
                    writer.println(textArea.getText());
                    
                } catch (Exception writeException) {
                    
                    JOptionPane.showMessageDialog(null, stringMap.get(4), stringMap.get(5), JOptionPane.ERROR_MESSAGE);

                } finally {

                    writer.close();

                }

            }

        }

        if (e.getSource() == comboBox) {

            textArea.setEditable(true);

            if (comboBox.getSelectedItem().equals(stringMap.get(2))) {

                textArea.setText(parsedText.get(0));

            } else if (comboBox.getSelectedItem().equals(stringMap.get(3))) {

                textArea.setText(parsedText.get(1));
                
            }

        }

    };
    
}
