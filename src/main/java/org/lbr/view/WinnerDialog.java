package org.lbr.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class WinnerDialog extends JFrame {

    public WinnerDialog(String title, MainFrame mainFrame) {
        this.setUndecorated(true);
        this.setSize(new Dimension(500, 350));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(mainFrame);

        JPanel roundedPanel = new DialogPanel(400, 300, new Color(230, 230, 230), new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 5, 5, 5);
        c.gridx = 0;
        c.gridy = 0;

        add(roundedPanel);

        JLabel titleLabel = new JLabel("CONGRATULATIONS!");
        titleLabel.setFont(new Font("Linux Libertine", 1, 30));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        roundedPanel.add(titleLabel, c);

        c.gridy = 1;
        ImageIcon icon = new ImageIcon(this.getClass().getResource("/images/emilia.png"));
        Image image = icon.getImage().getScaledInstance(350, 197, Image.SCALE_SMOOTH);
        JLabel imageLabel = new JLabel();
        imageLabel.setIcon(new ImageIcon(image));
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        roundedPanel.add(imageLabel, c);

        c.gridy = 2;
        c.gridx = 0;
        JLabel playerName = new JLabel(title + " Wins");
        if (title.equals("Draw")) {
            playerName.setText("It's a Draw");
        }
        playerName.setHorizontalAlignment(SwingConstants.CENTER);
        playerName.setFont(new Font("Linux Libertine", 1, 24));
        roundedPanel.add(playerName, c);

        c.gridy = 3;
        RoundedButton okButton = new RoundedButton("Close", new Insets(0, 16, 0, 16), 16, 8);
        okButton.addMouseListener(new MouseAdapter() {
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

            @Override
            public void mouseEntered(MouseEvent e) {
                JButton curr =(JButton) e.getSource();
                curr.setBackground(Color.GRAY);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                JButton curr =(JButton) e.getSource();
                curr.setBackground(Color.LIGHT_GRAY);
            }
        });
        okButton.addActionListener(e -> {
            dispose();
            mainFrame.dispose();
        });
        roundedPanel.add(okButton, c);
    }
}
