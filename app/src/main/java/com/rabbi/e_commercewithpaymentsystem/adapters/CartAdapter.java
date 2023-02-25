package com.rabbi.e_commercewithpaymentsystem.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rabbi.e_commercewithpaymentsystem.R;
import com.rabbi.e_commercewithpaymentsystem.models.CartModel;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewAdapter> {

    Context context;
    List<CartModel> list;
    int totalAmount=0;

    public CartAdapter(Context context, List<CartModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public CartViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.my_cart_item,parent,false);
        return new CartViewAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewAdapter holder, int position) {

        holder.name.setText(list.get(position).getProductName());
        holder.time.setText(list.get(position).getCurrentTime());
        holder.date.setText(list.get(position).getCurrentDate());
        holder.price.setText(list.get(position).getProductPrice());
        holder.quantity.setText(String.valueOf(list.get(position).getQuantity()));
        holder.totalPrice.setText(String.valueOf(list.get(position).getTotalPrice()));

        totalAmount = totalAmount + list.get(position).getTotalPrice();
        Intent intent = new Intent("MyTotalAmount");
        intent.putExtra("totalAmount",totalAmount);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class CartViewAdapter extends RecyclerView.ViewHolder {

        TextView name,price,date,time,totalPrice,quantity;

        public CartViewAdapter(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.product_name);
            price = itemView.findViewById(R.id.product_price);
            date = itemView.findViewById(R.id.current_date);
            time = itemView.findViewById(R.id.current_time);
            quantity = itemView.findViewById(R.id.total_quantity);
            totalPrice = itemView.findViewById(R.id.total_price);
        }
    }
}
