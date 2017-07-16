package com.example.eight.scannews.utils;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import org.litepal.crud.DataSupport;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by eight on 2017/6/11.
 */

public class ChannelsUtils {


    /**
     * en : social
     * cn : 社会
     */

    public class ChannelBean {
        @SerializedName("en")
        private String en;
        @SerializedName("cn")
        private String cn;

        public String getEn() {
            return en;
        }

        public void setEn(String en) {
            this.en = en;
        }

        public String getCn() {
            return cn;
        }

        public void setCn(String cn) {
            this.cn = cn;
        }
    }


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
            //ChannelBean channelBean = gson.fromJson(stringBuffer.toString(),
            //        ChannelBean.class);
            int i = 0;
            for (ChannelBean channelBean : channelsList) {
                Channels channels = new Channels();
                channels.setEn(channelBean.getEn());
                channels.setCn(channelBean.getCn());
                channels.setType(i);
                channels.save();
                i++;
                Log.i("------>", "handleChannels: " + channelBean.getCn() + channelBean.getEn());
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static List<String> setupTab(String nameType) {
        List<Channels> channelsList = DataSupport.findAll(Channels.class);
        List<String> newsTab = new ArrayList<>();
        switch (nameType) {
            case "en":
                for (Channels c : channelsList) {
                    newsTab.add(c.getEn());
                }
                return newsTab;
            case "cn":
                for (Channels c : channelsList) {
                    newsTab.add(c.getCn());
                }
                return newsTab;
            default:
                return null;
        }
    }

}
