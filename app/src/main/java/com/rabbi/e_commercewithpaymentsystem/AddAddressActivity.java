package com.rabbi.e_commercewithpaymentsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.rabbi.e_commercewithpaymentsystem.databinding.ActivityAddAddressBinding;

import java.util.HashMap;

public class AddAddressActivity extends AppCompatActivity {

    ActivityAddAddressBinding binding;
    FirebaseFirestore db;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddAddressBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.addAddressToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        binding.addAddressBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName  = binding.adName.getText().toString();
                String userPost  = binding.adCode.getText().toString();
                String userNumber  = binding.adPhone.getText().toString();
                String userCity  = binding.adCity.getText().toString();
                String userAddress  = binding.adAddress.getText().toString();

                String final_address = "";

                if (!userName.isEmpty()){
                    final_address+= userName+", ";
                }
                if (!userPost.isEmpty()){
                    final_address+=""+userPost+", ";
                }if (!userNumber.isEmpty()){
                    final_address+=""+userNumber+", ";
                }if (!userCity.isEmpty()){
                    final_address+=""+userCity+", ";
                }if (!userAddress.isEmpty()){
                    final_address+=""+userAddress+", ";
                }

                if (!userName.isEmpty() && !userPost.isEmpty() && !userNumber.isEmpty() && !userCity.isEmpty() && !userAddress.isEmpty()) {

                    HashMap<String, String> map = new HashMap<>();
                    map.put("userAddress",final_address);
                    db.collection("CurrentUser").document(auth.getCurrentUser().getUid()).collection("Address")
                            .add(map).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                                @Override
                                public void onComplete(@NonNull Task<DocumentReference> task) {
                                    if (task.isSuccessful()){
                                        Toast.makeText(AddAddressActivity.this, "Address added successfully!", Toast.LENGTH_SHORT).show();
                                    }else
                                    {
                                        Toast.makeText(AddAddressActivity.this, "error occurred", Toast.LENGTH_SHORT).show();
                                    }

                                }
                            });
                }else {
                    Toast.makeText(AddAddressActivity.this, "field must not be empty", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}