package org.lbr.gameobject.item;

import org.lbr.gameobject.GameObject;
import org.lbr.gameobject.cultivable.Cultivable;

public class Destroy extends Item {
    public Destroy() {
        super("Destroy", "/images/destroy.png");
    }

    public Destroy(Destroy other){
        super(other.getName(), other.getImgUrlPath());
    }

    @Override
    public GameObject clone(){
        return new Destroy(this);
    }


    @Override
    public void runEffect(Cultivable cultivable) {
//        if (!cultivable.getIsProtected()){
//            cultivable.addActiveItem(this);
//            cultivable = null;
//        }
    }
}