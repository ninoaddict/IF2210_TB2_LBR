package org.lbr.gui;

import org.lbr.BearAttack;
import org.lbr.gameobject.cultivable.animal.Omnivore;
import org.lbr.gameobject.item.*;
import org.lbr.gui.card.Card;
import org.lbr.player.Player;
import org.lbr.GameEngine;
import org.lbr.gameobject.cultivable.Cultivable;
import org.lbr.gameobject.cultivable.animal.Animal;
import org.lbr.gameobject.item.Delay;
import org.lbr.gameobject.item.Destroy;
import org.lbr.gameobject.item.Item;
import org.lbr.gameobject.product.Product;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;

class RoundButton extends JButton {
    public RoundButton(String label) {
        super(label);
        this.setFont(new Font("Linux Libertine", 1, 16));
        this.setPreferredSize(new Dimension(50, 40));
        this.setBackground(Color.white);
        this.setForeground(Color.white);
        this.setText(label);
        this.setFocusPainted(false);

        //this.setForeground(Color.black);
        this.setOpaque(false);

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
        g.fillRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height);

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
    //    public panel_with_image mainPanel;
    BufferedImage curBufferedImage;
    private GameEngine gameEngine;
    private JPanel panel_atas;
    private JPanel panel_tengah;
    private JPanel panel_bawah;
    private ArrayList<Card> handPlayer1;
    private ArrayList<Card> handPlayer2;
    private ArrayList<ArrayList<Card>> fieldPlayer1;
    private ArrayList<ArrayList<Card>> fieldPlayer2;
    private ArrayList<Card> shopCard;
    private String shopString = "SHOP";
    private String fieldOneString = "FIELD1";
    private String fieldTwoString = "FIELD2";
    private String deckOneString = "DECK1";
    private String deckTwoString = "DECK2";
    private MainFrame mainFrame;
    JLabel howMuchHisMoneyJLabel;
    JLabel howMuchMyMoneyJLabel;
    private int secondRemaining = 50;
    private boolean timerVisited = false;
    ScheduledExecutorService executor;
    Runnable bearAttack;
    private boolean executorUsed = false;
    private boolean canTransferNow = true;
    private RoundButton[] roundButtons = new RoundButton[6];
    private RoundButton roundButton;
    private JLabel labelNext;
    private CardLayout deckCardLayout;
    private CardLayout cardLayout;
    private JPanel card_grid_panel;
    private long last_bear_attack = -1;
    private JLabel temp;
    private JLabel temp2;
    private JLabel deck_remaining_label;

