package com.myfirstapp.markgeohelper.repositories.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SqlMarkHelper extends SQLiteOpenHelper {
    private String[] tableNames = {"benchmarks","baselines","roundpoints"};
    private static final String DBNAME = "geomark.db";
    private static final int VERSION = 1;
    public SqlMarkHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
