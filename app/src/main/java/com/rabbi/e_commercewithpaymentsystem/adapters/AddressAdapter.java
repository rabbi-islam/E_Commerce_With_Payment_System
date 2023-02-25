package com.rabbi.e_commercewithpaymentsystem.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rabbi.e_commercewithpaymentsystem.R;
import com.rabbi.e_commercewithpaymentsystem.models.AddressModel;

import java.util.List;

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.AddressViewAdapter> {

    Context context;
    List<AddressModel> list;
    SelectedAddress selectedAddress;
    private RadioButton selectedRadioBtn;

    public AddressAdapter(Context context, List<AddressModel> list, SelectedAddress selectedAddress) {
        this.context = context;
        this.list = list;
        this.selectedAddress = selectedAddress;
    }

    @NonNull
    @Override
    public AddressViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.address_item,parent,false);
        return new AddressViewAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AddressViewAdapter holder, int position) {

        holder.address.setText(list.get(position).getUserAddress());
        holder.radioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "hii", Toast.LENGTH_SHORT).show();
                for(AddressModel address:list){
                    address.setSelected(false);
                }

                list.get(holder.getAdapterPosition()).setSelected(true);

                if (selectedRadioBtn != null){
                    selectedRadioBtn.setChecked(false);
                }
                selectedRadioBtn = (RadioButton) view;
                selectedRadioBtn.setChecked(true);
                selectedAddress.setAddress(list.get(holder.getAdapterPosition()).getUserAddress());

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class AddressViewAdapter extends RecyclerView.ViewHolder {

        TextView address;
        RadioButton radioButton;

        public AddressViewAdapter(@NonNull View itemView) {
            super(itemView);

            address = itemView.findViewById(R.id.address_add);
            radioButton = itemView.findViewById(R.id.select_address);
        }
    }

    public  interface SelectedAddress{
        void setAddress(String address);
    }
}
