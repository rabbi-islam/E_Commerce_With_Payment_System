package com.rabbi.e_commercewithpaymentsystem.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.rabbi.e_commercewithpaymentsystem.DetailsActivity;
import com.rabbi.e_commercewithpaymentsystem.R;
import com.rabbi.e_commercewithpaymentsystem.models.PopularProductModel;

import java.util.List;

public class PopularProductAdapter extends RecyclerView.Adapter<PopularProductAdapter.PopularProductViewHolder> {

    private Context context;
    private List<PopularProductModel> popularProductList;

    public PopularProductAdapter(Context context, List<PopularProductModel> popularProductList) {
        this.context = context;
        this.popularProductList = popularProductList;
    }

    @NonNull
    @Override
    public PopularProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.popular_product_item,parent,false);
        return new PopularProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PopularProductViewHolder holder, int position) {
        try {
            Glide.with(context).load(popularProductList.get(position).getImage_url()).into(holder.popularProductImage);
        } catch (Exception e) {
            e.printStackTrace();
        }

        holder.popularProductsName.setText(popularProductList.get(position).getProduct_name());
        holder.popularProductPrice.setText("$"+popularProductList.get(position).getProduct_price());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, DetailsActivity.class);
                intent.putExtra("ProductsObject",popularProductList.get(holder.getAdapterPosition()));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (popularProductList != null){
            return popularProductList.size();
        }
        return 0;
    }

    public class PopularProductViewHolder extends RecyclerView.ViewHolder {

         ImageView popularProductImage;
         TextView popularProductsName,popularProductPrice;


        public PopularProductViewHolder(@NonNull View itemView) {
            super(itemView);
            popularProductImage = itemView.findViewById(R.id.prodImg1);
            popularProductsName = itemView.findViewById(R.id.prodName1);
            popularProductPrice = itemView.findViewById(R.id.prodPrice1);
        }
    }
}
