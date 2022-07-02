package com.example.yiapp.need;

import android.content.Intent;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.yiapp.R;
import com.example.yiapp.base.BaseActivity;
import com.example.yiapp.data.Gauge;
import com.example.yiapp.data.Need;
import com.example.yiapp.data.Service;
import com.example.yiapp.data.Service1;
import com.example.yiapp.gauge.GaugeDetailActivity;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;

public class ServiceGiveActivity extends BaseActivity {

    ListView listView;
    List<Service> servicegiveList = new ArrayList<Service>();

    TextView needtitle;
    TextView needcategory;
    TextView needtime;

    int needId;
    Need need;

    List<Service1> service1List = new ArrayList<Service1>();

    protected void init() {
        listView = find(R.id.givelist);
        needtitle = find(R.id.title11);
        needcategory = find(R.id.category11);
        needtime = find(R.id.time11);
    }

    private void initService() {
        servicegiveList = LitePal.where("category=?", need.getCategory()).find(Service.class);
        listView.setAdapter(new CustomAdapter());
        String log = "查询到" + servicegiveList.size() + "条记录";
        Toasty.success(this, log, Toast.LENGTH_SHORT, true).show();
    }

    @Override
    protected void initViews() {
        init();
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        needId = bundle.getInt("needid");
        need = LitePal.find(Need.class, needId);
        needtitle.setText(need.getTitle());
        needcategory.setText(need.getCategory());
        needtime.setText(need.getTime());
        initService();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.service_give;
    }

    public void toGauge1(View v) {
        int userId = need.getUserId();
        List<Gauge> gaugeList = LitePal.where("userId=?", String.valueOf(userId)).find(Gauge.class);
        int gaugeId = -1;
        for (Gauge gauge : gaugeList) {
            gaugeId = gauge.getId();
        }
        Intent intent = new Intent(this, GaugeDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("gaugeid", gaugeId);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void backk(View v) {
        finish();
    }

    public void saveClick(View view) {
        SparseBooleanArray checkedItemPositions = listView.getCheckedItemPositions();
        //循环遍历集合中所有的数据，获取每个item是否在SparseBooleanArray存储，以及对应的值；
        for (int i = 0; i < servicegiveList.size(); i++) {
            //根据key获取对应的boolean值，没有则返回false
            boolean isChecked = checkedItemPositions.get(i);
            if (isChecked) {
                Service service = servicegiveList.get(i);
                Service1 service1 = new Service1();
                service1.setUserId(need.getUserId());
                service1.setNeedId(needId);
                service1.setServiceId(service.getId());
                service1.setState("待确定");
                service1List.add(service1);
            }
        }
        LitePal.saveAll(service1List);
        setResult(200);
        finish();
    }

    private class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return servicegiveList.size();
        }

        @Override
        public Service getItem(int position) {
            return servicegiveList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return servicegiveList.get(position).hashCode();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup container) {
            ViewHolder viewHolder = null;
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.item_all_service1, container, false);
                viewHolder = new ViewHolder();
                viewHolder.institution = convertView.findViewById(R.id.institution11);
                viewHolder.person = convertView.findViewById(R.id.person11);
                viewHolder.price = convertView.findViewById(R.id.price11);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.institution.setText(servicegiveList.get(position).getInstitution());
            viewHolder.person.setText(servicegiveList.get(position).getPerson());
            viewHolder.price.setText(servicegiveList.get(position).getPrice());
            return convertView;
        }

        public class ViewHolder {
            TextView institution;
            TextView person;
            TextView price;
        }
    }

}
