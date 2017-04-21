package com.teamenfuego.barcodeshopper.barcodeshopper;

import java.util.ArrayList;

/**
 * Created by natha on 4/21/2017.
 */

public class List
{
    private String list_name;
    private ArrayList<String> items;
    private int listIndex;
    private int listID;

    public List(String list_name, int listIndex, int listID)
    {
        this.list_name = list_name;
        this.listIndex = listIndex;
        this.listID = listID;
    }

    public void addItem(String item)
    {
        items.add(item);
    }

    public ArrayList<String> getItems()
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



}
