package com.example.designpatern_assignment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    Product[] products ;
    Context context;

    public ProductAdapter(Product[] data){
        products = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View row = inflater.inflate(R.layout.product,parent,false);
        ViewHolder viewHolder = new ViewHolder(row);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title_text.setText(products[position].getTitle());
        holder.price_text.setText(products[position].getPrice());
        holder.brand_text.setText(products[position].getBrand());
        holder.desc_text.setText(products[position].getDesc());
        holder.rat.setRating((float) products[position].getRate());


        Glide.with(context).load(products[position].getUrl()).apply(new RequestOptions().override(150,150).placeholder(R.drawable.ic_launcher_background).error(R.drawable.ic_launcher_background)).into(holder.pro_img);


    }

    @Override
    public int getItemCount() {
        return products.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title_text;
        TextView price_text;
        TextView brand_text;
        TextView desc_text;
        ImageView pro_img;
        RatingBar rat;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title_text = itemView.findViewById(R.id.Title_value);
            price_text = itemView.findViewById(R.id.price_value);
            brand_text = itemView.findViewById(R.id.brand_value);
            desc_text= itemView.findViewById(R.id.descript_value);
            pro_img = itemView.findViewById(R.id.product_imag);
            rat = itemView.findViewById(R.id.ratingBar_value);


        }
    }
}
