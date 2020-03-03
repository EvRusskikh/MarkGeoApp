package com.myfirstapp.markgeohelper.countoperations.countElevStr;

import com.myfirstapp.markgeohelper.countoperations.CountLeveling;

public class CountStaffStr implements CountPointElevStrInt {
    @Override
    public String count(double bmHeight, double bmStaff, double pointElev) {
        CountLeveling countLeveling = new CountLeveling();
        String staff = String.valueOf(countLeveling.getStaff(bmHeight, bmStaff, pointElev));
        int point = staff.indexOf(".");
        if (staff.substring(point).equals(".0")){
            return staff.substring(0,point);
        } else return staff;
    }
}
