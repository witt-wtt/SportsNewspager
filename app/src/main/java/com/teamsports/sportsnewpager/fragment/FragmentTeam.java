package com.teamsports.sportsnewpager.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.Switch;

import com.teamsports.sportsnewpager.asynctask.MyAsyncTask;
import com.teamsports.sportsnewspager.entity.BallInfo;
import com.teamsports.sportsnewspager.sportsnewspager.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/3/24.
 */
public class FragmentTeam extends Fragment implements RadioGroup.OnCheckedChangeListener{

	private ViewPager team_viewPager;
	private RadioGroup team_rg_menu;
	private List<Fragment> fragmentList = new ArrayList<>();
	private MyPagerAdapter pagerAdapter;
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_team, container, false);
		team_rg_menu = (RadioGroup) view.findViewById(R.id.team_rg_menu);
		team_viewPager = (ViewPager) view.findViewById(R.id.team_viewPager);
		//在list里面添加碎片
		fragmentList.add(new MyBallTeamFragment());
		fragmentList.add(new TeamFragment_Ranking());
		//注意点 碎片上放置碎片时，要用子碎片管理器getChildFragmentManager()
		pagerAdapter = new MyPagerAdapter(getChildFragmentManager());
		//设置Viewpager适配器
		team_viewPager.setAdapter(pagerAdapter);
		//设置菜单监听
		team_rg_menu.setOnCheckedChangeListener(this);
		//设置默认显示界面
		team_viewPager.setCurrentItem(0);
		return view;
	}
	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		switch (checkedId){
			case R.id.team_rb_MyTeam:
				team_viewPager.setCurrentItem(0);
				break;
			case R.id.team_rb_PopularityRranking:
				team_viewPager.setCurrentItem(1);
				break;
		}
	}
	//自定义viewPager适配器
	class MyPagerAdapter extends FragmentPagerAdapter{

		public MyPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			return fragmentList.get(position);
		}

		@Override
		public int getCount() {
			return fragmentList.size();
		}
	}
}
