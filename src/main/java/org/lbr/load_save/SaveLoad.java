package org.lbr.load_save;

import java.util.ArrayList;
import java.util.HashMap;

public interface SaveLoad {
    class Coordinate {
        public int x;
        public int y;
        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }
        public static Coordinate translate (String coor) throws Exception {
            int x = 0;
            int y = 0;

            coor = coor.toUpperCase();
            if (!Character.isLetter(coor.charAt(0))) {
                throw new Exception("Invalid file content");
            }
            y = coor.charAt(0) - 'A';

            for (int i = 1; i < coor.length(); i++) {
                if (!Character.isDigit(coor.charAt(i))) {
                    throw new Exception("Invalid file content");
                }
                x *= 10;
                x += coor.charAt(i) - '0';
            }
            x--;
            return new Coordinate(x, y);
        }

        @Override
        public String toString () {
            String col = Character.toString((char) (y + 'A'));
            String row = Integer.toString(x + 1);
            return col + row;
        }
    }
    class GameData {
        public int currentTurn;
        public HashMap<String, Integer> shop;
        public GameData(int currentTurn, HashMap<String, Integer> shop) {
            this.currentTurn = currentTurn;
            this.shop = shop;
        }
    }
    class HandData {
        public Coordinate coordinate;
        public String name;

        public HandData(Coordinate coordinate, String name) {
            this.coordinate = coordinate;
            this.name = name;
        }
    }

    class FieldData {
        public Coordinate coordinate;
        public String name;
        public int weight_or_age;
        public ArrayList<String> item;

        public FieldData (Coordinate coordinate, String name, int weight_or_age, ArrayList<String> item) {
            this.coordinate = coordinate;
            this.name = name;
            this.weight_or_age = weight_or_age;
            this.item = item;
        }
    }
    public class PlayerData {
        public int gulden;
        public int deck;
        public ArrayList<HandData> activeDeck;
        public ArrayList<FieldData> field;
        public PlayerData(int gulden, int deck, ArrayList<HandData> activeDeck, ArrayList<FieldData> field) {
            this.gulden = gulden;
            this.deck = deck;
            this.activeDeck = activeDeck;
            this.field = field;
        }
    }

    public void onLoad(String directory, GameData gameData, PlayerData player1, PlayerData player2) throws Exception;
    public void onSave(String directory, GameData gameData, PlayerData player1, PlayerData player2) throws Exception;
    public String extensionType();
}