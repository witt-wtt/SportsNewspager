package com.teamsports.sportsnewspager.column;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.teamsports.com.teamsports.sportsnewspager.utils.AppConstants;
import com.teamsports.sportsnewpager.adapter.ColumnDetailAdapter;
import com.teamsports.sportsnewspager.entity.ColumnDetailEntity;
import com.teamsports.sportsnewspager.sportsnewspager.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ColumnDetail extends ActionBarActivity {

    private String jsonId="";
    @ViewInject(R.id.columnDetail_listView)
    private ListView listView;
    private ColumnDetailAdapter adapter;
    private HttpUtils http;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_column_detail);
        ViewUtils.inject(this);
        //冠军时刻的id=cczmvun4647844
        jsonId=getIntent().getExtras().getString("jsonId");
        //适配器
        adapter=new ColumnDetailAdapter(this,new ArrayList<ColumnDetailEntity>());
        http=new HttpUtils();
        listView.setAdapter(adapter);

        loadData();
    }
    private void loadData(){

        if(("cczmvun4647844").equals(jsonId)){

            http.send(HttpRequest.HttpMethod.GET,
                    AppConstants.GUANJUN,
                    new RequestCallBack<String>() {
                        @Override
                        public void onSuccess(ResponseInfo<String> stringResponseInfo) {
                            try {
                                JSONObject object=new JSONObject(stringResponseInfo.result);
                                List<ColumnDetailEntity>entitiesOne=new ArrayList<ColumnDetailEntity>();
                                JSONObject result=object.getJSONObject("result");
                                Log.d("FIND","------>"+result);
                                JSONArray data=result.getJSONArray("data");

                                for (int i = 0; i <data.length() ; i++) {
                                    JSONObject dataObject=data.getJSONObject(i);
                                    ColumnDetailEntity entity=new ColumnDetailEntity();

                                    entity.setTitle(dataObject.getString("title"));
                                    entity.setComments(dataObject.getString("hot"));
                                    entitiesOne.add(entity);
                                }
                                adapter.addAll(entitiesOne);



                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onFailure(HttpException e, String s) {
                            Toast.makeText(ColumnDetail.this,
                                    s, Toast.LENGTH_LONG).show();
                        }
                    });


        }else {
            http.send(HttpRequest.HttpMethod.GET,
                    AppConstants.MODEADDRESSHEAD+jsonId+AppConstants.MODEADDRESSEND,
                    new RequestCallBack<String>() {
                        @Override
                        public void onSuccess(ResponseInfo<String> stringResponseInfo) {
                            try {
                                JSONObject object=new JSONObject(stringResponseInfo.result);

                                List<ColumnDetailEntity>entities=new ArrayList<ColumnDetailEntity>();
                                JSONObject result=object.getJSONObject("result");
                                JSONObject data=result.getJSONObject("data");
                                JSONObject feed=data.getJSONObject("feed");
                                JSONArray dataArray=feed.getJSONArray("data");
                                for (int i = 0; i <dataArray.length() ; i++) {
                                    ColumnDetailEntity entity=new ColumnDetailEntity();
                                    JSONObject dataObject=dataArray.getJSONObject(i);
                                    entity.setUrl(dataObject.getString("wapurl"));
                                    entity.setTitle(dataObject.getString("title"));
                                    entity.setComments(dataObject.getString("comment_show"));
                                    entities.add(entity);
                                }
                                adapter.addAll(entities);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onFailure(HttpException e, String s) {
                            Toast.makeText(ColumnDetail.this,
                                    s, Toast.LENGTH_LONG).show();
                        }
                    });

        }
    }

}
