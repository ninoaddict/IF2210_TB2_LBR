package org.lbr.gui;

import javax.swing.*;

import org.lbr.ObjectFactory;
import org.lbr.gameobject.GameObject;
import org.lbr.gui.card.Card;
import org.lbr.player.Player;

import java.awt.*;
import java.util.ArrayList;

public class ShuffleDialog extends JFrame {
    private JPanel roundedPanel;
    private final ArrayList<Card> cardList;
    private final int needed;

    public ShuffleDialog(Player player, MainWindow frame, MainFrame mainFrame) {
        this.setUndecorated(true);
        this.setSize(new Dimension(300, 400));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(frame);
        this.setOpacity((float)0.95);
        cardList = new ArrayList<>();

        roundedPanel = new DialogPanel(300, 400, new Color(230, 230, 230), new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 5, 10, 5);
        // check how many needed card
        int neededCard = 4;
        if (player.getRemainingHand() < 4) neededCard = player.getRemainingHand();
        this.needed = neededCard;
        ArrayList<GameObject> arr = ObjectFactory.getShuffleCards(neededCard);

        c.gridx = 0;
        c.gridy = 0;

        if (neededCard == 1) {
            c.gridwidth = 2;
            Card card = new Card(arr.getFirst(), player, 0, 0, Card.SHUFFLE, false);
            cardList.add(card);
            roundedPanel.add(card, c);
            c.gridheight = 1;
            c.gridwidth = 1;
        } else if (neededCard == 2) {
            c.gridwidth = 2;
            Card card1 = new Card(arr.getFirst(), player, 0, 0, Card.SHUFFLE, false);
            roundedPanel.add(card1, c);
            c.gridy = 1;
            Card card2 = new Card(arr.get(1), player, 0, 0, Card.SHUFFLE, false);
            roundedPanel.add(card2, c);
            c.gridwidth = 1;
            cardList.add(card1);
            cardList.add(card2);
        } else if (neededCard == 3) {
            Card card1 = new Card(arr.getFirst(), player, 0, 0, Card.SHUFFLE, false);
            roundedPanel.add(card1, c);
            c.gridx = 1;
            Card card2 = new Card(arr.get(1), player, 0, 0, Card.SHUFFLE, false);
            roundedPanel.add(card2, c);
            c.gridy = 1;
            c.gridx = 0;
            c.gridwidth = 2;
            Card card3 = new Card(arr.get(2), player, 0, 0, Card.SHUFFLE, false);
            roundedPanel.add(card3, c);
            c.gridwidth = 1;
            cardList.add(card1);
            cardList.add(card2);
            cardList.add(card3);
        } else if (neededCard >= 4){
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 2; j++) {
                    c.gridx = i;
                    c.gridy = j;
                    Card card = new Card(arr.get(i * 2 + j), player, i, j, Card.SHUFFLE, false);
                    roundedPanel.add(card, c);
                    cardList.add(card);
                }
            }
        }

        if (neededCard > 0) {
            c.gridx = 0;
            c.gridy++;
            JButton shuffleButton = new JButton();
            shuffleButton.setFocusPainted(false);
            shuffleButton.setContentAreaFilled(false);
            shuffleButton.setMargin(new Insets(0, 0, 0, 0));
            shuffleButton.setOpaque(false);
            ImageIcon icon = new ImageIcon(this.getClass().getResource("/images/shuffle.png"));
            Image image = icon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
            shuffleButton.setIcon(new ImageIcon(image));
            shuffleButton.addActionListener(e -> {
                ArrayList<GameObject> newObjList = ObjectFactory.getShuffleCards(needed);
                for (int i = 0; i < needed; i++) {
                    cardList.get(i).setGameObject(newObjList.get(i));
                }
            });
            roundedPanel.add(shuffleButton, c);

            c.gridx++;
            JButton confirmButton = new JButton();
            confirmButton.setFocusPainted(false);
            confirmButton.setContentAreaFilled(false);
            confirmButton.setMargin(new Insets(0, 0, 0, 0));
            confirmButton.setOpaque(false);
            icon = new ImageIcon(this.getClass().getResource("/images/check-mark.png"));
            image = icon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
            confirmButton.setIcon(new ImageIcon(image));
            confirmButton.addActionListener(e -> {
                for (int i = 0; i < needed; i++) {
                    try {
                        player.addToHandDeck(cardList.get(i).getGameObject());
                    } catch (Exception e1) {
                        // TODO: handle
                        JOptionPane.showMessageDialog(mainFrame, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                player.decreaseDeckRemaining(needed);
                frame.updatePlayerHandDisplay();
                frame.setRemainingDeck(player.getRemainingDeck());
                mainFrame.setEnabled(true);
                this.dispose();
                frame.considerBearAttack();
            });
            roundedPanel.add(confirmButton, c);

        }
        else {
            JLabel nino = new JLabel();
            nino.setText("Tidak cukup ruang pada hand Anda");
            nino.setFont(new Font("Linux Libertine", 1, 16));
            nino.setForeground(Color.BLACK);
            roundedPanel.add(nino, c);

            c.gridy++;
            JButton confirmButton = new JButton();
            confirmButton.setFocusPainted(false);
            confirmButton.setContentAreaFilled(false);
            confirmButton.setMargin(new Insets(0, 0, 0, 0));
            confirmButton.setOpaque(false);
            ImageIcon icon = new ImageIcon(this.getClass().getResource("/images/check-mark.png"));
            Image image = icon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
            confirmButton.setIcon(new ImageIcon(image));
            confirmButton.addActionListener(e -> {
                mainFrame.setEnabled(true);
                this.dispose();
                frame.considerBearAttack();
            });
            roundedPanel.add(confirmButton, c);
        }

        this.add(roundedPanel);
    }
}