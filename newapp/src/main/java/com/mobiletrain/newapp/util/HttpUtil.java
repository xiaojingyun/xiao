package com.mobiletrain.newapp.util;

import android.support.annotation.NonNull;
import android.util.Log;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by idea on 2016/10/8.
 */
public class HttpUtil {

    public static final int APP_ID = 25300;
    public static final String APP_SIGN = "c01fe0e21e9b444fb25f3783a5be40ae";
    private static final String TAG = "test";


   /*
        查询的类型，默认全部返回。
            type=10 图片
            type=29 段子
            type=31 声音
            type=41 视频

            page	String	1		否	第几页。每页最多返回20条记录

           title	String			否	文本中包括的内容，模糊查询
            */
    public static String getParticularContent(int type ,int page) {
        String json = getStringByOkhttp("https://route.showapi.com/255-1?" +"page=" +page+ "&showapi_appid="+APP_ID+"&showapi_timestamp=20161024091617" + "&title=" + "&type=" +type+ "&showapi_sign="+APP_SIGN);
        Log.e(TAG, "json "+json );
        return json;
    }

         /*  title	String			否	文本中包括的内容，模糊查询
         * page	String	1		否	第几页。每页最多返回20条记录
         * */

    public static String getVague(String str,int page) {
        String json = getStringByOkhttp("https://route.showapi.com/255-1?"+"page="+page+"&showapi_appid="+APP_ID+"&showapi_timestamp=20161024091617"+"&title="+str+"&type="+ "&showapi_sign="+APP_SIGN);

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


    public static InputStream getBitmapByOkhttp(String url) {
      //  String json = "";
        InputStream inputStream =null;
        OkHttpClient okHttpClient = new OkHttpClient();//1、创建OkHttpClient
        Request request = new Request.Builder()//2、通过构造器构造Request
                .url(url)//配置URL
                .tag("tag")//为request设置标签，将来可以通过标签找到请求，并取消之
                .build();
        try {
            Response response = okHttpClient.newCall(request).execute();//3、执行Request

            //4、解析Response
            if(response.isSuccessful()){
               inputStream = response.body().byteStream();//拿到字符流，还可以是bytes(),byteStream()
            }else {
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(inputStream!=null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

        return inputStream;
    }
    public static InputStream gethttp(String url) {




        //  String json = "";
        InputStream inputStream =null;
        OkHttpClient okHttpClient = new OkHttpClient();//1、创建OkHttpClient
        Request request = new Request.Builder()//2、通过构造器构造Request
                .url(url)//配置URL
                .tag("tag")//为request设置标签，将来可以通过标签找到请求，并取消之
                .build();
        try {
            Response response = okHttpClient.newCall(request).execute();//3、执行Request

            //4、解析Response
            if(response.isSuccessful()){
               inputStream = response.body().byteStream();//拿到字符流，还可以是bytes(),byteStream()
            }else {
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(inputStream!=null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }


        return inputStream;
    }

}
