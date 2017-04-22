package com.teamenfuego.barcodeshopper.barcodeshopper;


        import android.os.Process;

        import java.io.IOException;
        import java.net.URL;
        import java.net.URLConnection;

        import javax.xml.parsers.DocumentBuilder;
        import javax.xml.parsers.DocumentBuilderFactory;
        import javax.xml.parsers.ParserConfigurationException;
        import javax.xml.transform.TransformerException;

        import org.w3c.dom.Document;
        import org.w3c.dom.Element;
        import org.w3c.dom.Node;
        import org.w3c.dom.NodeList;
        import org.xml.sax.SAXException;

/**
 * Created by isaac on 4/21/17.
 */

public class Item {

    private String name;
    private String price;
    private String seller;
    private String barcodeID;
    private boolean checked = false, xed = false;

    public Item(String name, String price, String seller, String barcodeID) {
        this.name = name;
        this.price = price;
        this.seller = seller;
        this.barcodeID = barcodeID;
    }

    public Item(Item other) {
        this.name = other.getName();
        this.price = other.price;
        this.seller = other.seller;
        this.barcodeID = other.getBarcode();
    }

    public Item(String barcodeID) {
        this("NoName", "$0.50","Sell, Sell, Sell",barcodeID);

        String price = "$0.00";
        String name = "Unnamed item";
        String seller = "Nothing";

        try {
            URL url = new URL("http://api.upcdatabase.org/xml/0a4a07f05adbdb4d244054fdfa66aea5/" + barcodeID);
            URLConnection conn = url.openConnection();

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(conn.getInputStream());

            doc.getDocumentElement().normalize();

            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

            NodeList nList = doc.getElementsByTagName("output");

            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);

                //System.out.println("\nCurrent Element :" + nNode.getNodeName());

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;

                    //price is assigned
                    NodeList price1 = eElement.getElementsByTagName("avgprice");
                    if (price1 != null && price1.getLength() > 0)
                        price = price1.item(0).getTextContent();


                    //finds itemname in one of three places or defaults to assignments above
                    String itemname = "";
                    NodeList itemname1 = eElement.getElementsByTagName("itemname");
                    if (itemname1 != null && itemname1.getLength() > 0)
                        itemname = itemname1.item(0).getTextContent();
                    if (! itemname.equals("")) {
                        name = itemname;
                        assign(name, price, seller);
                        return;
                    } else {
                        itemname1 = eElement.getElementsByTagName("alias");
                        if (itemname1 != null && itemname1.getLength() > 0)
                            itemname = itemname1.item(0).getTextContent();
                        if (! itemname.equals("")) {
                            name = itemname;
                            assign(name, price, seller);
                            return;
                        } else {
                            itemname1 = eElement.getElementsByTagName("description");
                            if (itemname1 != null && itemname1.getLength() > 0)
                                itemname = itemname1.item(0).getTextContent();
                            if (! itemname.equals("")) {
                                name = itemname;
                                assign(name, price, seller);
                                return;
                            } else {
                                assign(name, price, seller);
                                return;
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            assign(name, price, seller);
            e.printStackTrace();
            return;
        }
    }

    private void assign(String name, String price, String seller) {
        this.name = name;
        this.price = price;
        this.seller = seller;
    }

    public String getName() {
        return this.name;
    }

    public String getBarcode() {
        return barcodeID;
    }

    public Item setBarcode(String barcode) {
        this.barcodeID = barcode;
        return this;
    }

    public String toString() {
        return this.name + "\n" + this.seller + "\n" + this.price;
    }


    public void checkItem(){
        this.checked = true;
        this.xed = false;
    }

    public void uncheckItem(){
        this.checked = false;
    }

    public void flipCheck(){
        this.checked = ! this.checked;
    }

    public void xItem(){
        this.xed = true;
        this.checked = false;
    }

    public void unXItem(){
        this.xed = false;
    }

    public void flipX(){
        this.xed = ! xed;
    }

    public boolean isChecked() {
        return this.checked;
    }

    public boolean isXed() {
        return this.xed;
    }

}
