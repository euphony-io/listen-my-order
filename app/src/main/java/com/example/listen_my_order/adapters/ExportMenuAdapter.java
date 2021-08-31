package com.example.listen_my_order.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.listen_my_order.R;
import com.example.listen_my_order.activities.MenuData;

import java.util.ArrayList;

public class ExportMenuAdapter extends RecyclerView.Adapter<ExportMenuAdapter.CustomViewHolder> {

    private ArrayList<MenuData> menuList;

    public ExportMenuAdapter(ArrayList<MenuData> menuList) {
        this.menuList = menuList;
    }

    @NonNull
    @Override
    public ExportMenuAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_menu, parent, false);
        CustomViewHolder holder = new CustomViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ExportMenuAdapter.CustomViewHolder holder, int position) {
        holder.tv_name.setText(menuList.get(position).getName());
        holder.tv_content.setText(menuList.get(position).getContent());
        holder.tv_price.setText("$ " + Float.toString(menuList.get(position).getPrice()));

        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return (menuList != null ? menuList.size() : 0);
    }

    public void remove(int position){
        try{
            menuList.remove(position);
            notifyItemRemoved(position);
        } catch (IndexOutOfBoundsException e){
            e.printStackTrace();
        }
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        protected TextView tv_name;
        protected TextView tv_content;
        protected TextView tv_price;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            this.tv_content = (TextView) itemView.findViewById(R.id.tv_content);
            this.tv_price = (TextView) itemView.findViewById(R.id.tv_price);
        }
    }

}
