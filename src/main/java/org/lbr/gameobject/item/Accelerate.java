package org.lbr.gameobject.item;

import org.lbr.gameobject.cultivable.Cultivable;

public class Accelerate extends Item {
    public Accelerate() {
        super("Accelerate", "/images/accelerate.png");
    }

    @Override
    public void runEffect(Cultivable cultivable) throws Exception {
        cultivable.addActiveItem(this);
        cultivable.accelerate();
    }
}
