package com.mobiletrain.qqmusic.util;

import android.support.annotation.NonNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by idea on 2016/10/8.
 */
public class HttpUtil {

    public static final int APP_ID = 19988;
    public static final String APP_SIGN = "968ad4fcc2144e41b5c366838d1b0ec4";
    private static final String TAG = "test";

    /**
     * 根据榜单ID获取榜单JSON
     *
     * @param topId 榜单参数 5=内地，19=摇滚
     * @return json
     */
    public static String getTops(int topId) {
        String json = getStringByOkhttp("http://route.showapi.com/213-4?showapi_appid=" + APP_ID + "&topid=" + topId + "&showapi_sign=" + APP_SIGN);
        return json;
    }

    @NonNull
    private static String getString(String urlStr) {
        String json = "";
        InputStream inputStream = null;
        BufferedReader reader = null;

        try {
            URL url = new URL(urlStr);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setReadTimeout(20000);
            connection.connect();

            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                inputStream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(inputStream));
                String line = null;

                while ((line = reader.readLine()) != null) {
                    json += line;
                }

                return json;

            } else {
                json = "responseCode=" + responseCode;
            }

        } catch (Exception e) {
            e.printStackTrace();
            json = "Exception=" + e;
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return json;
    }

    /**
     * 根据musicId获得歌曲歌词
     *
     * @param musicId
     * @return json
     */
    public static String getLyric(int musicId) {
        String json = getStringByOkhttp("http://route.showapi.com/213-2?musicid="+musicId+"&showapi_appid="+APP_ID+"&showapi_sign="+APP_SIGN);
        return json;
    }

    /**
     * 根据输入的关键字（歌手名或单曲名）显示对应的搜索结果
     *
     * @param keyword 用户输入的关键字
     * @return JSON
     */
    public static String query(String keyword) {
        String json = getStringByOkhttp("http://route.showapi.com/213-1?keyword="+keyword+"&page=1&showapi_appid="+APP_ID+"&showapi_sign="+APP_SIGN);
        return json;
    }

    @NonNull
    private static String getStringByOkhttp(String url) {
        String json = "";
        OkHttpClient okHttpClient = new OkHttpClient();//1、创建OkHttpClient
        Request request = new Request.Builder()//2、通过构造器构造Request
                .url(url)//配置URL
                .tag("tag")//为request设置标签，将来可以通过标签找到请求，并取消之
                .build();
        try {
            Response response = okHttpClient.newCall(request).execute();//3、执行Request

            //4、解析Response
            if(response.isSuccessful()){
                json = response.body().string();//拿到字符流，还可以是bytes(),byteStream()
            }else {
                json = "response not successful";
            }
        } catch (IOException e) {
            e.printStackTrace();
            json = "IOException";
        }

        return json;
    }

}
