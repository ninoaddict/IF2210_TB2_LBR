package org.lbr;

public class Trap extends Item {
    public Trap() {
        super("TRAP");
    }

    @Override
    public void runEffect(Cultivable cultivable) throws Exception {
        cultivable.addActiveItem(this);
        cultivable.trap();
    }
}