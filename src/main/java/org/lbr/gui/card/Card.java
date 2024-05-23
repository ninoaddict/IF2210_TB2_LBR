package org.lbr.gui.card;

import org.lbr.gameobject.GameObject;
import org.lbr.gameobject.cultivable.Cultivable;
import org.lbr.gui.MainWindow;
import org.lbr.gui.ObjectInfoDialog;
import org.lbr.gui.ShuffleDialog;
import org.lbr.player.Player;
import org.lbr.shop.Shop;
import org.lbr.gameobject.product.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class Card extends JPanel {
    public static int DECK = 0;
    public static int FIELD = 1;
    public static int SHOP = 2;
    public static int SHUFFLE = 3;
    private GameObject gameObject;
    private final JLabel label;
    private final JLabel nameLabel;
    private final JLabel stockJLabel;
    private final JLabel priceJLabel;
    private Player owner;
    private int row;
    private int col;
    private int currentPosition;
    private int stock = 0;
    private int price = 0;
    private boolean isDraggable;

    public Card(GameObject gameObject, Player owner, int row, int col, int currentPosition, boolean isDraggable) {
        this.gameObject = gameObject;
        this.owner = owner;
        this.currentPosition = currentPosition;
        this.row = row;
        this.col = col;
        this.isDraggable = isDraggable;

        if (this.gameObject != null) {
            this.gameObject.setParent(this);
        }

        this.label = new JLabel();
        this.setOpaque(false);
        if(currentPosition == SHOP) {
        	this.setPreferredSize(new Dimension(150, 150));
        }else {
        	this.setPreferredSize(new Dimension(90, 120));
        }
        this.setBackground(new Color(224, 247, 250));
        this.setPreferredSize(new Dimension(90, 120));
        this.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;

        if (gameObject != null) {
            ImageIcon icon = new ImageIcon(this.getClass().getResource(gameObject.getImgUrlPath()));
            Image image = icon.getImage().getScaledInstance(75, 75, java.awt.Image.SCALE_SMOOTH);
            label.setIcon(new ImageIcon(image));
            label.setHorizontalAlignment(JLabel.CENTER);
        } else {
            label.setIcon(null);
        }
        add(label, gbc);

        gbc.gridy = 1;
        this.nameLabel = new JLabel();
        if (this.gameObject != null) {
            nameLabel.setText("<html><body style='text-align:center'>" + this.gameObject.getName() + "</body></html>");
        } else {
            nameLabel.setText("");
        }
        nameLabel.setFont(new Font("Linux Libertine", 1, 14));
        nameLabel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
        add(nameLabel, gbc);

        setTransferHandler(new CardTransferHandler());

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                Card comp = (Card) e.getSource();
                if (getDraggable()) {
                    TransferHandler handler = comp.getTransferHandler();
                    handler.exportAsDrag(comp, e, TransferHandler.MOVE);
                }
            }
        });

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Card comp = (Card) e.getSource();
                if (comp.getGameObject() != null) {
                    if (comp.getCurrentPosition() == Card.FIELD) {
                        ObjectInfoDialog info = new ObjectInfoDialog(comp);
                        info.setVisible(true);
                    }
                } else {
//                    ShuffleDialog sd = new ShuffleDialog(comp.getOwner(), comp);
//                    sd.setVisible(true);
                }
            }
        });
        stockJLabel = new JLabel();
        priceJLabel = new JLabel();
    }

    public Card(GameObject gameObject, Player owner, int row, int col, int currentPosition, int changer) {
        this.gameObject = gameObject;
        this.owner = owner;
        this.currentPosition = currentPosition;
        this.row = row;
        this.col = col;

        if (this.gameObject != null) {
            this.gameObject.setParent(this);
        }

        this.label = new JLabel();
        this.setOpaque(false);
        this.setBackground(new Color(224, 247, 250));
        if(currentPosition == SHOP) {
        	this.setPreferredSize(new Dimension(160, 160));
        }else {
        	this.setPreferredSize(new Dimension(90, 120));
        }
        this.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;

        if (gameObject != null) {
            System.out.println(gameObject.getImgUrlPath());
            ImageIcon icon = new ImageIcon(this.getClass().getResource(gameObject.getImgUrlPath()));
            Image image = icon.getImage().getScaledInstance(75, 75, java.awt.Image.SCALE_SMOOTH);
            label.setIcon(new ImageIcon(image));
            label.setHorizontalAlignment(JLabel.CENTER);
        } else {
            label.setIcon(null);
        }
        add(label, gbc);

        gbc.gridy = 1;
        this.nameLabel = new JLabel();
        if (this.gameObject != null) {
            nameLabel.setText("<html><body style='text-align:center'>" + this.gameObject.getName() + "</body></html>");
        } else {
            nameLabel.setText("");
        }
        nameLabel.setFont(new Font("Linux Libertine", 1, 14));
        nameLabel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
        add(nameLabel, gbc);

        gbc.gridy = 2;

        this.stock = 1;

        stockJLabel = new JLabel("Stock: " + Integer.toString(stock));

        add(stockJLabel, gbc);


        this.price = ((Product)this.gameObject).getPrice();

        priceJLabel = new JLabel("Price: " + Integer.toString(price));

        gbc.gridy = 3;

        add(priceJLabel, gbc);

        setTransferHandler(new CardTransferHandler());

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                Card comp = (Card) e.getSource();
                TransferHandler handler = comp.getTransferHandler();
                handler.exportAsDrag(comp, e, TransferHandler.MOVE);
            }
        });

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Card comp = (Card) e.getSource();
                if (comp.getGameObject() != null) {
                    if (comp.getCurrentPosition() == Card.FIELD) {
                        ObjectInfoDialog info = new ObjectInfoDialog(comp);
                        info.setVisible(true);
                    }
                } else {
                    // TODO: handle
                }
            }
        });
    }

    public boolean getDraggable() {
        return isDraggable;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Dimension arcs = new Dimension(15, 15);
        int width = getWidth();
        int height = getHeight();
        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setColor(getBackground());
        graphics.fillRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height); // paint background
        graphics.setColor(getForeground());
        graphics.setStroke(new BasicStroke((float) 1.5));
        graphics.drawRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height); // border
    }

    public void updateCardDisplay() {
        if (gameObject != null) {
            ImageIcon icon = new ImageIcon(this.getClass().getResource(gameObject.getImgUrlPath()));
            if (gameObject instanceof Cultivable && ((Cultivable) gameObject).isReady()) {
                icon = new ImageIcon(this.getClass().getResource(((Cultivable) gameObject).getProduct().getImgUrlPath()));
            }
            Image image = icon.getImage().getScaledInstance(75, 75, java.awt.Image.SCALE_SMOOTH);
            label.setIcon(new ImageIcon(image));
            label.setHorizontalAlignment(JLabel.CENTER);
            nameLabel.setText("<html><body style='text-align:center'>" + this.gameObject.getName() + "</body></html>");
        } else {
            label.setIcon(null);
            nameLabel.setText("");
            this.revalidate();
            this.label.revalidate();
            this.label.repaint();
            this.nameLabel.revalidate();
            this.nameLabel.repaint();
            this.repaint();
        }
    }

    public void setGameObject(GameObject gameObject) {
        this.gameObject = gameObject;
        if (this.gameObject != null) {
            this.gameObject.setParent(this);
        }
        updateCardDisplay();
    }

    public GameObject getGameObject() {
        return this.gameObject;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public Player getOwner() {
        return owner;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public void buyHappened(int c) throws Exception {
    	if(this.stock + c == -1) {
    		throw new Exception("BUY CANNOT BE DONE!");
    	}
    	this.stock += c;
    	this.stockJLabel.setText("Stock: " + Integer.toString(this.stock));
    }
}
