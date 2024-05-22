package org.lbr.gui;

import javax.swing.*;

import org.lbr.gameobject.product.Product;
import org.lbr.gui.card.Card;
import org.lbr.player.Player;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class ShuffleDialog extends JFrame {
    private Player player;
    private JPanel roundedPanel;

    public ShuffleDialog(Player player, Component frame) {
        this.player = player;
        this.setUndecorated(true);
//        this.setBackground(Color.WHITE);
        this.setSize(new Dimension(300, 400));
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(frame.getParent().getParent());
        this.setOpacity((float)0.9);

        roundedPanel = new CustomPanel();
        roundedPanel.setBackground(new Color(230, 230, 230));
        roundedPanel.setLayout(new GridBagLayout());
        roundedPanel.setPreferredSize(new Dimension(300, 400));
        roundedPanel.setOpaque(false);

        GridBagConstraints c = new GridBagConstraints();

        // TODO: change with real player data
        c.insets = new Insets(10, 5, 10, 5);
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                c.gridx = i;
                c.gridy = j;
                Card card = new Card(new Product("SUSU"), player, i, j, Card.SHUFFLE, false);
                roundedPanel.add(card, c);
            }
        }

        c.gridx = 0;
        c.gridy = 2;
        JButton shuffleButton = new JButton();
        shuffleButton.setFocusPainted(false);
        shuffleButton.setContentAreaFilled(false);
        shuffleButton.setMargin(new Insets(0, 0, 0, 0));
        shuffleButton.setOpaque(false);
        ImageIcon icon = new ImageIcon(this.getClass().getResource("/images/shuffle.png"));
        Image image = icon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        shuffleButton.setIcon(new ImageIcon(image));
        roundedPanel.add(shuffleButton, c);

        c.gridx = 1;
        JButton confirmButton = new JButton();
        confirmButton.setFocusPainted(false);
        confirmButton.setContentAreaFilled(false);
        confirmButton.setMargin(new Insets(0, 0, 0, 0));
        confirmButton.setOpaque(false);
        icon = new ImageIcon(this.getClass().getResource("/images/check-mark.png"));
        image = icon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        confirmButton.setIcon(new ImageIcon(image));
        roundedPanel.add(confirmButton, c);

        this.add(roundedPanel);
    }
}

class CustomPanel extends JPanel{
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Dimension arcs = new Dimension(16, 16);
        int width = 300;
        int height = 400;
        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setColor(new Color(230, 230, 230));
        graphics.fillRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height);
        graphics.setColor(getForeground());
        graphics.setStroke(new BasicStroke(1.5f));
        graphics.drawRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height);
    }
}