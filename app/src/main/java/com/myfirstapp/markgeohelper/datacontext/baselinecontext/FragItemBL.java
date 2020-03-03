package com.myfirstapp.markgeohelper.datacontext.baselinecontext;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import androidx.fragment.app.Fragment;

import com.myfirstapp.markgeohelper.R;
import com.myfirstapp.markgeohelper.datacontext.abstractfragment.ItemFrag;
import com.myfirstapp.markgeohelper.repositories.BaseLineRepository;
import com.myfirstapp.markgeohelper.types.Point;
import com.myfirstapp.markgeohelper.types.mydata.line.BaseLine;
import com.myfirstapp.markgeohelper.utils.CheckInput;

import java.util.Map;
import java.util.UUID;


public class FragItemBL extends ItemFrag implements View.OnClickListener {
    private EditText x1text;
    private EditText y1text;
    private EditText h1text;
    private EditText x2text;
    private EditText y2text;
    private EditText h2text;
    private EditText blNameText;
    private Button saveBtn;
    private TextView viewX1;
    private TextView viewY1;
    private TextView viewH1;
    private TextView viewX2;
    private TextView viewY2;
    private TextView viewH2;
    private TextView viewBLname;
    private BaseLine baseLine;
    private static final String ARG_BL_ID = "bl_id";
    private CheckInput check;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID blid = (UUID) getArguments().getSerializable(ARG_BL_ID);
        baseLine= BaseLineRepository.getRepository(getActivity()).getItem(blid);
        check = new CheckInput();
    }
    public static FragItemBL newInstance(UUID blid) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_BL_ID,blid);
        FragItemBL fragmentItemBL = new FragItemBL();
        fragmentItemBL.setArguments(args);
        return fragmentItemBL;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_item_bl, container, false);
        createEditText(v);
        createTextView(v);
        createButton(v);
        upDateView();
        return v;
    }

    @Override
    protected String changeData() {
        String name = check.chekString(blNameText,viewBLname);
        Point pOne = getPone();
        Point pTwo = getPtwo();
        baseLine.setName(name);
        baseLine.setPOne(pOne);
        baseLine.setPTwo(pTwo);
        return "Данные успешно обновлены";
    }
    private Point getPone(){
        Point pOne = new Point();
        pOne.setX(check.chekDouble(x1text,viewX1));
        pOne.setY(check.chekDouble(y1text,viewY1));
        pOne.setH(check.chekDouble(h1text,viewH1));
        return pOne;
    }
    private Point getPtwo(){
        Point pTwo = new Point();
        pTwo.setX(check.chekDouble(x2text,viewX2));
        pTwo.setY(check.chekDouble(y2text,viewY2));
        pTwo.setH(check.chekDouble(h2text,viewH2));
        return pTwo;
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(getActivity(),updateData(),Toast.LENGTH_SHORT).show();
    }
    private void upDateView(){
        Point point1 = baseLine.getPOne();
        Point point2 = baseLine.getPTwo();
        String name = baseLine.getName();
        if (name != null){
            blNameText.setText(name);
        }
        if (point1 != null){
            x1text.setText(String.valueOf(point1.getX()));
            y1text.setText(String.valueOf(point1.getY()));
            h1text.setText(String.valueOf(point1.getH()));
        }
        if (point2 != null){
            x2text.setText(String.valueOf(point2.getX()));
            y2text.setText(String.valueOf(point2.getY()));
            h2text.setText(String.valueOf(point2.getH()));
        }
    }
    private void createEditText(View v){
        blNameText= v.findViewById(R.id.fr_it_bl_bl_name);
        x1text= v.findViewById(R.id.fr_it_bl_x1);
        y1text= v.findViewById(R.id.fr_it_bl_y1);
        h1text= v.findViewById(R.id.fr_it_bl_h1);
        x2text= v.findViewById(R.id.fr_it_bl_x2);
        y2text= v.findViewById(R.id.fr_it_bl_y2);
        h2text= v.findViewById(R.id.fr_it_bl_h2);
    }
    private void createTextView(View v){
        viewBLname =  v.findViewById(R.id.fr_it_bl_tv_bl_name_alert);
        viewBLname.setVisibility(View.INVISIBLE);
        viewX1= v.findViewById(R.id.fr_it_bl_tv_x1_alert);
        viewX1.setVisibility(View.INVISIBLE);
        viewY1= v.findViewById(R.id.fr_it_bl_tv_y1_alert);
        viewY1.setVisibility(View.INVISIBLE);
        viewH1= v.findViewById(R.id.fr_it_bl_tv_h1_alert);
        viewH1.setVisibility(View.INVISIBLE);
        viewX2= v.findViewById(R.id.fr_it_bl_tv_x2_alert);
        viewX2.setVisibility(View.INVISIBLE);
        viewY2= v.findViewById(R.id.fr_it_bl_tv_y2_alert);
        viewY2.setVisibility(View.INVISIBLE);
        viewH2= v.findViewById(R.id.fr_it_bl_tv_h2_alert);
        viewH2.setVisibility(View.INVISIBLE);

    }
    private void createButton(View v){
        saveBtn= v.findViewById(R.id.fr_it_bl_btn_save);
        saveBtn.setOnClickListener(this);

    }
}
