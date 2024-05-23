package org.lbr.gameobject.cultivable.animal;

import org.lbr.gameobject.item.Item;
import org.lbr.gameobject.product.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Herbivore extends Animal {
    private static Map<String, Herbivore> herbivoreMap = new HashMap<String, Herbivore>();

    static {
        herbivoreMap.put("SAPI", new Herbivore("Sapi",10, new Product("SUSU"), "/images/sapi.png"));
        herbivoreMap.put("DOMBA", new Herbivore("Domba",12, new Product("DAGING_DOMBA"), "/images/domba.png"));
        herbivoreMap.put("KUDA", new Herbivore("Kuda",14, new Product("DAGING_KUDA"), "/images/kuda.png"));
    }

    public Herbivore(String name, int weight_to_ready, Product product, String imageUrlPath){
        super(name,weight_to_ready, imageUrlPath, product);
    }

    public Herbivore(String name, int weight_to_ready, int weight, Product product, boolean is_protected, boolean is_trap, ArrayList<Item> activeItems, String imageUrlPath) {
        super(name, weight_to_ready, weight, product, is_protected, is_trap, activeItems, imageUrlPath);
    }

    public Herbivore (Herbivore other){
        super(other);
    }

    public Herbivore(String name){
        this(herbivoreMap.get(name));
    }

    @Override
    public void eat(Product p) throws Exception {
        if (p.getProductType().equals("animal_product")) {
            throw new Exception("Hewan ini hanya bisa makan-makanan dari tanaman");
        }
        this.addWeight((p.getAddWeight()));
    }
    @Override
    public String toString() {
        return super.toString();
    }
}



