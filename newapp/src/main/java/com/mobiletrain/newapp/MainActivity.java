package com.mobiletrain.newapp;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.mobiletrain.newapp.Fragment.DuanziFragment;
import com.mobiletrain.newapp.Fragment.MusicFragment;
import com.mobiletrain.newapp.Fragment.PictureFragment;
import com.mobiletrain.newapp.Fragment.VideoFragment;
import com.mobiletrain.newapp.model.Video;
import com.mobiletrain.newapp.util.HttpUtil;
import com.mobiletrain.newapp.util.JsonUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity {

    private static final int MSG_JSON_GOT = 10;
    private static final String TAG = "test";
    @BindView(R.id.rb1)
    RadioButton rb1;
    @BindView(R.id.rb2)
    RadioButton rb2;
    @BindView(R.id.rb3)
    RadioButton rb3;
    @BindView(R.id.rb4)
    RadioButton rb4;
    @BindView(R.id.rg)
    RadioGroup rg;
    @BindView(R.id.vp)
    ViewPager vp;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private TextView tv;
    private List<Fragment> fragments = new ArrayList<>();
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_JSON_GOT:
                    // tv.setText(((List<Video.ShowapiResBodyBean.PagebeanBean.ContentlistBean>) msg.obj).toString());
                  /*  String string = ((String) msg.obj).toString();
                    if(string!=null){
                        tv.setText(string);
                    }else{
                        Toast.makeText(MainActivity.this,"获取数据失败",Toast.LENGTH_SHORT).show();
                    }*/
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        DuanziFragment duanziFragment = new DuanziFragment();
        VideoFragment videoFragment = new VideoFragment();
        PictureFragment pictureFragment = new PictureFragment();
        MusicFragment musicFragment = new MusicFragment();

        fragments.add(duanziFragment);
        fragments.add(videoFragment);
        fragments.add(pictureFragment);
        fragments.add(musicFragment);
        //Viewpager页面改变的监听
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //正确的做法
                ((RadioButton) rg.getChildAt(position)).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        //为ViewPager设置适配器
        vp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });


        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb1:
                        vp.setCurrentItem(0);
                        break;
                    case R.id.rb2:
                        vp.setCurrentItem(1);
                        break;
                    case R.id.rb3:
                        vp.setCurrentItem(2);
                        break;
                    case R.id.rb4:
                        vp.setCurrentItem(3);
                        break;
                }
            }
        });



        //Toolbar设置
        setSupportActionBar(toolbar);
       // toolbar.setSubtitle("百思不得姐");
        toolbar.setNavigationIcon(R.mipmap.ic_launcher);
        toolbar.setSubtitleTextColor(Color.WHITE);

    }

    private void testVideo() {

        String json = HttpUtil.getParticularContent(41, 1);
        Log.e(TAG, "json " + json);
        Video video = JsonUtil.parseVideo(json);
        List<Video.ShowapiResBodyBean.PagebeanBean.ContentlistBean> contentlist = video.getShowapi_res_body().getPagebean().getContentlist();
        Message msg = handler.obtainMessage();
        msg.what = MSG_JSON_GOT;
        msg.obj = contentlist;
        handler.sendMessage(msg);
    }


}
