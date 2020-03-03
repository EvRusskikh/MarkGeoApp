package com.myfirstapp.markgeohelper.count_interface;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.myfirstapp.markgeohelper.R;
import com.myfirstapp.markgeohelper.countoperations.CountCircle;
import com.myfirstapp.markgeohelper.datacontext.benchmarkcontext.FragListBenchMark;
import com.myfirstapp.markgeohelper.datacontext.roundcentercontext.ActivityItemPointR;
import com.myfirstapp.markgeohelper.recycleview.DataAdapter;
import com.myfirstapp.markgeohelper.recycleview.DataHolder;
import com.myfirstapp.markgeohelper.repositories.BaseLineRepository;
import com.myfirstapp.markgeohelper.repositories.RoundPointRepository;
import com.myfirstapp.markgeohelper.repositories.repint.Repository;
import com.myfirstapp.markgeohelper.types.Point;
import com.myfirstapp.markgeohelper.types.mydata.MyData;
import com.myfirstapp.markgeohelper.types.mydata.circle.Circle;
import com.myfirstapp.markgeohelper.types.mydata.circle.RoundPoint;
import com.myfirstapp.markgeohelper.types.mydata.elevation.BenchMark;

import java.util.ArrayList;
import java.util.List;


public class FragCountRoundCenter extends Fragment implements View.OnClickListener{
    private View view;
    private Button add;
    private Button count;
    private RecyclerView pointsView;
    private RecyclerView radsView;
    private RoundPointAdapter adapter;
    private List<RoundPoint> roundPoints;
    private TextView viewXcoord;
    private TextView viewYcoord;
    private TextView viewRadius;
    private RoundPointRepository repository;
    private List<RoundPoint> points;
    private List<RoundPoint> circlePoints;
    private ResultPointAdapter resAdapter;
    @Nullable
    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_count_round_center,container,false);
        repository = RoundPointRepository.getRepository(getActivity());


        createButtons(view);
        createResViews(view);
        updatePointsView();
        createTextView();
        return view;
    }
    public void updatePointsView(){
        points = repository.getList();
        if (adapter == null){
            adapter = new RoundPointAdapter();
            adapter.setItems(points);
            pointsView.setAdapter(adapter);
        } else{
            adapter.notifyDataSetChanged();
        }
    }
    private void createButtons(View v){
        add = v.findViewById(R.id.fr_c_rc_btn_add);
        add.setOnClickListener(this);
        count = v.findViewById(R.id.fr_c_rc_btn_count);
        count.setOnClickListener(this);
    }


    @Override
    public void onResume() {
        super.onResume();
        updatePointsView();
    }
    protected void goToActivity() {
        repository.addItem();
        RoundPoint point = repository.getLastItem();
        Intent intent = ActivityItemPointR.newIntent(getActivity(),point.getId());
        startActivity(intent);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case (R.id.fr_c_rc_btn_add) :
                goToActivity();
                break;
            case (R.id.fr_c_rc_btn_count) :
                countCenters();
                break;
        }
    }
    private void countCenters(){
        if (repository.getList().size()>= 3){
            CountCircle countCircle = new CountCircle();
            Circle circle = countCircle.getCircle(repository.getList());
            updateRads(circle);
            updateCenter(circle);
        } else {
            Toast.makeText(getActivity(),"Необходимо минимум 3 точки",Toast.LENGTH_SHORT).show();
        }
    }
    private void updateRads(Circle circle){
        if (resAdapter == null){
            resAdapter = new ResultPointAdapter();
            resAdapter.setItems(circle.getPoints());
            radsView.setAdapter(resAdapter);
        } else {
            resAdapter.notifyDataSetChanged();
        }

    }
    private void updateCenter(Circle circle){
         viewXcoord.setText(String.valueOf(circle.getMidCenter().getX()));
         viewYcoord.setText(String.valueOf(circle.getMidCenter().getY()));
         viewRadius.setText(String.valueOf(circle.getMidRadius()));
    }

    private class RoundPointsHolder extends DataHolder<RoundPoint> {
        private RoundPoint point;
        private TextView nameView;

        public RoundPointsHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater, parent, R.layout.view_holder_item);
            nameView = itemView.findViewById(R.id.it_tv_name);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent = ActivityItemPointR.newIntent(getActivity(),point.getId());
            startActivity(intent);
        }
        @Override
        protected void bind(RoundPoint point) {
            this.point = point;
            nameView.setText(point.getName());

        }
    }
    private class RoundPointAdapter extends DataAdapter<RoundPoint, RoundPointsHolder> {
        @NonNull
        @Override
        public RoundPointsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new RoundPointsHolder(layoutInflater, parent);
        }
    }
    private class ResultPointsHolder extends DataHolder<RoundPoint> {
        private RoundPoint point;
        private TextView nameView;
        private TextView radiusView;
        public ResultPointsHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater, parent, R.layout.view_holder_rc_item);
            nameView = itemView.findViewById(R.id.it_rc_name);
            radiusView = itemView.findViewById(R.id.it_rc_rad);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
        }
        @Override
        protected void bind(RoundPoint point) {
            this.point = point;
            nameView.setText(point.getName());
            radiusView.setText(String.valueOf(point.getRadius()));
        }
    }
    private class ResultPointAdapter extends DataAdapter<RoundPoint, ResultPointsHolder> {
        @NonNull
        @Override
        public ResultPointsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new ResultPointsHolder(layoutInflater, parent);
        }
    }

    private void createResViews(View view){
        pointsView = view.findViewById(R.id.fr_c_rc_res_view_points);
        pointsView.setLayoutManager(new LinearLayoutManager(getActivity()));
        radsView = view.findViewById(R.id.fr_c_rc_res_view_rads);
        radsView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }
    private void createTextView(){
        viewXcoord = view.findViewById(R.id.fr_c_rc_x_coord_report);
        viewYcoord = view.findViewById(R.id.fr_c_rc_y_coord_report);
        viewRadius = view.findViewById(R.id.fr_c_rc_round_radius_report);
    }

}
