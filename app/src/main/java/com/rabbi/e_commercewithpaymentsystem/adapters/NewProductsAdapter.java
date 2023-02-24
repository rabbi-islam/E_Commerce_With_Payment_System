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
import com.rabbi.e_commercewithpaymentsystem.models.NewProductsModel;

import java.util.List;

public class NewProductsAdapter extends RecyclerView.Adapter<NewProductsAdapter.ProductsViewAdapter> {

     Context context;
     List<NewProductsModel> productsList;

    public NewProductsAdapter(Context context, List<NewProductsModel> productsList) {
        this.context = context;
        this.productsList = productsList;
    }

    @NonNull
    @Override
    public ProductsViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.new_product_layout,parent,false);
        return new ProductsViewAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductsViewAdapter holder, int position) {
        try {
            Glide.with(context).load(productsList.get(position).getImgUrl()).into(holder.productImage);
        } catch (Exception e) {
            e.printStackTrace();
        }

        holder.productsName.setText(productsList.get(position).getName());
        holder.productPrice.setText("$"+ productsList.get(position).getPrice());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, DetailsActivity.class);
                intent.putExtra("ProductsObject",productsList.get(holder.getAdapterPosition()));
                context.startActivity(intent);
            }
        });



    }

    @Override
    public int getItemCount() {
        if (productsList != null){
            return productsList.size();
        }
         return 0;
    }

    public class ProductsViewAdapter extends RecyclerView.ViewHolder {

        private ImageView productImage;
        private TextView productsName,productPrice;

        public ProductsViewAdapter(@NonNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.prod_img);
            productsName = itemView.findViewById(R.id.prodName);
            productPrice = itemView.findViewById(R.id.prod_price);
        }
    }
}
