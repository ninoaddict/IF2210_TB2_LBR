package org.lbr;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.IOException;

public class Card extends JPanel {
    private GameObject gameObject;
    private JLabel label;
    private JLabel nameLabel;
    private Player owner;

    public Card(GameObject gameObject, Player owner) {
        this.gameObject = gameObject;
        this.owner = owner;

        this.label = new JLabel();
        this.setOpaque(false);
        this.setBackground(Color.LIGHT_GRAY);
        this.setPreferredSize(new Dimension(90, 120));
        this.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;


    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Dimension arcs = new Dimension(16, 16);
        int width = getWidth();
        int height = getHeight();
        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setColor(getBackground());
        graphics.fillRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height); // paint background
        graphics.setColor(getForeground());
        graphics.setStroke(new BasicStroke((float)1.5));
        graphics.drawRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height); // border
    }

    private void updateCardDisplay() {
        if (gameObject != null) {
            ImageIcon icon = new ImageIcon(this.getClass().getResource("/images/gold-coin.png"));
        }
    }
}
