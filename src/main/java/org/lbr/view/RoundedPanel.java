package org.lbr.view;

import javax.swing.*;
import java.awt.*;

public class RoundedPanel extends JPanel {
    private Color backgroundColor;
    private int cornerRadius = 15;

    public RoundedPanel(LayoutManager layout, int radius) {
        super(layout);
        cornerRadius = radius;
    }

    public RoundedPanel(LayoutManager layout, int radius, Color bgColor) {
        super(layout);
        cornerRadius = radius;
        backgroundColor = bgColor;
    }

    public RoundedPanel(int radius, LayoutManager layout, Color bgColor, int width, int height) {
        super();
        cornerRadius = radius;
        backgroundColor = bgColor;
        setPreferredSize(new Dimension(width, height));
        setOpaque(false);
        setLayout(layout);
        setBackground(bgColor);
    }

    public RoundedPanel(int radius) {
        super();
        this.setPreferredSize(new Dimension(90, 120));
        this.setBackground(new Color(213, 220, 246));
        this.setOpaque(false);
        this.setLayout(new GridLayout());
        JLabel awaJLabel = new JLabel("HAHAA");
        awaJLabel.setForeground(java.awt.Color.black);
        awaJLabel.setPreferredSize(new Dimension(10, 10));
        cornerRadius = radius;
    }

    public RoundedPanel(int radius, Color bgColor) {
        super();
        cornerRadius = radius;
        backgroundColor = bgColor;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Dimension arcs = new Dimension(cornerRadius, cornerRadius);
        int width = getWidth();
        int height = getHeight();
        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        //Draws the rounded panel with borders.
        if (backgroundColor != null) {
            graphics.setColor(backgroundColor);
        } else {
            graphics.setColor(getBackground());
        }
        //graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        //graphics.setStroke(new BasicStroke);
        graphics.fillRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height); //paint background

        graphics.drawRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height);
        //graphics.setColor(getForeground());
        //graphics.setStroke(new BasicStroke((float) 1.0));
        //graphics.drawRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height); //paint border
        super.paintComponent(g);
    }
}
