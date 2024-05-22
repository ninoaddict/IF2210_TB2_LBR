package org.lbr.gameobject.item;

import org.lbr.gameobject.cultivable.Cultivable;

public class InstantHarvest extends Item {
    public InstantHarvest() {
        super("Instant Harvest", "/images/instant_harvest.png");
    }

    @Override
    public void runEffect(Cultivable c) {

    }

}