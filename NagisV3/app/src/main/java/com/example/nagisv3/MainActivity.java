package com.example.nagisv3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

public class MainActivity extends AppCompatActivity {


    public Button listele;
    ListView listView_Canli;
    List<Canli> canliList;
    DatabaseReference databaseReference;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        databaseReference = FirebaseDatabase.getInstance().getReference("nagis");

        listele = findViewById(R.id.listele);
        listView_Canli = findViewById(R.id.listView_Canli);


        canliList = new ArrayList<>();


       listView_Canli.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               Canli nagis = canliList.get(position);

               Intent intent = new Intent(getApplicationContext(), guncelle_activity.class);
               intent.putExtra("id", nagis.getid());
               intent.putExtra("title", nagis.gettitle());
               intent.putExtra("body", nagis.getbody());
               startActivity(intent);
           }
       });



        listele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent listele = new Intent(MainActivity.this,ApiActivity.class);
                startActivity(listele);

            }
        });






    }

    @Override
    protected void onStart() {

        super.onStart();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                canliList.clear();

                for (DataSnapshot canliSnapshot : snapshot.getChildren()) {
                    Canli nagis = canliSnapshot.getValue(Canli.class);
                    canliList.add(nagis);
                }
                CanliList adapter = new CanliList(MainActivity.this, canliList);
                listView_Canli.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }




}