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
import com.example.assm.Ql_lop_hoc;
import com.example.assm.R;

import java.util.ArrayList;

public class LopHocAdapter extends BaseAdapter {

    Context c;
    ArrayList<LopHoc> ds = new ArrayList<LopHoc>();

    public LopHocAdapter(Context c, ArrayList<LopHoc> ds) {
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
        convertView = inf.inflate(R.layout.lophoc_mot_o,null);

        TextView tv_malop = convertView.findViewById(R.id.malop);
        TextView tv_tenlop = convertView.findViewById(R.id.tenlop);
        Button bt_delete = convertView.findViewById(R.id.bt_delete);
        Button bt_update = convertView.findViewById(R.id.bt_update);

        LopHoc lh = ds.get(position);
        tv_malop.setText(lh._id+"");
        tv_tenlop.setText(lh.tenlop+"");

        bt_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //position là index trên listView, từ position phải lấy tại malop để xóa
               int _id = ds.get(position)._id;
                ((Ql_lop_hoc)c).deleteLopHoc(_id);
            }
        });

        bt_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LopHoc lh = ds.get(position);
                ((Ql_lop_hoc)c).fillLopHoc(lh);
            }
        });

        return convertView;
    }
}
