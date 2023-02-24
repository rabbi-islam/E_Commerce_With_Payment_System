package com.rabbi.e_commercewithpaymentsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
    int totalQuantity = 1;
    int totalPrice =0;

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

            totalPrice = productsModel.getPrice() * totalQuantity;
        }
        if (popularProductModel != null){
            Glide.with(getApplicationContext()).load(popularProductModel.getImage_url()).into(binding.detailsImage);
            binding.detailsName.setText(popularProductModel.getProduct_name());
            binding.detailsDesc.setText(popularProductModel.getDesc());
            binding.rating.setText(popularProductModel.getRating());
            binding.detailsPrice.setText(String.valueOf(popularProductModel.getProduct_price()));
            totalPrice = popularProductModel.getProduct_price() * totalQuantity;
        }
        
        binding.addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddToCart();
            }
        });

        binding.plusIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (totalQuantity < 10 ){
                    totalQuantity++;
                    binding.quantity.setText(String.valueOf(totalQuantity));

                    if (productsModel != null){
                        totalPrice = productsModel.getPrice() * totalQuantity;
                    }
                    if (popularProductModel != null){
                        totalPrice = popularProductModel.getProduct_price() * totalQuantity;
                    }
                }
            }
        });

        binding.minusIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (totalQuantity > 1 ){
                    totalQuantity--;
                    binding.quantity.setText(String.valueOf(totalQuantity));
                    if (productsModel != null){
                        totalPrice = productsModel.getPrice() * totalQuantity;
                    }
                    if (popularProductModel != null){
                        totalPrice = popularProductModel.getProduct_price() * totalQuantity;
                    }
                }
            }
        });

        binding.quantity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                totalQuantity = Integer.parseInt(binding.quantity.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

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
        cartList.put("quantity",totalQuantity);
        cartList.put("totalPrice",totalPrice);

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