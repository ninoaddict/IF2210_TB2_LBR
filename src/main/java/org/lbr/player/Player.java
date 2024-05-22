package org.lbr.player;

import org.lbr.gameobject.GameObject;
import org.lbr.gameobject.cultivable.Cultivable;
import org.lbr.gameobject.cultivable.animal.Carnivore;
import org.lbr.gameobject.product.Product;
import org.lbr.shop.Shop;

import java.util.ArrayList;

public class Player {
    private int gulden;
    private ArrayList<ArrayList<Cultivable>> field;
    private int deck_remaining;
    private ArrayList<GameObject> hand_deck;

    public Player() {
        this.gulden = 0;
        this.field = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            ArrayList<Cultivable> row = new ArrayList<>(5);
            for (int j = 0; j < 5; j++) {
                Cultivable dummy =  new Carnivore("HIU_DARAT");
                dummy.setIs_active(false);
                row.add(dummy);
            }
            this.field.add(row);
        }

        this.hand_deck = new ArrayList<>();
        this.deck_remaining = 40 ;
    }

    public Player(int gulden, ArrayList<ArrayList<Cultivable>> field, int deck_remaining, ArrayList<GameObject> hand_deck){
        this.gulden = gulden;
        this.field = field;
        this.deck_remaining = deck_remaining;
        this.hand_deck = hand_deck;
    }

    // getter
    public int getGulden() {
        return gulden;
    }

    public ArrayList<ArrayList<Cultivable>> getField() {
        return field;
    }

    public Cultivable getCultivable(int i, int j){
        return field.get(i).get(j);
    }

    public GameObject getHandIdx(int idx){
        return hand_deck.get(idx);
    }

    public Product getCoorProduct(int row, int col) {
        return getCultivable(row, col).getProduct();
    }

    public int getDeck_remaining() {
        return deck_remaining;
    }

    public ArrayList<GameObject> getHand_deck() {
        return hand_deck;
    }

    // setter
    public void setGulden(int gulden) {
        this.gulden = gulden;
    }

    public void setField(ArrayList<ArrayList<Cultivable>> field) {
        this.field = field;
    }

    public void setCultivable(Cultivable cultivable, int row, int col) throws  Exception{
        if (row < 0 || row > 4 || col < 0 || col > 5 ){
            throw  new Exception("Index out of range");
        } else {
            field.get(row).set(col, cultivable);
        }
    }

    public void setHandIdx(GameObject object, int idx){
        hand_deck.set(idx, object);
    }

    public void addToHandDeck(GameObject object) {
        if (isHandDeckFull()){
            System.out.println("HAND DECK IS FULL! THE CARD IS DELETED");
        }
        this.hand_deck.add(object);
    }

    public void removeHandDeck(int idx) {
        this.hand_deck.remove(idx);
    }

    public void setDeck_remaining(int deck_remaining) {
        this.deck_remaining = deck_remaining;
    }

    public void setHand_deck(ArrayList<GameObject> hand_deck) {
        this.hand_deck = hand_deck;
    }

    // method
    public void inactiveCell(int row, int col){
        getCultivable(row, col).inactivateCultivable();
    }

    public void addGulden(int addedGulden){
        setGulden(getGulden()  + addedGulden);
    }

    public  void reduceGulden(int reducedGulden){
        setGulden(getGulden() - reducedGulden);
    }

    public boolean isHandDeckFull() {
        for (int i = 0; i < 6; i++) {
            if (isHandIdxEmpty(i)) {
                return false;
            }
        }
        return true;
    }

    public boolean isHandIdxEmpty(int idx) {
        return getHandIdx(idx) == null;
    }

    public boolean isFieldCellEmpty(int row, int col){
        return !getCultivable(row, col).getIsActive();
    }

    public boolean isFieldCellProtected(int row, int col){
        return !isFieldCellEmpty(row,col) && getCultivable(row, col).getIsProtected();
    }

    public boolean isFieldCellTrap(int row, int col){
        return !isFieldCellEmpty(row,col) && getCultivable(row, col).getIsTrap();
    }

    public void addCultivable(Cultivable cultivable) throws Exception {
        boolean added = false;
        int i = 0;
        while ((i < 4) && !added) {
            int j = 0;
            while (j < 5 && !added) {
                if (isFieldCellEmpty(i, j)) {
                    setCultivable(cultivable, i, j);
                    added = true;
                }
            }
        }

        throw new Exception("FAILED TO ADD CULTIVABLE");
    }

    public void harvest(int row, int col) throws Exception {
        if (isFieldCellEmpty(row, col)) {
            throw new Exception("Can't harvest empty cell");
        }
        if (isHandDeckFull()){
            throw new Exception("Hand deck is full :(");
        }

        addToHandDeck(getCoorProduct(row, col));
        inactiveCell(row, col);
    }

    public void buy(Product product, Shop shop) throws Exception {
        if (isHandDeckFull()){
            throw new Exception("Hand deck is full ");
        }

        if (this.gulden < product.getPrice()){
            throw  new Exception("Gulden insufficient!");
        }

        addToHandDeck(product);
        shop.reduceProduct(product);
        reduceGulden(product.getPrice());
    }

    public void sell(int idx, Shop shop) throws Exception {
        GameObject gameObject = getHandIdx(idx);
        if (!(gameObject instanceof Product product)){
            throw new Exception("Can only sell product");
        }

        removeHandDeck(idx);
        shop.addProduct(product);
        addGulden(product.getPrice());
    }
}