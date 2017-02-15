package com.share.jack.im;

import android.util.Log;

import com.easemob.EMCallBack;
import com.easemob.EMEventListener;
import com.easemob.EMNotifierEvent;
import com.easemob.chat.EMChat;
import com.easemob.chat.EMChatManager;
import com.easemob.chat.EMGroupManager;
import com.easemob.chat.EMMessage;
import com.jack.mc.cyg.cygtools.app.CygApplication;
import com.jack.mc.cyg.cygtools.util.CygString;
import com.share.jack.easeui.controller.EaseUI;

import java.util.List;


/**
 *
 */
public final class EaseMobApp {

    private static final String TAG = "EaseMobApp";

    private EaseMobApp() {
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    public static boolean initialize() {
        EMChat.getInstance().setAutoLogin(false);
        if (!EaseUI.getInstance().init(CygApplication.getInstance())) {
            return false;
        }

        //设为调试模式，打成正式包时，最好设为false，以免消耗额外的资源
        EMChat.getInstance().setDebugMode(true);

        EMChatManager.getInstance().registerEventListener(new EMEventListener() {
            @Override
            public void onEvent(EMNotifierEvent emNotifierEvent) {
                onEMNotifierEvent(emNotifierEvent);
            }
        });

        return true;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    public static void login(final String userName, String password) {
        EMChatManager.getInstance().login(userName, password, new EMCallBack() {//回调
            @Override
            public void onSuccess() {
                EMGroupManager.getInstance().loadAllGroups();
                EMChatManager.getInstance().loadAllConversations();
                if (!EMChatManager.getInstance().updateCurrentUserNick(userName)) {
                    Log.e(TAG, "update current user nick fail");
                }
                Log.d(TAG, "登录聊天服务器成功！");
            }

            @Override
            public void onProgress(int progress, String status) {

            }

            @Override
            public void onError(int code, String message) {
                Log.d("main", "登录聊天服务器失败！");
            }
        });
        name = userName;
        pwd = password;
    }

    private static String name;
    private static String pwd;

    public static void logout() {
        name = null;
        pwd = null;
        EMChatManager.getInstance().logout(new EMCallBack() {
            @Override
            public void onSuccess() {
                Log.i(TAG, "onSuccess: ");
            }

            @Override
            public void onError(int code, String message) {
                Log.e(TAG, "onError: code: " + code + ", message: " + message);
            }

            @Override
            public void onProgress(int progress, String status) {

            }
        });
    }

    public static boolean checkLoggedIn() {
        if (isLogin()) {
            return true;
        }
        login(name, pwd);
        return false;
    }

    public static boolean isLogin() {
        try {
            if (!EMChat.getInstance().isLoggedIn()) {
                return false;
            }
            if (EMChatManager.getInstance().isConnected()) {
                return true;
            }
            String currentUser;
            try {
                currentUser = EMChatManager.getInstance().getCurrentUser();
            } catch (Exception e) {
                Log.i(TAG, "isLogin: " + e.getMessage());
                return false;
            }
            return !CygString.isEmpty(currentUser);
        } catch (Exception e) {
            Log.e(TAG, "isLogin: " + e.getMessage());
            return false;
        }
    }

    private static void onEMNotifierEvent(EMNotifierEvent event) {
        try {
            switch (event.getEvent()) {
                case EventNewMessage: {//接收新消息
                    EMMessage message = (EMMessage) event.getData();
                    break;
                }
                case EventDeliveryAck: {//接收已发送回执
                    break;
                }
                case EventNewCMDMessage: {//接收透传消息
                    break;
                }
                case EventReadAck: {//接收已读回执
                    break;
                }
                case EventOfflineMessage: {//接收离线消息
                    List<EMMessage> messages = (List<EMMessage>) event.getData();
                    break;
                }
                case EventConversationListChanged: {//通知会话列表通知event注册（在某些特殊情况，SDK去删除会话的时候会收到回调监听）
                    break;
                }
                case EventMessageChanged: {
                    break;
                }
                case EventLogout: {
                    break;
                }
                default: {
                    break;
                }
            }
        } catch (Exception e) {
            Log.e(TAG, "onEMNotifierEvent: " + e.getMessage());
        }
    }
}
