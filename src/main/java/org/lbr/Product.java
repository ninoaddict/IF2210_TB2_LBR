package org.lbr;

public class Product extends GameObject {
    private String product_type;
    private int add_weight;

    public Product(String name, int price, String product_type_, int add_weight_) {
        super(name, price);
        this.product_type = product_type_;
        this.add_weight = add_weight_;
    }

    // getter
    public String getProductType() {
        return product_type;
    }

    public int getAddWeight() {
        return add_weight;
    }

    @Override
    public String toString() {
        return getTypeObject() + "\nName: " + this.getName() + "\nPrice: " + this.getPrice() + "\nProduct Type: " + product_type
                + "\nAdd Weight: " + add_weight + "\nType: " + getProductType();
    }

    // setter
    public void setProductType(String newProductType) {
        this.product_type = newProductType;
    }

    public void setAddWeight(int newAddWeight) {
        this.add_weight = newAddWeight;
    }
}
