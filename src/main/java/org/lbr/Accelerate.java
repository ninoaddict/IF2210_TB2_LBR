package org.lbr;

public class Accelerate extends Item {
    public Accelerate() {
        super("ACCELERATE", "/images/accelerate.png");
    }

    @Override
    public void runEffect(Cultivable cultivable) throws Exception {
        cultivable.addActiveItem(this);
        cultivable.accelerate();
    }
}
