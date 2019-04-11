package com.lab_work;

import java.util.List;
import android.view.View;
import android.graphics.Color;
import android.view.ViewGroup;
import android.widget.TextView;
import android.content.Context;
import android.widget.ImageView;
import android.view.LayoutInflater;
import com.squareup.picasso.Picasso;
import android.support.v7.widget.RecyclerView;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private List<CivilizationItem> items;
    private LayoutInflater inflater;
    private ItemClickListener clickListener;

    private final String PIC_URL = "https://raw.githubusercontent.com/wesleywerner/ancient-tech/02decf875616dd9692b31658d92e64a20d99f816/src/images/tech/";

    RecyclerAdapter(Context context, List<CivilizationItem> items) {
        this.inflater = LayoutInflater.from(context);
        this.items = items;
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

    public void setClickListener(ItemClickListener itemClickListener) {
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
        Picasso.with(inflater.getContext())
                .load(PIC_URL + items.get(position).getGraphic())
                .placeholder(R.drawable.white_pic)
                .error(R.drawable.image)
                .into(holder.icon);
        holder.text.setText(items.get(position).getName());
        String color = ((position + 1) % 2 == 0) ? "#FFD700" : "#FFFFFF";
        holder.itemView.setBackgroundColor(Color.parseColor(color));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}