package com.teamenfuego.barcodeshopper.barcodeshopper;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.view.menu.ActionMenuItem;
import android.support.v7.view.menu.MenuView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import com.google.zxing.integration.android.IntentIntegrator;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private NavigationView navigationView;

    public ArrayList<List> myLists = new ArrayList<>();
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

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //myItemList.add("Hello");
        //myItemList.add("This is an item in the list");

        //ListView itemListView = (ListView)findViewById(R.id.item_list);
        //ListAdapter itemAdapter = new ListAdapter(this, myItemList);
        //itemListView.setAdapter(itemAdapter);
        //List happy = new List("happy");
        //myLists.add(happy);
        //TextView text = (TextView)findViewById(R.id.textView3);
        //text.setText("" + myLists.size());
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.createNew) {
            Menu menu = navigationView.getMenu();
            menu.add(R.id.listMenu, myLists.size() + 1, Menu.NONE,"List Number " + (myLists.size() + 1));
            List list1 = new List("List Number " + (myLists.size() + 1), myLists.size() + 1);
            myLists.add(list1);
        }
        else
        {
            for(List the_list: myLists)
            {

            }
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        //list1.addItem("item 1");
        //list1.addItem("item 2");
        //list1.addItem("item 3");
        //list1.addItem("item 4");
        //list1.addItem("item 5");
        //list1.addItem("item 6");
        //list1.addItem("item 7");
        //list1.addItem("item 8");
        //list1.addItem("item 9");
        //list1.addItem("item 10");
        //list1.addItem("item 11");
        //list1.addItem("item 12");
        //list1.addItem("item 13");
        //list1.addItem("item 14");
        //list1.addItem("item 15");
        //list1.addItem("item 16");
        //list1.addItem("item 17");
        //list1.addItem("item 18");
        //list1.addItem("item 19");
        //list1.addItem("item 20");
        //list1.addItem("item 21");
        //list1.addItem("item 22");
        //list1.addItem("item 23");
        //list1.addItem("item 24");
        //list1.addItem("item 25");
        //ListView itemListView = (ListView)findViewById(R.id.item_list);
        //ListAdapter itemAdapter = new ListAdapter(this, myLists.get(0).getItems());
        //itemListView.setAdapter(itemAdapter);

        TextView text = (TextView)findViewById(R.id.textView3);
        text.setText("" + myLists.size());
        return true;
    }
    public void openCamera()
    {
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        integrator.setOrientationLocked(false);
        Intent intent = integrator.createScanIntent();
        startActivity(intent);
    }
}
