package com.example.meancalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;

import java.util.ArrayList;

public class GradesActivity extends AppCompatActivity {

    Button button;
    RecyclerView recyclerView;
    ArrayList<GradeModel> gradeModelArrayList;

    public GradesActivity(Button button, RecyclerView recyclerView) {
        this.button = button;
        this.recyclerView = recyclerView;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grades);

        gradeModelArrayList = new ArrayList<>();

        String[] gradesList = getResources().getStringArray(R.array.gradeTable);

        Bundle bundle = getIntent().getExtras();
        final int gradesNumber = bundle.getInt("gradesInput");

        for (int i = 0; i < gradesNumber; i++) {
            GradeModel grade = new GradeModel(gradesList[i]);
            gradeModelArrayList.add(grade);
        }

        // Create an adapter
        InteractiveArrayAdapter interactiveArrayAdapter = new InteractiveArrayAdapter(gradeModelArrayList, this);
        // Find the reference to the RecycleView object
        recyclerView = findViewById(R.id.gradesList);
        // Connect the grades list with data
        recyclerView.setAdapter(interactiveArrayAdapter);

        // Set the layout that set the elements in RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}