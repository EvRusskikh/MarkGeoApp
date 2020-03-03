package com.myfirstapp.markgeohelper.datacontext.roundcentercontext;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.myfirstapp.markgeohelper.R;
import com.myfirstapp.markgeohelper.datacontext.abstractfragment.ItemFrag;
import com.myfirstapp.markgeohelper.repositories.RoundPointRepository;
import com.myfirstapp.markgeohelper.types.mydata.circle.RoundPoint;
import com.myfirstapp.markgeohelper.utils.CheckInput;

import java.util.UUID;

public class FragItemPointR extends ItemFrag implements View.OnClickListener {
    private static final String ARG_POINTR_ID = "point_r_id";
    private RoundPoint point;
    private Button saveBtn;
    private EditText nameText;
    private TextView nameReport;
    private EditText xText;
    private TextView xReport;
    private EditText yText;
    private TextView yReport;
    private View v;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID prId = (UUID) getArguments().getSerializable(ARG_POINTR_ID);
        point = RoundPointRepository.getRepository(getActivity()).getItem(prId);
    }

    public static FragItemPointR newInstance(UUID prId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_POINTR_ID, prId);
        FragItemPointR fragItemPointR = new FragItemPointR();
        fragItemPointR.setArguments(args);
        return fragItemPointR;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_item_round, container, false);
        createButtonView();
        createEditText();
        createTextView();
        upDateView();
        return v;
    }

    @Override
    protected String changeData() {
        CheckInput input = new CheckInput();
        point.setName(input.chekString(nameText,nameReport));
        point.setX(input.chekDouble(xText,xReport));
        point.setY(input.chekDouble(yText,yReport));
        return "Данные обновлены";
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(getActivity(),updateData(),Toast.LENGTH_SHORT).show();
    }
    private void upDateView(){
        if (point.getName() != null){
            nameText.setText(point.getName());
            xText.setText(String.valueOf(point.getX()));
            yText.setText(String.valueOf(point.getY()));
        }
    }
    private void createEditText() {
        nameText = v.findViewById(R.id.fr_it_rp_text_name);
        xText =  v.findViewById(R.id.fr_it_rp_text_x);
        yText =  v.findViewById(R.id.fr_it_rp_text_y);
    }

    private void createTextView() {
        xReport = v.findViewById(R.id.fr_it_rp_x_alert);
        xReport.setVisibility(View.INVISIBLE);
        yReport = v.findViewById(R.id.fr_it_rp_y_alert);
        yReport.setVisibility(View.INVISIBLE);
        nameReport = v.findViewById(R.id.fr_it_rp_name_alert);
        nameReport.setVisibility(View.INVISIBLE);
    }

    private void createButtonView() {
        saveBtn = v.findViewById(R.id.fr_it_rp_btn_save);
        saveBtn.setOnClickListener(this);
    }
}
