package com.example.meancalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;

public class GradesActivity extends AppCompatActivity {

    Button button;
    RecyclerView recyclerView;

    public GradesActivity(Button button, RecyclerView recyclerView) {
        this.button = button;
        this.recyclerView = recyclerView;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grades);
    }
}