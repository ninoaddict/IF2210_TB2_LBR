package org.lbr;

public class Destroy extends Item {
    public Destroy(String name, int price) {
        super(name, price);
    }

    @Override
    public void runEffect(Cultivable c) {
        c = null;
        System.gc();
    }
}