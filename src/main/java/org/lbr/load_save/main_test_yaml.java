package org.lbr.load_save;

public class main_test_yaml {
    public static void main(String[] args) {
        SaveLoadYAML uwu = new SaveLoadYAML();
        try {
            uwu.onLoad(null, null, null, null);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
