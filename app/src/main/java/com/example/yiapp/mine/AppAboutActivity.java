package com.example.yiapp.mine;

import android.view.View;
import android.widget.TextView;

import com.example.yiapp.R;
import com.example.yiapp.base.BaseActivity;

public class AppAboutActivity extends BaseActivity {

    @Override
    protected void initViews() {

    }

    //返回键功能
    public void onReturn(View v) {
        finish();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.my_app_about;
    }
}
