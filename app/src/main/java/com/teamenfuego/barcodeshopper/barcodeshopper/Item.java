package com.teamenfuego.barcodeshopper.barcodeshopper;

/**
 * Created by isaac on 4/21/17.
 */

public class Item {

    private String name;
    private String price;
    private String seller;
    private int barcodeID;

    public Item(String name, String price, String seller, int barcodeID) {
        this.name = name;
        this.price = price;
        this.seller = seller;
        this.barcodeID = barcodeID;
    }

    public Item(int barcodeID) {
        //contact sellers
        this("default", "$0.00", "McDonalds co.", barcodeID);
    }

    public String toString() {
        return this.name + ", " + this.seller + "\n" + this.price;
    }

}
