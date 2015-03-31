package com.teamsports.sportsnewpager.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.teamsports.com.teamsports.sportsnewspager.utils.urlUtils;
import com.teamsports.sportsnewpager.adapter.ItemAdapter;
import com.teamsports.sportsnewspager.entity.NewsBean;
import com.teamsports.sportsnewspager.sportsnewspager.R;
import com.teamsports.sportsnewspager.sportsnewspager.WebActivity;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HTao on 2015/3/24.
 */
@SuppressLint("ValidFragment")
public class MainFragment extends ListFragment implements SwipeRefreshLayout.OnRefreshListener{
    private int feed_id;
    private static String Info = "MainFragment";
    private String app_key = "2586208540";
    private ItemAdapter adapter;
    private HttpUtils httpUtils;
    @ViewInject(R.id.swipe)
    private SwipeRefreshLayout swipe;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_layout, container, false);
        feed_id = getArguments().getInt("feed_id");
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ViewUtils.inject(this, view);
        adapter = new ItemAdapter(getActivity(), new ArrayList<NewsBean>());
        httpUtils = new HttpUtils();
        setListAdapter(adapter);
        swipe.setOnRefreshListener(this);
        startRefresh();

    }

    @Override
    public void onRefresh() {
        startRefresh();
    }
    public void startRefresh(){
        httpUtils.send(HttpRequest.HttpMethod.GET,
                urlUtils.getChapter(app_key, feed_id),
                new RequestCallBack<String>() {
                    @Override
                    public void onSuccess(ResponseInfo<String> info) {
                        try {
                            //Log.i(Info, "--->"+info.result);
                            //Log.d("MISTAKE","------>"+info.result);
                            // Toast.makeText(getActivity(),"执行到这儿了"+info.result,Toast.LENGTH_LONG).show();
                            JSONObject object = new JSONObject(info.result);
                            //Toast.makeText(getActivity(),"执行到这儿了1"+object,Toast.LENGTH_LONG).show();
                            JSONObject result = object.getJSONObject("result");
                            JSONObject data = result.getJSONObject("data");
                            JSONObject feed = data.getJSONObject("feed");
                            JSONArray dataArray = feed.getJSONArray("data");
                            Log.i(Info, "---+>"+info.result);
                            if(feed_id ==1009){
                                Log.i(Info, "--->"+info.result);
                                JSONObject object1 = dataArray.getJSONObject(1);

                                JSONArray data_array = object1.getJSONArray("data");
                                List<NewsBean> beans = new ArrayList<NewsBean>();
                                for (int i = 0; i < dataArray.length(); i++) {
                                    JSONObject dataObject = data_array.getJSONObject(i);
                                    //Toast.makeText(getActivity(),"执行到这儿了3",Toast.LENGTH_LONG).show();
                                    NewsBean bean = new NewsBean();
                                    bean.setTitle(dataObject.getString("title"));
                                    bean.setUrl(dataObject.getString("url"));
                                    if (dataObject.optJSONObject("video_info") != null) {
                                        JSONObject video = dataObject.optJSONObject("video_info");
                                        //Toast.makeText(getActivity(),"执行到这儿了4",Toast.LENGTH_LONG).show();
                                        bean.setShow_count(video.getString("count"));
                                        //Toast.makeText(getActivity(),"执行到这儿了5",Toast.LENGTH_LONG).show();
                                        bean.setImage(video.getString("imagelink"));
                                    } else {
                                        bean.setShow_count(dataObject.getString("comment_show"));
                                        JSONObject img = dataObject.getJSONObject("img");
                                        bean.setImage(img.getString("u"));

                                    }
                                    beans.add(bean);

                                }
                                adapter.addAll(beans);
                                Toast.makeText(getActivity(), "", Toast.LENGTH_LONG).show();
                            }else {
                                //Toast.makeText(getActivity(),"执行到这儿了2"+dataArray.length(),Toast.LENGTH_LONG).show();
                                List<NewsBean> beans = new ArrayList<NewsBean>();
                                for (int i = 0; i < dataArray.length(); i++) {
                                    JSONObject dataObject = dataArray.getJSONObject(i);
                                    //Toast.makeText(getActivity(),"执行到这儿了3",Toast.LENGTH_LONG).show();
                                    NewsBean bean = new NewsBean();
                                    bean.setTitle(dataObject.getString("title"));
                                    bean.setUrl(dataObject.getString("url"));
                                    if (dataObject.optJSONObject("video_info") != null) {
                                        JSONObject video = dataObject.optJSONObject("video_info");
                                        //Toast.makeText(getActivity(),"执行到这儿了4",Toast.LENGTH_LONG).show();
                                        bean.setShow_count(video.getString("count"));
                                        //Toast.makeText(getActivity(),"执行到这儿了5",Toast.LENGTH_LONG).show();
                                        bean.setImage(video.getString("imagelink"));
                                    } else {
                                        bean.setShow_count(dataObject.getString("comment_show"));
                                        JSONObject img = dataObject.getJSONObject("img");
                                        bean.setImage(img.getString("u"));

                                    }
                                    beans.add(bean);

                                }
                                adapter.addAll(beans);
//                                Toast.makeText(getActivity(), "", Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        swipe.setRefreshing(false);
                    }

                    @Override
                    public void onFailure(HttpException e, String s) {
                        Toast.makeText(getActivity(),
                                "网络异常", Toast.LENGTH_LONG).show();
                        swipe.setRefreshing(false);
                    }
                });
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Intent intent = new Intent(getActivity(),WebActivity.class);
        NewsBean item = (NewsBean) adapter.getItem(position);
        intent.putExtra("url",item.getUrl());
        Log.i(Info,"----^^"+item.getUrl());
        startActivity(intent);

    }
}
