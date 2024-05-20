package org.lbr;

public class Protect extends Item {
    public Protect(String name, int price) {
        super(name, price);
    }

    @Override
    public void runEffect(Cultivable cultivable) throws Exception {
        cultivable.addActiveItem(this);
        cultivable.protect();
    }
}
