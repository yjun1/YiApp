package com.example.yiapp.mine;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.allen.library.SuperTextView;
import com.example.yiapp.LoginActivity;
import com.example.yiapp.R;
import com.example.yiapp.base.BaseFragment;

import es.dmoral.toasty.Toasty;


public class MineFragment extends BaseFragment {

    private TextView username;
    SuperTextView userabout;
    SuperTextView useraddress;
    SuperTextView appabout;
    Button btnout;
    String role;
    int userId;

    private void init() {
        username = find(R.id.user_name);
        userabout = find(R.id.user_about);
        useraddress = find(R.id.user_address);
        appabout = find(R.id.app_about);
        btnout = find(R.id.btn_out);
    }

    View.OnClickListener btnListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.user_about:
                    Intent intent1 = new Intent(getActivity(), UserAboutActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("userid", userId);
                    intent1.putExtras(bundle);
                    startActivity(intent1);
                    break;
                case R.id.user_address:
                    Intent intent2 = new Intent();
                    Bundle bundle2 = new Bundle();
                    if (role.equals("user")) {
                        bundle2.putInt("userid", userId);
                        intent2.putExtras(bundle2);
                        intent2.setClass(getContext(), UserAddressActivity.class);
                    } else {
                        intent2.setClass(getContext(), AdminServiceActivity.class);
                    }
                    startActivity(intent2);
                    break;
                case R.id.app_about:
                    Intent intent3 = new Intent(getActivity(), AppAboutActivity.class);
                    startActivity(intent3);
                    break;
                case R.id.btn_out:
                    Intent intent4 = new Intent(getActivity(), LoginActivity.class);
                    Toasty.success(getContext(), "退出成功!", Toast.LENGTH_SHORT, true).show();
                    startActivity(intent4);
                    getActivity().finish();
                    break;
            }
        }
    };

    @Override
    protected void initViews() {
        init();
        setView();
        userabout.setOnClickListener(btnListener);
        useraddress.setOnClickListener(btnListener);
        appabout.setOnClickListener(btnListener);
        btnout.setOnClickListener(btnListener);
    }

    protected void setView() {
        Intent intent = getActivity().getIntent();
        Bundle bundle = intent.getExtras();
        String name = bundle.getString("name");
        role = bundle.getString("role");
        userId=bundle.getInt("userid");
        username.setText(name);
        if (role.equals("user")) {
            useraddress.setLeftString("我的地址");
        } else {
            useraddress.setLeftString("所有服务");
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }
}
