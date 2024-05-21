package org.lbr;

public class Protect extends Item {
    public Protect() {
        super("PROTECT", "/images/protect.png");
    }

    @Override
    public void runEffect(Cultivable cultivable) throws Exception {
        cultivable.addActiveItem(this);
        cultivable.protect();
    }
}
