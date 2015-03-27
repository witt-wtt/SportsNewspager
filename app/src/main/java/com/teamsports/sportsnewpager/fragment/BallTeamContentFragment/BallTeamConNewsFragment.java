package com.teamsports.sportsnewpager.fragment.BallTeamContentFragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.teamsports.sportsnewpager.adapter.MyBallTeamConNewsAdapter;
import com.teamsports.sportsnewpager.asynctask.BallTeamNewsInfAsyTask;
import com.teamsports.sportsnewspager.entity.BallTeamNewsInfo;
import com.teamsports.sportsnewspager.sportsnewspager.R;

import java.util.ArrayList;
import java.util.List;

public class BallTeamConNewsFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
	private String newsString="http://platform.sina.com.cn/sports_client/feed?app_key=2586208540&team_id=130&f=title%2Curl%2Ccategoryid%2Cimg%2Ccomment_show%2Cctime%2Cvid%2Cvideo_info&num=40";
	private MyBallTeamConNewsAdapter newsAdapter;
	private ListView newsListView;
	private RequestQueue mQueue;
//	private int num = 20;

	private boolean buttom=false;
	private List<BallTeamNewsInfo> totalList = new ArrayList<>();
	private SwipeRefreshLayout refreshLayout;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_ball_team_con_match, container, false);
		mQueue = Volley.newRequestQueue(getActivity());
		refreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe);
		newsListView = (ListView) view.findViewById(R.id.ballteam_lv_coninfo);
		newsAdapter = new MyBallTeamConNewsAdapter(getActivity(),totalList,mQueue);
		newsListView.setAdapter(newsAdapter);
		refreshLayout.setOnRefreshListener(this);

//		newsListView.setOnScrollListener(new AbsListView.OnScrollListener() {
//			@Override
//			public void onScrollStateChanged(AbsListView view, int scrollState) {
//				if(scrollState==0&&buttom==true){
//					num+=20;
//					totalList.clear();
//					new BallTeamNewsInfAsyTask(getActivity(),totalList,newsAdapter,refreshLayout).execute(newsString+num);
//				}
//			}
//
//			@Override
//			public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
//				buttom = (firstVisibleItem+visibleItemCount)==totalItemCount;
//			}
//		});
		return view;
	}

	public void onAttach(Activity activity) {
		super.onAttach(activity);

	}


	@Override
	public void onRefresh() {
		totalList.clear();
		new BallTeamNewsInfAsyTask(getActivity(),totalList,newsAdapter,refreshLayout).execute(newsString);
	}
}
