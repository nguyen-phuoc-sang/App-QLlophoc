package com.example.assm.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.assm.Model.LopHoc;
import com.example.assm.Model.SinhVien;
import com.example.assm.Ql_lop_hoc;
import com.example.assm.Ql_sv;
import com.example.assm.R;

import java.util.ArrayList;

public class SinhVienAdapter extends BaseAdapter {

    Context c;
    ArrayList<SinhVien> ds = new ArrayList<SinhVien>();

    public SinhVienAdapter(Context c, ArrayList<SinhVien> ds) {
        this.c=c;
        this.ds=ds;
    }

    @Override
    public int getCount() {
        return ds.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inf = ((Activity)c).getLayoutInflater();
        convertView = inf.inflate(R.layout.sinhvien_mot_o,null);

        TextView tv_masinhvien = convertView.findViewById(R.id.tv_masinhvien);
        TextView tv_tensinhvien = convertView.findViewById(R.id.tv_tensinhvien);
        TextView tv_malopsv = convertView.findViewById(R.id.tv_malopsv);
        Button bt_delete_sv = convertView.findViewById(R.id.bt_delete_sv);
        Button bt_update_sv = convertView.findViewById(R.id.bt_update_sv);

        SinhVien sv = ds.get(position);
        tv_masinhvien.setText(sv._id+"");
        tv_tensinhvien.setText(sv.tensv+"");
        tv_malopsv.setText(sv.id_lop+"");

        bt_delete_sv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //position là index trên listView, từ position phải lấy tại malop để xóa
               int _id = ds.get(position)._id;
                ((Ql_sv)c).deleteSinhVien(_id);
            }
        });

        bt_update_sv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SinhVien sv = ds.get(position);
                ((Ql_sv)c).fillSinhVien(sv);
            }
        });

        return convertView;
    }
}
