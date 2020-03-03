package com.myfirstapp.markgeohelper.datacontext.roundcentercontext;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.myfirstapp.markgeohelper.R;
import com.myfirstapp.markgeohelper.datacontext.benchmarkcontext.ActivityItemBenchMark;
import com.myfirstapp.markgeohelper.recycleview.DataAdapter;
import com.myfirstapp.markgeohelper.recycleview.DataHolder;
import com.myfirstapp.markgeohelper.repositories.RoundPointRepository;
import com.myfirstapp.markgeohelper.types.mydata.MyData;
import com.myfirstapp.markgeohelper.types.mydata.circle.RoundPoint;
import com.myfirstapp.markgeohelper.types.mydata.elevation.BenchMark;

import java.util.List;

public class FragListPointR extends Fragment {
    private RecyclerView resView;
    private RoundPointAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recycle_view,container,false);
        resView = view.findViewById(R.id.recycle_view);
        resView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
        return view;
    }

    private void updateUI() {
        RoundPointRepository repository = RoundPointRepository.getRepository(getActivity());
        List<RoundPoint> points = repository.getList();
        if(adapter == null){
            adapter = new RoundPointAdapter();
            adapter.setItems(points);
            resView.setAdapter(adapter);
        }else {
            adapter.notifyDataSetChanged();
        }
    }
    private class PointrHolder extends DataHolder<RoundPoint> {
        private TextView nameView;
        private RoundPoint point;

        @Override
        protected void bind(RoundPoint point) {
            this.point = point;
            nameView.setText(point.getName());
        }

        @Override
        public void onClick(View v) {
            Intent intent = ActivityItemPointR.newIntent(getActivity(), point.getId());
            startActivity(intent);
        }

        public PointrHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater, parent, R.layout.view_holder_item);
            nameView = itemView.findViewById(R.id.it_tv_name);
            itemView.setOnClickListener(this);
        }
    }
    private class RoundPointAdapter extends DataAdapter<RoundPoint,PointrHolder>{

        @NonNull
        @Override
        public PointrHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new PointrHolder(layoutInflater, parent);
        }
    }

}
