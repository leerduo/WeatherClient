package com.dy.ustc.weatherpro.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/5/7.
 */
public class WeatherInfo {

    private String date;

    private int error;

    private List<results> results = new ArrayList<>();

    private String status;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public List<results> getResults() {
        return results;
    }

    public void setResults(List<results> results) {
        this.results = results;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "WeatherInfo{" +
                "date='" + date + '\'' +
                ", error=" + error +
                ", results=" + results +
                ", status='" + status + '\'' +
                '}';
    }
}
