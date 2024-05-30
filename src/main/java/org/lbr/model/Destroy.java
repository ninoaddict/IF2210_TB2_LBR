package org.lbr.model;

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
    public void runEffect(Cultivable cultivable) throws Exception {
        cultivable.destroy();
    }
}