package com.news.fanabc.Activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import androidx.core.app.FragmentPagerAdapter;
import androidx.core.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.news.fanabc.Adapter.PagerAdapter;
import com.news.fanabc.Fragments.NewsLoader;
import com.news.fanabc.R;
import com.news.fanabc.Utils.Lang_Type;
import com.news.fanabc.Utils.UrlType;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        /**
         * The {@link androidx.core.view.PagerAdapter} that will provide
         * fragments for each of the sections. We use a
         * {@link FragmentPagerAdapter} derivative, which will keep every
         * loaded fragment in memory. If this becomes too memory intensive, it
         * may be best to switch to a
         * {@link androidx.core.app.FragmentStatePagerAdapter}.
         */
        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager(), this);

        ArrayList<UrlType> urlTypeList = new Lang_Type().getLanguageUrl(Lang_Type.LangCode.Amharic);
        int i = 0;
        for (UrlType urlType : urlTypeList) {
            if(i > 3)break;
            pagerAdapter.addFragment(NewsLoader.newInstance(urlType.getUrl()), urlType.getType());
            i++;
        }
        // Set up the ViewPager with the sections adapter.
        /**
         * The {@link ViewPager} that will host the section contents.
         */
        ViewPager mViewPager = findViewById(R.id.pager);
        mViewPager.setAdapter(pagerAdapter);
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(mViewPager);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */

}
