package org.lbr;

public class Accelerate extends Item {
    public Accelerate() {
        super("ACCELERATE");
    }

    @Override
    public void runEffect(Cultivable cultivable) throws Exception {
        cultivable.addActiveItem(this);
        cultivable.accelerate();
    }
}
