package com.teamsports.sportsnewpager.adapter;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;

import com.lidroid.xutils.view.annotation.ViewInject;

import com.teamsports.com.teamsports.sportsnewspager.utils.BitmapHelper;
import com.teamsports.sportsnewspager.entity.NewsBean;
import com.teamsports.sportsnewspager.sportsnewspager.R;

import java.util.List;

/**
 * Created by HTao on 2015/3/25.
 */
public class ItemAdapter extends BaseAdapter{
    private Context context;
    private List<NewsBean> data;

    public ItemAdapter(Context context, List<NewsBean> data) {
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
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_news,parent,false);
            convertView.setTag(new ViewHolder(convertView));
        }
        ViewHolder holder = (ViewHolder) convertView.getTag();
        NewsBean bean = data.get(position);
        holder.title.setText(bean.getTitle());
        holder.count.setText(bean.getShow_count());
        BitmapHelper.getUtils().display(holder.image,bean.getImage());
//                new BitmapLoadCallBack<ImageView>() {
//                    /**
//                     * 下载完成
//                     * @param imageView
//                     * @param s
//                     * @param bitmap
//                     * @param bitmapDisplayConfig
//                     * @param bitmapLoadFrom
//                     */
//                    @Override
//                    public void onLoadCompleted(ImageView imageView, String s, Bitmap bitmap, BitmapDisplayConfig bitmapDisplayConfig, BitmapLoadFrom bitmapLoadFrom) {
//                        //为了保持大小一致，进行图片剪切
//                        int height=bitmap.getHeight();
//                        Bitmap bitmap1=Bitmap.createBitmap(height,height, Bitmap.Config.ARGB_8888);
//                        //获得一个画布，绘制在Bitmap上
//                        //Canvas canvas=new Canvas(bitmap1);
//                       //Paint paint=new Paint();
//                        //开启抗锯齿
//                       // paint.setAntiAlias(true);
//                        //设置画笔填充物
//                        paint.setShader(new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
//                        //画一个圆形
//                       // canvas.drawCircle(height/2,height/2,height/2,paint);
//                       imageView.setImageBitmap(bitmap1);
//                        }
//
//                    /**
//                     * 下载失败
//                     * @param imageView
//                     * @param s
//                     * @param drawable
//                     */
//                    @Override
//                    public void onLoadFailed(ImageView imageView, String s, Drawable drawable) {
//                        imageView.setImageDrawable(drawable);
//                    }

        return convertView;
    }

    public void addAll(List<NewsBean> beans){
        data.addAll(beans);
        notifyDataSetChanged();
    }

    public static class ViewHolder{
        private View itemView;
        @ViewInject(R.id.imageView_img)
        private ImageView image;
        @ViewInject(R.id.textView_title)
        private TextView title;
        @ViewInject(R.id.textView_date)
        private TextView count;

        public ViewHolder(View itemView){
            this.itemView = itemView;
            ViewUtils.inject(this,itemView);
        }
    }
}
