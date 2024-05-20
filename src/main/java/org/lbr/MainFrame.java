package org.lbr;

import javax.swing.*;
import java.awt.*;

public class MainFrame {

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setPreferredSize(new Dimension(600, 600));
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        MainWindow mainWindow = new MainWindow();
        mainWindow.initComponent();
        frame.setContentPane(mainWindow.mainPanel);
        frame.setVisible(true);
    }
}
