package com.example.assm.SQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public  class MyHelper extends SQLiteOpenHelper {

    static String  dbname = "quanlysinhvien";
    static int version = 1;
    static Context c;
    public MyHelper(@Nullable Context context) {
        super(context, dbname, null , version);
        this.c=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        /*
         * create table lophoc
         * (
         * _id integer primary key autoincrement,
         *  tenlop  text
         * )
         * create table sinhvien
         * (
         * _id integer primary key autoincrement,
         *  tensv  text,
         * id_lophoc int
         * )
         * */
        String sql1 = "create table lophoc" +
                "("+
                "_id integer primary key autoincrement,"+
                "tenlop  text"+
                ")";
        db.execSQL(sql1);

        String sql2 = "create table sinhvien" +
                "(" +
                "_id integer primary key autoincrement," +
                "tensv  text," +
                "id_lophoc int "+
                ")";
        db.execSQL(sql2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists lophoc");
        db.execSQL("drop table if exists sinhvien");
        this.onCreate(db);
    }

}
