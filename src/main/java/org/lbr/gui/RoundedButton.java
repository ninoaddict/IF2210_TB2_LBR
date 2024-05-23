package org.lbr.gui;

import javax.swing.*;
import java.awt.*;

public class RoundedButton extends JButton {
    private int round;

    public RoundedButton(String label, Insets insets, int fontSize, int round) {
        super(label);
        this.round = round;
        this.setMargin(insets);
        this.setBackground(Color.LIGHT_GRAY);
        this.setForeground(Color.BLACK);
        this.setText(label);
        this.setFont(new Font("Linux Libertine", 1, fontSize));
        this.setFocusPainted(false);
        this.setBorderPainted(false);
        this.setOpaque(false);
        this.setContentAreaFilled(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Dimension arcs = new Dimension(round, round);
        int width = getWidth();
        int height = getHeight();
        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setColor(getBackground());
        graphics.fillRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height); // paint background
        graphics.setColor(getForeground());
        graphics.setStroke(new BasicStroke((float) 1.5));
        graphics.drawRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height);
        super.paintComponent(g);
    }
}
