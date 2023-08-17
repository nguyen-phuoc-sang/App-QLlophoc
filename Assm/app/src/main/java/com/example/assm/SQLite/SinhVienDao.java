package com.example.assm.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.assm.Model.LopHoc;
import com.example.assm.Model.SinhVien;

import java.util.ArrayList;

public class SinhVienDao {
    SQLiteDatabase db;
    Context c;
    public SinhVienDao(Context c){
        this.c=c;
        MyHelper helper = new MyHelper(c);
        db = helper.getWritableDatabase();
    }

    public void insertSinhVien(SinhVien sinhvien){
        ContentValues values = new ContentValues();
        values.put("tensv",sinhvien.tensv);
        values.put("id_lophoc",sinhvien.id_lop);
        db.insert("sinhvien",null,values);
    }

    public ArrayList<SinhVien> getAllSinhVien(){
        ArrayList<SinhVien> ds = new ArrayList<SinhVien>();
        String sql = "select * from sinhvien";
        Cursor c = db.rawQuery(sql,null);
        if(c.moveToFirst()==true){
            do {
                int masinhvien = c.getInt(0);
                String tensinhvien = c.getString(1);
                int ma_lophoc = c.getInt(2);
                SinhVien sv = new SinhVien(masinhvien, tensinhvien, ma_lophoc);
                ds.add(sv);
            }while (c.moveToNext()==true);

        }
        return ds;
    }

    public void deleteSinhVien(int _id){
        db.delete("sinhvien","_id=?",new String[]{_id+""});
    }

    public void editSinhVien(SinhVien sv){
        ContentValues values = new ContentValues();
        values.put("tensv",sv.tensv);
        values.put("id_lophoc",sv.id_lop);
        db.update("sinhvien",values,"_id=?",new String[]{sv._id+""});

    }
}
