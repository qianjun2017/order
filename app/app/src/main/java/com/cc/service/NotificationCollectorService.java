package com.cc.service;

import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;

import com.cc.bean.App;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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
            Log.v("监控服务", "通知包："+packageName+", 通知标题："+title+", 通知内容："+content);
            for(App app: appList){
                if(app.getPackageName().equalsIgnoreCase(packageName)){
                    Log.v("监控服务", "通知标题："+title+", 通知内容："+content);
                    break;
                }
            }
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        appList = fetch();
        Log.v("监听通知服务", "NotificationCollectorService正在onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    /**
     * 获取本地存储的app设置
     * @return
     */
    public List<App> fetch(){
        FileInputStream fis=null;
        byte[] buffer=null;
        try {
            fis=openFileInput("apps");
            buffer=new  byte[fis.available()];
            fis.read(buffer);
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if(fis!=null){
                try {
                    fis.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        if(buffer==null){
            return null;
        }
        Gson gson = new Gson();
        List<App> appList = gson.fromJson(new String(buffer), new TypeToken<List<App>>(){}.getType());
        return appList;
    }
}
