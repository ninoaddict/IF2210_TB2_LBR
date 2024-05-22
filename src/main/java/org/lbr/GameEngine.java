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

    public GameEngine() {
        currPlayer = new Player[2];
        currTurn = 1;
        currPlayer[0] = new Player();
        currPlayer[1] = new Player();
        timerService = Executors.newScheduledThreadPool(1);
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
