package com.example.eight.scannews.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;

/**
 * Created by eight on 2017/9/21.
 */

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
