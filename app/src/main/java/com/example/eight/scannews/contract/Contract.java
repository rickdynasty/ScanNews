package com.example.eight.scannews.contract;

import com.example.eight.scannews.beans.NewsBean;

import java.util.List;

/**
 * Created by eight on 2017/6/6.
 */

public interface Contract {
    interface NewsModel {
        void loadNews(String channel, String key, int num, int page, OnLoadNewsListListener listener);
    }

    interface NewsPresenter {
        void loadNews(int type, String key, int num, int page);
    }

    interface NewsView {
        void showProgress();
        void addNews(List<NewsBean.NewslistBean> newsBeanList);
        void hideProgress();
        void showLoadingFail();
    }

    interface OnLoadNewsListListener {
        void onSuccess(List<NewsBean.NewslistBean> list);
        void onFailure(String msg, Exception e);
    }

}
