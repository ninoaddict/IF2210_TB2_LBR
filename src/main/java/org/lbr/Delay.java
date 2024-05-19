package org.lbr;

public class Delay extends Item {
    public Delay(String name, int price) {
        super(name, price);
    }

    @Override
    public void runEffect(Cultivable c) throws Exception {
        if (c.getClass().getSimpleName().equals("Animal")) {
            int reducedWeight = ((Animal) c).getweight() < 5 ? ((Animal) c).getweight() : 5;
            ((Animal) c).reduceWeight(reducedWeight);
            return;
        }

        int reducedAge = (((Plant) c).getAge() < 2 ? ((Plant) c).getAge() : 2);
        ((Plant) c).reduceAge(reducedAge);

    }

}