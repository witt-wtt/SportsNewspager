package com.teamsports.sportsnewspager.sportsnewspager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


public class WelcomeActivity extends ActionBarActivity {
    //ViewPager图片（为避免图片过大，应用二次采样）载入问题
    private final static String TAG="WelcomeActivity";
    private ViewPager viewPager;
    private int [] layoutIds ={R.layout.welcome_bgo,R.layout.welcome_bgt,
            R.layout.welcome_bgh};

    private List<View> layouts =new ArrayList<View>();
    private View view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        viewPager = ((ViewPager) findViewById(R.id.welcome_viewPager));

        for (int i = 0; i < layoutIds.length ; i++) {

            LayoutInflater inflater=LayoutInflater.from(this);
             view=inflater.inflate(layoutIds[i],null);
            layouts.add(view);
        }



        viewPager.setAdapter(new MyPagerAdapter());

        viewPager.setOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position==layouts.size()-1){
                    goActivity(MainActivity.class);
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    class MyPagerAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return layouts.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object o) {
            return view==o;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(layouts.get(position));
            return layouts.get(position);
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            //super.destroyItem(container, position, object);

            container.removeView(layouts.get(position));
        }
    }
    /**
     * 路转到某页面
     */
    protected void goActivity(Class<?> cls) {
        Log.i(TAG, "==goActivity()");
        Intent intent = new Intent(this, cls);
        startActivity(intent);
        this.finish();
    }


}
