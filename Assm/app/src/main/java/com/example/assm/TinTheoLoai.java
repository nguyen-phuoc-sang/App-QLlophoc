package com.example.assm;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.assm.Adapter.TinTheoLoaiAdapter;
import com.example.assm.Model.Item;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class TinTheoLoai extends AppCompatActivity {
    String diachi_rss;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tin_theo_loai);

        diachi_rss=getIntent().getExtras().getString("diachi_rss");
        lv = findViewById(R.id.lv);

        Toast.makeText(getApplicationContext(), diachi_rss, Toast.LENGTH_SHORT).show();

        MyAsyncTask gandulieu=new MyAsyncTask(TinTheoLoai.this,diachi_rss);
        gandulieu.execute();

    }

    public class MyAsyncTask extends AsyncTask<Void,Void,Void>
    {
        ArrayList<Item> items=new ArrayList<Item>();
        String chuoi="";
        Context c;
        String diachi_rss;

        MyAsyncTask(Context c, String diachi_rss)
        {
            this.diachi_rss=diachi_rss;
            this.c=c;
        }
        @Override
        protected Void doInBackground(Void... arg0) {
            // TODO Auto-generated method stub
            try {
                URL url=new URL(diachi_rss);
                URLConnection connection=url.openConnection();

                InputStream is=connection.getInputStream();
                items=(ArrayList<Item>) MySaxParser.xmlParser(is);


            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                Log.d("dulieu",e.toString());
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);
            Log.d("dulieu", chuoi);
            try{
                TinTheoLoaiAdapter adapter=new TinTheoLoaiAdapter(c,R.layout.layout_tintheoloai_itemlistview,items);
                ((TinTheoLoai)c).lv.setAdapter(adapter);

                ((TinTheoLoai)c).lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String link=items.get(position).getLink();
                        //Toast.makeText(getApplicationContext(),link, Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(c,XemTin.class);
                        intent.putExtra("link", link);
                        c.startActivity(intent);

                    }
                });

            }catch(Exception e)
            {
                Log.d("title","adapter khong duoc");
            }

        }
    }


    public static class MySaxHandler extends DefaultHandler {

        ArrayList<Item> items;
        Item item_tam;
        String chuoi_tam;
        boolean vao_item=false;

        public MySaxHandler()
        {
            items=new ArrayList<Item>();
        }
        public ArrayList<Item> getItems()
        {
            return items;
        }


        @Override
        public void startDocument() throws SAXException {
            super.startDocument();
        }

        @Override
        public void endDocument() throws SAXException {
            super.endDocument();
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            super.startElement(uri, localName, qName, attributes);

            if(qName.equalsIgnoreCase("item"))
            {
                item_tam=new Item();
                vao_item=true;
            }

        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            super.endElement(uri, localName, qName);

            if(qName.equalsIgnoreCase("item"))
            {
                items.add(item_tam);
            }else if(vao_item==true)
            {
                if(qName.equalsIgnoreCase("title"))
                    item_tam.setTitle(chuoi_tam);
                if(qName.equalsIgnoreCase("description"))
                    item_tam.setDescription(chuoi_tam);
                if(qName.equalsIgnoreCase("link"))
                    item_tam.setLink(chuoi_tam);
                if(qName.equalsIgnoreCase("pubdate"))
                    item_tam.setPubdate(chuoi_tam);
            }

        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            super.characters(ch, start, length);

            if(vao_item==true)
                chuoi_tam=new String(ch,start,length);

        }
    }


}