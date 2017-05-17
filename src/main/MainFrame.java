package main;

import javax.swing.*;
import java.awt.*;

/**
 * Created by ochile on 13-May-17.
 *
 * @author ochile
 */
public class MainFrame extends JFrame {
    ControlPanel controlPanel;
    DesignPanel designPanel;

    public MainFrame() {
        super("Swing Designer");
        init();
    }

    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame();
    }

    private void init() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.getContentPane().setLayout(new FlowLayout(FlowLayout.LEFT));
        this.getContentPane().setPreferredSize(new Dimension(1000, 700)); //660, 651

        this.controlPanel = new ControlPanel(this);
        this.designPanel = new DesignPanel(this);
        this.add(this.controlPanel);
        this.add(this.designPanel);
        //...create and add to the frame the controlPanel and designPanel objects

        this.pack();
        this.setVisible(true);
    }
}
