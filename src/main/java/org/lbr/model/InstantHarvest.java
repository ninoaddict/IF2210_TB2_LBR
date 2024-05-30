package org.lbr.model;

public class InstantHarvest extends Item {
    public InstantHarvest() {
        super("Instant Harvest", "/images/instant_harvest.png");
    }

    public InstantHarvest(InstantHarvest other){
        super(other.getName(), other.getImgUrlPath());
    }

    @Override
    public GameObject clone(){
        return new InstantHarvest(this);
    }


    @Override
    public void runEffect(Cultivable c) throws Exception {
        c.instantHarvest();
    }

}