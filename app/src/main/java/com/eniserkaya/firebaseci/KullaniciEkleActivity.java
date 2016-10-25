package com.eniserkaya.firebaseci;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class KullaniciEkleActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText ad,soyad,yas;
    private Button kaydet,gor;
    private TextView kayitlar;



    FirebaseDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kullanici_ekle);
        baslangic();
    }
    private void baslangic(){

        db=FirebaseDatabase.getInstance();
        ad=(EditText)findViewById(R.id.adEt);
        soyad=(EditText)findViewById(R.id.soyadEt);
        yas=(EditText)findViewById(R.id.yasEt);
        kaydet=(Button)findViewById(R.id.kaydetBtn);
        gor=(Button)findViewById(R.id.button2);
        kayitlar=(TextView)findViewById(R.id.kullaniciTv);

        kaydet.setOnClickListener(this);
        gor.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.kaydetBtn:
                String isim,soyisim;
                int yasi;
                isim=ad.getText().toString().trim();
                soyisim=soyad.getText().toString().trim();
                yasi=Integer.valueOf(yas.getText().toString().trim());
                kullaniciKaydet(isim,soyisim,yasi);
                break;
            case R.id.button2:
                kayitlariGetir();
                break;
        }
    }

    private void kullaniciKaydet(String ad, String soyad, int yas){

        DatabaseReference dbRef = db.getReference("Kullanicilar");
        String key = dbRef.push().getKey();
        DatabaseReference dbRefKeyli = db.getReference("Kullanicilar/"+key);

        dbRefKeyli.setValue(new Kullanici(ad,soyad,yas));

    }

    private void kayitlariGetir(){
        kayitlar.setText("");


        DatabaseReference dbGelenler = db.getReference("Kullanicilar");
        dbGelenler.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot gelenler: dataSnapshot.getChildren()) {

                    kayitlar.append(gelenler.getValue(Kullanici.class).getAd()+"\n");

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




    }
}
