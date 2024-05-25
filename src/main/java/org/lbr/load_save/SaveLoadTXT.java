package org.lbr.load_save;

import com.sun.source.doctree.EscapeTree;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SaveLoadTXT implements SaveLoad {
    public SaveLoadTXT() {

    }

    @Override
    public void onSave(String directory, GameData gameData, PlayerData player1, PlayerData player2) throws Exception {
        // initialize file writer

        // write gameData
        File gameStateFile = new File(directory + "/" + "gameState.txt");
        FileWriter gameStateWriter = new FileWriter(gameStateFile);
        gameStateFile.createNewFile();
        gameStateWriter.write(Integer.toString(gameData.currentTurn));
        gameStateWriter.write("\n");
        gameStateWriter.write(Integer.toString(gameData.shop.size()));
        gameStateWriter.write("\n");
        for (Map.Entry<String, Integer> entry : gameData.shop.entrySet()) {
            String key = entry.getKey();
            int value = entry.getValue();
            gameStateWriter.write(key);
            gameStateWriter.write(" ");
            gameStateWriter.write(Integer.toString(value));
            gameStateWriter.write("\n");
        }
        gameStateWriter.close();

        // write player 1 data
        File player1File = new File(directory + "/" + "player1.txt");
        FileWriter player1Writer = new FileWriter(player1File);
        player1File.createNewFile();
        player1Writer.write(Integer.toString(player1.gulden));
        player1Writer.write("\n");
        player1Writer.write(Integer.toString(player1.deck));
        player1Writer.write("\n");

        player1Writer.write(Integer.toString(player1.activeDeck.size()));
        player1Writer.write("\n");
        for (int i = 0; i < player1.activeDeck.size(); i++) {
            player1Writer.write(player1.activeDeck.get(i).coordinate.toString());
            player1Writer.write(" ");
            player1Writer.write(player1.activeDeck.get(i).name);
            player1Writer.write("\n");
        }

        player1Writer.write(Integer.toString(player1.field.size()));
        player1Writer.write("\n");
        for (int i = 0; i < player1.field.size(); i++) {
            player1Writer.write(player1.field.get(i).coordinate.toString());
            player1Writer.write(" ");
            player1Writer.write(player1.field.get(i).name);
            player1Writer.write(" ");
            player1Writer.write(Integer.toString(player1.field.get(i).weight_or_age));
            player1Writer.write(" ");
            player1Writer.write(Integer.toString(player1.field.get(i).item.size()));
            player1Writer.write(" ");
            for (int j = 0; j < player1.field.get(i).item.size(); j++) {
                player1Writer.write(player1.field.get(i).item.get(j));
                player1Writer.write(" ");
            }
            player1Writer.write("\n");
        }
        player1Writer.close();

        // write player 2 data
        File player2File = new File(directory + "/" + "player2.txt");
        player2File.createNewFile();
        FileWriter player2Writer = new FileWriter(player2File);
        player2Writer.write(Integer.toString(player2.gulden));
        player2Writer.write("\n");
        player2Writer.write(Integer.toString(player2.deck));
        player2Writer.write("\n");
        player2Writer.write(Integer.toString(player2.activeDeck.size()));
        player2Writer.write("\n");
        for (int i = 0; i < player2.activeDeck.size(); i++) {
            player2Writer.write(player2.activeDeck.get(i).coordinate.toString());
            player2Writer.write(" ");
            player2Writer.write(player2.activeDeck.get(i).name);
            player2Writer.write("\n");
        }

        player2Writer.write(Integer.toString(player2.field.size()));
        player2Writer.write("\n");
        for (int i = 0; i < player2.field.size(); i++) {
            player2Writer.write(player2.field.get(i).coordinate.toString());
            player2Writer.write(" ");
            player2Writer.write(player2.field.get(i).name);
            player2Writer.write(" ");
            player2Writer.write(Integer.toString(player2.field.get(i).weight_or_age));
            player2Writer.write(" ");
            player2Writer.write(Integer.toString(player2.field.get(i).item.size()));
            player2Writer.write(" ");
            for (int j = 0; j < player2.field.get(i).item.size(); j++) {
                player2Writer.write(player2.field.get(i).item.get(j));
                player2Writer.write(" ");
            }
            player2Writer.write("\n");
        }
        player2Writer.close();
    }

    @Override
    public void onLoad(String directory, GameData gameData, PlayerData player1, PlayerData player2) throws Exception {
        // initialize scanner
        Scanner sc;

        // read game data
        File gameStateFile = new File(directory + "/" + "gamestate.txt");
        sc = new Scanner(gameStateFile);
        int currentTurn = sc.nextInt();

        HashMap<String, Integer> shop = new HashMap<>();
        int numOfShopItem = sc.nextInt();
        for (int i = 0; i < numOfShopItem; i++) {
            String itemName = sc.next();
            int quantity = sc.nextInt();
            shop.put(itemName, quantity);
        }

        gameData.currentTurn = currentTurn;
        gameData.shop = shop;

        // read player 1 data
        File player1File = new File(directory + "/" + "player1.txt");
        sc = new Scanner(player1File);
        int gulden1 = sc.nextInt();
        int numOfDeck1 = sc.nextInt();

        int numOfHandItem1 = sc.nextInt();
        ArrayList<HandData> activeDeck1 = new ArrayList<>();
        for (int i = 0; i < numOfHandItem1; i++) {
            String coor = sc.next();
            String name = sc.next();
            HandData curr = new HandData(Coordinate.translate(coor), name);
            activeDeck1.add(curr);
        }

        int numOfField1 = sc.nextInt();
        ArrayList<FieldData> field1 = new ArrayList<>();
        for (int i = 0; i < numOfField1; i++) {
            String coor = sc.next();
            String name = sc.next();
            int ageOrWeight = sc.nextInt();
            int numOfPotion = sc.nextInt();
            ArrayList<String> potions = new ArrayList<>();

            for (int j = 0; j < numOfPotion; j++) {
                potions.add(sc.next());
            }
            field1.add(new FieldData(Coordinate.translate(coor), name, ageOrWeight, potions));
        }
        player1.gulden = gulden1;
        player1.deck = numOfDeck1;
        player1.activeDeck = activeDeck1;
        player1.field = field1;

        // read player 2 data
        File player2File = new File(directory + "/" + "player2.txt");
        sc = new Scanner(player2File);
        int gulden2 = sc.nextInt();
        int numOfDeck2 = sc.nextInt();

        int numOfHandItem2 = sc.nextInt();
        ArrayList<HandData> activeDeck2 = new ArrayList<>();
        for (int i = 0; i < numOfHandItem2; i++) {
            String coor = sc.next();
            String name = sc.next();
            HandData curr = new HandData(Coordinate.translate(coor), name);
            activeDeck2.add(curr);
        }

        int numOfField2 = sc.nextInt();
        ArrayList<FieldData> field2 = new ArrayList<>();
        for (int i = 0; i < numOfField2; i++) {
            String coor = sc.next();
            String name = sc.next();
            int ageOrWeight = sc.nextInt();
            int numOfPotion = sc.nextInt();
            ArrayList<String> potions = new ArrayList<>();

            for (int j = 0; j < numOfPotion; j++) {
                potions.add(sc.next());
            }
            field2.add(new FieldData(Coordinate.translate(coor), name, ageOrWeight, potions));
        }
        player2.gulden = gulden2;
        player2.deck = numOfDeck2;
        player2.activeDeck = activeDeck2;
        player2.field = field2;

        // close scanner
        sc.close();
    }

    @Override
    public String extensionType() {
        return "TXT";
    }
}
