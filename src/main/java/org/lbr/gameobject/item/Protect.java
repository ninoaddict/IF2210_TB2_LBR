package org.lbr.gameobject.item;

import org.lbr.gameobject.GameObject;
import org.lbr.gameobject.cultivable.Cultivable;

public class Protect extends Item {
    public Protect() {
        super("Protect", "/images/protect.png");
    }

    public Protect(Protect other){
        super(other.getName(), other.getImgUrlPath());
    }

    @Override
    public GameObject clone(){
        return new Protect(this);
    }


    @Override
    public void runEffect(Cultivable cultivable) throws Exception {
        cultivable.addActiveItem(this);
        cultivable.protect();
    }
}
