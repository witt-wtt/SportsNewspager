package com.teamsports.sportsnewpager.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.teamsports.sportsnewpager.adapter.MyBallAdapter;
import com.teamsports.sportsnewpager.asynctask.MyAsyncTask;
import com.teamsports.sportsnewspager.entity.BallInfo;
import com.teamsports.sportsnewspager.sportsnewspager.BallTeamContentActivity;
import com.teamsports.sportsnewspager.sportsnewspager.BkBallDetailActivity;
import com.teamsports.sportsnewspager.sportsnewspager.FootBallDetailActivity;
import com.teamsports.sportsnewspager.sportsnewspager.R;

import java.util.ArrayList;
import java.util.List;


public class TeamFragment_Ranking extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener {
	private ListView team_lv_footBall;
	private ListView team_lv_baseketBall;

	private String bkBallUrl="http://platform.sina.com.cn/sports_client/team_hot?app_key=2586208540&uid=5116318736&type=2&num=4";

	//private String allBkballUrl="http://platform.sina.com.cn/sports_client/team_hot?app_key=2586208540&uid=5116318736&type=2&num=50";


	private String footBallUrl="http://platform.sina.com.cn/sports_client/team_hot?app_key=2586208540&type=1&num=4";


	private List<BallInfo> totalist = new ArrayList<>();
	private List<BallInfo> totalist1 = new ArrayList<>();
	private MyBallAdapter adapter;
	private MyBallAdapter fadapter;
	private RequestQueue mQueue;
	private TextView team_tv_bkball;
	private TextView team_tv_football;
	private MyAsyncTask basyncTask;
	private MyAsyncTask fasyncTask;
	//在此方法中进行网络访问，持方法只执行一次
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		if (adapter == null && fadapter==null ) {
			adapter = new MyBallAdapter(getActivity(),totalist,mQueue);
			fadapter = new MyBallAdapter(getActivity(),totalist1,mQueue);
		}else {
			totalist.clear();
			totalist1.clear();
		}
		//		初始化请求队列
		mQueue = Volley.newRequestQueue(getActivity());
		//		初始化适配器
		adapter = new MyBallAdapter(getActivity(),totalist,mQueue);
		fadapter = new MyBallAdapter(getActivity(),totalist1,mQueue);

//		启动异步任务
		basyncTask = new MyAsyncTask(getActivity(),totalist,adapter);
		basyncTask.execute(bkBallUrl);

		fasyncTask = new MyAsyncTask(getActivity(),totalist1,fadapter);
		fasyncTask.execute(footBallUrl);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_team_fragment__ranking, container, false);
//		初始化View
		initView(view);
//		设置篮球适配器
		team_lv_baseketBall.setAdapter(adapter);
		//设置足球适配器
		team_lv_footBall.setAdapter(fadapter);
		//查看更多设置监听
		team_tv_bkball.setOnClickListener(this);
		team_tv_football.setOnClickListener(this);

		//设置篮球条目监听
		team_lv_baseketBall.setOnItemClickListener(this);



		return view;
	}
	private void initView(View view){
		team_lv_baseketBall = (ListView) view.findViewById(R.id.team_lv_baseketBall);
		team_lv_footBall = (ListView) view.findViewById(R.id.team_lv_footBall);
		team_tv_bkball = (TextView) view.findViewById(R.id.team_tv_baseketball);
		team_tv_football = (TextView) view.findViewById(R.id.team_tv_football);
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.team_tv_baseketball:
				//跳转到篮球全部排行页面
				Intent intent = new Intent(getActivity(),BkBallDetailActivity.class);
				startActivity(intent);
				break;
			case R.id.team_tv_football:
				//跳转到足球全部排行页面
				Intent intent1 = new Intent(getActivity(),FootBallDetailActivity.class);
				startActivity(intent1);
				break;
		}
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		basyncTask.cancel(true);
		fasyncTask.cancel(true);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Intent intent = new Intent(getActivity(), BallTeamContentActivity.class);
		Bundle bundle = new Bundle();
		bundle.putInt("position", position);
		intent.putExtras(bundle);
		startActivity(intent);

	}
}
