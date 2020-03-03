package com.myfirstapp.markgeohelper.utils;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.myfirstapp.markgeohelper.exceptions.input.IncorrectInputException;

public class CheckInput {
    public Double chekDouble(EditText editText, TextView view){
        String text = editText.getText().toString();
        try {
            double result = Double.parseDouble(text);
            view.setVisibility(View.INVISIBLE);
            return result;
        }catch (NumberFormatException ex){
            view.setVisibility(View.VISIBLE);
            throw new IncorrectInputException(text + " не число");
        }
    }
    public String chekString(EditText editText, TextView view){
        String text = editText.getText().toString();
        if(text.equals("")){
            view.setVisibility(View.VISIBLE);
            throw new IncorrectInputException("имя не может быть пустым");
        }else {
            view.setVisibility(View.INVISIBLE);
            return text;
        }
    }
}
