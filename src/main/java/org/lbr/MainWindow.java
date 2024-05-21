package org.lbr;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

class DummyCard extends JPanel {
    DummyCard() {
        super();
        this.setPreferredSize(new Dimension(80, 110));
        this.setBackground(Color.white);
        this.setBorder(new RoundEdgedBorder());
        this.setOpaque(false);
    }
    
    
    private class RoundEdgedBorder extends LineBorder{
	    int arcWidth=20,arcHeight=20;
	    Color fillColor= Color.green;
	
	    public RoundEdgedBorder() {
	    	super(Color.red);
	    }
	
	
	
	    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height){
	    	Graphics2D graphics2d = (Graphics2D) g.create();
	    	graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	    	graphics2d.setColor(fillColor);
	    	graphics2d.fillRoundRect(x, y, width, height, arcWidth, arcHeight);
	    	graphics2d.dispose();

	    }
    }
}

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

class RoundedPanel extends JPanel
{
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

    public RoundedPanel(int radius) {
        super();
        this.setPreferredSize(new Dimension(80, 110));
        this.setBackground(Color.green);
        this.setOpaque(false);
        this.add(new JLabel("UWUWUWUW"));
        cornerRadius = radius;
    }

    public RoundedPanel(int radius, Color bgColor) {
        super();
        cornerRadius = radius;
        backgroundColor = bgColor;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
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
        graphics.fillRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height); //paint background
        graphics.setColor(getForeground());
        //graphics.setStroke(new BasicStroke((float) 1.0));
        //graphics.drawRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height); //paint border
        graphics.dispose();
    }
}

class RoundButton extends JButton {
	  public RoundButton(String label) {
	    super(label);
	    this.setPreferredSize(new Dimension(50, 50));
        this.setBackground(Color.white);
        this.setText(label);
        this.setFocusPainted(false);

        //this.setForeground(Color.black);
        this.setOpaque(false);

	// These statements enlarge the button so that it 
	// becomes a circle rather than an oval.
        /*
	    Dimension size = getPreferredSize();
	    size.width = size.height = Math.max(size.width, 
	      size.height);
	    setPreferredSize(size);*/

	// This call causes the JButton not to paint 
	   // the background.
	// This allows us to paint a round background.
	    setContentAreaFilled(false);
	  }

