package org.lbr;

public abstract class Item extends GameObject {
    public Item(String name, int price) {
        super(name, price);
    }

    public abstract void runEffect(Cultivable cultivable) throws Exception;
}
