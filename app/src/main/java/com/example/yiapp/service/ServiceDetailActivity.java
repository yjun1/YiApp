package com.example.yiapp.service;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;

import com.example.yiapp.MainActivity;
import com.example.yiapp.R;
import com.example.yiapp.base.BaseActivity;
import com.example.yiapp.data.Address;
import com.example.yiapp.data.Need;
import com.example.yiapp.data.Service;
import com.example.yiapp.data.Service1;
import com.example.yiapp.need.AddNeedActivity;

import org.litepal.LitePal;

import es.dmoral.toasty.Toasty;

public class ServiceDetailActivity extends BaseActivity {

    TextView title;
    TextView category;
    TextView time;
    TextView name;
    TextView phone;
    TextView address;
    TextView note;
    TextView institution;
    TextView person;
    TextView service_phone;
    TextView price;
    TextView detail;
    ImageView img;
    Button deletebtn;
    LinearLayout lay1;
    LinearLayout lay2;
    Button btn;
    int service1Id;
    String role;
    int isevaluate;

    private void init() {
        title = find(R.id.need_title);
        category = find(R.id.need_category);
        time = find(R.id.need_time);
        name = find(R.id.need_name);
        phone = find(R.id.need_phone);
        address = find(R.id.need_address);
        note = find(R.id.need_note);
        institution = find(R.id.service_institution);
        person = find(R.id.service_person);
        service_phone = find(R.id.service_phone);
        price = find(R.id.service_price);
        detail = find(R.id.service_detail);
        lay1 = find(R.id.lay1);
        lay2 = find(R.id.lay2);
        btn = find(R.id.btn_pj);
        img = find(R.id.deleteimg);
        deletebtn = find(R.id.deletebtn);
    }

    private void setView() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        service1Id = bundle.getInt("service1id");
        role = bundle.getString("role");
        Service1 service1 = LitePal.find(Service1.class, service1Id);
        isevaluate = 0;
        String state = service1.getState();
        if (service1.getGrade() == null) {
            isevaluate = 0;
        } else {
            isevaluate = 1;
        }
        switch (state) {
            case "待确定":
                if (role.equals("user")) {
                    lay2.setVisibility(View.GONE);
                    lay1.setVisibility(View.VISIBLE);
                } else {
                    lay1.setVisibility(View.GONE);
                    lay2.setVisibility(View.GONE);
                }
                img.setVisibility(View.VISIBLE);
                deletebtn.setVisibility(View.GONE);
                break;
            case "已拒绝":
                lay1.setVisibility(View.GONE);
                lay2.setVisibility(View.GONE);
                if (role.equals("admin")) {
                    img.setVisibility(View.GONE);
                    deletebtn.setVisibility(View.VISIBLE);
                } else {
                    deletebtn.setVisibility(View.GONE);
                    img.setVisibility(View.VISIBLE);
                }
                break;
            case "已接受":
                if (role.equals("user")) {
                    lay2.setVisibility(View.VISIBLE);
                    if (isevaluate == 1) {
                        btn.setText("查看评价");
                    } else {
                        btn.setText("去评价");
                    }
                } else {
                    if(isevaluate==0){
                        lay2.setVisibility(View.GONE);
                    }else {
                        lay2.setVisibility(View.VISIBLE);
                        btn.setText("查看评价");
                    }
                }
                img.setVisibility(View.VISIBLE);
                deletebtn.setVisibility(View.GONE);
                lay1.setVisibility(View.GONE);
                break;
        }
        int needid = service1.getNeedId();
        Need need = LitePal.find(Need.class, needid);
        title.setText(need.getTitle());
        category.setText(need.getCategory());
        time.setText(need.getTime());
        note.setText(need.getNote());
        int addressid = need.getAddressId();
        Address address1 = LitePal.find(Address.class, addressid);
        name.setText(address1.getName());
        phone.setText(address1.getPhone());
        address.setText(address1.getAddress());
        int serviceid = service1.getServiceId();
        Service service = LitePal.find(Service.class, serviceid);
        institution.setText(service.getInstitution());
        person.setText(service.getPerson());
        service_phone.setText(service.getPhone());
        price.setText(service.getPrice());
        detail.setText(service.getDetail());
    }

    @Override
    protected void initViews() {
        init();
        setView();
    }

    public void btnreturn(View v) {
        finish();
    }

    public void accept(View v) {
        Service1 service1 = new Service1();
        service1.setState("已接受");
        service1.update(service1Id);
        initViews();
    }

    //拒绝服务
    public void refuse(View v) {
        Service1 service1 = new Service1();
        service1.setState("已拒绝");
        service1.update(service1Id);
        setResult(100);
        finish();
    }

    public void todelete(View v) {
        if (service1Id > -1) {
            new AlertDialog.Builder(this).setTitle("删除该推荐信息")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            //删除当前行，代码见下页
                            int log=LitePal.delete(Service1.class, service1Id);
                            if (log==1) {
                                Toasty.success(getApplicationContext(), "该推荐记录删除成功", Toast.LENGTH_SHORT, true).show();
                                setResult(100);
                                finish();
                            } else {
                                Toasty.error(getApplicationContext(), "该推荐记录删除失败", Toast.LENGTH_SHORT, true).show();
                            }
                        }
                    })
                    .setNegativeButton("取消", null).show();
        }
    }

    public void topj(View v) {
        if (role.equals("user")) {
            Intent intent = new Intent(this, ServiceEvaluateActivity.class);
            Bundle bundle = new Bundle();
            bundle.putInt("service1id", service1Id);
            intent.putExtras(bundle);
            intentActivityResultLauncher.launch(intent);
        }else {
            Intent intent=new Intent(this,ServiceEvaluate1Activity.class);
            Bundle bundle=new Bundle();
            bundle.putInt("service1id", service1Id);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }

    ActivityResultLauncher<Intent> intentActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    //此处是跳转的result回调方法
                    if (result.getResultCode() == 200) {
                        initViews();
                    }
                }
            });

    @Override
    protected int getLayoutId() {
        return R.layout.service_detail;
    }
}
