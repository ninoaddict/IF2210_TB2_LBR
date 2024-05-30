package org.lbr.model;

public abstract class Item extends GameObject implements Affecting {
    public Item(String name, String imageUrlPath) {
        super(name, imageUrlPath);
    }

    @Override
    public abstract GameObject clone();

    @Override
    public abstract void runEffect(Cultivable cultivable) throws Exception;
}
