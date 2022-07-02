package com.example.yiapp.base;


import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.IdRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initViews();
    }

    protected abstract void initViews();
    protected abstract int getLayoutId();

    protected <T extends View> T find(@IdRes int id){
        return findViewById(id);
    }

}
