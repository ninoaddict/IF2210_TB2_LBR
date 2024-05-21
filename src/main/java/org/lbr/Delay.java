package org.lbr;

public class Delay extends Item {
    public Delay() {
        super("DELAY", "/images/delay.png");
    }

    @Override
    public void runEffect(Cultivable cultivable) throws Exception {
        cultivable.addActiveItem(this);
        cultivable.delay();
    }

}