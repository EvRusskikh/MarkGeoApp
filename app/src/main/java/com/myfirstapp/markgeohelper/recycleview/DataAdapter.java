package com.myfirstapp.markgeohelper.recycleview;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.myfirstapp.markgeohelper.types.mydata.MyData;

import java.util.List;



public abstract class DataAdapter<T extends MyData,V extends DataHolder<T>> extends RecyclerView.Adapter<V>{

    private List<T> items;
    @Override
    public void onBindViewHolder(@NonNull V holder, int position) {
        T type = items.get(position);
        holder.bind(type);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(List<T> items) {
        this.items = items;
    }
}
