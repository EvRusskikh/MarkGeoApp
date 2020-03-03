package com.myfirstapp.markgeohelper.datacontext.benchmarkcontext;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;


import com.myfirstapp.markgeohelper.datacontext.activityinterface.SingleFragmentItemActivity;
import com.myfirstapp.markgeohelper.types.mydata.elevation.BenchMark;

import java.util.List;
import java.util.UUID;

public class ActivityItemBenchMark extends SingleFragmentItemActivity {

    public static final String EXTRA_LEV__REF_ID =
            "com.example.geodezhelper.lev_ref_id";
    public static Intent newIntent(Context packageContext, UUID levrefId) {
        Intent intent = new Intent(packageContext, ActivityItemBenchMark.class);
        intent.putExtra(EXTRA_LEV__REF_ID,levrefId);
        return intent;
    }
    @Override
    protected Fragment createFragment() {
        UUID levrefId = (UUID)getIntent()
                .getSerializableExtra(EXTRA_LEV__REF_ID);
        return FragItemBenchMark.newInstance(levrefId);
    }


}
