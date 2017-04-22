package com.teamenfuego.barcodeshopper.barcodeshopper;

import android.content.DialogInterface;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public static final String OUTPUT_FILE = "shoppingLists.json";

    private NavigationView navigationView;

    public ListList myLists = new ListList();
    private String m_Text = "";

    private EntryBox popup;
    private EditText product;
    private EditText price;
    private EditText seller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                openCamera();

            }
        });


        FloatingActionButton addItemButton = (FloatingActionButton) findViewById(R.id.addItemButton);
        addItemButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {

                if(!myLists.noSelectedList())
                {
                    popup = new EntryBox();
                    popup.show(getFragmentManager(), "tag");
                }
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        loadListsFromFile();

        final LayoutInflater factory = getLayoutInflater();
        final View entryBoxView = factory.inflate(R.layout.entry_box, null);
        price = (EditText) entryBoxView.findViewById(R.id.price);
        seller = (EditText) entryBoxView.findViewById(R.id.seller);
    }

    public void onPopupComplete(View view) {
        product = (EditText) findViewById(R.id.product);
        String product1 = product.getText().toString();
        String price1 = price.getText().toString();
        String seller1 = seller.getText().toString();
        System.out.println("PRODUCT: " + product.getText());
        myLists.getCurrent().addItem(new Item(product1, price1, seller1, 10231920));
        ListView currentListView = (ListView) findViewById(R.id.item_list);
        ListAdapter listAdapter = new ListAdapter(getApplicationContext(), myLists.getCurrent().getItems());
        currentListView.setAdapter(listAdapter);
        popup.dismiss();
        //View productView = new View()
    }

        @Override
    public void onStop() {
        super.onStop();
        writeListsToFile();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.clearLists) {
            Menu menu = navigationView.getMenu();
            for (ShoppingList list: myLists.getIterable()) {
                menu.removeItem(list.getListID());
            }
            myLists = new ListList();
        } else if (id == R.id.createNew) {
            int listIndex = myLists.size();
            int listID = View.generateViewId();

            ShoppingList list1 = new ShoppingList(listIndex, listID);
            myLists.add(list1);
            addListToSidebar(list1);
            renderList(myLists.getCurrent());
        }
        else
        {
            for(ShoppingList the_list: myLists.getIterable())
            {
                if(id == the_list.getListID())
                {
                    myLists.setCurrentList(the_list.getListIndex());
                    renderList(myLists.getCurrent());
                }
            }
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    public void renderList(ShoppingList list)
    {
        ListView currentListView =(ListView)findViewById(R.id.item_list);
        ListAdapter listAdapter = new ListAdapter(getApplicationContext(), list.getItems());
        currentListView.setAdapter(listAdapter);
        TextView text = (TextView)findViewById(R.id.Title);
        text.setText(myLists.getCurrent().getList_name());
    }

    private void addListToSidebar(ShoppingList list) {
        Menu menu = navigationView.getMenu();
        menu.add(R.id.listMenu, list.getListID(), Menu.NONE, list.getName());
    }

    public void openCamera()
    {
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        integrator.setOrientationLocked(false);
        integrator.initiateScan();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent)
    {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (result != null) {
            System.out.println(result.getContents());
        }
    }

    private void loadListsFromFile() {
        File path = getApplicationContext().getFilesDir();
        File file = new File(path, OUTPUT_FILE);

        Gson gson = new Gson();
        try {
            FileReader in = new FileReader(file);
            myLists = gson.fromJson(in, ListList.class);
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            for (int i = 0; i < myLists.getIterable().size(); i++) {
                addListToSidebar(myLists.get(i));
            }
        }
    }

    private void writeListsToFile() {
        Gson gson = new Gson();
        File path = getApplicationContext().getFilesDir();
        File file = new File(path, OUTPUT_FILE);
        FileOutputStream stream;
        try {
            stream = new FileOutputStream(file);
            stream.write(gson.toJson(myLists).getBytes());
            stream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
