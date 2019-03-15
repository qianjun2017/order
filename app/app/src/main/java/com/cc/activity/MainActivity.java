package com.cc.activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.NotificationManagerCompat;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.ListView;

import com.cc.bean.App;
import com.cc.service.NotificationCollectorService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class MainActivity extends TopBarBaseActivity {

    private ListView listView;

    private List<App> appList;

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void init(Bundle saveInstanceState) {
        setTitle("设置");
        appList = fetch();
        if(appList==null) {
            appList = new ArrayList<>();
            App alipay = new App();
            alipay.setName("支付宝");
            alipay.setMonitor(Boolean.FALSE);
            appList.add(alipay);
            App wechat = new App();
            wechat.setName("微信");
            wechat.setMonitor(Boolean.FALSE);
            appList.add(wechat);
        }
        listView = findViewById(R.id.app_list);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.app_list_item_multiple_choice, appList.stream().map(app->app.getName()).collect(Collectors.toList()).toArray(new String[0])){
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                CheckedTextView checkedTextView = (CheckedTextView)super.getView(position, convertView, parent);
                return checkedTextView;
            }
        };
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        for(App app: appList){
            if(app.getMonitor()){
                int position = appList.indexOf(app);
                listView.performItemClick(listView.getChildAt(position), position, listView.getItemIdAtPosition(position));
            }
        }
    }

    /**
     * 保存
     * @param view
     */
    public void save(View view){
        SparseBooleanArray checkedItemIds = listView.getCheckedItemPositions();
        for(int i = 0; i < checkedItemIds.size(); i ++){
            int key = checkedItemIds.keyAt(i);
            App app = appList.get(key);
            Boolean value = checkedItemIds.valueAt(i);
            if(value){
                app.setMonitor(Boolean.TRUE);
            }else{
                app.setMonitor(Boolean.FALSE);
            }
        }
        store(appList);
        if(!isNotificationListenerEnabled(this)){
            openNotificationListenSettings();
        }
        toggleNotificationListenerService();
    }

    /**
     * 检测通知监听服务是否被授权
     * @param context
     * @return
     */
    public boolean isNotificationListenerEnabled(Context context) {
        Set<String> packageNames = NotificationManagerCompat.getEnabledListenerPackages(this);
        if (packageNames.contains(context.getPackageName())) {
            return true;
        }
        return false;
    }

    /**
     * 打开通知监听设置页面
     */
    public void openNotificationListenSettings() {
        try {
            startActivity(new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 把应用的NotificationListenerService实现类disable再enable，即可触发系统rebind操作
     */
    private void toggleNotificationListenerService() {
        PackageManager pm = getPackageManager();
        pm.setComponentEnabledSetting(
                new ComponentName(this, NotificationCollectorService.class),
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);

        pm.setComponentEnabledSetting(
                new ComponentName(this, NotificationCollectorService.class),
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);
    }

    /**
     * 存储app设置到本地
     * @param appList
     */
    public void store(List<App> appList){
        Gson gson = new Gson();
        FileOutputStream fos = null;
        try {
            fos = openFileOutput("apps", MODE_PRIVATE);
            fos.write(gson.toJson(appList).getBytes());
            fos.flush();
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if(fos!=null){
                try {
                    fos.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }

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
