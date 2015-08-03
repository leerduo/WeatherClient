package com.dy.ustc.weatherpro.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.dy.ustc.weatherpro.R;
import com.dy.ustc.weatherpro.db.WeatherDB;
import com.dy.ustc.weatherpro.fragment.WeatherFragment;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class MainActivity extends SingleFragmentActivity {

    private static final String TAG = "MainActivity";

    private ListView cityList;

    private DrawerLayout drawerLayout;
    private Set<String> setCity;
    private List<String> listCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        cityList = (ListView) findViewById(R.id.cityList);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);


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

        if (listCity.isEmpty()){
            listCity.add("合肥");
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listCity);
        cityList.setAdapter(adapter);

        adapter.notifyDataSetChanged();

        cityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it = new Intent(MainActivity.this,WeatherPagerActivity.class);
                it.putExtra(WeatherFragment.PAGERCITYNAME,WeatherPagerActivity.class);
                startActivity(it);
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected Fragment createFragment() {
        return new WeatherFragment();
    }


}
