package com.lab_work;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private List<String> names;
    private LayoutInflater inflater;
    private ItemClickListener clickListener;

    RecyclerAdapter(Context context, List<String> names) {
        this.inflater = LayoutInflater.from(context);
        this.names = names;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView text;
        public ImageView icon;

        public ViewHolder(View itemView) {
            super(itemView);
            this.text = (TextView) itemView.findViewById(R.id.text);
            this.icon = (ImageView) itemView.findViewById(R.id.icon);

            itemView.setOnClickListener(this);
            }

            @Override
            public void onClick (View view){
            if(clickListener != null)
                clickListener.onItemClick(view, getAdapterPosition());
        }
    }

    String getName(int position) {
        return names.get(position);
    }

    void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item, parent,
                false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.icon.setImageResource(R.drawable.books);
        String name = names.get(position);
        holder.text.setText(name);
    }

    @Override
    public int getItemCount() {
        return names.size();
    }
}