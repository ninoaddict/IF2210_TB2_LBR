package org.lbr.gameobject.cultivable;

import org.lbr.gameobject.item.Item;
import org.lbr.gameobject.product.Product;
import org.lbr.gameobject.GameObject;

import java.util.ArrayList;

public abstract class Cultivable extends GameObject {
    protected boolean is_protected;
    protected boolean is_trap;
    protected ArrayList<Item> activeItems;
    protected boolean is_active;
    protected Product product;

    public Cultivable(String name, String imageUrlPath){
        super(name, imageUrlPath);
        is_protected = false;
        is_trap = false;
        activeItems = new ArrayList<Item>();
        is_active = true;
    }

    public Cultivable(String name, boolean is_protected,
                      boolean is_trap,
                      ArrayList<Item> activeItems,
                      String imageUrlPath,
                      boolean is_active,
                      Product product ){
        super(name, imageUrlPath);
        this.is_protected = is_protected;
        this.is_trap = is_trap;
        this.activeItems = activeItems;
        this.is_active = is_active;
        this.product = product ;
    }

    // getter
    public boolean getIsProtected(){
        return is_protected;
    }

    public boolean getIsTrap(){
        return is_trap;
    }

    public boolean getIsActive() {
        return is_active;
    }

    public Product getProduct() {
        return product;
    }

    public ArrayList<Item> getActiveItems() {
        return activeItems;
    }

    //setter
    public void enableProtect() {
        this.is_protected = true;
    }

    public void enableTrap() {
        this.is_protected = true;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }

    @Override
    public String toString() {
        return super.toString() + "\nProtected: " + getIsProtected() + "\nTrap: " + getIsTrap();
    }

    // method
    public void inactivateCultivable(){
        this.is_active = false;
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
