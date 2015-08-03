package com.dy.ustc.weatherpro.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;

import com.dy.ustc.weatherpro.R;
import com.dy.ustc.weatherpro.db.WeatherDB;
import com.dy.ustc.weatherpro.fragment.WeatherFragment;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2015/5/8.
 */
public class WeatherPagerActivity extends ActionBarActivity {

    private static final String TAG ="WeatherPagerActivity";

    private ViewPager mViewPager;

    private Set<String> setCity;
    private List<String> listCity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewPager = new ViewPager(this);
        mViewPager.setId(R.id.viewPager);
        setContentView(mViewPager);


        List<String> stringList = WeatherDB.getInstance(this).loadSelectedCounty();

        setCity = new HashSet<>();
        listCity = new ArrayList<>();
        for (int i = 0; i < stringList.size(); i++) {
            Log.e(TAG, "哈哈哈，集合内容传过来了:" + stringList.get(i));


            setCity.add(stringList.get(i));

        }
        Log.e(TAG, "哈哈哈，set集合内容为我已经过滤掉重复元素:" + setCity);

        Iterator<String> iterator = setCity.iterator();

        while (iterator.hasNext()) {
            listCity.add(iterator.next());
        }


        FragmentManager fragmentManager = getSupportFragmentManager();

        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                String cityName = listCity.get(position);
                return WeatherFragment.newInstance(cityName);
            }

            @Override
            public int getCount() {
                return listCity.size();
            }
        });


    }
}
