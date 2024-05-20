package org.lbr;

public class Delay extends Item {
    public Delay(String name, int price) {
        super(name, price);
    }

    @Override
    public void runEffect(Cultivable cultivable) throws Exception {
        cultivable.addActiveItem(this);
        cultivable.delay();
    }

}