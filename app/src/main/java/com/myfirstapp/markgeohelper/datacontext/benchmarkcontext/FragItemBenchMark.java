package com.myfirstapp.markgeohelper.datacontext.benchmarkcontext;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.myfirstapp.markgeohelper.R;

import com.myfirstapp.markgeohelper.utils.CheckInput;
import com.myfirstapp.markgeohelper.datacontext.abstractfragment.ItemFrag;
import com.myfirstapp.markgeohelper.repositories.BenchMarkRepository;
import com.myfirstapp.markgeohelper.types.mydata.elevation.BenchMark;


import java.util.UUID;


public class FragItemBenchMark extends ItemFrag {
    private EditText nameText;
    private EditText elevText;

    private TextView nameView;
    private TextView elevView;

    private Button saveBtn;
    private BenchMark benchMark;

    private static final String ARG_BM_ID = "lev_ref_id";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID benchMarkId = (UUID) getArguments().getSerializable(ARG_BM_ID);
        benchMark = BenchMarkRepository.getRepository(getActivity()).getItem(benchMarkId);
    }

    public static FragItemBenchMark newInstance(UUID benchMarkfId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_BM_ID, benchMarkfId);
        FragItemBenchMark fragment = new FragItemBenchMark();
        fragment.setArguments(args);
        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_item_bench_mark, container, false);
        createEditText(v);
        createTextView(v);
        createButton(v);
        upDateView();

        return v;
    }

    private void upDateView() {
        String name = String.valueOf(benchMark.getName());
        String height = String.valueOf(benchMark.getHeight());
        if (!name.equals("null")) {
            nameText.setText(name);
        }
        if (!height.equals("null")) {
            elevText.setText(String.valueOf(benchMark.getHeight()));
        }
    }

    public String changeData(){
        CheckInput chek = new CheckInput();
        String name = chek.chekString(nameText,nameView);
        double height = chek.chekDouble(elevText,elevView);
        benchMark.setName(name);
        benchMark.setHeight(height);
        return "Данные успешно обновлены";
    }

    private void createEditText(View v) {
        nameText = v.findViewById(R.id.fr_it_bm_name);
        elevText = v.findViewById(R.id.fr_it_bm_elev);
    }

    private void createTextView(View v) {
        nameView = v.findViewById(R.id.fr_it_bm_name_alert);
        nameView.setVisibility(View.INVISIBLE);
        elevView = v.findViewById(R.id.fr_it_bm_elev_alert);
        elevView.setVisibility(View.INVISIBLE);
    }

    private void createButton(View v) {
        saveBtn = v.findViewById(R.id.fr_it_bm_btn_save);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(getActivity(),updateData(),Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }
}



