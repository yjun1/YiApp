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

import java.util.List;

public class NeedAdapter extends ArrayAdapter<Need> {

    private int resourceId;

    public NeedAdapter(@NonNull Context context, int resource, @NonNull List<Need> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Need need = getItem(position);
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, null);
            viewHolder = new ViewHolder();
            viewHolder.title = view.findViewById(R.id.need_title);
            viewHolder.time = view.findViewById(R.id.need_time);
            viewHolder.state = view.findViewById(R.id.need_state);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.title.setText(need.getTitle());
        viewHolder.time.setText(need.getTime());
        viewHolder.state.setText(need.getState());
        return view;
    }

    class ViewHolder {
        TextView title;
        TextView time;
        TextView state;
    }
}
