package com.rabbi.e_commercewithpaymentsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.rabbi.e_commercewithpaymentsystem.adapters.CategoryAdapter;
import com.rabbi.e_commercewithpaymentsystem.adapters.ShowAllAdapter;
import com.rabbi.e_commercewithpaymentsystem.models.CategoryModel;
import com.rabbi.e_commercewithpaymentsystem.models.ShowAllModel;

import java.util.ArrayList;
import java.util.List;

public class ShowAllActivity extends AppCompatActivity {

    RecyclerView itemRecyclerview;
    ShowAllAdapter showAllAdapter;
    List<ShowAllModel> showAllModelList;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all);


        itemRecyclerview = findViewById(R.id.item_recyclerView);
        db = FirebaseFirestore.getInstance();
        String type = getIntent().getStringExtra("type");


        Toast.makeText(this, type, Toast.LENGTH_SHORT).show();

        if (type==null && type.isEmpty()){
            getData();
        }else if (type.equalsIgnoreCase("shoes")){
            forShoe();
        }else if (type.equalsIgnoreCase("camera")){
            forCamera();
        }

    }

    public void forShoe(){
        itemRecyclerview.setLayoutManager(new GridLayoutManager(ShowAllActivity.this,2));
        showAllModelList = new ArrayList<>();
        showAllAdapter = new ShowAllAdapter(getApplicationContext(),showAllModelList);
        itemRecyclerview.setAdapter(showAllAdapter);
        db.collection("AllProducts").whereEqualTo("type","shoes")
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if (task.isSuccessful()){
                            for (DocumentSnapshot snapshot: task.getResult().getDocuments()){
                                ShowAllModel model = snapshot.toObject(ShowAllModel.class);
                                showAllModelList.add(model);
                                showAllAdapter.notifyDataSetChanged();
                            }
                        }else
                        {
                            Toast.makeText(ShowAllActivity.this, task.getException()+"", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }
    public void forCamera(){
        itemRecyclerview.setLayoutManager(new GridLayoutManager(ShowAllActivity.this,2));
        showAllModelList = new ArrayList<>();
        showAllAdapter = new ShowAllAdapter(getApplicationContext(),showAllModelList);
        itemRecyclerview.setAdapter(showAllAdapter);
        db.collection("AllProducts").whereEqualTo("type","camera")
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if (task.isSuccessful()){
                            for (DocumentSnapshot snapshot: task.getResult().getDocuments()){
                                ShowAllModel model = snapshot.toObject(ShowAllModel.class);
                                showAllModelList.add(model);
                                showAllAdapter.notifyDataSetChanged();
                            }
                        }else
                        {
                            Toast.makeText(ShowAllActivity.this, task.getException()+"", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }

    private void getData() {
        itemRecyclerview.setLayoutManager(new GridLayoutManager(ShowAllActivity.this,2));
        showAllModelList = new ArrayList<>();
        showAllAdapter = new ShowAllAdapter(getApplicationContext(),showAllModelList);
        itemRecyclerview.setAdapter(showAllAdapter);
        db.collection("AllProducts")
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if (task.isSuccessful()){
                            for (QueryDocumentSnapshot snapshot: task.getResult()){
                                ShowAllModel categoryModel = snapshot.toObject(ShowAllModel.class);
                                showAllModelList.add(categoryModel);
                                showAllAdapter.notifyDataSetChanged();
                            }
                        }else
                        {
                            Toast.makeText(ShowAllActivity.this, task.getException()+"", Toast.LENGTH_SHORT).show();
                        }

                    }
                });

    }
}