package com.example.nagisv3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class Custom extends BaseAdapter {
    private ArrayList<Model>modelArrayList;
    private Context context;
    private int layout;

    public Custom(ArrayList<Model> modelArrayList, Context context, int layout) {
        this.modelArrayList = modelArrayList;
        this.context = context;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return modelArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return modelArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder{

        TextView idtxt, titletxt, bodytxt;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = layoutInflater.inflate(layout, null);
        viewHolder.idtxt = convertView.findViewById(R.id.idtxt);
        viewHolder.titletxt = convertView.findViewById(R.id.titletxt);
        viewHolder.bodytxt = convertView.findViewById(R.id.bodytxt);
        Model model = modelArrayList.get(position);
        viewHolder.idtxt.setText(model.getId());
        viewHolder.titletxt.setText(model.getTitle());
        viewHolder.bodytxt.setText(model.getBody());
        return convertView;
    }
}
