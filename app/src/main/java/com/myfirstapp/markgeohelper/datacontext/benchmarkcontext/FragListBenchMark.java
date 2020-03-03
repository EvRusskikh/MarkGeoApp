package com.myfirstapp.markgeohelper.datacontext.benchmarkcontext;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.myfirstapp.markgeohelper.R;
import com.myfirstapp.markgeohelper.recycleview.DataAdapter;
import com.myfirstapp.markgeohelper.recycleview.DataHolder;
import com.myfirstapp.markgeohelper.repositories.BenchMarkRepository;
import com.myfirstapp.markgeohelper.types.mydata.MyData;
import com.myfirstapp.markgeohelper.types.mydata.elevation.BenchMark;

import java.util.List;


public class FragListBenchMark extends Fragment {
    private RecyclerView resView;
    private BenchMarkAdapter bmAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recycle_view, container, false);
        resView = view.findViewById(R.id.recycle_view);
        resView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }
    public void updateUI() {
        BenchMarkRepository repository = BenchMarkRepository.getRepository(getActivity());
        List<BenchMark> benchMarks = repository.getList();
        if (bmAdapter == null) {
            bmAdapter = new BenchMarkAdapter();
            bmAdapter.setItems(benchMarks);
            resView.setAdapter(bmAdapter);
        } else {
            bmAdapter.notifyDataSetChanged();
        }
    }

    private class BenchMarkHolder extends DataHolder<BenchMark> {

        private TextView nameView;
        private TextView elevView;
        private BenchMark benchMark;

        public BenchMarkHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater, parent, R.layout.view_holder_item);
            nameView = itemView.findViewById(R.id.it_tv_name);
            elevView = itemView.findViewById(R.id.it_tv_params);
            itemView.setOnClickListener(this);
        }

        public void bind(BenchMark benchMark) {
            this.benchMark = benchMark;
            nameView.setText(benchMark.getName());
            elevView.setText(String.valueOf(benchMark.getHeight()));
        }

        @Override
        public void onClick(View v) {
            Intent intent = ActivityItemBenchMark.newIntent(getActivity(), benchMark.getId());
            startActivity(intent);
        }
    }

    private class BenchMarkAdapter extends DataAdapter<BenchMark, BenchMarkHolder> {
        @NonNull
        @Override
        public BenchMarkHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new BenchMarkHolder(layoutInflater, parent);
        }
    }

}