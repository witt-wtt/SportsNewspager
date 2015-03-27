package com.teamsports.sportsnewpager.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.teamsports.com.teamsports.sportsnewspager.utils.BitmapHelper;
import com.teamsports.sportsnewpager.adapter.TabAdapter;
import com.teamsports.sportsnewspager.sportsnewspager.R;
import com.viewpagerindicator.TabPageIndicator;

/**
 * Created by Administrator on 2015/3/24.
 */
public class FragmentNews extends Fragment {

	private ViewPager mViewPager;
	private TabPageIndicator mIndicator;
	private FragmentPagerAdapter mAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_news, container, false);
		BitmapHelper.initUtils(getActivity());
		mIndicator = (TabPageIndicator)view.findViewById(R.id.indicator);
		mViewPager = (ViewPager) view.findViewById(R.id.pager);
		mAdapter = new TabAdapter(getChildFragmentManager());
		mViewPager.setAdapter(mAdapter);
		mIndicator.setViewPager(mViewPager,0);
        return view;
    }

}
