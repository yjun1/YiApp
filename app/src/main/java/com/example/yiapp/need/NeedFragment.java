package com.example.yiapp.need;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;

import com.example.yiapp.R;
import com.example.yiapp.adapter.NeedAdapter;
import com.example.yiapp.base.BaseFragment;
import com.example.yiapp.data.Address;
import com.example.yiapp.data.Gauge;
import com.example.yiapp.data.Need;
import com.example.yiapp.data.Service;
import com.example.yiapp.mine.AddAddressActivity;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;

public class NeedFragment extends BaseFragment {

    ListView listView;
    Button btn;
    ImageView img;
    List<Need> needList = new ArrayList<Need>();
    NeedAdapter adapter;
    String role;
    int userId;

    private void init() {
        btn = find(R.id.add_need);
        img = find(R.id.add_img);
        listView = find(R.id.needlist);
    }

    protected void addNeed() {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AddNeedActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("log", 0);
                bundle.putInt("to",1);
                bundle.putInt("userid", userId);
                intent.putExtras(bundle);
                intentActivityResultLauncher.launch(intent);
            }
        });
    }

    private void initNeed() {
        if (role.equals("user")) {
            needList = LitePal.where("userId=?", String.valueOf(userId)).find(Need.class);
        } else {
            needList = LitePal.findAll(Need.class);
        }
    }

    public void init_listView() {
        initNeed();
        adapter = new NeedAdapter(getContext(), R.layout.item_need, needList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Need need = adapter.getItem(position);
                Intent intent=new Intent();
                Bundle bundle = new Bundle();
                if (role.equals("user")) {
                    intent.setClass(getActivity(), NeedDetailActivity.class);
                } else {
                    intent.setClass(getActivity(), NeedDetail1Activity.class);
                }
                bundle.putInt("needid", need.getId());
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
        addNeed();
    }

    protected void setView() {
        Intent intent = getActivity().getIntent();
        Bundle bundle = intent.getExtras();
        role = bundle.getString("role");
        userId = bundle.getInt("userid");
        if (role.equals("user")) {
            img.setVisibility(View.GONE);
        } else {
            btn.setVisibility(View.GONE);
        }
    }

    ActivityResultLauncher<Intent> intentActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    //此处是跳转的result回调方法
                    if (result.getResultCode() == 100) {
                        initViews();
                    }else {
                        initViews();
                    }
                }
            });

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_need;
    }
}
