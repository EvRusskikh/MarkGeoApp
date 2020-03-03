package com.myfirstapp.markgeohelper.datacontext.baselinecontext;

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
import com.myfirstapp.markgeohelper.repositories.BaseLineRepository;

import com.myfirstapp.markgeohelper.types.mydata.line.BaseLine;

import java.util.List;

public class FragmentListBL extends Fragment {
    private RecyclerView resView;
    private BaseLineAdapter baseLineAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_recycle_view,container,false);
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
    public void updateUI(){
        BaseLineRepository repository = BaseLineRepository.getRepository(getActivity());
        List<BaseLine> baseLines = repository.getList();

        if (baseLineAdapter==null){
            baseLineAdapter = new BaseLineAdapter();
            baseLineAdapter.setItems(baseLines);
            resView.setAdapter(baseLineAdapter);
        }else {
            baseLineAdapter.notifyDataSetChanged();
        }
    }

    private class BaseLineHolder extends DataHolder<BaseLine> {
        private TextView nameView;
        private BaseLine baseLine;

        public BaseLineHolder(LayoutInflater inflater, ViewGroup parent){
            super(inflater,parent,R.layout.view_holder_item);
            itemView.setOnClickListener(this);
            nameView = itemView.findViewById(R.id.it_tv_name);

        }

        @Override
        protected void bind(BaseLine baseLine) {
            this.baseLine = baseLine;
            nameView.setText(baseLine.getName());
        }

        @Override
        public void onClick(View v) {
            Intent intent = ActivityItemBL.newIntent(getActivity(),baseLine.getId());
            startActivity(intent);
        }


    }

    private class BaseLineAdapter extends DataAdapter<BaseLine, BaseLineHolder> {
        @NonNull
        @Override
        public BaseLineHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new BaseLineHolder(layoutInflater, parent);
        }
    }
}
