package com.example.yiapp.mine;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
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

import org.litepal.LitePal;

import es.dmoral.toasty.Toasty;

public class AddAddressActivity extends BaseActivity {

    private EditText name1;
    private EditText phone1;
    private EditText address1;
    private TextView title;
    int log;
    int addressId;
    int userId;

    private void init() {
        name1 = find(R.id.name1);
        phone1 = find(R.id.phone1);
        address1 = find(R.id.address1);
        title = find(R.id.address_title);
    }

    @Override
    protected void initViews() {
        init();
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        log = bundle.getInt("log");
        if (log == 1) {
            addressId = bundle.getInt("addressid");
            Address address = LitePal.find(Address.class, addressId);
            name1.setText(address.getName());
            phone1.setText(address.getPhone());
            address1.setText(address.getAddress());
        } else {
            userId = bundle.getInt("userid");
        }
        title.setText(bundle.getString("title"));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.my_add_address;
    }

    public void onBack2(View view) {
        finish();
    }

    public void onAddSave(View view) {
        String name = name1.getText().toString();
        String phone = phone1.getText().toString();
        String address = address1.getText().toString();
        if(name.equals("")||phone.equals("")||address.equals("")){
            Toasty.error(this, "地址信息填写不完整，请检查！", Toast.LENGTH_LONG, true).show();
            return;
        }
        Address addressnew = new Address();
        addressnew.setName(name);
        addressnew.setPhone(phone);
        addressnew.setAddress(address);
        if (log == 0) {
            addressnew.setUserId(userId);
            addressnew.save();
        } else {
            addressnew.update(addressId);
        }
        setResult(100);
        finish();
    }
}
