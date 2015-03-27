package com.teamsports.sportsnewpager.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.teamsports.sportsnewspager.sportsnewspager.BallTeamManageActivity;
import com.teamsports.sportsnewspager.sportsnewspager.R;


public class MyBallTeamFragment extends Fragment implements View.OnClickListener {
	private ImageButton ballTeam_img;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_my_ball_team, container, false);
		ballTeam_img = (ImageButton) view .findViewById(R.id.ballTeam_img);

		ballTeam_img.setOnClickListener(this);
		return view;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()){
			case R.id.ballTeam_img:
				Intent intent = new Intent(getActivity(),BallTeamManageActivity.class);
				startActivity(intent);
				break;
		}
	}
}
