package com.example.nagisv3;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
public interface MyApi {
    @GET("posts")
    Call<ArrayList<Model>>callModel();
}
