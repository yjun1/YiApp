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
import com.example.yiapp.data.Gauge;
import com.example.yiapp.data.Need;

import java.util.List;

public class GaugeAdapter extends ArrayAdapter<Gauge> {

    private int resourceId;

    public GaugeAdapter(@NonNull Context context, int resource, @NonNull List<Gauge> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Gauge gauge = getItem(position);
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, null);
            viewHolder = new ViewHolder();
            viewHolder.name = view.findViewById(R.id.name);
            viewHolder.xb = view.findViewById(R.id.xb);
            viewHolder.nl = view.findViewById(R.id.nl);
            viewHolder.sjynx = view.findViewById(R.id.sjynx);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.name.setText(gauge.getName());
        viewHolder.xb.setText(gauge.getXb());
        viewHolder.nl.setText(gauge.getNl());
        viewHolder.sjynx.setText(gauge.getSjynx());
        return view;
    }

    class ViewHolder {
        TextView name;
        TextView xb;
        TextView nl;
        TextView sjynx;
    }
}
