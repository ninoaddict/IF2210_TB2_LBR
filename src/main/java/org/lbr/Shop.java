package org.lbr;

import java.util.Map;

public class Shop {
    private Map<String, Integer> products;

    public Shop(Map<String, Integer> products) {
        this.products = products;
    }

    // getter
    public Map<String, Integer> getProducts() {
        return products;
    }

    // setter
    public void setItems(Map<String, Integer> products) {
        this.products = products;
    }

    // method
    public Product contructProduct(String key){
        return switch (key) {
            case "sirip hiu" -> new Product(key, 500, "animal product", 12);
            case "susu" -> new Product(key, 100, "animal product", 4);
            case "daging domba" -> new Product(key, 120, "animal product", 6);
            case "daging kuda" -> new Product(key, 150, "animal product", 8);
            case "telur" -> new Product(key, 50, "animal product", 2);
            case "daging beruang" -> new Product(key, 500, "animal product", 12);
            case "jagung" -> new Product(key, 150, "plant product", 3);
            case "labu" -> new Product(key, 500, "plant product", 10);
            case "stoberi" -> new Product(key, 350, "plant product", 5);
            default -> null;
        };
    }

    public void reduceProduct(String key){
        products.put(key, products.get(key) - 1);
    }

    public void reduceProduct(Product product){
        reduceProduct(product.getName());
    }

    public void addProduct(String name){
        products.put(name, products.getOrDefault(name, 0) + 1);
    }

    public void addProduct(Product product){
        addProduct(product.getName());
    }
}
