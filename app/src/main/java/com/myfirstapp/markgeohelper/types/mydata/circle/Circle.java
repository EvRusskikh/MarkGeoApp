package com.myfirstapp.markgeohelper.types.mydata.circle;

import com.myfirstapp.markgeohelper.types.Point;
import com.myfirstapp.markgeohelper.types.mydata.MyData;

import java.util.List;
import java.util.UUID;

import lombok.Data;

@Data
public class Circle implements MyData {
    private UUID id;
    private String name;
    private RoundCenter midCenter;
    private double midRadius;
    private List<RoundPoint> points;
    private List<RoundCenter> centers;
}
