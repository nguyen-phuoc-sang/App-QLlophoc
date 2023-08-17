package com.example.assm.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.example.assm.Model.LopHoc;
import com.example.assm.SQLite.MyHelper;

import java.util.ArrayList;

public class LopHocDao {
    SQLiteDatabase db;
    Context c;
    public LopHocDao(Context c){
        this.c=c;
        MyHelper helper = new MyHelper(c);
        db = helper.getWritableDatabase();
    }

    public void insertLopHoc(LopHoc lophoc){
        ContentValues values = new ContentValues();
        values.put("tenlop",lophoc.tenlop);
        db.insert("lophoc",null,values);
    }

    public ArrayList<LopHoc> getAllLopHoc(){
        ArrayList<LopHoc> ds = new ArrayList<LopHoc>();
        String sql = "select * from lophoc";
        Cursor c = db.rawQuery(sql,null);
        if(c.moveToFirst()==true){
            do {
                int malop = c.getInt(0);
                String tenlop = c.getString(1);
                LopHoc lophoc = new LopHoc(malop,tenlop);
                ds.add(lophoc);
            }while (c.moveToNext()==true);

        }
        return ds;
    }

    public void deleteLopHoc(int _id){

        db.delete("lophoc","_id=?",new String[]{_id+""});
    }

    public void editLopHoc(LopHoc lh){
        ContentValues values = new ContentValues();
        values.put("tenlop",lh.tenlop);
        db.update("lophoc",values,"_id=?",new String[]{lh._id+""});
    }



}
