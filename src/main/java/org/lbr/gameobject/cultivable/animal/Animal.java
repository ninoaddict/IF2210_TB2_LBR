package org.lbr.gameobject.cultivable.animal;

import org.lbr.gameobject.GameObject;
import org.lbr.gameobject.item.Item;
import org.lbr.gameobject.product.Product;
import org.lbr.gameobject.cultivable.Cultivable;

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
        super(other.getName(), other.getIsProtected(), other.getIsTrap(), other.getActiveItems(), other.getImgUrlPath(), other.product);
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

    public void accelerate() {
        try {
            this.addWeight(8);
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void delay() {
        try {
            this.reduceWeight(Math.min(5, getWeight()));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void protect() {
        try {
            this.enableProtect();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void trap() {
        try {
            this.enableTrap();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
