package com.qtechie.ghausiyamaktab;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.qtechie.ghausiyamaktab.Constant.FIRST_COLUMN;
import static com.qtechie.ghausiyamaktab.Constant.SECOND_COLUMN;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private ArrayList<HashMap> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out); //animate changing of activities
        setContentView(R.layout.activity_home);

//        try {
//            ViewGroup decorView = (ViewGroup) this.getWindow().getDecorView();
//            Toast.makeText(this, decorView.getWidth()+"", Toast.LENGTH_SHORT).show();
//            LinearLayout root = (LinearLayout) decorView.getChildAt(0);
//            Toast.makeText(this, root.getWidth()+"", Toast.LENGTH_SHORT).show();
//            FrameLayout titleContainer = (FrameLayout) root.getChildAt(0);
//            Toast.makeText(this, titleContainer.getWidth()+"", Toast.LENGTH_SHORT).show();
//            TextView title = (TextView) titleContainer.getChildAt(0);
//            Toast.makeText(this, title.getWidth()+"", Toast.LENGTH_SHORT).show();
//            title.setGravity(Gravity.CENTER);
//        }
//        catch (Exception e){
//            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
//        }

        //-----------------------------------------------Drop downs----------------------------------//
        //--initialise
        final List<String> items_of_mainClass=new ArrayList<>();
        final List<String> items_of_subClass=new ArrayList<>();
        final MaterialSpinner spinner_mainClass = (MaterialSpinner) findViewById(R.id.spinner_mainClass);
        final MaterialSpinner spinner_subClass = (MaterialSpinner) findViewById(R.id.spinner_subClass);
        //--prepare items
        spinner_mainClass.setItems("FarzUloom", "Bahar-e-Shariyat", "Quran o Hadith", "Ilm e Nahw o Sarf", "Faizane Murshid","Faizane DI");
        items_of_subClass.clear();
        items_of_subClass.add("For Kids");
        for (int i = 0; i <5 ; i++) {
            items_of_subClass.add(spinner_mainClass.getItems().get(0)+" Class "+i);
        }
        spinner_subClass.setItems(items_of_subClass);

        //set select actions
        spinner_mainClass.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                Snackbar.make(view, "Main-Class: " + item, Snackbar.LENGTH_LONG).show();
                items_of_subClass.clear();
                items_of_subClass.add("For Kids");
                for (int i = 0; i <5 ; i++) {
                    items_of_subClass.add(item+" Class "+i);
                }
                //spinner_subClass.setItems("Farz Uloom Class", "Bahar-e-Shariyat", "Quran o Hadith", "Ilm e Nahw o Sarf", "Faizane Murshid","Faizane DI");
                spinner_subClass.setItems(items_of_subClass);
                spinner_subClass.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

                    @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                        Snackbar.make(view, "Sub-Class: " + item, Snackbar.LENGTH_LONG).show();
                    }
                });

            }
        });

        ///---------------------------------------------list of attendance----------------------//
        ListView lview = (ListView) findViewById(R.id.attendance_list);
        populateList();
        ListviewAttendanceAdapter adapter = new ListviewAttendanceAdapter(this, list);
        lview.setAdapter(adapter);



























































        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.setTitle("USERNAME,"+CustomUtils.getCurrentTimeHHMMSS());//title bar text with username and login time

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
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
    }

    private void populateList() {
        list = new ArrayList<HashMap>();
int i=0;

        HashMap temp = new HashMap();
        temp.put(FIRST_COLUMN,++i+"");
        temp.put(SECOND_COLUMN, "Student "+i);
        //temp.put(THIRD_COLUMN, "Rs. 200");
        //temp.put(FOURTH_COLUMN, "Per Unit");
        list.add(temp);

        HashMap temp1 = new HashMap();
        temp1.put(FIRST_COLUMN,++i+"");
        temp1.put(SECOND_COLUMN, "Student "+i);
        //temp1.put(THIRD_COLUMN, "Rs. 400");
        //temp1.put(FOURTH_COLUMN, "Per Unit");
        list.add(temp1);

        HashMap temp2 = new HashMap();
        temp2.put(FIRST_COLUMN,++i+"");
        temp2.put(SECOND_COLUMN, "Student "+i);
        //temp2.put(THIRD_COLUMN, "Rs. 600");
        //temp2.put(FOURTH_COLUMN, "Per Unit");
        list.add(temp2);

        HashMap temp3 = new HashMap();
        temp3.put(FIRST_COLUMN,++i+"");
        temp3.put(SECOND_COLUMN, "Student "+i);
        //temp3.put(THIRD_COLUMN, "Rs. 800");
        //temp3.put(FOURTH_COLUMN, "Per Unit");
        list.add(temp3);

        HashMap temp4 = new HashMap();
        temp4.put(FIRST_COLUMN,++i+"");
        temp4.put(SECOND_COLUMN, "Student "+i);
        //temp4.put(THIRD_COLUMN, "Rs. 100");
        //temp4.put(FOURTH_COLUMN, "Per Unit");
        list.add(temp4);

        HashMap temp5 = new HashMap();
        temp5.put(FIRST_COLUMN,++i+"");
        temp5.put(SECOND_COLUMN, "Student "+i);
        //temp4.put(THIRD_COLUMN, "Rs. 100");
        //temp4.put(FOURTH_COLUMN, "Per Unit");
        list.add(temp5);
    }

//    @Override
//    protected void onSavedInstanceState(Bundle outState){
//    super.onSaveInstanceState(outState);
//        //outState.putInt("key_spinner",items_of_subClass);
//    }
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
        getMenuInflater().inflate(R.menu.home, menu);
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

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
