package org.lbr.load_save;
import org.lbr.gameobject.GameObject;
import org.lbr.GameEngine;
import org.lbr.gameobject.cultivable.plant.Plant;
import org.lbr.gameobject.cultivable.animal.Animal;
import org.lbr.gameobject.product.Product;
import org.lbr.player.Player;
import org.lbr.gameobject.cultivable.Cultivable;
import org.lbr.shop.Shop;

import java.util.Map;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;
public class Save {
    public static void savePlayer(Player p,int playerNum){
        try {
            String spr = System.lineSeparator();
            FileWriter fw = new FileWriter("../../../../resources/savestate/player"+Integer.toString(playerNum)+".txt");
            fw.write(p.getGulden()+spr);
            fw.write(p.getDeck_remaining()+spr);
            fw.write(p.getHand_deck().size()+spr);
            for (int i = 0; i < p.getHand_deck().size(); i++) {
                if (!p.isHandIdxEmpty(i)) {
                    fw.write(((char)'A'+i)+"01 "+p.getHand_deck().get(i).getName()+spr);
                }
            }
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 4; j++) {
                    if (!p.isFieldCellEmpty(j,i)) {
                        Cultivable cC = p.getCultivable(j,i);
                        fw.write(((char)'A'+i)+"0"+j+" "+cC.getName()+" ");
                        if (cC instanceof Plant) {
                            fw.write(((Plant) cC).getAge()+" "+cC.getActiveItems());
                        } else {
                            fw.write(((Animal) cC).getWeight()+" "+cC.getActiveItems().size()+" ");
                        }
                        for (int k = 0; k < cC.getActiveItems().size(); k++) {
                            fw.write(cC.getActiveItems().get(k).getName()+" ");
                        }
                        fw.write(spr);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static void saveGameState(GameEngine ge){
        try {
            String spr = System.lineSeparator();
            FileWriter fw = new FileWriter("../../../../resources/savestate/gamestate.txt");
            fw.write(ge.getCurrTurn()+spr);

          Shop shop = Shop.getInstance(null);
          Map<Product, Integer>  products = shop.getProducts();

          ArrayList<Product> s = new ArrayList(products.keySet());
          for (int i = 0; i < s.size(); i++) {
              fw.write(s.get(i)+" "+products.get(s.get(i))+spr);
          }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