    MainWindow(GameEngine ge) {
        try {
            gameEngine = ge;
            curBufferedImage = resize(ImageIO.read(this.getClass().getResource("/images/bgguioopatl1.jpg")), 800, 800);
            handPlayer1 = new ArrayList<>();
            handPlayer2 = new ArrayList<>();
            shopCard = new ArrayList<>();
            fieldPlayer1 = new ArrayList<>(4);
            fieldPlayer2 = new ArrayList<>(4);

            try {
                AudioInputStream audioInputStream =
                        AudioSystem.getAudioInputStream(this.getClass().getResource("/music/background_music.wav"));
                Clip clip = AudioSystem.getClip();

                Thread uwu = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            clip.open(audioInputStream);
                            clip.loop(Clip.LOOP_CONTINUOUSLY);
                        } catch (Exception e) {
                            // System.out.println(e.getMessage());
                        }
                    }
                });
                uwu.start();
            } catch (Exception e) {
                // System.out.println(e.getMessage());
                e.printStackTrace();
            }

            for (int i = 0; i < 4; i++) {
                ArrayList<Card> temp1 = new ArrayList<>(5);
                ArrayList<Card> temp2 = new ArrayList<>(5);
                for (int j = 0; j < 5; j++) {
                    temp1.add(null);
                    temp2.add(null);
                }
                fieldPlayer1.add(temp1);
                fieldPlayer2.add(temp2);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        initComponent();
    }

    public void initComponent() {
        this.setBackground(new Color(170, 193, 237));
        this.setPreferredSize(new Dimension(800, 600));
        this.setLayout(new GridBagLayout());
        panel_atas = new JPanel();
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

        card_grid_panel = new JPanel();
        card_grid_panel.setBackground(new Color(0, 108, 103));
        card_grid_panel.setOpaque(false);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.weighty = 1.0;

        cardLayout = new CardLayout();

        card_grid_panel.setLayout(cardLayout);

        panel_tengah.add(card_grid_panel, gridBagConstraints);

        gridBagConstraints.gridx = 1;
        gridBagConstraints.weightx = 1.0;

        JPanel button_grid_panel = new JPanel();
        button_grid_panel.setBackground(Color.MAGENTA);
        button_grid_panel.setOpaque(false);

        panel_tengah.add(button_grid_panel, gridBagConstraints);

        JPanel real_card_gridJPanel = new JPanel();
        real_card_gridJPanel.setBackground(new Color(0, 108, 103));
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

        JPanel player_two_fieldJPanel = new JPanel();
        player_two_fieldJPanel.setBackground(Color.yellow);
        player_two_fieldJPanel.setOpaque(false);

        player_two_fieldJPanel.setLayout(new GridBagLayout());


        real_card_gridJPanel.setLayout(new GridBagLayout());

        gridBagConstraints = new GridBagConstraints();

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.insets = new Insets(10, 10, 10, 10);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.insets = new Insets(7, 7, 7, 7);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                gridBagConstraints.gridx = j;
                gridBagConstraints.gridy = i;
                Card card = new Card(null, gameEngine.getCurrPlayer(), i, j, Card.FIELD, true);
                real_card_gridJPanel.add(card, gridBagConstraints);
                fieldPlayer1.get(i).set(j, card);
            }
        }

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.insets = new Insets(7, 7, 7, 7);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;


        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                gridBagConstraints.gridx = j;
                gridBagConstraints.gridy = i;
                Card card = new Card(null, gameEngine.getPlayerAtIndex(1), i, j, Card.FIELD, true);
                player_two_fieldJPanel.add(card, gridBagConstraints);
                fieldPlayer2.get(i).set(j, card);
            }
        }

        card_grid_panel.add(fieldOneString, real_card_gridJPanel);

        card_grid_panel.add(fieldTwoString, player_two_fieldJPanel);

        card_grid_panel.add(shopString, shop_card_gridJPanel);

        cardLayout.show(card_grid_panel, fieldOneString);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.insets = new Insets(7, 7, 7, 7);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;

        ArrayList<String> productNameStrings = new ArrayList<String>();

        Map<Product, Integer> hMap = gameEngine.getShop().getProducts();

        for (Product name_product : hMap.keySet()) {
            productNameStrings.add(name_product.getName().toUpperCase().replace(' ', '_'));
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                gridBagConstraints.gridx = j;
                gridBagConstraints.gridy = i;
                Card card = new Card(new Product(productNameStrings.get(i * 3 + j)), null, i, j, Card.SHOP, 0);
                shopCard.add(card);
                shop_card_gridJPanel.add(card, gridBagConstraints);
            }
        }

