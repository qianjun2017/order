package com.cc.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;

public abstract class TopBarBaseActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private FrameLayout viewContent;
    private TextView mTitle;

    private int menuId;
    private String menuStr;

    private OnClickListener onClickListenerTopLeft;   //左边图标的点击事件
    private OnClickListener onClickListenerTopRight;

    //定义接口
    public interface  OnClickListener {
        void onClick();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_bar_base);

        toolbar = findViewById(R.id.toolbar);
        viewContent = findViewById(R.id.view_content);
        mTitle = findViewById(R.id.tv_title);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        LayoutInflater.from(this).inflate(getContentView(), viewContent);
        init(savedInstanceState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (menuId != 0 || !TextUtils.isEmpty(menuStr)) {
            getMenuInflater().inflate(R.menu.menu_activity_base_top_bar,menu);
        }
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (menuId != 0) {
            menu.findItem(R.id.menu_1).setIcon(menuId);
        }

        if (!TextUtils.isEmpty(menuStr)) {
            menu.findItem(R.id.menu_1).setTitle(menuStr);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onClickListenerTopLeft.onClick();
        }else if(item.getItemId() == R.id.menu_1) {
            onClickListenerTopRight.onClick();
        }
        return true;
    }

    protected void setTopRightButton(String menuStr, OnClickListener rightListener){
        this.onClickListenerTopRight = rightListener;
        this.menuStr = menuStr;
    }

    protected void setTopRightButton(String menuStr,int menuId, OnClickListener rightListener){
        this.menuStr = menuStr;
        this.menuId = menuId;
        this.onClickListenerTopRight = rightListener;
    }

    protected void setTopLeftButton(int iconId,OnClickListener listener) {
        toolbar.setNavigationIcon(iconId);
        this.onClickListenerTopLeft = listener;
    }

    protected void setTitle(String title){
        if(!TextUtils.isEmpty(title)){
            mTitle.setText(title);
        }
    }

    protected abstract int getContentView();

    protected abstract  void init(Bundle saveInstanceState);
}
