package org.lbr;

public class Protect extends Item {
    public Protect() {
        super("PROTECT");
    }

    @Override
    public void runEffect(Cultivable cultivable) throws Exception {
        cultivable.addActiveItem(this);
        cultivable.protect();
    }
}
