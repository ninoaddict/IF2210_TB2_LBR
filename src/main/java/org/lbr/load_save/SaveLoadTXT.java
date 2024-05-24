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
    @Override
    public void onSave(String directory, GameData gameData, PlayerData player1, PlayerData player2) throws Exception {
        // initialize file writer
        FileWriter writer;

        // write gameData
        File gameStateFile = new File(directory + "/" + "gameState.txt");
        gameStateFile.createNewFile();
        writer = new FileWriter(gameStateFile);
        writer.write(gameData.currentTurn);
        writer.write("\n");
        writer.write(gameData.shop.size());
        writer.write("\n");
        for (Map.Entry<String, Integer> entry : gameData.shop.entrySet()) {
            String key = entry.getKey();
            int value = entry.getValue();
            writer.write(key);
            writer.write(" ");
            writer.write(value);
            writer.write("\n");
        }

        // write player 1 data
        File player1File = new File(directory + "/" + "player1.txt");
        player1File.createNewFile();
        writer = new FileWriter(player1File);
        writer.write(player1.gulden);
        writer.write("\n");
        writer.write(player1.deck);
        writer.write("\n");

        writer.write(player1.activeDeck.size());
        writer.write("\n");
        for (int i = 0; i < player1.activeDeck.size(); i++) {
            writer.write(player1.activeDeck.get(i).coordinate.toString());
            writer.write(" ");
            writer.write(player1.activeDeck.get(i).name);
            writer.write("\n");
        }

        writer.write(player1.field.size());
        writer.write("\n");
        for (int i = 0; i < player1.field.size(); i++) {
            writer.write(player1.field.get(i).coordinate.toString());
            writer.write(" ");
            writer.write(player1.field.get(i).name);
            writer.write(" ");
            writer.write(player1.field.get(i).weight_or_age);
            writer.write(" ");
            writer.write(player1.field.get(i).item.size());
            writer.write(" ");
            for (int j = 0; j < player1.field.get(i).item.size(); j++) {
                writer.write(player1.field.get(i).item.get(j));
                writer.write(" ");
            }
            writer.write("\n");
        }

        // write player 2 data
        File player2File = new File(directory + "/" + "player2.txt");
        player2File.createNewFile();
        writer = new FileWriter(player2File);
        writer.write(player2.gulden);
        writer.write("\n");
        writer.write(player2.deck);
        writer.write("\n");
        writer.write(player2.activeDeck.size());
        writer.write("\n");
        for (int i = 0; i < player2.activeDeck.size(); i++) {
            writer.write(player2.activeDeck.get(i).coordinate.toString());
            writer.write(" ");
            writer.write(player2.activeDeck.get(i).name);
            writer.write("\n");
        }

        writer.write(player2.field.size());
        writer.write("\n");
        for (int i = 0; i < player2.field.size(); i++) {
            writer.write(player2.field.get(i).coordinate.toString());
            writer.write(" ");
            writer.write(player2.field.get(i).name);
            writer.write(" ");
            writer.write(player2.field.get(i).weight_or_age);
            writer.write(" ");
            writer.write(player2.field.get(i).item.size());
            writer.write(" ");
            for (int j = 0; j < player2.field.get(i).item.size(); j++) {
                writer.write(player2.field.get(i).item.get(j));
                writer.write(" ");
            }
            writer.write("\n");
        }

        // close filewriter
        writer.close();
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

        gameData = new GameData(currentTurn, shop);

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
        player1 = new PlayerData(gulden1, numOfDeck1, activeDeck1, field1);

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
        player2 = new PlayerData(gulden2, numOfDeck2, activeDeck2, field2);

        // close scanner
        sc.close();
    }

    @Override
    public String extensionType() {
        return "TXT";
    }
}
