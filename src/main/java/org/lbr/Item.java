package org.lbr;

public abstract class Item extends GameObject {
    public Item(String name) {
        super(name);
    }

    public abstract void runEffect(Cultivable cultivable) throws Exception;
}
