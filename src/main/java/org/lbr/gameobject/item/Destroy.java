package org.lbr.gameobject.item;

import org.lbr.gameobject.cultivable.Cultivable;

public class Destroy extends Item {
    public Destroy() {
        super("Destroy", "/images/destroy.png");
    }

    @Override
    public void runEffect(Cultivable c) {
//        c.setIs_active(false);
        System.out.println("Destroy");
    }
}