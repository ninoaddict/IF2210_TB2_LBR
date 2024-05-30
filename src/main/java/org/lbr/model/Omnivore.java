package org.lbr.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
public class Omnivore extends Animal {
    private static Map<String, Omnivore> omnivoreMap = new HashMap<String, Omnivore>();

    static {
        omnivoreMap.put("AYAM", new Omnivore("Ayam",5, new Product("TELUR"), "/images/ayam.png"));
        omnivoreMap.put("BERUANG", new Omnivore("Beruang",25, new Product("DAGING_BERUANG"), "/images/beruang.png"));
    }

    public Omnivore(String name, int weight_to_ready, Product product, String imageUrlPath){
        super(name,weight_to_ready,imageUrlPath, product);
    }

    public Omnivore(String name,
                    int weight_to_ready,
                    int weight,
                    Product product,
                    boolean is_protected,
                    boolean is_trap,
                    ArrayList<Item> activeItems,
                    String imageUrlPath,
                    boolean is_active) {
        super(name, weight_to_ready, weight, product, is_protected, is_trap, activeItems, imageUrlPath);
    }

    public Omnivore (Omnivore other){
        super(other);
    }

    @Override
    public GameObject clone(){
        return new Omnivore(this);
    }

    public Omnivore(String name){
        this(omnivoreMap.get(name));
    }

    @Override
    public void eat(Product p) {
        try {
            this.addWeight(p.getAddWeight());
        } catch(Exception e) {
            
        }
    }
    @Override
    public String toString() {
        return super.toString();
    }
}
