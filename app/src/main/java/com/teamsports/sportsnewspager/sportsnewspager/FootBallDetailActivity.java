package com.teamsports.sportsnewspager.sportsnewspager;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.teamsports.sportsnewpager.adapter.MyBallAdapter;
import com.teamsports.sportsnewpager.asynctask.MyAsyncTask;
import com.teamsports.sportsnewspager.entity.BallInfo;

import java.util.ArrayList;
import java.util.List;


public class FootBallDetailActivity extends ActionBarActivity {
	private String allFootballUrl="http://platform.sina.com.cn/sports_client/team_hot?app_key=2586208540&type=1&num=50";
	private List<BallInfo> totalist = new ArrayList<>();
	private MyBallAdapter adapter;
	private RequestQueue mQueue;
	private ListView football_lv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_foot_ball_detail);
		mQueue = Volley.newRequestQueue(this);
		football_lv = (ListView) findViewById(R.id.football_lv);
		adapter = new MyBallAdapter(this,totalist,mQueue);
		football_lv.setAdapter(adapter);
		new MyAsyncTask(this,totalist,adapter).execute(allFootballUrl);
	}
}
