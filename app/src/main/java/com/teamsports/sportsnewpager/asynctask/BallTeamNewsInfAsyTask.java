package com.teamsports.sportsnewpager.asynctask;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.Toast;

import com.teamsports.sportsnewpager.adapter.MyBallTeamConNewsAdapter;
import com.teamsports.sportsnewspager.entity.BallTeamNewsInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/3/27.
 */
public class BallTeamNewsInfAsyTask extends AsyncTask<String,Void,byte[]> {
	private List<BallTeamNewsInfo> totalList ;
	private MyBallTeamConNewsAdapter adapter;
	private Context context;
	private SwipeRefreshLayout swipeRefreshLayout;
	public BallTeamNewsInfAsyTask(Context context,List<BallTeamNewsInfo> totalList,MyBallTeamConNewsAdapter adapter,SwipeRefreshLayout swipeRefreshLayout){
		this.totalList = totalList;
		this.context = context;
		this.adapter =adapter;
		this.swipeRefreshLayout = swipeRefreshLayout;

	}
	@Override
	protected byte[] doInBackground(String... params) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		BufferedInputStream bis=null;
		try {
			HttpURLConnection conn = (HttpURLConnection) new URL(params[0]).openConnection();
			conn.setRequestMethod("GET");
			conn.setConnectTimeout(5000);
			conn.connect();

			if(conn.getResponseCode()==200){
				bis = new BufferedInputStream(conn.getInputStream());

				int len =0;
				byte[] buffer = new byte[1024*8];
				while ((len = bis.read(buffer))!=-1){
					baos.write(buffer,0,len);
					baos.flush();
				}
				return baos.toByteArray();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(bis!=null){
				try {
					bis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(baos!=null){
				try {
					baos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	@Override
	protected void onPostExecute(byte[] bytes) {
		if(bytes!=null){
			List<BallTeamNewsInfo> list = jsonStringToList(new String(bytes));
			totalList.addAll(list);
			adapter.notifyDataSetChanged();
			swipeRefreshLayout.setRefreshing(false);
		}else{
			Toast.makeText(context, "网络连接失败", Toast.LENGTH_SHORT).show();
			swipeRefreshLayout.setRefreshing(false);
		}
	}

	private List<BallTeamNewsInfo> jsonStringToList(String jsonString) {
		List<BallTeamNewsInfo> list = new ArrayList<>();
		try {
			JSONObject jsonObject = new JSONObject(jsonString);
			JSONObject jsonObject_result= jsonObject.getJSONObject("result");
			JSONObject jsonObject_data = jsonObject_result.getJSONObject("data");
			JSONObject jsonObject_feed = jsonObject_data.getJSONObject("feed");
			JSONArray jsonArray_data = jsonObject_feed.getJSONArray("data");
			for (int i = 0; i < jsonArray_data.length(); i++) {
				JSONObject jsonObject_item = jsonArray_data.getJSONObject(i);
				BallTeamNewsInfo newsInfo = new BallTeamNewsInfo();
				newsInfo.setComment(jsonObject_item.getString("comment_show"));
				newsInfo.setTitle(jsonObject_item.getString("title"));
				JSONObject jsonObject_img = jsonObject_item.getJSONObject("img");
				newsInfo.setImg(jsonObject_img.getString("u"));
				list.add(newsInfo);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return list;
	}
}
