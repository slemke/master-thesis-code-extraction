package io.github.slemke.methodextractor.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ClassificationPanel extends JPanel {

    private JLabel counterLabel;

    private JButton cleanButton;

    private JButton notCleanButton;

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

    public void updateCounter(int current, int max) {
        this.counterLabel.setText("Methode " + (current + 1) + " von " + (max + 1));
    }

    public void setCleanButtonAction(ActionListener listener) {
        cleanButton.addActionListener(listener);
    }

    public void setUncleanButtonAction(ActionListener listener) {
        notCleanButton.addActionListener(listener);
    }

    public void setSkipButtonAction(ActionListener listener) {
        skipButton.addActionListener(listener);
    }
}
