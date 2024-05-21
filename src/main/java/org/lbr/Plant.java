package org.lbr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Plant extends Cultivable {
    private final int age_to_ready;
    private int age;
    private static Map<String, Plant> plantMap = new HashMap<String, Plant>();

    static {
        plantMap.put("BIJI_JAGUNG", new Plant("BIJI_JAGUNG", 3, new Product("JAGUNG")));
        plantMap.put("BIJI_LABU", new Plant("BIJI_LABU", 5, new Product("LABU") ));
        plantMap.put("BIJI_STROBERI", new Plant("BIJI_STROBERI", 4, new Product("STROBERI")));
    }

    public Plant(String name, int age_to_ready_, Product product) {
        super(name);
        this.age_to_ready = age_to_ready_;
        this.age = 0 ;
        this.product = product;
    }

    public Plant(String name, int age_to_ready_, int age_, Product product, boolean is_protected, boolean is_trap, ArrayList<Item> activeItems) {
        super(name, is_protected, is_trap, activeItems);
        this.age_to_ready = age_to_ready_;
        this.product = product;
        this.age = age_ ;
    }

    public Plant(Plant other){
        super(other.getName(), other.getIsProtected(), other.getIsTrap(), other.getActiveItems());
        this.age_to_ready = other.getAgeToReady();
        this.age = other.getAge();
        this.product = other.product ;
    }

    public Plant(String name){
        this(plantMap.get(name));
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

    public void setProducts(Product newProduct) {
        product = newProduct;
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
            this.reduceAge(2);
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
