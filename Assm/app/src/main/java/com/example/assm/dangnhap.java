package com.example.assm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class dangnhap extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dangnhap);
        EditText etuser = findViewById(R.id.user);
        EditText et2pass = findViewById(R.id.pass);
        Button bt = findViewById(R.id.nut);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = etuser.getText().toString();
                String pass = et2pass.getText().toString();
                if (user.equals("fpt123") && pass.equals("123456")){
                    Intent intent = new Intent(dangnhap.this, chon.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(dangnhap.this, "sai", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
