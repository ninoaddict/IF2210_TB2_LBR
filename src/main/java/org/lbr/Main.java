package org.lbr;

import org.lbr.controller.GameEngine;
import org.lbr.view.MainFrame;

import javax.swing.*;
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
        	GameEngine gameEngine = GameEngine.getInstance();
        	MainFrame frame = new MainFrame(gameEngine);
        });
    }
}