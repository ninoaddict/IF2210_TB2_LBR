package org.lbr.load_save;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SaveLoadYAML implements SaveLoad {
    public SaveLoadYAML() {

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
                .getResourceAsStream("gameState.yaml");*/
        InputStream inputStream = new FileInputStream(directory + "/gameState.yaml");
        HashMap<String, Object> yamlMap = yaml.load(inputStream);
        int curr_turn = (Integer) yamlMap.get("current_turn");
        gameData.currentTurn = curr_turn;
        int num_of_shop_item = (Integer) yamlMap.get("num_of_shop_item");
        ArrayList<HashMap<String, Object>> shop_items = (ArrayList<HashMap<String, Object>>) yamlMap.get("shop_items");
        HashMap<String, Integer> shop = new HashMap<>();
        for(int i = 0; i < num_of_shop_item; i++) {
            HashMap<String, Object> shop_item = (HashMap<String, Object>) shop_items.get(i);
            shop.put((String) shop_item.get("name"), (Integer) shop_item.get("num_item"));
        }

        gameData.shop = shop;

        // player 1
        InputStream inputStreamP1 = new FileInputStream(directory + "/player1.yaml");
        HashMap<String, Object> player1Data = yaml.load(inputStreamP1);
        int guldenPlayer1 = (Integer) player1Data.get("gulden");
        int num_of_deck_player1 = (Integer) player1Data.get("num_of_deck");
        int num_of_active_deck_player1 = (Integer) player1Data.get("num_of_active_deck");
        player1.gulden = guldenPlayer1;
        player1.deck = num_of_deck_player1;
        ArrayList<HashMap<String, Object>> active_deck_player1 = (ArrayList<HashMap<String, Object>>) player1Data.get("active_deck");
        ArrayList<HandData> activeDeck1 = new ArrayList<>();
        for (int i = 0; i < num_of_active_deck_player1; i++) {
            HashMap<String, Object> cur_deck = (HashMap<String, Object>) active_deck_player1.get(i);
            String coor = (String) cur_deck.get("location");
            String name = (String) cur_deck.get("name");
            HandData curr = new HandData(Coordinate.translate(coor), name);
            activeDeck1.add(curr);
        }
        player1.activeDeck = activeDeck1;
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
        player1.field = field1;

        // player 2
        InputStream inputStreamP2 = new FileInputStream(directory + "/player2.yaml");
        HashMap<String, Object> player2Data = yaml.load(inputStreamP2);
        int guldenPlayer2 = (Integer) player2Data.get("gulden");
        int num_of_deck_player2 = (Integer) player2Data.get("num_of_deck");
        int num_of_active_deck_player2 = (Integer) player2Data.get("num_of_active_deck");
        player2.gulden = guldenPlayer2;
        player2.deck = num_of_deck_player2;
        ArrayList<HashMap<String, Object>> active_deck_player2 = (ArrayList<HashMap<String, Object>>) player2Data.get("active_deck");
        ArrayList<HandData> activeDeck2 = new ArrayList<>();
        for (int i = 0; i < num_of_active_deck_player2; i++) {
            HashMap<String, Object> cur_deck = active_deck_player2.get(i);
            String coor = (String) cur_deck.get("location");
            String name = (String) cur_deck.get("name");
            HandData curr = new HandData(Coordinate.translate(coor), name);
            activeDeck2.add(curr);
        }
        player2.activeDeck = activeDeck2;
        int num_of_card_in_field2 = (Integer) player2Data.get("num_of_card_in_field");
        ArrayList<HashMap<String, Object>> card_in_fieldP2 = (ArrayList<HashMap<String, Object>>) player2Data.get("active_field");
        ArrayList<FieldData> field2 = new ArrayList<>();
        for(int i = 0; i < num_of_card_in_field2; i++) {
            HashMap<String, Object> cur_card = card_in_fieldP2.get(i);
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
            field2.add(new FieldData(Coordinate.translate(location), name, age, potions));
        }
        player2.field = field2;

        inputStreamP1.close();
        inputStreamP2.close();
        inputStream.close();
        for(String name: gameData.shop.keySet()) {
            System.out.println(name);
            System.out.println(gameData.shop.get(name));
        }
        System.out.println("Player1");
        for(int i = 0; i < player1.activeDeck.size(); i++) {
            System.out.println(player1.activeDeck.get(i).name);
            System.out.println(player1.activeDeck.get(i).coordinate);
        }
        System.out.println("Player2");
        for(int i = 0; i < player2.activeDeck.size(); i++) {
            System.out.println(player2.activeDeck.get(i).name);
            System.out.println(player2.activeDeck.get(i).coordinate);
        }
    }

    @Override
    public void onSave(String directory, GameData gameData, PlayerData player1, PlayerData player2) throws Exception {
        DumperOptions options = new DumperOptions();
        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);

        int current_turn = gameData.currentTurn;
        HashMap<String, Integer> current_shop = gameData.shop;
        int num_of_current_shop = current_shop.keySet().size();
        ArrayList<HashMap<String, Object>> shop_items = new ArrayList<>();
        for (String card_name: current_shop.keySet()) {
            HashMap<String, Object> curHashMap = new HashMap<>();
            curHashMap.put("name", card_name);
            curHashMap.put("num_item", current_shop.get(card_name));
            shop_items.add(curHashMap);
        }

        Map<String, Object> data = new HashMap<String, Object>();
        data.put("shop_items", shop_items);
        data.put("num_of_shop_item", num_of_current_shop);
        data.put("current_turn", current_turn);

        Yaml yaml = new Yaml(options);
        FileWriter writer = new FileWriter(directory + "/gameState.yaml");
        yaml.dump(data, writer);

        // player 1
        data = new HashMap<>();
        ArrayList<HandData> activeDeck1 = player1.activeDeck;
        int guldenP1 = player1.gulden;
        int num_of_deck_player1 = player1.deck;
        ArrayList<FieldData> field1 = player1.field;

        ArrayList<HashMap<String, Object>> active_deck = new ArrayList<>();

        for(int i = 0; i < activeDeck1.size(); i++){
            HashMap<String, Object> curr_hash_map = new HashMap<>();
            curr_hash_map.put("location", activeDeck1.get(i).coordinate.toString());
            curr_hash_map.put("name", activeDeck1.get(i).name);
            active_deck.add(curr_hash_map);
        }

        data.put("gulden", guldenP1);
        data.put("num_of_deck", num_of_deck_player1);
        data.put("num_of_active_deck", activeDeck1.size());
        data.put("active_deck", active_deck);

        data.put("num_of_card_in_field", field1.size());

        ArrayList<HashMap<String, Object>> active_field = new ArrayList<>();

        for(int i = 0; i < field1.size(); i++) {
            HashMap<String, Object> curr_hash_map = new HashMap<>();
            curr_hash_map.put("location", field1.get(i).coordinate.toString());
            curr_hash_map.put("name", field1.get(i).name);
            curr_hash_map.put("age", field1.get(i).weight_or_age);
            curr_hash_map.put("num_of_effect", field1.get(i).item.size());
            ArrayList<HashMap<String, Object>> effects = new ArrayList<>();
            for(int j = 0; j < field1.get(i).item.size(); j++){
                HashMap<String, Object> malas = new HashMap<>();
                malas.put("name", field1.get(i).item.get(j));
                effects.add(malas);
            }
            curr_hash_map.put("effects", effects);
            active_field.add(curr_hash_map);
        }
        data.put("active_field", active_field);

        FileWriter player1Writer = new FileWriter(directory + "/player1.yaml");
        yaml.dump(data, player1Writer);

        data = new HashMap<>();
        ArrayList<HandData> activeDeck2 = player2.activeDeck;
        int guldenP2 = player2.gulden;
        int num_of_deck_player2 = player2.deck;
        ArrayList<FieldData> field2 = player2.field;

        active_deck = new ArrayList<>();

        for(int i = 0; i < activeDeck2.size(); i++){
            HashMap<String, Object> curr_hash_map = new HashMap<>();
            curr_hash_map.put("location", activeDeck2.get(i).coordinate.toString());
            curr_hash_map.put("name", activeDeck2.get(i).name);
            active_deck.add(curr_hash_map);
        }

        data.put("gulden", guldenP2);
        data.put("num_of_deck", num_of_deck_player2);
        data.put("num_of_active_deck", activeDeck2.size());
        data.put("active_deck", active_deck);

        data.put("num_of_card_in_field", field2.size());

        active_field = new ArrayList<>();

        for(int i = 0; i < field2.size(); i++) {
            HashMap<String, Object> curr_hash_map = new HashMap<>();
            curr_hash_map.put("location", field2.get(i).coordinate.toString());
            curr_hash_map.put("name", field2.get(i).name);
            curr_hash_map.put("age", field2.get(i).weight_or_age);
            curr_hash_map.put("num_of_effect", field2.get(i).item.size());
            ArrayList<HashMap<String, Object>> effects = new ArrayList<>();
            for(int j = 0; j < field2.get(i).item.size(); j++){
                HashMap<String, Object> malas = new HashMap<>();
                malas.put("name", field2.get(i).item.get(j));
                effects.add(malas);
            }
            curr_hash_map.put("effects", effects);
            active_field.add(curr_hash_map);
        }
        data.put("active_field", active_field);

        FileWriter player2Writer = new FileWriter(directory + "/player2.yaml");
        yaml.dump(data, player2Writer);

        player1Writer.close();
        player2Writer.close();
        writer.close();
    }
}
