package com.teamenfuego.barcodeshopper.barcodeshopper;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
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
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private NavigationView navigationView;

    public ArrayList<List> myLists = new ArrayList<>();
    public int currentList = R.id.createNew;

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
                if(currentList != R.id.createNew)
                {
                    myLists.get(currentList - 1).addItem("just added an item");
                    ListView currentListView =(ListView)findViewById(R.id.item_list);
                    ListAdapter listAdapter = new ListAdapter(getApplicationContext(), myLists.get(currentList - 1).getItems());
                    currentListView.setAdapter(listAdapter);
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

        if (id == R.id.createNew) {
            int listIndex = myLists.size() + 1;
            int listID = View.generateViewId();

            Menu menu = navigationView.getMenu();
            menu.add(R.id.listMenu, listID, Menu.NONE, "List Number " + listIndex);
            List list1 = new List("List Number " + listIndex, listIndex, listID);
            myLists.add(list1);
            currentList = listIndex;
            renderList(currentList);
        }
        else
        {
            for(List the_list: myLists)
            {
                if(id == the_list.getListID())
                {
                    currentList = the_list.getListIndex();
                    renderList(currentList);
                }
            }
        }

        //TextView text = (TextView)findViewById(R.id.textView3);
        //text.setText("" + currentList);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    public void renderList(int listIndex)
    {
        ListView currentListView =(ListView)findViewById(R.id.item_list);
        ListAdapter listAdapter = new ListAdapter(getApplicationContext(), myLists.get(currentList - 1).getItems());
        currentListView.setAdapter(listAdapter);
        TextView text = (TextView)findViewById(R.id.Title);
        text.setText(myLists.get(currentList - 1).getList_name());
    }

    public void openCamera()
    {
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        integrator.setOrientationLocked(false);
        Intent intent = integrator.createScanIntent();
        startActivity(intent);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent)
    {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (result != null) {
            System.out.println(result.getContents());
        }
    }

}
