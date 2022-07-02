package com.example.yiapp;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.yiapp.base.BaseActivity;
import com.example.yiapp.gauge.GaugeFragment;
import com.example.yiapp.need.NeedFragment;
import com.example.yiapp.service.ServiceFragment;
import com.example.yiapp.mine.MineFragment;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private Fragment[] fragments;
    private int lastFragmentIndex = 0;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }


    @Override
    protected void initViews() {
        BottomNavigationView bottomNavigationView = find(R.id.main_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        fragments = new Fragment[]{
                new GaugeFragment(),
                new NeedFragment(),
                new ServiceFragment(),
                new MineFragment()};

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.main_frame, fragments[0])
                .commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        menuItem.setChecked(true);  //令点击的菜单为选中状态，从而图片和字体颜色为设置的选中颜色
        switch (menuItem.getItemId()) {     //根据选中菜单的id进行Fragment页面的跳转
            case R.id.gauge:
                GaugeFragment gf = new GaugeFragment();
                replaceFragment(gf, 0);
                break;
            case R.id.need:
                NeedFragment nf = new NeedFragment();
                replaceFragment(nf, 1);
                break;
            case R.id.service:
                ServiceFragment sf = new ServiceFragment();
                replaceFragment(sf, 2);
                break;
            case R.id.mine:
                MineFragment mf = new MineFragment();
                replaceFragment(mf, 3);
                break;
        }
        return false;
    }
    // 动态切换fragment函数
    private void replaceFragment(Fragment fragment, int to) {
        if (lastFragmentIndex == to) { //如果点击的按钮未发生变化则无需进行操作
            return;
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transation = fragmentManager.beginTransaction();
        if (!fragment.isAdded()) {
            transation.replace(R.id.main_frame, fragment);  //用新的Fragment替换已有的Fragment
        } else {
            transation.show(fragment); //直接显示新的Fragment
        }
        transation.hide(fragments[lastFragmentIndex]).commitAllowingStateLoss(); //隐藏之前的Fragment
        lastFragmentIndex = to;
    }
}