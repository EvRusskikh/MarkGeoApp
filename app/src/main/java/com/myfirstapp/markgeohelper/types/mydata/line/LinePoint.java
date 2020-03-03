package com.myfirstapp.markgeohelper.types.mydata.line;

import com.myfirstapp.markgeohelper.types.Point;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class LinePoint extends Point {
    private double radius;
    private double pk;
    private double gorPK;
    private double gorDev;
    private double verDev;
}
