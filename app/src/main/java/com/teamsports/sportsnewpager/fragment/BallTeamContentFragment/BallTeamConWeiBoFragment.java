package com.teamsports.sportsnewpager.fragment.BallTeamContentFragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.teamsports.sportsnewpager.adapter.MyBallteamConWeiboAdapter;
import com.teamsports.sportsnewpager.asynctask.BallTeamWeiboInfAsyTask;
import com.teamsports.sportsnewspager.entity.BallTeamWeiboInfo;
import com.teamsports.sportsnewspager.sportsnewspager.R;

import java.util.ArrayList;
import java.util.List;


public class BallTeamConWeiBoFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
	private ListView con_lv;
	private MyBallteamConWeiboAdapter weiboAdapter;
	private List<BallTeamWeiboInfo> totalList = new ArrayList<>();
	private RequestQueue mQueue;
	private SwipeRefreshLayout swipeRefreshLayout;
	private String weiboUrl = "http://platform.sina.com.cn/sports_client/team_weibo?app_key=2586208540&team_id=nba_5&_page=";
	private int page = 1;
	private boolean buttom=false;

	public BallTeamConWeiBoFragment(String weiboUrl){
		this.weiboUrl = weiboUrl;
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_ball_team_con, container, false);
		mQueue = Volley.newRequestQueue(getActivity());
		swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe);
		con_lv = (ListView) view.findViewById(R.id.ballteam_lv_coninfo);
		weiboAdapter = new MyBallteamConWeiboAdapter(getActivity(),totalList,mQueue);
		con_lv.setAdapter(weiboAdapter);
		swipeRefreshLayout.setOnRefreshListener(this);

		con_lv.setOnScrollListener(new AbsListView.OnScrollListener() {
			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				if(buttom&scrollState==0){
					page++;
					new BallTeamWeiboInfAsyTask(getActivity(),totalList,weiboAdapter,swipeRefreshLayout).execute(weiboUrl+page);
				}
			}

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
				buttom = (firstVisibleItem+visibleItemCount)==totalItemCount;
			}
		});
		return view;
	}
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
	}

	@Override
	public void onRefresh() {
		totalList.clear();
		new BallTeamWeiboInfAsyTask(getActivity(),totalList,weiboAdapter,swipeRefreshLayout).execute(weiboUrl);
	}
}
