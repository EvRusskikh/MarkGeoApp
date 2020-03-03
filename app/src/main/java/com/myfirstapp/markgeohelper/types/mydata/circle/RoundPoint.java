package com.myfirstapp.markgeohelper.types.mydata.circle;

import com.myfirstapp.markgeohelper.types.Point;
import com.myfirstapp.markgeohelper.types.mydata.MyData;

import java.util.UUID;

import lombok.Data;

@Data
public class RoundPoint extends Point implements MyData {
    private UUID id;
    private double radius;
    public RoundPoint() {
        id = UUID.randomUUID();
    }
}
