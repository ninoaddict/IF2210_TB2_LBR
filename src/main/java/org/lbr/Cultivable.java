package org.lbr;

import java.util.ArrayList;

public abstract class Cultivable extends GameObject {
    protected boolean is_protected;
    protected boolean is_trap;
    protected ArrayList<Item> activeItems;
    protected Product product;

    public Cultivable(String name, int price){
        super(name,price);
        is_protected = false;
        is_trap = false;
        activeItems = new ArrayList<Item>();
    }

    // getter
    public boolean isProtected(){return is_protected;}

    public boolean isTrap(){return is_trap;}

    public Product getProduct() {
        return product;
    }

    //setter
    public void setProtectionYes() {this.is_protected = true;}

    public void setProtectionNo() {this.is_protected = false;}

    public void setTrapYes() {
        this.is_trap = true;
    }

    public void setTrapNo() {
        this.is_trap = false;
    }


    @Override
    public String toString() {
        return getTypeObject() + "\nName: " + getName() + "\nPrice: " + getPrice() + "\nProtected: " + isProtected() + "\nTrap: " + isTrap();
    }

    public void addActiveItem(Item item){
        activeItems.add(item);
    }

    // effects
    public abstract void accelerate();

    public abstract void delay();

    public abstract void protect();

    public abstract void trap();



}
