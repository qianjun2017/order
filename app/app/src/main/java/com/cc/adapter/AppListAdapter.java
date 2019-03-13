package com.cc.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.cc.activity.R;
import com.cc.bean.App;

import java.util.List;

public class AppListAdapter extends BaseAdapter {

    private List<App> appList;

    private Context context;

    private int resource;

    /**
     * 构造函数
     * @param context
     * @param appList
     * @param resource
     */
    public AppListAdapter(Context context, List<App> appList, int resource){
        this.context = context;
        this.appList = appList;
        this.resource = resource;
    }

    @Override
    public int getCount() {
        return appList.size();
    }

    @Override
    public Object getItem(int position) {
        return appList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        final App app = appList.get(position);
        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(resource, null);
            holder = new ViewHolder();
            holder.appNameTv = convertView.findViewById(R.id.app_name);
            holder.monitorCb = convertView.findViewById(R.id.monitor);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();
        }
        holder.appNameTv.setText(app.getName());
        holder.monitorCb.setChecked(app.getMonitor());
        return convertView;
    }

    static class ViewHolder {
        TextView appNameTv;
        CheckBox monitorCb;
    }
}
