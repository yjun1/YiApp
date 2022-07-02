package com.example.yiapp.mine;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import com.example.yiapp.MainActivity;
import com.example.yiapp.R;
import com.example.yiapp.adapter.AddressAdapter;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.yiapp.base.BaseActivity;
import com.example.yiapp.data.Address;
import com.example.yiapp.data.Gauge;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;

public class UserAddressActivity extends BaseActivity {

    ListView listView;
    //ListView数据集（存放查询Person表结果）
    List<Address> addressList = new ArrayList<Address>();
    //ListView适配器
    AddressAdapter adapter;
    int userId;

    private void initAddress() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        userId = bundle.getInt("userid");
        addressList = LitePal.where("userId=?", String.valueOf(userId)).find(Address.class);
        adapter = new AddressAdapter(this, R.layout.item_address, addressList);
        listView.setAdapter(adapter);
        String log = "查询到" + addressList.size() + "条记录";
        Toasty.success(this, log, Toast.LENGTH_SHORT, true).show();
    }

    public void init_listView() {
        initAddress();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Address address = adapter.getItem(position);
                Intent intent = new Intent(getApplicationContext(), AddAddressActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("addressid", address.getId());
                bundle.putInt("log", 1);
                bundle.putString("title", "地址详情");
                intent.putExtras(bundle);
                intentActivityResultLauncher.launch(intent);
            }
        });
        listView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
                contextMenu.setHeaderTitle("选择操作");
                contextMenu.add(0, 0, 0, "编辑");
                contextMenu.add(0, 1, 0, "删除");
            }
        });
    }

    //选中菜单Item后触发
    public boolean onContextItemSelected(MenuItem item) {
        //关键代码在这里
        AdapterView.AdapterContextMenuInfo menuInfo;
        menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case 0:
                //点击第一个菜单项要做的事，如获取点击listview的位置
                Address address = adapter.getItem(menuInfo.position);
                Intent intent = new Intent(getApplicationContext(), AddAddressActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("addressid", address.getId());
                bundle.putInt("log", 1);
                bundle.putString("title", "编辑地址");
                intent.putExtras(bundle);
                intentActivityResultLauncher.launch(intent);
                break;
            case 1:
                Address address1 = adapter.getItem(menuInfo.position);
                int id = address1.getId();
                if (id > -1) {
                    new AlertDialog.Builder(this).setTitle("删除该地址信息")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    //删除当前行，代码见下页
                                    int log=LitePal.delete(Address.class, id);
                                    if (log==1) {
                                        Toasty.success(getApplicationContext(), "该地址记录删除成功", Toast.LENGTH_SHORT, true).show();
                                    } else {
                                        Toasty.error(getApplicationContext(), "该地址记录删除失败", Toast.LENGTH_SHORT, true).show();
                                    }
                                    initAddress();
                                }
                            })
                            .setNegativeButton("取消", null).show();
                }
                break;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    protected void initViews() {
        listView = (ListView) findViewById(R.id.addresslist);
        init_listView();
    }

    public void onBack1(View view) {
        finish();
    }

    ActivityResultLauncher<Intent> intentActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    //此处是跳转的result回调方法
                    if (result.getResultCode() == 100) {
                        initAddress();
                    }
                }
            });

    public void onAdd(View view) {
        Intent intent = new Intent(this, AddAddressActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("log", 0);
        bundle.putInt("userid", userId);
        bundle.putString("title", "添加地址");
        intent.putExtras(bundle);
        intentActivityResultLauncher.launch(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.my_user_address;
    }

}
