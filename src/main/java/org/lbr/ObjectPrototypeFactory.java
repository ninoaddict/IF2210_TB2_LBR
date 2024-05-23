package org.lbr;

import org.lbr.gameobject.GameObject;
import org.lbr.gameobject.cultivable.animal.Carnivore;
import org.lbr.gameobject.cultivable.animal.Herbivore;
import org.lbr.gameobject.cultivable.animal.Omnivore;
import org.lbr.gameobject.cultivable.plant.Plant;
import org.lbr.gameobject.item.*;
import org.lbr.gameobject.product.Product;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.Random;

public class ObjectPrototypeFactory {
    private static final Map<String, GameObject> CARD_KEYS = new HashMap<>();

    static {
        // 0 - 5 : Animal
        CARD_KEYS.put("HIU_DARAT", new Carnivore("HIU_DARAT"));
        CARD_KEYS.put("SAPI", new Herbivore("SAPI"));
        CARD_KEYS.put("DOMBA", new Herbivore("DOMBA"));
        CARD_KEYS.put("KUDA", new Herbivore("KUDA"));
        CARD_KEYS.put("AYAM", new Omnivore("AYAM"));
        CARD_KEYS.put("BERUANG", new Omnivore("BERUANG"));

        // 6 - 9 : Plant
        CARD_KEYS.put("BIJI_JAGUNG", new Plant("BIJI_JAGUNG"));
        CARD_KEYS.put("BIJI_LABU", new Plant("BIJI_LABU"));
        CARD_KEYS.put("BIJI_STROBERI", new Plant("BIJI_STROBERI"));

        // 10 - 14 : Item
        CARD_KEYS.put("ACCELERATE", new Accelerate());
        CARD_KEYS.put("DELAY", new Delay());
        CARD_KEYS.put("INSTANT_HARVEST", new InstantHarvest());
        CARD_KEYS.put("DESTROY", new Destroy());
        CARD_KEYS.put("PROTECT", new Protect());
        CARD_KEYS.put("TRAP", new Trap());

        // 15 - 23 : Product
        CARD_KEYS.put("SIRIP_HIU", new Product("SIRIP_HIU"));
        CARD_KEYS.put("SUSU", new Product("SUSU"));
        CARD_KEYS.put("DAGING_DOMBA", new Product("DAGING_DOMBA"));
        CARD_KEYS.put("DAGING_KUDA", new Product("DAGING_KUDA"));
        CARD_KEYS.put("TELUR", new Product("TELUR"));
        CARD_KEYS.put("DAGING_BERUANG", new Product("DAGING_BERUANG"));
        CARD_KEYS.put("JAGUNG", new Product("JAGUNG"));
        CARD_KEYS.put("LABU", new Product("LABU"));
        CARD_KEYS.put("STROBERI", new Product("STROBERI"));
    }

    public static GameObject getGameObject(String name) {
        return CARD_KEYS.get(name);
    }

    public static GameObject constructObject(String name){
        return getGameObject(name).clone();
    }

    // method
    public ArrayList<GameObject> getShuffleCards(int needed) {
        ArrayList<GameObject> gameObjects = new ArrayList<>();
        Random random = new Random();
        String[] keyArray = CARD_KEYS.keySet().stream().toArray(String[]::new);
        String key;

        for (int i = 0; i < needed; i++) {
            int randomInt;
            do {
                randomInt = random.nextInt(CARD_KEYS.size());
                key = keyArray[randomInt] ;
            }  while (key.equals("BERUANG"));

            gameObjects.add(constructObject(key));
        }
        return gameObjects;
    }
}
