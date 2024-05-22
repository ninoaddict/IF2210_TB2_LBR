package org.lbr;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.lbr.gameobject.*;
import org.lbr.gameobject.item.*;
import org.lbr.gameobject.product.*;
import org.lbr.gameobject.cultivable.animal.*;
import org.lbr.gameobject.cultivable.plant.*;
import org.lbr.player.*;

public class GameEngine {
    private Player[] currPlayer;
    private int currTurn;
    private ArrayList<String> cardKeys;
    private String winner;
    private ScheduledExecutorService timerService;

    private static final ArrayList<String> CARD_KEYS = new ArrayList<>();

    static {
        // 0 - 5 : Animal
        CARD_KEYS.add("HIU_DARAT");
        CARD_KEYS.add("SAPI");
        CARD_KEYS.add("DOMBA");
        CARD_KEYS.add("KUDA");
        CARD_KEYS.add("AYAM");
        CARD_KEYS.add("BERUANG");

        // 6 - 9 : Plant
        CARD_KEYS.add("BIJI_JAGUNG");
        CARD_KEYS.add("BIJI_LABU");
        CARD_KEYS.add("BIJI_STROBERI");

        // 10 - 14 : Item
        CARD_KEYS.add("ACCELERATE");
        CARD_KEYS.add("DELAY");
        CARD_KEYS.add("INSTANT_HARVEST");
        CARD_KEYS.add("DESTROY");
        CARD_KEYS.add("PROTECT");
        CARD_KEYS.add("TRAP");

        // 15 - 23 : Product
        CARD_KEYS.add("SIRIP_HIU");
        CARD_KEYS.add("SUSU");
        CARD_KEYS.add("DAGING_DOMBA");
        CARD_KEYS.add("DAGING_KUDA");
        CARD_KEYS.add("TELUR");
        CARD_KEYS.add("DAGING_BERUANG");
        CARD_KEYS.add("JAGUNG");
        CARD_KEYS.add("LABU");
        CARD_KEYS.add("STROBERI");
    }

    public GameEngine() {
//        currPlayer = new Player[2];
//        currTurn = 1;
//        currPlayer[0] = new Player();
//        currPlayer[1] = new Player();
//        timerService = Executors.newScheduledThreadPool(1);
    }

    // getter
    public int getCurrTurn() {
        return currTurn;
    }

    public void nextTurn() {
        currTurn++;
    }

    public int getCurrPlayer() {
        return (currTurn % 2) - 1;
    }

    public String getCardKey(int idx) {
        return cardKeys.get(idx);
    }

    // method
    public GameObject constructGameObject(int idx) {
        String name = getCardKey(idx);
        if (idx <= 5) {
            if (idx == 0) {
                return new Carnivore(name);
            } else if (idx <= 3) {
                return new Herbivore(name);
            } else {
                return new Omnivore(name);
            }
        } else if (idx <= 8) {
            return new Plant(name);
        } else if (idx <= 14) {
            return switch (idx) {
                case 9 -> new Accelerate();
                case 10 -> new Delay();
                case 11 -> new InstantHarvest();
                case 12 -> new Destroy();
                case 13 -> new Protect();
                default -> new Trap();
            };
        } else {
            return new Product(name);
        }
    }

    public ArrayList<GameObject> getShuffleCards() {
        ArrayList<GameObject> gameObjects = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 4; i++) {
            int randomInt = random.nextInt(23); // 0 to 14
            gameObjects.add(constructGameObject(randomInt));
        }
        return gameObjects;
    }

    public String getWinner() {
        if (currPlayer[0].getGulden() > currPlayer[1].getGulden()) {
            return "Player 1";
        } else if (currPlayer[0].getGulden() < currPlayer[1].getGulden()) {
            return "Draw";
        } else {
            return "Player 2";
        }
    }

    public void defaultSetup() {
        // Initialize or reset game state if needed
        currPlayer = new Player[2];
        currTurn = 1;
        currPlayer[0] = new Player();
        currPlayer[1] = new Player();
        timerService = Executors.newScheduledThreadPool(1);
    }

    public void WaitParalelly(int seconds) {
        timerService.scheduleAtFixedRate(new Runnable() {
            int remainingTime = seconds;

            @Override
            public void run() {
                if (remainingTime > 0) {
                    System.out.println("Time left: " + remainingTime + " seconds");
                    remainingTime--;
                } else {
                    timerService.shutdown();
                }
            }
        }, 0, 1, TimeUnit.SECONDS);
    }

    public void run() {
        defaultSetup();
        while (currTurn < 20) {
            ArrayList<GameObject> shuffles = getShuffleCards();
            // ADD TO CURRENT PLAYER
            int bearAttackChance = (new Random()).nextInt(100) + 1;
            if (bearAttackChance <= 60) {
                BearAttack.refresh(currPlayer[getCurrPlayer()]);
                Random random = new Random();
                WaitParalelly( random.nextInt(31) + 30);
//                BearAttack.execute();
            }
            nextTurn();
        }
        System.out.println(getWinner());
        timerService.shutdown();
    }
}
