package com.example.yiapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.yiapp.data.Address;
import com.example.yiapp.data.User;

import org.litepal.LitePal;

import java.util.List;

import es.dmoral.toasty.Toasty;

public class LoginActivity extends AppCompatActivity {

    private EditText userid;
    private EditText userpaw;

    //创建数据库
    protected void createDB() {
        LitePal.getDatabase();
        User user = new User();
        user.setUsername("LYJ");
        user.setPassword("123");
        user.setRole("admin");
        user.save();
//        User user1 = new User();
//        user1.setUsername("lyj");
//        user1.setPassword("456");
//        user1.setRole("user");
//        user1.save();
//        User user2 = new User();
//        user2.setUsername("ww");
//        user2.setPassword("111");
//        user2.setRole("user");
//        user2.save();
//
//        Address address=new Address();
//        address.setName("小刘");
//        address.setPhone("15623818131");
//        address.setAddress("湖北省武汉市洪山区武汉科技大学黄家湖校区南九221");
//        address.setUserId(2);
//        address.save();
//        Address address1=new Address();
//        address1.setName("刘燕君");
//        address1.setPhone("15623818131");
//        address1.setAddress("湖北省武汉市洪山区福星惠誉东澜岸六栋1单元101室");
//        address1.setUserId(2);
//        address1.save();
//        Address address2=new Address();
//        address2.setName("王五");
//        address2.setPhone("13812345678");
//        address2.setAddress("湖北省武汉市洪山区华中科技大学社区喻园一期19单元201室");
//        address2.setUserId(3);
//        address2.save();
//        Address address3=new Address();
//        address3.setName("王五");
//        address3.setPhone("13812345678");
//        address3.setAddress("湖北省武汉市洪山区华中科技大学东2区47栋");
//        address3.setUserId(3);
//        address3.save();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        createDB();

        userid = (EditText) findViewById(R.id.userid);
        userpaw = (EditText) findViewById(R.id.userpaw);

    }

    //注册
    public void register(View v) {
        Intent intent = new Intent();
        intent.setClass(this, RegisterActivity.class);
        startActivity(intent);
    }

    // 登录按钮监听
    public void load(View v) {
        String name = userid.getText().toString().trim();
        String pwd = userpaw.getText().toString().trim();
        List<User> users = LitePal.where("username=?", name).find(User.class);
        int size = users.size();
        // 判断用户名是否为空
        if (name.equals("")) {
            errorMsg(this, "错误信息", "用户名不能为空！");
        } else if (size == 0) {
            errorMsg(this, "错误信息", "用户名不存在！");
        } else {
            int userid = -1;
            String password = null;
            String role = null;
            for (User user : users) {
                userid = user.getId();
                password = user.getPassword();
                role = user.getRole();
            }
            if (pwd.equals(password)) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putInt("userid", userid);
                bundle.putString("role", role);
                bundle.putString("name", name);
                intent.putExtras(bundle);
                intent.setClass(this, MainActivity.class);
                startActivity(intent);
                Toasty.success(this, "登录成功！", Toast.LENGTH_SHORT, true).show();
                finish();
            } else {
                errorMsg(this, "错误信息", "密码错误！");
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
