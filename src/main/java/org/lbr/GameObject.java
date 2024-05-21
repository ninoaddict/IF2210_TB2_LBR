package org.lbr;

public class GameObject extends Exception {
    private String name;

    public GameObject(String name_) {
        this.name = name_;
    }

    // getter
    public String getName() {
        return name;
    }

    public String getTypeObject() {
        String temp = this.getClass().getSimpleName();
        return temp;
        // int lastIdxDot = temp.lastIndexOf(".");
        // return temp.substring(lastIdxDot + 1, temp.length());
    }

    @Override
    public String toString() {
        return getTypeObject() + "\nName: " + name + " \nPrice: " + price;
    }

    // setter
    void setName(String newName) {
        name = newName;
    }

}