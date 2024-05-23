package org.lbr.gameobject.cultivable.plant;

import org.lbr.gameobject.GameObject;
import org.lbr.gameobject.item.Item;
import org.lbr.gameobject.product.Product;
import org.lbr.gameobject.cultivable.Cultivable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Plant extends Cultivable {
    private final int age_to_ready;
    private int age;
    private static Map<String, Plant> plantMap = new HashMap<String, Plant>();

    static {
        plantMap.put("BIJI_JAGUNG", new Plant("Biji Jagung", 3, new Product("JAGUNG"), "/images/biji_jagung.png"));
        plantMap.put("BIJI_LABU", new Plant("Biji Labu", 5, new Product("LABU"), "/images/biji_labu.png"));
        plantMap.put("BIJI_STROBERI", new Plant("Biji Stroberi", 4, new Product("STROBERI"), "/images/biji_stroberi.png"));
    }

    public Plant(String name, int age_to_ready_, Product product, String imageUrlPath) {
        super(name, imageUrlPath, product);
        this.age_to_ready = age_to_ready_;
        this.age = 0 ;
    }

    public Plant(String name, int age_to_ready_, int age_, Product product, boolean is_protected, boolean is_trap, ArrayList<Item> activeItems, String imageUrlPath) {
        super(name, is_protected, is_trap, activeItems, imageUrlPath, product);
        this.age_to_ready = age_to_ready_;
        this.age = age_ ;
    }

    public Plant(Plant other){
        super(other.getName(), other.getIsProtected(), other.getIsTrap(), other.getActiveItems(), other.getImgUrlPath(), new Product(other.getProduct()));
        this.age_to_ready = other.getAgeToReady();
        this.age = other.getAge();
    }

    @Override
    public GameObject clone(){
        return new Plant(this);
    }

    public Plant(String name){
        this(plantMap.get(name));
    }

    // getter
    @Override
    public boolean isReady() {
        return age >= age_to_ready;
    }

    public int getAgeToReady() {
        return age_to_ready;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return super.toString() + "\nAge To Ready: " + getAgeToReady()
                + "\nAge: " + getAge() + "\nProduct: " + getProduct().getName() + "\nReady: " + isReady();
    }

    // setter
    public void setAge(int newAge) throws Exception {
        if (newAge < 0) {
            throw new Exception("Plant Weight can't be negative");
        }

        age = newAge;
    }

    //
    public void addAge(int addedAge) throws Exception {
        setAge(this.getAge() + addedAge);
    }

    public void reduceAge(int reducedAge) throws Exception {
        setAge(this.getAge() - reducedAge);
    }

    public void accelerate() {
        try {
            this.addAge(2);
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void delay() {
        try {
            this.reduceAge(Math.min(2, getAge()));
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
