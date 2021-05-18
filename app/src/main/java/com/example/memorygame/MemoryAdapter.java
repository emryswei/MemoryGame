package com.example.memorygame;

import android.content.Context;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Enumeration;

public class MemoryAdapter extends RecyclerView.Adapter<MemoryAdapter.ViewHolder>{

    private static final String TAG = "MemoryAdapter";
    private Context context;
    private ImageButton imageButton;
    private int numPieces;

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private ImageButton imageButton;
        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            imageButton = itemView.findViewById(R.id.imageButton);
        }
    }

    public MemoryAdapter(Context context, ImageButton imageButton, int numPieces) {
        this.context = context;
        this.imageButton = imageButton;
        this.numPieces = numPieces;
    }


    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        double parentHeight = parent.getHeight() / 4;
        double parentWidth = parent.getWidth() / 2;
        double smaller = Math.min(parentHeight, parentWidth);
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.memory_card, parent, false);
        View layoutParams = view.findViewById(R.id.cardView);
        layoutParams.getLayoutParams().width = (int)smaller;
        layoutParams.getLayoutParams().height = (int)smaller;
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MemoryAdapter.ViewHolder holder, int position) {
        holder.imageButton.setImageResource(R.drawable.ic_launcher_background);
        holder.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "onClicked position "+position, Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return numPieces;
    }



}
