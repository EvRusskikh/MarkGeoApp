package com.myfirstapp.markgeohelper.types.mydata.line;

import com.myfirstapp.markgeohelper.types.mydata.MyData;

import java.util.UUID;

public class BaseLine extends Line implements MyData {
    private UUID id;
    public BaseLine(){
        id = UUID.randomUUID();
    }
    @Override
    public UUID getId() {
        return id;
    }
}
