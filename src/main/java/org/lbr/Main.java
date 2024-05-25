package org.lbr;

import org.lbr.gui.MainFrame;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
        	GameEngine gameEngine = new GameEngine();
        	MainFrame frame = new MainFrame(gameEngine);
        });
    }
}