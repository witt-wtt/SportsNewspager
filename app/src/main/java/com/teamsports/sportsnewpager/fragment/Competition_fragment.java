package com.teamsports.sportsnewpager.fragment;

import android.content.Intent;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.teamsports.sportsnewpager.adapter.ComptertitionAdapter;
import com.teamsports.sportsnewspager.entity.Competition;
import com.teamsports.sportsnewspager.sportsnewspager.DetaileActivity;
import com.teamsports.sportsnewspager.sportsnewspager.R;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Witt on 2015/3/26.
 */
public class Competition_fragment extends Fragment  {
    //访问网络的地址
    private String url="http://interface.sina.cn/sports/sports_navs/client_sports_ctrl/client_sports_ctrl.d.json";
    //谷歌官方推荐的下拉加载
    @ViewInject(R.id.swipe)//注解的方式定义控件，不需要再findviewbyid
    private SwipeRefreshLayout swipe;
    @ViewInject(R.id.gridview)
    //定义gridview
    private GridView gridView;
    //适配器
    private ComptertitionAdapter adapter=null;
    //访问网络资源的
    private HttpUtils http;
    //数据源
    List<Competition> data=new ArrayList<>();
    //设置fragment的布局


    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        return inflater.inflate(R.layout.competition_fragment_layout,container,false);
    }
    @Override
    public void onViewCreated(View view,  Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //展示刷新
        ViewUtils.inject(this, view);
        //获取传过来的标识符

        //初始化适配器
        adapter = new ComptertitionAdapter(getActivity(), data);
        //初始化访问网络的工具
        http = new HttpUtils();
        //设置适配器
        gridView.setAdapter(adapter);
        startRefresh();
        gridviewItemClick();
        //监听刷新
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                startRefresh();
            }
        });
    }
    //gridview的监听事件
    private void gridviewItemClick(){
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getActivity(), DetaileActivity.class);
                startActivity(intent);
            }
        });
    }
    private void startRefresh(){
        http.send(HttpRequest.HttpMethod.GET,url,new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> objectResponseInfo) {
                if(objectResponseInfo.result!=null) {
                    Log.i("info", "-----------有数据么");
                    JSONObject object = null;
                    try {
                        object = new JSONObject(objectResponseInfo.result);
                    JSONObject jsonObject_result = object.getJSONObject("result");
                        JSONObject jsonObject_data = jsonObject_result.getJSONObject("data");
                        JSONArray data = jsonObject_data.getJSONArray("list");
                        List<Competition> competitions = new ArrayList<>();
                        for (int i = 0; i < data.length(); i++) {
                            JSONObject object1 = data.getJSONObject(i);
                            //创建实体对象
                            Competition cption = new Competition();
                            cption.setLogo(object1.getString("logo"));
                            Log.d("","----->"+cption.getLogo());
                            cption.setTitle(object1.getString("title"));
                            cption.setId(object1.getString("ID"));
                            Log.d("","----->"+cption.getTitle());
                            competitions.add(cption);
                        }
                        adapter.addAll(competitions);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                swipe.setRefreshing(false);
            }

            @Override
            public void onFailure(HttpException e, String s) {
                Toast.makeText(getActivity(),"网络加载失败！", Toast.LENGTH_LONG).show();
                swipe.setRefreshing(false);
            }
        });
    }

}
