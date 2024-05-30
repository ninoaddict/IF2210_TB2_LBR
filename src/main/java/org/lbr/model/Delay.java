package org.lbr.model;

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
        cultivable.delay();
        cultivable.addActiveItem(this);
    }
}