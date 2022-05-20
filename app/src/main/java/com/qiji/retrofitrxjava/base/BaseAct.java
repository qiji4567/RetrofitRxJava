package com.qiji.retrofitrxjava.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.qiji.network.utils.ActivityManagerUtils;

/**
 * 功能描述
 *
 * @author qiji
 * @since 2022-05-20
 */
public abstract class BaseAct extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityManagerUtils.getInstance().addActivity(this);
        layoutId();
        iniData();
    }

    /**
     * 初始化数据
     */
    protected abstract void iniData();

    /**
     * 加载布局
     * @return
     */
    protected abstract int layoutId();
}
