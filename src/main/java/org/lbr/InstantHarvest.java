package org.lbr;

public class InstantHarvest extends Item {
    public InstantHarvest(String name, int price) {
        super(name, price);
    }

    @Override
    public void runEffect(Cultivable c) {
        if (c.getClass().getSimpleName().equals("Animal")) {
            ((Animal) c).harvest();
            return;
        }
        ((Plant) c).harvest();
    }

}