package com.cc.activity;

import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.cc.bean.App;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        alipay.setMonitor(Boolean.TRUE);
        appList.add(alipay);
        App wechat = new App();
        wechat.setName("微信");
        wechat.setMonitor(Boolean.FALSE);
        appList.add(wechat);
        listView = findViewById(R.id.app_list);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice, appList.stream().map(app->app.getName()).collect(Collectors.toList()).toArray(new String[0]));
        listView.setAdapter(adapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        listView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        for(App app: appList){
            if(app.getMonitor()){
                int position = appList.indexOf(app);
                listView.performItemClick(listView.getChildAt(position), position, listView.getItemIdAtPosition(position));
            }
        }
    }

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
        StringBuffer buffer = new StringBuffer("当前监控通知：");
        for (App app: appList){
            if(app.getMonitor()){
                buffer.append(app.getName());
            }
        }
        Toast.makeText(getApplicationContext(), buffer.substring(0), Toast.LENGTH_SHORT).show();
    }

}
