package com.vortexel.cwdlauncher;

import javax.swing.*;
import java.awt.*;


public class ErrorFrame extends JFrame {

    private JTextArea jtaMessage;
    private JTextArea jtaDetails;

    public ErrorFrame() {
        setupUI();
    }

    public ErrorFrame(Exception exception) {
        setupUI();
        setTexts(exception);
        pack();
    }

    public ErrorFrame(String message) {
        setupUI();
        setTexts(message, "");
        pack();
    }

    private void setTexts(String message, String details) {
        jtaMessage.setText(message);
        // ignore details for now
    }

    private void setTexts(Exception exception) {
        // ignore details as well
        setTexts(exception.getMessage(), "");
    }

    private void setupUI() {
        setTitle("Error");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        jtaMessage = new JTextArea();
        jtaMessage.setForeground(Color.RED);
        getContentPane().add(jtaMessage, BorderLayout.NORTH);
    }
}
