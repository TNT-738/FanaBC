package com.news.fanabc.Activities;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.news.fanabc.Adapter.PagerAdapter;
import com.news.fanabc.Fragments.NewsLoader;
import com.news.fanabc.R;
import com.news.fanabc.Utils.Lang_Type;
import com.news.fanabc.Utils.SettingManager;
import com.news.fanabc.Utils.UrlType;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Locale;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private PagerAdapter pagerAdapter;

    private TabLayout tabLayout;

    private Lang_Type.LangCode settingLanguage;
    private Lang_Type lang_type;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.settingLanguage = SettingManager.SettingBuilder(this).getSettingLanguage();

        switch (this.settingLanguage){
            case AfanOromo:
                Log.d("zzzzz", "Setting Oromifa Language Locale");
                setLocale("afn");
                break;
            case English:
                setLocale("en");
                break;
            case Tigrigna:
                setLocale("tgr");
                break;
            default:
                setLocale("amh");
                break;
        }
        setContentView(R.layout.activity_home);

        Log.d("zzzzz", String.format("Current Language is: %s", this.settingLanguage.name()));
        this.lang_type = new Lang_Type(this.settingLanguage, this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        pagerAdapter = new PagerAdapter(getSupportFragmentManager(), this);
        ArrayList<UrlType> urlTypeList = this.lang_type.getTabUrlType();
        for (UrlType urlType : urlTypeList) {
            //Log.d("eeeee", urlType.getType());
            pagerAdapter.addFragment(NewsLoader.newInstance(urlType.getUrl()), urlType.getType());
        }
        // Set up the ViewPager with the sections adapter.

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
        if (settingLanguage.equals(Lang_Type.LangCode.AfanOromo))
            navigationView.getMenu().findItem(R.id.videos).setVisible(false);
        if (settingLanguage.equals(Lang_Type.LangCode.English)) {
            navigationView.getMenu().findItem(R.id.videos).setVisible(false);
            navigationView.getMenu().findItem(R.id.health).setVisible(false);
        }
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
        if (id == R.id.about_menu) {
            startAboutActivity();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void startAboutActivity() {
    Intent aboutActivityStarter = new Intent(this, AboutActivity.class);
    startActivity(aboutActivityStarter);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NotNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.home) {
            int t = isFoundOnTheTab(this.lang_type.HOME);
            if (t >= 0) {
                // Set to the Tab
                if (tabLayout.getTabAt(t) != null) {
                    tabLayout.getTabAt(t).select();
                }
            } else {
                // Replace The First Tab By The Selected Item.
                addFragmentOnTherLast(this.lang_type.HOME);
            }
        } else if (id == R.id.ethiopia_news) {
            int t = isFoundOnTheTab(this.lang_type.NEWS);
            if (t >= 0) {
                // Set to the Tab
                if (tabLayout.getTabAt(t) != null) tabLayout.getTabAt(t).select();
            } else {
                // Replace The First Tab By The Selected Item.
                addFragmentOnTherLast(this.lang_type.NEWS);
            }

        } else if (id == R.id.business_news) {

            int t = isFoundOnTheTab(this.lang_type.BUSINESS);
            if (t >= 0) {
                // Set to the Tab
                if (tabLayout.getTabAt(t) != null) tabLayout.getTabAt(t).select();
            } else {
                // Replace The First Tab By The Selected Item.
                addFragmentOnTherLast(this.lang_type.BUSINESS);
            }

        } else if (id == R.id.sport) {
            int t = isFoundOnTheTab(this.lang_type.SPORT);
            if (t >= 0) {
                // Set to the Tab
                if (tabLayout.getTabAt(t) != null) tabLayout.getTabAt(t).select();
            } else {
                // Replace The First Tab By The Selected Item.
                addFragmentOnTherLast(this.lang_type.SPORT);
            }

        } else if (id == R.id.world) {
            int t = isFoundOnTheTab(this.lang_type.WORLD);
            if (t >= 0) {
                // Set to the Tab
                if (tabLayout.getTabAt(t) != null) tabLayout.getTabAt(t).select();
            } else {
                // Replace The First Tab By The Selected Item.
                addFragmentOnTherLast(this.lang_type.WORLD);
            }
        } else if (id == R.id.tech) {
            int t = isFoundOnTheTab(this.lang_type.TECH);
            if (t >= 0) {
                // Set to the Tab
                if (tabLayout.getTabAt(t) != null) tabLayout.getTabAt(t).select();
            } else {
                // Replace The First Tab By The Selected Item.
                addFragmentOnTherLast(this.lang_type.TECH);
            }

        } else if (id == R.id.videos) {
            int t = isFoundOnTheTab(this.lang_type.VIDEO);
            if (t >= 0) {
                // Set to the Tab
                if (tabLayout.getTabAt(t) != null) tabLayout.getTabAt(t).select();
            } else {
                // Replace The First Tab By The Selected Item.
                addFragmentOnTherLast(this.lang_type.VIDEO);
            }

        } else if (id == R.id.health) {
            int t = isFoundOnTheTab(this.lang_type.HEALTH);
            if (t >= 0) {
                // Set to the Tab
                if (tabLayout.getTabAt(t) != null) tabLayout.getTabAt(t).select();
            } else {
                // Replace The First Tab By The Selected Item.
                addFragmentOnTherLast(this.lang_type.HEALTH);
            }

        } else if (id == R.id.amh_language) {
            if (!this.settingLanguage.equals(Lang_Type.LangCode.Amharic)) {
                SettingManager.SettingBuilder(this).setSettingLanguage(Lang_Type.LangCode.Amharic);
                this.restartActivity();
            }
        } else if (id == R.id.eng_language) {
            if (!this.settingLanguage.equals(Lang_Type.LangCode.English)) {
                SettingManager.SettingBuilder(this).setSettingLanguage(Lang_Type.LangCode.English);
                this.restartActivity();
            }
        } else if (id == R.id.oro_language) {
            if (!this.settingLanguage.equals(Lang_Type.LangCode.AfanOromo)) {
                SettingManager.SettingBuilder(this).setSettingLanguage(Lang_Type.LangCode.AfanOromo);
                this.restartActivity();
            }
        } else if (id == R.id.tigri_language) {
            if (!this.settingLanguage.equals(Lang_Type.LangCode.Tigrigna)) {
                SettingManager.SettingBuilder(this).setSettingLanguage(Lang_Type.LangCode.Tigrigna);
                this.restartActivity();
            }
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    int isFoundOnTheTab(String nav_selected_item_name) {
        int number_of_tabs = this.pagerAdapter.getCount();
        for (int i = 0; i < number_of_tabs; i++)
            if (nav_selected_item_name.contentEquals(pagerAdapter.getPageTitle(i))) return i;
        return -1;
    }

    void restartActivity() {

                startActivity(new Intent(this, HomeActivity.class));
        this.finishAffinity();
    }


    public void addFragmentOnTherLast(String type) {
        ArrayList<UrlType> newurlTypes = new Lang_Type(this.settingLanguage, this).getAllUrl();//.get(tabindex);

        UrlType urlType = null;
        for(UrlType selectedUrlType : newurlTypes){
            if(selectedUrlType.getType().equals(type)){
                urlType = selectedUrlType;
                break;
            }
        }
        if(urlType != null) {
            pagerAdapter.addFragment(NewsLoader.newInstance(urlType.getUrl()), urlType.getType());
            int tabCount = this.pagerAdapter.getCount() - 1;
            tabLayout.getTabAt(tabCount).select();
        }
    }

    public void setLocale(String langcode) {
        Locale locale = new Locale(langcode.toLowerCase());
        Resources res = this.getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = locale;
        res.updateConfiguration(conf, dm);
    }






}
