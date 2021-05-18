package com.example.memorygame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.GridLayoutManager;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView numMoves;
    private TextView numPairs;
    private RecyclerView recyclerView;
    private ImageButton imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numMoves = findViewById(R.id.numMoves);
        numPairs = findViewById(R.id.numPairs);
        imageButton = findViewById(R.id.imageButton);
        recyclerView = findViewById(R.id.recyclerViewBoard);
        recyclerView.setHasFixedSize(true);

        MemoryAdapter memoryAdapter = new MemoryAdapter(this, imageButton, 8);
        recyclerView.setAdapter(memoryAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));

    }
}