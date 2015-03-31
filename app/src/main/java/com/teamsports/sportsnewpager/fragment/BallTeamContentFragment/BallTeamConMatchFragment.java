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
import com.teamsports.sportsnewpager.adapter.MyBallTeamConAdapter;
import com.teamsports.sportsnewpager.asynctask.BallTeamMatchInfAsyTask;
import com.teamsports.sportsnewspager.entity.BallTeamMatchInfo;
import com.teamsports.sportsnewspager.sportsnewspager.R;

import java.util.ArrayList;
import java.util.List;


public class BallTeamConMatchFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
	private ListView ballteam_lv_coninfo;
	private MyBallTeamConAdapter conAdapter;
	private List<BallTeamMatchInfo> totalList = new ArrayList<>();
	private RequestQueue mQueue;
	private SwipeRefreshLayout swipeRefreshLayout;

	private String url="http://platform.sina.com.cn/sports_all/client_api?app_key=2586208540&_sport_t_=livecast&__version__=3.0.0.12&__os__=android&show_extra=1&f=livecast_id,LeagueType,status,Team1Id,Team2Id,Score1,Score2,MatchId,LiveMode,Discipline,data_from,Title,date,time,Team1,Team2,Flag1,Flag2,NewsUrl,VideoUrl,LiveUrl,LiveStatusExtra,VideoLiveUrl,VideoBeginTime,narrator,LeagueType_cn,Discipline_cn,comment_id,odds_id,VideoEndTime,if_rotate_video,LiveStatusExtra_cn,m3u8,android,period_cn,program,penalty1,penalty1,Round_cn,extrarec_ovx&_sport_a_=typeMatches&team_id=130";
	public BallTeamConMatchFragment(){}
	public BallTeamConMatchFragment(String url){
		this.url = url;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_ball_team_con, container, false);
		//初始化请求队列
		mQueue = Volley.newRequestQueue(getActivity());
		ballteam_lv_coninfo = (ListView) view .findViewById(R.id.ballteam_lv_coninfo);
		swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe);

		conAdapter = new MyBallTeamConAdapter(getActivity(),totalList,mQueue);
		ballteam_lv_coninfo.setAdapter(conAdapter);
		//设置刷新监听
		swipeRefreshLayout.setOnRefreshListener(this);
		return view;
	}
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

	}

	@Override
	public void onRefresh() {
		totalList.clear();
		new BallTeamMatchInfAsyTask(getActivity(),totalList,conAdapter,swipeRefreshLayout).execute(url);
	}
}
