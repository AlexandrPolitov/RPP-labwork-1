package com.lab_work;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private final static int SIZE_OF_LIST = 1000000;

    public RecyclerViewAdapter() {
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView text;
        public ImageView image;
        public ViewHolder(View itemView) {
            super(itemView);
            this.text = (TextView) itemView.findViewById(R.id.number);
            this.image = (ImageView) itemView.findViewById(R.id.image);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new ViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String color = ((position + 1) % 2 == 0) ? "#CCCCCC" : "#FFFFFF";
        holder.itemView.setBackgroundColor(Color.parseColor(color));
        holder.image.setImageResource(R.drawable.pic_36);
        holder.text.setText(ItemModel.num2words(position + 1,1));
    }

    @Override
    public int getItemCount() {
        return SIZE_OF_LIST;
    }
}
