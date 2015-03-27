package com.teamsports.sportsnewspager.column;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.teamsports.com.teamsports.sportsnewspager.utils.AppConstants;
import com.teamsports.sportsnewpager.adapter.ColumnAdapter;
import com.teamsports.sportsnewspager.entity.ColumnEntity;
import com.teamsports.sportsnewspager.sportsnewspager.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


@ContentView(R.layout.activity_more_column)//绑定对应的布局
public class MoreColumn extends ActionBarActivity {

    @ViewInject(R.id.column_more_listView)
    private ListView listView;
    private ColumnAdapter adapter;
    private HttpUtils http;
    private RequestParams params;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewUtils.inject(this);

        params=new RequestParams();
        params.addBodyParameter("app_key", AppConstants.APP_KEY);


        adapter=new ColumnAdapter(this,new ArrayList<ColumnEntity>());
        listView.setAdapter(adapter);
        http=new HttpUtils();
        loadData();

    }
    private void loadData(){
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
                            List<ColumnEntity> columnEntities=new ArrayList<ColumnEntity>();
                            for (int i = 0; i <list.length() ; i++) {
                                JSONObject list_object=list.getJSONObject(i);
                                ColumnEntity entity=new ColumnEntity();
                                entity.setTitle(list_object.getString("title"));
                                entity.setDesc(list_object.getString("desc"));
                                entity.setPic(list_object.getString("pic"));
                                columnEntities.add(entity);
                            }

                            adapter.addAll(columnEntities);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onFailure(HttpException e, String s) {
                        Toast.makeText(MoreColumn.this,
                                s, Toast.LENGTH_LONG).show();

                    }
                });
    }
    public void clickText(View v){

        switch(v.getId()){
            case R.id.moreColumn_return:
                finish();
                break;
        }

    }

}
