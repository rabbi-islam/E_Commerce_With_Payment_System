package com.rabbi.e_commercewithpaymentsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.rabbi.e_commercewithpaymentsystem.databinding.ActivityPaymentBinding;

public class PaymentActivity extends AppCompatActivity {

    ActivityPaymentBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityPaymentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.paymentToolbar);
        if (getSupportActionBar() != null){
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        double amount = 0.0;
        amount = getIntent().getDoubleExtra("amount",0.0);
        binding.subTotal.setText(amount+"$");
    }
}