package com.example.memorygame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.GridLayoutManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import models.model.BoardSize;
import models.model.MemoryCard;

public class MainActivity extends AppCompatActivity{

    private TextView numMoves;
    private TextView numPairs;
    private RecyclerView recyclerView;
    private ImageButton imageButton;
    private BoardSize boardSize;
    private MemoryAdapter memoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numMoves = findViewById(R.id.numMoves);
        numPairs = findViewById(R.id.numPairs);
        imageButton = findViewById(R.id.imageButton);
        recyclerView = findViewById(R.id.recyclerViewBoard);

        BoardSize boardSize = BoardSize.EASY;

        // shuffle所有IMAGE_ICONS中的元素，並賦值個新的list，方便調用
        Collections.shuffle(Constants.IMAGE_ICONS);
        List<Integer> shuffled = new ArrayList<>();
        shuffled.addAll(Constants.IMAGE_ICONS);

        // 配對只需要一半IMAGE_ICONS，獲得shuffle後，根據boardsize大小
        // 來選擇相應數量的IMAGE_ICONS，但選完後，選中的IMAGE_ICONS要*2，保證
        // 填滿board
        List<Integer> shuffledGetPairs = shuffled.subList(0, boardSize.getNumPairs());
        List<Integer> shuffledGetPairs2 = shuffled.subList(0, boardSize.getNumPairs());
        shuffledGetPairs2.addAll(shuffledGetPairs);
        Collections.shuffle(shuffledGetPairs2);

        List<MemoryCard> finalShuffled = new ArrayList<>();
        for(int i=0;i<shuffledGetPairs2.size();i++){
            MemoryCard memoryCard = new MemoryCard(shuffledGetPairs2.get(i), false, false);
            finalShuffled.add(memoryCard);
        }

        memoryAdapter = new MemoryAdapter(this, imageButton, boardSize, finalShuffled,
                new MemoryAdapter.CardClickListener() {  // 要使用MemoryAdapter中定义的interface，直接new一个就可以，记得一定要override
                    @Override
                    public void onCardClicked(int position) {
//                        Log.e("onCardClicked","onCardClicked in MainActivity: "+position);
                         updateGameFlip(position);
                    }
                });

        recyclerView.setAdapter(memoryAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, boardSize.getColumnNum()));

    }


    private void updateGameFlip(int position){
        memoryAdapter.flipCard(position);
        memoryAdapter.notifyDataSetChanged();
    }
}