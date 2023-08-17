package com.example.assm;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.assm.Adapter.SinhVienAdapter;
import com.example.assm.Model.LopHoc;
import com.example.assm.Model.SinhVien;
import com.example.assm.SQLite.LopHocDao;
import com.example.assm.SQLite.SinhVienDao;

import java.util.ArrayList;

public class Ql_sv extends AppCompatActivity {

    ListView lv1;
    TextView tv_id;
    Button bt_insert_sv, bt_update_sv;
    EditText et_tensv;
    Spinner sp_lop;
    ArrayList<LopHoc> ds_lophoc = new ArrayList<LopHoc>();
    ArrayList<SinhVien> ds_sinhvien = new ArrayList<SinhVien>();
    LopHocDao lophocdao;
    SinhVienDao sinhviendao;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ql_sv);
        lv1 = findViewById(R.id.lv_sv);
        tv_id = findViewById(R.id.tv_id);
        bt_insert_sv = findViewById(R.id.bt_insert_sv);
        bt_update_sv = findViewById(R.id.bt_update_sv);
        et_tensv = findViewById(R.id.et_tensv);
        sp_lop = findViewById(R.id.sp_lop);

        //lấy ds lớp học
        lophocdao = new LopHocDao(Ql_sv.this);
        ds_lophoc = lophocdao.getAllLopHoc();

        // dỗ dữ liệu lên spinner
        ArrayList<String> ds_tenlop = new ArrayList<String>();
        for (int i=0; i<ds_lophoc.size(); i++){
            ds_tenlop.add(ds_lophoc.get(i).tenlop);
        }

        ArrayAdapter adapter = new ArrayAdapter(Ql_sv.this, android.R.layout.simple_spinner_item,ds_tenlop);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_lop.setAdapter(adapter);

        //lấy ds sinh vien
        dodulieusinhvien();

        bt_insert_sv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tensv=et_tensv.getText().toString();
                int index = sp_lop.getSelectedItemPosition();
                int id_lop = ds_lophoc.get(index)._id;
                SinhVien sv = new SinhVien(tensv,id_lop);
                sinhviendao.insertSinhVien(sv);
                dodulieusinhvien();
            }
        });

        bt_update_sv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int _id = Integer.parseInt(tv_id.getText().toString());
                String tensv = et_tensv.getText().toString();
                int index = sp_lop.getSelectedItemPosition();
                int id_lop = ds_lophoc.get(index)._id;
                SinhVien sv = new SinhVien(_id,tensv,id_lop);
                sinhviendao.editSinhVien(sv);
                dodulieusinhvien();
            }
        });

    }

    public void dodulieusinhvien(){
        sinhviendao = new SinhVienDao(Ql_sv.this);
        ds_sinhvien = sinhviendao.getAllSinhVien();

        SinhVienAdapter sinhvienadapter = new SinhVienAdapter(Ql_sv.this,ds_sinhvien);
        lv1.setAdapter(sinhvienadapter);
    }

    public void deleteSinhVien(int _id){
        sinhviendao.deleteSinhVien(_id);
        dodulieusinhvien();
    }

    public void fillSinhVien(SinhVien sv){
        tv_id.setText(sv._id+"");
        et_tensv.setText(sv.tensv+"");
    }











}
















