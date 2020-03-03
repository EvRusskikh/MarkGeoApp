package com.myfirstapp.markgeohelper.types.mydata.elevation;


import com.myfirstapp.markgeohelper.types.mydata.MyData;

import java.util.UUID;

public class BenchMark extends LevelPoint implements MyData {
    UUID id;
    public BenchMark(){
        id = UUID.randomUUID();
    }
    @Override
    public UUID getId() {
        return id;
    }
}
