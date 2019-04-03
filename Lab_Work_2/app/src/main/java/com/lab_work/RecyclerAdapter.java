package com.lab_work;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private final static int SIZE_OF_LIST = 100;

    public RecyclerAdapter() {
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView text;
        public ImageView icon;
        public ViewHolder(View itemView) {
            super(itemView);
            this.text = (TextView) itemView.findViewById(R.id.text);
            this.icon = (ImageView) itemView.findViewById(R.id.icon);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false);
        return new ViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.icon.setImageResource(R.drawable.books);
        holder.text.setText("Item Example");
    }

    @Override
    public int getItemCount() {
        return SIZE_OF_LIST;
    }
}
