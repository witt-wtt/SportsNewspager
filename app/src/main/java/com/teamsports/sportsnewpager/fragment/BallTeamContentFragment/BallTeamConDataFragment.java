package com.teamsports.sportsnewpager.fragment.BallTeamContentFragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.teamsports.sportsnewspager.sportsnewspager.R;

public class BallTeamConDataFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.fragment_ball_team_con, container, false);
	}
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

	}
}
