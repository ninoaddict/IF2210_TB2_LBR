package org.lbr.model;

import java.util.ArrayList;

public abstract class Animal extends Cultivable {
    private final int weight_to_ready;
    private int weight;

    public Animal(String name, int weight_to_ready_, String imageUrlPath, Product product) {
        super(name, imageUrlPath, product);
        this.weight_to_ready = weight_to_ready_;
        this.weight = 0;
    }
    public Animal(String name, int weight_to_ready_, int weight_, Product product_, boolean is_protected, boolean is_trap, ArrayList<Item> activeItems, String imageUrlPath) {
        super(name,is_protected,is_trap,activeItems, imageUrlPath, product_);
        this.weight_to_ready = weight_to_ready_;
        this.weight = weight_;
    }

    public Animal(Animal other){
        super(other.getName(), other.getIsProtected(), other.getIsTrap(), other.getActiveItems(), other.getImgUrlPath(), new Product(other.getProduct()));
        this.weight_to_ready = other.getWeightToReady();
        this.weight = other.getWeight();
    }

    @Override
    public abstract GameObject clone();

    // abstract class
    public abstract void eat(Product p) throws Exception;

    // getter
    @Override
    public boolean isReady() {
        return weight >= weight_to_ready;
    }

    public int getWeightToReady() {
        return weight_to_ready;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return super.toString()+ "\nWeight: "+ getWeightToReady() + "\nWeight: " + getWeight() + "\nProduct: " + getProduct().getName() + "\nReady: " + isReady();
    }

    // setter
    public void setWeight(int newWeight) throws Exception {
        if (newWeight < 0) {
            throw new Exception("Animal Weight cannot be negative!");
        }
        weight = newWeight;

    }

    public void addWeight(int addedWeight) throws Exception {
        setWeight(this.getWeight() + addedWeight);
    }

    public void reduceWeight(int reducedWeight) throws Exception {
        setWeight(this.getWeight() - reducedWeight);
    }

    public void accelerate() throws Exception{
        this.addWeight(8);
    }

    public void delay()  throws  Exception{
        if (!getIsProtected()){
            int u = 5 ;
            if (u > getWeight()) u = getWeight();
            this.reduceWeight(u);
        } else {
            throw new Exception("Animal is protected!");
        }
    }

    @Override
    public void instantHarvest() throws Exception{
        setWeight(weight_to_ready);
    }
}
