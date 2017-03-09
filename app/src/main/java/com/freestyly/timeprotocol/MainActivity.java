package com.freestyly.timeprotocol;

import android.content.Context;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.text.FieldPosition;
import java.text.ParsePosition;
import java.util.Calendar;

import java.util.Date;
import java.util.List;

import com.freestyly.timeprotocol.database.DataSource;
import com.freestyly.timeprotocol.database.Worktime;
import com.freestyly.timeprotocol.fragments.FragmentSettings;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static FragmentManager fragmentManager;

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
                Snackbar.make(view, "TODO: Daten als csv Export schicken", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //init fragment manager
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.commit();


        //set listener for come and leave button
        ((Button) findViewById(R.id.come)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickCome();
            }
        });
        ((Button) findViewById(R.id.leave)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickLeave();
            }
        });

        //read config
        dataSource = new DataSource(this);
        dataSource.open();
        if (dataSource.getConfigs().size() == 0)
            dataSource.createConfig();

        //we need some data to show
        List<Worktime> wt = dataSource.getAllWorktimes();
        //db not needed any more, so close it
        dataSource.close();
        //prepare visible elements
        ArrayAdapter<Worktime> worktimeAdapter = new ArrayAdapter<>(getApplicationContext(),
                    android.R.layout.simple_list_item_1, wt);


        //show it
        ListView works = (ListView) findViewById(R.id.listView);
        works.setAdapter(worktimeAdapter);
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
            // open fragment for settings
            FragmentTransaction fragmentTransaction = MainActivity.fragmentManager.beginTransaction();
            FragmentSettings fragmentSettings = new FragmentSettings();
            //LinearLayout ll = (LinearLayout) findViewById(R.id.llh);

            //fragmentTransaction.add(ll);
            fragmentTransaction.commit();
            //return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_main) {

        } else if (id == R.id.nav_manage) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private DataSource dataSource;

    public void onClickCome() {
            dataSource.open();
            Calendar rightNow = Calendar.getInstance();
            //build up string for database YYYY.MM.DD
            StringBuilder sbDay = new StringBuilder();
            sbDay.append("" + rightNow.YEAR + "." + rightNow.MONTH + "." + rightNow.DAY_OF_MONTH);
            //now build the time
            StringBuilder sbTime = new StringBuilder();
            sbTime.append("" + rightNow.HOUR_OF_DAY + ":" + (rightNow.AM_PM + rightNow.HOUR));

            //dataSource.createWorktimes(sb.toString(),null,null);
             dataSource.close();
    }

    public void onClickLeave() {
        dataSource.open();

        dataSource.close();
    }
}
