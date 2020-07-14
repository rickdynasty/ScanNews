package com.example.eight.scannews.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.eight.scannews.R;
import com.example.eight.scannews.beans.NewsBean;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by eight on 2017/6/10.
 */

public class HttpUtils {
    private static String baseUrl = "https://api.tianapi.com/";
    private static final int DEFAULT_TIMEOUT = 10;
    private Retrofit retrofit;
    private ApiService apiService;

    private static boolean isWifiConnected;

    public static boolean isWifiConnected() {
        return isWifiConnected;
    }

    public static void setIsWifiConnected(boolean isWifiConnected) {
        HttpUtils.isWifiConnected = isWifiConnected;
    }

    /**
     * 私有化构造函数
     */
    private HttpUtils() {

        OkHttpClient client = new OkHttpClient();
        client.newBuilder()
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Log.e("------->", "HttpUtils: " + baseUrl);
        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);
    }

    /**
     * 创建单例
     */
    private static class SingleInstance {
        private static final HttpUtils INSTANCE = new HttpUtils();
    }

    /**
     * 获取单例
     *
     * @param baseUrl 链接
     * @return 返回实例
     */
    public static HttpUtils getInstance(String baseUrl) {
        HttpUtils.baseUrl = baseUrl;
        return SingleInstance.INSTANCE;
    }

    /**
     * @param channel  频道
     * @param key      密钥
     * @param num      每页数量
     * @param page     页数
     * @param observer 观察值对象
     */

    public void getNewsFromHttp(String channel, String key, int num, int page,
                                Observer<NewsBean> observer) {
        apiService.getNews(channel, key, num, page)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    /**
     * @param context   上下文
     * @param imageView 图片控件
     * @param url       图片链接
     */
    public static void showPicture(Context context, ImageView imageView, String url) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }

        SharedPreferences sp = context.getSharedPreferences("SETTING", Context.MODE_PRIVATE);
        boolean isWifiLoading = sp.getBoolean("WIFI_LOADING", false);
        if (isWifiLoading && !isWifiConnected()) {
            url = "";
        }
        Glide.with(context)
                .load(url)
                .placeholder(R.drawable.ic_block)
                .error(R.drawable.ic_news)
                .crossFade()
                .into(imageView);
    }

}
