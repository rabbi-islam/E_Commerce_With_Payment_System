package com.rabbi.e_commercewithpaymentsystem.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.rabbi.e_commercewithpaymentsystem.R;
import com.rabbi.e_commercewithpaymentsystem.models.CategoryModel;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewAdapter> {

    Context context;
    List<CategoryModel> categoryList;

    public CategoryAdapter(Context context, List<CategoryModel> categoryList) {
        this.context = context;
        this.categoryList = categoryList;
    }

    @NonNull
    @Override
    public CategoryViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.categort_list,parent,false);
        return new CategoryViewAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewAdapter holder, int position) {

        try {
            Glide.with(context).load(categoryList.get(position).getImgUrl()).into(holder.catImage);
        } catch (Exception e) {
            e.printStackTrace();
        }

        holder.catName.setText(categoryList.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public class CategoryViewAdapter extends RecyclerView.ViewHolder {

        ImageView catImage;
        TextView catName;

        public CategoryViewAdapter(@NonNull View itemView) {
            super(itemView);

            catImage = itemView.findViewById(R.id.cat_img);
            catName = itemView.findViewById(R.id.cat_name);
        }
    }
}
