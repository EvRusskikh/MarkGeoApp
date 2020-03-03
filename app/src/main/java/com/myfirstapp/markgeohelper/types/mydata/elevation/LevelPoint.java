package com.myfirstapp.markgeohelper.types.mydata.elevation;

import androidx.annotation.NonNull;

import lombok.Data;

@Data
public class LevelPoint {
    private String name;
    private double height;
    @NonNull
    @Override
    public String toString() {
        return name;
    }
}
