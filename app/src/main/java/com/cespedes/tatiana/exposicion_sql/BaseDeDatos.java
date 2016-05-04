package com.cespedes.tatiana.exposicion_sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Tatiana on 4/05/2016.
 */
public class BaseDeDatos extends SQLiteOpenHelper {


    public BaseDeDatos(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table estudiantes(identificacion integer primary key ,nombre text, nota integer)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists estudiantes");//elimina la anterior con drop
        db.execSQL("create table estudiantes(identificacion integer primary key ,nombre text, nota integer)");

    }
}
