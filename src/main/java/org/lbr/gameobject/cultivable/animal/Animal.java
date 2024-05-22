package org.lbr.gameobject.cultivable.animal;

import org.lbr.gameobject.item.Item;
import org.lbr.gameobject.product.Product;
import org.lbr.gameobject.cultivable.Cultivable;

import java.util.ArrayList;

public abstract class Animal extends Cultivable {
    private final int weight_to_ready;
    private int weight;

    public Animal(String name, int weight_to_ready_, Product product_, String imageUrlPath) {
        super(name, imageUrlPath);
        this.weight_to_ready = weight_to_ready_;
        this.weight = 0;
        this.product = product_;
    }
    public Animal(String name, int weight_to_ready_, int weight_, Product product_, boolean is_protected, boolean is_trap, ArrayList<Item> activeItems, String imageUrlPath) {
        super(name,is_protected,is_trap,activeItems, imageUrlPath);
        this.weight_to_ready = weight_to_ready_;
        this.weight = weight_;
        this.product = product_;
    }

    public Animal(Animal other){
        super(other.getName(), other.getIsProtected(), other.getIsTrap(), other.getActiveItems(), other.getImgUrlPath());
        this.weight_to_ready = other.getWeightToReady();
        this.weight = other.getWeight();
        this.product = other.product ;
    }

    // abstract class
    public abstract void eat(Product p) throws Exception;

    // getter
    public boolean isReady() {
        return weight >= weight_to_ready;
    }

    public int getWeightToReady() {
        return weight_to_ready;
    }

    public int getWeight() {
        return weight;
    }

    public Product getProduct() {
        return product;
    }

    public boolean getProtectedStatus() {
        return is_protected;
    }

    public boolean getTrapStatus() { return is_trap; }

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

    public void setProducts(Product newProduct) {
        product = newProduct;
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
            this.reduceWeight(5);
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
