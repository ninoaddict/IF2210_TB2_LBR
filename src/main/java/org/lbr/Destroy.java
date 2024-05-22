package org.lbr;

public class Destroy extends Item {
    public Destroy() {
        super("Destroy", "/images/destroy.png");
    }

    @Override
    public void runEffect(Cultivable c) {
        c = null;
        System.gc();
    }
}