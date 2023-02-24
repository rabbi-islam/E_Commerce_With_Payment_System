package com.rabbi.e_commercewithpaymentsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.google.firebase.firestore.FirebaseFirestore;
import com.rabbi.e_commercewithpaymentsystem.databinding.ActivityDetailsBinding;
import com.rabbi.e_commercewithpaymentsystem.models.NewProductsModel;
import com.rabbi.e_commercewithpaymentsystem.models.PopularProductModel;

public class DetailsActivity extends AppCompatActivity {

    ActivityDetailsBinding binding;
    FirebaseFirestore db;
    NewProductsModel productsModel=null;
    PopularProductModel popularProductModel =null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        db = FirebaseFirestore.getInstance();

        final Object obj = getIntent().getSerializableExtra("ProductsObject");
        if (obj instanceof NewProductsModel){
            productsModel = (NewProductsModel) obj;
        }else if (obj instanceof PopularProductModel){
            popularProductModel = (PopularProductModel) obj;
        }

        if (productsModel != null){
            Glide.with(getApplicationContext()).load(productsModel.getImgUrl()).into(binding.detailsImage);
            binding.detailsName.setText(productsModel.getName());
            binding.detailsDesc.setText(productsModel.getDescription());
            binding.rating.setText(productsModel.getRating());
            binding.detailsPrice.setText(String.valueOf(productsModel.getPrice()));
        }
        if (popularProductModel != null){
            Glide.with(getApplicationContext()).load(popularProductModel.getImage_url()).into(binding.detailsImage);
            binding.detailsName.setText(popularProductModel.getProduct_name());
            binding.detailsDesc.setText(popularProductModel.getDesc());
            binding.rating.setText(popularProductModel.getRating());
            binding.detailsPrice.setText(String.valueOf(popularProductModel.getProduct_price()));
        }
    }
}