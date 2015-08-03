package com.dy.ustc.weatherpro.ui;

import android.support.v4.app.Fragment;

import com.dy.ustc.weatherpro.fragment.ChooseAreaFragment;

/**
 * Created by Administrator on 2015/5/7.
 */
public class ChooseAreaActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new ChooseAreaFragment();
    }
}
