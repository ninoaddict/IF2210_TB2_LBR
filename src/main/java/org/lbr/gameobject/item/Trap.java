package org.lbr.gameobject.item;

import org.lbr.gameobject.GameObject;
import org.lbr.gameobject.cultivable.Cultivable;

public class Trap extends Item {
    public Trap() {
        super("Trap", "/images/trap.png");
    }

    public Trap(Trap other){
        super(other.getName(), other.getImgUrlPath());
    }

    @Override
    public GameObject clone(){
        return new Trap(this);
    }


    @Override
    public void runEffect(Cultivable cultivable) throws Exception {
        cultivable.addActiveItem(this);
        cultivable.trap();
    }
}