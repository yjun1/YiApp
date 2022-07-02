package com.example.yiapp.service;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;

import com.example.yiapp.R;
import com.example.yiapp.adapter.Service1Adapter;
import com.example.yiapp.base.BaseFragment;
import com.example.yiapp.data.Need;
import com.example.yiapp.data.Service1;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;

public class ServiceFragment extends BaseFragment {

    TextView all;
    TextView accept;
    TextView other;
    ListView listView;
    List<Service1> service1List = new ArrayList<Service1>();
    Service1Adapter adapter;
    int statelog;
    String role;
    int userId;

    protected void init() {
        all = find(R.id.all_service);
        accept = find(R.id.accept_service);
        other = find(R.id.other_service);
        listView = find(R.id.service_list);
    }

    protected void changelist() {
        all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (statelog != 0) {
                    statelog = 0;
                    all.setBackgroundColor(Color.parseColor("#FFCAAD"));
                    accept.setBackgroundColor(Color.parseColor("#B3D9FF"));
                    other.setBackgroundColor(Color.parseColor("#B3D9FF"));
                    init_listView();
                }
            }
        });
        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (statelog != 1) {
                    statelog = 1;
                    accept.setBackgroundColor(Color.parseColor("#FFCAAD"));
                    all.setBackgroundColor(Color.parseColor("#B3D9FF"));
                    other.setBackgroundColor(Color.parseColor("#B3D9FF"));
                    init_listView();
                }
            }
        });
        other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (statelog != 2) {
                    statelog = 2;
                    other.setBackgroundColor(Color.parseColor("#FFCAAD"));
                    accept.setBackgroundColor(Color.parseColor("#B3D9FF"));
                    all.setBackgroundColor(Color.parseColor("#B3D9FF"));
                    init_listView();
                }
            }
        });
    }

    private void initService() {
        switch (statelog) {
            case 0:
                if (role.equals("user")) {
                    service1List = LitePal.where("userId=?", String.valueOf(userId)).find(Service1.class);
                } else {
                    service1List = LitePal.findAll(Service1.class);
                }
                break;
            case 1:
                if (role.equals("user")) {
                    service1List = LitePal.where("userId=? and state=?", String.valueOf(userId), "已接受").find(Service1.class);
                } else {
                    service1List = LitePal.where("state=?", "已接受").find(Service1.class);
                }
                break;
            case 2:
                if (role.equals("user")) {
                    service1List = LitePal.where("userId=? and state<>?", String.valueOf(userId), "已接受").find(Service1.class);
                } else {
                    service1List = LitePal.where("state<>?", "已接受").find(Service1.class);
                }
                break;
        }
    }

    public void init_listView() {
        initService();
        adapter = new Service1Adapter(getContext(), R.layout.item_service, service1List);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Service1 service = adapter.getItem(position);
                Intent intent = new Intent(getActivity(), ServiceDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("service1id", service.getId());
                bundle.putString("role", role);
                intent.putExtras(bundle);
                intentActivityResultLauncher.launch(intent);
            }
        });
    }

    @Override
    protected void initViews() {
        init();
        setView();
        init_listView();
        changelist();
    }

    protected void setView() {
        Intent intent = getActivity().getIntent();
        Bundle bundle = intent.getExtras();
        role = bundle.getString("role");
        userId = bundle.getInt("userid");
        statelog = 0;
        all.setBackgroundColor(Color.parseColor("#FFCAAD"));
        accept.setBackgroundColor(Color.parseColor("#B3D9FF"));
        other.setBackgroundColor(Color.parseColor("#B3D9FF"));
    }

    ActivityResultLauncher<Intent> intentActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    //此处是跳转的result回调方法
                    if (result.getResultCode() == 100) {
                        initViews();
                    } else {
                        initViews();
                    }
                }
            });

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_service;
    }


}
