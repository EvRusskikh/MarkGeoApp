package com.myfirstapp.markgeohelper.count_interface;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputLayout;
import com.myfirstapp.markgeohelper.R;
import com.myfirstapp.markgeohelper.countoperations.countElevStr.CountElevStr;
import com.myfirstapp.markgeohelper.countoperations.countElevStr.CountPointElevStrInt;
import com.myfirstapp.markgeohelper.countoperations.countElevStr.CountStaffStr;
import com.myfirstapp.markgeohelper.recycleview.SpinAdapter;
import com.myfirstapp.markgeohelper.repositories.BaseLineRepository;
import com.myfirstapp.markgeohelper.repositories.BenchMarkRepository;
import com.myfirstapp.markgeohelper.types.mydata.elevation.BenchMark;
import com.myfirstapp.markgeohelper.utils.CheckInput;
import com.myfirstapp.markgeohelper.datacontext.benchmarkcontext.ActivityListBenchMark;
import com.myfirstapp.markgeohelper.exceptions.input.IncorrectInputException;

import static androidx.constraintlayout.widget.Constraints.TAG;


public class FragCountElev extends Fragment implements View.OnClickListener {

    private EditText benchMarkElevT;
    private EditText benchMarkStaffT;
    private EditText pointParamT;
    private Button countBN;
    private Button addBMdataBN;
    private TextView title;
    private TextView result;
    private TextView resultText;
    private TextView bmElevAlert;
    private TextView bmStaffAlert;
    private TextView pointAlert;
    private RadioButton countElevRB;
    private RadioButton countStaffRB;
    private CountPointElevStrInt countInt;
    private TextInputLayout layout;
    private Spinner spinner;
    private SpinAdapter<BenchMark> adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_count_point_elevation, container, false);
        createEditText(view);
        createTextView(view);
        createRadio(view);
        createButton(view);
        createSpinner(view);
        countInt = new CountElevStr();
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case (R.id.c_el_bn_count):
                result.setText(count());
                break;
            case (R.id.c_el_rb_count_point_elev):
                title.setText(R.string.Title_text_Count_Point_Height);
                layout.setHint("Отчет по рейке на точке,мм");
                resultText.setText(R.string.Result_point_M);
                countInt = new CountElevStr();
                break;
            case (R.id.c_el_rb_count_point_staff):
                title.setText(R.string.Title_text_Count_Point_Report);
                layout.setHint("Отметка точки,м");
                resultText.setText(R.string.Result_point_MM);
                countInt = new CountStaffStr();
                break;
            case (R.id.c_el_bn_add_bm_data):
                Intent intent = new Intent(getActivity(), ActivityListBenchMark.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }

    private String count() {
        try {
            CheckInput input = new CheckInput();
            double bmElev = input.chekDouble(benchMarkElevT, bmElevAlert);
            double bmHeight = input.chekDouble(benchMarkStaffT, bmStaffAlert);
            double pointParam = input.chekDouble(pointParamT, pointAlert);
            return count(bmElev, bmHeight, pointParam);
        } catch (IncorrectInputException ex) {
            Log.d(TAG, "count: impossible");
            return "";
        }
    }

    private String count(double bmElev, double bmStaff, double pointParam) {
        return countInt.count(bmElev, bmStaff, pointParam);
    }

    private void createEditText(View view) {
        benchMarkElevT = view.findViewById(R.id.c_el_et_bench_mark_el);
        benchMarkStaffT = view.findViewById(R.id.c_el_et_bench_mark_staff);
        pointParamT = view.findViewById(R.id.c_el_et_point);
    }

    private void createTextView(View view) {
        layout = view.findViewById(R.id.c_el_lt_point);
        title = view.findViewById(R.id.c_el_tv_title);
        bmElevAlert = view.findViewById(R.id.c_el_tv_bm_elev);
        bmElevAlert.setVisibility(View.INVISIBLE);
        bmStaffAlert = view.findViewById(R.id.c_el_tv_bm_staff);
        bmStaffAlert.setVisibility(View.INVISIBLE);
        pointAlert = view.findViewById(R.id.c_el_tv_point);
        pointAlert.setVisibility(View.INVISIBLE);

        result = view.findViewById(R.id.c_el_tv_result);
        resultText = view.findViewById(R.id.c_el_tv_result_desc);
    }

    private void createButton(View view) {
        countBN = view.findViewById(R.id.c_el_bn_count);
        countBN.setOnClickListener(this);

        addBMdataBN = view.findViewById(R.id.c_el_bn_add_bm_data);
        addBMdataBN.setOnClickListener(this);
    }

    private void createRadio(View view) {
        countElevRB = view.findViewById(R.id.c_el_rb_count_point_elev);
        countElevRB.setOnClickListener(this);

        countStaffRB = view.findViewById(R.id.c_el_rb_count_point_staff);
        countStaffRB.setOnClickListener(this);
    }
    private void createSpinner(View view){
        BenchMarkRepository repository = BenchMarkRepository.getRepository(getActivity());
        spinner = view.findViewById(R.id.c_el_spinner_bm_choose);
        adapter = new SpinAdapter<>(
                getActivity(),android.R.layout.simple_spinner_item,repository.getList());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                benchMarkElevT.setText(String.valueOf(adapter.getItem(position).getHeight()));
                spinner.setSelection(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

    }
}
