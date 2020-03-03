package com.myfirstapp.markgeohelper.datacontext.benchmarkcontext;

import android.content.Intent;

import androidx.fragment.app.Fragment;


import com.myfirstapp.markgeohelper.datacontext.activityinterface.SingleFragmentListActivity;
import com.myfirstapp.markgeohelper.repositories.BenchMarkRepository;
import com.myfirstapp.markgeohelper.types.mydata.elevation.BenchMark;

public class ActivityListBenchMark extends SingleFragmentListActivity {
    @Override
    protected Fragment createFragment() {
        return new FragListBenchMark();
    }

    @Override
    protected void goToActivity() {
        BenchMarkRepository repository = BenchMarkRepository.getRepository(this);
        repository.addItem();
        BenchMark benchMark = repository.getLastItem();
        Intent intent = ActivityItemBenchMark.newIntent(this, benchMark.getId());
        startActivity(intent);
    }

}
