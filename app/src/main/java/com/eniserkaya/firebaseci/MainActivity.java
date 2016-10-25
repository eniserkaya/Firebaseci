package com.eniserkaya.firebaseci;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText yapilacak;
    private Button ekle,kullaniciKayit,gorBtn;
    private TextView gor;

    FirebaseDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        baslangic();

    }


    private void baslangic(){
        db=FirebaseDatabase.getInstance();
        yapilacak=(EditText)findViewById(R.id.yapilacakEt);
        ekle=(Button)findViewById(R.id.ekleBtn);
        kullaniciKayit=(Button)findViewById(R.id.button4);
        gorBtn=(Button)findViewById(R.id.yapilacakGorBtn);
        gor=(TextView)findViewById(R.id.gosterTv);

        ekle.setOnClickListener(this);
        kullaniciKayit.setOnClickListener(this);
        gorBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ekleBtn:
                String todo=yapilacak.getText().toString().trim();
                yapilacakEkle(todo);
                break;
            case R.id.yapilacakGorBtn:
                yapilacaklariGor();
                break;
            case R.id.button4:
                kullaniciAktivitesiniAc();
                break;
        }
    }

    private void yapilacakEkle(String yapilacak){

        DatabaseReference dbRef = db.getReference("yapilacaklar");
        String key = dbRef.push().getKey();
        DatabaseReference dbRefYeni = db.getReference("yapilacaklar/"+key);
        dbRefYeni.setValue(yapilacak);




    }
    private void yapilacaklariGor(){


        DatabaseReference okuma = db.getReference("yapilacaklar");
        okuma.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                gor.setText("");
                Iterable<DataSnapshot> keys = dataSnapshot.getChildren();
                for (DataSnapshot key: keys) {

                    gor.append(key.getValue().toString()+"\n");

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }
    private void kullaniciAktivitesiniAc(){
        Intent intent = new Intent(getApplicationContext(),KullaniciEkleActivity.class);
        startActivity(intent);
    }
}
