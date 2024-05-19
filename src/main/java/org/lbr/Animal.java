package org.lbr;

import java.util.ArrayList;

public class Animal extends GameObject {
    private int weight_to_ready;
    private int weight;
    private ArrayList<Product> products;
    private int is_protected;
    private int is_trap;

    public Animal(String name, int price, int weight_to_ready_, int weight_, ArrayList<Product> products_) {
        super(name, price);
        this.weight_to_ready = weight_to_ready_;
        this.weight = weight_;
        this.products = products_;
        this.is_protected = 0;
    }

    public void eat() {
    };

    public void harvest() {
    };

    // getter
    public boolean isReady() {
        return weight >= weight_to_ready;
    }

    public int getWeightToReady() {
        return weight_to_ready;
    }

    public int getweight() {
        return weight;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public int getProtectedStatus() {
        return is_protected;
    }

    @Override
    public String toString() {
        return "Name: " + this.getName() + "\nPrice: " + this.getPrice() + "\nWeight To Ready: " + weight_to_ready
                + "\nWeight: " + weight + "\nProducts: " + products + "\nReady: " + isReady();
    }

    // setter
    public void setWeight(int newWeight) throws Exception {
        if (newWeight < 0) {
            throw new Exception("Animal Weight cannot be negative!");
        }
        weight = newWeight;

    }

    public void setProducts(ArrayList<Product> newProducts) {
        products = newProducts;
    }

    public void setProtectionTrap() {
        this.is_protected = 2;
    }

    public void setProtectionYes() {
        this.is_protected = 1;
    }

    public void setProtectionNo() {
        this.is_protected = 0;
    }

    public void addWeight(int addedWeight) throws Exception {
        setWeight(this.getweight() + addedWeight);
    }

    public void reduceWeight(int reducedWeight) throws Exception {
        setWeight(this.getweight() - reducedWeight);
    }
}
