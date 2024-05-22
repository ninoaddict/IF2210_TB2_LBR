package org.lbr;

import org.lbr.gui.MainFrame;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
        	GameEngine gameEngine = new GameEngine();
        	MainFrame frame = new MainFrame(gameEngine);
            
        });
    }
}