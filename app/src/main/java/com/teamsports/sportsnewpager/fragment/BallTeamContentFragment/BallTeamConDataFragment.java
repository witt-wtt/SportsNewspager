package com.teamsports.sportsnewpager.fragment.BallTeamContentFragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.teamsports.sportsnewspager.sportsnewspager.R;

public class BallTeamConDataFragment extends Fragment {
	private int flag;
	private View view;
	public BallTeamConDataFragment(int flag){
		this.flag = flag;
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		if(flag==0){
			view = inflater.inflate(R.layout.fragment_ball_team_confootdata, container, false);
		}else{
			view = inflater.inflate(R.layout.fragment_ball_team_conbkdata, container, false);
		}

		return view;
	}
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

	}
}
