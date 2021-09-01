package com.example.listen_my_order.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.listen_my_order.R;
import com.example.listen_my_order.activities.MenuData;

import java.util.ArrayList;

public class ExportOrderAdapter extends RecyclerView.Adapter<ExportOrderAdapter.ViewHolder> {

    private ArrayList<MenuData> mData;

    public ExportOrderAdapter(ArrayList<MenuData> list) {
        mData = list;
    }

    // ViewHolder class that store itemView
    public class ViewHolder extends RecyclerView.ViewHolder {
        protected TextView tv_order_name;
        protected TextView tv_order_price;

        ViewHolder(View itemView) {
            super(itemView);

            // reference about view object (hold strong reference)
            this.tv_order_name = (TextView) itemView.findViewById(R.id.tv_order_name);
            this.tv_order_price = (TextView) itemView.findViewById(R.id.tv_order_price);
        }
    }

    // onCreateViewHolder() - create ViewHolder object for itemView and return it.
    @Override
    public ExportOrderAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.item_order, parent, false);
        ExportOrderAdapter.ViewHolder vh = new ExportOrderAdapter.ViewHolder(view);

        return vh;
    }

    // onBindViewHolder() - set data corresponding to position of mData to itemView of viewHolder.
    @Override
    public void onBindViewHolder(ExportOrderAdapter.ViewHolder holder, int position) {
        MenuData item = mData.get(position);

        holder.tv_order_name.setText(item.getName());
        holder.tv_order_price.setText("$ " + Float.toString(item.getPrice()));

        holder.itemView.setTag(position);
    }

    // getItemCount() - return mData size.
    @Override
    public int getItemCount() {
        return mData != null ? mData.size() : 0;
    }
}
