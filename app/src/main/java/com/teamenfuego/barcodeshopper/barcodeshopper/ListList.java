package com.teamenfuego.barcodeshopper.barcodeshopper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by isaac on 4/21/17.
 */

public class ListList {

    private List<ShoppingList> shoppingLists;
    private int currentList;

    public ListList() {
        this.shoppingLists = new ArrayList<ShoppingList>();
        this.currentList = -1;
    }

    public void add(ShoppingList list) {
        this.shoppingLists.add(list);
        currentList = this.shoppingLists.size() - 1;
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

    public boolean noSelectedList() {
        return this.currentList == -1;
    }

}
