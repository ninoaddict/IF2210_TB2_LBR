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
    	System.out.println("HWHW");
    	if(!products.containsKey(key)) {
    		System.out.println("ARRAU");
    	}
    	int getSisa = -1;
    	Product getterProduct = null;
    	for(Product key1: products.keySet()) {
    		if(key1.getName() == key.getName()) {
    			if (products.get(key1) == 0) {
    				throw new Exception("NO PRODUCT TO BUY");
    			}
    			products.put(key1, products.get(key1) - 1);
    			getSisa = products.get(key1);
    			getterProduct = key1;
    			System.out.println("ACC EXTI");
    			break;
    		}
    	}
    	if(getSisa == -1) {
    		throw new Exception("NO PRODUCT TO BUY");
    	}
        System.out.println("MADRID");
        if (getSisa == 0){
            products.remove(getterProduct);
            System.out.println("EKSP");
        }
        System.out.println("HAHA");
    }

    public void addProduct(Product newProduct){
        products.put(newProduct, products.getOrDefault(newProduct, 0) + 1);
    }
}
