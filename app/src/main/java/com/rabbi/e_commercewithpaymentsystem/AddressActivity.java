package com.rabbi.e_commercewithpaymentsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.rabbi.e_commercewithpaymentsystem.adapters.AddressAdapter;
import com.rabbi.e_commercewithpaymentsystem.adapters.CategoryAdapter;
import com.rabbi.e_commercewithpaymentsystem.models.AddressModel;
import com.rabbi.e_commercewithpaymentsystem.models.CategoryModel;

import java.util.ArrayList;
import java.util.List;

public class AddressActivity extends AppCompatActivity implements AddressAdapter.SelectedAddress{

    Button add_address_btn,payment_btn;
    RecyclerView recyclerView;
    AddressAdapter addressAdapter;
    List<AddressModel> addressModelList;
    FirebaseFirestore db;
    FirebaseAuth auth;
    Toolbar toolbar;
    String mAddress = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);

        toolbar = findViewById(R.id.address_toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        recyclerView = findViewById(R.id.address_recycler);
        add_address_btn = findViewById(R.id.add_address_btn);
        payment_btn = findViewById(R.id.payment_btn);

        setAddressRecyclerview();

        add_address_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddressActivity.this,AddAddressActivity.class));
            }
        });

        payment_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddressActivity.this,PaymentActivity.class));
            }
        });
    }

    private void setAddressRecyclerview() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        addressModelList = new ArrayList<>();
        addressAdapter = new AddressAdapter(getApplicationContext(),addressModelList,this);
        recyclerView.setAdapter(addressAdapter);
        db.collection("CurrentUser").document(auth.getCurrentUser().getUid()).collection("Address")
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if (task.isSuccessful()){
                            for (DocumentSnapshot snapshot: task.getResult().getDocuments()){
                                AddressModel addressModel = snapshot.toObject(AddressModel.class);
                                addressModelList.add(addressModel);
                                addressAdapter.notifyDataSetChanged();
                            }
                        }else
                        {
                            Toast.makeText(AddressActivity.this, task.getException()+"", Toast.LENGTH_SHORT).show();
                        }

                    }
                });

    }

    @Override
    public void setAddress(String address) {
        mAddress = address;
    }
}