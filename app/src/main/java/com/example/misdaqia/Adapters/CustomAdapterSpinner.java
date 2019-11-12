package com.example.misdaqia.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.misdaqia.R;

import java.util.ArrayList;

public class CustomAdapterSpinner extends BaseAdapter {

    Context context;
    ArrayList<String> StrList;
    LayoutInflater inflter;
    int layout;

    public CustomAdapterSpinner(Context applicationContext, ArrayList<String> StrList) {
        this.context = applicationContext;
        this.StrList = StrList;
        inflter = (LayoutInflater.from(applicationContext));
        this.layout = layout;
    }

    public CustomAdapterSpinner(Context applicationContext, ArrayList<String> StrList, int layout) {
        this.context = applicationContext;
        this.StrList = StrList;
        inflter = (LayoutInflater.from(applicationContext));
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return StrList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (layout != 0) {
            view = inflter.inflate(layout, null);
        } else {
            view = inflter.inflate(R.layout.custom_spinner_items, null);

        }

        TextView names = (TextView) view.findViewById(R.id.textView_spinner);
        names.setText(StrList.get(i));
        if (i == 0) {
            names.setTextColor(context.getResources().getColor(R.color.tint_gray_line));
        }
        return view;
    }
}