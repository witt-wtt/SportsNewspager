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
import com.teamsports.sportsnewspager.entity.BallTeamNewsInfo;
import com.teamsports.sportsnewspager.sportsnewspager.R;

import java.util.List;

/**
 * Created by Administrator on 2015/3/27.
 */
public class MyBallTeamConNewsAdapter extends BaseAdapter {
	private Context context;
	private List<BallTeamNewsInfo> totalList ;
	private ImageLoader imageLoader;
	public MyBallTeamConNewsAdapter(Context context,List<BallTeamNewsInfo> totalList,RequestQueue mQueue){
		this.context = context;
		this.totalList = totalList;
		imageLoader = new ImageLoader(mQueue, MyImageCache.getInstance());
	}
	@Override
	public int getCount() {
		return totalList.size();
	}

	@Override
	public BallTeamNewsInfo getItem(int position) {
		return totalList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if(convertView==null){
			convertView = LayoutInflater.from(context).inflate(R.layout.ballteam_connews_item,parent,false);
			convertView.setTag(new ViewHolder(convertView));
		}
		ViewHolder mHolder = (ViewHolder) convertView.getTag();
		mHolder.title.setText(totalList.get(position).getTitle());
		mHolder.comment.setText(totalList.get(position).getComment());

		String imgUrl = totalList.get(position).getImg();
		imageLoader.get(imgUrl, ImageLoader.getImageListener(mHolder.img, 0, 0),150,100);
		return convertView;
	}
	class ViewHolder{
		private View itemView;
		private TextView title;
		private TextView comment;
		private ImageView img;
		public ViewHolder(View itemView){
			this.itemView = itemView;
			title = (TextView) itemView.findViewById(R.id.ballteam_item_news_title);
			comment = (TextView) itemView.findViewById(R.id.ballteam_item_news_comment_show);
			img = (ImageView) itemView.findViewById(R.id.ballteam_item_news_img);
		}

	}
}
