package org.lbr.view;

import javax.swing.*;

import org.lbr.controller.GameEngine;

import java.awt.*;

public class MainFrame extends JFrame{
    private MainWindow mainWindow;

    public MainFrame(GameEngine ge) {
        mainWindow = new MainWindow(ge);
        initComponent();
    }

    private void initComponent() {
        this.setSize(new Dimension(800, 800));
        this.setLayout(new GridLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setMainFrame(this);
        this.add(mainWindow);
        this.setResizable(false);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        mainWindow.start();
    }
}
