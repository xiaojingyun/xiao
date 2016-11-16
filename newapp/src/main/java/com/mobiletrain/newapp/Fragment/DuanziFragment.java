package com.mobiletrain.newapp.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.mobiletrain.newapp.R;
import com.mobiletrain.newapp.adapter.DuanziAdapter;
import com.mobiletrain.newapp.model.Episode_duanzi;
import com.mobiletrain.newapp.util.HttpUtil;
import com.mobiletrain.newapp.util.JsonUtil;
import com.mobiletrain.newapp.util.ThreadUtil;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by wangchenggeng on 2016/10/25.
 */
public class DuanziFragment extends Fragment {

    @BindView(R.id.lv_duanzi)
    ListView lvDuanzi;
    private View view;
    private static final String TAG ="test" ;
    private static final int MSG_JSON_GOT = 10;
    private Episode_duanzi episode_duanzi;
    private DuanziAdapter adapter;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_JSON_GOT:
                    episode_duanzi= ((Episode_duanzi) msg.obj);
                    adapter = new DuanziAdapter(episode_duanzi,getActivity());
                   Log.e(TAG, " episode_duanzi:"+episode_duanzi.getShowapi_res_body().getPagebean().getContentlist());
                    lvDuanzi.setAdapter(adapter);
                    break;
            }
        }
    };
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (view == null) {
            view = inflater.inflate(R.layout.duanzifragment, container, false);
            ButterKnife.bind(this, view);
                testDuanzi();
        }
        return view;
    }

    private void testDuanzi() {
        ThreadUtil.execute(new Runnable() {
            @Override
            public void run() {
                String json = HttpUtil.getParticularContent(29, 1);
             //   Log.e(TAG, "json " + json);
                Episode_duanzi episode_duanzi = JsonUtil.parseEpisode_duanzi(json);

        //  Log.e(TAG, "contentlist哈哈哈哈 :"+contentlist);
          Message msg = handler.obtainMessage();
          msg.what = MSG_JSON_GOT;
          msg.obj = episode_duanzi;
          handler.sendMessage(msg);
            }
         });

    }
}
