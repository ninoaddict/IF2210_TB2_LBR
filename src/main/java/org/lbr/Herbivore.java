package org.lbr;

public class Herbivore extends Animal {
    public Herbivore(String name, int price, int weight_to_ready_, int weight_, Product product_) {
        super(name, price, weight_to_ready_, weight_, product_);
    }

    @Override
    public void eat(Product p) throws Exception {
        if (p.getProductType().equals("animal_product")) {
            throw new Exception("Hewan ini hanya bisa makan-makanan dari tanaman");
        }
        this.addWeight((p.getAddWeight()));
    }
}



