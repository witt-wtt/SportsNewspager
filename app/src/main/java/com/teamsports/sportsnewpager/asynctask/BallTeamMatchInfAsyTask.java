package com.teamsports.sportsnewpager.asynctask;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.Toast;

import com.teamsports.sportsnewpager.adapter.MyBallTeamConAdapter;
import com.teamsports.sportsnewspager.entity.BallTeamMatchInfo;

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
public class BallTeamMatchInfAsyTask extends AsyncTask<String,Void,byte[]> {
	private List<BallTeamMatchInfo> totalList ;
	private MyBallTeamConAdapter adapter;
	private Context context;
	private SwipeRefreshLayout swipeRefreshLayout;
	public BallTeamMatchInfAsyTask(Context context,List<BallTeamMatchInfo> totalList,MyBallTeamConAdapter adapter,SwipeRefreshLayout swipeRefreshLayout){
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
			List<BallTeamMatchInfo> list = jsonStringToList(new String(bytes));
			totalList.addAll(list);
			adapter.notifyDataSetChanged();
			swipeRefreshLayout.setRefreshing(false);
		}else{
			Toast.makeText(context, "网络连接失败", Toast.LENGTH_SHORT).show();
			swipeRefreshLayout.setRefreshing(false);
		}
	}

	private List<BallTeamMatchInfo> jsonStringToList(String jsonString) {
		List<BallTeamMatchInfo> list = new ArrayList<>();
		try {
			JSONObject jsonObject = new JSONObject(jsonString);
			JSONObject jsonObject_result= jsonObject.getJSONObject("result");
			JSONObject jsonObject_data = jsonObject_result.getJSONObject("data");
			JSONArray jsonArray_full = jsonObject_data.getJSONArray("full");
			for (int i = 0; i < jsonArray_full.length(); i++) {
				JSONObject jsonObject_item = jsonArray_full.getJSONObject(i);
				BallTeamMatchInfo matchInfo = new BallTeamMatchInfo();
				matchInfo.setDate(jsonObject_item.getString("date"));
				matchInfo.setShortTitle(jsonObject_item.getString("ShortTitle"));
				matchInfo.setStatus(jsonObject_item.getString("status_cn"));
				matchInfo.setTeam1(jsonObject_item.getString("Team1"));
				matchInfo.setTeam2(jsonObject_item.getString("Team2"));
				matchInfo.setFlag1(jsonObject_item.getString("Flag1"));
				matchInfo.setFlag2(jsonObject_item.getString("Flag2"));
				matchInfo.setRound(jsonObject_item.getString("Round_cn"));
				list.add(matchInfo);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return list;
	}
}
