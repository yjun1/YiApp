package com.example.yiapp.need;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;

import com.example.yiapp.R;
import com.example.yiapp.base.BaseActivity;
import com.example.yiapp.data.Address;
import com.example.yiapp.data.Gauge;
import com.example.yiapp.data.Need;
import com.example.yiapp.data.Service;
import com.example.yiapp.data.Service1;
import com.example.yiapp.gauge.AddGaugeActivity;
import com.example.yiapp.gauge.GaugeDetailActivity;

import org.litepal.LitePal;

import java.util.List;

public class NeedDetail1Activity extends BaseActivity {

    TextView title;
    TextView category;
    TextView time;
    TextView name;
    TextView phone;
    TextView address;
    TextView note;
    Button gaugebtn;
    Button acceptbtn;
    Button rejectbtn;
    Button tjbtn;
    LinearLayout layout1;
    LinearLayout layout2;
    LinearLayout layout3;
    TextView tvlog;

    int needId;
    Need need;

    private void init() {
        title = find(R.id.need_title);
        category = find(R.id.need_category);
        time = find(R.id.need_time);
        name = find(R.id.need_name);
        phone = find(R.id.need_phone);
        address = find(R.id.need_address);
        note = find(R.id.need_note);
        gaugebtn = find(R.id.to_gauge);
        acceptbtn = find(R.id.accept_need);
        rejectbtn = find(R.id.reject_need);
        tjbtn = find(R.id.btn_tj);
        layout1 = find(R.id.layout1);
        layout2 = find(R.id.layout2);
        layout3 = find(R.id.layout3);
        tvlog = find(R.id.tvlog);
    }

    protected void toGauge() {
        gaugebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int userId = need.getUserId();
                List<Gauge> gaugeList = LitePal.where("userId=?", String.valueOf(userId)).find(Gauge.class);
                int gaugeId = -1;
                for (Gauge gauge : gaugeList) {
                    gaugeId = gauge.getId();
                }
                Intent intent = new Intent(NeedDetail1Activity.this, GaugeDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("gaugeid", gaugeId);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initViews() {
        init();
        toGauge();
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        needId = bundle.getInt("needid");
        need = LitePal.find(Need.class, needId);
        String state = need.getState();
        if (state.equals("已通过")) {
            layout1.setVisibility(View.GONE);
            List<Service1> service1List = LitePal.where("needId=?", String.valueOf(needId)).find(Service1.class);
            if (service1List.size() > 0) {
                layout2.setVisibility(View.GONE);
                tvlog.setText("已给该需求推荐服务");
                layout3.setVisibility(View.VISIBLE);
            } else {
                layout2.setVisibility(View.VISIBLE);
                layout3.setVisibility(View.GONE);
            }
        } else if (state.equals("已驳回")) {
            tvlog.setText("该需求已被驳回");
            layout3.setVisibility(View.VISIBLE);
            layout2.setVisibility(View.GONE);
            layout1.setVisibility(View.GONE);
        } else {
            layout1.setVisibility(View.VISIBLE);
            layout2.setVisibility(View.GONE);
            layout3.setVisibility(View.GONE);
        }
        title.setText(need.getTitle());
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

    //通过联系人的服务需求
    public void accept(View v) {
        Need need = new Need();
        need.setState("已通过");
        need.update(needId);
        initViews();
    }

    //拒绝联系人的服务需求
    public void reject(View v) {
        Need need = new Need();
        need.setState("已驳回");
        need.update(needId);
        initViews();
    }

    public void toService(View v) {
        Intent intent = new Intent(this,ServiceGiveActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("needid",needId);
        intent.putExtras(bundle);
        intentActivityResultLauncher.launch(intent);
    }

    ActivityResultLauncher<Intent> intentActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    //此处是跳转的result回调方法
                    if (result.getResultCode() == 200){
                        setResult(100);
                        finish();
                    }else {
                        finish();
                    }
                }
            });

    @Override
    protected int getLayoutId() {
        return R.layout.need_detail1;
    }
}
