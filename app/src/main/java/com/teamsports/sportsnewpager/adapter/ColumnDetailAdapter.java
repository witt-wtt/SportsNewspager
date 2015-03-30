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
import com.teamsports.sportsnewspager.entity.ColumnDetailEntity;
import com.teamsports.sportsnewspager.sportsnewspager.R;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Administrator on 2015/3/27.
 */
public class ColumnDetailAdapter extends BaseAdapter {

    private Context context;
    private List<ColumnDetailEntity>data;

    public ColumnDetailAdapter(Context context, List<ColumnDetailEntity> data) {
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
        if (convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.item_column_detail_,parent,false);
            convertView.setTag(new ViewHolder(convertView));
        }
        ViewHolder holder= (ViewHolder) convertView.getTag();

        ColumnDetailEntity entity=data.get(position);

        holder.title.setText(entity.getTitle());
        holder.comments.setText(entity.getComments());
        //图片默认
        holder.imageView.setImageResource(R.drawable.ic_item_news_default);


        return convertView;
    }

    //添加数据
    public void addAll(List<ColumnDetailEntity>columnDetailEntities){
        data.addAll(columnDetailEntities);
        notifyDataSetChanged();
    }
    //数据清空
    public void clear(){
        data.clear();
        notifyDataSetChanged();
    }


    public static class ViewHolder{
        private View itemView;

        @ViewInject(R.id.columnDetail_imageView)
        private ImageView imageView;
        @ViewInject(R.id.columnDetail_title)
        private TextView title;
        @ViewInject(R.id.columnDetail_comments)
        private TextView comments;


        public ViewHolder(View itemView) {
            this.itemView = itemView;
            ViewUtils.inject(this,itemView);
        }
    }
}
