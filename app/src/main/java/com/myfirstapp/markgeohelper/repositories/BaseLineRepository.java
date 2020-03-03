package com.myfirstapp.markgeohelper.repositories;

import android.content.Context;

import com.myfirstapp.markgeohelper.repositories.repint.RepositoryImpl;
import com.myfirstapp.markgeohelper.types.mydata.line.BaseLine;

import java.util.ArrayList;
import java.util.List;

public class BaseLineRepository extends RepositoryImpl<BaseLine> {
    private static BaseLineRepository repository;
    private static List<BaseLine> baseLines;

    public static BaseLineRepository getRepository(Context context) {
        if (repository == null){
            baseLines = new ArrayList<>();
            repository = new BaseLineRepository(context, baseLines);
        }
        return repository;
    }

    @Override
    public void addItem() {
        baseLines.add(new BaseLine());
    }
    private BaseLineRepository(Context context, List<BaseLine> baseLines){
        super(baseLines);
    }
}
