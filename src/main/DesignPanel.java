package main;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * Created by ochile on 13-May-17.
 *
 * @author ochile
 */
class DesignPanel extends JPanel {
    private static final int W = 800;
    private static final int H = 600;
    private final MainFrame frame;

    DesignPanel(MainFrame frame) {
        this.frame = frame;
        setPreferredSize(new Dimension(W, H));
        setLayout(null);
    }

    private static int getW() {
        return W;
    }

    private static int getH() {
        return H;
    }

    void addAtRandomLocation(JComponent comp) {
        int x = new Random().nextInt(getW() - 100); //...create a random integer between 0 and W-1
        int y = new Random().nextInt(getH() - 100); //...create a random integer between 0 and H-1
        int w = comp.getPreferredSize().width;
        int h = comp.getPreferredSize().height;
        System.out.println("bounds: " + x + "," + y + "," + w + "," + h);
        comp.setBounds(x, y, w, h);
        comp.setToolTipText(comp.getClass().getName());
        this.add(comp);
        frame.repaint();
    }
}
