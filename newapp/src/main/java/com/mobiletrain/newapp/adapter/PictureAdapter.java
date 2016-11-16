package com.mobiletrain.newapp.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.AbstractDraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.mobiletrain.newapp.R;
import com.mobiletrain.newapp.model.PictureBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/10/26.
 */
public class PictureAdapter extends BaseAdapter {

    private static final String TAG = "test";
    private final LayoutInflater inflater;
    private List<PictureBean.ShowapiResBodyBean.PagebeanBean.ContentlistBean> contentlist;
    private Context context;
    private PictureBean pictureBean;
    private View view;

    public PictureAdapter(Context context, PictureBean pictureBean) {
        this.context = context;
        if (pictureBean != null) {
            contentlist = pictureBean.getShowapi_res_body().getPagebean().getContentlist();
        }
        // Log.e(TAG, "contentlist哈哈哈哈 :"+list);
        inflater = LayoutInflater.from(context);
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
            view = inflater.inflate(R.layout.picture_item, viewGroup, false);
            holder = new Holder(view);
            view.setTag(holder);
        } else {
            holder = (Holder) view.getTag();
        }

        PictureBean.ShowapiResBodyBean.PagebeanBean.ContentlistBean contentlistBean = contentlist.get(position);

          holder.tvwenzi.setText(contentlistBean.getText());//正文
         holder.biaotiming.setText(contentlistBean.getName());//标题

        String image1 = contentlistBean.getImage1();//图片
        AbstractDraweeController draweeController = Fresco.newDraweeControllerBuilder()
                .setUri(Uri.parse(image1))
                .setAutoPlayAnimations(true)
                .build();
        holder.sdvPicture.setController(draweeController);
        String profile_image = contentlistBean.getProfile_image();

         holder.sdv.setImageURI(Uri.parse(profile_image));//头像;

         holder.tvtime.setText(contentlistBean.getCreate_time());//上传时间

        return view;
    }
    static class Holder {
        @BindView(R.id.sdv)
        SimpleDraweeView sdv;
        @BindView(R.id.biaotiming)
        TextView biaotiming;
        @BindView(R.id.tvtime)
        TextView tvtime;
        @BindView(R.id.tvwenzi)
        TextView tvwenzi;
        @BindView(R.id.sdvPicture)
        SimpleDraweeView sdvPicture;

        Holder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
