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
import com.teamsports.sportsnewspager.entity.BallTeamInfo;
import com.teamsports.sportsnewspager.sportsnewspager.R;

import java.util.List;

/**
 * Created by Administrator on 2015/3/26.
 */
public class MyBallTeamListAdapter extends BaseAdapter {
	private List<BallTeamInfo> totalList;
	private ImageLoader imageLoader;
	private Context context;
	public MyBallTeamListAdapter(Context context,List<BallTeamInfo> totalList,RequestQueue mQueue){
		this.totalList = totalList;
		this.context = context;
		imageLoader = new ImageLoader(mQueue, MyImageCache.getInstance());
	}
	@Override
	public int getCount() {
		return totalList.size();
	}

	@Override
	public BallTeamInfo getItem(int position) {
		return totalList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if(convertView==null){
			convertView = LayoutInflater.from(context).inflate(R.layout.ballteam_item,parent,false);
			convertView.setTag(new ViewHolder(convertView));
		}
		ViewHolder mHolder = (ViewHolder)convertView.getTag();
		mHolder.ballteam_item_title.setText(totalList.get(position).getTitle());
		String ImageUrl = totalList.get(position).getLogo();
		imageLoader.get(ImageUrl, ImageLoader.getImageListener(mHolder.ballteam_item_image,0,0),0,0);
		return convertView;
	}
	class ViewHolder{
		private View itemView;
		private TextView ballteam_item_title;
		private ImageView ballteam_item_image;

		public ViewHolder(View itemView){
			this.itemView = itemView;
			ballteam_item_title = (TextView) itemView.findViewById(R.id.ballTeam_item_title);
			ballteam_item_image = (ImageView) itemView.findViewById(R.id.ballTeam_item_image);
		}
	}
}
