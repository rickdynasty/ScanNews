package com.example.eight.scannews.view;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.eight.scannews.utils.BootReceiver;
import com.example.eight.scannews.utils.NewsAdapter;
import com.example.eight.scannews.R;
import com.example.eight.scannews.beans.NewsBean;
import com.example.eight.scannews.contract.Contract;
import com.example.eight.scannews.presenter.NewsPresenterImpl;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;
import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Created by eight on 2017/6/9.
 */

public class NewsListFragment extends Fragment
        implements Contract.NewsView, SwipeRefreshLayout.OnRefreshListener {

    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView newsListView;
    private LinearLayoutManager layoutManager;
    private Contract.NewsPresenter newsPresenter;
    private NewsAdapter newsAdapter;


    private List<NewsBean.NewslistBean> data;
    private int type = 0;
    private int pageIndex = 0;
    private final int pageSize = 10;
    private final String key = "27fe422dbc0fafc86d0be396ea5761e0";

    public static NewsListFragment newInstance(int type) {
        Bundle bundle = new Bundle();
        NewsListFragment fragment = new NewsListFragment();
        bundle.putInt("type", type);
        Log.e("----->", "newInstance: " + type);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        newsPresenter = new NewsPresenterImpl(getContext(), this);
        type = getArguments().getInt("type");
        Log.e("---------->", "onCreate: " + type);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.news_list_fragment, null);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);
        newsListView = (RecyclerView) view.findViewById(R.id.news_list_view);
        Log.e("----->", "onCreateView: ");

        swipeRefreshLayout.setColorSchemeResources(
                R.color.colorPrimary,
                R.color.colorAccent,
                R.color.colorPrimaryDark);
        swipeRefreshLayout.setOnRefreshListener(this);

        newsListView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        newsListView.setLayoutManager(layoutManager);
        newsListView.setItemAnimator(new DefaultItemAnimator());
        newsListView.addItemDecoration(new DividerItemDecoration(getActivity(),
                DividerItemDecoration.VERTICAL));
        newsAdapter = new NewsAdapter(getActivity().getApplicationContext());
        newsAdapter.setOnItemClickListener(onItemClickListener);
        newsListView.setAdapter(newsAdapter);
        newsListView.addOnScrollListener(onScrollListener);
        onRefresh();
        return view;
    }



    private NewsAdapter.OnItemClickListener onItemClickListener = new NewsAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(View view, int position) {
            if (data.size() <= 0) {
                return;
            }
            NewsBean.NewslistBean newslistBean = newsAdapter.getItem(position);
            // 跳转
            Intent intent = new Intent(getActivity(), NewsDetailActivity.class);
            ArrayList<String> news = new ArrayList<>();
            news.add(newslistBean.getTitle());
            news.add(newslistBean.getUrl());
            if (SettingsActivity.isSwitchEnable) {
                news.add(newslistBean.getPicUrl());
            } else {
                news.add("");
            }
            Log.e(TAG, "onItemClick: ---> " + news.toString());
            Bundle bundle = new Bundle();
            bundle.putStringArrayList("news", news);
            intent.putExtras(bundle);
            View transitionView = view.findViewById(R.id.news_picture);
            ActivityOptionsCompat optionsCompat = ActivityOptionsCompat
                    .makeSceneTransitionAnimation(getActivity(), transitionView, "news_picture");
            ActivityCompat.startActivity(getActivity(), intent, optionsCompat.toBundle());
        }
    };


    private RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener() {
        private int lastVisibleItem;

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if (newState == RecyclerView.SCROLL_STATE_IDLE
                    && lastVisibleItem + 1 == newsAdapter.getItemCount()
                    && newsAdapter.isShowFooter()) {
                newsPresenter.loadNews(type, key, pageSize, pageIndex);
            }
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            lastVisibleItem = layoutManager.findLastVisibleItemPosition();
        }
    };

    @Override
    public void showProgress() {
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void addNews(List<NewsBean.NewslistBean> newsBeanList) {
        newsAdapter.setShowFooter(true);
        if (data == null) {
            data = new ArrayList<>();
        }
        data.addAll(newsBeanList);

        if (pageIndex == 0) {
            newsAdapter.setData(data);
        } else {
            if (newsBeanList.size() == 0) {
                newsAdapter.setShowFooter(false);
            }
            newsAdapter.notifyDataSetChanged();
        }
        pageIndex ++;
    }

    @Override
    public void hideProgress() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showLoadingFail() {
        if (pageIndex == 0) {
            newsAdapter.setShowFooter(false);
            newsAdapter.notifyDataSetChanged();
        }
        View view = getActivity() == null ? newsListView.getRootView()
                : getActivity().findViewById(R.id.drawer_layout);
        Snackbar.make(view, "加载失败...", Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onRefresh() {
        pageIndex = 0;
        if (data != null) {
            data.clear();
        }
        newsPresenter.loadNews(type, key, pageSize, pageIndex);
    }

}
