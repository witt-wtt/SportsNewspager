package com.teamsports.sportsnewspager.sportsnewspager;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.teamsports.sportsnewpager.adapter.MyBallTeamListAdapter;
import com.teamsports.sportsnewpager.asynctask.BallTeamListAsyncTask;
import com.teamsports.sportsnewspager.entity.BallTeamInfo;

import java.util.ArrayList;
import java.util.List;


public class BallTeamManageActivity extends ActionBarActivity {
	private ListView ballTeam_list;
	private SearchView searchView;
	private String ballteamUrl="http://interface.sina.cn/sports/sports_navs/client_sports_ctrl/client_sports_ctrl.d.json";
	private MyBallTeamListAdapter adapter;
	private List<BallTeamInfo> totalList = new ArrayList<>();
	private RequestQueue mQueue;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ball_manage);
		mQueue = Volley.newRequestQueue(this);
		ballTeam_list = (ListView) findViewById(R.id.ballTeam_list);
		adapter = new MyBallTeamListAdapter(this,totalList,mQueue);
		ballTeam_list.setAdapter(adapter);
		//启动异步加载球队列表数据
		new BallTeamListAsyncTask(this,totalList,adapter).execute(ballteamUrl);


	}
}
