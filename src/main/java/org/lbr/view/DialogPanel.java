package org.lbr.view;

import javax.swing.*;
import java.awt.*;

class DialogPanel extends JPanel {

    public DialogPanel(int width, int height, Color background, LayoutManager layout) {
        this.setBackground(background);
        this.setLayout(layout);
        this.setPreferredSize(new Dimension(width, height));
        this.setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Dimension arcs = new Dimension(16, 16);
        int width = getWidth();
        int height = getHeight();
        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setColor(getBackground());
        graphics.fillRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height);
        graphics.setColor(getForeground());
        graphics.setStroke(new BasicStroke(1.5f));
        graphics.drawRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height);
        super.paintComponent(g);
    }
}
