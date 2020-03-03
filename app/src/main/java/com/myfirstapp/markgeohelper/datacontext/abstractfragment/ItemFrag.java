package com.myfirstapp.markgeohelper.datacontext.abstractfragment;


import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.myfirstapp.markgeohelper.exceptions.input.IncorrectInputException;

public abstract class ItemFrag extends Fragment {
        protected abstract String changeData();
    protected String updateData() {
        try{
            return changeData();
        } catch (IncorrectInputException ex){
            return wrongData(ex);
        }
    }
    private String wrongData (IncorrectInputException ex){
        StringBuilder sb = new StringBuilder();
        sb.append("Данные не обновлены!");
        sb.append("\n");
        sb.append(ex.getMessage());
        return sb.toString();
    }

}
