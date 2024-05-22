package org.lbr.gameobject.item;

import org.lbr.gameobject.GameObject;
import org.lbr.gameobject.cultivable.Cultivable;

public abstract class Item extends GameObject implements Affecting{
    public Item(String name, String imageUrlPath) {
        super(name, imageUrlPath);
    }

    @Override
    public abstract void runEffect(Cultivable cultivable) throws Exception;
}
