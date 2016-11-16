package com.mobiletrain.newapp.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mobiletrain.newapp.R;


/**
 * Created by wangchenggeng on 2016/10/25.
 */
public class VideoFragment extends Fragment {

    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       if(view==null){
           view = inflater.inflate(R.layout.videofragment, container, false);
       }

        return view;
    }
}
