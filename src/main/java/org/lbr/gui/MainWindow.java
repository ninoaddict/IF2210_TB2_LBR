package org.lbr.gui;

import org.lbr.gui.card.Card;
import org.lbr.GameEngine;
import org.lbr.gameobject.cultivable.animal.Herbivore;
import org.lbr.gameobject.product.Product;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.*;
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
        this.setPreferredSize(new Dimension(90, 120));
        this.setBackground(new Color(213,220,246));
        this.setOpaque(false);
        this.setLayout(new GridLayout());
        JLabel awaJLabel = new JLabel("HAHAA");
        awaJLabel.setForeground(Color.black);
        awaJLabel.setPreferredSize(new Dimension(10, 10));
        //this.add(awaJLabel);
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
        graphics.fillRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height); //paint background
       
        graphics.drawRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);
        //graphics.setColor(getForeground());
        //graphics.setStroke(new BasicStroke((float) 1.0));
        //graphics.drawRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height); //paint border
        super.paintComponent(g);
    }
}

class RoundButton extends JButton {
	  public RoundButton(String label) {
	    super(label);
	    this.setPreferredSize(new Dimension(50, 40));
        this.setBackground(Color.white);
        this.setForeground(Color.white);
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
	        Dimension arcs = new Dimension(7, 7);
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

class panel_with_image extends JPanel {
	
	BufferedImage curBufferedImage;
	
	public static BufferedImage resize(BufferedImage img, int newW, int newH) { 
	    Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
	    BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

	    Graphics2D g2d = dimg.createGraphics();
	    g2d.drawImage(tmp, 0, 0, null);
	    g2d.dispose();

	    return dimg;
	}  
	
	public panel_with_image() {
		try {
		curBufferedImage = resize(ImageIO.read(this.getClass().getResource("/images/bgguioopatl1.jpg")), 800, 800);
		} catch (Exception e) {
			// TODO: handle exception
		}
		// TODO Auto-generated constructor stub
	}
	
	protected void paintComponent(Graphics g) {
	    g.drawImage(curBufferedImage, 0, 0, null);
	}
}


public class MainWindow extends JPanel {
//    public panel_with_image mainPanel;
    BufferedImage curBufferedImage;
    private GameEngine gameEngine;
    private JPanel panel_atas;
    private JPanel panel_tengah;
    private JPanel panel_bawah;
    private ArrayList<Card> card_list;
    private String cardString = "CARD";
    private String fieldString = "FIELD";
    JLabel howMuchHisMoneyJLabel;
    JLabel howMuchMyMoneyJLabel;
    MainWindow(GameEngine ge){
        try {
        	gameEngine = ge;
            curBufferedImage = resize(ImageIO.read(this.getClass().getResource("/images/bgguioopatl1.jpg")), 800, 800);
        } catch (Exception e) {
            // TODO: handle exception
        }
        initComponent();
    }

    public void initComponent() {
        this.setBackground(new Color(170,193,237));
        this.setPreferredSize(new Dimension(800, 600));
        this.setLayout(new GridBagLayout());
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
        panel_atas.setPreferredSize(new Dimension(500, 20));
        panel_atas.setBackground(Color.blue);
        panel_atas.setOpaque(false);
        this.add(panel_atas, gridBagConstraints);
        gridBagConstraints.weighty = 4.0;

        gridBagConstraints.gridheight = 1;
        gridBagConstraints.gridy = 1;
        panel_tengah.setBackground(Color.yellow);
        panel_tengah.setPreferredSize(new Dimension(100, 420));
        panel_tengah.setOpaque(false);
        this.add(panel_tengah, gridBagConstraints);
        gridBagConstraints.weighty = 0.1;

        gridBagConstraints.gridheight = 1;
        gridBagConstraints.gridy = 2;
        panel_bawah.setBackground(Color.red);
        panel_bawah.setPreferredSize(new Dimension(100, 140));
        panel_bawah.setOpaque(false);
        this.add(panel_bawah, gridBagConstraints);

        panel_tengah.setLayout(new GridBagLayout());

        JPanel card_grid_panel = new JPanel();
        card_grid_panel.setBackground(new Color(0,108,103));
        card_grid_panel.setOpaque(false);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.weighty = 1.0;
        
        CardLayout cardLayout = new CardLayout();
        
        card_grid_panel.setLayout(cardLayout);
        

        panel_tengah.add(card_grid_panel, gridBagConstraints);

        gridBagConstraints.gridx = 1;
        gridBagConstraints.weightx = 1.0;

        JPanel button_grid_panel = new JPanel();
        button_grid_panel.setBackground(Color.MAGENTA);
        button_grid_panel.setOpaque(false);

        panel_tengah.add(button_grid_panel, gridBagConstraints);
        
        JPanel real_card_gridJPanel = new JPanel();
        real_card_gridJPanel.setBackground(new Color(0,108,103));
        real_card_gridJPanel.setOpaque(false);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.weighty = 1.0;
        
        
        JPanel shop_card_gridJPanel = new JPanel();
        shop_card_gridJPanel.setBackground(Color.CYAN);
        shop_card_gridJPanel.setOpaque(false);
        
        shop_card_gridJPanel.setLayout(new GridBagLayout());
        
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.weighty = 1.0;
        
        
        

        real_card_gridJPanel.setLayout(new GridBagLayout());

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
                real_card_gridJPanel.add(new Card(null, gameEngine.getCurrPlayer(), i, j, Card.FIELD), gridBagConstraints);
            }
        }
        
        card_grid_panel.add(fieldString, real_card_gridJPanel);
        
        card_grid_panel.add(cardString, shop_card_gridJPanel);
        
        cardLayout.show(card_grid_panel, fieldString);
        
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.insets = new Insets(7, 7, 7, 7);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        
        ArrayList<String> productNameStrings = new ArrayList<String>();
        
        Map<Product, Integer> hMap = gameEngine.getShop().getProducts();
        
        for(Product name_product: hMap.keySet()) {
        	productNameStrings.add(name_product.getName().toUpperCase().replace(' ', '_'));
        }
        
        System.out.println(productNameStrings.size());
        
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                gridBagConstraints.gridx = j;
                gridBagConstraints.gridy = i;
                System.out.println(productNameStrings.get(3 * i + j));
                shop_card_gridJPanel.add(new Card(new Product(productNameStrings.get(i * 3 + j)), null, i, j, Card.SHOP, 1), gridBagConstraints);
            }
        }
        
        


        panel_bawah.setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, Color.WHITE));
        panel_bawah.setLayout(new GridBagLayout());
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.insets = new Insets(7, 27, 7, 7);
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        JLabel temp = new JLabel();

        panel_bawah.add(new Card(null, null, 4, 0, Card.DECK), gridBagConstraints);
        gridBagConstraints.insets = new Insets(7, 7, 7, 7);
        for(int i = 1; i < 6; i++) {
        	gridBagConstraints.gridx = i;
            Card card = new Card(null, gameEngine.getCurrPlayer(), 4, i, Card.DECK);
            panel_bawah.add(card, gridBagConstraints);
        }

        gridBagConstraints.gridx = 6;
        gridBagConstraints.weightx = 1.0;
        panel_bawah.add(temp, gridBagConstraints);
        
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
        
        RoundButton[] roundButtons = new RoundButton[6];
        
        for(int i = 0; i < 6; i++) {
        	gridBagConstraints.gridy = i;
        	RoundButton jtempButton = new RoundButton(button_name_array[i]);
        	jtempButton.setForeground(Color.black);
        	button_grid_panel.add(jtempButton, gridBagConstraints);
        	roundButtons[i] = jtempButton;
        }
        
        roundButtons[0].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(card_grid_panel, fieldString);
				
			}
		});
        
        roundButtons[1].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(card_grid_panel, fieldString);
				
			}
		});
        
       roundButtons[2].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(card_grid_panel, cardString);
				
			}
		});
        
        panel_atas.setLayout(new GridBagLayout());
        RoundedPanel uwuPanel = new RoundedPanel(15);
        uwuPanel.setLayout(new GridBagLayout());
        uwuPanel.setPreferredSize(new Dimension(300, 40));
        uwuPanel.setBackground(Color.white);
        uwuPanel.setOpaque(false);
        
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(10, 10, 10, 10);
        
        JLabel labelNext = new JLabel("Number of Turn: 1");
        labelNext.setBackground(Color.red);
        labelNext.setOpaque(false);
        
        uwuPanel.add(labelNext, gridBagConstraints);
        
        RoundButton roundButton = new RoundButton("Next Turn");
        roundButton.setBackground(Color.black);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.insets = new Insets(3, 10, 3, 10);

        
        uwuPanel.add(roundButton, gridBagConstraints);
        
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(0, 20, 0, 80);
                
        JPanel moniesJPanel = new JPanel();
        moniesJPanel.setPreferredSize(new Dimension(150, 50));
        moniesJPanel.setOpaque(false);
        panel_atas.add(moniesJPanel, gridBagConstraints);
        
        moniesJPanel.setLayout(new GridBagLayout());
        
        GridBagConstraints newGridBagConstraints = new GridBagConstraints();
        newGridBagConstraints.gridx = 0;
        newGridBagConstraints.gridy = 0;
        newGridBagConstraints.fill = GridBagConstraints.BOTH;
        newGridBagConstraints.weightx = 0.75;
        newGridBagConstraints.weighty = 1.0;
        ImageIcon imageIcon = new ImageIcon(this.getClass().getResource("/images/gold-coin.png"));
        Image image = imageIcon.getImage(); // transform it 
        Image newimg = image.getScaledInstance(15, 15,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
        imageIcon = new ImageIcon(newimg);  // transform it back
        
        JLabel myselfMoniesJPanel = new JLabel("Player 1");
        myselfMoniesJPanel.setBackground(Color.green);
        myselfMoniesJPanel.setOpaque(false);
        newGridBagConstraints.insets = new Insets(0, 10, 0, 0);
        moniesJPanel.add(myselfMoniesJPanel, newGridBagConstraints);
        
        JLabel hisMoniesJPanel = new JLabel("Player 2");
        hisMoniesJPanel.setBackground(Color.red);
        hisMoniesJPanel.setOpaque(false);
        newGridBagConstraints.gridy = 1;

        moniesJPanel.add(hisMoniesJPanel, newGridBagConstraints);
        
        howMuchMyMoneyJLabel = new JLabel(Integer.toString(gameEngine.getPlayerAtIndex(0).getGulden()));
        howMuchMyMoneyJLabel.setBackground(Color.cyan);
        howMuchMyMoneyJLabel.setOpaque(false);
        howMuchMyMoneyJLabel.setIcon(imageIcon);
        howMuchMyMoneyJLabel.setHorizontalAlignment(JLabel.RIGHT);
        howMuchMyMoneyJLabel.setHorizontalTextPosition(SwingConstants.LEADING);
        newGridBagConstraints.gridx = 1;
        newGridBagConstraints.gridy = 0;
        newGridBagConstraints.insets = new Insets(0, 0, 0, 10);
        newGridBagConstraints.weightx = 1.0;

        moniesJPanel.add(howMuchMyMoneyJLabel, newGridBagConstraints);
        
        howMuchHisMoneyJLabel = new JLabel(Integer.toString(gameEngine.getPlayerAtIndex(1).getGulden()));
        
        howMuchHisMoneyJLabel.setBackground(Color.orange);
        howMuchHisMoneyJLabel.setOpaque(false);
        howMuchHisMoneyJLabel.setIcon(imageIcon);
        howMuchHisMoneyJLabel.setHorizontalAlignment(JLabel.RIGHT);
        howMuchHisMoneyJLabel.setHorizontalTextPosition(SwingConstants.LEADING);
        newGridBagConstraints.gridy = 1;
        moniesJPanel.add(howMuchHisMoneyJLabel, newGridBagConstraints);
        
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.gridx = 1;
        gridBagConstraints.weightx = 0.0;
        panel_atas.add(uwuPanel, gridBagConstraints);
        
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.gridx = 2;
        JPanel emptJPanel = new JPanel();
        emptJPanel.setOpaque(false);
        panel_atas.add(emptJPanel, gridBagConstraints);

        //panel_atas.setLayout(new GridBagLayout());
        this.setVisible(true);
    }

    public static BufferedImage resize(BufferedImage img, int newW, int newH) {
        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return dimg;
    }
    
    public void buyProduct(Product product) throws Exception {
    	gameEngine.getCurrPlayer().buy(product, gameEngine.getShop());
    	if(gameEngine.getCurrTurn() == 1) {
    		this.howMuchMyMoneyJLabel.setText(Integer.toString(gameEngine.getCurrPlayer().getGulden()));
    	}else {
    		this.howMuchHisMoneyJLabel.setText(Integer.toString(gameEngine.getCurrPlayer().getGulden()));
    	}
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(curBufferedImage, 0, 0, null);
    }
}
