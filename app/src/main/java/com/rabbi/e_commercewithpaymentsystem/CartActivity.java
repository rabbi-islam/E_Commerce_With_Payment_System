package com.rabbi.e_commercewithpaymentsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.rabbi.e_commercewithpaymentsystem.adapters.CartAdapter;
import com.rabbi.e_commercewithpaymentsystem.adapters.CategoryAdapter;
import com.rabbi.e_commercewithpaymentsystem.models.CartModel;
import com.rabbi.e_commercewithpaymentsystem.models.CategoryModel;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {

    RecyclerView cartRecyclerView;
    CartAdapter cartAdapter;
    List<CartModel> cartModels;
    FirebaseFirestore db;
    FirebaseAuth auth;
    int overAllAmount = 0;

    TextView totalPriceTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        totalPriceTv = findViewById(R.id.totalPriceTv);
        cartRecyclerView = findViewById(R.id.card_rec);
        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        LocalBroadcastManager.getInstance(this).registerReceiver(broadcastReceiver,new IntentFilter("MyTotalAmount"));
        setCartRecyclerview();
    }

    private void setCartRecyclerview() {
        cartRecyclerView.setLayoutManager(new LinearLayoutManager(CartActivity.this));
        cartModels = new ArrayList<>();
        cartAdapter = new CartAdapter(this,cartModels);
        cartRecyclerView.setAdapter(cartAdapter);
        db.collection("AddToCart").document(auth.getCurrentUser().getUid()).collection("User")
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if (task.isSuccessful()){
                            for (DocumentSnapshot snapshot: task.getResult().getDocuments()){
                                CartModel cartModel = snapshot.toObject(CartModel.class);
                                cartModels.add(cartModel);
                                cartAdapter.notifyDataSetChanged();
                            }
                        }else
                        {
                            Toast.makeText(CartActivity.this, task.getException()+"", Toast.LENGTH_SHORT).show();
                        }

                    }
                });

    }

    public BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            int finalBill = intent.getIntExtra("totalAmount",0);
            totalPriceTv.setText("Total Price: "+finalBill+ " $");

        }
    };
}