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
        return instance;
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
    public void reduceProduct(Product key) throws Exception {
    	int getSisa = -1;
    	for(Product key1: products.keySet()) {
    		if(key1.getName().equals(key.getName())) {
    			if (products.get(key1) == 0) {
    				throw new Exception("No product to buy");
    			}
    			products.put(key1, products.get(key1) - 1);
    			getSisa = products.get(key1);
    			break;
    		}
    	}
    	if(getSisa == -1) {
    		throw new Exception("No product to buy");
    	}
    }

    public void addProduct(Product newProduct) throws Exception {
        boolean check = false;
        for (Product key: products.keySet()) {
            if(key.getName().equals(newProduct.getName())) {
                check = true;
                products.put(key, products.getOrDefault(key, 0) + 1);
                break;
            }
        }
        if (!check) {
            throw new Exception("No product to buy");
        }
    }
}
