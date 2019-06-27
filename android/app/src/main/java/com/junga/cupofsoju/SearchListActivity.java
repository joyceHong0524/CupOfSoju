package com.junga.cupofsoju.Adapter;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.junga.cupofsoju.R;
import com.junga.cupofsoju.model.StoreData;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context  context;
    ArrayList<Drawable> drawables = new ArrayList<>();
    public static class SearchViewHodler extends RecyclerView.ViewHolder {
        ImageView ivPicture;
        TextView tvName;
        TextView tvAddress;
        TextView tvCall;

        SearchViewHodler(View view) {
            super(view);
            ivPicture = view.findViewById(R.id.item_image);
            tvName = view.findViewById(R.id.item_name);
            tvAddress =  view.findViewById(R.id.item_address);
            tvCall =  view.findViewById(R.id.item_call);

        }
    }

    private ArrayList<StoreData> storeInfo;

    public SearchAdapter(ArrayList<StoreData> storeInfo, Context context) {
        this.storeInfo = storeInfo;
        this.context = context;

        drawables.add(ContextCompat.getDrawable(context,R.drawable.store1));
        drawables.add(ContextCompat.getDrawable(context,R.drawable.store2));
        drawables.add(ContextCompat.getDrawable(context,R.drawable.store3));
        drawables.add(ContextCompat.getDrawable(context,R.drawable.store4));
        drawables.add(ContextCompat.getDrawable(context,R.drawable.store5));

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_store, parent, false);

        return new SearchViewHodler(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        SearchViewHodler myViewHolder = (SearchViewHodler) holder;



        Glide.with(context).load(drawables.get(position%5)).into(myViewHolder.ivPicture);
        myViewHolder.tvName.setText(storeInfo.get(position).getName());
        myViewHolder.tvAddress.setText(storeInfo.get(position).getLocation());
        myViewHolder.tvCall.setText(storeInfo.get(position).getPhone());

        myViewHolder.ivPicture.setBackground(new ShapeDrawable(new OvalShape()));
        myViewHolder.ivPicture.setClipToOutline(true);

    }

    @Override
    public int getItemCount() {
        return storeInfo.size();
    }

    public  void resetting(ArrayList<StoreData>newlist){
        storeInfo = newlist;
        notifyDataSetChanged();
    }
    public void clear(){
        storeInfo.clear();
        notifyDataSetChanged();
    }
}
