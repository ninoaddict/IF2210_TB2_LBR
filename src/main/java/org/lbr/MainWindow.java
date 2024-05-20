package org.lbr;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MainWindow extends JPanel {
    public JPanel mainPanel;
    private JPanel panel_atas;
    private JPanel panel_tengah;
    private JPanel panel_bawah;
    MainWindow(){

    }
    public void initComponent(){
        mainPanel = new JPanel();
        mainPanel.setPreferredSize(new Dimension(600, 600));
        mainPanel.setLayout(new GridBagLayout());
        panel_atas  = new JPanel();
        panel_tengah = new JPanel();
        panel_bawah = new JPanel();
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 1;
        panel_atas.setBackground(Color.blue);
        panel_atas.setOpaque(true);
        mainPanel.add(panel_atas, gridBagConstraints);
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.gridy = 1;
        panel_tengah.setBackground(Color.yellow);
        panel_tengah.setOpaque(true);
        mainPanel.add(panel_tengah, gridBagConstraints);
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.gridy = 2;
        mainPanel.add(panel_bawah, gridBagConstraints);

        panel_atas.setLayout(new GridBagLayout());
        mainPanel.setVisible(true);
    }
}
