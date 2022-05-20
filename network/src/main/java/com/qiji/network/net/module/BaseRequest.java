package com.qiji.network.net.module;


import com.qiji.network.utils.SPUtils;
import com.qiji.network.utils.Utils;

/**
 * Created by jokerlee on 16/9/28.
 */
public class BaseRequest {
    public String token;


    public BaseRequest() {
        token= (String) SPUtils.get(Utils.getContext(),"token","");
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void updateToken(String token) {
        this.token = token;
    }
}
