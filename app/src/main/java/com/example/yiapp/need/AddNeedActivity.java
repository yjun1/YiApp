package com.example.yiapp.need;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.yiapp.R;
import com.example.yiapp.adapter.AddressAdapter;
import com.example.yiapp.base.BaseActivity;
import com.example.yiapp.data.Address;
import com.example.yiapp.data.Need;

import org.litepal.LitePal;

import java.util.List;

import es.dmoral.toasty.Toasty;

public class AddNeedActivity extends BaseActivity {

    final String[] items = {"医生出诊", "健康咨询", "送药上门", "身体评估", "生活护理", "精神心理", " 老年文化"};
    int selectaddressid = -1;
    int selectPosition = -1;
    String selectcategory = "";

    TextView toptitle;
    EditText title;
    TextView category;
    EditText time;
    TextView name;
    TextView phone;
    TextView address;
    EditText note;
    LinearLayout l2;
    TextView tv1;

    int log;
    int to;
    int userId;
    int needId;

    private void init() {
        toptitle = find(R.id.top_title);
        title = find(R.id.need_title);
        category = find(R.id.need_category);
        time = find(R.id.need_time);
        name = find(R.id.need_name);
        phone = find(R.id.need_phone);
        address = find(R.id.need_address);
        note = find(R.id.need_note);
        l2 = find(R.id.l2);
        tv1 = find(R.id.tv1);
    }


    @Override
    protected void initViews() {
        init();
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        log = bundle.getInt("log");
        to = bundle.getInt("to");
        if (log == 1) {
            needId = bundle.getInt("needid");
            toptitle.setText("修改需求");
            Need need = LitePal.find(Need.class, needId);
            userId=need.getUserId();
            title.setText(need.getTitle());
            category.setText(need.getCategory());
            time.setText(need.getTime());
            note.setText(need.getNote());
            int addressid = need.getAddressId();
            selectaddressid=addressid;
            Address addressnew = LitePal.find(Address.class, addressid);
            name.setText(addressnew.getName());
            phone.setText(addressnew.getPhone());
            address.setText(addressnew.getAddress());
            l2.setVisibility(View.VISIBLE);
            tv1.setText("已选");
        } else {
            userId = bundle.getInt("userid");
            toptitle.setText("新建需求");
            l2.setVisibility(View.GONE);
            tv1.setText("请选择");
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.need_add;
    }

    public void submit(View v) {
        String n_title = title.getText().toString();
        String n_category = category.getText().toString();
        String n_time = time.getText().toString();
        String n_note = note.getText().toString();
        int n_addressid = selectaddressid;
        if (n_title.equals("") || n_time.equals("") || n_note.equals("") || selectcategory.equals("") || selectaddressid == -1) {
            Toasty.error(this, "需求信息填写或选择不完整，请检查！", Toast.LENGTH_LONG, true).show();
            return;
        }
        Need neednew = new Need();
        neednew.setTitle(n_title);
        neednew.setCategory(n_category);
        neednew.setTime(n_time);
        neednew.setNote(n_note);
        neednew.setAddressId(n_addressid);
        if (log == 0) {
            neednew.setUserId(userId);
            neednew.setState("审核中");
            neednew.save();
        } else {
            neednew.update(needId);
        }
        if (to == 1) {
            setResult(100);
        } else {
            setResult(200);
        }
        finish();
    }

    public void back2(View v) {
        finish();
    }

    public void selectAddress(View v) {
        View alertDialogView = LayoutInflater.from(this).inflate(R.layout.dialog_address, null);
        ListView list = (ListView) alertDialogView.findViewById(R.id.dialog_address_list);
        List<Address> addressList = LitePal.where("userId=?", String.valueOf(userId)).find(Address.class);
        AddressAdapter1 adapter = new AddressAdapter1(this, addressList);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectPosition = position;
                adapter.notifyDataSetChanged();
                Address address = addressList.get(position);
                selectaddressid = address.getId();
                int id1 = position + 1;
                Toast.makeText(getApplicationContext(), "你选择了第" + id1 + "个地址", Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("请选择地址信息");
        dialog.setIcon(R.drawable.more);
        dialog.setView(alertDialogView);
        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                l2.setVisibility(View.VISIBLE);
                Address address1 = LitePal.find(Address.class, selectaddressid);
                name.setText(address1.getName());
                phone.setText(address1.getPhone());
                address.setText(address1.getAddress());
                tv1.setText("已选");
                dialog.dismiss();
            }
        });
        dialog.setNegativeButton("取消", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                selectPosition = -1;
            }
        });
        dialog.show();
    }

    public void selectCategory(View v) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("请选择服务类别");
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
                category.setText(selectcategory);
                dialog.dismiss();
            }
        });
        dialog.setNegativeButton("取消", null);
        dialog.show();
    }

    public class AddressAdapter1 extends BaseAdapter {

        Context context;
        List<Address> addressList;
        LayoutInflater mInflater;

        public AddressAdapter1(Context context, List<Address> mList) {
            this.context = context;
            this.addressList = mList;
            mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return addressList.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            ViewHolder viewHolder = null;
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.item_address1, parent, false);
                viewHolder = new ViewHolder();
                viewHolder.name = convertView.findViewById(R.id.name1);
                viewHolder.phone = convertView.findViewById(R.id.phone1);
                viewHolder.address = convertView.findViewById(R.id.address1);
                viewHolder.select = convertView.findViewById(R.id.id_select);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.name.setText(addressList.get(position).getName());
            viewHolder.phone.setText(addressList.get(position).getPhone());
            viewHolder.address.setText(addressList.get(position).getAddress());
            if (selectPosition == position) {
                viewHolder.select.setChecked(true);
            } else {
                viewHolder.select.setChecked(false);
            }
            return convertView;
        }

        public class ViewHolder {
            TextView name;
            TextView phone;
            TextView address;
            RadioButton select;
        }
    }
}
