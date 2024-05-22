package org.lbr;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;

import javax.swing.JButton;
import javax.swing.border.LineBorder;

class RealButton extends JButton {
	private String text;
	public RealButton(String text) {
		super();
		this.text = text;
        this.setPreferredSize(new Dimension(50, 50));
        this.setBackground(Color.white);
        this.setText(text);
        //this.setForeground(Color.black);
        this.setBorder(new RoundEdgedBorder(text));
        this.setOpaque(true);
		// TODO Auto-generated constructor stub
	}
	protected void paintComponent(Graphics g) {
        if (getBorder() instanceof RoundEdgedBorder) {
            Shape borderShape = (Shape) ((RoundEdgedBorder) getBorder());
            g.setClip(borderShape);
        }
        super.paintComponent(g);
    }
	private class RoundEdgedBorder extends LineBorder{
	    int arcWidth=20,arcHeight=20;
	    Color fillColor= Color.green;
	    private String textString;
	
	    public RoundEdgedBorder(String t) {
	    	super(Color.red);
	    	textString = t;
	    }
	
	
	
	    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height){
	    	((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	    	g.setColor(fillColor);
	    	g.drawRoundRect(x, y, width, height, arcWidth, arcHeight);
	    	g.dispose();

	    }
    }
}
