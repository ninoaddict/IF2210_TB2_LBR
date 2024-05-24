package org.lbr.load_save;

import java.util.ArrayList;
import java.util.HashMap;

public class main_test_yaml {
    public static void main(String[] args) {
        SaveLoadYAML uwu = new SaveLoadYAML();
        System.out.println(System.getProperty("user.dir"));

        try {
            HashMap<String, Integer> owo = new HashMap<>();
            owo.put("NAMA_MAKANAN", 1);
            owo.put("NAMA", 2);
            SaveLoad.GameData uu = new SaveLoad.GameData(2, owo);
            //uwu.onSave("./src/main/resources/yaml/", uu, null, null);
            uwu.onLoad("./src/main/resources/yaml", new SaveLoad.GameData(0, new HashMap<>()), new SaveLoad.PlayerData(0, 0, new ArrayList<>(), new ArrayList<>()), new SaveLoad.PlayerData(0, 0, new ArrayList<>(), new ArrayList<>()));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        /*
        try {
            HashMap<String, Integer> owo = new HashMap<>();
            owo.put("NAMA_MAKANAN", 1);
            owo.put("NAMA", 2);
            SaveLoad.GameData uu = new SaveLoad.GameData(2, owo);
            ArrayList<SaveLoad.HandData> omJanganOm = new ArrayList<>();
            ArrayList<SaveLoad.FieldData> janganOm = new ArrayList<>();
            omJanganOm.add(new SaveLoad.HandData(new SaveLoad.Coordinate(1, 1), "I FEEL IT COMING"));
            ArrayList<String> newString = new ArrayList<>();
            newString.add("EFFECT1");
            janganOm.add(new SaveLoad.FieldData(new SaveLoad.Coordinate(1, 1), "NAME", 2, newString));
            SaveLoad.PlayerData oo = new SaveLoad.PlayerData(5, 4, omJanganOm, janganOm);
            System.out.println(oo);
            uwu.onSave("./src/main/resources/yaml", uu, oo, oo);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }*/
    }
}
