package com.vortexel.cwdlauncher;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;


public class MainUI extends JFrame {

    private static final String TITLE = "CWD Launcher";
    private static final Dimension SIZE = new Dimension(200, 150);

    private JComboBox<LaunchConfig> configSelector;
    private JTextField workingDirField;
    private JButton chooseDirButton;
    private JButton startButton;

    public MainUI() {
        setupUI();
    }

    public void setLaunchConfigs(List<LaunchConfig> configs) {
        ComboBoxModel<LaunchConfig> model =
                new DefaultComboBoxModel<>(new Vector<>(configs));
        configSelector.setModel(model);
    }

    private ActionListener chooseDirButtonHandler = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent event) {
            // Build the dialog
            File currentPath = new File(workingDirField.getText());
            JFileChooser jfc = new JFileChooser(currentPath);
            jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

            // Show the dialog and handle the result
            int result = jfc.showOpenDialog(MainUI.this);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selected = jfc.getSelectedFile();
                workingDirField.setText(selected.getAbsolutePath());
            }
        }
    };

    private ActionListener startButtonHandler = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent event) {
            LaunchConfig launch =
                    (LaunchConfig)configSelector.getSelectedItem();
            if (launch == null) {
                new ErrorFrame("Please select launch configuration")
                        .setVisible(true);
            }
            try {
                launch.launch(new File(workingDirField.getText()));
            } catch (IOException e) {
                new ErrorFrame(e).setVisible(true);
            }
        }
    };

    private void setupUI() {
        setTitle(TITLE);
        setSize(SIZE);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel contentPane = new JPanel(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;

        configSelector = new JComboBox<>();
        contentPane.add(configSelector, gbc);

        workingDirField = new JTextField(20);
        workingDirField.setText(new File(".").getAbsolutePath());
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        contentPane.add(workingDirField, gbc);

        chooseDirButton = new JButton("Choose Directory");
        chooseDirButton.addActionListener(chooseDirButtonHandler);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 0.1;
        contentPane.add(chooseDirButton, gbc);

        startButton = new JButton("Start");
        startButton.addActionListener(startButtonHandler);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 1;
        gbc.gridwidth = 2;
        contentPane.add(startButton, gbc);

        setContentPane(contentPane);
        pack();
    }
}
