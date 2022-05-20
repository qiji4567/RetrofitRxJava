package com.qiji.retrofitrxjava;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import androidx.annotation.Nullable;

import com.qiji.network.utils.ActivityManagerUtils;
import com.qiji.network.utils.SPUtils;
import com.qiji.retrofitrxjava.base.BaseAct;

public class MainActivity extends BaseAct {


    @Override
    protected int layoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void iniData(@Nullable Bundle savedInstanceState) {
        isLogIn();

    }

    /**
     * 是否登录过
     */
    private void isLogIn() {
        String username = (String) SPUtils.get(this, "username", "");
        if (TextUtils.isEmpty(username)) {
            Intent intent = new Intent();
            intent.setClass(this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            ActivityManagerUtils.getInstance().finishActivity(this);
        }
    }


}