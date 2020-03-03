package com.myfirstapp.markgeohelper.countoperations.countElevStr;

import com.myfirstapp.markgeohelper.countoperations.CountLeveling;



public class CountElevStr implements CountPointElevStrInt {
    @Override
    public String count(double bmHeight, double bmStaff, double pointStaff) {
        CountLeveling countLeveling = new CountLeveling();
        String height = String.valueOf(countLeveling.getHeight(bmHeight, bmStaff, pointStaff));
        int point = height.indexOf(".");
        StringBuilder sb = new StringBuilder();
        if (height.substring(point).length() < 3){
            sb.append(height);
            sb.append("00");
            return sb.toString();
        } else if (height.substring(point).length() < 4) {
            sb.append(height);
            sb.append("0");
            return sb.toString();
        } else{
            return height;
        }
    }
}
