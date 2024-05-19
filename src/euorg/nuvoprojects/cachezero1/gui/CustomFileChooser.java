package euorg.nuvoprojects.cachezero1.gui;

import java.awt.Component;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JList;

import euorg.nuvoprojects.cachezero1.Utility;

public class CustomFileChooser extends JFileChooser {

    public CustomFileChooser() {
        Component[] comps = getComponents();
        recursiveTransparent(comps);
    }

    private void recursiveTransparent(Component[] comps) {
        for (Component comp : comps) {
            if (comp instanceof JComponent && !(comp instanceof JList)) {
                ((JComponent) comp).setOpaque(false);
            }
            if (comp instanceof Container) {
                Component[] subComps = ((Container) comp).getComponents();
                recursiveTransparent(subComps);
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setPaint(Utility.optionBackgroundDark);
        g2.fillRect(0, 0, getWidth(), getHeight());
    }
}
