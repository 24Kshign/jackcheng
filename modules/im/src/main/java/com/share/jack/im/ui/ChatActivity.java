package com.share.jack.im.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.jack.mc.cyg.cygtools.activity.CygStartActivity;
import com.share.jack.easeui.EaseConstant;
import com.share.jack.easeui.ui.EaseBaseActivity;
import com.share.jack.easeui.ui.EaseChatFragment;
import com.share.jack.im.EaseMobApp;
import com.share.jack.im.R;


/**
 * 聊天页面
 */
public class ChatActivity extends EaseBaseActivity {

    public static void start(final Activity activity, final String userId, final String userNick) {
        if (EaseMobApp.isLogin()) {
            startChatActivity(activity, userId, userNick);
        } else {
            if (EaseMobApp.checkLoggedIn()) {
                startChatActivity(activity, userId, userNick);
            }
        }
    }

    private static void startChatActivity(Activity activity, String userId, String userNick) {
        Bundle bundle = new Bundle();
        bundle.putString(EaseConstant.EXTRA_USER_ID, userId.toLowerCase());
        bundle.putString(EXTRA_USER_NICK, userNick);
        CygStartActivity.start(activity, ChatActivity.class, bundle);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    public static final String EXTRA_USER_NICK = "userNick";    // 显示名

    public static ChatActivity activityInstance;

    private EaseChatFragment chatFragment;
    private String toChatUsername;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.em_activity_chat);
        activityInstance = this;
        //聊天人或群id
        toChatUsername = getIntent().getExtras().getString(EaseConstant.EXTRA_USER_ID);
        //可以直接new EaseChatFratFragment使用
        chatFragment = new ChatFragment();
        //传入参数
        chatFragment.setArguments(getIntent().getExtras());
        getSupportFragmentManager().beginTransaction().add(R.id.container, chatFragment).commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        activityInstance = null;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        // 点击notification bar进入聊天页面，保证只有一个聊天页面
        String username = intent.getStringExtra(EaseConstant.EXTRA_USER_ID);
        if (toChatUsername.equals(username))
            super.onNewIntent(intent);
        else {
            finish();
            startActivity(intent);
        }
    }

    @Override
    public void onBackPressed() {
        chatFragment.onBackPressed();
    }

    public String getToChatUsername() {
        return toChatUsername;
    }
}
