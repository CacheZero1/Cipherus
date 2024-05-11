package euorg.nuvoprojects.cachezero1.ciphers.tartarus;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import euorg.nuvoprojects.cachezero1.Utility;

public class Tartarus {

    private TartarusASCII asciiClass;

    public Tartarus() {

        asciiClass = new TartarusASCII();

    }

    public BufferedImage createImage(String text, String colourscheme) {

        text = text.toUpperCase();

        // Mandatory
        BufferedImage image = new BufferedImage(256, 144, BufferedImage.TYPE_INT_RGB);
        int imgWidth = image.getWidth();
        List<Integer> codes = new ArrayList<Integer>();


        // Exit if too big
        if (text.length() > 36864) {
            throw new NullPointerException();
        }


        // Turn into char
        for (char stringPos : text.toCharArray()) {

            codes.add(asciiClass.translateToASCII(stringPos));

        }

        // Create pixels
        for (int row = 0; row <= Math.floor(text.length() / imgWidth); row++) {

            for (int col = 0; col < imgWidth; col++) {

                if (codes.isEmpty()) {

                    return image;

                } else {

                    switch (colourscheme) {
                        case Utility.red:
                            image.setRGB(col, row, new Color(codes.get(0), 0, 0).getRGB());
                            break;

                        case Utility.green:
                            image.setRGB(col, row, new Color(0, codes.get(0), 0).getRGB());
                            break;
                    
                        default:
                            image.setRGB(col, row, new Color(0, 0, codes.get(0)).getRGB());
                            break;
                    }
                    
                    codes.remove(0);

                }
                
            }

        }

        return image;

    }
    
    public String readImage(BufferedImage bufferedImage, String colourscheme) {

        List<Integer> codeList = new ArrayList<Integer>();
        String translated = "";

        for (int i = 0; i < bufferedImage.getHeight(); i++) {

            for (int j = 0; j < bufferedImage.getWidth(); j++) {

                int blueValue;

                switch (colourscheme) {
                    case Utility.red:
                        blueValue = new Color(bufferedImage.getRGB(j, i)).getRed();
                        break;

                    case Utility.green:
                        blueValue = new Color(bufferedImage.getRGB(j, i)).getGreen();
                        break;
                
                    default:
                        blueValue = new Color(bufferedImage.getRGB(j, i)).getBlue();
                        break;
                }

                codeList.add(blueValue);
                
                translated += asciiClass.translateFromASCII(blueValue);
            }

        }

        return translated;

    }

}
