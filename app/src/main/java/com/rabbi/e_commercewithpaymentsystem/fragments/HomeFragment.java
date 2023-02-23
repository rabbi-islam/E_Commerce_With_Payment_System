package com.rabbi.e_commercewithpaymentsystem.fragments;

import static android.content.ContentValues.TAG;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.rabbi.e_commercewithpaymentsystem.R;
import com.rabbi.e_commercewithpaymentsystem.adapters.NewProductsAdapter;
import com.rabbi.e_commercewithpaymentsystem.adapters.CategoryAdapter;
import com.rabbi.e_commercewithpaymentsystem.adapters.PopularProductAdapter;
import com.rabbi.e_commercewithpaymentsystem.models.CategoryModel;
import com.rabbi.e_commercewithpaymentsystem.models.NewProductsModel;
import com.rabbi.e_commercewithpaymentsystem.models.PopularProductModel;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {

    RecyclerView categoryRecyclerView;
    RecyclerView productRecyclerView;
    RecyclerView popularRecyclerView;
    CategoryAdapter adapter;
    NewProductsAdapter productsAdapter;
    PopularProductAdapter popularProductAdapter;
    List<CategoryModel> categoryModelList;
    List<NewProductsModel> newProductsList;
    List<PopularProductModel> popularProductList;
    FirebaseFirestore db;


    public HomeFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        categoryRecyclerView = view.findViewById(R.id.rec_category);
        productRecyclerView = view.findViewById(R.id.new_product_rec);
        popularRecyclerView = view.findViewById(R.id.popular_rec);
        db = FirebaseFirestore.getInstance();
        ImageSlider imageSlider = view.findViewById(R.id.image_slider);
        List<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel(R.drawable.banner1,"Discount On Shoes Item", ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel(R.drawable.banner2,"Discount On Perfume", ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel(R.drawable.banner3,"70% OFF", ScaleTypes.CENTER_CROP));

        imageSlider.setImageList(slideModels);

        setCategoryRecyclerview();
        setNewProductRecyclerview();
        setPopularProductRecyclerview();

        return view;
    }


    private void setCategoryRecyclerview() {
        categoryRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        categoryModelList = new ArrayList<>();
        adapter = new CategoryAdapter(getActivity(),categoryModelList);
        categoryRecyclerView.setAdapter(adapter);
        db.collection("Category")
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if (task.isSuccessful()){
                            for (QueryDocumentSnapshot snapshot: task.getResult()){
                                CategoryModel categoryModel = snapshot.toObject(CategoryModel.class);
                                categoryModelList.add(categoryModel);
                                adapter.notifyDataSetChanged();
                            }
                        }else
                        {
                            Toast.makeText(getActivity(), task.getException()+"", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }
    private void setNewProductRecyclerview() {
        productRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        newProductsList = new ArrayList<>();
        productsAdapter = new NewProductsAdapter(getActivity(),newProductsList);
        productRecyclerView.setAdapter(productsAdapter);

        db.collection("NewProducts")
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if (task.isSuccessful()){
                            for (QueryDocumentSnapshot snapshot: task.getResult()){
                                NewProductsModel newProductsModel = snapshot.toObject(NewProductsModel.class);
                                newProductsList.add(newProductsModel);
                                productsAdapter.notifyDataSetChanged();
                            }
                        }else
                        {
                            Toast.makeText(getActivity(), task.getException()+"", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    private void setPopularProductRecyclerview() {
        popularRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        popularProductList = new ArrayList<>();
        popularProductAdapter = new PopularProductAdapter(getContext(),popularProductList);
        popularRecyclerView.setAdapter(popularProductAdapter);



        db.collection("Popular Products")
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if (task.isSuccessful()){
                            for (QueryDocumentSnapshot snapshot:task.getResult()){
                                PopularProductModel popularProductModel = snapshot.toObject(PopularProductModel.class);
                                popularProductList.add(popularProductModel);
                                productsAdapter.notifyDataSetChanged();
                            }
                        }else
                        {
                            Toast.makeText(getActivity(), task.getException()+"", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
