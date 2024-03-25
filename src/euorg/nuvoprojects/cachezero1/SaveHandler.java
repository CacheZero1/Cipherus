package euorg.nuvoprojects.cachezero1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Properties;


import javax.swing.JOptionPane;

public class SaveHandler {

    private final String savePath;
    private File saveFile;
    private Properties properties;
    
    public SaveHandler(String desiredPath) {

        this.savePath = desiredPath + "/cipherusSave.xml";
        saveFile = new File(savePath);

    }

    public boolean saveExists() {
        return saveFile.exists();
    }

    public void createSaveFile() {
        try {
            saveFile.createNewFile();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "An error occured, whilst trying to write a file", "Major Exception", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void writeXML(HashMap<String, String> entryMap) {

        try {

            properties = new Properties();

            for (String key : entryMap.keySet()) {

                properties.setProperty(key, entryMap.get(key));
                OutputStream outputStream = new FileOutputStream(savePath);
                properties.storeToXML(outputStream, null);

            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "An error occured, whilst trying to write a file", "Major Exception", JOptionPane.ERROR_MESSAGE);
        }

    }

    public HashMap<String, String> readXML() {

        HashMap<String, String> map = new HashMap<>();

        try {

            properties = new Properties();
            InputStream inputStream = new FileInputStream(savePath);
            properties.loadFromXML(inputStream);

            properties.forEach((key, value) -> {
                map.put(String.valueOf(key), String.valueOf(value));
            });
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "An error occured, whilst trying to read a file", "Major Exception", JOptionPane.ERROR_MESSAGE);
            properties = new Properties();
        }

        return map;

    }

}
