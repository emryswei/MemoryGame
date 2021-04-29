package com.example.memorygame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView numMoves;
    private TextView numPairs;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numMoves = findViewById(R.id.numMoves);
        numPairs = findViewById(R.id.numPairs);
        recyclerView = findViewById(R.id.recyclerViewBoard);



    }
}