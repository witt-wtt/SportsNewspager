package com.teamsports.sportsnewpager.asynctask;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.teamsports.sportsnewpager.adapter.MyBallAdapter;
import com.teamsports.sportsnewpager.adapter.MyBallTeamListAdapter;
import com.teamsports.sportsnewspager.entity.BallInfo;
import com.teamsports.sportsnewspager.entity.BallTeamInfo;

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
 * Created by Administrator on 2015/3/26.
 */
public class BallTeamListAsyncTask extends AsyncTask<String,Void,byte[]> {
	private List<BallTeamInfo> totalList ;
	private MyBallTeamListAdapter adapter;
	private Context context;
	public BallTeamListAsyncTask(Context context,List<BallTeamInfo> totalList,MyBallTeamListAdapter adapter){
		this.totalList = totalList;
		this.context = context;
		this.adapter =adapter;

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
			List<BallTeamInfo> list = jsonStringToList(new String(bytes));
			totalList.addAll(list);
			adapter.notifyDataSetChanged();
		}else{
			Toast.makeText(context,"网络连接失败",Toast.LENGTH_SHORT).show();
		}
	}

	private List<BallTeamInfo> jsonStringToList(String jsonString) {
		List<BallTeamInfo> list = new ArrayList<>();
		JSONObject jsonObject = null;
		try {
			jsonObject = new JSONObject(jsonString);
			JSONObject jsonObject_result = jsonObject.getJSONObject("result");
			JSONObject jsonObject_data = jsonObject_result.getJSONObject("data");
			JSONArray jsonArray_list = jsonObject_data.getJSONArray("list");
			for (int i = 0; i < 11; i++) {
				JSONObject jsonObject_item = jsonArray_list.getJSONObject(i);
				BallTeamInfo btinfo = new BallTeamInfo();
				btinfo.setTitle(jsonObject_item.getString("title"));
				btinfo.setLogo(jsonObject_item.getString("logo"));
				list.add(btinfo);
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}
		return list;
	}
}
