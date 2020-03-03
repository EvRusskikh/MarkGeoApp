package com.myfirstapp.markgeohelper.types.mydata.line;

import androidx.annotation.NonNull;

import com.myfirstapp.markgeohelper.types.Point;

import lombok.Data;

@Data
public class Line {
    private String name;
    private Point pOne;
    private Point pTwo;

    @NonNull
    @Override
    public String toString() {
        return name;
    }
}
