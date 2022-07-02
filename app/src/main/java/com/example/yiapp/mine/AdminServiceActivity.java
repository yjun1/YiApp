package com.example.yiapp.mine;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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
import androidx.activity.result.contract.ActivityResultContracts;

import com.example.yiapp.R;
import com.example.yiapp.adapter.AllServiceAdapter;
import com.example.yiapp.base.BaseActivity;
import com.example.yiapp.data.Address;
import com.example.yiapp.data.Service;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;

public class AdminServiceActivity extends BaseActivity {

    ListView listView;
    //ListView数据集（存放查询Person表结果）
    List<Service> serviceList = new ArrayList<Service>();
    //ListView适配器
    AllServiceAdapter adapter;

    //查询机构服务表中所有数据
    private void initService() {
        serviceList = LitePal.findAll(Service.class);
        adapter = new AllServiceAdapter(this, R.layout.item_all_service, serviceList);
        listView.setAdapter(adapter);
        String log = "查询到" + serviceList.size() + "条记录";
        Toasty.success(this, log, Toast.LENGTH_SHORT, true).show();
    }

    public void init_listView() {
        initService();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Service service = adapter.getItem(position);
                Intent intent = new Intent(getApplicationContext(), AddServiceActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("serviceid", service.getId());
                bundle.putInt("log", 1);
                bundle.putString("title2", "服务详情");
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
                Service service = adapter.getItem(menuInfo.position);
                Intent intent = new Intent(getApplicationContext(), AddServiceActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("title2", "修改服务");
                bundle.putInt("serviceid", service.getId());
                bundle.putInt("log", 1);
                intent.putExtras(bundle);
                intentActivityResultLauncher.launch(intent);
                break;
            case 1:
                Service service1 = adapter.getItem(menuInfo.position);
                int id = service1.getId();
                if (id > -1) {
                    //弹窗，确定是否删除
                    new AlertDialog.Builder(this).setTitle("删除该服务信息")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    //删除该机构服务记录
                                    int log=LitePal.delete(Service.class, id);
                                    if (log==1) {
                                        Toasty.success(getApplicationContext(), "该服务记录删除成功",
                                                Toast.LENGTH_SHORT, true).show();
                                    } else {
                                        Toasty.error(getApplicationContext(), "该服务记录删除失败",
                                                Toast.LENGTH_SHORT, true).show();
                                    }
                                    initService();
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
        listView = (ListView) findViewById(R.id.servicelist);
        init_listView();
    }

    public void onBack1(View view) {
        finish();
    }

    public void onAdd(View view) {
        Intent intent1 = new Intent(this, AddServiceActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("log", "0");
        bundle.putString("title2", "添加服务");
        intent1.putExtras(bundle);
        intentActivityResultLauncher.launch(intent1);
    }

    ActivityResultLauncher<Intent> intentActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    //此处是跳转的result回调方法
                    if (result.getResultCode() == 100) {
                        initService();
                    }
                }
            });

    @Override
    protected int getLayoutId() {
        return R.layout.my_admin_service;
    }
}
