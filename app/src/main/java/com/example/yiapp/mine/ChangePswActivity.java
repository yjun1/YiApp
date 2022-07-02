package com.example.yiapp.mine;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.yiapp.R;
import com.example.yiapp.base.BaseActivity;
import com.example.yiapp.data.User;

import org.litepal.LitePal;

import es.dmoral.toasty.Toasty;

public class ChangePswActivity extends BaseActivity {

    EditText psw1;
    EditText psw2;
    EditText psw3;
    User user;
    int userId;

    private void init() {
        psw1 = find(R.id.psw1);
        psw2 = find(R.id.psw2);
        psw3 = find(R.id.psw3);
    }

    @Override
    protected void initViews() {
        init();
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        userId = bundle.getInt("userid");
        user = LitePal.find(User.class, userId);
    }

    public void onBack(View view) {
        finish();
    }

    public void onSave(View view) {
        String old = psw1.getText().toString();
        String new1 = psw2.getText().toString();
        String new2 = psw3.getText().toString();
        if (old.equals(user.getPassword())) {
            if (new1.equals(new2)) {
                if (new1.equals("")) {
                    Toasty.error(this, "密码不能为空！", Toast.LENGTH_SHORT, true).show();
                } else {
                    User user1 = new User();
                    user1.setPassword(new1);
                    user1.update(userId);
                    Toasty.success(this, "密码修改成功！", Toast.LENGTH_SHORT, true).show();
                    finish();
                }
            } else
                Toasty.error(this, "两次密码不同！", Toast.LENGTH_SHORT, true).show();
        } else {
            Toasty.error(this, "原始密码错误！", Toast.LENGTH_SHORT, true).show();
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.my_change_psw;
    }
}
