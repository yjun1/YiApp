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
import com.example.yiapp.data.Service;

import java.util.List;

public class AllServiceAdapter extends ArrayAdapter<Service> {

    private int resourceId;

    public AllServiceAdapter(@NonNull Context context, int resource, @NonNull List<Service> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Service service = getItem(position);
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, null);
            viewHolder = new ViewHolder();
            viewHolder.institution = view.findViewById(R.id.service_institution1);
            viewHolder.person = view.findViewById(R.id.service_person1);
            viewHolder.price = view.findViewById(R.id.service_price1);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.institution.setText(service.getInstitution());
        viewHolder.person.setText(service.getPerson());
        viewHolder.price.setText(service.getPrice());
        return view;
    }
    class ViewHolder {
        TextView institution;
        TextView person;
        TextView price;
    }
}
