package io.github.slemke.methodextractor.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * The ClassificationPanel class implements the UI to classify code.
 *
 * @author Sascha Lemke
 * @version 0.1
 */
public class ClassificationPanel extends JPanel {

    /**
     * The label that displays the progress (x of y methods).
     */
    private JLabel counterLabel;

    /**
     * The button that classifies the code as "clean".
     */
    private JButton cleanButton;

    /**
     * The button that classifies the code as "not clean".
     */
    private JButton notCleanButton;

    /**
     * The button that allows to skip the classification for the currently selected method.
     */
    private JButton skipButton;

    public ClassificationPanel() {
        super();

        setLayout(new FlowLayout(FlowLayout.LEFT));
        counterLabel = new JLabel();
        cleanButton = new JButton("Clean");
        notCleanButton = new JButton("Not clean");
        skipButton = new JButton("Skip");

        add(this.cleanButton);
        add(notCleanButton);
        add(skipButton);
        add(this.counterLabel);
    }

    /**
     * Updates the "counter" label.
     *
     * @param current The index of the currently selected Method
     * @param max The number of all methods
     */
    public void updateCounter(int current, int max) {
        this.counterLabel.setText("Methode " + (current + 1) + " von " + (max + 1));
    }

    /**
     * Sets the action for the "clean" button.
     * @param listener The listener for the "clean" button
     */
    public void setCleanButtonAction(ActionListener listener) {
        cleanButton.addActionListener(listener);
    }

    /**
     * Sets the action for the "not clean" button.
     * @param listener The listener for the "not clean" button
     */
    public void setUncleanButtonAction(ActionListener listener) {
        notCleanButton.addActionListener(listener);
    }

    /**
     * Sets the action for the "skip" button.
     * @param listener The listener for the "skip" button.
     */
    public void setSkipButtonAction(ActionListener listener) {
        skipButton.addActionListener(listener);
    }
}
