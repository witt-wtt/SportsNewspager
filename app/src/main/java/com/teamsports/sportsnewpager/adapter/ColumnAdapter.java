package com.teamsports.sportsnewpager.adapter;

import android.content.Context;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Shader.TileMode;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.bitmap.BitmapDisplayConfig;
import com.lidroid.xutils.bitmap.callback.BitmapLoadCallBack;
import com.lidroid.xutils.bitmap.callback.BitmapLoadFrom;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.teamsports.com.teamsports.sportsnewspager.utils.BitmapHelper;
import com.teamsports.sportsnewspager.entity.ColumnEntity;
import com.teamsports.sportsnewspager.sportsnewspager.R;

import java.util.List;

/**
 * Created by Administrator on 2015/3/24.
 */
public class ColumnAdapter extends BaseAdapter {
    private Context context;
    private List<ColumnEntity> data;
    private ColumnEntity columnEntity;

    public ColumnAdapter(Context context, List<ColumnEntity> data) {
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
        //注意此处的写法
        if (convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.item_column,parent,false);
            convertView.setTag(new ViewHolder(convertView));
        }
        ViewHolder holder= (ViewHolder) convertView.getTag();
        columnEntity=data.get(position);
        holder.textView_title.setText(columnEntity.getTitle());
        holder.textView_desc.setText(columnEntity.getDesc());
        //还差图片
        BitmapHelper.getUtils().display(holder.imageView_pic,
                columnEntity.getPic(),
                new BitmapLoadCallBack<ImageView>() {
                    /**
                     * 下载完成
                     * @param imageView
                     * @param s
                     * @param bitmap
                     * @param bitmapDisplayConfig
                     * @param bitmapLoadFrom
                     */
                    @Override
                    public void onLoadCompleted(ImageView imageView, String s, Bitmap bitmap, BitmapDisplayConfig bitmapDisplayConfig, BitmapLoadFrom bitmapLoadFrom) {
                        //为了保持大小一致，进行图片剪切
                        int height=bitmap.getHeight();
                        Bitmap bitmap1=Bitmap.createBitmap(height,height, Bitmap.Config.ARGB_8888);
                        //获得一个画布，绘制在Bitmap上
                        Canvas canvas=new Canvas(bitmap1);
                        Paint paint=new Paint();
                        //开启抗锯齿
                        paint.setAntiAlias(true);
                        //设置画笔填充物
                        paint.setShader(new BitmapShader(bitmap,TileMode.CLAMP,TileMode.CLAMP));
                        //画一个圆形
                        canvas.drawCircle(height/2,height/2,height/2,paint);
                        imageView.setImageBitmap(bitmap1);

                    }

                    /**
                     * 下载失败
                     * @param imageView
                     * @param s
                     * @param drawable
                     */
                    @Override
                    public void onLoadFailed(ImageView imageView, String s, Drawable drawable) {
                           imageView.setImageDrawable(drawable);
                    }
                });

        return convertView;
    }
    //添加数据
    public void addAll(List<ColumnEntity>columnEntities){
        data.addAll(columnEntities);
        notifyDataSetChanged();
    }
    public void clear(){
        data.clear();
        notifyDataSetChanged();
    }

    //注意该模型类的写法
    public static class ViewHolder{
        private View itemView;
        @ViewInject(R.id.column_imageView_pic)
        private ImageView imageView_pic;
        @ViewInject(R.id.column_textView_title)
        private TextView textView_title;
        @ViewInject(R.id.column_textView_desc)
        private TextView textView_desc;

        @ViewInject(R.id.column_textView_submit)
        private TextView textView_submit;

        public ViewHolder(View itemView) {
            this.itemView = itemView;
            ViewUtils.inject(this, itemView);
        }
    }
}
