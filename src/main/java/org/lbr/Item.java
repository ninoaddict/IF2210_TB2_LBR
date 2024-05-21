package org.lbr;

public abstract class Item extends GameObject {
    public Item(String name, String imageUrlPath) {
        super(name, imageUrlPath);
    }

    public abstract void runEffect(Cultivable cultivable) throws Exception;
}
