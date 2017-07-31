package com.qtechie.ghausiyamaktab.activity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.style.TextAppearanceSpan;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListView;

import com.jaredrummler.materialspinner.MaterialSpinner;
import com.qtechie.ghausiyamaktab.utils.CustomUtils;
import com.qtechie.ghausiyamaktab.adapters.ListviewAttendanceAdapter;
import com.qtechie.ghausiyamaktab.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.qtechie.ghausiyamaktab.adapters.ListviewAttendanceAdapter.FIRST_COLUMN;
import static com.qtechie.ghausiyamaktab.adapters.ListviewAttendanceAdapter.SECOND_COLUMN;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private ArrayList<HashMap> list;
    Menu menu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out); //animate changing of activities
        setContentView(R.layout.activity_home);
        Window w = getWindow(); // in Activity's onCreate() for instance
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            w.setStatusBarColor(Color.parseColor("#aa86b300"));//colored status bar
        }
        else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);  //for making it totally transparent
        }

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
       // spinner_mainClass.setItems("FarzUloom", "Bahar-e-Shariyat", "Quran o Hadith", "Ilm e Nahw o Sarf", "Faizane Murshid","Faizane DI");
        spinner_mainClass.setItems("Analytics", "Bussiness Studies", "Commerce", "Data Mining", "E-Commerce");
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


        LayoutInflater inflater =  this.getLayoutInflater();
        View foldingCellView=inflater.inflate(R.layout.attendance_list_item, null);

        //Toast.makeText(this, ""+fc.getId(), Toast.LENGTH_SHORT).show();


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //this.setTitle("USERNAME,"+ CustomUtils.getCurrentTimeHHMMSS());//title bar text with username and login time
        this.setTitle("");
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Hey FOOL!!!. Don't poke me like that", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.getChildAt(0).setVerticalScrollBarEnabled(false);//remove scroll
        menu = navigationView.getMenu();

        MenuItem item_home= menu.findItem(R.id.nav_home_main);
        MenuItem item_student= menu.findItem(R.id.nav_student_main);
        MenuItem item_member= menu.findItem(R.id.nav_member_main);
        MenuItem item_account= menu.findItem(R.id.nav_accounts_main);
        MenuItem item_others= menu.findItem(R.id.nav_others_main);
        modifyNavDrawerMenuTitle(item_home);
        modifyNavDrawerMenuTitle(item_student);
        modifyNavDrawerMenuTitle(item_member);
        modifyNavDrawerMenuTitle(item_account);
        modifyNavDrawerMenuTitle(item_others);

        navigationView.setNavigationItemSelectedListener(this);

    }
private void modifyNavDrawerMenuTitle(MenuItem item){
    SpannableString s = new SpannableString(item.getTitle());
    s.setSpan(new TextAppearanceSpan(this, R.style.TextAppearance4navDrawerMenuTitle), 0, s.length(), 0);
    item.setTitle(s);
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

        if (id == R.id.nav_student_main) {
            boolean b=!menu.findItem(R.id.nav_student_attend).isVisible();
            menu.findItem(R.id.nav_student_attend).setVisible(b);
            menu.findItem(R.id.nav_student_regd).setVisible(b);
            menu.findItem(R.id.nav_student_search).setVisible(b);
            return true;
            // Handle the camera action
        }
        //else if (id == R.id.nav_gallery) {
//
//        } else if (id == R.id.nav_slideshow) {
//
//        } else if (id == R.id.nav_manage) {
//
//        } else if (id == R.id.nav_share) {
//
//        } else if (id == R.id.nav_send) {
//
//        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
