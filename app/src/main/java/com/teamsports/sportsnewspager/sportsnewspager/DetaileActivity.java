package com.teamsports.sportsnewspager.sportsnewspager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.teamsports.sportsnewpager.fragment.Match_Fragment;

import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.activity_detaile)
public class DetaileActivity extends ActionBarActivity {
//定义控件
    private List<TextView> texts=new ArrayList<>();
    @ViewInject(R.id.match)
    private TextView match;
    @ViewInject(R.id.video)
    private TextView voide;
    @ViewInject(R.id.news)
    private TextView news;
    @ViewInject(R.id.data)
    private TextView data;
    @ViewInject(R.id.community)
    private TextView community;
    //viewpager
    private ViewPager viewPager_details_show;
    //适配器
    private DetailesAdapter detailesAdapter;
    //创建list集合存储fragment
    List<Fragment> fragmentlist=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewUtils.inject(this);
    }

    private void init(){
        for (int i = 0; i <5 ; i++) {
            Match_Fragment fragment_detail=new Match_Fragment();
            fragmentlist.add(fragment_detail);
            texts.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (v.getId()){
                        case R.id.match:
                            viewPager_details_show.setCurrentItem(0);
                            break;
                        case R.id.video:
                            viewPager_details_show.setCurrentItem(1);
                            break;
                        case R.id.news:
                            viewPager_details_show.setCurrentItem(2);
                            break;
                        case R.id.data:
                            viewPager_details_show.setCurrentItem(3);
                            break;
                        case R.id.community:
                            viewPager_details_show.setCurrentItem(4);
                            break;
                    }
                }
            });
        }
        viewPager_details_show.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }
    class DetailesAdapter extends FragmentPagerAdapter{
    private List<Fragment> fragments=new ArrayList<>();
        public DetailesAdapter(FragmentManager fm,List<Fragment> fragments) {
            super(fm);
            this.fragments=fragments;
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }
}
