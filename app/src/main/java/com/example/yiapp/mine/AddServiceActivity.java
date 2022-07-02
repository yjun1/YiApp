package com.example.yiapp.mine;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yiapp.R;
import com.example.yiapp.base.BaseActivity;
import com.example.yiapp.data.Address;
import com.example.yiapp.data.Service;

import org.litepal.LitePal;

import es.dmoral.toasty.Toasty;

public class AddServiceActivity extends BaseActivity {

    final String[] items = {"医生出诊", "健康咨询", "送药上门", "身体评估", "生活护理", "精神心理", " 老年文化"};
    String selectcategory = "";

    private EditText institution1;
    private EditText person1;
    private EditText phone1;
    private EditText price1;
    private EditText detail1;
    private TextView title1;
    private TextView category1;
    int log;
    int serviceId;

    private void init() {
        institution1 = find(R.id.institution);
        person1 = find(R.id.person);
        phone1 = find(R.id.phone);
        price1 = find(R.id.price);
        detail1 = find(R.id.detail);
        title1 = find(R.id.service_title);
        category1 = find(R.id.category);
    }

    @Override
    protected void initViews() {
        init();
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        log = bundle.getInt("log");
        if (log == 1) {
            serviceId = bundle.getInt("serviceid");
            Service service = LitePal.find(Service.class, serviceId);
            institution1.setText(service.getInstitution());
            person1.setText(service.getPerson());
            phone1.setText(service.getPhone());
            price1.setText(service.getPrice());
            detail1.setText(service.getDetail());
            category1.setText(service.getCategory());
        }
        title1.setText(bundle.getString("title2"));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.my_add_service;
    }

    public void onback(View view) {
        finish();
    }

    public void onsave(View view) {
        String institution = institution1.getText().toString();
        String person = person1.getText().toString();
        String phone = phone1.getText().toString();
        String price = price1.getText().toString();
        String detail = detail1.getText().toString();
        String category = category1.getText().toString();
        if (institution.equals("") || person.equals("") || phone.equals("") || price.equals("") || detail.equals("") || selectcategory.equals("")) {
            Toasty.error(this, "服务信息填写不完整，请检查！", Toast.LENGTH_LONG, true).show();
            return;
        }
        Service servicenew = new Service();
        servicenew.setInstitution(institution);
        servicenew.setPerson(person);
        servicenew.setPhone(phone);
        servicenew.setPrice(price);
        servicenew.setDetail(detail);
        servicenew.setCategory(category);
        if (log == 0) {
            servicenew.save();
        } else {
            servicenew.update(serviceId);
        }
        setResult(100);
        finish();
    }

    public void selectCategory(View v) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("请设置该服务类别");
        dialog.setIcon(R.drawable.more);
        dialog.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                selectcategory = items[which];
                Toast.makeText(getApplicationContext(), "你选择了" + selectcategory, Toast.LENGTH_SHORT).show();
            }
        });
        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                category1.setText(selectcategory);
                dialog.dismiss();
            }
        });
        dialog.setNegativeButton("取消", null);
        dialog.show();
    }

}
