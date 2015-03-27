package com.teamsports.sportsnewpager.asynctask;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.teamsports.sportsnewpager.adapter.MyBallAdapter;
import com.teamsports.sportsnewspager.entity.BallInfo;

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
 * Created by Administrator on 2015/3/25.
 */
public class MyAsyncTask extends AsyncTask<String,Void,byte[]> {
	private List<BallInfo> totallist ;
	private MyBallAdapter adapter;
	private Context context;
	public MyAsyncTask(Context context,List<BallInfo> totallist,MyBallAdapter adapter){
		this.totallist = totallist;
		this.context = context;
		this.adapter =adapter;

	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
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
			List<BallInfo> list = jsonStringToList(new String(bytes));
			totallist.addAll(list);
			adapter.notifyDataSetChanged();
		}else{
			Toast.makeText(context,"网络连接失败",Toast.LENGTH_SHORT).show();
		}
	}

	private List<BallInfo> jsonStringToList(String jsonString) {
		List<BallInfo> blist = new ArrayList<>();
		try {
			JSONObject jsonObject = new JSONObject(jsonString);
			JSONObject jsonObject_result = jsonObject.getJSONObject("result");
			JSONArray jsonArray_data = jsonObject_result.getJSONArray("data");
			for (int i = 0; i < jsonArray_data.length(); i++) {
				JSONObject jsonObject_item = jsonArray_data.getJSONObject(i);
				BallInfo bkInfo = new BallInfo();
				bkInfo.setCount(jsonObject_item.getString("count"));
				bkInfo.setRank(jsonObject_item.getString("rank"));
				bkInfo.setTname_cn(jsonObject_item.getString("tname_cn"));
				bkInfo.setLogo(jsonObject_item.optString("logo"));
				blist.add(bkInfo);
			}


		} catch (JSONException e) {
			e.printStackTrace();
		}
		return blist;
	}
}
