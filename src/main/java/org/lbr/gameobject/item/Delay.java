package org.lbr.gameobject.item;

import org.lbr.gameobject.cultivable.Cultivable;

public class Delay extends Item {
    public Delay() {
        super("Delay", "/images/delay.png");
    }

    @Override
    public void runEffect(Cultivable cultivable) throws Exception {
        cultivable.addActiveItem(this);
        cultivable.delay();
    }
}