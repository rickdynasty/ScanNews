- [获取新闻标题](https://github.com/iMeiji/Toutiao/wiki/%E4%BB%8A%E6%97%A5%E5%A4%B4%E6%9D%A1Api%E5%88%86%E6%9E%90#%E8%8E%B7%E5%8F%96%E6%96%B0%E9%97%BB%E6%A0%87%E9%A2%98)
  - [普通用法](https://github.com/iMeiji/Toutiao/wiki/%E4%BB%8A%E6%97%A5%E5%A4%B4%E6%9D%A1Api%E5%88%86%E6%9E%90#%E6%99%AE%E9%80%9A%E7%94%A8%E6%B3%95)
  - [高级用法](https://github.com/iMeiji/Toutiao/wiki/%E4%BB%8A%E6%97%A5%E5%A4%B4%E6%9D%A1Api%E5%88%86%E6%9E%90#%E9%AB%98%E7%BA%A7%E7%94%A8%E6%B3%95)
- [获取新闻](https://github.com/iMeiji/Toutiao/wiki/%E4%BB%8A%E6%97%A5%E5%A4%B4%E6%9D%A1Api%E5%88%86%E6%9E%90#%E8%8E%B7%E5%8F%96%E6%96%B0%E9%97%BB)
 - [获取新闻内容](https://github.com/iMeiji/Toutiao/wiki/%E4%BB%8A%E6%97%A5%E5%A4%B4%E6%9D%A1Api%E5%88%86%E6%9E%90#%E8%8E%B7%E5%8F%96%E6%96%B0%E9%97%BB%E5%86%85%E5%AE%B9)
 - [获取新闻评论](https://github.com/iMeiji/Toutiao/wiki/%E4%BB%8A%E6%97%A5%E5%A4%B4%E6%9D%A1Api%E5%88%86%E6%9E%90#%E8%8E%B7%E5%8F%96%E6%96%B0%E9%97%BB%E8%AF%84%E8%AE%BA)
- [获取头条号文章](https://github.com/iMeiji/Toutiao/wiki/%E4%BB%8A%E6%97%A5%E5%A4%B4%E6%9D%A1Api%E5%88%86%E6%9E%90#%E8%8E%B7%E5%8F%96%E5%A4%B4%E6%9D%A1%E5%8F%B7%E6%96%87%E7%AB%A0)
- [获取段子](https://github.com/iMeiji/Toutiao/wiki/%E4%BB%8A%E6%97%A5%E5%A4%B4%E6%9D%A1Api%E5%88%86%E6%9E%90#%E8%8E%B7%E5%8F%96%E6%AE%B5%E5%AD%90)
 - [获取段子内容](https://github.com/iMeiji/Toutiao/wiki/%E4%BB%8A%E6%97%A5%E5%A4%B4%E6%9D%A1Api%E5%88%86%E6%9E%90#%E8%8E%B7%E5%8F%96%E6%AE%B5%E5%AD%90%E5%86%85%E5%AE%B9)
 - [获取段子评论](https://github.com/iMeiji/Toutiao/wiki/%E4%BB%8A%E6%97%A5%E5%A4%B4%E6%9D%A1Api%E5%88%86%E6%9E%90#%E8%8E%B7%E5%8F%96%E6%AE%B5%E5%AD%90%E8%AF%84%E8%AE%BA)

## 获取新闻标题
### 普通用法
```
http://toutiao.com/api/article/recent/?source=2&category=类型&as=A1D5D87595C3287
```
例如获取热点新闻  
```
http://toutiao.com/api/article/recent/?source=2&category=news_hot&as=A1D5D87595C3287
```
其中 `category` 指类型 `as` 值随机生成 感兴趣的自己去抓取    
得到的信息为
```
{
  "has_more": true,
  "message": "success",
  "data": [
    {
      "media_name": "侠客岛",
      "abstract": "今天又有大新闻啦。据美国多家媒体的消息， 12月15日，中国海军在南海趁美国一艘无人水下潜航器（UUV）故障之机，抢在美军打捞人员之前将其捞走。",
      "impression_count": 7433280,
      "image_list": [
        {
          "url": "http://p1.pstatp.com/list/134b00048dd993d77429"
        },
        {
          "url": "http://p3.pstatp.com/list/1349000017bb1b3c7366"
        },
        {
          "url": "http://p3.pstatp.com/list/134c0003788b24d74346"
        }
      ],
      "media_avatar_url": "http://p3.pstatp.com/large/1731/4112623237",
      "external_visit_count": 14999,
      "article_type": 0,
      "more_mode": true,
      "tag": "news_military",
      "is_favorite": 0,
      "has_m3u8_video": 0,
      "keywords": "南海诸岛,南海,美国,专属经济区,公约,国际法",
      "has_mp4_video": 0,
      "favorite_count": 1059,
      "display_url": "http://toutiao.com/group/6365056560833659137/",
      "article_sub_type": 0,
      "bury_count": 9,
      "title": "美国想在南海下黑手，结果被中国抓个正着！这下送礼了",
      "datetime": "2016-12-17 22:27",
      "has_video": false,
      "share_url": "http://toutiao.com/group/6365056560833659137/?iid=41507200127&app=news_article",
      "id": 6365056560833659000,
      "source": "侠客岛",
      "comment_count": 17,
      "article_url": "http://toutiao.com/group/6365056560833659137/",
      "create_time": 1481980801,
      "recommend": 0,
      "tips": 0,
      "aggr_type": 1,
      "item_source_url": "/item/6365059050404577794/",
      "media_url": "http://toutiao.com/m3741758306/",
      "display_time": 1481980795,
      "publish_time": 1481980795,
      "go_detail_count": 504591,
      "group_flags": 2,
      "middle_mode": false,
      "display_title": " ",
      "gallary_image_count": 3,
      "item_seo_url": "/item/6365059050404577794/",
      "tag_id": 6365056560833659000,
      "source_url": "/group/6365056560833659137/",
      "article_genre": "article",
      "large_mode": false,
      "item_id": 6365059050404578000,
      "natant_level": 0,
      "is_digg": 0,
      "seo_url": "/group/6365056560833659137/",
      "repin_count": 1059,
      "url": "http://toutiao.com/group/6365056560833659137/",
      "level": 0,
      "digg_count": 55,
      "behot_time": 1481984828,
      "hot": 0,
      "preload_web": 2,
      "comments_count": 17,
      "has_image": true,
      "is_bury": 0,
      "group_id": 6365056560833659000,
      "middle_image": {
        "url": "http://p1.pstatp.com/list/134b00048dd993d77429",
        "width": 415,
        "url_list": [
          {
            "url": "http://p1.pstatp.com/list/134b00048dd993d77429"
          },
          {
            "url": "http://pb3.pstatp.com/list/134b00048dd993d77429"
          },
          {
            "url": "http://pb3.pstatp.com/list/134b00048dd993d77429"
          }
        ],
        "uri": "list/134b00048dd993d77429",
        "height": 536
      }
    }
  ],
  "next": {
    "max_behot_time": 1481984828
  }
}
```
我们要获取的新闻都在 `data` 数组里, 这里我只罗列了部分  

以下给出部分类型 仅供参考  
```
'推荐': '__all__',
'热点': 'news_hot',
'社会': 'news_society',
'娱乐': 'news_entertainment',
'科技': 'news_tech',
'军事': 'news_military',
'体育': 'news_sports'
'汽车': 'news_car',
'财经': 'news_finance',
'国际': 'news_world',
'时尚': 'news_fashion',
'旅游': 'news_travel',
'探索': 'news_discovery',
'育儿': 'news_baby',
'养生': 'news_regimen',
'故事': 'news_story',
'美文': 'news_essay',
'游戏': 'news_game',
'历史': 'news_history',
'美食': 'news_food',
```

### 高级用法
```
http://toutiao.com/api/article/recent/?source=2&category=news_hot&as=A105177907376A5&cp=5797C7865AD54E1&count=20&offset=0&_=1481986412
```
-  `category` 指类型  
- `as` 随机生成  
- `cp` 随机生成  
- `count` 获取文章数量  
- `offset` 偏移量  
- `_=` 指 `max_behot_time` 

补充一个Api, 从手机版获取到的
```
http://m.toutiao.com/list/?tag=__all__&ac=wap&count=20&format=json_raw&as=A17538D54D106FF&cp=585DF0A65F0F1E1&min_behot_time=1482491618
```

## 获取新闻
### 获取新闻内容
```
http://m.toutiao.com/item_seo_url值/info/
```
例如获取 [美国无人潜航器被中国抓获引爆网络](http://www.toutiao.com/i6364969235889783298/) 这篇新闻的内容  
```
http://m.toutiao.com/i6364969235889783298/info/
```
其中 `i6364969235889783298` 值为 `item_seo_url` 可从上面的Api获取  
得到的信息为   
```
{
    "_ck": {},
    "data": {
        "detail_source": "诤闻军事",
        "media_user": {
            "avatar_url": "http://p2.pstatp.com/large/11543/4356023632",
            "id": 5866716857,
            "screen_name": "诤闻军事"
        },
        "publish_time": 1481959884,
        "title": "美国无人潜航器被中国抓获引爆网络，一中国网友外媒舌战群儒",
        "url": "http://toutiao.com/group/6364965628189327618/",
        "is_original": true,
        "is_pgc_article": true,
        "content": "<div><p>据外媒报道，12月15日中国海军一艘南救510打捞救生船，在靠近菲律宾的海域持续跟踪美军海洋测量船鲍迪奇号之后，当着美军的面，将其放出的两艘水下无人航行器之一打捞带走。目前美方已经向中国提起抗议，要求归还无人航行器。<strong>据CNN报道，当时美军鲍迪奇号立刻使用无线电呼叫中国舰艇，但是却没有应答，过了很久之后中国军舰突然回应：有什么事要帮忙吗？我们正在附近巡逻。</strong></p><p><img src=\"http://p3.pstatp.com/large/12db0007c12ad2732220\" img_width=\"938\" img_height=\"732\" alt=\"美国无人潜航器被中国抓获引爆网络，一中国网友外媒舌战群儒\" onerror=\"javascript:errorimg.call(this);\"></p><p class=\"pgc-img-caption\">鲍迪奇号</p><p>类似《华尔街日报》、CNN等媒体，对这一事件都冠以“Breaking News”（重大新闻）的标题，迅速进行了报道，因此也引发了国外网友的讨论。特别有趣的是，在《华尔街日报》网站上，一位网友“<strong>adam w”却独自在外媒网站和拥护美国的外国网友进行了一场激烈的辩论</strong>。从名字和用语来开，这位网友很可能来自中国。</p><p>例如网名“Bryan Jaeck”用户认为，如果中国不归还无人航行器，过一段时间中国就能山寨所有的美国技术来建造自己的无人航行器。美国舰长应该把手放到扳机上发出警告：要么归还要么就去死！“adam w”则回答说：一名间谍被抓获了，现在暴徒说了：如果不交出间谍他们就开战！</p><p>网名“Christopher Holland”表示，中国的法律认为：我的是我的，你的也是我的。这就是中国人的想法。偷窃美国政府财产对于中国人来说小菜一碟。<strong>“adam w”则回答说：美国拥有世界上最庞大的间谍网络，不断从中国、俄罗斯窃取机密，甚至连德国、法国这样的盟友都不放过</strong>。</p><p><img src=\"http://p3.pstatp.com/large/134d0000fb0668a5844f\" img_width=\"634\" img_height=\"439\" alt=\"美国无人潜航器被中国抓获引爆网络，一中国网友外媒舌战群儒\" onerror=\"javascript:errorimg.call(this);\"></p><p class=\"pgc-img-caption\">无人潜航器</p><p>网名“Timothy corrigan”称：下次中国如果还要这么干，美国就应该把他们都赶出这片水域。应该让附近的潜艇发射战斧导弹到中国的岛礁上去“参观”。中国已经看穿了奥巴马是一个弱者，特朗普应该给中国一个教训。“adam w”回答说：<strong>美国人当着中国舰艇的面释放间谍无人器。中国抓获了它，美国却要以此为借口发动战争。美国暴徒终于显露了自己的本色</strong>。</p><p>“Timothy corrigan”回应说：国际海域意味着你不能碰其他人的东西。中国的所作所为是盗窃，应该被严厉惩罚。<strong>美国应该击沉中国舰艇，派遣两个航母编队到南海，如果不够就派三个</strong>。“adam w”则回答；<strong>真的吗？美国就没有在国际海域抓获其他人的舰艇吗（应该是意指银河号事件）？</strong>在你谴责其他人搞间谍的时候，其他国家的所有计算机又在被谁监控？美国CIA一直在干这事，你这个伪君子。</p><p>网名“michael de martine”认为。为什么美国海军会派遣一艘小型没有武装的测量船到“敌军海域”？这不是成心让中国这个坏人来来搞事吗？“adam w”回应说：派没有武装的测量船到“敌军海域”，还来到中国舰艇的旁边。这件事美国才是事实上的挑衅者。</p><p><img src=\"http://p1.pstatp.com/large/134c0002f256279ce44d\" img_width=\"634\" img_height=\"444\" alt=\"美国无人潜航器被中国抓获引爆网络，一中国网友外媒舌战群儒\" onerror=\"javascript:errorimg.call(this);\"></p><p class=\"pgc-img-caption\">特朗普的某些举动激怒了中国<br></p><p>网友“WILLIAM A TAYLOR”说，这并不是“敌军海域”而是国际公海。而中国声称对这片国际海域拥有主权。美国有义务保持这一海域的航行自由，但中国去想把其他人排除在外。权利如果没有武力维护就没有任何意义，这就是为什么那么多人要带枪。“adam w”则问道：义务？到底是什么义务？菲律宾已经告诉美国离开他们的海域，不想当美国人对抗中国的马前卒。所以现在是美国打算单枪匹马挑衅中国。到底谁才是恶人？</p><p>除了那些为美国鼓噪的人之外，其实还是有清醒者。一位Gioklie Tanthe说到：<strong>美国必须懂得尊重其他国家，才能够得到其他国家的尊重。</strong></p></div><p class=\"footnote\">本文为头条号作者原创，未经授权，不得转载。</p>",
        "source": "诤闻军事",
        "video_play_count": 6
    },
    "success": true
}
```


### 获取新闻评论
```
http://www.toutiao.com/api/comment/list/?group_id=头条号&item_id=文章号&offset=偏移量&count=数量
```
例如获取 [美国无人潜航器被中国抓获引爆网络](http://www.toutiao.com/i6364969235889783298/) 这篇新闻的评论

```
http://www.toutiao.com/api/comment/list/?group_id=6364965628189327618&item_id=6364969235889783298&offset=0&count=10
```
其中 `group_id` 和 `item_id` 可从上面的Api获取  
得到的信息为  
```
{
    "message": "success",
    "data": {
        "has_more": true,
        "total": 2410,
        "comments": [
            {
                "text": "中国是一个负责任的大国，只要美方提供证据证明这东西是美国的，中国可以归还。流程是:美方提供生产厂家原始记录、生产编号，该型号潜航器的设计图纸，以及该次海洋调查任务的详细说明、行动计划等等。中方需要半年左右的调查就可以了。",
                "digg_count": 4351,
                "reply_data": {
                    "reply_list": []
                },
                "reply_count": 176,
                "create_time": 1481964505,
                "user": {
                    "avatar_url": "http://p0.pstatp.com/origin/3793/3131589739",
                    "user_id": 6180186929,
                    "name": "hanxinping1969"
                },
                "dongtai_id": 7105200091,
                "user_digg": 0,
                "id": 52960355361
            }
        ]
    }
}
```

## 获取头条号文章
```
http://www.toutiao.com/pgc/ma/?media_id=头条号&page_type=1&count=10&version=2&platform=pc&as=A1C548D5FDB17E6&cp=585DB1871ED64E1&max_behot_time=偏移量
```
例如获取 [国际在线](http://www.toutiao.com/m3232597584/) 的文章  
```
http://www.toutiao.com/pgc/ma/?media_id=3232597584&page_type=1&max_behot_time=0&count=10&version=2&platform=pc&as=A1C548D5FDB17E6&cp=585DB1871ED64E1
```
其中 `media_id` 可从上面的Api获取  
得到的信息为  
```
{
    "media_id": 3232597584,
    "has_more": 1,
    "next": {
        "max_behot_time": 1482474718
    },
    "page_type": 1,
    "message": "success",
    "data": [
        {
            "play_effective_count": "0",
            "gallery_pic_count": 0,
            "go_detail_count": "62",
            "title": "【老外谈】巴基斯坦专家：中央经济工作会议为2017年中国发展奠定基础",
            "image_list": [
                {
                    "url": "http://p2.pstatp.com/list/13c5000a0fe801679b19",
                    "pc_url": "http://p2.pstatp.com/list/194x108/13c5000a0fe801679b19"
                }
            ],
            "abstract": "莎菲·哈里（巴基斯坦）一年一度的中央经济工作会议日前在北京召开。与会的领导人们总结中国在2016年的经济工作，并着眼于2017年的经济战略。",
            "show_play_effective_count": 0,
            "middle_mode": true,
            "has_video": false,
            "source_url": "http://www.toutiao.com/item/6367207970920464898/",
            "datetime": "2016-12-23 16:06",
            "live_status": "",
            "more_mode": false,
            "comments_count": "0",
            "video_duration_str": "00:00",
            "has_gallery": false,
            "pc_image_url": "http://p2.pstatp.com/list/126x82/13c5000a0fe801679b19"
        }
    ]
}
```

## 获取段子
## 获取段子内容
```
http://www.toutiao.com/api/article/feed/?category=essay_joke&as=A115C8457F69B85&cp=585F294B8845EE1
```
得到的信息为  
```
{
  "has_more": true,
  "message": "success",
  "data": [
    {
      "group": {
        "text": "地铁上，一个怂孩子在那一直打他妈妈的手。然后他妈妈把手放在了小孩儿的脸上，怂孩子又使劲要打过去，结果他妈妈突然把手给撤开了。就听啪的一声然后就没有然后了。",
        "create_time": 1483004786,
        "id": 53368972910,
        "favorite_count": 78,
        "go_detail_count": 43711,
        "user_favorite": 0,
        "share_type": 1,
        "is_can_share": 1,
        "comment_count": 81,
        "share_url": "http://m.neihanshequ.com/share/group/53368972910/?iid=0&amp;app=joke_essay",
        "label": 1,
        "content": "地铁上，一个怂孩子在那一直打他妈妈的手。然后他妈妈把手放在了小孩儿的脸上，怂孩子又使劲要打过去，结果他妈妈突然把手给撤开了。就听啪的一声然后就没有然后了。",
        "category_type": 1,
        "id_str": "53368972910",
        "media_type": 0,
        "share_count": 119,
        "type": 3,
        "status": 112,
        "has_comments": 1,
        "user_bury": 0,
        "status_desc": "热门投稿",
        "user": {
          "is_following": false,
          "avatar_url": "http://p3.pstatp.com/thumb/1353000081efff334e3e",
          "user_id": 6088312852,
          "name": "蓝筱蝶",
          "user_verified": false
        },
        "user_digg": 0,
        "online_time": 1483004786,
        "category_name": "内涵段子",
        "category_visible": false,
        "bury_count": 248,
        "is_anonymous": false,
        "repin_count": 78,
        "digg_count": 7537,
        "has_hot_comments": 1,
        "user_repin": 0,
        "activity": {},
        "group_id": 53368972910,
        "category_id": 1
      },
      "comments": [
        {
          "status": 5,
          "user_profile_url": "",
          "is_digg": 0,
          "user_id": 3126306787,
          "description": "",
          "bury_count": 0,
          "avatar_url": "http://p3.pstatp.com/thumb/1354000a27104141c471",
          "text": "这个故事，告诉了我们，不要坐地铁",
          "comment_id": 53372167364,
          "user_verified": false,
          "digg_count": 11343,
          "user_profile_image_url": "http://p3.pstatp.com/thumb/1354000a27104141c471",
          "platform_id": "feifei",
          "platform": "feifei",
          "create_time": 1483011406,
          "user_digg": 0,
          "group_id": 53368972910,
          "user_name": "哈哈1个大开心",
          "id": 53372167364,
          "user_bury": 0
        }
      ],
      "type": 1,
      "display_time": 1483101547,
      "online_time": 1483101547
    }
  ],
  "next": {
    "max_behot_time": 1483101547
  }
}
```

### 获取段子评论
```
http://www.toutiao.com/group/段子ID/comments/?count=总数&offset=偏移量&item_id=0&format=json
```
例如  
```
http://www.toutiao.com/group/53404620593/comments/?count=20&offset=0&item_id=0&format=json
```
其中 `段子ID` 可从上面的Api获取  
得到的信息为  
```
{
    "message": "success",
    "data": {
        "comment_pagination": {
            "count": 1,
            "total_count": 75,
            "url_fmt": "?offset=%s&amp;count=%s",
            "offset": 0
        },
        "group": {
            "status": 1,
            "type": 3,
            "id": 53404620593,
            "_id": 892139876
        },
        "comments": [
            {
                "status": 1,
                "user_uid": null,
                "description": "这个用户很懒，神马都木有写",
                "reply_to_user_name": null,
                "reply_to_user_verified": null,
                "text": "抽烟害人害己",
                "target_id": null,
                "forum_id": 0,
                "reply_count": 1,
                "reply_to_user_id": null,
                "is_blocked": 0,
                "user_verified": false,
                "user_followers_count": 0,
                "is_blocking": 0,
                "user_id": 5874570453,
                "bury_count": 0,
                "type": 2,
                "digg_count": 405,
                "id": 53409568162,
                "platform_id": 0,
                "rate_score": 0,
                "create_time": 1483093293,
                "user_name": "今天回家吃晚饭",
                "user_profile_image_url": "http://p9.pstatp.com/thumb/bc3000463d913c1744c"
            }
        ],
        "page_display_range": [
            1,
            2,
            3,
            4,
            5,
            6,
            7
        ],
        "page": 1,
        "page_size": 1,
        "page_last": 75
    }
}
```
