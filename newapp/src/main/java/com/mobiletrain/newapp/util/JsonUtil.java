package com.mobiletrain.newapp.util;

import android.support.annotation.Nullable;

import com.google.gson.Gson;
import com.mobiletrain.newapp.model.Episode_duanzi;
import com.mobiletrain.newapp.model.MusicBean;
import com.mobiletrain.newapp.model.PictureBean;
import com.mobiletrain.newapp.model.Video;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by idea on 2016/10/8.
 */
public class JsonUtil {

    public static Episode_duanzi parseEpisode_duanzi(String json) {
        Episode_duanzi episode_duanzi = new Gson().fromJson(json, Episode_duanzi.class);
        return episode_duanzi;
    }

    public static MusicBean parseMusicBean(String json) {
//        return getTopResultByNative(json);
        MusicBean musicBean = new Gson().fromJson(json, MusicBean.class);
        return musicBean;
    }

    @Nullable/*TopResult*/
    private static void getTopResultByNative(String json) {
        //TopResult topResult = null;
        try {
            JSONObject jsonObject = new JSONObject(json);
            int showapi_res_code = jsonObject.getInt("showapi_res_code");
            if (showapi_res_code != 0) {
              //  return null;
            }

            JSONObject showapi_res_body = jsonObject.getJSONObject("showapi_res_body");
            JSONObject pagebean = showapi_res_body.getJSONObject("pagebean");
            JSONArray songlist = pagebean.getJSONArray("songlist");
          //  topResult = new TopResult();
           // List<TopResult.Song> list = new ArrayList<>();
            for (int i = 0; i < songlist.length(); i++) {
                JSONObject songObj = songlist.getJSONObject(i);

            /*    TopResult.Song song = new TopResult.Song();
                song.setSongname(songObj.getString("songname"));
                song.setSingername(songObj.getString("singername"));
                song.setDownloadUrl(songObj.getString("downUrl"));
                song.setAlbumpic_big(songObj.getString("albumpic_big"));
                song.setAlbumpic_small(songObj.getString("albumpic_small"));*/

             //   list.add(song);
            }
          //  topResult.setSonglist(list);

        } catch (Exception e) {
            e.printStackTrace();
          //  return null;
        }

      //  return topResult;
    }

    public static PictureBean parsePicture(String json) {
        PictureBean picture = new Gson().fromJson(json, PictureBean.class);
        return picture;
    }

    public static Video parseVideo(String json) {
        Video video = new Gson().fromJson(json,  Video.class);
        return video;
    }
}
