package com.teamenfuego.barcodeshopper.barcodeshopper;

import java.util.ArrayList;
import java.util.jar.Attributes;

/**
 * Created by natha on 4/21/2017.
 */

public class ShoppingList
{
    private static final String NAME_PREFIX = "List Number ";

    private String list_name;
    private ArrayList<Item> items;
    private int listIndex;
    private int listID;

    public ShoppingList(int listIndex, int listID)
    {
        this.items = new ArrayList<Item>();
        this.list_name = NAME_PREFIX + (listIndex + 1);
        this.listIndex = listIndex;
        this.listID = listID;
    }

    public String getList_name() {
        return list_name;
    }

    public void setList_name(String list_name) {
        this.list_name = list_name;
    }

    public void addItem(Item item)
    {
        items.add(item);
    }

    public ArrayList<Item> getItems()
    {
        return items;
    }

    public int getListID() {
        return listID;
    }

    public void setListID(int listID) {
        this.listID = listID;
    }

    public int getListIndex() {
        return listIndex;
    }

    public void setListIndex(int listIndex) {
        this.listIndex = listIndex;
    }

    public String getName() {
        return NAME_PREFIX + (getListIndex() + 1);
    }

}
