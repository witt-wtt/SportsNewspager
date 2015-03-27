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
import com.teamsports.sportsnewspager.entity.Competition;
import com.teamsports.sportsnewspager.sportsnewspager.R;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Witt on 2015/3/24.
 */
public class ComptertitionAdapter extends BaseAdapter {
    private Context context;
    private List<Competition> data=new ArrayList<>();

    public ComptertitionAdapter(Context context, List<Competition> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.competition_item_layout,parent,false);
            convertView.setTag(new ViewHolder(convertView));
        }
        //获取ViewHolder对象
        final ViewHolder holder=(ViewHolder)convertView.getTag();
        //获取每个item，从数据源中获取
        Competition competition=data.get(position);
        //获取Logo的地址
        String logoUrl=competition.getLogo();
        //给item上的每个控件设置数据
        holder.competition_item_title.setText(competition.getTitle());
        //判断customLogo是否为空
            //下载图片
            BitmapHelper.getUtils().display(holder.competition_item_logo,logoUrl,new BitmapLoadCallBack<ImageView>() {
                @Override
                public void onLoadCompleted(ImageView imageView, String s, Bitmap bitmap, BitmapDisplayConfig bitmapDisplayConfig, BitmapLoadFrom bitmapLoadFrom) {
                      holder.competition_item_logo.setImageBitmap(bitmap);
                }

                @Override
                public void onLoadFailed(ImageView imageView, String s, Drawable drawable) {

                }
            });
        return convertView;
    }
    public void addAll(List<Competition> competitions) {
        data.addAll(competitions);
        notifyDataSetChanged();
    }
    public static class ViewHolder{
        private View viewitem;
        private ImageView competition_item_logo;
        private TextView competition_item_title;
        public ViewHolder(View viewitem){
            this.viewitem=viewitem;
            competition_item_title= ((TextView) viewitem.findViewById(R.id.competition_item_title));
            competition_item_logo=(ImageView)viewitem.findViewById(R.id.competition_item_logo);
        }
    }
}
