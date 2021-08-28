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

    // ViewHolder class that store itemView
    public class ViewHolder extends RecyclerView.ViewHolder {
        protected TextView tv_name;
        protected TextView tv_content;
        protected TextView tv_price;

        ViewHolder(View itemView) {
            super(itemView);

            // reference about view object (hold strong reference)
            this.tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            this.tv_content = (TextView) itemView.findViewById(R.id.tv_content);
            this.tv_price = (TextView) itemView.findViewById(R.id.tv_price);
        }
    }

    // onCreateViewHolder() - create ViewHolder object for itemView and return it.
    @Override
    public ImportMenuAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.item_list_menu, parent, false);
        ImportMenuAdapter.ViewHolder vh = new ImportMenuAdapter.ViewHolder(view);

        return vh;
    }

    // onBindViewHolder() - set data corresponding to position of mData to itemView of viewHolder.
    @Override
    public void onBindViewHolder(ImportMenuAdapter.ViewHolder holder, int position) {
        holder.tv_name.setText(mData.get(position).getName());
        holder.tv_content.setText(mData.get(position).getContent());
        holder.tv_price.setText("$ " + Float.toString(mData.get(position).getPrice()));

        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Todo:메뉴 선택 기능 구현
            }
        });
    }

    // getItemCount() - return mData size.
    @Override
    public int getItemCount() {
        return mData != null ? mData.size() : 0;
    }
}
