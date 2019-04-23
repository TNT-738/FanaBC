package com.news.fanabc.Adapter;

import android.content.Context;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class PagerAdapter extends FragmentPagerAdapter {
    private final ArrayList<Fragment> myFragments = new ArrayList<>();
    private final ArrayList<String> myFragmentTitles = new ArrayList<>();
    private Context context;

    public PagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    public void addFragment(Fragment fragment, String title) {
        myFragments.add(fragment);
        myFragmentTitles.add(title);
        this.notifyDataSetChanged();
    }
    public void replaceLastFragment(Fragment newFragment, String title){
        myFragmentTitles.remove(this.getCount() - 1);
        myFragments.remove(this.getCount() - 1);
        //this.addFragment(newFragment, title);
        this.notifyDataSetChanged();
        this.addFragment(newFragment, title);
    }

    @Override
    public Fragment getItem(int position) {
        return myFragments.get(position);
    }

    @Override
    public int getCount() {
        return myFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return myFragmentTitles.get(position);
    }
}