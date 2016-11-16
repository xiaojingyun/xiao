package com.mobiletrain.qqmusic.util;

import android.support.annotation.Nullable;

import com.google.gson.Gson;
import com.mobiletrain.qqmusic.model.TopResult;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by idea on 2016/10/8.
 */
public class JsonUtil {

    public static TopResult parseTopResult(String json) {
//        return getTopResultByNative(json);
        TopResult topResult = new Gson().fromJson(json, TopResult.class);
        return topResult;
    }

    @Nullable
    private static TopResult getTopResultByNative(String json) {
        TopResult topResult = null;
        try {
            JSONObject jsonObject = new JSONObject(json);
            int showapi_res_code = jsonObject.getInt("showapi_res_code");
            if (showapi_res_code != 0) {
                return null;
            }

            JSONObject showapi_res_body = jsonObject.getJSONObject("showapi_res_body");
            JSONObject pagebean = showapi_res_body.getJSONObject("pagebean");
            JSONArray songlist = pagebean.getJSONArray("songlist");
            topResult = new TopResult();
            List<TopResult.Song> list = new ArrayList<>();
            for (int i = 0; i < songlist.length(); i++) {
                JSONObject songObj = songlist.getJSONObject(i);

                TopResult.Song song = new TopResult.Song();
                song.setSongname(songObj.getString("songname"));
                song.setSingername(songObj.getString("singername"));
                song.setDownloadUrl(songObj.getString("downUrl"));
                song.setAlbumpic_big(songObj.getString("albumpic_big"));
                song.setAlbumpic_small(songObj.getString("albumpic_small"));

                list.add(song);
            }
            topResult.setSonglist(list);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return topResult;
    }

}
