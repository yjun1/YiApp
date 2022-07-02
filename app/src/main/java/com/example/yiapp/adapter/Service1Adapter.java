package com.example.yiapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.yiapp.R;
import com.example.yiapp.data.Need;
import com.example.yiapp.data.Service;
import com.example.yiapp.data.Service1;

import org.litepal.LitePal;

import java.util.List;

public class Service1Adapter extends ArrayAdapter<Service1> {

    private int resourceId;

    public Service1Adapter(@NonNull Context context, int resource, @NonNull List<Service1> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Service1 service1 = getItem(position);
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, null);
            viewHolder = new ViewHolder();
            viewHolder.institution = view.findViewById(R.id.service_institution);
            viewHolder.need_category = view.findViewById(R.id.service_need_category);
            viewHolder.preson = view.findViewById(R.id.service_person);
            viewHolder.price = view.findViewById(R.id.service_price);
            viewHolder.state=view.findViewById(R.id.service_state);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        int serviceid=service1.getServiceId();
        Service service= LitePal.find(Service.class,serviceid);
        int needid=service1.getNeedId();
        Need need=LitePal.find(Need.class,needid);
        viewHolder.institution.setText(service.getInstitution());
        viewHolder.need_category.setText(need.getCategory());
        viewHolder.preson.setText(service.getPerson());
        viewHolder.price.setText(service.getPrice());
        viewHolder.state.setText(service1.getState());
        return view;
    }

    class ViewHolder {
        TextView institution;
        TextView need_category;
        TextView preson;
        TextView price;
        TextView state;
    }
}
