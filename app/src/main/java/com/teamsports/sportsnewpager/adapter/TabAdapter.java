package com.teamsports.sportsnewpager.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.teamsports.sportsnewpager.fragment.MainFragment;

/**
 * Created by HTao on 2015/3/24.
 */
public class TabAdapter extends FragmentPagerAdapter {
    public static final String[] TITLES = new String[]{"热门", "NBA", "国际足球", "中国足球", "CBA", "综合体育"};
    public static final int [] feed_id = new int[]{1009,653,654,655,656,657};
    public TabAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
       if (position==0){
           MainFragment fragment = new MainFragment();
           Bundle bundle = new Bundle();
           bundle.putInt("feed_id",feed_id[position]);
           bundle.putString("TITLES",TITLES[position]);
           fragment.setArguments(bundle);
           return fragment;
       }else{
           MainFragment fragment = new MainFragment();
           Bundle bundle = new Bundle();
           bundle.putInt("feed_id",feed_id[position]);
           bundle.putString("TITLES",TITLES[position]);
           fragment.setArguments(bundle);
           return fragment;
       }

    }


    @Override
    public int getCount() {
        return TITLES.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return TITLES[position % TITLES.length];
    }
}
