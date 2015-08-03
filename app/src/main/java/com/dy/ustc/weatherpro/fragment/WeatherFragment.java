package com.dy.ustc.weatherpro.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.dy.ustc.weatherpro.R;
import com.dy.ustc.weatherpro.domain.WeatherInfo;
import com.dy.ustc.weatherpro.domain.index;
import com.dy.ustc.weatherpro.domain.results;
import com.dy.ustc.weatherpro.domain.weather_data;
import com.dy.ustc.weatherpro.network.HttpCallbackListener;
import com.dy.ustc.weatherpro.network.HttpUtil;
import com.dy.ustc.weatherpro.ui.ChooseAreaActivity;

import java.net.URLEncoder;
import java.util.List;


public class WeatherFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private static final String TAG = "WeatherFragment";

    public static final String PAGERCITYNAME = "pagerCityName";

    private SwipeRefreshLayout mSwipeRefreshWidget;

    TextView cityName;


    //当天
    TextView date;
    TextView pm25;
    ImageView dayUrl;
    TextView temp;
    TextView weather;
    TextView wind;
    TextView cloth;
    TextView sport;
    //第二天
    TextView dateSecond;
    ImageView dayUrlSecond;
    TextView tempSecond;
    TextView weatherSecond;
    TextView windSecond;

    //第三天
    TextView dateThird;
    ImageView dayUrlThird;
    TextView tempThird;
    TextView weatherThird;
    TextView windThird;
    //第四天
    TextView dateForth;
    ImageView dayUrlForth;
    TextView tempForth;
    TextView weatherForth;
    TextView windForth;


    private Handler mHandler = new Handler();
    private final Runnable mRefreshDone = new Runnable() {

        @Override
        public void run() {
            mSwipeRefreshWidget.setRefreshing(false);
        }

    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        getActivity().setTitle("彩虹天气");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_weather, container, false);

        cityName = (TextView) view.findViewById(R.id.cityName);

        //当天的===============================================
        date = (TextView) view.findViewById(R.id.date);
        pm25 = (TextView) view.findViewById(R.id.pm25);
        dayUrl = (ImageView) view.findViewById(R.id.dayUrl);
        temp = (TextView) view.findViewById(R.id.temp);
        weather = (TextView) view.findViewById(R.id.weather);
        wind = (TextView) view.findViewById(R.id.wind);
        cloth = (TextView) view.findViewById(R.id.cloth);
        sport = (TextView) view.findViewById(R.id.sport);
        //====================================================

        //第二天的===========================================
        dateSecond = (TextView) view.findViewById(R.id.dateSecond);
        dayUrlSecond = (ImageView) view.findViewById(R.id.dayUrlSecond);
        tempSecond = (TextView) view.findViewById(R.id.tempSecond);
        weatherSecond = (TextView) view.findViewById(R.id.weatherSecond);
        windSecond = (TextView) view.findViewById(R.id.windSecond);
        //===================================================


        //第三天的========================================

        dateThird = (TextView) view.findViewById(R.id.dateThird);
        dayUrlThird = (ImageView) view.findViewById(R.id.dayUrlThird);
        tempThird = (TextView) view.findViewById(R.id.tempThird);
        weatherThird = (TextView) view.findViewById(R.id.weatherThird);
        windThird = (TextView) view.findViewById(R.id.windThird);
        //================================================

        //第四天的======================================

        dateForth = (TextView) view.findViewById(R.id.dateForth);
        dayUrlForth = (ImageView) view.findViewById(R.id.dayUrlForth);
        tempForth = (TextView) view.findViewById(R.id.tempForth);
        weatherForth = (TextView) view.findViewById(R.id.weatherForth);
        windForth = (TextView) view.findViewById(R.id.windForth);
        //================================================

        mSwipeRefreshWidget = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_widget);
        mSwipeRefreshWidget.setColorSchemeResources(R.color.color1, R.color.color2, R.color.color3,
                R.color.color4);
        //mSwipeRefreshWidget.setProgressViewOffset(false, 0, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources().getDisplayMetrics()));
        mSwipeRefreshWidget.setOnRefreshListener(this);

        String countyName = getActivity().getIntent().getStringExtra("countyName");
        String countyCode = getActivity().getIntent().getStringExtra("countyCode");

        try {
            queryWeatherInfo(countyName);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return view;
    }


    private void queryWeatherInfo(String name) throws Exception {

        String address = null;

        if (name == null) {
            address = "http://api.map.baidu.com/telematics/v3/weather?location=%E5%90%88%E8%82%A5&output=json&ak=ts49h7nDhPEAL0qgsYX5N1qt";

        } else {

            String encode = URLEncoder.encode(name, "UTF-8");

            address = "http://api.map.baidu.com/telematics/v3/weather?location=" + encode + "&output=json&ak=ts49h7nDhPEAL0qgsYX5N1qt";
        }
        Log.e(TAG, "address:" + address);
        queryFromServer(address);
    }

    private void queryFromServer(String address) {
        HttpUtil.sendHttpRequest(address, new HttpCallbackListener() {
            @Override
            public void onSuccess(final String response) {

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {


                        //加入WeatherInfo是以内部类的形式  那么会报出这样的错误：create instance error
                        WeatherInfo weatherInfo = JSON.parseObject(response, WeatherInfo.class);
                        List<results> listResults = weatherInfo.getResults();
                        Log.e(TAG, listResults.toString());

                        results resultsWeather = listResults.get(0);

                        String currentCity = resultsWeather.getCurrentCity();

                        cityName.setText(currentCity);

                        int pm = resultsWeather.getPm25();

                        pm25.setText("" + pm);

                        List<index> listIndex = resultsWeather.getIndex();

                        //我们只需要拿出当天的数据
                        String clothdesc = listIndex.get(0).getDes();
                        String sportdesc = listIndex.get(4).getDes();
                        cloth.setText(clothdesc);
                        sport.setText(sportdesc);

                        List<weather_data> listWeather_data = resultsWeather.getWeather_data();

                        String date1 = listWeather_data.get(0).getDate();
                        String url1 = listWeather_data.get(0).getDayPictureUrl();
                        String temp1 = listWeather_data.get(0).getTemperature();
                        String weather1 = listWeather_data.get(0).getWeather();
                        String wind1 = listWeather_data.get(0).getWind();

                        date.setText(date1);
                        Glide.with(WeatherFragment.this).load(url1).into(dayUrl);
                        temp.setText(temp1);
                        weather.setText(weather1);
                        wind.setText(wind1);


                        String date2 = listWeather_data.get(1).getDate();
                        String url2 = listWeather_data.get(1).getDayPictureUrl();
                        String temp2 = listWeather_data.get(1).getTemperature();
                        String weather2 = listWeather_data.get(1).getWeather();
                        String wind2 = listWeather_data.get(1).getWind();

                        dateSecond.setText(date2);
                        Glide.with(WeatherFragment.this).load(url2).into(dayUrlSecond);
                        tempSecond.setText(temp2);
                        weatherSecond.setText(weather2);
                        windSecond.setText(wind2);


                        String date3 = listWeather_data.get(2).getDate();
                        String url3 = listWeather_data.get(2).getDayPictureUrl();
                        String temp3 = listWeather_data.get(2).getTemperature();
                        String weather3 = listWeather_data.get(2).getWeather();
                        String wind3 = listWeather_data.get(2).getWind();

                        dateThird.setText(date3);
                        Glide.with(WeatherFragment.this).load(url3).into(dayUrlThird);
                        tempThird.setText(temp3);
                        weatherThird.setText(weather3);
                        windThird.setText(wind3);

                        String date4 = listWeather_data.get(3).getDate();
                        String url4 = listWeather_data.get(3).getDayPictureUrl();
                        String temp4 = listWeather_data.get(3).getTemperature();
                        String weather4 = listWeather_data.get(3).getWeather();
                        String wind4 = listWeather_data.get(3).getWind();

                        dateForth.setText(date4);
                        Glide.with(WeatherFragment.this).load(url4).into(dayUrlForth);
                        tempForth.setText(temp4);
                        weatherForth.setText(weather4);
                        windForth.setText(wind4);


                    }
                });

            }

            @Override
            public void onError(Exception e) {
                Log.e(TAG, "发生错误:" + e.getMessage());
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_main, menu);
    }

    /**
     * Click handler for the menu item to force a refresh.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final int id = item.getItemId();
        switch (id) {
            case R.id.refresh:
                mSwipeRefreshWidget.setRefreshing(true);
                refresh();
                return true;

            case R.id.add:

                Intent i = new Intent(getActivity(), ChooseAreaActivity.class);

                startActivity(i);
                return true;
        }
        return false;
    }

    @Override
    public void onRefresh() {
        refresh();
    }

    private void refresh() {
        String countyName = getActivity().getIntent().getStringExtra("countyName");
        try {
            queryWeatherInfo(countyName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mHandler.removeCallbacks(mRefreshDone);
        mHandler.postDelayed(mRefreshDone, 3000);
    }

    public static WeatherFragment newInstance(String name) {
        Bundle args = new Bundle();
        args.putSerializable("", name);
        WeatherFragment fragment = new WeatherFragment();
        fragment.setArguments(args);
        return fragment;
    }


}
