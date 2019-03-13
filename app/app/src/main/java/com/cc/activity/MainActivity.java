package com.cc.activity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.cc.adapter.AppListAdapter;
import com.cc.bean.App;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends TopBarBaseActivity {

    private ListView listView;

    private List<App> appList = new ArrayList<>();

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void init(Bundle saveInstanceState) {
        setTitle("设置");
        appList.clear();
        App alipay = new App();
        alipay.setName("支付宝");
        alipay.setMonitor(Boolean.FALSE);
        appList.add(alipay);
        App wechat = new App();
        wechat.setName("微信");
        wechat.setMonitor(Boolean.FALSE);
        appList.add(wechat);
        listView = findViewById(R.id.app_list);
        AppListAdapter appListAdapter = new AppListAdapter(MainActivity.this, appList, R.layout.app_list_item);
        listView.setAdapter(appListAdapter);
    }

}
