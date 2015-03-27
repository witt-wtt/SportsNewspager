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
import com.teamsports.sportsnewspager.entity.BallInfo;
import com.teamsports.sportsnewspager.sportsnewspager.R;

import java.util.List;

/**
 * Created by Administrator on 2015/3/25.
 */
public class MyBallAdapter extends BaseAdapter {
	private Context context;
	private List<BallInfo> totallist;
	private ImageLoader imageCache;
	public MyBallAdapter(Context context, List<BallInfo> totallist, RequestQueue mQueue) {
		this.context = context;
		this.totallist = totallist;
		imageCache = new ImageLoader(mQueue,MyImageCache.getInstance());
	}

	@Override
	public int getCount() {
		return totallist.size();

	}

	@Override
	public BallInfo getItem(int position) {
		return totallist.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if(convertView==null){
			convertView = LayoutInflater.from(context).inflate(R.layout.ball_item,parent,false);
			convertView.setTag(new ViewHolder(convertView));
		}
		ViewHolder mHolder = (ViewHolder) convertView.getTag();
		String imgUrl = totallist.get(position).getLogo();
		imageCache.get(imgUrl, ImageLoader.getImageListener(mHolder.bkball_logo,0,0),0,0);

		mHolder.bkball_count.setText(totallist.get(position).getCount());
		mHolder.bkball_rank.setText(totallist.get(position).getRank());
		mHolder.bkball_tname_cn.setText(totallist.get(position).getTname_cn());
		return convertView;
	}

	class ViewHolder{
		private TextView bkball_count;
		private TextView bkball_tname_cn;
		private TextView bkball_rank;
		private ImageView bkball_logo;
		public ViewHolder(View itemView){
			bkball_count = (TextView) itemView.findViewById(R.id.bkball_item_count);
			bkball_tname_cn = (TextView) itemView.findViewById(R.id.bkball_item_tname_cn);
			bkball_rank = (TextView) itemView.findViewById(R.id.bkball_item_rank);
			bkball_logo = (ImageView) itemView.findViewById(R.id.bkball_item_logo);
		}
	}
	public void clear(){
		totallist.clear();
		notifyDataSetChanged();
	}
}
