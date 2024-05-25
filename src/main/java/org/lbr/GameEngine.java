package org.lbr;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.lbr.gameobject.*;
import org.lbr.gameobject.cultivable.Cultivable;
import org.lbr.gameobject.item.*;
import org.lbr.gameobject.product.*;
import org.lbr.gameobject.cultivable.animal.*;
import org.lbr.gameobject.cultivable.plant.*;
import org.lbr.load_save.SaveLoad;
import org.lbr.load_save.SaveLoadTXT;
import org.lbr.player.*;
import org.lbr.shop.Shop;

public class GameEngine {
    private final Player[] currPlayer;
    private int currTurn;
    private String winner;
    private ScheduledExecutorService timerService;
    private final Shop mainShop;
    private HashMap<String, SaveLoad> saveLoadMap;

    public GameEngine() {
        currPlayer = new Player[2];
        currTurn = 1;
        currPlayer[0] = new Player();
        currPlayer[1] = new Player();
        timerService = Executors.newScheduledThreadPool(1);
        saveLoadMap = new HashMap<>();
        saveLoadMap.put("TXT", new SaveLoadTXT());

        Map<Product, Integer> productArrayList = new HashMap<>();

        productArrayList.put(new Product("SIRIP_HIU"), 0);
        productArrayList.put(new Product("SUSU"), 0);
        productArrayList.put(new Product("DAGING_DOMBA"), 0);
        productArrayList.put(new Product("DAGING_KUDA"), 0);
        productArrayList.put(new Product("TELUR"), 0);
        productArrayList.put(new Product("DAGING_BERUANG"), 0);
        productArrayList.put(new Product("JAGUNG"), 0);
        productArrayList.put(new Product("LABU"), 0);
        productArrayList.put(new Product("STROBERI"), 0);

        mainShop = Shop.getInstance(productArrayList);
    }

    public Shop getShop() {
    	return mainShop;
    }

    public ArrayList<String> getSupportedExtension() {
        ArrayList<String> res = new ArrayList<>();
        for (Map.Entry<String, SaveLoad> entry : saveLoadMap.entrySet()) {
            res.add(entry.getKey());
        }
        return res;
    }

    public void save(String path, String extension) throws Exception {
        if (saveLoadMap.containsKey(extension)) {
            SaveLoad saveLoad = saveLoadMap.get(extension);
            HashMap<String, Integer> newShop = new HashMap<>();
            Map<Product, Integer> shopGoods = mainShop.getProducts();

            for (Map.Entry<Product, Integer> entry : shopGoods.entrySet()) {
                String key = entry.getKey().getName().toUpperCase().replace(" ", "_");
                int value = entry.getValue();
                if (value > 0) {
                    newShop.put(key, value);
                }
            }
            SaveLoad.GameData gameData = new SaveLoad.GameData(currTurn, newShop);

            // save to player 1
            ArrayList<SaveLoad.HandData> handData1 = new ArrayList<>();
            for (int j = 0; j < 6; j++) {
                if (!currPlayer[0].isHandIdxEmpty(j)) {
                    GameObject obj = currPlayer[0].getHandIdx(j);
                    SaveLoad.HandData hand = new SaveLoad.HandData(new SaveLoad.Coordinate(0, j), obj.getName().toUpperCase().replace(" ", "_"));
                    handData1.add(hand);
                }
            }
            ArrayList<SaveLoad.FieldData> fieldData1 = new ArrayList<>();
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 5; k++) {
                    if (!currPlayer[0].isFieldCellEmpty(j, k)) {
                        Cultivable cult = currPlayer[0].getCultivable(j, k);

                        int age_or_weight;
                        if (cult instanceof Animal) {
                            age_or_weight = ((Animal) cult).getWeight();
                        } else {
                            age_or_weight = ((Plant) cult).getAge();
                        }

                        ArrayList<String> potions = new ArrayList<>();
                        for (int l = 0; l < cult.getActiveItems().size(); l++) {
                            potions.add(cult.getActiveItems().get(l).getName().toUpperCase().replace(" ", "_"));
                        }
                        SaveLoad.FieldData field = new SaveLoad.FieldData(new SaveLoad.Coordinate(j, k), cult.getName().toUpperCase().replace(" ", "_"), age_or_weight, potions);
                        fieldData1.add(field);
                    }
                }
            }
            SaveLoad.PlayerData player1 = new SaveLoad.PlayerData(currPlayer[0].getGulden(), currPlayer[0].getDeck_remaining(), handData1, fieldData1);

