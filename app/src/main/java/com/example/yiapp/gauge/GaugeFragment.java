package com.example.yiapp.gauge;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;

import com.example.yiapp.R;
import com.example.yiapp.adapter.GaugeAdapter;
import com.example.yiapp.base.BaseFragment;
import com.example.yiapp.data.Gauge;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;

public class GaugeFragment extends BaseFragment {

    ListView listView;
    TextView title;
    Button btn_gauge;
    List<Gauge> gaugeList = new ArrayList<Gauge>();
    GaugeAdapter adapter;
    int log;
    String role;
    int userId;

    protected void init() {
        title = find(R.id.gauge_title);
        btn_gauge = find(R.id.btn_gauge);
        listView = find(R.id.gaugelist);
    }

    protected void setView() {
        Intent intent = getActivity().getIntent();
        Bundle bundle = intent.getExtras();
        role = bundle.getString("role");
        userId = bundle.getInt("userid");
        if (role.equals("user")) {
            title.setText("我的量表");
            List<Gauge> gauges = LitePal.where("userId=?", String.valueOf(userId)).find(Gauge.class);
            int size = gauges.size();
            if (size == 0) {
                log = 0;
            } else {
                log = 1;
            }
        } else {
            title.setText("所有量表");
            log = 1;
        }
    }

    protected void addGauge() {
        btn_gauge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AddGaugeActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("addlog", 0);
                bundle.putInt("userid", userId);
                intent.putExtras(bundle);
                intentActivityResultLauncher.launch(intent);
            }
        });
    }

    private void initGauge() {
        if (role.equals("user")) {
            gaugeList = LitePal.where("userId=?", String.valueOf(userId)).find(Gauge.class);
        } else {
            gaugeList = LitePal.findAll(Gauge.class);
        }
    }

    public void init_listView() {
        initGauge();
        adapter = new GaugeAdapter(getContext(), R.layout.item_gauge, gaugeList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Gauge gauge = adapter.getItem(position);
                Intent intent;
                Bundle bundle = new Bundle();
                if (role.equals("user")) {
                    intent = new Intent(getActivity(), AddGaugeActivity.class);
                    bundle.putInt("addlog", 1);
                } else {
                    intent = new Intent(getActivity(), GaugeDetailActivity.class);
                }
                bundle.putInt("gaugeid", gauge.getId());
                intent.putExtras(bundle);
                intentActivityResultLauncher.launch(intent);
            }
        });
    }

    ActivityResultLauncher<Intent> intentActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    //此处是跳转的result回调方法
                    if (result.getResultCode() == 100) {
                        initViews();
                    }
                }
            });

    @Override
    protected void initViews() {
        init();
        setView();
        if (log == 0) {
            btn_gauge.setVisibility(View.VISIBLE);
            listView.setVisibility(View.GONE);
            addGauge();
        } else {
            btn_gauge.setVisibility(View.GONE);
            listView.setVisibility(View.VISIBLE);
            init_listView();
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_gauge;
    }

}
