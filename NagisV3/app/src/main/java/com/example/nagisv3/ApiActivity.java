package com.example.nagisv3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiActivity extends AppCompatActivity {



 ArrayList<Model>modelArrayList;
 MyApi myApi;
ListView lv;
TextView titletxt;
TextView idtxt;
TextView bodytxt;
Button ekle_btn;
 String BaseUrl="https://jsonplaceholder.typicode.com/";
DatabaseReference databaseReference;


    private void init(){


        lv = findViewById(R.id.lv);
        ekle_btn = findViewById(R.id.ekle_btn);

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api);
        init();


        databaseReference = FirebaseDatabase.getInstance().getReference("nagis");



        ekle_btn = findViewById(R.id.ekle_btn);
        lv = findViewById(R.id.lv);
        modelArrayList = new ArrayList<>();
        displayRetrofitData();


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Model insan = modelArrayList.get(position);
                Intent intent = new Intent(getApplicationContext(), ApiActivity.class);

                intent.putExtra("id", insan.getId());
                intent.putExtra("title", insan.getTitle());
                intent.putExtra("body",insan.getBody());



                idtxt = findViewById(R.id.idtxt);
                titletxt = findViewById(R.id.titletxt);
                bodytxt = findViewById(R.id.bodytxt);
                ekle_btn = findViewById(R.id.ekle_btn);

                startActivity(intent);
            }
        });



        ekle_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                kullaniciEkle();

            }



        });




    }


    private void displayRetrofitData() {

        Retrofit retrofit = new Retrofit.Builder().baseUrl(BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        myApi = retrofit.create(MyApi.class);
        Call<ArrayList<Model>>arrayListCall = myApi.callModel();
        arrayListCall.enqueue(new Callback<ArrayList<Model>>() {
            @Override
            public void onResponse(Call<ArrayList<Model>> call, Response<ArrayList<Model>> response) {
                modelArrayList = response.body();
                for (int i = 0; i<modelArrayList.size(); i++);
                Custom custom = new Custom(modelArrayList,ApiActivity.this,R.layout.singleview);
                lv.setAdapter(custom);

            }

            @Override
            public void onFailure(Call<ArrayList<Model>> call, Throwable t) {

                Toast.makeText(ApiActivity.this, "Olmadı", Toast.LENGTH_LONG).show();
            }
        });

    }


    private void kullaniciEkle() {

        for (int i = 0; i<modelArrayList.size(); i++)
        {
            Model insan = modelArrayList.get(i);
            databaseReference.child(insan.getId()).setValue(insan);
        }

    }
}