package com.teamsports.sportsnewpager.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.teamsports.com.teamsports.sportsnewspager.utils.MyImageCache;
import com.teamsports.sportsnewspager.entity.BallTeamWeiboInfo;
import com.teamsports.sportsnewspager.sportsnewspager.R;

import java.util.List;

/**
 * Created by Administrator on 2015/3/30.
 */
public class MyBallteamConWeiboAdapter extends BaseAdapter {
	private Context context;
	private List<BallTeamWeiboInfo> totalList ;
	private ImageLoader imageLoader;
	public MyBallteamConWeiboAdapter(Context context,List<BallTeamWeiboInfo> totalList,RequestQueue mQueue){
		this.context = context;
		this.totalList = totalList;
		imageLoader = new ImageLoader(mQueue, MyImageCache.getInstance());
	}
	@Override
	public int getCount() {
		return totalList.size();
	}

	@Override
	public BallTeamWeiboInfo getItem(int position) {
		return totalList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if(convertView==null){
			convertView = LayoutInflater.from(context).inflate(R.layout.ballteam_conweibo_item,parent,false);
			convertView.setTag(new ViewHolder(convertView));
		}
		ViewHolder mHolder = (ViewHolder) convertView.getTag();
		mHolder.name.setText(totalList.get(position).getName());
		mHolder.text.setText(totalList.get(position).getText());
		mHolder.vReason.setText(totalList.get(position).getvReason());
		mHolder.comment.setText(totalList.get(position).getComment());
		mHolder.reposts_count.setText(totalList.get(position).getReposts());

		String imgUrl2 = totalList.get(position).getThumbnail();
		if(!TextUtils.isEmpty(imgUrl2)){
			imageLoader.get(imgUrl2,ImageLoader.getImageListener(mHolder.thumbnail_pic,0,0),0,0);
		}
		String imgUrl = totalList.get(position).getImageUrl();
		imageLoader.get(imgUrl, ImageLoader.getImageListener(mHolder.imgUrl, 0, 0),0,0);
		return convertView;
	}
	class ViewHolder{
		private View itemView;
		private TextView text;
		private TextView name;
		private ImageView imgUrl;
		private TextView vReason;
		private TextView comment;
		private ImageView thumbnail_pic;
		private TextView reposts_count;
		public ViewHolder(View itemView){
			this.itemView = itemView;
			reposts_count = (TextView) itemView.findViewById(R.id.ballteam_item_sharecount);
			comment = (TextView) itemView.findViewById(R.id.ballteam_item_commentCount);
			text = (TextView) itemView.findViewById(R.id.ballteam_item_weibo_text);
			name = (TextView) itemView.findViewById(R.id.ballteam_item_weibo_name);
			imgUrl = (ImageView) itemView.findViewById(R.id.ballteam_item_weibo_img);
			thumbnail_pic = (ImageView) itemView.findViewById(R.id.ballteam_item_weibo_thumbnail_pic);
			vReason = (TextView) itemView.findViewById(R.id.ballteam_item_weibo_vreason);
		}

	}
}
