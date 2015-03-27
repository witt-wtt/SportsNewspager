package com.teamsports.sportsnewpager.fragment;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.ListFragment;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.teamsports.com.teamsports.sportsnewspager.utils.BitmapHelper;
import com.teamsports.sportsnewspager.sportsnewspager.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/3/24.
 */
public class FragmentMatch extends Fragment implements ViewPager.OnPageChangeListener {
	//定义一个集合来存储fragment
	private List<Fragment> fragmentList=new ArrayList<>();
	//适配器
	private Myadapter myadapter;
	//viewpager
	private ViewPager viewPager_mian_show;
	//tab中显示的内容
	private TextView competition;
	private TextView hot;
	private TextView mine;
	//tab的引导线
	private ImageView id_tab_line_iv;
	//定义fragment
	private Competition_fragment comptetition_fragment;
	private Hot_fragment hot_fragment;
	private Mine_fragment mine_fragment;
	//viewpager当前选择的页数
	private int currentIndex;
	/**
	 * 屏幕的宽度
	 */
	private int screenWidth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_match,container,false);

		viewPager_mian_show = (ViewPager) view.findViewById(R.id.viewPager_mian_shows);
		competition = (TextView) view.findViewById(R.id.competition);
		hot = (TextView) view.findViewById(R.id.hot);
		mine = (TextView) view.findViewById(R.id.mine);
		id_tab_line_iv = (ImageView) view.findViewById(R.id.id_tab_line_iv);


		BitmapHelper.initUtils(getActivity());

		init();
		initTabLineWidth();
		onclick();
		return view;
    }
	//textView的点击事件
	private void onclick(){
		List<TextView> texts=new ArrayList<>();
		texts.add(competition);
		texts.add(hot);
		texts.add(mine);
		for(int i=0;i<3;i++){
			texts.get(i).setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					switch (v.getId()){
						case R.id.competition:
							viewPager_mian_show.setCurrentItem(0);
							break;
						case R.id.hot:
							viewPager_mian_show.setCurrentItem(1);
							break;
						case R.id.mine:
							viewPager_mian_show.setCurrentItem(2);
							break;
					}
				}
			});
		}
	}
	private void init(){
		//处理fragment
		comptetition_fragment=new Competition_fragment();
		hot_fragment=new Hot_fragment();
		mine_fragment=new Mine_fragment();
		//添加到list集合中
		fragmentList.add(comptetition_fragment);
		fragmentList.add(hot_fragment);
		fragmentList.add(mine_fragment);
		//处理adapter
		myadapter=new Myadapter(getChildFragmentManager(),fragmentList);
		viewPager_mian_show.setAdapter(myadapter);
		viewPager_mian_show.setCurrentItem(0);
		//监听viewpager的滑动事件
		viewPager_mian_show.setOnPageChangeListener(this);
	}
	@Override
	public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
		LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams)id_tab_line_iv
				.getLayoutParams();
		Log.i("offset:", positionOffset + 1 + "");
		if (currentIndex == 0 && position == 0)// 0->1
		{
			lp.leftMargin = (int) (positionOffset * (screenWidth * 1.0 / 3) + currentIndex
					* (screenWidth / 3));

		} else if (currentIndex == 1 && position == 0) // 1->0
		{
			lp.leftMargin = (int) (-(1 - positionOffset)
					* (screenWidth * 1.0 / 3) + currentIndex
					* (screenWidth / 3));

		} else if (currentIndex == 1 && position == 1) // 1->2
		{
			lp.leftMargin = (int) (positionOffset * (screenWidth * 1.0 / 3) + currentIndex
					* (screenWidth / 3));
		} else if (currentIndex == 2 && position == 1) // 2->1
		{
			lp.leftMargin = (int) (-(1 - positionOffset)
					* (screenWidth * 1.0 / 3) + currentIndex
					* (screenWidth / 3));
		}
		id_tab_line_iv.setLayoutParams(lp);
	}

	@Override
	public void onPageSelected(int position) {
		resetTextView();
		switch (position) {
			case 0:
				competition.setTextColor(Color.BLUE);
				break;
			case 1:
				hot.setTextColor(Color.BLUE);
				break;
			case 2:
				mine.setTextColor(Color.BLUE);
				break;
		}
		currentIndex = position;
	}


	@Override
	public void onPageScrollStateChanged(int state) {

	}
	/**
	 * 设置滑动条的宽度为屏幕的1/3(根据Tab的个数而定)
	 */
	private void initTabLineWidth() {
		DisplayMetrics dpMetrics = new DisplayMetrics();
		getActivity().getWindow().getWindowManager().getDefaultDisplay()
				.getMetrics(dpMetrics);
		screenWidth = dpMetrics.widthPixels;
		LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) id_tab_line_iv
				.getLayoutParams();
		lp.width = screenWidth / 3;
		id_tab_line_iv.setLayoutParams(lp);
	}

	/**
	 * 重置颜色
	 */
	private void resetTextView() {
		competition.setTextColor(Color.BLACK);
		hot.setTextColor(Color.BLACK);
		mine.setTextColor(Color.BLACK);
	}
	class Myadapter extends FragmentPagerAdapter {
		private List<Fragment> fragmentList=new ArrayList<>();
		public Myadapter(FragmentManager fm,List<Fragment> fragmentList) {
			super(fm);
			this.fragmentList=fragmentList;
		}

		@Override
		public Fragment getItem(int position) {
			return fragmentList.get(position);
		}

		@Override
		public int getCount() {
			return fragmentList.size();
		}
	}
}
