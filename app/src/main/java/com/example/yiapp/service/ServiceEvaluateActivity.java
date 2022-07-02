package com.example.yiapp.service;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yiapp.R;
import com.example.yiapp.base.BaseActivity;
import com.example.yiapp.data.Service;
import com.example.yiapp.data.Service1;

import org.litepal.LitePal;

import es.dmoral.toasty.Toasty;

public class ServiceEvaluateActivity extends BaseActivity {

    TextView institution;
    TextView state;
    TextView person;
    TextView price;
    RadioGroup grade;
    EditText content;
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
        String grade1 = service1.getGrade();
        if (grade1 != null) {
            switch (grade1) {
                case "好评":
                    grade.check(R.id.hao);
                    break;
                case "中评":
                    grade.check(R.id.zhong);
                    break;
                case "差评":
                    grade.check(R.id.cha);
                    break;
            }
        }
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

    public void toSave(View v) {
        if (content.getText().toString().equals("") || grade.getCheckedRadioButtonId() == -1) {
            Toasty.error(this, "评价信息填写或选择不完整，请检查！", Toast.LENGTH_LONG, true).show();
            return;
        }
        Service1 service1 = new Service1();
        RadioButton pj = (RadioButton) findViewById(grade.getCheckedRadioButtonId());
        service1.setGrade(pj.getText().toString());
        service1.setContent(content.getText().toString());
        service1.update(service1Id);
        setResult(200);
        finish();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.service_evaluate;
    }

}
