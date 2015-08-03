package com.dy.ustc.weatherpro.network;

/**
 * Created by Administrator on 2015/5/7.
 */
public interface HttpCallbackListener {

    void onSuccess(String response);
    void onError(Exception e);

}
