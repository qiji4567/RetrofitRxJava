package com.qiji.retrofitrxjava;

import android.content.Intent;
import android.text.TextUtils;

import com.qiji.network.utils.ActivityManagerUtils;
import com.qiji.network.utils.SPUtils;
import com.qiji.retrofitrxjava.act.LoginActivity;
import com.qiji.retrofitrxjava.base.BaseAct;

public class MainActivity extends BaseAct {


    @Override
    protected void iniData() {
        String username = (String) SPUtils.get(this, "username", "");
        if (TextUtils.isEmpty(username)) {
            Intent intent = new Intent();
            intent.setClass(this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            ActivityManagerUtils.getInstance().finishActivity(this);
        }
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_main;
    }
}