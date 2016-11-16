package com.mobiletrain.newapp.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mobiletrain.newapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by wangchenggeng on 2016/10/25.
 */
public class MusicFragment extends Fragment {
    @BindView(R.id.tv)
    TextView tv;
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (view == null) {
            view = inflater.inflate(R.layout.musicfragment, container, false);
            ButterKnife.bind(this, view);

        }


        return view;
    }
}
