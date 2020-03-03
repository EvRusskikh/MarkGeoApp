package com.myfirstapp.markgeohelper.datacontext.roundcentercontext;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;


import com.myfirstapp.markgeohelper.datacontext.activityinterface.SingleFragmentItemActivity;

import java.util.UUID;

public class ActivityItemPointR extends SingleFragmentItemActivity {
    public static String EXTRA_RP_ID = "com.example.geodezhelper.round.roundP_id";
    public static Intent newIntent(Context packageContext, UUID rpId){
        Intent intent = new Intent(packageContext, ActivityItemPointR.class);
        intent.putExtra(EXTRA_RP_ID,rpId);
        return intent;
    }
    @Override
    protected Fragment createFragment() {
        UUID rpId = (UUID)getIntent()
                .getSerializableExtra(EXTRA_RP_ID);
        return FragItemPointR.newInstance(rpId);
    }
}
