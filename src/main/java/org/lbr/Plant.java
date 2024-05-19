package org.lbr;

import java.util.ArrayList;

public class Plant extends GameObject implements Cultivable {
    private int age_to_ready;
    private int age;
    private ArrayList<Product> products;
    private int is_protected;

    public Plant(String name, int price, int age_to_ready_, int age_, ArrayList<Product> products_) {
        super(name, price);
        this.age_to_ready = age_to_ready_;
        this.age = age_;
        this.products = products_;
        this.is_protected = 0;
    }

    // getter
    public boolean isReady() {
        return age >= age_to_ready;
    }

    public int getAgeToReady() {
        return age_to_ready;
    }

    public int getAge() {
        return age;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public int getProtectedStatus() {
        return is_protected;
    }

    @Override
    public String toString() {
        return "Name: " + this.getName() + "\nPrice: " + this.getPrice() + "\nAge To Ready: " + age_to_ready
                + "\nAge: " + age + "\nProducts: " + products + "\nReady: " + isReady();
    }

    // setter
    public void setAge(int newAge) throws Exception {
        if (newAge < 0) {
            throw new Exception("Plant Weight can't be negative");
        }

        age = newAge;
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

    //
    @Override
    public void harvest() {
        return;
    }

    public void addAge(int addedAge) throws Exception {
        setAge(this.getAge() + addedAge);
    }

    public void reduceAge(int reducedAge) throws Exception {
        setAge(this.getAge() - reducedAge);
    }

}
