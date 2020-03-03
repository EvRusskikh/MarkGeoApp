package com.myfirstapp.markgeohelper.datacontext.baselinecontext;

import android.content.Intent;

import androidx.fragment.app.Fragment;

import com.myfirstapp.markgeohelper.datacontext.activityinterface.SingleFragmentListActivity;
import com.myfirstapp.markgeohelper.repositories.BaseLineRepository;
import com.myfirstapp.markgeohelper.types.mydata.line.BaseLine;


public class ActivityListBL extends SingleFragmentListActivity {
    @Override
    protected Fragment createFragment() {
        return new FragmentListBL();
    }

    @Override
    protected void goToActivity() {
        BaseLineRepository repository = BaseLineRepository.getRepository(this);
        repository.addItem();
        BaseLine baseLine = repository.getLastItem();
        Intent intent = ActivityItemBL.newIntent(this, baseLine.getId());
        startActivity(intent);
    }
}
