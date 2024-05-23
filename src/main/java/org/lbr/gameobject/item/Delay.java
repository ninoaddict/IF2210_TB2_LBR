package org.lbr.gameobject.item;

import org.lbr.gameobject.GameObject;
import org.lbr.gameobject.cultivable.Cultivable;

public class Delay extends Item {
    public Delay() {
        super("Delay", "/images/delay.png");
    }

    public Delay(Delay other){
        super(other.getName(), other.getImgUrlPath());
    }

    @Override
    public GameObject clone(){
        return new Delay(this);
    }


    @Override
    public void runEffect(Cultivable cultivable) throws Exception {
        if (!cultivable.getIsProtected()){
            cultivable.addActiveItem(this);
            cultivable.delay();
        }
    }
}