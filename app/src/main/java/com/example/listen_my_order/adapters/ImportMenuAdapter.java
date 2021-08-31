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

public class ImportMenuAdapter extends RecyclerView.Adapter<ImportMenuAdapter.ViewHolder> {

    private ArrayList<MenuData> mData;

    public ImportMenuAdapter(ArrayList<MenuData> list) {
        mData = list;
    }

    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }

    private OnItemClickListener mItemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        mItemClickListener = listener;
    }

    // ViewHolder class that store itemView
    public class ViewHolder extends RecyclerView.ViewHolder {
        protected TextView tv_item_name;
        protected TextView tv_item_content;
        protected TextView tv_item_price;

        ViewHolder(View itemView, final OnItemClickListener itemClickListener) {
            super(itemView);

            // reference about view object (hold strong reference)
            this.tv_item_name = (TextView) itemView.findViewById(R.id.tv_item_name);
            this.tv_item_content = (TextView) itemView.findViewById(R.id.tv_item_content);
            this.tv_item_price = (TextView) itemView.findViewById(R.id.tv_item_price);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final int position = getAdapterPosition();

                    if(position != RecyclerView.NO_POSITION) {
                        itemClickListener.onItemClick(v, position);
                    }
                }
            });
        }
    }

    // onCreateViewHolder() - create ViewHolder object for itemView and return it.
    @Override
    public ImportMenuAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.item_select_menu, parent, false);
        ImportMenuAdapter.ViewHolder vh = new ImportMenuAdapter.ViewHolder(view, mItemClickListener);

        return vh;
    }

    // onBindViewHolder() - set data corresponding to position of mData to itemView of viewHolder.
    @Override
    public void onBindViewHolder(ImportMenuAdapter.ViewHolder holder, int position) {
        MenuData item = mData.get(position);

        holder.tv_item_name.setText(item.getName());
        holder.tv_item_content.setText(item.getContent());
        holder.tv_item_price.setText("$ " + Float.toString(item.getPrice()));

        holder.itemView.setTag(position);
    }

    // getItemCount() - return mData size.
    @Override
    public int getItemCount() {
        return mData != null ? mData.size() : 0;
    }
}
