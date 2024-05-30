package org.lbr.model;

import java.util.ArrayList;

public abstract class Cultivable extends GameObject {
    protected boolean is_protected;
    protected boolean is_trap;
    protected ArrayList<Item> activeItems;
    protected Product product;

    public Cultivable(String name, String imageUrlPath, Product product){
        super(name, imageUrlPath);
        is_protected = false;
        is_trap = false;
        activeItems = new ArrayList<Item>();
        this.product = product;
    }

    public Cultivable(String name, boolean is_protected,
                      boolean is_trap,
                      ArrayList<Item> activeItems,
                      String imageUrlPath,
                      Product product ){
        super(name, imageUrlPath);
        this.is_protected = is_protected;
        this.is_trap = is_trap;
        this.activeItems = new ArrayList<>(activeItems);
        this.product = product ;
    }

    public abstract GameObject clone();

    // getter
    public boolean getIsProtected(){
        return is_protected;
    }

    public boolean getIsTrap(){
        return is_trap;
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
        this.is_trap = true;
    }

    public void setProducts(Product newProduct) {
        product = newProduct;
    }

    @Override
    public String toString() {
        return super.toString() + "\nProtected: " + getIsProtected() + "\nTrap: " + getIsTrap();
    }

    public void addActiveItem(Item item){
        activeItems.add(item);
    }

    public void setActiveItems(ArrayList<Item> activeItems) {
        this.activeItems = activeItems;
    }

    // effects
    public abstract boolean isReady();

    public abstract void accelerate() throws Exception;

    public abstract void delay() throws Exception;

    public abstract void instantHarvest() throws Exception;

    public void protect() throws Exception{
        enableProtect();
    }

    public void trap() throws Exception{
        enableTrap();
    }

    public void destroy() throws Exception{
        if (getIsProtected()){
            throw new Exception("This object is protected!");
        }
    }
}
