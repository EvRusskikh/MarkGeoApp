package com.myfirstapp.markgeohelper.count_interface;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.myfirstapp.markgeohelper.R;
import com.myfirstapp.markgeohelper.countoperations.CountPointOfLane;
import com.myfirstapp.markgeohelper.datacontext.baselinecontext.ActivityListBL;
import com.myfirstapp.markgeohelper.exceptions.input.IncorrectInputException;
import com.myfirstapp.markgeohelper.recycleview.SpinAdapter;
import com.myfirstapp.markgeohelper.repositories.BaseLineRepository;
import com.myfirstapp.markgeohelper.repositories.repint.Repository;
import com.myfirstapp.markgeohelper.types.Point;
import com.myfirstapp.markgeohelper.types.mydata.line.BaseLine;
import com.myfirstapp.markgeohelper.types.mydata.line.Line;
import com.myfirstapp.markgeohelper.types.mydata.line.LinePoint;
import com.myfirstapp.markgeohelper.utils.CheckInput;


public class FragCountPointLane extends Fragment implements View.OnClickListener {
    private EditText xText;
    private EditText yText;
    private EditText hText;
    private TextView xView;
    private TextView yView;
    private TextView hView;
    private TextView radView;
    private TextView gor_lengthView;
    private TextView lengthView;
    private TextView gorDevView;
    private TextView verDevView;

    private Button choose_bl;
    private Button countBtn;

    private SpinAdapter<BaseLine> spinAdapter;
    private Spinner spinner;
    private Repository repository;

    private View view;

    private BaseLine baseForCount;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_count_point_lane, null);
        repository = BaseLineRepository.getRepository(getActivity());
        addSpinner();
        addEditTexts();
        addViews();
        addButtons();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (spinAdapter != null) {
            spinAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.choose_bl:
                Intent intent = new Intent(getActivity(), ActivityListBL.class);
                startActivity(intent);
                break;
            case R.id.fr_c_bl_btn_count:
                updateResults();
                break;
        }
    }

    private void updateResults() {
        CountPointOfLane countPointOfLane = new CountPointOfLane();
        try {
            LinePoint point = getPoint();
            LinePoint linePoint = countPointOfLane.getPointWithParams(baseForCount, point);
            updateTV(linePoint);
        } catch (NullPointerException ex) {
            Toast.makeText(getActivity(), "Выберите БЛ", Toast.LENGTH_SHORT).show();
        } catch (IncorrectInputException e) {
            Toast.makeText(getActivity(), "Введите корректные данные", Toast.LENGTH_SHORT).show();
        }

    }

    private LinePoint getPoint() {
        CheckInput input = new CheckInput();
        LinePoint point = new LinePoint();
        point.setX(input.chekDouble(xText, xView));
        point.setY(input.chekDouble(yText, yView));
        point.setH(input.chekDouble(hText, hView));
        return point;
    }

    private void updateTV(LinePoint point) {
        radView.setText(String.valueOf(point.getRadius()));
        gor_lengthView.setText(String.valueOf(point.getGorPK()));
        lengthView.setText(String.valueOf(point.getPk()));
        gorDevView.setText(String.valueOf(point.getGorDev()));
        verDevView.setText(String.valueOf(point.getVerDev()));
    }


    private void addEditTexts() {
        xText = view.findViewById(R.id.fr_c_bl_x_text);
        yText = view.findViewById(R.id.fr_c_bl_y_text);
        hText = view.findViewById(R.id.fr_c_bl_h_text);
    }

    private void addViews() {
        xView = view.findViewById(R.id.fr_c_bl_tv_x_alert);
        xView.setVisibility(View.INVISIBLE);

        yView = view.findViewById(R.id.fr_c_bl_tv_y_alert);
        yView.setVisibility(View.INVISIBLE);

        hView = view.findViewById(R.id.fr_c_bl_tv_h_alert);
        hView.setVisibility(View.INVISIBLE);

        radView = view.findViewById(R.id.fr_c_bl_tv_radius);
        gor_lengthView = view.findViewById(R.id.fr_c_bl_tv_gor_length);
        lengthView = view.findViewById(R.id.fr_c_bl_tv_absolute_length);
        gorDevView = view.findViewById(R.id.fr_c_bl_tv_gor_dev);
        verDevView = view.findViewById(R.id.fr_c_bl_tv_ver_dev);
    }

    private void addButtons() {
        choose_bl = view.findViewById(R.id.choose_bl);
        choose_bl.setOnClickListener(this);
        countBtn = view.findViewById(R.id.fr_c_bl_btn_count);
        countBtn.setOnClickListener(this);
    }


    private void addSpinner() {
        spinAdapter = new SpinAdapter<BaseLine>(getActivity(),
                android.R.layout.simple_spinner_item, repository.getList());
        spinner = view.findViewById(R.id.fr_c_bl_spinner_bl_choose);
        spinAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                baseForCount = spinAdapter.getItem(position);
                spinner.setSelection(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
