package com.example.mediaplayer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {

    private final List<SingerModel> imageList;

    public ImageAdapter(List<SingerModel> imageList) {
        this.imageList = imageList;
    }

    public static class ImageViewHolder extends RecyclerView.ViewHolder {
        ImageView itemImage;
        TextView itemName;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            // Find views by ID defined in list_item_image.xml
            itemImage = itemView.findViewById(R.id.item_image);
            itemName = itemView.findViewById(R.id.item_name);
        }
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the item layout (list_item_image.xml)
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_singer_card, parent, false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        SingerModel currentItem = imageList.get(position);

        // Set the actual image resource ID
        holder.itemImage.setImageResource(currentItem.getImageResId());

        // Set the name text
        holder.itemName.setText(currentItem.getName());
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }
}