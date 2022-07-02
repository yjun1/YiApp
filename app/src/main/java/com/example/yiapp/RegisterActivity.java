package com.example.yiapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.yiapp.data.User;

import org.litepal.LitePal;

import java.util.List;

import es.dmoral.toasty.Toasty;

public class RegisterActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
    }


    public void fanhui(View v) {
        finish();
    }

    // 注册按钮监听事件
    public void register(View v) {
        String name = username.getText().toString().trim();
        String pwd = password.getText().toString().trim();
        if (name.equals("") || pwd.equals("")) {
            errorMsg(this, "错误信息", "用户名和密码均不能为空！");
        } else {
            //查找输入的用户名是否已存在
            List<User> users = LitePal.where("username=?", name).find(User.class);
            if (users.size() != 0) {
                errorMsg(this, "错误信息", "该用户名已存在");
                username.setText("");
                password.setText("");
            } else {
                User user = new User();
                user.setUsername(name);
                user.setPassword(pwd);
                user.setRole("user");
                user.save();    //给用户表添加一条数据
                Toasty.success(this, "注册成功！即将跳转回登录页面...",
                        Toast.LENGTH_SHORT, true).show();
                //延迟2秒跳转
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(2000);
                            finish();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
            }
        }
    }

    // 错误消息对话框
    public void errorMsg(Context context, String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("确定", null);
        builder.create();
        builder.show();
    }
}
