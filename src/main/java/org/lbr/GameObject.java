package org.lbr;

public class GameObject extends Exception {
    private String name;
    private int price;

    public GameObject(String name_, int price_) {
        this.name = name_;
        this.price = price_;
    }

    // getter
    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getTypeObject() {
        String temp = this.getClass().getSimpleName();
        return temp;
        // int lastIdxDot = temp.lastIndexOf(".");
        // return temp.substring(lastIdxDot + 1, temp.length());
    }

    @Override
    public String toString() {
        return "Name: " + name + " \nPrice: " + price;
    }

    // setter
    void setName(String newName) {
        name = newName;
    }

    void setPrice(int newPrice) {
        price = newPrice;
    }

}