//        panel_bawah.setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, Color.WHITE));

        deckCardLayout = new CardLayout();
        panel_bawah.setLayout(deckCardLayout);

        JPanel first_deck_playerJPanel = new JPanel();
        JPanel second_deck_playerJPanel = new JPanel();

        first_deck_playerJPanel.setBackground(Color.red);
        first_deck_playerJPanel.setPreferredSize(new Dimension(100, 140));
        first_deck_playerJPanel.setOpaque(false);

        second_deck_playerJPanel.setBackground(Color.red);
        second_deck_playerJPanel.setPreferredSize(new Dimension(100, 140));
        second_deck_playerJPanel.setOpaque(false);

        first_deck_playerJPanel.setLayout(new GridBagLayout());
        second_deck_playerJPanel.setLayout(new GridBagLayout());

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.insets = new Insets(7, 27, 7, 7);
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        temp = new JLabel();
        temp.setForeground(Color.WHITE);
        temp.setFont(new Font("Linux Libertine", Font.BOLD, 16));
        temp.setVerticalAlignment(JLabel.TOP);
        temp.setBackground(Color.yellow);
        temp.setOpaque(true);

        for (int i = 0; i < 6; i++) {
            gridBagConstraints.gridx = i;
            Card card = new Card(null, gameEngine.getCurrPlayer(), 0, i, Card.DECK, true);
            first_deck_playerJPanel.add(card, gridBagConstraints);
            handPlayer1.add(card);
            if (i == 0) {
                gridBagConstraints.insets = new Insets(7, 7, 7, 7);
            }
        }


        gridBagConstraints.gridx = 6;
        gridBagConstraints.weightx = 1.0;

        first_deck_playerJPanel.add(temp, gridBagConstraints);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.insets = new Insets(7, 27, 7, 7);
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        temp2 = new JLabel();
        for (int i = 0; i < 6; i++) {
            gridBagConstraints.gridx = i;
            Card card = new Card(null, gameEngine.getPlayerAtIndex(1), 0, i, Card.DECK, true);
            second_deck_playerJPanel.add(card, gridBagConstraints);
            handPlayer2.add(card);
            if (i == 0) {
                gridBagConstraints.insets = new Insets(7, 7, 7, 7);
            }
        }

        gridBagConstraints.gridx = 6;
        gridBagConstraints.weightx = 1.0;
        second_deck_playerJPanel.add(temp2, gridBagConstraints);

        panel_bawah.add(deckOneString, first_deck_playerJPanel);
        panel_bawah.add(deckTwoString, second_deck_playerJPanel);

        deckCardLayout.show(panel_bawah, deckOneString);

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

        JLabel timeJLabel = new JLabel("Time remaining: 5");
        timeJLabel.setBackground(new Color(170, 193, 237));
        timeJLabel.setOpaque(true);
        timeJLabel.setPreferredSize(new Dimension(30, 30));
        timeJLabel.setVisible(false);

        timeJLabel.setHorizontalAlignment(JLabel.CENTER);

        gridBagConstraints.gridy = 0;

        button_grid_panel.add(timeJLabel, gridBagConstraints);



        executor = Executors.newScheduledThreadPool(1);

        bearAttack = new Runnable() {
            public void run() {
                if (!timerVisited) {
                    secondRemaining = BearAttack.duration * 10;
                    timerVisited = true;
                    timeJLabel.setVisible(true);
                    executorUsed = true;
                    int x1 = BearAttack.x1;
                    int y1 = BearAttack.y1;
                    int x2 = BearAttack.x2;
                    int y2 = BearAttack.y2;
                    for(int i = y1; i <= y2; i++){
                        for(int j = x1; j <= x2; j++) {
                            int finalI = i;
                            int finalJ = j;
                            SwingUtilities.invokeLater(new Runnable() {
                                @Override
                                public void run() {
                                    int k = (gameEngine.getCurrTurn() - 1) % 2;
                                    if (k == 0) {
                                        fieldPlayer1.get(finalI).get(finalJ).setBackground(Color.red);
                                    } else {
                                        fieldPlayer2.get(finalI).get(finalJ).setBackground(Color.red);
                                    }
                                }
                            });
                        }
                    }
                }
                secondRemaining--;
                int fi = secondRemaining / 10;
                int se = secondRemaining % 10;
                timeJLabel.setText("Time remaining: " + Integer.toString(fi) + "," + Integer.toString(se));
                if (secondRemaining == 0) {
                    last_bear_attack = System.currentTimeMillis();
                    canTransferNow = false;
                    timeJLabel.setVisible(false);
                    timerVisited = false;
                    int x1 = BearAttack.x1;
                    int y1 = BearAttack.y1;
                    int x2 = BearAttack.x2;
                    int y2 = BearAttack.y2;
                    if (!BearAttack.execute(gameEngine.getCurrPlayer(), x1, x2, y1, y2, 50) ) {
                        for(int i = y1; i <= y2; i++){
                            for(int j = x1; j <= x2; j++) {
                                int finalI = i;
                                int finalJ = j;
                                SwingUtilities.invokeLater(new Runnable() {
                                    @Override
                                    public void run() {
                                        int k = (gameEngine.getCurrTurn() - 1) % 2;
                                        if (k == 0) {
                                            fieldPlayer1.get(finalI).get(finalJ).setBackground(new Color(224, 247, 250));
                                            fieldPlayer1.get(finalI).get(finalJ).setGameObject(fieldPlayer1.get(finalI).get(finalJ).getGameObject());
                                        } else {
                                            fieldPlayer2.get(finalI).get(finalJ).setBackground(new Color(224, 247, 250));
                                            fieldPlayer2.get(finalI).get(finalJ).setGameObject(fieldPlayer2.get(finalI).get(finalJ).getGameObject());

                                        }
                                    }
                                });
                            }
                        }
                        Omnivore bear = new Omnivore("BERUANG");
                        try {
                            gameEngine.getCurrPlayer().addToHandDeck(bear);
                        } catch (Exception ignored) {

                        }
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                updatePlayerHandDisplay();
                            }
                        });
                        canTransferNow = true;
                        executor.shutdown();
                        for(int i = 0; i < 6; i++){
                            roundButtons[i].setEnabled(true);
                        }
                        roundButton.setEnabled(true);
                        return;
                    }
                    for(int i = y1; i <= y2; i++){
                        for(int j = x1; j <= x2; j++) {
                            int finalI = i;
                            int finalJ = j;
                            SwingUtilities.invokeLater(new Runnable() {
                                @Override
                                public void run() {
                                    int k = (gameEngine.getCurrTurn() - 1) % 2;
                                    if (k == 0) {
                                        fieldPlayer1.get(finalI).get(finalJ).setBackground(new Color(224, 247, 250));
                                        if (fieldPlayer1.get(finalI).get(finalJ).getGameObject() != null && ((Cultivable)fieldPlayer1.get(finalI).get(finalJ).getGameObject()).getIsProtected()) {
                                            fieldPlayer1.get(finalI).get(finalJ).setGameObject(fieldPlayer1.get(finalI).get(finalJ).getGameObject());

                                        } else {
                                            fieldPlayer1.get(finalI).get(finalJ).setGameObject(null);
                                            try {
                                                fieldPlayer1.get(finalI).get(finalJ).getOwner().setNullField(finalI, finalJ);
                                            } catch (Exception ignored) {
                                                // 
                                            }
                                        }


                                    } else {
                                        fieldPlayer2.get(finalI).get(finalJ).setBackground(new Color(224, 247, 250));
                                        if (fieldPlayer2.get(finalI).get(finalJ).getGameObject() != null && ((Cultivable)fieldPlayer2.get(finalI).get(finalJ).getGameObject()).getIsProtected()) {
                                            fieldPlayer2.get(finalI).get(finalJ).setGameObject(fieldPlayer2.get(finalI).get(finalJ).getGameObject());
                                        } else {
                                            fieldPlayer2.get(finalI).get(finalJ).setGameObject(null);
                                            try {
                                                fieldPlayer2.get(finalI).get(finalJ).getOwner().setNullField(finalI, finalJ);
                                            } catch (Exception ignored) {

                                            }
                                        }


                                    }
                                }
                            });

                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {

                            }
                        }
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {

                        }
                    }
                    canTransferNow = true;
                    executor.shutdown();
                    for(int i = 0; i < 6; i++){
                        roundButtons[i].setEnabled(true);
                    }
                    roundButton.setEnabled(true);
                }
            }
        };

        for(int i = 1; i < 7; i++) {
        	gridBagConstraints.gridy = i;
        	RoundButton jtempButton = new RoundButton(button_name_array[i - 1]);
        	jtempButton.setForeground(Color.black);
        	button_grid_panel.add(jtempButton, gridBagConstraints);
        	roundButtons[i - 1] = jtempButton;
        }

        gridBagConstraints.gridy = 7;

        deck_remaining_label = new JLabel("DECK: 40/40");
        deck_remaining_label.setFont(new Font("Linux Libertine", Font.ITALIC | Font.BOLD, 14));

        deck_remaining_label.setBackground(new Color(228, 236, 252));
        deck_remaining_label.setOpaque(true);
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        deck_remaining_label.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));//top,left,bottom,right


        deck_remaining_label.setHorizontalAlignment(SwingConstants.CENTER);

        button_grid_panel.add(deck_remaining_label, gridBagConstraints);


        roundButtons[0].addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int k = (gameEngine.getCurrTurn() - 1) % 2;
                if (k == 0) {
                    cardLayout.show(card_grid_panel, fieldOneString);
                } else {
                    cardLayout.show(card_grid_panel, fieldTwoString);
                }
                roundButtons[0].setBackground(Color.green);
                roundButtons[1].setBackground(Color.white);
                roundButtons[2].setBackground(Color.white);
            }
        });

        roundButtons[1].addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int k = (gameEngine.getCurrTurn() - 1) % 2;
                if (k == 0) {
                    cardLayout.show(card_grid_panel, fieldTwoString);
                } else {
                    cardLayout.show(card_grid_panel, fieldOneString);
                }
                roundButtons[0].setBackground(Color.white);
                roundButtons[1].setBackground(Color.red);
                roundButtons[2].setBackground(Color.white);
            }
        });

        roundButtons[2].addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(card_grid_panel, shopString);
                roundButtons[0].setBackground(Color.white);
                roundButtons[1].setBackground(Color.white);
                roundButtons[2].setBackground(Color.green);
            }
        });

        roundButtons[3].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showSaveDialog();
            }
        });

        roundButtons[4].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showLoadDialog();
            }
        });

        roundButtons[5].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showPluginDialog();
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

        labelNext = new JLabel("Number of Turn: 1");
        labelNext.setFont(new Font("Linux Libertine", 1, 14));
        labelNext.setBackground(Color.red);
        labelNext.setOpaque(false);

        uwuPanel.add(labelNext, gridBagConstraints);

        roundButton = new RoundButton("Next Turn");
        roundButton.setFont(new Font("Linux Libertine", 1, 14));
        roundButton.setBackground(Color.black);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.insets = new Insets(3, 10, 3, 10);

        uwuPanel.add(roundButton, gridBagConstraints);

        roundButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int k = (gameEngine.getCurrTurn() - 1) % 2;
                if (k == 0) {
                    cardLayout.show(card_grid_panel, fieldTwoString);
                    deckCardLayout.show(panel_bawah, deckTwoString);
                } else {
                    cardLayout.show(card_grid_panel, fieldOneString);
                    deckCardLayout.show(panel_bawah, deckOneString);
                }

                gameEngine.nextTurn();

                labelNext.setText("Number of Turn: " + Integer.toString(gameEngine.getCurrTurn()));
                setRemainingDeck(gameEngine.getCurrPlayer().getRemainingDeck());

                roundButtons[0].setBackground(Color.green);
                roundButtons[1].setBackground(Color.white);
                roundButtons[2].setBackground(Color.white);
                if (gameEngine.getCurrTurn() > 20) {
                    win(gameEngine.getWinner());
                } else {
                    start();
                    updatePlayerFieldDisplay();
                }
            }
        });

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
        Image newimg = image.getScaledInstance(15, 15, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        imageIcon = new ImageIcon(newimg);  // transform it back

        JLabel myselfMoniesJPanel = new JLabel("Player 1");
        myselfMoniesJPanel.setFont(new Font("Linux Libertine", 1, 16));;
        myselfMoniesJPanel.setBackground(Color.green);
        myselfMoniesJPanel.setOpaque(false);
        newGridBagConstraints.insets = new Insets(0, 10, 0, 0);
        moniesJPanel.add(myselfMoniesJPanel, newGridBagConstraints);

        JLabel hisMoniesJPanel = new JLabel("Player 2");
        hisMoniesJPanel.setBackground(Color.red);
        hisMoniesJPanel.setFont(new Font("Linux Libertine", 1, 16));
        hisMoniesJPanel.setOpaque(false);
        newGridBagConstraints.gridy = 1;

        moniesJPanel.add(hisMoniesJPanel, newGridBagConstraints);

        howMuchMyMoneyJLabel = new JLabel(Integer.toString(gameEngine.getPlayerAtIndex(0).getGulden()));
        howMuchMyMoneyJLabel.setFont(new Font("Linux Libertine", 1, 16));
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
        howMuchHisMoneyJLabel.setFont(new Font("Linux Libertine", 1, 16));
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
        roundButtons[0].setBackground(Color.green);
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

    public void setMainFrame(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    public void buyProduct(Product product, int idx) throws Exception {
        gameEngine.getCurrPlayer().buy(product, gameEngine.getShop(), idx);
        int k = (gameEngine.getCurrTurn() - 1) % 2;
        if (k == 0) {
            this.howMuchMyMoneyJLabel.setText(Integer.toString(gameEngine.getCurrPlayer().getGulden()));
        } else {
            this.howMuchHisMoneyJLabel.setText(Integer.toString(gameEngine.getCurrPlayer().getGulden()));
        }
    }

    public void sellProduct(Product product, int idx) throws Exception {
        gameEngine.getCurrPlayer().sell(idx, gameEngine.getShop());
        int k = (gameEngine.getCurrTurn() - 1) % 2;
        if (k == 0) {
            this.howMuchMyMoneyJLabel.setText(Integer.toString(gameEngine.getCurrPlayer().getGulden()));
        } else {
            this.howMuchHisMoneyJLabel.setText(Integer.toString(gameEngine.getCurrPlayer().getGulden()));
        }
    }

    public void swapDeck(int from, int col) throws Exception {
        gameEngine.getCurrPlayer().swap_deck(from, col);
    }

    public void from_deck_to_field(int fromCol, int toRow, int toCol) throws Exception {
        gameEngine.getCurrPlayer().from_deck_to_field(fromCol, toRow, toCol);
    }

    public void swapField(int rowFrom, int colFrom, int rowTo, int colTo) {
        gameEngine.getCurrPlayer().swap_field(rowFrom, colFrom, rowTo, colTo);
    }

    public void showSaveDialog() {
        mainFrame.setEnabled(false);
        SaveDialog sld = new SaveDialog(gameEngine, mainFrame, this);
        sld.setVisible(true);
    }

    public void showLoadDialog() {
        mainFrame.setEnabled(false);
        LoadDialog ld = new LoadDialog(gameEngine, mainFrame, this);
        ld.setVisible(true);
    }

    public void showPluginDialog() {
        mainFrame.setEnabled(false);
        PluginDialog pd = new PluginDialog(gameEngine, mainFrame, this);
        pd.setVisible(true);
    }

    public void start() {
        mainFrame.setEnabled(false);
        ShuffleDialog shuffle = new ShuffleDialog(gameEngine.getCurrPlayer(), this, mainFrame);
        shuffle.setVisible(true);
    }

    public void win(String winner) {
        mainFrame.setEnabled(false);
        WinnerDialog wd = new WinnerDialog(winner, mainFrame);
        wd.setVisible(true);
    }

    public void considerBearAttack() {
        Random rand = new Random();
        int bear_attack_pos = rand.nextInt(11);
        if (bear_attack_pos >= 3) return;
        for(int i = 0; i < 6; i++){
            roundButtons[i].setEnabled(false);
        }
        roundButton.setEnabled(false);
        BearAttack.refresh();
        if (executorUsed) executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(bearAttack, 100, 100, TimeUnit.MILLISECONDS);
    }

    public void updatePlayerHandDisplay() {
        Player curr = gameEngine.getCurrPlayer();
        int currTurn = gameEngine.getCurrTurn();
        for (int i = 0; i < 6; i++) {
            if (currTurn % 2 == 1) {
                handPlayer1.get(i).setGameObject(curr.getHandIdx(i));
            } else {
                handPlayer2.get(i).setGameObject(curr.getHandIdx(i));
            }
        }
    }

    public void updatePlayerFieldDisplay() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                fieldPlayer1.get(i).get(j).updateCardDisplay();
                fieldPlayer2.get(i).get(j).updateCardDisplay();
            }
        }
    }

    public void updateGameDisplay() {
        howMuchMyMoneyJLabel.setText(Integer.toString(gameEngine.getPlayerAtIndex(0).getGulden()));
        howMuchHisMoneyJLabel.setText(Integer.toString(gameEngine.getPlayerAtIndex(1).getGulden()));
        labelNext.setText("Number of Turn: " + Integer.toString(gameEngine.getCurrTurn()));

        // update hand
        for (int i = 0; i < 6; i++) {
            handPlayer1.get(i).setGameObject(gameEngine.getPlayerAtIndex(0).getHandIdx(i));
            handPlayer2.get(i).setGameObject(gameEngine.getPlayerAtIndex(1).getHandIdx(i));
        }

        // update field
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                fieldPlayer1.get(i).get(j).setGameObject(gameEngine.getPlayerAtIndex(0).getCultivable(i, j));
                fieldPlayer2.get(i).get(j).setGameObject(gameEngine.getPlayerAtIndex(1).getCultivable(i, j));
            }
        }

        int u = (gameEngine.getCurrTurn() - 1) % 2;
        if (u == 0) {
            cardLayout.show(card_grid_panel, fieldOneString);
            deckCardLayout.show(panel_bawah, deckOneString);
        } else {
            cardLayout.show(card_grid_panel, fieldTwoString);
            deckCardLayout.show(panel_bawah, deckTwoString);
        }

        // update shop
        Map<Product, Integer> mp = gameEngine.getShop().getProducts();
        int idx = 0;
        for (Map.Entry<Product, Integer> entry : mp.entrySet()) {
            shopCard.get(idx).setGameObject(new Product(entry.getKey()));
            shopCard.get(idx).updateStock(entry.getValue());
            shopCard.get(idx).updatePrice(entry.getKey().getPrice());
            idx++;
        }
        setRemainingDeck(gameEngine.getCurrPlayer().getRemainingDeck());
    }

    public boolean productDrop(Player cardOwner, Product dropProduct, Animal animal, int colDeck) {
        if (!cardOwner.equals(gameEngine.getCurrPlayer())) {
            JOptionPane.showMessageDialog(mainFrame, "Cannot give food to enemy's field", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        try {
            animal.eat(dropProduct);
            gameEngine.getCurrPlayer().setHandIdx(null, colDeck);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(mainFrame, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    public boolean itemDrop(Player cardOwner, Item dropItem, Cultivable cultivableObj, int colIdx, int row, int col) {
        if (dropItem instanceof Delay || dropItem instanceof Destroy) {
            if (cardOwner.equals(gameEngine.getCurrPlayer())) {
                JOptionPane.showMessageDialog(mainFrame, "Cannot put Delay and Destroy on your own field", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            if (dropItem instanceof Delay) {
                try {
                    dropItem.runEffect(cultivableObj);
                    gameEngine.getCurrPlayer().setHandIdx(null, colIdx);
                    return true;
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(mainFrame, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    return false;
                }

            } else {
                try {
                    dropItem.runEffect(cultivableObj);
                    gameEngine.getCurrPlayer().setHandIdx(null, colIdx);
                    cardOwner.setNullField(row, col);
                    return true;
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(mainFrame, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            }
        } else {
            if (!cardOwner.equals(gameEngine.getCurrPlayer())) {
                JOptionPane.showMessageDialog(mainFrame, "Cannot put item on enemy's field", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        try {
            dropItem.runEffect(cultivableObj);
            gameEngine.getCurrPlayer().setHandIdx(null, colIdx);

            // check if dropItem is instant harvest
            if (dropItem instanceof InstantHarvest) {
                cardOwner.harvest(row, col);
                updatePlayerHandDisplay();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(mainFrame, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    public void setRemainingDeck(int remaining_deck) {
        deck_remaining_label.setText("DECK: " + remaining_deck + "/40");
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(curBufferedImage, 0, 0, null);
    }

    public synchronized boolean canTransfer() {
        return canTransferNow;
    }

    public synchronized  long getLastBearAttack() {
        return last_bear_attack;
    }
}
