//package org.lbr;
//
//import java.awt.desktop.SystemEventListener;
//import java.util.ArrayList;
//
//public class Main {
//    public static void main(String[] args) {
//        try {
//            System.out.println("===============GameObject===============");
//            GameObject g = new GameObject("test", 100);
//            System.out.println(g.getTypeObject());
//            System.out.println(g);
//            g.setName("newName");
//            g.setPrice(1000);
//            System.out.println(g);
//
//            System.out.println("===============Product===============");
//            Product p = new Product("p", 1, "plant_product", 12);
//            System.out.println(p.getTypeObject());
//            System.out.println(p.getProductType());
//            System.out.println(p);
//            p.setPrice(0);
//            p.setProductType("animal_product");
//            p.setAddWeight(1000);
//            System.out.println(p);
//
//            Product pp= new Product("pp", 1, "plant_product", 12);
//            System.out.println(pp.getTypeObject());
//            System.out.println(pp.getProductType());
//            System.out.println(pp);
//
//            System.out.println("===============Plant===============");
//            ArrayList<Product> l = new ArrayList<>();
//            l.add(p);
//            Plant t = new Plant("t", 1, 10, 1, p);
//            System.out.println(t.getTypeObject());
//            System.out.println(t);
//            t.setName("nt");
//            t.setPrice(1000);
//            t.setAge(12);
//            System.out.println(t);
//            System.out.println(t.getTypeObject());
//
//            Herbivore h = new Herbivore("car", 10,100,0, p);
//            System.out.println(h);
//            h.eat(pp);
//            System.out.println(h);
//            h.eat(p);
//            System.out.println(h);
//            h.eat(p);
//            System.out.println(h);
//
//        } catch (Exception e) {
//            System.err.println(e.getMessage());
//        }
//    }
//}
//
//// & 'C:\Program Files\Java\jdk-21\bin\java.exe'
//// '-XX:+ShowCodeDetailsInExceptionMessages' '-cp' 'C:\Kuliah\Tingkat 2\Semester
//// 4\OOP\Tubesss\IF2210_TB2_LBR\target\classes' 'org.lbr.Main'