package com.mobiletrain.newapp.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.mobiletrain.newapp.R;
import com.mobiletrain.newapp.adapter.PictureAdapter;
import com.mobiletrain.newapp.model.PictureBean;
import com.mobiletrain.newapp.util.HttpUtil;
import com.mobiletrain.newapp.util.JsonUtil;
import com.mobiletrain.newapp.util.ThreadUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by wangchenggeng on 2016/10/25.
 */
public class PictureFragment extends Fragment {
    private static final String TAG ="test" ;
    private static final int MSG_JSON_GOT = 10;

    List<PictureBean> list;
     //private  List<String> list;
    @BindView(R.id.lv)
    ListView lv;
    private View view;
    private PictureAdapter adapter;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_JSON_GOT:
                    PictureBean pictureBean = (PictureBean) (PictureBean) msg.obj;
                    adapter = new PictureAdapter(getActivity(),pictureBean);
                // Log.e(TAG, "contentlistcbcbcbcb :"+list);
                    lv.setAdapter(adapter);
                    break;
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.picturefragment, container, false);
            ButterKnife.bind(this, view);

                testPicture();
        }
        return view;
    }

    private void testPicture() {

        ThreadUtil.execute(new Runnable() {
            @Override
            public void run() {
            String json = HttpUtil.getParticularContent(10, 2);
       // Log.e(TAG, "json " + json);
            PictureBean pictureBean = JsonUtil.parsePicture(json);

      //  Log.e(TAG, "contentlist哈哈哈哈 :"+contentlist);
            Message msg = handler.obtainMessage();
            msg.what = MSG_JSON_GOT;
            msg.obj = pictureBean;
            handler.sendMessage(msg);

            }
        });

    }
}
