package com.example.yiapp.need;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;

import com.example.yiapp.R;
import com.example.yiapp.base.BaseActivity;
import com.example.yiapp.data.Address;
import com.example.yiapp.data.Need;

import org.litepal.LitePal;

import es.dmoral.toasty.Toasty;

public class NeedDetailActivity extends BaseActivity {

    TextView title;
    TextView state;
    TextView category;
    TextView time;
    TextView name;
    TextView phone;
    TextView address;
    TextView note;
    Button updatebtn;
    Button deletebtn;
    ImageView updateimg;
    Button btn;

    int needId;

    private void init() {
        title = find(R.id.need_title);
        state = find(R.id.need_state);
        category = find(R.id.need_category);
        time = find(R.id.need_time);
        name = find(R.id.need_name);
        phone = find(R.id.need_phone);
        address = find(R.id.need_address);
        note = find(R.id.need_note);
        btn = find(R.id.btn);
        updatebtn = find(R.id.updatebtn);
        deletebtn = find(R.id.deletebtn);
        updateimg = find(R.id.updateImg);
    }

    @Override
    protected void initViews() {
        init();
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        needId = bundle.getInt("needid");
        Need need = LitePal.find(Need.class, needId);
        if (need.getState().equals("已通过")) {
            updatebtn.setVisibility(View.GONE);
            deletebtn.setVisibility(View.GONE);
            updateimg.setVisibility(View.VISIBLE);
            btn.setVisibility(View.VISIBLE);
        } else if (need.getState().equals("已驳回")) {
            updateimg.setVisibility(View.GONE);
            updatebtn.setVisibility(View.GONE);
            deletebtn.setVisibility(View.VISIBLE);
            btn.setVisibility(View.GONE);
        } else {
            updateimg.setVisibility(View.GONE);
            deletebtn.setVisibility(View.GONE);
            updatebtn.setVisibility(View.VISIBLE);
            btn.setVisibility(View.GONE);
        }
        title.setText(need.getTitle());
        state.setText(need.getState());
        category.setText(need.getCategory());
        time.setText(need.getTime());
        note.setText(need.getNote());
        int addressid = need.getAddressId();
        Address address1 = LitePal.find(Address.class, addressid);
        name.setText(address1.getName());
        phone.setText(address1.getPhone());
        address.setText(address1.getAddress());
    }

    public void return2(View v) {
        finish();
    }

    public void update(View v) {
        Intent intent = new Intent(this, AddNeedActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("log", 1);
        bundle.putInt("to", 2);
        bundle.putInt("needid", needId);
        intent.putExtras(bundle);
        intentActivityResultLauncher.launch(intent);
    }

    public void delete(View v) {
        if (needId > -1) {
            new AlertDialog.Builder(this).setTitle("删除该需求信息")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            //删除当前行，代码见下页
                            int log=LitePal.delete(Need.class, needId);
                            if (log==1) {
                                Toasty.success(getApplicationContext(), "该需求记录删除成功", Toast.LENGTH_SHORT, true).show();
                                setResult(100);
                                finish();
                            } else {
                                Toasty.error(getApplicationContext(), "该需求记录删除失败", Toast.LENGTH_SHORT, true).show();
                            }
                        }
                    })
                    .setNegativeButton("取消", null).show();
        }
    }

    public void btn(View v) {
        finish();
    }

    ActivityResultLauncher<Intent> intentActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    //此处是跳转的result回调方法
                    if (result.getResultCode() == 200) {
                        setResult(100);
                        finish();
                    }
                }
            });

    @Override
    protected int getLayoutId() {
        return R.layout.need_detail;
    }
}
