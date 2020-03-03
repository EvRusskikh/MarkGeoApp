package com.myfirstapp.markgeohelper.datacontext.activityinterface;

import androidx.appcompat.app.ActionBar;
import androidx.core.content.res.ResourcesCompat;
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat;


import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.myfirstapp.markgeohelper.R;

public abstract class SingleFragmentItemActivity extends SingleFragmentActivity {
    @Override
    protected FloatingActionButton createFAB() {
        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.single_fr_act_fab);
        fab.hide();
        return fab;
    }

    @Override
    protected ActionBar createToolbar() {
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar !=null){
            VectorDrawableCompat indicator = VectorDrawableCompat.create(getResources(),
                    R.drawable.ic_add_black_24dp, getTheme());
            indicator.setTint(ResourcesCompat.getColor(getResources(),R.color.white,getTheme()));
            supportActionBar.setHomeAsUpIndicator(indicator);
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        return supportActionBar;
    }

}
