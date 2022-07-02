package com.example.yiapp.mine;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yiapp.R;
import com.example.yiapp.base.BaseActivity;
import com.example.yiapp.data.User;

import org.litepal.LitePal;


public class UserAboutActivity extends BaseActivity {

    private TextView username;
    int userId;

    View.OnClickListener btnListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.return_btn:
                    finish();
                    break;
                case R.id.btn_xgmm:
                    Intent intent2 = new Intent(UserAboutActivity.this, ChangePswActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("userid", userId);
                    intent2.putExtras(bundle);
                    startActivity(intent2);
                    break;
            }
        }
    };

    @Override
    protected void initViews() {
        Button returnbtn = find(R.id.return_btn);
        username=find(R.id.name);
        Button xgmmbtn = find(R.id.btn_xgmm);

        setUserAbout();

        returnbtn.setOnClickListener(btnListener);
        xgmmbtn.setOnClickListener(btnListener);
    }

    protected void setUserAbout() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        userId=bundle.getInt("userid");
        User user= LitePal.find(User.class, userId);
        username.setText(user.getUsername());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.my_user_about;
    }
}
