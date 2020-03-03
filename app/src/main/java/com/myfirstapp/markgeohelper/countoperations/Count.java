package com.myfirstapp.markgeohelper.countoperations;



import java.text.DecimalFormat;
import java.util.List;

public abstract class Count {
    public double format(double request){
        DecimalFormat formater = new DecimalFormat("#.000");
        return Double.valueOf(formater.format(request).replace(",","."));
    }
    public double getRound(List<Double> params){
        double countX = 0;
        int count = 0;
        for (Double param : params){
            countX += param;
            count++;
        }
        return count == 0 ? 0 : format(countX/count);
    }


}
