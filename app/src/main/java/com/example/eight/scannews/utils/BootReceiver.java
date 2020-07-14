package com.example.eight.scannews.utils;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.example.eight.scannews.R;
import com.example.eight.scannews.beans.NewsBean;
import com.example.eight.scannews.contract.Contract;
import com.example.eight.scannews.presenter.NewsPresenterImpl;
import com.example.eight.scannews.view.NewsDetailActivity;

import java.util.List;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Created by eight on 2017/9/18.
 */

public class BootReceiver extends BroadcastReceiver implements Contract.NewsView {
    private Contract.NewsPresenter newsPresenter;
    private List<NewsBean.NewslistBean> news;

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)) {
            newsPresenter = new NewsPresenterImpl(context, null);
            newsPresenter.loadNews(0, "27fe422dbc0fafc86d0be396ea5761e0", 1, 0);
            // 执行的操作
            // 构建一个Intent
            Intent nIntent = new Intent(context, NewsDetailActivity.class);
            // 封装一个Intent
            PendingIntent pendingIntent = PendingIntent.getActivity(context,
                    0, nIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            NotificationManager notificationManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
            NotificationCompat.Builder builder = new NotificationCompat.Builder(context);

            builder.setContentTitle(news.get(0).getTitle()) // 标题
                    .setContentText(news.get(0).getDescription()) // 内容
                    .setSmallIcon(R.drawable.ic_launcher) // 图标
                    .setWhen(System.currentTimeMillis())
                    .setPriority(Notification.PRIORITY_DEFAULT)
                    .setDefaults(Notification.DEFAULT_VIBRATE)
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true);
            notificationManager.notify(1, builder.build());

        }
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void addNews(List<NewsBean.NewslistBean> newsBeanList) {
        news = newsBeanList;
    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showLoadingFail() {

    }
}
