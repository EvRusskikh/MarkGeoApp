package com.myfirstapp.markgeohelper.recycleview;

import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;


import androidx.recyclerview.widget.RecyclerView;

import com.myfirstapp.markgeohelper.types.mydata.MyData;

public abstract class DataHolder<T extends MyData> extends RecyclerView.ViewHolder
        implements View.OnClickListener{

    public DataHolder(LayoutInflater inflater, ViewGroup parent, int layout){
        super(inflater.inflate(layout,parent,false));
    }
    protected abstract void bind(T type);
}
