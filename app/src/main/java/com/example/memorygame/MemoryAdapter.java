package com.example.memorygame;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

public class MemoryAdapter extends RecyclerView.Adapter<MemoryAdapter.ViewHolder>{

    private static final String TAG = "MemoryAdapter";
    private Context context;
    private ImageButton imageButton;
    private int numPieces;
    private static final int MARGIN_SIZE = 10;


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
        int cardHeight = parent.getHeight() / 4 - (2*MARGIN_SIZE);  // 减去(2*MARGIN_SIZE)保证卡与卡之间有一定空隙
        int cardWidth = parent.getWidth() / 2 - (2*MARGIN_SIZE);
        int cardSmaller = Math.min(cardHeight, cardWidth); // 保证每张卡都是正方形，取最小值

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.memory_card, parent, false);

        View cardParams = view.findViewById(R.id.cardView);
        cardParams.getLayoutParams().width = (int)cardSmaller;
        cardParams.getLayoutParams().height = (int)cardSmaller;

        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        layoutParams.setMargins(MARGIN_SIZE,MARGIN_SIZE,MARGIN_SIZE,MARGIN_SIZE);
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
