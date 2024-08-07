package com.example.designpatern_assignment;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface productService {

    @GET("products")
    Call<List<Product>> getProducts();
}
