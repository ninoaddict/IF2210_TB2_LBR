package org.lbr;

public class Protect extends Item {
    public Protect(String name, int price) {
        super(name, price);
    }

    @Override
    public void runEffect(Cultivable c) {
        if (c.getClass().getSimpleName().equals("Animal")) {
            ((Animal) c).setProtectionYes();
            return;
        }
        ((Plant) c).setProtectionYes();
    }

}