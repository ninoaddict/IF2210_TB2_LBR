package org.lbr;

public class Destroy extends Item {
    public Destroy() {
        super("DESTROY");
    }

    @Override
    public void runEffect(Cultivable c) {
        c = null;
        System.gc();
    }
}