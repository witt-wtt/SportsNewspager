package com.teamsports.sportsnewspager.sportsnewspager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.LinearLayout;

import com.teamsports.com.teamsports.sportsnewspager.utils.BitmapHelper;
import com.teamsports.sportsnewpager.fragment.FragmentColumn;
import com.teamsports.sportsnewpager.fragment.FragmentMatch;
import com.teamsports.sportsnewpager.fragment.FragmentNews;
import com.teamsports.sportsnewpager.fragment.FragmentTeam;
import com.teamsports.sportsnewspager.column.MoreColumn;


public class MainActivity extends FragmentActivity {
	private FragmentMatch fragmentMatch;
	private FragmentNews fragmentNews;
	private FragmentTeam fragmentTeam;
	private FragmentColumn fragmentColumn;

	private DrawerLayout layout_drawer;
	private LinearLayout layout_main_leftdrawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BitmapHelper.initUtils(this);

		//默认显示
		FragmentMatch fragmentMatch=new FragmentMatch();
		// 开启事务
		FragmentTransaction transaction = getSupportFragmentManager()
				.beginTransaction();
		transaction.replace(R.id.layout_container, fragmentMatch);
		// 让事务开始执行
		transaction.commit();

		layout_drawer = (DrawerLayout) findViewById(R.id.layout_drawer);
		layout_main_leftdrawer = (LinearLayout) findViewById(R.id.layout_main_leftdrawer);

		DrawerLayout.LayoutParams layoutParams = (DrawerLayout.LayoutParams) layout_main_leftdrawer.getLayoutParams();
		layoutParams.width = getResources().getDisplayMetrics().widthPixels * 7 / 8;// 设置抽屉出现时的宽度

		layout_main_leftdrawer.setLayoutParams(layoutParams);

    }

	public void clickButton(View view) {
		switch (view.getId()) {
			/*case R.id.imageView_main_more:
				layout_drawer.openDrawer(layout_main_rightdrawer);
				break;*/
			case R.id.imageView_drawer_back:
				layout_drawer.closeDrawer(layout_main_leftdrawer);
				break;
		}
	}


    public void clickText(View v){
    //注意碎片事务管理器的用法
        switch(v.getId()){
            case R.id.main_match:
				if(fragmentMatch==null){
					fragmentMatch=new FragmentMatch();
				}
				// 开启事务
                FragmentTransaction transaction = getSupportFragmentManager()
                        .beginTransaction();
                transaction.replace(R.id.layout_container, fragmentMatch);
                // 让事务开始执行
                transaction.commit();
                break;

            case R.id.main_news:
				if(fragmentNews==null){
					fragmentNews=new FragmentNews();
				}
                // 开启事务
                FragmentTransaction transactionNews = getSupportFragmentManager()
                        .beginTransaction();
                transactionNews.replace(R.id.layout_container, fragmentNews);
                // 让事务开始执行
                transactionNews.commit();
                break;

            case R.id.main_column:
				if(fragmentColumn==null){
					fragmentColumn=new FragmentColumn();
				}
                // 开启事务
                FragmentTransaction transactionColumn = getSupportFragmentManager()
                        .beginTransaction();
                transactionColumn.replace(R.id.layout_container, fragmentColumn);
                // 让事务开始执行
                transactionColumn.commit();
                break;

            case R.id.main_team:
				if(fragmentTeam==null){
					fragmentTeam=new FragmentTeam();
				}
                // 开启事务
                FragmentTransaction transactionTeam = getSupportFragmentManager()
					.beginTransaction();
				transactionTeam.replace(R.id.layout_container, fragmentTeam);
				// 让事务开始执行
				transactionTeam.commit();

                break;
			case R.id.column_intent:
				Intent intent=new Intent(MainActivity.this, MoreColumn.class);
				startActivity(intent);
				break;
        }

    }
}
