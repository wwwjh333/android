package com.jnu.exp13;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.jnu.exp13.atys.ExplainActivity;
import com.jnu.exp13.atys.GameActivity;
import com.jnu.exp13.service.MyMusicService;

public class MainActivity extends Activity {

    private Button startGame;
    private Button continueGame;
    private Button setting;
    private Button explain;
    private Button exit;


    public final Context TAG = MainActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListener();

    }

    private void initListener() {
        BtnListener listener = new BtnListener();
        startGame.setOnClickListener(listener);
        continueGame.setOnClickListener(listener);
        setting.setOnClickListener(listener);
        explain.setOnClickListener(listener);
        exit.setOnClickListener(listener);
    }

    private void initView() {

        startGame = (Button) findViewById(R.id.bt_start);
        continueGame = (Button) findViewById(R.id.bt_continue);
        setting = (Button) findViewById(R.id.bt_setting);
        explain = (Button) findViewById(R.id.bt_explain);
        exit = (Button) findViewById(R.id.bt_exit);
    }

    class BtnListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.bt_start:
                    Intent intent1 = new Intent(TAG, GameActivity.class);
                    startActivity(intent1);
                    break;
                case R.id.bt_continue:
                    Intent intent2 = new Intent(TAG, GameActivity.class);
                    startActivity(intent2);
                    break;
                case R.id.bt_setting:
                    Toast.makeText(TAG, "没什么需要设置的，快去游戏吧", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.bt_explain:
                    Intent intent4 = new Intent(TAG, ExplainActivity.class);
                    startActivity(intent4);
                    break;
                case R.id.bt_exit:
                    finish();
                    Intent intent = new Intent(MainActivity.this, MyMusicService.class);
                    stopService(intent);
                    break;
            }        }
    }

}
