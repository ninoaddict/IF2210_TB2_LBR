package org.lbr.player;

import org.lbr.gameobject.GameObject;
import org.lbr.gameobject.cultivable.Cultivable;
import org.lbr.gameobject.cultivable.plant.Plant;
import org.lbr.gameobject.product.Product;
import org.lbr.shop.Shop;

import java.util.ArrayList;
import java.util.Collections;

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
                row.add(null);
            }
            this.field.add(row);
        }

        this.hand_deck = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            this.hand_deck.add(null);
        }
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
            throw new Exception("Index out of range");
        } else {
            field.get(row).set(col, cultivable);
        }
    }

    public void clearField() throws Exception {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                setNullField(i, j);
            }
        }
    }

    public void clearDeck() throws Exception {
        for (int i = 0; i < 6; i++) {
            setHandIdx(null, i);
        }
    }

    public void setNullField(int row, int col) throws Exception {
        setCultivable(null, row, col);
    }

    public void decreaseDeckRemaining(int shuffleTaken) {
        deck_remaining -= shuffleTaken;
    }

    public int getRemainingDeck() {
        return deck_remaining;
    }

    public void setHandIdx(GameObject object, int idx) throws  Exception {

        hand_deck.set(idx, object);
    }

    public void addToHandDeck(GameObject object) throws Exception{
        // First fit algorithm
        for (int i = 0; i < 6; i++) {
            if (isHandIdxEmpty(i)) {
                setHandIdx(object, i);
                return;
            }
        }
        throw new Exception("Hand deck is full");
    }

    public void removeHandDeck(int idx) {
        this.hand_deck.set(idx, null);
    }


    public void setDeck_remaining(int deck_remaining) {
        this.deck_remaining = deck_remaining;
    }

    public void setHand_deck(ArrayList<GameObject> hand_deck) {
        this.hand_deck = hand_deck;
    }

    public int getRemainingHand() {
        int res = 0;
        for (int i = 0; i < 6; i++) {
            if (isHandIdxEmpty(i)) {
                res++;
            }
        }
        return res;
    }

    public void swap_deck(int idx_from, int idx_to) {
    	Collections.swap(hand_deck, idx_from, idx_to);
    }

    public void swap_field(int row_from, int col_from, int row_to, int col_to) {
    	Cultivable tempCultivable = field.get(row_from).get(col_from);
    	field.get(row_from).set(col_from, field.get(row_to).get(col_to));
    	field.get(row_to).set(col_to, tempCultivable);
    }

    // method
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
        return getCultivable(row,col) == null;
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
                j++;
            }
            i++;
        }
        throw new Exception("Failed to add cultivable");
    }

    public void from_deck_to_field(int fromCol, int toRow, int toCol) throws Exception {
    	Cultivable tempCultivable = field.get(toRow).get(toCol);
    	field.get(toRow).set(toCol, (Cultivable) hand_deck.get(fromCol));
    	setHandIdx(tempCultivable, fromCol);
    }

    public void harvest(int row, int col) throws Exception {
        if (isFieldCellEmpty(row, col)) {
            throw new Exception("Can't harvest empty cell");
        }
        if (isHandDeckFull()){
            throw new Exception("Hand deck is full :(");
        }

        addToHandDeck(getCoorProduct(row, col));
        setNullField(row, col);
    }

    public void buy(Product product, Shop shop, int idx) throws Exception {
        if (isHandDeckFull()){
            throw new Exception("Hand deck is full ");
        }

        if (this.gulden < product.getPrice()){
            throw  new Exception("Gulden insufficient!");
        }
        shop.reduceProduct(product);
        setHandIdx(product, idx);
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

    public void addPlantAge(int row, int col){
        if (getCultivable(row,col) instanceof Plant plant){
            try {
                plant.addAge(1);
            } catch (Exception e){
                //
            }
        }
    }

    public void addAllPlantAge(){
        for (int i = 0; i < 4 ; i ++){
            for (int j = 0; j < 5 ; j ++){
                addPlantAge(i, j);
            }
        }
    }
}