package com.news.fanabc.Activities;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewPager;
import androidx.core.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.news.fanabc.Adapter.PagerAdapter;
import com.news.fanabc.Fragments.NewsLoader;
import com.news.fanabc.R;
import com.news.fanabc.Utils.Lang_Type;
import com.news.fanabc.Utils.UrlType;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private PagerAdapter pagerAdapter;

    private TabLayout tabLayout;
    private ArrayList<UrlType> urlTypeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        pagerAdapter = new PagerAdapter(getSupportFragmentManager(), this);

        urlTypeList = new Lang_Type().getLanguageUrl(Lang_Type.LangCode.Amharic);
        int i = 0;
        for (UrlType urlType : urlTypeList) {
            if (i > 3) break;
            pagerAdapter.addFragment(NewsLoader.newInstance(urlType.getUrl()), urlType.getType());
            i++;
        }
        // Set up the ViewPager with the sections adapter.

        /**
         * The {@link ViewPager} that will host the section contents.
         */
        ViewPager mViewPager = findViewById(R.id.pager);
        mViewPager.setAdapter(pagerAdapter);
        tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(mViewPager);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
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
    public boolean onNavigationItemSelected(@NotNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.home) {
            // Handle the camera action
            int t = isFoundOnTheTab(UrlType.HOME);
            if (t >= 0) {
                // Set to the Tab
                if(tabLayout.getTabAt(t) != null)tabLayout.getTabAt(t).select();
            } else {
                // Replace The First Tab By The Selected Item.
                addFragmentOnTherLast(0);
            }
        } else if (id == R.id.ethiopia_news) {
            int t = isFoundOnTheTab(UrlType.NEWS);
            if (t >= 0) {
                // Set to the Tab
                if(tabLayout.getTabAt(t) != null)tabLayout.getTabAt(t).select();
            } else {
                // Replace The First Tab By The Selected Item.
                addFragmentOnTherLast(1);
            }

        } else if (id == R.id.business_news) {

            int t = isFoundOnTheTab(UrlType.BUSINESS);
            if (t >= 0) {
                // Set to the Tab
                if(tabLayout.getTabAt(t) != null)tabLayout.getTabAt(t).select();
            } else {
                // Replace The First Tab By The Selected Item.
                addFragmentOnTherLast(2);
            }

        } else if (id == R.id.sport) {
            int t = isFoundOnTheTab(UrlType.SPORT);
            if (t >= 0) {
                // Set to the Tab
                if(tabLayout.getTabAt(t) != null)tabLayout.getTabAt(t).select();
            } else {
                // Replace The First Tab By The Selected Item.
                addFragmentOnTherLast(3);
            }

        } else if (id == R.id.world) {
            int t = isFoundOnTheTab(UrlType.WORLD);
            if (t >= 0) {
                // Set to the Tab
                if(tabLayout.getTabAt(t) != null)tabLayout.getTabAt(t).select();
            } else {
                // Replace The First Tab By The Selected Item.
                addFragmentOnTherLast(4);
            }
        } else if (id == R.id.tech) {
            int t = isFoundOnTheTab(UrlType.TECH);
            if (t >= 0) {
                // Set to the Tab
                if(tabLayout.getTabAt(t) != null)tabLayout.getTabAt(t).select();
            } else {
                // Replace The First Tab By The Selected Item.
                addFragmentOnTherLast(5);
            }

        } else if (id == R.id.health) {
            int t = isFoundOnTheTab(UrlType.HEALTH);
            if (t >= 0) {
                // Set to the Tab
                if(tabLayout.getTabAt(t) != null)tabLayout.getTabAt(t).select();
            } else {
                addFragmentOnTherLast(6);
            }
        } else if (id == R.id.fana_collection) {

            int t = isFoundOnTheTab(UrlType.FANA_COLLECTION);
            if (t >= 0) {
                // Set to the Tab
                if(tabLayout.getTabAt(t) != null)tabLayout.getTabAt(t).select();
            } else {
                // Replace The First Tab By The Selected Item.
                addFragmentOnTherLast(7);
            }

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    int isFoundOnTheTab(String nav_selected_item_name) {
        int number_of_tabs = this.pagerAdapter.getCount();
        for (int i = 0; i < number_of_tabs; i++)
            if (nav_selected_item_name.equals(pagerAdapter.getPageTitle(i))) return i;
        return -1;
    }


    public void addFragmentOnTherLast(int tabindex) {
        UrlType urlType = urlTypeList.get(tabindex);
        /*if(pagerAdapter.getCount() > 4)pagerAdapter.replaceLastFragment(NewsLoader.newInstance(urlType.getUrl()), urlType.getType());
        else*/
        pagerAdapter.addFragment(NewsLoader.newInstance(urlType.getUrl()), urlType.getType());
        int tabCount = this.pagerAdapter.getCount() - 1;
        tabLayout.getTabAt(tabCount).select();
    }

}
