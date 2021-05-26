package com.example.memorygame;

import android.content.Context;
import android.content.res.ColorStateList;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import models.model.BoardSize;
import models.model.MemoryCard;

public class MemoryAdapter extends RecyclerView.Adapter<MemoryAdapter.ViewHolder>{

    private static final String TAG = "MemoryAdapter";
    private Context context;
    private ImageButton imageButton;
    private BoardSize boardSize;
    private static final int MARGIN_SIZE = 10;
    private List<MemoryCard> cardImages;
    private CardClickListener cardClickListener;
    private Integer indexOfSelectedCard = null;
    private MemoryCard memoryCard;
    private int numPairsFound;
    public interface CardClickListener{
        void onCardClicked(int position);
    }

    public int getNumPairsFound() {
        return numPairsFound;
    }

    public void setNumPairsFound(int numPairsFound) {
        this.numPairsFound = numPairsFound;
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
        if(cardImages.get(position).getMatched()){
            holder.imageButton.setAlpha(0.4f);
        } else {
            holder.imageButton.setAlpha(1.0f);}
        ColorStateList colorStateList = ContextCompat.getColorStateList(context, R.color.color_gray);
        ViewCompat.setBackgroundTintList(holder.imageButton, colorStateList);
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


    public boolean flipCard(int position){
//        Boolean cardFacedUp = cardImages.get(position).getFaceUp();
//        cardImages.get(position).setFaceUp(!cardFacedUp);

        boolean foundMatched = false;

        // 翻牌的3种情况
        // 1. 翻了0张 ----> 把所有的牌都翻回去，翻开选择的那1张
        // 2. 翻了1张 ----> 选中新的1张翻开，和之前的1张做比较看是否配对
        // 3. 翻了2张 ----> 之前翻的2张翻回去，再翻开选择的那张
        if(indexOfSelectedCard==null){
            // 不管翻0张还是2张，因为没有配对，先都翻回去
            // position是当前点击的牌
            restoreCards();
            indexOfSelectedCard = position;
        } else {
            // 已经翻了1张，做比较
            // 成功配对，indexOfSelectedCard变为null，不做比较判断
            // 不成功，indexOfSelectedCard也变为null，因为没配对的在下一次点击时要翻回去
            foundMatched = checkMatch(indexOfSelectedCard, position);
            indexOfSelectedCard = null;
        }
        //点击后的那张牌都要翻出来
        cardImages.get(position).setFaceUp(!cardImages.get(position).getFaceUp());
        return foundMatched;
    }

    public boolean checkMatch(int position1, int position2){
        // 配对成功，2张的setMatched = true
        if(cardImages.get(position1).getIt() != cardImages.get(position2).getIt()){
            return false;
        }
        cardImages.get(position1).setMatched(true);
        cardImages.get(position2).setMatched(true);
        numPairsFound++;
        return true;
    }

    public void restoreCards(){
        // 把没有match的翻回去
        for(MemoryCard card:cardImages){
            if(!card.getMatched()){
                card.setFaceUp(false);
            }
        }
    }

    // 当全部配对时赢得游戏
    public boolean wonTheGame(){
        return numPairsFound == boardSize.getNumPairs();
    }

    // 查看是否被翻出来
    public boolean ifIsFaceUp(int position){
        return cardImages.get(position).getFaceUp();
    }
}
