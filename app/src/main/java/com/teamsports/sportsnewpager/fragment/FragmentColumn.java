package com.teamsports.sportsnewpager.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v4.app.ListFragment;
import android.support.v4.widget.SwipeRefreshLayout;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;
import android.widget.Toast;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.teamsports.com.teamsports.sportsnewspager.utils.AppConstants;
import com.teamsports.sportsnewpager.adapter.ColumnAdapter;
import com.teamsports.sportsnewspager.column.MoreColumn;
import com.teamsports.sportsnewspager.entity.ColumnEntity;
import com.teamsports.sportsnewspager.sportsnewspager.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/3/24.
 */
public class FragmentColumn extends ListFragment implements SwipeRefreshLayout.OnRefreshListener{


    @ViewInject(R.id.swipe)
    //SwipeRefreshLayout,要注意其用法
    private SwipeRefreshLayout swipe;
    private ColumnAdapter adapter;
    private HttpUtils http;
    private RequestParams params;
    private TextView column_textView_submit;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (adapter == null) {

            adapter=new ColumnAdapter(activity,new ArrayList<ColumnEntity>());
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_column,container,false);


        //POST请求携带数据
        params=new RequestParams();
        params.addBodyParameter("app_key",AppConstants.APP_KEY);
        params.addBodyParameter("list_type",AppConstants.LIST_TYPE);
        return view;
    }
    //创建头部
    private void initHeadView(){
        //listview添加头部
        TextView textView=new TextView(getActivity());
        textView.setText("热门推荐");
      //  getListView().addHeaderView(textView);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ViewUtils.inject(this, view);

        http=new HttpUtils();
        //继承ListFragment的便利性，可直接设置listView的适配器
        setListAdapter(adapter);
        loadData();
        swipe.setOnRefreshListener(this);

    }

    @Override
    public void onRefresh() {
        //尚缺头部
        //initHeadView();
        //adapter.clear();
       // loadData();
        Toast.makeText(getActivity(),"FIGHT",Toast.LENGTH_LONG).show();
        swipe.setRefreshing(false);

    }
    public void loadData(){
        initHeadView();
        http.send(HttpRequest.HttpMethod.POST,
                AppConstants.COLUMN_MAIN, params,
                new RequestCallBack<String>() {
                    @Override
                    public void onSuccess(ResponseInfo<String> stringResponseInfo) {

                        try {//注意该监听
                            JSONObject object=new JSONObject(stringResponseInfo.result);
                            JSONObject result=object.getJSONObject("result");
                            JSONObject data=result.getJSONObject("data");
                            JSONArray list=data.getJSONArray("list");
                            List<ColumnEntity>columnEntities=new ArrayList<ColumnEntity>();
                            for (int i = 0; i <list.length() ; i++) {
                                JSONObject list_object=list.getJSONObject(i);
                                ColumnEntity entity=new ColumnEntity();
                                entity.setId(list_object.getString("_id"));
                                entity.setTitle(list_object.getString("title"));
                                entity.setDesc(list_object.getString("desc"));
                                entity.setPic(list_object.getString("pic"));
                                columnEntities.add(entity);
                            }

                            adapter.addAll(columnEntities);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        //swipe.setRefreshing(false);
                    }

                    @Override
                    public void onFailure(HttpException e, String s) {
                        Toast.makeText(getActivity(),
                                s,Toast.LENGTH_LONG).show();
                        //停止刷新
                        //swipe.setRefreshing(false);
                    }
                });
    }


    //不同页面的点击事件最好不要设为一样的,fragment页面的点击事件还是要放到MainActivity中去写
//    public void clickIntent(View v){
//
//        Toast.makeText(getActivity(),"点了",Toast.LENGTH_LONG).show();
//        Intent intent=new Intent(getActivity(), MoreColumn.class);
//        startActivity(intent);
//    }


}
