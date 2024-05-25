package org.lbr.gui;

import org.lbr.GameEngine;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.*;

public class SaveDialog extends JFrame {
    private JPanel roundedPanel;
    private JComboBox<String> selectExtension;
    private JFileChooser jfk;

    public SaveDialog(GameEngine gameEngine, MainFrame mainFrame, MainWindow mainWindow) {
        this.jfk = new JFileChooser();
        this.setUndecorated(true);
        this.setSize(new Dimension(400, 300));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(mainWindow);
        this.setOpacity((float) 0.96);

        this.roundedPanel = new DialogPanel(300, 300, new Color(230, 230, 230), new GridBagLayout());
        add(roundedPanel);

        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.WEST;
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1.0;
        c.insets = new Insets(-50, 50, 20, 0);
        RoundedButton backButton = new RoundedButton("Back", new Insets(1, 12, 1, 12), 12, 8);
        backButton.addActionListener(e -> {
            mainFrame.setEnabled(true);
            dispose();
        });
        backButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                JButton curr =(JButton) e.getSource();
                curr.setBackground(Color.GRAY);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                JButton curr =(JButton) e.getSource();
                curr.setBackground(Color.LIGHT_GRAY);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                JButton curr =(JButton) e.getSource();
                curr.setBackground(Color.GRAY);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                JButton curr =(JButton) e.getSource();
                curr.setBackground(Color.LIGHT_GRAY);
            }
        });
        roundedPanel.add(backButton, c);

        c.gridx = 1;
        c.weightx = 3.0;
        c.insets = new Insets(0, 50, 20, 0);
        JLabel title = new JLabel();
        title.setFont(new Font("Linux Libertine", 1, 30));
        title.setText("Save");
        roundedPanel.add(title, c);

        c.insets = new Insets(0, 50, 0, 0);
        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 1.0;
        JLabel formatLabel = new JLabel();
        formatLabel.setText("Format");
        formatLabel.setFont(new Font("Linux Libertine", 1, 18));
        roundedPanel.add(formatLabel, c);

        c.gridx = 1;
        c.weightx = 4.0;
        c.insets = new Insets(0, 0, 0, 0);
        selectExtension = new JComboBox<>();
        selectExtension.setBackground(Color.LIGHT_GRAY);
        ArrayList<String> supportedExtensions = gameEngine.getSupportedExtension();
        String[] exts = new String[supportedExtensions.size()];
        exts = supportedExtensions.toArray(exts);
        selectExtension.setModel(new DefaultComboBoxModel<>(exts));
        selectExtension.setForeground(Color.BLACK);
        selectExtension.setFont(new Font("Linux Libertine", 1, 18));
        selectExtension.setPreferredSize(new Dimension(230, 30));
        selectExtension.setFocusable(false);
        roundedPanel.add(selectExtension, c);

        c.gridy = 2;
        c.gridx = 0;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.EAST;
        c.insets = new Insets(20, 0, 20, 49);
        RoundedButton chooseFileButton = new RoundedButton("Choose File", new Insets(1, 12, 1, 12), 12, 8);
        chooseFileButton.addActionListener(e -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception eee) {

            }
            jfk = new JFileChooser();
            jfk.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int returnVal = jfk.showOpenDialog(this);
            try {
                UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            } catch (Exception eee) {

            }
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                chooseFileButton.setText(jfk.getSelectedFile().getName());
            } else {
                chooseFileButton.setText("Choose File");
            }
        });
        chooseFileButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                JButton curr =(JButton) e.getSource();
                curr.setBackground(Color.GRAY);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                JButton curr =(JButton) e.getSource();
                curr.setBackground(Color.LIGHT_GRAY);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                JButton curr =(JButton) e.getSource();
                curr.setBackground(Color.GRAY);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                JButton curr =(JButton) e.getSource();
                curr.setBackground(Color.LIGHT_GRAY);
            }
        });

        roundedPanel.add(chooseFileButton, c);

        c.gridy = 3;
        c.anchor = GridBagConstraints.CENTER;
        c.gridwidth = 2;
        c.insets = new Insets(0, 0, 0, 0);
        RoundedButton submit = new RoundedButton("Save", new Insets(12, 1, 16, 1), 16, 8);
        submit.setPreferredSize(new Dimension(300, 25));
        submit.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                JButton curr =(JButton) e.getSource();
                curr.setBackground(Color.GRAY);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                JButton curr =(JButton) e.getSource();
                curr.setBackground(Color.LIGHT_GRAY);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                JButton curr =(JButton) e.getSource();
                curr.setBackground(Color.GRAY);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                JButton curr =(JButton) e.getSource();
                curr.setBackground(Color.LIGHT_GRAY);
            }
        });
        submit.addActionListener(e -> {
            if (jfk.getSelectedFile() == null) {
                JOptionPane.showMessageDialog(mainFrame, "Please select a file", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    gameEngine.save(jfk.getSelectedFile().getAbsolutePath(), (String) selectExtension.getSelectedItem());
                    mainFrame.setEnabled(true);
                    dispose();
                } catch (Exception ee) {
                    JOptionPane.showMessageDialog(mainFrame, ee.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        roundedPanel.add(submit, c);
    }
}