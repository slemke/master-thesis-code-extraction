package io.github.slemke.methodextractor.gui;

import io.github.slemke.methodextractor.exceptions.UserInterfaceException;

import javax.swing.*;
import java.awt.event.ActionListener;

public class UserInterface extends JFrame {

    private JTextArea feature;

    private ClassificationPanel classificationPanel;

    public UserInterface() throws UserInterfaceException {
        setLookAndFeel();
        setTitle("Code Classification");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        this.feature = new JTextArea(30, 20);
        JScrollPane scrollPane = new JScrollPane(this.feature);
        this.classificationPanel = new ClassificationPanel();

        add(classificationPanel);
        add(scrollPane);

        setSize(1200, 400);
        setVisible(true);
    }

    private void setLookAndFeel() throws UserInterfaceException {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            throw new UserInterfaceException("Unable to initialize user interface", e);
        }
    }

    public void setCleanCodeAction(ActionListener listener) {
        classificationPanel.setCleanButtonAction(listener);
    }

    public void setUncleanCodeAction(ActionListener listener) {
        classificationPanel.setUncleanButtonAction(listener);
    }

    public void setSkipAction(ActionListener listener) {
        classificationPanel.setSkipButtonAction(listener);
    }

    public void updateFeature(String method) {
        this.feature.setText(method);
    }

    public void updateCounter(int current, int max) {
        this.classificationPanel.updateCounter(current, max);
    }
}
