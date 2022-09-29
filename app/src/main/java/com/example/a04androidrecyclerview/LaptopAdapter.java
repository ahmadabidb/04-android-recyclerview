package com.example.a04androidrecyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class LaptopAdapter extends RecyclerView.Adapter<LaptopAdapter.LaptopViewHolder>{
    private ArrayList<LaptopData> laptopList;
    private LayoutInflater lInflater;
    private View.OnClickListener lOnItemClickListener;

    public LaptopAdapter(Context context, ArrayList<LaptopData> laptopList){
        lInflater = LayoutInflater.from(context);
        this.laptopList = laptopList;
    }

    @NonNull
    @Override
    public LaptopAdapter.LaptopViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View lItemView = lInflater.inflate(R.layout.laptop_list, viewGroup, false);
        return new LaptopViewHolder(lItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull LaptopAdapter.LaptopViewHolder laptopViewHolder, int position) {
        laptopViewHolder.name.setText((laptopList.get(position)).getName());
        laptopViewHolder.description.setText((laptopList.get(position)).getDescription());
        Glide.with(laptopViewHolder.itemView)
                .load(laptopList.get(position).getImage())
                .override(100, 150)
                .into(laptopViewHolder.image);
    }


    @Override
    public int getItemCount() {
        return laptopList.size();
    }

    public void setOnItemClickListener(View.OnClickListener onItemClickListener) {
        lOnItemClickListener = onItemClickListener;
    }

    class LaptopViewHolder extends RecyclerView.ViewHolder {
        TextView name, description;
        ImageView image;


        public LaptopViewHolder(View itemView){
            super(itemView);
            name = itemView.findViewById(R.id.laptop_name);
            description = itemView.findViewById(R.id.laptop_description);
            image = itemView.findViewById(R.id.laptop_image);
            itemView.setTag(this);
            itemView.setOnClickListener(lOnItemClickListener);
        }
    }
}