package org.lbr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Carnivore extends Animal{
    private static Map<String, Carnivore> carnivoreMap = new HashMap<String, Carnivore>();

    static {
        carnivoreMap.put("HIU_DARAT", new Carnivore("HIU_DARAT",20, new Product("SIRIP_HIU")));
    }

    public Carnivore(String name, int weight_to_ready, Product product){
        super(name,weight_to_ready,product);
    }

    @Override
    public void eat(Product p) throws Exception{
        if(!p.getTypeObject().equals("animal_product")){
            throw new Exception("Hewan ini hanya bisa makan-makanan dari hewan");
        }
        this.addWeight(p.getAddWeight());
    }
}