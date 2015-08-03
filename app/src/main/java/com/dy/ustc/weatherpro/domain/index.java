package com.dy.ustc.weatherpro.domain;

/**
 * Created by Administrator on 2015/5/7.
 */
public class index {
    private String des;
    private String tipt;
    private String title;
    private String zs;

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getTipt() {
        return tipt;
    }

    public void setTipt(String tipt) {
        this.tipt = tipt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getZs() {
        return zs;
    }

    public void setZs(String zs) {
        this.zs = zs;
    }

    @Override
    public String toString() {
        return "index{" +
                "des='" + des + '\'' +
                ", tipt='" + tipt + '\'' +
                ", title='" + title + '\'' +
                ", zs='" + zs + '\'' +
                '}';
    }
}
