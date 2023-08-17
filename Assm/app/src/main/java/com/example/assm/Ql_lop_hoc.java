package com.example.assm;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.assm.Adapter.LopHocAdapter;
import com.example.assm.Model.LopHoc;
import com.example.assm.SQLite.LopHocDao;

import java.util.ArrayList;

public class Ql_lop_hoc extends AppCompatActivity {

    ListView lv;
    Button bt_insert,bt_update;
    EditText et_tenlop;
    TextView tv_malop;
    LopHocDao lophocdao;
    ArrayList<LopHoc> ds = new ArrayList<LopHoc>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ql_lop_hoc);
        lv = findViewById(R.id.lv);
        bt_insert = findViewById(R.id.bt_insert);
        bt_update = findViewById(R.id.bt_update);
        et_tenlop = findViewById(R.id.ed_tenlop);
        tv_malop = findViewById(R.id.tv_malop);

        lophocdao = new LopHocDao(Ql_lop_hoc.this);
        xuatdulieu();
        bt_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String tenlop = et_tenlop.getText().toString();
                LopHoc lh = new LopHoc(tenlop);
                lophocdao.insertLopHoc(lh);
                xuatdulieu();
            }
        });

        bt_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int _id = Integer.parseInt(tv_malop.getText().toString());
                String tenlop = et_tenlop.getText().toString();
                //_id là cũ tên lớp là mới
                LopHoc lh = new LopHoc(_id,tenlop);
                lophocdao.editLopHoc(lh);
                xuatdulieu();
            }
        });

    }

    public void xuatdulieu(){
        ds = lophocdao.getAllLopHoc();
        LopHocAdapter adapter = new LopHocAdapter(Ql_lop_hoc.this,ds);
        lv.setAdapter(adapter);
    }

    public void deleteLopHoc(int _id){
        lophocdao.deleteLopHoc(_id);
        xuatdulieu();
    }

    public void fillLopHoc(LopHoc lh){
        tv_malop.setText(lh._id+"");
        et_tenlop.setText(lh.tenlop);
    }




}













