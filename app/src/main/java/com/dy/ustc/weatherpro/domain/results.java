package com.dy.ustc.weatherpro.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/5/7.
 */
public class results {
    private String currentCity;

    private List<index> index = new ArrayList<>();

    private int pm25;

    private List<weather_data> weather_data = new ArrayList<>();

    public String getCurrentCity() {
        return currentCity;
    }

    public void setCurrentCity(String currentCity) {
        this.currentCity = currentCity;
    }

    public List<com.dy.ustc.weatherpro.domain.index> getIndex() {
        return index;
    }

    public void setIndex(List<com.dy.ustc.weatherpro.domain.index> index) {
        this.index = index;
    }

    public int getPm25() {
        return pm25;
    }

    public void setPm25(int pm25) {
        this.pm25 = pm25;
    }

    public List<com.dy.ustc.weatherpro.domain.weather_data> getWeather_data() {
        return weather_data;
    }

    public void setWeather_data(List<com.dy.ustc.weatherpro.domain.weather_data> weather_data) {
        this.weather_data = weather_data;
    }

    @Override
    public String toString() {
        return "results{" +
                "currentCity='" + currentCity + '\'' +
                ", index=" + index +
                ", pm25=" + pm25 +
                ", weather_data=" + weather_data +
                '}';
    }
}