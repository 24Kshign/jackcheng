package com.share.jack.jpush;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.jack.mc.cyg.cygtools.http.callback.RxBus;
import com.share.jack.jpush.event.JPushAutoRefresh;
import com.share.jack.jpush.event.JPushRegistrationIdEvent;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import cn.jpush.android.api.JPushInterface;

/**
 * 自定义接收器
 */
public class MyReceiver extends BroadcastReceiver {

    private static final String TAG = "MyReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "收到了JPush的通知");
        Bundle bundle = intent.getExtras();
        if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
            String regId = bundle.getString(JPushInterface.EXTRA_REGISTRATION_ID);
            Log.i(TAG, "[MyReceiver] 接收Registration Id : " + regId);
            //send the Registration Id to your server...
            RxBus.getInstance().post(new JPushRegistrationIdEvent(regId));
        } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {
            Log.i(TAG, "接收到推送下来的通知");
            String title = bundle.getString(JPushInterface.EXTRA_NOTIFICATION_TITLE);
            Log.i(TAG, "title==" + title);
            String message = bundle.getString(JPushInterface.EXTRA_ALERT);
            Log.i(TAG, "message==" + message);
            String extra = bundle.getString(JPushInterface.EXTRA_EXTRA);
            Log.i(TAG, "extra==" + extra);
        } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
            Log.i(TAG, "用户打开了通知");
            String extra = bundle.getString(JPushInterface.EXTRA_EXTRA);
            Map<String, Object> map = getMapFromExtra(extra);
            String url = map.get("url").toString();
            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        } else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
            Log.d(TAG, "[MyReceiver] 接收到推送下来的自定义消息: " + bundle.getString(JPushInterface.EXTRA_MESSAGE));
            RxBus.getInstance().post(new JPushAutoRefresh());
        }
    }

    /**
     * 推送过来的url信息(json数据)转为Map集合
     *
     * @param extra
     * @return
     */
    private Map<String, Object> getMapFromExtra(String extra) {
        Map<String, Object> map = new HashMap<>();
        try {
            JSONObject jsonObject = new JSONObject(extra);
            Iterator<String> keys = jsonObject.keys();
            String key;
            Object value;
            while (keys.hasNext()) {
                key = keys.next();
                value = jsonObject.get(key);
                map.put(key, value);
            }
            return map;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
