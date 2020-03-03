package com.myfirstapp.markgeohelper.repositories;

import android.content.Context;

import com.myfirstapp.markgeohelper.repositories.repint.RepositoryImpl;
import com.myfirstapp.markgeohelper.types.mydata.circle.RoundPoint;

import java.util.ArrayList;
import java.util.List;

public class RoundPointRepository extends RepositoryImpl<RoundPoint> {
    private static RoundPointRepository repository;
    private static List<RoundPoint> items;
    @Override
    public void addItem() {
        items.add(new RoundPoint());
    }

    public static RoundPointRepository getRepository(Context context) {
        if (repository == null){
            items = new ArrayList<>();
            repository = new RoundPointRepository(items,context);
        }
        return repository;
    }

    private RoundPointRepository(List<RoundPoint> items, Context context) {
        super(items);
    }
}
