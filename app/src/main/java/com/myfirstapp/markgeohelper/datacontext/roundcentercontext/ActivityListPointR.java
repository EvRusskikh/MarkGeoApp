package com.myfirstapp.markgeohelper.datacontext.roundcentercontext;

import android.content.Intent;

import androidx.fragment.app.Fragment;

import com.myfirstapp.markgeohelper.datacontext.activityinterface.SingleFragmentListActivity;
import com.myfirstapp.markgeohelper.repositories.RoundPointRepository;
import com.myfirstapp.markgeohelper.repositories.repint.Repository;
import com.myfirstapp.markgeohelper.types.mydata.circle.RoundPoint;

public class ActivityListPointR extends SingleFragmentListActivity {
    @Override
    protected void goToActivity() {
        Repository<RoundPoint> repository = RoundPointRepository.getRepository(this);
        repository.addItem();
        RoundPoint point = repository.getLastItem();
        Intent intent = ActivityItemPointR.newIntent(this,point.getId());
        startActivity(intent);
    }

    @Override
    protected Fragment createFragment() {
        return new FragListPointR();
    }
}
