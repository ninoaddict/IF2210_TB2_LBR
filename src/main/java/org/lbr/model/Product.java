package org.lbr.model;

import java.util.HashMap;
import java.util.Map;

public class Product extends GameObject  {
    private String product_type;
    private int add_weight;
    private int price ;
    private static Map<String, Product> productMap = new HashMap<String, Product>();

    static {
        productMap.put("SIRIP_HIU" , new Product("Sirip Hiu", 500, "animal product", 12, "/images/sirip_hiu.png"));
        productMap.put("SUSU" , new Product("Susu", 100, "animal product", 4, "/images/susu.png"));
        productMap.put("DAGING_DOMBA" , new Product("Daging Domba", 120, "animal product", 6, "/images/daging_domba.png"));
        productMap.put("DAGING_KUDA" , new Product("Daging Kuda", 150, "animal product", 8, "/images/daging_kuda.png"));
        productMap.put("TELUR" , new Product("Telur", 50, "animal product", 2, "/images/telur.png"));
        productMap.put("DAGING_BERUANG" , new Product("Daging Beruang", 500, "animal product", 12, "/images/daging_beruang.png"));
        productMap.put("JAGUNG" , new Product("Jagung", 150, "plant product", 3, "/images/jagung.png"));
        productMap.put("LABU" , new Product("Labu", 500, "plant product", 10, "/images/labu.png"));
        productMap.put("STROBERI" , new Product("Stroberi", 350, "plant product", 5, "/images/stroberi.png"));
    }

    public Product(String name, int price, String product_type_, int add_weight_, String imageUrlPath) {
        super(name, imageUrlPath);
        this.price = price;
        this.product_type = product_type_;
        this.add_weight = add_weight_;
    }

    public Product(Product other){
        super(other.getName(), other.getImgUrlPath());
        this.price  = other.getPrice();
        this.product_type = other.product_type;
        this.add_weight = other.add_weight;
    }

    public Product(String name) {
        this(productMap.get(name));
    }

    @Override
    public GameObject clone(){
        return new Product(this);
    }
   

    // getter
    public String getProductType() {
        return product_type;
    }

    public int getAddWeight() {
        return add_weight;
    }

    public int getPrice() {
        return price;
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

    public void setPrice(int price) {
        this.price = price;
    }

}
