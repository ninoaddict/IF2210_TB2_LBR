package org.lbr.model;

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