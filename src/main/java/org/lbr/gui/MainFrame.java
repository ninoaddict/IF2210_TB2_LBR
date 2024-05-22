package org.lbr.gui;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame{
    private MainWindow mainWindow;

    public MainFrame() {
        mainWindow = new MainWindow();
        initComponent();
    }

    private void initComponent() {
        this.setSize(new Dimension(800, 800));
        this.setLayout(new GridLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(mainWindow);
        this.setVisible(true);
    }
}
