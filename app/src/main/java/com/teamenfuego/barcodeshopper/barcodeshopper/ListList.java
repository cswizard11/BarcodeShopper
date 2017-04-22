package com.teamenfuego.barcodeshopper.barcodeshopper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by isaac on 4/21/17.
 */

public class ListList {

    private List<ShoppingList> shoppingLists;
    private int currentList;

    private List<Item> addedCodes;

    public ListList() {
        this.shoppingLists = new ArrayList<ShoppingList>();
        this.currentList = -1;
        this.addedCodes = new ArrayList<Item>();
    }

    public void add(ShoppingList list) {
        this.shoppingLists.add(list);
        currentList = this.shoppingLists.size() - 1;
    }

    public void remove(ShoppingList list) {
        this.shoppingLists.remove(shoppingLists.indexOf(list));
    }

    public ShoppingList get(int index) {
        return shoppingLists.get(index);
    }

    public ShoppingList getCurrent() {
        return shoppingLists.get(currentList);
    }

    public int size() {
        return shoppingLists.size();
    }

    public List<ShoppingList> getIterable() {
        return shoppingLists;
    }

    public String toString() {
        String res = "";
        for (ShoppingList list: shoppingLists) {
            res += list.toString();
        }
        return res;
    }

    public int getCurrentList() {
        return currentList;
    }

    public void setCurrentList(int newList) {
        this.currentList = newList;
    }

    public List<Item> getAddedCodes() {
        return addedCodes;
    }

    public boolean noSelectedList() {
        System.out.println(this.currentList);
        return this.currentList == -1;
    }

    public void addNewCode(Item list) {
        addedCodes.add(list);
    }

    public Item checkBarcodeInCodes(String barcode) {
        for (Item item : addedCodes) {
            if (item.getBarcode().equals(barcode)) {
                return item;
            }
        }
        return null;
    }

}
