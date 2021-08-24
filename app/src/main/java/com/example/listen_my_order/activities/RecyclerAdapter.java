package com.example.listen_my_order.activities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.listen_my_order.R;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private ArrayList<String> mData = null;

    RecyclerAdapter(ArrayList<String> list) {
        mData = list;
    }

    // ViewHolder class that store itemView
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv;

        ViewHolder(View itemView) {
            super(itemView);

            // reference about view object (hold strong reference)
            tv = itemView.findViewById(R.id.tv_rc_item);
        }
    }

    // onCreateViewHolder() - create ViewHolder object for itemView and return it.
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.recyclerview_item, parent, false);
        RecyclerAdapter.ViewHolder vh = new RecyclerAdapter.ViewHolder(view);

        return vh;
    }

    // onBindViewHolder() - set data corresponding to position of mData to itemView of viewHolder.
    @Override
    public void onBindViewHolder(RecyclerAdapter.ViewHolder holder, int position) {
        String text = mData.get(position);
        holder.tv.setText(text);
    }

    // getItemCount() - return mData size.
    @Override
    public int getItemCount() {
        return mData.size();
    }
}
