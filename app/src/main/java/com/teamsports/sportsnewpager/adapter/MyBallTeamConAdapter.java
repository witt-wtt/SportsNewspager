package com.teamsports.sportsnewpager.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.teamsports.com.teamsports.sportsnewspager.utils.MyImageCache;
import com.teamsports.sportsnewspager.entity.BallTeamMatchInfo;
import com.teamsports.sportsnewspager.sportsnewspager.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/3/27.
 */
public class MyBallTeamConAdapter extends BaseAdapter {
	private List<BallTeamMatchInfo> totalList = new ArrayList<>();
	private Context context;
	private ImageLoader imageLoader1;
	private ImageLoader imageLoader2;
	public MyBallTeamConAdapter(Context context,List<BallTeamMatchInfo> totalList,RequestQueue mQueue){
		this.totalList = totalList;
		this.context = context;
		imageLoader1 = new ImageLoader(mQueue, MyImageCache.getInstance());
		imageLoader2 = new ImageLoader(mQueue, MyImageCache.getInstance());
	}
	@Override
	public int getCount() {
		return totalList.size();
	}

	@Override
	public Object getItem(int position) {
		return totalList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if(convertView==null){
			convertView= LayoutInflater.from(context).inflate(R.layout.ballteam_conmatch_item,parent,false);
			convertView.setTag(new ViewHolder(convertView));
		}
		ViewHolder mHolder = (ViewHolder) convertView.getTag();
		mHolder.date.setText(totalList.get(position).getDate());
		mHolder.round.setText(totalList.get(position).getRound());
		mHolder.team1.setText(totalList.get(position).getTeam1());
		mHolder.team2.setText(totalList.get(position).getTeam2());
		mHolder.shortTitle.setText(totalList.get(position).getShortTitle());
		mHolder.status.setText(totalList.get(position).getStatus());

		//设置图片
		String imgflag1Url = totalList.get(position).getFlag1();
		String imgflag2Url = totalList.get(position).getFlag2();

		imageLoader1.get(imgflag1Url, ImageLoader.getImageListener(mHolder.flag1, 0, 0),0,0);
		imageLoader2.get(imgflag2Url, ImageLoader.getImageListener(mHolder.flag2, 0, 0),0,0);
		return convertView;
	}

	class ViewHolder{
		private View itemView;
		private TextView date;//日期
		private TextView shortTitle;
		private TextView round;//第几轮
		private TextView team1;//一队
		private TextView team2;//二队
		private ImageView flag1;//一队图标
		private ImageView flag2;//二队图标
		private TextView status;//进行状态
		public ViewHolder(View itemView){
			this.itemView = itemView;
			date = (TextView) itemView.findViewById(R.id.ballteamcon_item_date);
			shortTitle = (TextView) itemView.findViewById(R.id.ballTeam_item_ShortTitle);
			round = (TextView) itemView.findViewById(R.id.ballteam_item_Round_cn);
			team1 = (TextView) itemView.findViewById(R.id.ballteam_item_team1);
			team2 = (TextView) itemView.findViewById(R.id.ballteam_item_team2);
			status = (TextView) itemView.findViewById(R.id.ballteam_item_status_cn);
			flag1 = (ImageView) itemView.findViewById(R.id.ballteam_item_Flag1_small);
			flag2 = (ImageView) itemView.findViewById(R.id.ballteam_item_Flag2_small);
		}
	}
}
