package org.lbr;

import java.util.ArrayList;

public class Player {
    private int money;
    private ArrayList<ArrayList<Cultivable>> ladang;
    private int deck_remaining;
    private ArrayList<GameObject> hand_deck;

    public Player() {
        this.money = 0;
        this.ladang = new ArrayList<>();
        ArrayList<Cultivable> temp = new ArrayList<Cultivable>(5);
        ArrayList<Cultivable> temp1 = new ArrayList<Cultivable>(5);
        ArrayList<Cultivable> temp2 = new ArrayList<Cultivable>(5);
        ArrayList<Cultivable> temp3 = new ArrayList<Cultivable>(5);
        this.ladang.add(temp);
        this.ladang.add(temp1);
        this.ladang.add(temp2);
        this.ladang.add(temp3);

    }

}