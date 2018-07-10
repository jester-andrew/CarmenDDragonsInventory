package com.dragons.carmenddragonsinventory;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import java.util.Random;

import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class The_Main_Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private ImageView Circle;
    private Random random = new Random();
    private int lastDir;
    private boolean spinning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.the_main_activity);
        {
            Circle = findViewById(R.id.Circle);
        }
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }
public void spin(View v){
        if (!spinning) {
            int newDir = random.nextInt(1800);
            float pivotx = Circle.getWidth() / 2;
            float pivotY = Circle.getHeight() / 2;

            Animation rotate = new RotateAnimation(lastDir, newDir, pivotY, pivotx);
            rotate.setDuration(2500);
            rotate.setFillAfter(true);
            rotate.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    spinning = true;
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    spinning = false;
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });

            lastDir = newDir;
            Circle.startAnimation(rotate);
        }
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

        if (id == R.id.nav_camera) {
            // By clicking on the 1st button
            Intent Edit = new Intent(this, Add_Item.class);
            startActivity(Edit);
        } else if (id == R.id.Update) {
            //The second Button

        } else if (id == R.id.Inv1) {
            //Fantasy creature button
            Intent displayInventory = new Intent(this, InventoryDisplayActivity.class);
            displayInventory.putExtra("CATEGORY","fantasy");
            startActivity(displayInventory);

        } else if (id == R.id.Inv2) {
            //The fourth button
            Intent displayInventory = new Intent(this, InventoryDisplayActivity.class);
            displayInventory.putExtra("CATEGORY","woodland");
            startActivity(displayInventory);
        } else if (id == R.id.Inv3) {
            //THe fifth button
            Intent displayInventory = new Intent(this, InventoryDisplayActivity.class);
            displayInventory.putExtra("CATEGORY","underwater");
            startActivity(displayInventory);
        }
        else if (id == R.id.nav_share) {
            //Sales History button
            Intent displayInventory = new Intent(this, Sales_History.class);

        } else if (id == R.id.nav_send) {
            //The 7th button

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
