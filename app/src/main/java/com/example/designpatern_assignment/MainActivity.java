package com.example.designpatern_assignment;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private static final String url = "https://dummyjson.com/";
    Product[] products = new Product[0] ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.recycler);
        ProductAdapter adapter = new ProductAdapter(products);
        LinearLayoutManager layout = new LinearLayoutManager(this);
        layout.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layout);
        recyclerView.setAdapter(adapter);

        Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(url).build();
        productService productService = retrofit.create(productService.class);
        Call<List<Product>> call = productService.getProducts();
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if(response.isSuccessful()){
                    List<Product> productList = response.body();
                    if (productList != null && !productList.isEmpty()) {
                        products = new Product[productList.size()];
                        products = productList.toArray(products);
                        adapter.notifyDataSetChanged();



                    }
                }

            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                   Log.i("onfailur",""+t.getMessage());
            }
        });



    }
}