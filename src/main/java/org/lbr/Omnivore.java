package org.lbr;

import java.util.Map;
public class Omnivore extends Animal {
    public Omnivore(String name, int price, int weight_to_ready_, int weight_, Product product_) {
        super(name, price, weight_to_ready_, weight_, product_);
    }


    @Override
    public void eat(Product p) {
        try {
            this.addWeight(p.getAddWeight());
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
