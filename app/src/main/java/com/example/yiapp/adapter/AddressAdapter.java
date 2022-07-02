package com.example.yiapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yiapp.R;
import com.example.yiapp.data.Address;

import java.util.List;

import static androidx.recyclerview.widget.RecyclerView.*;

public class AddressAdapter extends ArrayAdapter<Address> {

    private int resourceId; //自定义成员，存放ListView的行布局id

    public AddressAdapter(@NonNull Context context, int resource, @NonNull List<Address> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Address addressnew = getItem(position);
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.name = view.findViewById(R.id.name1);
            viewHolder.phone = view.findViewById(R.id.phone1);
            viewHolder.address = view.findViewById(R.id.address1);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.name.setText(addressnew.getName());
        viewHolder.phone.setText(addressnew.getPhone());
        viewHolder.address.setText(addressnew.getAddress());
        return view;
    }

    class ViewHolder {
        TextView name;
        TextView phone;
        TextView address;
    }
}
