package org.lbr;

public class Product extends GameObject {
    private String product_type;
    private int add_weigth;

    public Product(String name, int price, String product_type_, int add_weigth_) {
        super(name, price);
        this.product_type = product_type_;
        this.add_weigth = add_weigth_;
    }

    // getter
    public String getProductType() {
        return product_type;
    }

    public int getAddWeigth() {
        return add_weigth;
    }

    @Override
    public String toString() {
        return "Name: " + this.getName() + "\nPrice: " + this.getPrice() + "\nProduct Type: " + product_type
                + "\nAdd Weight: " + add_weigth;
    }

    // setter
    public void setProductType(String newProductType) {
        this.product_type = newProductType;
    }

    public void setAddWeigth(int newAddWeigth) {
        this.add_weigth = newAddWeigth;
    }
}
