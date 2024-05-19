package org.lbr;

public class Item extends GameObject {
    public Item(String name, int price) {
        super(name, price);
    }

    public void runEffect(Cultivable c) throws Exception {
        // DO NOTHING - ALREADY OVERRIDEN

    }
}
