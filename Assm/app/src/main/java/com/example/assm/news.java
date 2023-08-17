package com.example.assm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class news extends AppCompatActivity {
    String [] ten_loai={"Bóng đá","Thời trang","Ẩm thực","du lich"};

    String [] rss_loai={"https://cdn.24h.com.vn/upload/rss/bongda.rss",
            "https://cdn.24h.com.vn/upload/rss/thoitrang.rss",
            "https://cdn.24h.com.vn/upload/rss/amthuc.rss",
            "https://cdn.24h.com.vn/upload/rss/dulich24h.rss"
    };

    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news);

        lv = findViewById(R.id.lv);

        ArrayAdapter<String> adapter=new
                ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,ten_loai);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                // TODO Auto-generated method stub
                //arg2 chính là vị trí (index) được nhấn

                Intent intent=new Intent(getApplicationContext(),TinTheoLoai.class);
                intent.putExtra("diachi_rss", rss_loai[arg2]);
                startActivity(intent);

            }
        });

    }
}