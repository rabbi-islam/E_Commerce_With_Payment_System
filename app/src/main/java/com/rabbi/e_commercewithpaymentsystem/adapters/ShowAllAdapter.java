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
import com.rabbi.e_commercewithpaymentsystem.models.ShowAllModel;

import java.util.List;

public class ShowAllAdapter extends RecyclerView.Adapter<ShowAllAdapter.ShowAllViewAdapter> {

    Context context;
    List<ShowAllModel> list;

    public ShowAllAdapter(Context context, List<ShowAllModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ShowAllViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.show_all_item,parent,false);
        return new ShowAllViewAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShowAllViewAdapter holder, int position) {

        try {
            Glide.with(context).load(list.get(position).getImg_url()).into(holder.image);
        } catch (Exception e) {
            e.printStackTrace();
        }

        holder.name.setText(list.get(position).getName());
        holder.price.setText("$"+list.get(position).getPrice());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ShowAllViewAdapter extends RecyclerView.ViewHolder {

        ImageView image;
        TextView name,price;

        public ShowAllViewAdapter(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.item_image);
            name = itemView.findViewById(R.id.item_nam);
            price = itemView.findViewById(R.id.item_cost);
        }
    }
}
