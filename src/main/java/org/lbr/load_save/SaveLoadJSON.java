package org.lbr.load_save;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public class SaveLoadJSON implements SaveLoad {
    public SaveLoadJSON() {

    }

    @Override
    public void onSave(String directory, GameData gameData, PlayerData player1, PlayerData player2) throws Exception {
        // SAVING PLAYER 1 DATA
        JSONObject mainObject = new JSONObject();
        mainObject.put("gulden",player1.gulden);
        mainObject.put("deckAmount",player1.deck);
        mainObject.put("activeDeck",player1.activeDeck.size());

        JSONArray activeCard = new JSONArray();
        for (int i = 0; i < player1.activeDeck.size(); i++) {
            String formattedCoor = player1.activeDeck.get(i).coordinate.toString();
            JSONObject tempObject = new JSONObject();
            tempObject.put("activeLoc",formattedCoor);
            tempObject.put("activeName",player1.activeDeck.get(i).name);
            activeCard.put(tempObject);
        }
        mainObject.put("activeCards",activeCard);
        mainObject.put("fieldAmount",player1.field.size());

        JSONArray fieldCard = new JSONArray();
        for (int i = 0; i < player1.field.size(); i++) {
            String formattedCoor = player1.field.get(i).coordinate.toString();
            JSONObject tempObject = new JSONObject();
            tempObject.put("fieldLoc", formattedCoor);
            tempObject.put("fieldName", player1.field.get(i).name);
            tempObject.put("required", player1.field.get(i).weight_or_age);
            tempObject.put("effectNum", player1.field.get(i).item.size());
            JSONArray effects = new JSONArray();
            for (int j = 0; j < player1.field.get(i).item.size(); j++) {
                JSONObject tempTempObject = new JSONObject();
                tempTempObject.put("effect_"+(j+1), player1.field.get(i).item.get(j));
                effects.put(tempTempObject);
            }
            tempObject.put("effects",effects);
            fieldCard.put(tempObject);
        }
        mainObject.put("fieldCards",fieldCard);

        FileWriter file = new FileWriter(directory + "/player1.json");
        file.write(mainObject.toString(4));// Indent with 4 spaces for readability
        file.close();

        // SAVING PLAYER 2 DATA
        mainObject = new JSONObject();
        mainObject.put("gulden",player2.gulden);
        mainObject.put("deckAmount",player2.deck);
        mainObject.put("activeDeck",player2.activeDeck.size());

        activeCard = new JSONArray();
        for (int i = 0; i < player2.activeDeck.size(); i++) {
            String formattedCoor = player2.activeDeck.get(i).coordinate.toString();
            JSONObject tempObject = new JSONObject();
            tempObject.put("activeLoc",formattedCoor);
            tempObject.put("activeName",player2.activeDeck.get(i).name);
            activeCard.put(tempObject);
        }
        mainObject.put("activeCards",activeCard);
        mainObject.put("fieldAmount", player2.field.size());

        fieldCard = new JSONArray();
        for (int i = 0; i < player2.field.size(); i++) {
            String formattedCoor = player2.field.get(i).coordinate.toString();
            JSONObject tempObject = new JSONObject();
            tempObject.put("fieldLoc",formattedCoor);
            tempObject.put("fieldName",player2.field.get(i).name);
            tempObject.put("required",player2.field.get(i).weight_or_age);
            tempObject.put("effectNum",player2.field.get(i).item.size());
            JSONArray effects = new JSONArray();
            for (int j = 0; j < player2.field.get(i).item.size(); j++) {
                JSONObject tempTempObject = new JSONObject();
                tempTempObject.put("effect_"+(j+1), player2.field.get(i).item.get(j));
                effects.put(tempTempObject);
            }
            tempObject.put("effects",effects);
            fieldCard.put(tempObject);
        }
        mainObject.put("fieldCards",fieldCard);

        FileWriter player2Writer = new FileWriter(directory + "/player2.json");
        player2Writer.write(mainObject.toString(4)); // Indent with 4 spaces for readability
        player2Writer.close();

        // SAVE GAME STATE
        mainObject = new JSONObject();
        mainObject.put("currTurn",gameData.currentTurn);
        mainObject.put("shopItemType",gameData.shop.size());

        JSONArray shopList = new JSONArray();
        for (String string: gameData.shop.keySet()) {
            JSONObject temp = new JSONObject();
            temp.put("item",string);
            temp.put("amount",gameData.shop.get(string));
            shopList.put(temp);
        }

        mainObject.put("shopItems",shopList);

        FileWriter gameDataWriter = new FileWriter(directory + "/gamestate.json");
        gameDataWriter.write(mainObject.toString(4)); // Indent with 4 spaces for readability
        gameDataWriter.close();
    }

    @Override
    public void onLoad(String directory, GameData gameData, PlayerData player1, PlayerData player2) throws Exception {
        ArrayList<HandData> activeDeck1 = new ArrayList<>();
        ArrayList<FieldData> activeFields1 = new ArrayList<>();

        ArrayList<HandData> activeDeck2 = new ArrayList<>();
        ArrayList<FieldData> activeFields2 = new ArrayList<>();

        HashMap<String,Integer> mapShop = new HashMap<>();

        // THIS STUFF IS TO LOAD PLAYER 1 STATS
        String chosenPlayer = directory + "/" + "player1.json";
        InputStream is = new FileInputStream(chosenPlayer);
        JSONTokener tokener = new JSONTokener(is);
        JSONObject obj = new JSONObject(tokener);
        int gulden1 = (int) obj.get("gulden");
        int deckAmount1 = (int) obj.get("deckAmount");
        int activeDeckAmount1 = (int) obj.get("activeDeck");
        int fieldAmount1 = (int) obj.get("fieldAmount");
        JSONArray activesArray = obj.getJSONArray("activeCards");
        for (int i = 0; i < activeDeckAmount1; i++) {
            JSONObject actev = activesArray.getJSONObject(i);
            activeDeck1.add(new HandData(Coordinate.translate(actev.getString("activeLoc")), actev.getString("activeName")));
        }
        JSONArray fieldsArray = obj.getJSONArray("fieldCards");
        for (int i = 0; i < fieldAmount1; i++) {
            JSONObject fielde = fieldsArray.getJSONObject(i);
            JSONArray effecte = fielde.getJSONArray("effects");
            int lenes = fielde.getInt("effectNum");
            ArrayList<String> effects = new ArrayList<>();
            if (lenes > 0) {
                JSONObject effect = effecte.getJSONObject(0);
                for (int j = 0; j < lenes; j++) {
                    effects.add(effect.getString("effect_"+(j+1)));
                }
            }
            activeFields1.add(new FieldData(Coordinate.translate(fielde.getString("fieldLoc")), (String) fielde.get("fieldName"), (int) fielde.get("required"), effects));
        }
        System.out.println("PLAYER 1");
        is.close();
        tokener.close();

        // THIS STUFF IS TO LOAD PLAYER 2 STATS
        chosenPlayer = directory + "/" + "player2.json";
        is = new FileInputStream(chosenPlayer);
        tokener = new JSONTokener(is);
        obj = new JSONObject(tokener);
        int gulden2 = (int) obj.get("gulden");
        int deckAmount2 = (int) obj.get("deckAmount");
        int activeDeckAmount2 = (int) obj.get("activeDeck");
        int fieldAmount2 = (int) obj.get("fieldAmount");
        activesArray = obj.getJSONArray("activeCards");
        for (int i = 0; i < activeDeckAmount2; i++) {
            JSONObject actev = activesArray.getJSONObject(i);
            activeDeck2.add(new HandData(Coordinate.translate(actev.getString("activeLoc")), actev.getString("activeName")));
        }
        fieldsArray = obj.getJSONArray("fieldCards");
        for (int i = 0; i < fieldAmount2; i++) {
            JSONObject fielde = fieldsArray.getJSONObject(i);
            JSONArray effecte = fielde.getJSONArray("effects");
            int lenes = fielde.getInt("effectNum");
            ArrayList<String> effects = new ArrayList<>();
            if (lenes > 0) {
                JSONObject effect = effecte.getJSONObject(0);
                for (int j = 0; j < lenes; j++) {
                    effects.add(effect.getString("effect_"+(j+1)));
                }
            }
            activeFields2.add(new FieldData(Coordinate.translate(fielde.getString("fieldLoc")), (String) fielde.get("fieldName"), (int) fielde.get("required"), effects));
        }
        is.close();
        tokener.close();
        System.out.println("PLAYER 2");

        // THIS STUFF IS TO LOAD GAME STATE
        chosenPlayer = directory + "/" + "gamestate.json";
        is = new FileInputStream(chosenPlayer);
        tokener = new JSONTokener(is);
        obj = new JSONObject(tokener);
        int currTurn = (int) obj.get("currTurn");
        int shopTypeAmount = (int) obj.get("shopItemType");
        JSONArray itemArray = obj.getJSONArray("shopItems");
        for (int i = 0; i < shopTypeAmount; i++) {
            JSONObject items = itemArray.getJSONObject(i);
            String temp_string = items.getString("item");
            int temp_amount = items.getInt("amount");
            mapShop.put(temp_string,temp_amount);
        }
        is.close();
        tokener.close();
        System.out.println("GAME STATE");

        // PUT IT ALL TOGETHEUR
        player1.gulden = gulden1;
        player1.activeDeck = activeDeck1;
        player1.field = activeFields1;
        player1.deck = deckAmount1;

        player2.gulden = gulden2;
        player2.activeDeck = activeDeck2;
        player2.field = activeFields2;
        player2.deck = deckAmount2;

        gameData.currentTurn = currTurn;
        gameData.shop = mapShop;
    }

    @Override
    public String extensionType() {
        return "JSON";
    }
}