            // player 2
            ArrayList<SaveLoad.HandData> handData2 = new ArrayList<>();
            for (int j = 0; j < 6; j++) {
                if (!currPlayer[1].isHandIdxEmpty(j)) {
                    GameObject obj = currPlayer[1].getHandIdx(j);
                    SaveLoad.HandData hand = new SaveLoad.HandData(new SaveLoad.Coordinate(0, j), obj.getName().toUpperCase().replace(" ", "_"));
                    handData2.add(hand);
                }
            }
            ArrayList<SaveLoad.FieldData> fieldData2 = new ArrayList<>();
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 5; k++) {
                    if (!currPlayer[1].isFieldCellEmpty(j, k)) {
                        Cultivable cult = currPlayer[1].getCultivable(j, k);

                        int age_or_weight;
                        if (cult instanceof Animal) {
                            age_or_weight = ((Animal) cult).getWeight();
                        } else {
                            age_or_weight = ((Plant) cult).getAge();
                        }

                        ArrayList<String> potions = new ArrayList<>();
                        for (int l = 0; l < cult.getActiveItems().size(); l++) {
                            potions.add(cult.getActiveItems().get(l).getName().toUpperCase().replace(" ", "_"));
                        }
                        SaveLoad.FieldData field = new SaveLoad.FieldData(new SaveLoad.Coordinate(j, k), cult.getName().toUpperCase().replace(" ", "_"), age_or_weight, potions);
                        fieldData2.add(field);
                    }
                }
            }
            SaveLoad.PlayerData player2 = new SaveLoad.PlayerData(currPlayer[1].getGulden(), currPlayer[1].getDeck_remaining(), handData2, fieldData2);
            saveLoad.onSave(path, gameData, player1, player2);
        } else {
            throw new Exception("Extension loader not found");
        }
    }

    public void load(String path, String extension) throws Exception {
        if (saveLoadMap.containsKey(extension)) {
            SaveLoad saveLoad = saveLoadMap.get(extension);
            SaveLoad.GameData gameData =  new SaveLoad.GameData(0, new HashMap<>());
            SaveLoad.PlayerData playerData1 = new SaveLoad.PlayerData(0, 0, new ArrayList<>(), new ArrayList<>());
            SaveLoad.PlayerData playerData2 = new SaveLoad.PlayerData(0, 0, new ArrayList<>(), new ArrayList<>());
            saveLoad.onLoad(path, gameData, playerData1, playerData2);

            // load gameData to gameEngine
            currTurn = gameData.currentTurn;
            HashMap<Product, Integer> newShop = new HashMap<>();
            newShop.put(new Product("SIRIP_HIU"), gameData.shop.getOrDefault("SIRIP_HIU", 0));
            newShop.put(new Product("SUSU"), gameData.shop.getOrDefault("SUSU", 0));
            newShop.put(new Product("DAGING_DOMBA"), gameData.shop.getOrDefault("DAGING_DOMBA", 0));
            newShop.put(new Product("DAGING_KUDA"), gameData.shop.getOrDefault("DAGING_KUDA", 0));
            newShop.put(new Product("TELUR"), gameData.shop.getOrDefault("TELUR", 0));
            newShop.put(new Product("DAGING_BERUANG"), gameData.shop.getOrDefault("DAGING_BERUANG", 0));
            newShop.put(new Product("JAGUNG"), gameData.shop.getOrDefault("JAGUNG", 0));
            newShop.put(new Product("LABU"), gameData.shop.getOrDefault("LABU", 0));
            newShop.put(new Product("STROBERI"), gameData.shop.getOrDefault("STROBERI", 0));
            mainShop.setItems(newShop);

            // load player1Data to player 1
            currPlayer[0].setGulden(playerData1.gulden);
            currPlayer[0].setDeck_remaining(playerData1.deck);

            // load to deck
            currPlayer[0].clearDeck();
            for (int j = 0; j < playerData1.activeDeck.size(); j++) {
                int col = playerData1.activeDeck.get(j).coordinate.y;
                GameObject obj = ObjectFactory.constructObject(playerData1.activeDeck.get(j).name);
                currPlayer[0].setHandIdx(obj, col);
            }

            // load to field
            currPlayer[0].clearField();
            for (int j = 0; j < playerData1.field.size(); j++) {
                int row = playerData1.field.get(j).coordinate.x;
                int col = playerData1.field.get(j).coordinate.y;
                Cultivable cultivable = (Cultivable) ObjectFactory.constructObject(playerData1.field.get(j).name);
                ArrayList<Item> activeItems = new ArrayList<>();
                for (int k = 0; k < playerData1.field.get(j).item.size(); k++) {
                    activeItems.add((Item) ObjectFactory.constructObject(playerData1.field.get(j).item.get(k)));
                }
                cultivable.setActiveItems(activeItems);
                if (cultivable instanceof Animal) {
                    ((Animal) cultivable).setWeight(playerData1.field.get(j).weight_or_age);
                } else {
                    ((Plant) cultivable).setAge(playerData1.field.get(j).weight_or_age);
                }
                currPlayer[0].setCultivable(cultivable, row, col);
            }

            // load data to player 2
            currPlayer[1].setGulden(playerData2.gulden);
            currPlayer[1].setDeck_remaining(playerData2.deck);

            // load to deck
            currPlayer[1].clearDeck();
            for (int j= 0; j < playerData2.activeDeck.size(); j++) {
                int col = playerData2.activeDeck.get(j).coordinate.y;
                GameObject obj = ObjectFactory.constructObject(playerData2.activeDeck.get(j).name);
                currPlayer[1].setHandIdx(obj, col);
            }

            // load to field
            currPlayer[1].clearField();
            for (int j = 0; j < playerData2.field.size(); j++) {
                int row = playerData2.field.get(j).coordinate.x;
                int col = playerData2.field.get(j).coordinate.y;
                Cultivable cultivable = (Cultivable) ObjectFactory.constructObject(playerData2.field.get(j).name);
                ArrayList<Item> activeItems = new ArrayList<>();
                for (int k = 0; k < playerData2.field.get(j).item.size(); k++) {
                    activeItems.add((Item) ObjectFactory.constructObject(playerData2.field.get(j).item.get(k)));
                }
                cultivable.setActiveItems(activeItems);
                if (cultivable instanceof Animal) {
                    ((Animal) cultivable).setWeight(playerData2.field.get(j).weight_or_age);
                } else {
                    ((Plant) cultivable).setAge(playerData2.field.get(j).weight_or_age);
                }
                currPlayer[1].setCultivable(cultivable, row, col);
            }
        } else {
            throw new Exception("Extension loader not found");
        }
    }

    public void addPlugin(String jarPath) throws Exception {
        try {
            File file = new File(jarPath);
            URL jarUrl = file.toURI().toURL();
            try (URLClassLoader loader = new URLClassLoader(new URL[]{jarUrl})) {
                JarFile jar = new JarFile(jarPath);
                Enumeration<JarEntry> entries = jar.entries();
                while (entries.hasMoreElements()) {
                    JarEntry entry = entries.nextElement();
                    if (!entry.isDirectory() && entry.getName().endsWith(".class")) {
                        String classPath = entry.getName().replace('/', '.').replace(".class", "");
                        Class <?> loaderClass = loader.loadClass(classPath);
                        Class <?> interfaceClass = Class.forName("org.lbr.load_save.SaveLoad");

                        if (!interfaceClass.isAssignableFrom(loaderClass) || loaderClass.isInterface()) {
                            continue;
                        }
                        jar.close();
                        Method getExt = loaderClass.getDeclaredMethod("extensionType");
                        SaveLoad saveLoad = (SaveLoad) loaderClass.getDeclaredConstructor().newInstance();
                        saveLoadMap.put((String) getExt.invoke(saveLoad), saveLoad);
                        return;
                    }
                }
                jar.close();
                throw new Exception("Extension loader not found");
            } catch (Exception e) {
                throw new Exception("Extension loader not found");
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public Player getPlayerAtIndex(int index) {
    	return currPlayer[index];
    }

    // getter
    public int getCurrTurn() {
        return currTurn;
    }

    public void nextTurn() {
        currTurn++;
        currPlayer[0].addAllPlantAge();
        currPlayer[1].addAllPlantAge();
    }

    public Player getCurrPlayer() {
        return this.currPlayer[(currTurn - 1) % 2];
    }

    // method
    public String getWinner() {
        if (currPlayer[0].getGulden() > currPlayer[1].getGulden()) {
            return "Player 1";
        } else if (currPlayer[0].getGulden() < currPlayer[1].getGulden()) {
            return "Draw";
        } else {
            return "Player 2";
        }
    }

    public void WaitParalelly(int seconds) {
        timerService.scheduleAtFixedRate(new Runnable() {
            int remainingTime = seconds;

            @Override
            public void run() {
                if (remainingTime > 0) {
                    remainingTime--;
                } else {
                    timerService.shutdown();
                }
            }
        }, 0, 1, TimeUnit.SECONDS);
    }

    public void run() {
        while (currTurn < 20) {
//            ArrayList<GameObject> shuffles = getShuffleCards();
            // ADD TO CURRENT PLAYER
            int bearAttackChance = (new Random()).nextInt(100) + 1;
            if (bearAttackChance <= 60) {
                BearAttack.refresh();
                Random random = new Random();
                WaitParalelly( random.nextInt(31) + 30);
            }
            nextTurn();
        }
        timerService.shutdown();
    }
}
