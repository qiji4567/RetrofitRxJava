package com.qiji.network.utils;

import java.util.ArrayList;

import android.app.Activity;

/**
 * <pre>
 *     author: admin
 *     e-mail  : sk_624@163.com
 *     time  : 2017/4/19
 *     desc  : CDA
 * </pre>
 */

public class ActivityManagerUtils {

    private volatile static ActivityManagerUtils mActivityManagerUtils;


    private ActivityManagerUtils() {
        /**
         * 这里面写一些需要执行初始化的工作
         */
    }

    public static ActivityManagerUtils getInstance() {
        if (mActivityManagerUtils == null) {
            synchronized (ActivityManagerUtils.class) {
                if (mActivityManagerUtils == null) {
                    mActivityManagerUtils = new ActivityManagerUtils();
                }
            }
        }
        return mActivityManagerUtils;
    }

    private ArrayList<Activity> list = new ArrayList<>();

    public void addActivity(Activity activity) {
        list.add(activity);
//        Log.e("ActivityManagerUtils:", list.size() + "-" + activity.getLocalClassName());
    }

    public void clearActivity() {
        for (int i = 0; i < list.size(); i++) {
            Activity activity = list.get(i);
            if (activity != null) {
                activity.finish();
            }
        }
        list.clear();
    }

    /**
     * 结束指定的Activity
     *
     * @param activity
     */

    public void finishActivity(Activity activity) {

        if (activity != null) {
            list.remove(activity);
            activity.finish();
        }
    }

    /**
     * 结束指定类名的Activity
     */
    public void finishActivityClass(Class<?> cls) {
        if (list != null) {
            for (Activity activity : list) {
                if (activity.getClass().equals(cls)) {
                    list.remove(activity);
                    finishActivity(activity);
                    break;
                }
            }
        }

    }

}
