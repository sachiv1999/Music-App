package com.example.mediaplayer;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.List;

public class SingerAdapter extends ArrayAdapter<SingerModel> {

    public SingerAdapter(Context context, List<SingerModel> singers) {
        super(context, 0, singers);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.item_singer, parent, false);
        }

        SingerModel singer = getItem(position);
        ImageView img = convertView.findViewById(R.id.imgSinger);
        TextView name = convertView.findViewById(R.id.txtSingerName);

        img.setImageResource(singer.getImageResId());
        name.setText(singer.getName());

        // Change background based on selection
        if (singer.isSelected()) {
            convertView.setBackgroundResource(R.drawable.item_selected_gradient);
        } else {
            convertView.setBackgroundResource(R.drawable.item_unselected);
        }

        return convertView;
    }
}