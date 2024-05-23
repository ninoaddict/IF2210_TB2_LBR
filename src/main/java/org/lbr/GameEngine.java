package org.lbr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
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
import org.lbr.shop.Shop;

public class GameEngine {
    private final Player[] currPlayer;
    private int currTurn;
    private ArrayList<String> cardKeys;
    private String winner;
    private ScheduledExecutorService timerService;
    private final Shop mainShop;

    public GameEngine() {
        currPlayer = new Player[2];
        currTurn = 1;
        currPlayer[0] = new Player();
        currPlayer[1] = new Player();
        timerService = Executors.newScheduledThreadPool(1);
        Map<Product, Integer> productArrayList = new HashMap<>();

        productArrayList.put(new Product("SIRIP_HIU"), 1);
        productArrayList.put(new Product("SUSU"), 1);
        productArrayList.put(new Product("DAGING_DOMBA"), 1);
        productArrayList.put(new Product("DAGING_KUDA"), 1);
        productArrayList.put(new Product("TELUR"), 1);
        productArrayList.put(new Product("DAGING_BERUANG"), 1);
        productArrayList.put(new Product("JAGUNG"), 1);
        productArrayList.put(new Product("LABU"), 1);
        productArrayList.put(new Product("STROBERI"), 1);

        mainShop = Shop.getInstance(productArrayList);
    }

    public Shop getShop() {
    	return mainShop;
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
                    System.out.println("Time left: " + remainingTime + " seconds");
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
                BearAttack.refresh(getCurrPlayer());
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
