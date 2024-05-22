package org.lbr.shop;

import org.lbr.gameobject.product.Product;

import java.util.Map;

public class Shop {
    private static Shop instance ;
    private Map<Product, Integer> products;

    private Shop(Map<Product, Integer> products) {
        this.products = products;
    }

    // getter
    public static Shop getInstance(Map<Product, Integer> products) {
        if (instance == null){
            instance = new Shop(products);
        }
        return  instance;
    }

    public static Shop getInstance() {
        if (instance == null){
            instance = new Shop(null);
        }
        return  instance;
    }

    public Map<Product, Integer> getProducts() {
        return products;
    }

    // setter
    public void setItems(Map<Product, Integer> products) {
        this.products = products;
    }

    // method
    public void reduceProduct(Product key){
        products.put(key, products.get(key) - 1);
        if (products.get(key) == 0){
            products.remove(key);
        }
    }

    public void addProduct(Product newProduct){
        products.put(newProduct, products.getOrDefault(newProduct, 0) + 1);
    }
}
