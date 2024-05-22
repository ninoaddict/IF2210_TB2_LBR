package org.lbr.gameobject.item;

import org.lbr.gameobject.cultivable.Cultivable;

public class Protect extends Item {
    public Protect() {
        super("Protect", "/images/protect.png");
    }

    @Override
    public void runEffect(Cultivable cultivable) throws Exception {
        cultivable.addActiveItem(this);
        cultivable.protect();
    }
}
