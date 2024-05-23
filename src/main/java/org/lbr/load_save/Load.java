package org.lbr.load_save;
import org.lbr.ObjectFactory;

import org.lbr.gameobject.cultivable.Cultivable;
import org.lbr.gameobject.cultivable.animal.Animal;
import org.lbr.gameobject.cultivable.plant.Plant;
import org.lbr.gameobject.item.Item;
import org.lbr.player.Player;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Load {
    public static Player loadPlayer(String file){
        Player player = new Player();
        int nDeck = 0;
        int nHand;
        int nField;
        ArrayList<String> coor = new ArrayList<>();
        ArrayList<String> field = new ArrayList<>();
        ArrayList<Integer> progress = new ArrayList<>();
        ArrayList<ArrayList<String>> inventory = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            line = br.readLine();
            player.setGulden(Integer.parseInt(line));
            line = br.readLine();
            player.setDeck_remaining(Integer.parseInt(line));
            line = br.readLine();
            nHand = Integer.parseInt(line);
            for (int i = 0; i < nHand; i++) {
                line = br.readLine();
                String[] temp = line.split(" ");
                player.setHandIdx(ObjectFactory.getGameObject(temp[1]), (Integer)(temp[0].charAt(0) - 'A'));
            }
            line = br.readLine();
            nField = Integer.parseInt(line);
            for (int i = 0; i < nField; i++) {
                line = br.readLine();
                String[] temp = line.split(" ");
                progress.add(Integer.parseInt(temp[2]));
                ArrayList<String> tempList = new ArrayList<>();
                Cultivable c = (Cultivable) ObjectFactory.getGameObject(temp[1]);
                for (int j = 0; j < Integer.parseInt(temp[3]); j++) {
                    tempList.add(temp[3+i]);
                    c.addActiveItem((Item) ObjectFactory.getGameObject(temp[3+i]));
                }
                if(c instanceof Animal){
                    ((Animal)c).setWeight(Integer.parseInt(temp[2]));
                }else{
                    ((Plant)c).setAge(Integer.parseInt(temp[2]));
                }
                player.setCultivable(c,(Integer)(temp[0].charAt(0) - 'A'),Integer.parseInt(temp[0].substring(1)));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return  player;
    }

    public static void loadShop(String file){
        int nTurn;
        Map<String,Integer> Shop = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            line = br.readLine();
            nTurn = Integer.parseInt(line);
            line = br.readLine();
            int n = Integer.parseInt(line);
            for (int i = 0; i < n; i++) {
                line = br.readLine();
                String[] temp = line.split(" ");
                Shop.put(temp[0], Integer.parseInt(temp[1]));
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
