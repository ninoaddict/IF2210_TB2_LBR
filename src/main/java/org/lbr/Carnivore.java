package org.lbr;
public class Carnivore extends Animal{
    public Carnivore(String name, int price, int weight_to_ready, int weight, Product product){
        super(name,price,weight_to_ready, weight, product);
    }

    @Override
    public void eat(Product p) throws Exception{
        if(!p.getTypeObject().equals("animal_product")){
            throw new Exception("Hewan ini hanya bisa makan-makanan dari hewan");
        }
        this.addWeight(p.getAddWeight());
    }
}