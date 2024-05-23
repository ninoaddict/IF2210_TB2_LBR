package org.lbr;

import java.security.cert.TrustAnchor;
import java.lang.Math;
import java.util.Random;
import org.lbr.gameobject.cultivable.animal.*;
import org.lbr.player.*;

public class BearAttack {
    public static int x1;
    public static int y1;
    public static int x2;
    public static int y2;
    public static Player poorGuy;

    public BearAttack(Player player){
        Random rand = new Random();
        x1 = rand.nextInt(5);
        x2 = rand.nextInt(5-x1) + x1;

        int distance = x2 - x1 + 1;

        y1 = rand.nextInt(4);
        int getRandom = rand.nextInt(Math.floorDiv(6,distance));

        if (getRandom + y1 > 3) {
            y2 = y1;
        } else {
            y2 = getRandom + y1;
        }
        y2 = y1 + rand.nextInt();
        poorGuy = player;
    }

    public static void refresh(Player player){
        Random rand = new Random();
        int x1 = rand.nextInt(5);
        int x2 = rand.nextInt(5-x1) + x1;

        int distance = x2 - x1 + 1;

        int y1 = rand.nextInt(4);
        int getRandom = rand.nextInt(Math.floorDiv(6,distance));

        int y2;

        if (getRandom + y1 > 3) {
            y2 = y1;
        } else {
            y2 = getRandom + y1;
        }

        int duration = rand.nextInt(31) + 30 ;
    }


    public int getX1() {
        return x1;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public int getY1() {
        return y1;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }

    public int getX2() {
        return x2;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public int getY2() {
        return y2;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }

    public Player getPoorGuy() {
        return poorGuy;
    }

    public void setPoorGuy(Player poorGuy) {
        this.poorGuy = poorGuy;
    }

    // method
    public static void execute(Player poorGuy, int x1, int x2, int y1, int y2, int duration){
        boolean noTrap = true;
        int i = x1;
        while (noTrap && i < x2 ){
            int j = y1;
            while (noTrap && j < y2){
                if ( poorGuy.isFieldCellTrap(i, j)){
                    noTrap = false;
                }
                j++;
            }
            i++;
        }

        if (noTrap){
            for (int k = x1; k < x2; k++){
                for (int l = y1; l < y2; l++) {
                    if (!poorGuy.isFieldCellProtected(k, l)) {
                        try {
//                            poorGuy.inactiveCell(k, l);
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }
            }
        } else {
            Omnivore bear = new Omnivore("BERUANG");
            try {
                poorGuy.addToHandDeck(bear);
            } catch (Exception e) {
                // TODO: HANDLE EXCEPTION
            }
        }
    }

}
