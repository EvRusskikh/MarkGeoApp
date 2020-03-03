package com.myfirstapp.markgeohelper.recycleview;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.myfirstapp.markgeohelper.types.mydata.MyData;

import java.util.List;

public class SpinAdapter<T extends MyData> extends ArrayAdapter<T> {
    List<T> objects;
    Context context;
    public SpinAdapter(@NonNull Context context, int resource, @NonNull List<T> objects) {
        super(context, resource, objects);
        this.objects = objects;
        this.context = context;
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Nullable
    @Override
    public T getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        TextView label = (TextView) super.getView(position, convertView, parent);
        label.setTextColor(Color.BLACK);

        label.setText(objects.get(position).getName());
        return label;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        TextView label = (TextView) super.getDropDownView(position, convertView, parent);
        label.setTextColor(Color.BLACK);
        label.setText(objects.get(position).getName());
        return label;
    }
}