	// Paint the round background and label.
	  protected void paintComponent(Graphics g) {
	        Dimension arcs = new Dimension(15, 15);
	        int width = getWidth();
	        int height = getHeight();
	    	((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	    if (getModel().isArmed()) {
	// You might want to make the highlight color 
	   // a property of the RoundButton class.
	      g.setColor(Color.lightGray);
	    } else {
	      g.setColor(getBackground());
	    }
	    g.fillRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);

	// This call will paint the label and the 
	   // focus rectangle.
	    super.paintComponent(g);
	  }

	// Paint the border of the button using a simple stroke.
	  protected void paintBorder(Graphics g) {
	    g.setColor(getForeground());
	    //g.drawOval(0, 0, getSize().width-1, 
	      //getSize().height-1);
	  }

	// Hit detection.
	  Shape shape;
	  public boolean contains(int x, int y) {
	// If the button has changed size, 
	   // make a new shape object.
	    if (shape == null || 
	      !shape.getBounds().equals(getBounds())) {
	      shape = new Ellipse2D.Float(0, 0, 
	        getWidth(), getHeight());
	    }
	    return shape.contains(x, y);
	  }
}

public class MainWindow extends JPanel {
    public JPanel mainPanel;
    private JPanel panel_atas;
    private JPanel panel_tengah;
    private JPanel panel_bawah;
    MainWindow(){

    }
    public void initComponent() {
        mainPanel = new JPanel();
        mainPanel.setBackground(Color.orange);
        mainPanel.setPreferredSize(new Dimension(800, 600));
        mainPanel.setLayout(new GridBagLayout());
        panel_atas  = new JPanel();
        panel_tengah = new JPanel();
        panel_bawah = new JPanel();
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.weighty = 2.0;
        gridBagConstraints.weightx = 1.0;
        panel_atas.setPreferredSize(new Dimension(100, 50));
        panel_atas.setBackground(Color.blue);
        panel_atas.setOpaque(true);
        mainPanel.add(panel_atas, gridBagConstraints);
        gridBagConstraints.weighty = 4.0;

        gridBagConstraints.gridheight = 1;
        gridBagConstraints.gridy = 1;
        panel_tengah.setBackground(Color.yellow);
        panel_tengah.setPreferredSize(new Dimension(100, 400));
        panel_tengah.setOpaque(true);
        mainPanel.add(panel_tengah, gridBagConstraints);
        gridBagConstraints.weighty = 1.25;

        gridBagConstraints.gridheight = 1;
        gridBagConstraints.gridy = 2;
        panel_bawah.setBackground(Color.red);
        panel_bawah.setPreferredSize(new Dimension(100, 100));
        mainPanel.add(panel_bawah, gridBagConstraints);

        panel_tengah.setLayout(new GridBagLayout());

        JPanel card_grid_panel = new JPanel();
        card_grid_panel.setBackground(new Color(0,108,103));

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 1.0;

        panel_tengah.add(card_grid_panel, gridBagConstraints);

        gridBagConstraints.gridx = 1;
        gridBagConstraints.weightx = 1.0;

        JPanel button_grid_panel = new JPanel();
        button_grid_panel.setBackground(Color.MAGENTA);

        panel_tengah.add(button_grid_panel, gridBagConstraints);

        //JPanel inside_card_grid_panel = new JPanel();
        //inside_card_grid_panel.setBackground(Color.gray);
        //inside_card_grid_panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        card_grid_panel.setLayout(new GridBagLayout());

        gridBagConstraints = new GridBagConstraints();

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.insets = new Insets(10, 10, 10, 10);

        //inside_card_grid_panel.setPreferredSize(new Dimension(50, 50));


        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.insets = new Insets(7, 7, 7, 7);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;

        //inside_card_grid_panel.add(new DummyCard(), gridBagConstraints);

        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 5; j++){
                gridBagConstraints.gridx = j;
                gridBagConstraints.gridy = i;
                card_grid_panel.add(new RoundedPanel(15), gridBagConstraints);
            }
        }
        
        panel_bawah.setLayout(new GridBagLayout());
        
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.insets = new Insets(7, 7, 7, 7);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        
        for(int i = 0; i < 6; i++) {
        	gridBagConstraints.gridx = i;
        	panel_bawah.add(new RoundedPanel(15), gridBagConstraints);
        }
        
        button_grid_panel.setLayout(new GridBagLayout());
        
        gridBagConstraints = new GridBagConstraints();
        
        gridBagConstraints.insets = new Insets(10, 7, 10, 7);
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        
        String[] button_name_array = new String[6];
        button_name_array[0] = "My Field";
        button_name_array[1] = "Opponent's Field";
        button_name_array[2] = "Shop";
        button_name_array[3] = "Save State";
        button_name_array[4] = "Load State";
        button_name_array[5] = "Load Plugin";
        
        gridBagConstraints.gridx = 0;
        gridBagConstraints.weightx = 1;
        
        for(int i = 0; i < 6; i++) {
        	gridBagConstraints.gridy = i;
        	button_grid_panel.add(new RoundButton(button_name_array[i]), gridBagConstraints);
        }
        
        //gridBagConstraints.insets = 
        
        panel_atas.setLayout(new GridBagLayout());
        JPanel uwuPanel = new JPanel();
        uwuPanel.setLayout(new GridBagLayout());
        uwuPanel.setPreferredSize(new Dimension(120, 80));
        uwuPanel.setBackground(Color.yellow);
        
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        
        JLabel labelNext = new JLabel("Number of Turn: 1");
        labelNext.setBackground(Color.red);
        
        uwuPanel.add(labelNext, gridBagConstraints);
        
        RoundButton roundButton = new RoundButton("Next Turn");
        roundButton.setBackground(Color.black);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.weightx = 1.0;
        
        uwuPanel.add(roundButton, gridBagConstraints);
        
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        
        panel_atas.add(uwuPanel, gridBagConstraints);
        
        //panel_atas.setLayout(new GridBagLayout());
        mainPanel.setVisible(true);
    }
}
