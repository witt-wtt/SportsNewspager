package com.teamsports.sportsnewspager.sportsnewspager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.widget.RadioGroup;

import com.teamsports.sportsnewpager.fragment.BallTeamContentFragment.BallTeamConMatchFragment;
import com.teamsports.sportsnewpager.fragment.BallTeamContentFragment.BallTeamConNewsFragment;

import java.util.ArrayList;
import java.util.List;


public class BallTeamContentActivity extends ActionBarActivity implements RadioGroup.OnCheckedChangeListener {
	//private int position=0;
	private ViewPager ballteam_content_viewpager;
	private List<Fragment> flist = new ArrayList<>();
	private MyPagerAdapter pAdapter;
	private RadioGroup ballteam_rg_menu;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ball_team_content);
		ballteam_content_viewpager = (ViewPager) findViewById(R.id.ballteam_content_viewpager);
		ballteam_rg_menu = (RadioGroup) findViewById(R.id.ballteam_rg_menu);

		flist.add(new BallTeamConMatchFragment());
		flist.add(new BallTeamConNewsFragment());
		flist.add(new BallTeamConMatchFragment());
		flist.add(new BallTeamConMatchFragment());
		flist.add(new BallTeamConMatchFragment());

		pAdapter = new MyPagerAdapter(getSupportFragmentManager());
		ballteam_content_viewpager.setAdapter(pAdapter);

		ballteam_rg_menu.setOnCheckedChangeListener(this);

	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		switch (checkedId){
			case R.id.ballteam_rb_match:
				ballteam_content_viewpager.setCurrentItem(0);
				break;
			case R.id.ballteam_rb_news:
				ballteam_content_viewpager.setCurrentItem(1);
				break;

			case R.id.ballteam_rb_data:
				ballteam_content_viewpager.setCurrentItem(2);
				break;
			case R.id.ballteam_rb_chatroom:
				ballteam_content_viewpager.setCurrentItem(3);
				break;
			case R.id.ballteam_rb_weibo:
				ballteam_content_viewpager.setCurrentItem(4);
				break;
		}
	}

	class MyPagerAdapter extends FragmentPagerAdapter {

		public MyPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int i) {
			return flist.get(i);
		}

		@Override
		public int getCount() {
			return flist.size();
		}
	}
}
