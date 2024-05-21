package org.lbr;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class Card extends JPanel {
    public static int DECK = 0;
    public static int FIELD = 1;
    private GameObject gameObject;
    private final JLabel label;
    private final JLabel nameLabel;
    private Player owner;
    private int row;
    private int col;
    private int currentPosition;

    public Card(GameObject gameObject, Player owner, int row, int col, int currentPosition) {
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
        this.setBackground(Color.LIGHT_GRAY);
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
                TransferHandler handler = comp.getTransferHandler();
                handler.exportAsDrag(comp, e, TransferHandler.MOVE);
            }
        });

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Card comp = (Card) e.getSource();
                if (comp.getGameObject() != null) {
                    JOptionPane.showMessageDialog(comp.getParent().getParent(), "GameObject: " + comp.getGameObject().getName(),
                            "Pop up", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    System.out.println("NULL");
                }
            }
        });
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
        graphics.setStroke(new BasicStroke((float)1.5));
        graphics.drawRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height); // border
    }

    public void updateCardDisplay() {
        if (gameObject != null) {
            ImageIcon icon = new ImageIcon(this.getClass().getResource(gameObject.getImgUrlPath()));
            Image image = icon.getImage().getScaledInstance(75, 75, java.awt.Image.SCALE_SMOOTH);
            label.setIcon(new ImageIcon(image));
            label.setHorizontalAlignment(JLabel.CENTER);
            nameLabel.setText("<html><body style='text-align:center'>" + this.gameObject.getName() + "</body></html>");
        } else {
            label.setIcon(null);
            nameLabel.setText("");
        }
    }

    public void setGameObject(GameObject gameObject) {
        this.gameObject = gameObject;
        if (this.gameObject != null) {
            this.gameObject.setParent(this);
        }

        System.out.println("Card at (" + row + "," + col + ") set to " + (gameObject == null ? "null" : gameObject.getName()));

        updateCardDisplay();
    }

    public GameObject getGameObject() {
        return gameObject;
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
}
