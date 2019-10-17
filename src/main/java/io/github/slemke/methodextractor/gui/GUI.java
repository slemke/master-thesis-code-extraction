package io.github.slemke.methodextractor.gui;

import io.github.slemke.methodextractor.exceptions.GUIException;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * The GUI class handles the UI for the Application class.
 * @author Sascha Lemke
 * @version 0.1
 */
public class GUI extends JFrame {

    /**
     * The textarea that is displaying the currently selected code.
     */
    private JTextArea code;

    /**
     * The panel that displays the classification options.
     */
    private ClassificationPanel classificationPanel;

    public GUI() throws GUIException {
        setLookAndFeel();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        this.code = new JTextArea(29, 20);
        JScrollPane scrollPane = new JScrollPane(this.code);
        this.classificationPanel = new ClassificationPanel();

        add(classificationPanel);
        add(scrollPane);

        setSize(800, 600);
        setVisible(true);
    }

    /**
     * Sets the "SystemLookAndFeel" for the application.
     * @throws GUIException Throws a GUIException when application is unable to use the "SystemLookAndFeel"
     */
    private void setLookAndFeel() throws GUIException {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            throw new GUIException("Unable to initialize GUI", e);
        }
    }

    /**
     * Sets the action for the "clean" code button on the ClassificationPanel.
     * @param listener The ActionListener
     */
    public void setCleanCodeAction(ActionListener listener) {
        classificationPanel.setCleanButtonAction(listener);
    }

    /**
     * Sets the action for the "not clean" code button on the ClassificationPanel.
     * @param listener The ActionListener
     */
    public void setUncleanCodeAction(ActionListener listener) {
        classificationPanel.setUncleanButtonAction(listener);
    }

    /**
     * Sets the action for the "skip" code button on the ClassificationPanel.
     * @param listener The ActionListener
     */
    public void setSkipAction(ActionListener listener) {
        classificationPanel.setSkipButtonAction(listener);
    }

    /**
     * Updates the code that is shown in the textarea.
     * @param method The new method to show
     */
    public void updateCode(String method) {
        this.code.setText(method);
    }

    /**
     * Updates the counter.
     * @param current The index of the currently selected method.
     * @param max The number of all methods
     */
    public void updateCounter(int current, int max) {
        this.classificationPanel.updateCounter(current, max);
    }
}
