package com.example.assm.Model;

public class SinhVien {
    public int _id;
    public String tensv;
    public int id_lop;


    public SinhVien(String tensv, int id_lop){
        this.tensv=tensv;
        this.id_lop=id_lop;
    }

    public SinhVien(int _id, String tensv, int id_lop){
        this._id=_id;
        this.tensv=tensv;
        this.id_lop=id_lop;
    }
}
