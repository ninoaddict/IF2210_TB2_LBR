package org.lbr;

public class Trap extends Item {
    public Trap(String name, int price) {
        super(name, price);
    }

    @Override
    public void runEffect(Cultivable cultivable) throws Exception {
        cultivable.addActiveItem(this);
        cultivable.trap();
    }
}