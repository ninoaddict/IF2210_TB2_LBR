package org.lbr.gui;

import org.lbr.gameobject.GameObject;
import org.lbr.gameobject.cultivable.Cultivable;
import org.lbr.gameobject.cultivable.animal.Animal;
import org.lbr.gameobject.cultivable.plant.Plant;
import org.lbr.gameobject.item.Item;
import org.lbr.gameobject.item.Trap;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import org.lbr.gui.card.Card;
import org.lbr.player.Player;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class ObjectInfoDialog extends JFrame {
    private Card parent;
    public ObjectInfoDialog(Card parent) {
        this.parent = parent;
        this.setUndecorated(true);
        this.setSize(new Dimension(300, 200));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(parent.getParent().getParent().getParent());
        this.setOpacity((float) 0.96);

        GameObject gameObject = parent.getGameObject();

        JPanel roundedPanel = new DialogPanel(300, 200, new Color(230, 230, 230), new GridBagLayout());
        add(roundedPanel);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.gridheight = 7;
        gbc.weightx = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        JLabel imageLabel = new JLabel();
        ImageIcon icon = new ImageIcon(this.getClass().getResource(gameObject.getImgUrlPath()));
        if (gameObject instanceof Cultivable && ((Cultivable) gameObject).isReady()) {
            icon = new ImageIcon(this.getClass().getResource(((Cultivable) gameObject).getProduct().getImgUrlPath()));
        }
        Image image = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        imageLabel.setIcon(new ImageIcon(image));
        roundedPanel.add(imageLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.anchor = GridBagConstraints.WEST;
        JLabel title = new JLabel();
        title.setFont(new Font("Linux Libertine", 1, 20));
        title.setText(gameObject.getName());
        title.setBorder(BorderFactory.createEmptyBorder(0, 0, 8, 0));
        roundedPanel.add(title, gbc);

        if (gameObject instanceof Cultivable) {
            gbc.gridy++;
            Cultivable cultivable = (Cultivable) gameObject;
            JLabel age_or_weight = new JLabel();
            age_or_weight.setFont(new Font("Linux Libertine", 1, 16));
            if (cultivable instanceof Animal) {
                age_or_weight.setText("Weight: " + ((Animal) cultivable).getWeight());
            } else {
                age_or_weight.setText("Age: " + ((Plant) cultivable).getAge());
            }
            roundedPanel.add(age_or_weight, gbc);

            ArrayList<Item> cult = cultivable.getActiveItems();
            HashMap<String, Integer> mp = new HashMap<>();

            for (int i = 0; i < cult.size(); i++) {
                mp.put(cult.get(i).getName(), mp.getOrDefault(cult.get(i).getName(), 0) + 1);
            }
            gbc.gridy++;
            JLabel accelerateLabel = new JLabel();
            accelerateLabel.setFont(new Font("Linux Libertine", 1, 16));
            accelerateLabel.setText("Accelerate: " + mp.getOrDefault("Accelerate", 0));
            roundedPanel.add(accelerateLabel, gbc);

            gbc.gridy++;
            JLabel delay = new JLabel();
            delay.setFont(new Font("Linux Libertine", 1, 16));
            delay.setText("Delay: " + mp.getOrDefault("Delay", 0));
            roundedPanel.add(delay, gbc);

            gbc.gridy++;
            JLabel protect = new JLabel();
            protect.setFont(new Font("Linux Libertine", 1, 16));
            protect.setText("Protect: " + mp.getOrDefault("Protect", 0));
            roundedPanel.add(protect, gbc);

            gbc.gridy++;
            JLabel trap = new JLabel();
            trap.setFont(new Font("Linux Libertine", 1, 16));
            trap.setText("Trap: " + mp.getOrDefault("Trap", 0));
            trap.setBorder(BorderFactory.createEmptyBorder(0, 0, 8, 0));
            roundedPanel.add(trap, gbc);

            gbc.gridy++;
            JPanel bottom = new JPanel();
            bottom.setLayout(new GridBagLayout());
            roundedPanel.add(bottom, gbc);

            gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.insets = new Insets(0, 0, 0, 10);

            RoundedButton button = new RoundedButton("Panen");
            button.addActionListener(e -> {
                try {
                    parent.getOwner().harvest(parent.getRow(), parent.getCol());
                    MainWindow main = (MainWindow)parent.getParent().getParent().getParent().getParent();
                    main.updatePlayerHandDisplay();
                    parent.setGameObject(null);
                } catch (Exception ee) {
                }
                dispose();
            });

            if (!cultivable.isReady()) {
                button.setEnabled(false);
            }

            button.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    JButton curr =(JButton) e.getSource();
                    curr.setBackground(Color.GRAY);
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    JButton curr =(JButton) e.getSource();
                    curr.setBackground(Color.LIGHT_GRAY);
                }
            });
            bottom.add(button, gbc);
            gbc.gridx = 1;
            gbc.insets = new Insets(0, 0, 0, 0);
            RoundedButton buttonClose = new RoundedButton("Close");
            buttonClose.addActionListener(e -> {
                dispose();
            });
            buttonClose.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    JButton curr =(JButton) e.getSource();
                    curr.setBackground(Color.GRAY);
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    JButton curr =(JButton) e.getSource();
                    curr.setBackground(Color.LIGHT_GRAY);
                }
            });
            bottom.add(buttonClose, gbc);
        }
    }

    public ObjectInfoDialog getCurrent() {
        return this;
    }

    public void closeWindow() {
        dispose();
    }
}

class RoundedButton extends JButton {
    public RoundedButton(String label) {
        super(label);
        this.setMargin(new Insets(3, 5, 3, 5));
        this.setBackground(Color.LIGHT_GRAY);
        this.setForeground(Color.BLACK);
        this.setText(label);
        this.setFocusPainted(false);
        this.setBorderPainted(false);
        this.setOpaque(false);
        this.setContentAreaFilled(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Dimension arcs = new Dimension(15, 15);
        int width = getWidth();
        int height = getHeight();
        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setColor(getBackground());
        graphics.fillRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height); // paint background
        graphics.setColor(getForeground());
        super.paintComponent(g);
    }
}