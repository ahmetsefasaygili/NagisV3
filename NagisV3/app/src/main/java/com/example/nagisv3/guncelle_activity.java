package com.example.nagisv3;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class guncelle_activity extends AppCompatActivity {

    TextView txt_guncelle_id, txt_guncelle_title, txt_guncelle_body;

    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guncelle);


        txt_guncelle_id = findViewById(R.id.guncelle_txt_id);
        txt_guncelle_title = findViewById(R.id.guncelle_txt_title);
        txt_guncelle_body = findViewById(R.id.guncelle_txt_body);

        Intent intent = getIntent();
        String gelenid = intent.getStringExtra("id");
        String gelentitle = intent.getStringExtra("title");
        String gelenbody = intent.getStringExtra("body");




        txt_guncelle_id.setText(gelenid);
        txt_guncelle_title.setText(gelentitle);
        txt_guncelle_body.setText(gelenbody);



        Button sil_btn = findViewById(R.id.sil_btn);

        sil_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                databaseReference = FirebaseDatabase.getInstance().getReference("nagis").child(gelenid);
                String gelenid= txt_guncelle_id.getText().toString().trim();
                String gelentitle = toString().trim();
                String gelenbody = toString().trim();


                Canli insan =new Canli(gelenid, gelentitle="Silindi", gelenbody="Silindi");
                databaseReference.setValue(insan);
                Toast.makeText(getApplicationContext(),"Veri Silindi Ve Id Veri Tabanına Kaydedildi", Toast.LENGTH_LONG).show();
                Intent intent1 = new Intent(guncelle_activity.this, MainActivity.class);
                startActivity(intent1);

            }
        });



                                   }
           /* @Override
            public void onClick(View v) {
                databaseReference = FirebaseDatabase.getInstance().getReference("nagis").child(gelenid);
                databaseReference.removeValue();

                Intent intent1 = new Intent(guncelle_activity.this, MainActivity.class);
                startActivity(intent1);

            }
        });

*/



}