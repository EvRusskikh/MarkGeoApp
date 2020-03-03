package com.myfirstapp.markgeohelper.repositories;

import android.content.Context;

import com.myfirstapp.markgeohelper.repositories.repint.RepositoryImpl;
import com.myfirstapp.markgeohelper.types.mydata.elevation.BenchMark;

import java.util.ArrayList;
import java.util.List;

public class BenchMarkRepository extends RepositoryImpl<BenchMark> {
    private static List<BenchMark> items;
    private static BenchMarkRepository repository;

    public static BenchMarkRepository getRepository(Context context) {
        if (repository == null){
            items = new ArrayList<>();
            repository = new BenchMarkRepository(items,context);
        }
        repository.getList().size();
        return repository;
    }

    @Override
    public void addItem() {
        items.add(new BenchMark());
    }
    private BenchMarkRepository(List<BenchMark> items, Context context){
        super(items);
    }
}
