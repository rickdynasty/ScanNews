# 一个基于Android的新闻客户端

## App地址和截图

**源码地址： [https://github.com/bazhancong/ScanNews](https://github.com/bazhancong/ScanNews)**

**App下载地址： [https://github.com/bazhancong/ScanNews/releases](https://github.com/bazhancong/ScanNews/releases)**

**或者： https://pan.baidu.com/s/1hr3EFw4**



App 截图：

![](https://raw.githubusercontent.com/bazhancong/ScanNews/master/Screenshot/p1.png)

![](https://raw.githubusercontent.com/bazhancong/ScanNews/master/Screenshot/p2.png)![](https://raw.githubusercontent.com/bazhancong/ScanNews/master/Screenshot/p3.png)![](https://raw.githubusercontent.com/bazhancong/ScanNews/master/Screenshot/p4.png)![](https://raw.githubusercontent.com/bazhancong/ScanNews/master/Screenshot/p5.png)![](https://raw.githubusercontent.com/bazhancong/ScanNews/master/Screenshot/p6.png)![](https://raw.githubusercontent.com/bazhancong/ScanNews/master/Screenshot/p7.png)



## 实现流程

### 1.新闻来源

天行数据 [https://www.tianapi.com](https://www.tianapi.com) 图文类接口，总共16个频道

接口地址： [http://api.tianapi.com/频道/?key=APIKEY&num=10](http://api.tianapi.com/频道/?key=APIKEY&num=10) 

| 频道    |      |       |      |
| ----- | ---- | ----- | ---- |
| 科技新闻  | 奇闻异事 | 健康资讯  | 旅游热点 |
| 社会新闻  | 国内新闻 | 国际新闻  | 娱乐花边 |
| 移动互联  | 体育新闻 | 创业新闻  | 军事新闻 |
| NBA新闻 | 足球新闻 | IT界资讯 | 苹果新闻 |

 

### 2.软件架构：

MVP架构，即MVP代表Model，View和Presenter。示意图如下：

 

![](https://raw.githubusercontent.com/bazhancong/ScanNews/master/Screenshot/mvp.png)

​							图  MVP架构示意图

View层负责处理用户事件和视图部分的展示。在Android中，它可能是Activity或者Fragment类。

Model层负责访问数据。数据可以是远端的Server API，本地数据库或者SharedPreference等。

Presenter层是连接（或适配）View和Model的桥梁。

特点：

1. 各部分之间的通信，都是双向的。
2. View 与 Model 不发生联系，都通过 Presenter 传递。
3. View 非常薄，不部署任何业务逻辑，称为"被动视图"（PassiveView），即没有任何主动性，而 Presenter非常厚，所有逻辑都部署在那里。



架构基础代码：

 ```java
public interface Contract {
    // Model 层
    interface NewsModel {
        // 加载
        void loadNews(String channel, String key, int num, int page, OnLoadNewsListListener listener);
    }

    // Presenter 层
    interface NewsPresenter {
        // 加载
        void loadNews(int type, String key, int num, int page);
    }

    // View 层
    interface NewsView {
        void showProgress(); // 显示进度条
        void addNews(List<NewsBean.NewslistBean> newsBeanList); // 数据显示
        void hideProgress(); // 隐藏进度条
        void showLoadingFail(); // 加载失败
    }

    // 监听事件
    interface OnLoadNewsListListener {
        void onSuccess(List<NewsBean.NewslistBean> list); // 返回数据成功
        void onFailure(String msg, Exception e); // 返回数据失败
    }
}
 ```



## 3.统一网络访问控制

数据访问控制 采用RxJava+ Retrofit 框架

 ```java
// 链接接口
public interface ApiService {
    @GET("{channel}/")
    Observable<NewsBean> getNews(@Path("channel") String channel,
                                 @Query("key") String key,
                                 @Query("num") int num,
                                 @Query("page") int page);
}


public class HttpUtils {
    private static String baseUrl = "https://api.tianapi.com/";
    private static final int DEFAULT_TIMEOUT = 10;
    private Retrofit retrofit;
    private ApiService apiService;

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
     * @param baseUrl 链接
     * @return 返回实例
     */
    public static HttpUtils getInstance(String baseUrl) {
        HttpUtils.baseUrl = baseUrl;
        return SingleInstance.INSTANCE;
    }

    /**
     * @param channel  频道
     * @param key  密钥
     * @param num  每页数量
     * @param page 页数
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
}

 ```



图片加载控制 采用Glide 框架

 ```java
	/**
     * @param context  上下文
     * @param imageView  图片控件
     * @param url  图片链接
     */
	public static void showPicture(Context context, ImageView imageView, String url) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(context)
                .load(url)
                .placeholder(R.drawable.ic_block)
                .error(R.drawable.ic_news)
                .crossFade()
                .into(imageView);
    }

 ```



新闻数据加载实现

 ```java
HttpUtils.getInstance(BASE_URL).getNewsFromHttp(channel, key, num, page, new Observer<NewsBean>() {
         @Override
         public void onSubscribe(@NonNull Disposable d) {
                 // 开始加载
         }

         @Override
         public void onNext(@NonNull NewsBean newsBean) {
                  // 加载成功
                  List<NewsBean.NewslistBean> newslistBeanList = newsBean.getNewslist();  
listener.onSuccess(newslistBeanList);
         }

         @Override
         public void onError(@NonNull Throwable e) {
                 // 加载失败
                  listener.onFailure("Failed...", (Exception) e);
         }

         @Override
         public void onComplete() {
                 // 加载完成
         }
});

 ```



 

### 4.界面设计

频道之间采用TabLayout + View Pager 实现

```xml
// news_tab_pager_fragment.xml 布局
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:orientation="vertical"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <android.support.design.widget.TabLayout
        android:id="@+id/tab_layout"
        android:layout_width="match_parent"
        android:layout_height="35dp"
        app:tabBackground="@color/white"
        app:tabTextColor="@color/colorPrimaryDark"
        app:tabSelectedTextColor="@color/colorAccent"
        app:tabMode="scrollable"/>

    <android.support.v4.view.ViewPager
        android:id="@+id/view_pager"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:background="@color/white"/>
</LinearLayout>

```

 ```java
// NewsTabPageFragment.java代码
public class NewsTabPageFragment extends Fragment {
    private static List<String> newsTab = new ArrayList<>();

    public static List<String> getNewsTab() {
        return newsTab;
    }

    public static void setNewsTab(List<String> newsTab) {
        NewsTabPageFragment.newsTab = newsTab;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.news_tab_page_layout, null);
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.view_pager);
        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tab_layout);

        newsTab =  ChannelsUtils.setupTab("cn");
        viewPager.setOffscreenPageLimit(1);
        initViewPager(viewPager);
        for (String aNewsTab : newsTab) {
            tabLayout.addTab(tabLayout.newTab().setText(aNewsTab));
        }
        tabLayout.setupWithViewPager(viewPager);
        return view;
    }

    private void initViewPager(ViewPager viewPager) {
        MyPagerAdapter adapter = new MyPagerAdapter(getChildFragmentManager());
        for (int i = 0; i < newsTab.size(); i++) {
            adapter.addFragment(NewsListFragment.newInstance(i), newsTab.get(i));
        }
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    // 适配器
    private class MyPagerAdapter extends FragmentPagerAdapter{
        private final List<Fragment> fragmentList = new ArrayList<>();
        private final List<String> titleList = new ArrayList<>();

        public MyPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        public void addFragment(Fragment fragment, String title) {
            fragmentList.add(fragment);
            titleList.add(title);
        }


        @Override
        public int getCount() {
            return fragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titleList.get(position);
        }
    }
}
 ```



### 5.频道管理

建立显示频道列表和隐藏频道列表，选中一个显示频道列表中的一个频道，则表示将其添加到隐藏频道列表中，即在显示频道列表中删除它，还要在隐藏频道列表的最后加上。反之亦然。

 ```java
    private void initData() {
        selectedList = new ArrayList<>();
        unselectedList = new ArrayList<>();

        SharedPreferences sp = getApplicationContext().getSharedPreferences("SETTING", MODE_PRIVATE);
        if (sp.getInt("selectedList", 0) == 0) {
            selectedList = NewsTabPageFragment.getNewsTab();
        } else {
            for (int i = 0; i < sp.getInt("selectedList", 0); i++) {
                selectedList.add(sp.getString("selectedList" + i, null));
            }
        }
        if (sp.getInt("unselectedList", 0) == 0) {
        } else {
            for (int i = 0; i < sp.getInt("unselectedList", 0); i++) {
                unselectedList.add(sp.getString("unselectedList" + i, null));
            }
        }
    }

    private void initView() {
        selectedChannels = (RecyclerView) findViewById(R.id.selected_channels);
        unselectedChannels = (RecyclerView) findViewById(R.id.unselected_channels);
        initSelectedChannels();
        initUnselectedChannels();
    }

    private void initSelectedChannels() {
        GridLayoutManager manager = new GridLayoutManager(selectedChannels.getContext(), 3);
        selectedChannels.setLayoutManager(manager);

        selectedAdapter = new ChannelsAdapter(ChannelManageActivity.this);
        selectedAdapter.getData(selectedList);
        selectedChannels.setAdapter(selectedAdapter);

        selectedAdapter.setItemClickListener(new ChannelsAdapter.ItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Log.e("-------->", "onItemClick: " + selectedList.get(position));
                unselectedList.add(selectedList.get(position));
                unselectedAdapter.getData(unselectedList);
                unselectedAdapter.notifyDataSetChanged();

                selectedList.remove(position);
                selectedAdapter.getData(selectedList);
                selectedAdapter.notifyDataSetChanged();

            }
        });
    }

    private void initUnselectedChannels() {
        GridLayoutManager manager = new GridLayoutManager(unselectedChannels.getContext(), 3);
        unselectedChannels.setLayoutManager(manager);

        unselectedAdapter = new ChannelsAdapter(ChannelManageActivity.this);
        unselectedAdapter.getData(unselectedList);
        unselectedChannels.setAdapter(unselectedAdapter);

        unselectedAdapter.setItemClickListener(new ChannelsAdapter.ItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Log.e("-------->", "onItemClick: " + unselectedList.get(position));
                selectedList.add(unselectedList.get(position));
                selectedAdapter.getData(selectedList);
                selectedAdapter.notifyDataSetChanged();

                unselectedList.remove(position);
                unselectedAdapter.getData(unselectedList);
                unselectedAdapter.notifyDataSetChanged();
            }
        });
    }

 ```



### 6.自启动管理和接收通知管理

逻辑实现：继承 BroadcastReceiver来接收系统广播，重写 onReceive() 方法，如果intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)为真，那么就加载一条新闻并在通知栏上显示。

 ```java
public class BootReceiver extends BroadcastReceiver implements Contract.NewsView {
    private Contract.NewsPresenter newsPresenter;
    private List<NewsBean.NewslistBean> news;

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)){
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
}
 ```



### 7.WiFi管理

逻辑实现：继承 BroadcastReceiver来接收系统广播，重写 onReceive() 方法，接收到的广播表示网络变化，即intent.getAction().equals(WifiManager.NETWORK_STATE_CHANGED_ACTION)，那么再判断这消息表示的WiFi状态。

 ````java
public class WifiStatusReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(WifiManager.NETWORK_STATE_CHANGED_ACTION)) {
            NetworkInfo info = intent.getParcelableExtra(WifiManager.EXTRA_NETWORK_INFO);
            if (info.getState().equals(NetworkInfo.State.DISCONNECTED)) {
                HttpUtils.setIsWifiConnected(false);
            } else if (info.getState().equals(NetworkInfo.State.CONNECTED)) {
                HttpUtils.setIsWifiConnected(true);
            } else {

            }
        }
    }
}
 ````



### 8.数据存储和清除

#### 8.1频道存储 

采用 LitePal 框架

实现逻辑：新建类继承自DataSupport，成员变量为频道的名称和标识符，解析频道列表的Json文件，遍历并存储频道。

 ```java
public static boolean handleChannels(Context context) {
    InputStreamReader inputStreamReader = null;
    try {
        inputStreamReader = new InputStreamReader(context.getAssets()
                .open("channel.json"));
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String line;
        StringBuilder stringBuffer = new StringBuilder();
        while ((line = bufferedReader.readLine()) != null) {
            stringBuffer.append(line);
        }
        bufferedReader.close();
        inputStreamReader.close();
        Gson gson = new Gson();
        List<ChannelBean> channelsList = gson.fromJson(stringBuffer.toString(),
                new TypeToken<List<ChannelBean>>(){}.getType());
        int i = 0;
        for (ChannelBean channelBean : channelsList) {
            Channels channels = new Channels();
            channels.setEn(channelBean.getEn());
            channels.setCn(channelBean.getCn());
            channels.setType(i);
            channels.save();
            i++;
        }
        return true;
    } catch (IOException e) {
        e.printStackTrace();
    }
    return false;
}
 ```



#### 8.2设置数据存储 

采用 SharedPreferences存储

实现逻辑：创建 SharedPreferences变量，赋值为 getSharedPreferences("SETTING",MODE_PRIVATE)，用 Editor 的 put 方法来存储数据，最后 apply() 即可。

```java
SharedPreferences sp =  getSharedPreferences("SETTING", MODE_PRIVATE);
SharedPreferences.Editor editor;
editor = sp.edit();
editor.putBoolean("WIFI_LOADING", isSwitchEnable);
editor.apply();
```



清除缓存：执行clear() 方法即可。

 ```
SharedPreferences.Editor editor;
p = getSharedPreferences("SETTING", MODE_PRIVATE);
editor = sp.edit();
editor.clear();
editor.apply();
 ```



