package IF2210_TB2_LBR.src;
public class Main {

    public static void main(String[] args) {
        GameObject g = new GameObject("test", 100);
        System.out.println(g.getTypeObject());
        System.out.println(g);
        Product p = new Product("p", 1, "plant_product", 12);
        System.out.println(p.getTypeObject());
        System.out.println(p);
    }
}
