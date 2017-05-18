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
class ControlPanel extends JPanel {
    private final MainFrame frame;
    private final JLabel classNameLabel = new JLabel("Class name");
    private final JTextField classNameField = new JTextField(30);
    private final JLabel textLabel = new JLabel("Default text");
    private final JTextField textField = new JTextField(10);
    private final JButton createButton = new JButton("Add component");

    ControlPanel(MainFrame frame) {
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
            if (comp != null){
                frame.designPanel.addAtRandomLocation(comp);
            } else {
                JOptionPane.showMessageDialog(frame, "Class does not exists, or is not supported", "Error occured", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private JComponent createDynamicComponent(String className, String text) {
        Class factory;
        try{
            factory = Class.forName("javax.swing." + className);
        } catch (ClassNotFoundException exc){
            return null;
        }

        JComponent comp = null;

        try {
            Constructor ctor = factory.getConstructor(new Class[] {String.class});
            comp = (JComponent) ctor.newInstance(text);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            try {
                comp = (JComponent) factory.newInstance();
            } catch (InstantiationException | IllegalAccessException ex) {
                ex.printStackTrace();
            }
        }

        if (comp != null){
            comp.setVisible(true);
            comp.setSize(100, 30);
            comp.setPreferredSize(new Dimension(100, 30));
        }
        return comp;

        /*
        try {
            Class factory = Class.forName("javax.swing." + className);
            Class[] signature = new Class[]{String.class};
            Constructor ctor = factory.getConstructor(signature);
            JComponent comp = (JComponent) ctor.newInstance(text);
            comp.setVisible(true);
            comp.setSize(100, 30);
            comp.setPreferredSize(new Dimension(100, 30));
            return comp;
        }
        try {
            Class factory = Class.forName("javax.swing." + className);
            Class[] signature = new Class[]{String.class};
            Constructor ctor = factory.getConstructor(signature);
            JComponent comp = (JComponent) ctor.newInstance(text);
            comp.setVisible(true);
            comp.setSize(100, 30);
            comp.setPreferredSize(new Dimension(100, 30));
            return comp;
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            Class factory = null;
            JComponent comp = null;
            try {
                factory = Class.forName("javax.swing." + className);
                comp = (JComponent) factory.newInstance();
                comp.setVisible(true);
                comp.setSize(100, 30);
                comp.setPreferredSize(new Dimension(100, 30));
            } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e1) {
                e1.printStackTrace();
            }
            return comp;
        }
        return null;
        catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }*/
    }
}