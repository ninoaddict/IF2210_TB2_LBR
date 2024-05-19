package org.lbr;

public class Accelerate extends Item {
    public Accelerate(String name, int price) {
        super(name, price);
    }

    @Override
    public void runEffect(Cultivable c) throws Exception {
        if (c.getClass().getSimpleName().equals("Animal")) {
            ((Animal) c).addWeight(8);
            return;
        }

        ((Plant) c).setAge(((Plant) c).getAge() + 2);
    }
}
