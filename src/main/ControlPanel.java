package main;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by ochile on 13-May-17.
 *
 * @author ochile
 */
public class ControlPanel extends JPanel {
    private final MainFrame frame;
    private final JLabel classNameLabel = new JLabel("Class name");
    private final JTextField classNameField = new JTextField(30);
    private final JLabel textLabel = new JLabel("Default text");
    private final JTextField textField = new JTextField(10);
    private final JButton createButton = new JButton("Add component");

    public ControlPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    private void init() {
        add(classNameLabel);
        add(classNameField);
        add(textLabel);
        add(textField);
        add(createButton);
        createButton.addActionListener(e -> {
            JComponent comp = createDynamicComponent(classNameField.getText(), textField.getText());
            frame.designPanel.addAtRandomLocation(comp);
        });
    }

    private JComponent createDynamicComponent(String className, String text) {
        try {
            Class factory = Class.forName("javax.swing." + className);
            Class[] signature = new Class[]{String.class};
            Constructor ctor = factory.getConstructor(signature);
            JComponent comp = (JComponent) ctor.newInstance(text);
            comp.setVisible(true);
            comp.setSize(100, 30);
            comp.setPreferredSize(new Dimension(100, 30));
            return comp;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            Class factory = null;
            try {
                factory = Class.forName("javax.swing." + className);
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            }
            JComponent comp = null;
            try {
                comp = (JComponent) factory.newInstance();
            } catch (InstantiationException e1) {
                e1.printStackTrace();
            } catch (IllegalAccessException e1) {
                e1.printStackTrace();
            }
            comp.setVisible(true);
            comp.setSize(100, 30);
            comp.setPreferredSize(new Dimension(100, 30));
            return comp;
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}