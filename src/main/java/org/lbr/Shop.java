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
