package com.teamsports.sportsnewpager.fragment;

import android.os.Bundle;

import android.support.v4.app.Fragment;
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
import com.teamsports.sportsnewpager.adapter.HotAdapter;
import com.teamsports.sportsnewspager.entity.Hot;
import com.teamsports.sportsnewspager.sportsnewspager.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Witt on 2015/3/24.
 */
public class Hot_fragment extends Fragment {
    //刷新

    private SwipeRefreshLayout swipe;

    //访问网络的地址
    private String url="http://client.mix.sina.com.cn/api/match_extra/get?app_key=2586208540&_sport_t_=livecast&__version__=3.0.0.12&__os__=android&show_extra=1&f=livecast_id,LeagueType,status,Team1Id,Team2Id,Score1,Score2,MatchId,LiveMode,Discipline,data_from,Title,date,time,Team1,Team2,Flag1,Flag2,NewsUrl,VideoUrl,LiveUrl,LiveStatusExtra,VideoLiveUrl,VideoBeginTime,narrator,LeagueType_cn,Discipline_cn,comment_id,odds_id,VideoEndTime,if_rotate_video,LiveStatusExtra_cn,m3u8,android,period_cn,program,penalty1,penalty1,Round_cn,extrarec_ovx&_sport_a_=hotMatches";
    //适配器
    private HotAdapter adapter;
    //访问网络资源的
    private HttpUtils http;
    //数据源
    List<Hot> hotdata=new ArrayList<>();
    //定义控件Listview
    @ViewInject(R.id.hot_fragment_listview)
    private ListView hot_fragment_listview;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.hot_fragment_layout,container,false);
    }

    @Override
    public void onViewCreated(View view,  Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ViewUtils.inject(this, view);
        adapter = new HotAdapter(getActivity(), hotdata);
        http = new HttpUtils();
        hot_fragment_listview.setAdapter(adapter);
        //初始化控件
        swipe= ((SwipeRefreshLayout) view.findViewById(R.id.swipe));

        //加载数据
        startRefresh();
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                startRefresh();
            }
        });
    }
    private void startRefresh(){
        http.send(HttpRequest.HttpMethod.GET,url,new RequestCallBack<String>() {
           //下载成功
            @Override
            public void onSuccess(ResponseInfo<String> objectResponseInfo) {
                if(objectResponseInfo.result!=null) {
                    Log.i("info", "-----有数据？");
                    JSONObject object = null;
                    try {
                        object = new JSONObject(objectResponseInfo.result);

                    JSONObject jsononject_result = object.getJSONObject("result");
                    JSONObject jsonObject_data = jsononject_result.getJSONObject("data");
                    JSONArray jsonArray_full = jsonObject_data.getJSONArray("full");
                    List<Hot> hotList = new ArrayList<>();
                    for (int i = 0; i < jsonArray_full.length(); i++) {
                        JSONObject itemdata = jsonArray_full.getJSONObject(i);
                        Hot hot = new Hot();
                        hot.setLeagueType_cn(itemdata.getString("LeagueType_cn"));
                        hot.setRound_cn(itemdata.getString("Round_cn"));
                        hot.setFlag1(itemdata.getString("Flag1"));
                        hot.setFlag2(itemdata.getString("Flag2"));
                        hot.setScore1(itemdata.getString("Score1"));
                        hot.setScore2(itemdata.getString("Score2"));
                        hot.setTeam1(itemdata.getString("Team1"));
                        hot.setTeam2(itemdata.getString("Team2"));
                        hotList.add(hot);

                    }
                        adapter.addAll(hotList);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                swipe.setRefreshing(false);
            }
            @Override
            public void onFailure(HttpException e, String s) {
                Toast.makeText(getActivity(),"网络加载失败！",Toast.LENGTH_LONG).show();
                swipe.setRefreshing(false);
            }
        });
    }
}
