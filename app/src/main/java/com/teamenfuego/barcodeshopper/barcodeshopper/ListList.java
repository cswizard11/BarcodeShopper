package com.teamenfuego.barcodeshopper.barcodeshopper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by isaac on 4/21/17.
 */

public class ListList {

    private List<ShoppingList> shoppingLists;

    public ListList() {
        this.shoppingLists = new ArrayList<ShoppingList>();
    }

    public void add(ShoppingList list) {
        this.shoppingLists.add(list);
    }

    public ShoppingList get(int index) {
        return shoppingLists.get(index);
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

}
