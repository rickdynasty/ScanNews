package com.example.eight.scannews.utils;

import com.example.eight.scannews.beans.NewsBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by eight on 2017/6/11.
 */

public interface ApiService {
    @GET("{channel}/")
    Observable<NewsBean> getNews(@Path("channel") String channel,
                                 @Query("key") String key,
                                 @Query("num") int num,
                                 @Query("page") int page);
}
