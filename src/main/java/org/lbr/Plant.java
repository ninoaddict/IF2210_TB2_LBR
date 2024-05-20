package org.lbr;

public class Plant extends Cultivable {
    private final int age_to_ready;
    private int age;

    public Plant(String name, int price, int age_to_ready_, int age_, Product product) {
        super(name, price);
        this.age_to_ready = age_to_ready_;
        this.age = age_;
        this.product = product;
        this.is_protected = false;
        this.is_trap = false;
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

    public boolean getProtectedStatus() {
        return is_protected;
    }

    public boolean getTrapStatus() { return is_trap; }

    @Override
    public String toString() {
        return getTypeObject() + "\nName: " + this.getName() + "\nPrice: " + this.getPrice() + "\nProtected: " + isProtected() + "\nTrap: " + isTrap() + "\nAge To Ready: " + getAgeToReady()
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
            this.setProtectionYes();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void trap() {
        try {
            this.setTrapYes();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
