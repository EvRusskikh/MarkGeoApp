package com.myfirstapp.markgeohelper.datacontext.activityinterface;

import android.view.View;

import androidx.appcompat.app.ActionBar;
import androidx.core.content.res.ResourcesCompat;
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.myfirstapp.markgeohelper.R;

public abstract class SingleFragmentListActivity extends SingleFragmentActivity
        implements View.OnClickListener {
    @Override
    protected ActionBar createToolbar() {
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            VectorDrawableCompat indicator = VectorDrawableCompat.create(getResources(),
                    R.drawable.ic_menu_black_24dp, getTheme());
            indicator.setTint(ResourcesCompat.getColor(getResources(), R.color.white, getTheme()));
            supportActionBar.setHomeAsUpIndicator(indicator);
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        return supportActionBar;
    }

    @Override
    protected FloatingActionButton createFAB() {
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.single_fr_act_fab);
        fab.setOnClickListener(this);

        return fab;
    }
    protected abstract void goToActivity();

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.single_fr_act_fab:
                goToActivity();
                break;
        }

    }
}
