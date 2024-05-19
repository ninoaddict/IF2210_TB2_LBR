package org.lbr;

public class Trap extends Item {
    public Trap(String name, int price) {
        super(name, price);
    }

    @Override
    public void runEffect(Cultivable c) {
        if (c.getClass().getSimpleName().equals("Animal")) {
            ((Animal) c).enableTrap();
            return;
        }
        ((Plant) c).enableTrap();
    }

}