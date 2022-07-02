package com.example.yiapp.service;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.yiapp.R;
import com.example.yiapp.base.BaseActivity;
import com.example.yiapp.data.Service;
import com.example.yiapp.data.Service1;

import org.litepal.LitePal;

public class ServiceEvaluate1Activity extends BaseActivity {

    TextView institution;
    TextView state;
    TextView person;
    TextView price;
    TextView grade;
    TextView content;
    int service1Id;

    private void init() {
        institution = find(R.id.service_institution);
        state = find(R.id.service_state);
        person = find(R.id.service_person);
        price = find(R.id.service_price);
        grade = find(R.id.service_grade);
        content = find(R.id.service_content);
    }

    private void setView() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        service1Id = bundle.getInt("service1id");
        Service1 service1 = LitePal.find(Service1.class, service1Id);
        int serviceid = service1.getServiceId();
        Service service = LitePal.find(Service.class, serviceid);
        institution.setText(service.getInstitution());
        person.setText(service.getPerson());
        price.setText(service.getPrice());
        state.setText(service1.getState());
        grade.setText(service1.getGrade());
        content.setText(service1.getContent());
    }

    @Override
    protected void initViews() {
        init();
        setView();
    }

    public void toBack(View v) {
        finish();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.service_evaluate1;
    }

}
