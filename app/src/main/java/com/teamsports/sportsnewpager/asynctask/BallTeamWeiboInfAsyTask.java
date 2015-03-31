package com.teamsports.sportsnewpager.asynctask;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.Toast;

import com.teamsports.sportsnewpager.adapter.MyBallteamConWeiboAdapter;
import com.teamsports.sportsnewspager.entity.BallTeamWeiboInfo;

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
 * Created by Administrator on 2015/3/30.
 */
public class BallTeamWeiboInfAsyTask extends AsyncTask<String,Void,byte[]> {
	private List<BallTeamWeiboInfo> totalList;
	private MyBallteamConWeiboAdapter adapter;
	private Context context;
	private SwipeRefreshLayout swipeRefreshLayout;

	public BallTeamWeiboInfAsyTask(Context context, List<BallTeamWeiboInfo> totalList, MyBallteamConWeiboAdapter adapter, SwipeRefreshLayout swipeRefreshLayout) {
		this.totalList = totalList;
		this.context = context;
		this.adapter = adapter;
		this.swipeRefreshLayout = swipeRefreshLayout;

	}

	@Override
	protected byte[] doInBackground(String... params) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		BufferedInputStream bis = null;
		try {
			HttpURLConnection conn = (HttpURLConnection) new URL(params[0]).openConnection();
			conn.setRequestMethod("GET");
			conn.setConnectTimeout(5000);
			conn.connect();

			if (conn.getResponseCode() == 200) {
				bis = new BufferedInputStream(conn.getInputStream());

				int len = 0;
				byte[] buffer = new byte[1024 * 8];
				while ((len = bis.read(buffer)) != -1) {
					baos.write(buffer, 0, len);
					baos.flush();
				}
				return baos.toByteArray();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bis != null) {
				try {
					bis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (baos != null) {
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
		if (bytes != null) {
			List<BallTeamWeiboInfo> list = jsonStringToList(new String(bytes));
			totalList.addAll(list);
			adapter.notifyDataSetChanged();
			swipeRefreshLayout.setRefreshing(false);
		} else {
			Toast.makeText(context, "网络连接失败", Toast.LENGTH_SHORT).show();
			swipeRefreshLayout.setRefreshing(false);
		}
	}

	private List<BallTeamWeiboInfo> jsonStringToList(String jsonString) {
		List<BallTeamWeiboInfo> list = new ArrayList<>();
		try {
			JSONObject jsonObject = new JSONObject(jsonString);
			JSONObject jsonObject_result = jsonObject.getJSONObject("result");
			JSONObject jsonObject_data = jsonObject_result.getJSONObject("data");
			JSONArray jsonArray_timeline = jsonObject_data.getJSONArray("timeline");
			for (int i = 0; i < jsonArray_timeline.length(); i++) {
				JSONObject jsonObject_item = jsonArray_timeline.getJSONObject(i);
				BallTeamWeiboInfo wbInfo = new BallTeamWeiboInfo();
				wbInfo.setText(jsonObject_item.getString("text"));
				wbInfo.setThumbnail(jsonObject_item.optString("thumbnail_pic"));
				wbInfo.setComment(jsonObject_item.getString("comments_count"));
				wbInfo.setReposts(jsonObject_item.getString("reposts_count"));

				JSONObject jsonObject_user = jsonObject_item.getJSONObject("user");
				wbInfo.setName(jsonObject_user.getString("name"));
				wbInfo.setvReason(jsonObject_user.getString("verified_reason"));
				wbInfo.setImageUrl(jsonObject_user.getString("profile_image_url"));

				list.add(wbInfo);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return list;
	}
}