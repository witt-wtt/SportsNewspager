package com.teamsports.sportsnewspager.sportsnewspager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.teamsports.sportsnewpager.fragment.BallTeamContentFragment.BallTeamConDataFragment;
import com.teamsports.sportsnewpager.fragment.BallTeamContentFragment.BallTeamConMatchFragment;
import com.teamsports.sportsnewpager.fragment.BallTeamContentFragment.BallTeamConNewsFragment;
import com.teamsports.sportsnewpager.fragment.BallTeamContentFragment.BallTeamConWeiBoFragment;

import java.util.ArrayList;
import java.util.List;


public class BallTeamContentActivity extends ActionBarActivity implements RadioGroup.OnCheckedChangeListener {
	//private int position=0;
	private ViewPager ballteam_content_viewpager;
	private List<Fragment> flist = new ArrayList<>();
	private MyPagerAdapter pAdapter;
	private RadioGroup ballteam_rg_menu;
	private int index=0;
	//足球比赛url
	private String url="http://platform.sina.com.cn/sports_all/client_api?app_key=2586208540&_sport_t_=livecast&__version__=3.0.0.12&__os__=android&show_extra=1&f=livecast_id,LeagueType,status,Team1Id,Team2Id,Score1,Score2,MatchId,LiveMode,Discipline,data_from,Title,date,time,Team1,Team2,Flag1,Flag2,NewsUrl,VideoUrl,LiveUrl,LiveStatusExtra,VideoLiveUrl,VideoBeginTime,narrator,LeagueType_cn,Discipline_cn,comment_id,odds_id,VideoEndTime,if_rotate_video,LiveStatusExtra_cn,m3u8,android,period_cn,program,penalty1,penalty1,Round_cn,extrarec_ovx&_sport_a_=typeMatches&team_id=130";
	//足球新闻url
	private String newsString="http://platform.sina.com.cn/sports_client/feed?app_key=2586208540&team_id=130&f=title%2Curl%2Ccategoryid%2Cimg%2Ccomment_show%2Cctime%2Cvid%2Cvideo_info&num=40";

	//篮球比赛url
	private String bkmatchurl="http://platform.sina.com.cn/sports_all/client_api?app_key=2586208540&_sport_t_=livecast&__version__=3.0.0.12&__os__=android&show_extra=1&f=livecast_id,LeagueType,status,Team1Id,Team2Id,Score1,Score2,MatchId,LiveMode,Discipline,data_from,Title,date,time,Team1,Team2,Flag1,Flag2,NewsUrl,VideoUrl,LiveUrl,LiveStatusExtra,VideoLiveUrl,VideoBeginTime,narrator,LeagueType_cn,Discipline_cn,comment_id,odds_id,VideoEndTime,if_rotate_video,LiveStatusExtra_cn,m3u8,android,period_cn,program,penalty1,penalty1,Round_cn,extrarec_ovx&_sport_a_=typeMatches&team_id=nba_5";
	//篮球新闻url
	private String bkNewsurl="http://platform.sina.com.cn/sports_client/feed?app_key=2586208540&team_id=nba_5&f=title%2Curl%2Ccategoryid%2Cimg%2Ccomment_show%2Cctime%2Cvid%2Cvideo_info&num=40";
	//足球微博Url
	private String footweibourl = "http://platform.sina.com.cn/sports_client/team_weibo?app_key=2586208540&team_id=130&_page=1";
	//篮球微博url
	private String bkweibourl = "http://platform.sina.com.cn/sports_client/team_weibo?app_key=2586208540&team_id=nba_5&_page=1";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ball_team_content);
		ballteam_content_viewpager = (ViewPager) findViewById(R.id.ballteam_content_viewpager);
		ballteam_rg_menu = (RadioGroup) findViewById(R.id.ballteam_rg_menu);

		index = getIntent().getExtras().getInt("index");
		if(index==0){
			flist.add(new BallTeamConMatchFragment(url));
			flist.add(new BallTeamConNewsFragment(newsString));
			flist.add(new BallTeamConDataFragment(0));
			flist.add(new BallTeamConMatchFragment(url));
			flist.add(new BallTeamConWeiBoFragment(footweibourl));
		}else{
			flist.add(new BallTeamConMatchFragment(bkmatchurl));
			flist.add(new BallTeamConNewsFragment(bkNewsurl));
			flist.add(new BallTeamConDataFragment(1));
			flist.add(new BallTeamConMatchFragment(bkmatchurl));
			flist.add(new BallTeamConWeiBoFragment(bkweibourl));
		}

		pAdapter = new MyPagerAdapter(getSupportFragmentManager());
		ballteam_content_viewpager.setAdapter(pAdapter);
		ballteam_rg_menu.setOnCheckedChangeListener(this);
		ballteam_content_viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

			}
			@Override
			public void onPageSelected(int position) {

				RadioButton radioButton = (RadioButton) ballteam_rg_menu.getChildAt(position);
				radioButton.setChecked(true);
			}
			@Override
			public void onPageScrollStateChanged(int state) {
			}
		});
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
