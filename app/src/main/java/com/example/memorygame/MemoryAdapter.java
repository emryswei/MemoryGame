package com.example.memorygame;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import models.model.BoardSize;
import models.model.MemoryCard;

public class MemoryAdapter extends RecyclerView.Adapter<MemoryAdapter.ViewHolder>{

    private static final String TAG = "MemoryAdapter";
    public static Object CardClickListener;
    private Context context;
    private ImageButton imageButton;
    private BoardSize boardSize;
    private static final int MARGIN_SIZE = 10;
    private List<MemoryCard> cardImages;
    private CardClickListener cardClickListener;

    public interface CardClickListener{
        void onCardClicked(int position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private ImageButton imageButton;
        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            imageButton = itemView.findViewById(R.id.imageButton);
        }
    }

    public MemoryAdapter(Context context, ImageButton imageButton, BoardSize boardSize, List<MemoryCard> cardImages, CardClickListener cardClickListener) {
        this.context = context;
        this.imageButton = imageButton;
        this.boardSize = boardSize;
        this.cardImages = cardImages;
        this.cardClickListener = cardClickListener;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int cardHeight = parent.getHeight() / boardSize.getRowNum() - (2*MARGIN_SIZE);  // 减去(2*MARGIN_SIZE)保证卡与卡之间有一定空隙
        int cardWidth = parent.getWidth() / boardSize.getColumnNum() - (2*MARGIN_SIZE);

        int cardSmallerSide = Math.min(cardHeight, cardWidth); // 保证每张卡都是正方形，取最小值

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.memory_card, parent, false);

        ViewGroup cardParams = view.findViewById(R.id.cardView);
        cardParams.getLayoutParams().width = cardSmallerSide;
        cardParams.getLayoutParams().height = cardSmallerSide;

        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        layoutParams.setMargins(MARGIN_SIZE,MARGIN_SIZE,MARGIN_SIZE,MARGIN_SIZE);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MemoryAdapter.ViewHolder holder, int position) {
        if(!cardImages.get(position).getFaceUp()){
            holder.imageButton.setImageResource(R.drawable.ic_launcher_background);
        } else {
            holder.imageButton.setImageResource(cardImages.get(position).getIt());
        }

        holder.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("OnClickListener", "position clicked: "+position);
                cardClickListener.onCardClicked(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return boardSize.getNumCards();
    }


}
