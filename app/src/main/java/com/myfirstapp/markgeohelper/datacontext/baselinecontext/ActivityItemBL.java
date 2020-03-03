package com.myfirstapp.markgeohelper.datacontext.baselinecontext;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;


import com.myfirstapp.markgeohelper.datacontext.activityinterface.SingleFragmentItemActivity;

import java.util.UUID;

public class ActivityItemBL extends SingleFragmentItemActivity {
    public static final String EXTRA_BL_ID =
            "com.example.geodezhelper.bl_id";

    public static Intent newIntent(Context packageContext, UUID blId) {
        Intent intent = new Intent(packageContext, ActivityItemBL.class);
        intent.putExtra(EXTRA_BL_ID,blId);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        UUID blID= (UUID)getIntent()
                .getSerializableExtra(EXTRA_BL_ID);
        return FragItemBL.newInstance(blID);
    }
}
