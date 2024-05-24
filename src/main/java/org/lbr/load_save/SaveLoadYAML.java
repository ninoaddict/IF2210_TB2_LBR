package org.lbr.load_save;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.yaml.snakeyaml.Yaml;

import java.util.ArrayList;
import java.util.HashMap;

public class SaveLoadYAML implements SaveLoad {
    SaveLoadYAML() {

    }

    @Override
    public String extensionType() {
        return "YAML";
    }

    @Override
    public void onLoad(String directory, GameData gameData, PlayerData player1, PlayerData player2) throws Exception {
        Yaml yaml = new Yaml();

        // load gamestate
        /*InputStream inputStream = this.getClass()
                .getClassLoader()
                .getResourceAsStream("something.yaml");*/
        InputStream inputStream = new FileInputStream(directory + "/gameState.yaml");
        HashMap<String, Object> yamlMap = yaml.load(inputStream);
        int curr_turn = (Integer) yamlMap.get("current_turn");
        int num_of_shop_item = (Integer) yamlMap.get("num_of_shop_item");
        for(String key : yamlMap.keySet()){
            System.out.println(key + ": " + yamlMap.get(key));
        }
        ArrayList<HashMap<String, Object>> shop_items = (ArrayList<HashMap<String, Object>>) yamlMap.get("shop_items");
        HashMap<String, Integer> shop = new HashMap<>();
        for(int i = 0; i < num_of_shop_item; i++) {
            HashMap<String, Object> shop_item = (HashMap<String, Object>) shop_items.get(i);
            shop.put((String) shop_item.get("name"), (Integer) shop_item.get("num_item"));
        }
        for(String nama: shop.keySet()) {
            System.out.println(nama);
            System.out.println(shop.get(nama));
        }

        // player 1
        InputStream inputStreamP1 = new FileInputStream(directory + "player1.yaml");
        HashMap<String, Object> player1Data = yaml.load(inputStreamP1);
        int guldenPlayer1 = (Integer) player1Data.get("gulden");
        int num_of_deck_player1 = (Integer) player1Data.get("num_of_deck");
        int num_of_active_deck_player1 = (Integer) player1Data.get("num_of_active_deck");
        ArrayList<HashMap<String, Object>> active_deck_player1 = (ArrayList<HashMap<String, Object>>) player1Data.get("active_deck");
        ArrayList<HandData> activeDeck1 = new ArrayList<>();
        for (int i = 0; i < num_of_active_deck_player1; i++) {
            HashMap<String, Object> cur_deck = (HashMap<String, Object>) active_deck_player1.get(i);
            String coor = (String) cur_deck.get("location");
            String name = (String) cur_deck.get("name");
            System.out.println(coor);
            HandData curr = new HandData(Coordinate.translate(coor), name);
            activeDeck1.add(curr);
        }
        int num_of_card_in_field = (Integer) player1Data.get("num_of_card_in_field");
        ArrayList<HashMap<String, Object>> card_in_fieldP1 = (ArrayList<HashMap<String, Object>>) player1Data.get("active_field");
        ArrayList<FieldData> field1 = new ArrayList<>();
        for(int i = 0; i < num_of_card_in_field; i++) {
            HashMap<String, Object> cur_card = (HashMap<String, Object>) card_in_fieldP1.get(i);
            String location = (String) cur_card.get("location");
            String name = (String) cur_card.get("name");
            int age = (Integer) cur_card.get("age");
            int num_of_effect = (Integer) cur_card.get("num_of_effect");
            ArrayList<HashMap<String, Object>> effects = (ArrayList<HashMap<String, Object>>) cur_card.get("effects");
            ArrayList<String> potions = new ArrayList<>();
            for(int j = 0; j < num_of_effect; j++) {
                String effect_name = (String) effects.get(j).get("name");
                potions.add(effect_name);
            }
            field1.add(new FieldData(Coordinate.translate(location), name, age, potions));
        }

    }

    @Override
    public void onSave(String directory, GameData gameData, PlayerData player1, PlayerData player2) throws Exception {

    }
}
