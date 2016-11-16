package com.mobiletrain.newapp.adapter;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.mobiletrain.newapp.R;
import com.mobiletrain.newapp.model.Episode_duanzi;

import java.io.InputStream;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/10/26.
 */
public class DuanziAdapter extends BaseAdapter {

    private List<Episode_duanzi.ShowapiResBodyBean.PagebeanBean.ContentlistBean> contentlist = null;
    private Context context;
    private Episode_duanzi episode_duanzi;
    private final LayoutInflater inflater;
    private InputStream inputStream = null;

    private Episode_duanzi.ShowapiResBodyBean.PagebeanBean.ContentlistBean contentlistBean;

    public DuanziAdapter(Episode_duanzi episode_duanzi, Context context) {
        this.episode_duanzi = episode_duanzi;
        this.context = context;
        inflater = LayoutInflater.from(context);
        if (episode_duanzi != null) {
            contentlist = episode_duanzi.getShowapi_res_body().getPagebean().getContentlist();

        }
    }

    @Override
    public int getCount() {
        if (contentlist != null) {
            return contentlist.size();
        }
        return 0;

    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        Holder holder;
        if (view == null) {
            view = inflater.inflate(R.layout.duanzi_item, viewGroup, false);
            holder = new Holder(view);
            view.setTag(holder);
        } else {
            holder = (Holder) view.getTag();
        }

        contentlistBean = contentlist.get(position);

        holder.tv.setText(contentlistBean.getText());//设置昵称

        holder.tvtime.setText(contentlistBean.getCreate_time().toString());
        ;//发表时间


        String profile_image = contentlistBean.getProfile_image();//图片网址

        Log.e("abc", "contentlistBean.getProfile_image(): " + profile_image);


       /* Picasso.with(context)
                .load(Uri.parse(profile_image))//参数为网络地址字符串，效率低于uri参数
                .noFade()
                .resize(40, 40)
                .into(holder.sdv);*/
        holder.sdv.setImageURI(Uri.parse(profile_image));//头像


        holder.biaotiming.setText(contentlistBean.getName().toString());
        //昵称

        return view;
    }


    static class Holder {
        @BindView(R.id.view)
        TextView view;
        @BindView(R.id.sdv)
        SimpleDraweeView sdv;
        @BindView(R.id.biaotiming)
        TextView biaotiming;
        @BindView(R.id.tvtime)
        TextView tvtime;
        @BindView(R.id.ll)
        LinearLayout ll;
        @BindView(R.id.tv)
        TextView tv;

        Holder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
