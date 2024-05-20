package org.lbr;

public class Accelerate extends Item {
    public Accelerate(String name, int price) {
        super(name, price);
    }

    @Override
    public void runEffect(Cultivable cultivable) throws Exception {
        cultivable.addActiveItem(this);
        cultivable.accelerate();
    }
}
