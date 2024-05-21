package org.lbr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
public class Omnivore extends Animal {
    public Omnivore(String name, int price, int weight_to_ready_, int weight_, Product product_) {
        super(name, price, weight_to_ready_, weight_, product_);
    }

    public Omnivore(String name){
        this(omnivoreMap.get(name));
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
