package com.teamsports.sportsnewpager.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.bitmap.BitmapDisplayConfig;
import com.lidroid.xutils.bitmap.callback.BitmapLoadCallBack;
import com.lidroid.xutils.bitmap.callback.BitmapLoadFrom;
import com.teamsports.com.teamsports.sportsnewspager.utils.BitmapHelper;
import com.teamsports.sportsnewspager.entity.Hot;
import com.teamsports.sportsnewspager.sportsnewspager.R;

import java.util.List;

/**
 * Created by Witt on 2015/3/24.
 */
public class HotAdapter extends BaseAdapter {
    private List<Hot>hotList;
    private Context context;

    public HotAdapter(Context context,List<Hot> hotList){
        this.context=context;
        this.hotList=hotList;
    }
    @Override
    public int getCount() {

        return hotList.size();
    }

    @Override
    public Object getItem(int position) {
        return hotList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.hot_item_layout,parent,false);
            convertView.setTag(new ViewHolder(convertView));
        }
        final ViewHolder holder=(ViewHolder)convertView.getTag();

        //获取图片的地址
        String flag1Url= hotList.get(position).getFlag1();
        String flag2Url=hotList.get(position).getFlag2();
        //LeagueType_cn
        String title=hotList.get(position).getLeagueType_cn()+" "+hotList.get(position).getRound_cn();
        holder.hot_item_title.setText(title);
        holder.score1_text.setText(hotList.get(position).getScore1());
        holder.score2_text.setText(hotList.get(position).getScore2());
        holder.team1_text.setText(hotList.get(position).getTeam1());
        holder.team2_text.setText(hotList.get(position).getTeam2());
        BitmapHelper.getUtils().display(holder.flag1_image,flag1Url,new BitmapLoadCallBack<ImageView>() {
            @Override
            public void onLoadCompleted(ImageView imageView, String s, Bitmap bitmap, BitmapDisplayConfig bitmapDisplayConfig, BitmapLoadFrom bitmapLoadFrom) {
                holder.flag1_image.setImageBitmap(bitmap);
            }

            @Override
            public void onLoadFailed(ImageView imageView, String s, Drawable drawable) {

            }
        });
        BitmapHelper.getUtils().display(holder.flag2_image, flag2Url, new BitmapLoadCallBack<ImageView>() {
			@Override
			public void onLoadCompleted(ImageView imageView, String s, Bitmap bitmap, BitmapDisplayConfig bitmapDisplayConfig, BitmapLoadFrom bitmapLoadFrom) {
				holder.flag2_image.setImageBitmap(bitmap);
			}

			@Override
			public void onLoadFailed(ImageView imageView, String s, Drawable drawable) {

			}
		});

        return convertView;
    }
    public void addAll(List<Hot> hotlist) {
        hotList.addAll(hotlist);
        notifyDataSetChanged();
    }
    public static class ViewHolder{
        private View viewitem;
        private TextView hot_item_title;
        private ImageView flag1_image;
        private ImageView flag2_image;
        private TextView score1_text;
        private TextView score2_text;
        private TextView team1_text;
        private TextView team2_text;
        public ViewHolder(View viewitem){
            this.viewitem=viewitem;
            hot_item_title= ((TextView) viewitem.findViewById(R.id.hot_item_title));
            flag1_image=(ImageView)viewitem.findViewById(R.id.Flag1_image);
            flag2_image=(ImageView)viewitem.findViewById(R.id.Flag2_image);
            score1_text=(TextView)viewitem.findViewById(R.id.Score1_text);
            score2_text=(TextView)viewitem.findViewById(R.id.Score2_text);
            team1_text=(TextView)viewitem.findViewById(R.id.Team1_text);
            team2_text=(TextView)viewitem.findViewById(R.id.Team2_text);
        }
    }
}
