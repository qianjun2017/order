package com.cc.service;

import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;

import com.cc.bean.App;

import java.util.List;

public class NotificationCollectorService extends NotificationListenerService {

    private List<App> appList;

    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        super.onNotificationPosted(sbn);
        if(appList==null || appList.isEmpty()){
            return;
        }
        Notification notification = sbn.getNotification();
        if(notification == null){
            return;
        }
        Bundle extras = notification.extras;
        if(extras!=null){
            String title = extras.getString(Notification.EXTRA_TITLE);
            String content = extras.getString(Notification.EXTRA_TEXT);
            String packageName = sbn.getPackageName();
            for(App app: appList){
                if(app.getPackageName().equalsIgnoreCase(packageName)){
                    break;
                }
            }
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.v("监听通知服务", "NotificationCollectorService正在onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }
}
