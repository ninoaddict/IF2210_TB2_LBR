package org.lbr;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        try {

            System.out.println("===============GameObject===============");
            GameObject g = new GameObject("test", 100);
            System.out.println(g.getTypeObject());
            System.out.println(g);
            g.setName("newName");
            g.setPrice(1000);
            System.out.println(g);

            System.out.println("===============Product===============");
            Product p = new Product("p", 1, "plant_product", 12);
            System.out.println(p.getTypeObject());
            System.out.println(p);
            p.setName("new");
            p.setPrice(0);
            p.setProductType("animal_product");
            p.setAddweight(0);
            System.out.println(p);

            System.out.println("===============Plant===============");
            ArrayList<Product> l = new ArrayList<>();
            l.add(p);
            Plant t = new Plant("t", 1, 10, 1, l);
            System.out.println(t.getTypeObject());
            System.out.println(t);
            t.setName("nt");
            t.setPrice(1000);
            t.setAge(12);
            System.out.println(t);
            System.out.println(t.getTypeObject());

            Animal a = new Animal("nama", 12, 12, 12, l);
            System.out.println(a.getTypeObject());

            System.err.println("get-Class: " + a.getClass());
        } catch (Exception e) {
            e.getMessage();
        }
    }
}

// & 'C:\Program Files\Java\jdk-21\bin\java.exe'
// '-XX:+ShowCodeDetailsInExceptionMessages' '-cp' 'C:\Kuliah\Tingkat 2\Semester
// 4\OOP\Tubesss\IF2210_TB2_LBR\target\classes' 'org.lbr.Main'