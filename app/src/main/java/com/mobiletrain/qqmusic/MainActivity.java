package com.mobiletrain.qqmusic;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.mobiletrain.qqmusic.model.TopResult;
import com.mobiletrain.qqmusic.util.HttpUtil;
import com.mobiletrain.qqmusic.util.JsonUtil;
import com.mobiletrain.qqmusic.util.ThreadUtil;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int MSG_JSON_GOT = 10;
    private TextView tv;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case MSG_JSON_GOT:
                    tv.setText(((List<TopResult.Song>) msg.obj).toString());
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = ((TextView) findViewById(R.id.tv));

        ThreadUtil.execute(new Runnable() {
            @Override
            public void run() {
                String json = HttpUtil.getTops(5);
                List<TopResult.Song> songs = null;
                TopResult topResult = JsonUtil.parseTopResult(json);
                if(topResult!=null){
                    songs = topResult.getSonglist();
                }
//                String json = HttpUtil.getLyric(4833285);
//                String json = HttpUtil.query("许巍");

                Message msg = handler.obtainMessage();
                msg.what = MSG_JSON_GOT;
                msg.obj = songs;
                handler.sendMessage(msg);
            }
        });

    }
}
