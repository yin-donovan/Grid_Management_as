package com.cn7782.management.android.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.PowerManager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.cn7782.management.R;
import com.cn7782.management.android.BaseActivity;
import com.easemob.easeui.EaseConstant;

public class MsgNotificationActivity extends BaseActivity implements View.OnClickListener {

    private PowerManager.WakeLock mWakelock;
    private Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Window win = getWindow();
        win.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        win.addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
        win.addFlags(WindowManager.LayoutParams.FLAG_ALLOW_LOCK_WHILE_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
        //WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD 为解锁设置
        setContentView(R.layout.msg_dialog_layout);
        bundle = getIntent().getBundleExtra("data");

        TextView msgnum = (TextView)this.findViewById(R.id.noread_textview);
        msgnum.setText(bundle.getInt("num")+"");

        TextView lastcontent = (TextView)findViewById(R.id.newmsg_textview);
        lastcontent.setText(bundle.getString("lastContent"));
        lastcontent.setOnClickListener(this);

        TextView lasttime = (TextView)findViewById(R.id.lasttime_textview);
        lasttime.setText(bundle.getString("lastTime"));

        this.findViewById(R.id.close_imageview).setOnClickListener(this);
        this.findViewById(R.id.detail_layout).setOnClickListener(this);

        new MyCountDownTimer(5000,5000).start();
    }

    @Override
    protected void onResume() {
        super.onResume();
        PowerManager pm = (PowerManager)getSystemService(Context.POWER_SERVICE);
        //点亮屏幕
        mWakelock = pm.newWakeLock(PowerManager.ACQUIRE_CAUSES_WAKEUP |PowerManager.SCREEN_DIM_WAKE_LOCK, "SimpleTimer");
        mWakelock.acquire();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mWakelock.release();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.close_imageview:
                finish();
                break;
            case R.id.detail_layout:
                Intent msg = new Intent(this,ConversationListActivity.class);
                startActivity(msg);
                finish();
                break;
            case R.id.newmsg_textview:
                Intent msgDetail = new Intent(this,ChatActivity.class);
                msgDetail.putExtra(EaseConstant.EXTRA_USER_ID,
                    bundle.getString(EaseConstant.EXTRA_USER_ID));
                startActivity(msgDetail);
                finish();
                break;
        }
    }
    private class MyCountDownTimer extends CountDownTimer {
        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {

        }

        @Override
        public void onFinish() {
            finish();
            cancel();
        }
    }
}