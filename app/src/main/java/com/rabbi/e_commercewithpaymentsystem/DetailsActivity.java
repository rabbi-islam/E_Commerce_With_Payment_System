package com.rabbi.e_commercewithpaymentsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.rabbi.e_commercewithpaymentsystem.databinding.ActivityDetailsBinding;
import com.rabbi.e_commercewithpaymentsystem.models.NewProductsModel;
import com.rabbi.e_commercewithpaymentsystem.models.PopularProductModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class DetailsActivity extends AppCompatActivity {

    ActivityDetailsBinding binding;
    FirebaseFirestore db;
    NewProductsModel productsModel=null;
    PopularProductModel popularProductModel =null;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

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
        
        binding.addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddToCart();
            }
        });
    }

    private void AddToCart() {
        String currentTime,currentDate;

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yy");
        currentTime = dateFormat.format(calendar.getTime());

        Calendar calForTime = Calendar.getInstance();
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss a");
        currentDate = timeFormat.format(calForTime.getTime());

        final HashMap<String, Object> cartList = new HashMap<>();
        cartList.put("productName",binding.detailsName.getText().toString());
        cartList.put("productPrice",binding.detailsPrice.getText().toString());
        cartList.put("currentDate",currentDate);
        cartList.put("currentTime",currentTime);

        db.collection("AddToCart").document(auth.getCurrentUser().getUid()).collection("User")
                .add(cartList).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(DetailsActivity.this, "Product added to the cart", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                });
    }
}