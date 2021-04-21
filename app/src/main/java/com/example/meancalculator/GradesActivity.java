package com.example.meancalculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import java.util.ArrayList;

public class GradesActivity extends AppCompatActivity {

    Button button;
    RecyclerView recyclerView;
    ArrayList<GradeModel> gradeModelArrayList;

    public GradesActivity() {}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grades_layout);

        gradeModelArrayList = new ArrayList<>();

        String[] gradesList = getResources().getStringArray(R.array.gradeTable);

        Bundle bundle = getIntent().getExtras();
        final int gradesNumber = bundle.getInt("gradesInput");

        GradeModel.setCount(0);
        for (int i = 0; i < gradesNumber; i++) {
            GradeModel grade = new GradeModel(gradesList[i]);
            gradeModelArrayList.add(grade);
        }

        // Create an adapter
        InteractiveArrayAdapter interactiveArrayAdapter = new InteractiveArrayAdapter(gradeModelArrayList, this);
        // Find the reference to the RecyclerView object
        recyclerView = findViewById(R.id.gradesList);
        // Connect the grades list with data
        recyclerView.setAdapter(interactiveArrayAdapter);
        // Set the layout that set the elements in RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        button = findViewById(R.id.countButton);

        button.setOnClickListener(v -> {
            Bundle bundle2 = new Bundle();
            bundle2.putDouble("srednia", calculateMean());
            Intent intent = new Intent();
            intent.putExtras(bundle2);
            setResult(RESULT_OK, intent);
            finish();
        });

        if (savedInstanceState != null ) {
            // Get a saved array with chosen grades
            int[] savedGrades = savedInstanceState.getIntArray("gradesValue");
            for (int i = 0; i < savedGrades.length; i++) {
                // Set chosen grades
                gradeModelArrayList.get(i).setValue(savedGrades[i]);
            }
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        // Create a new array the size of a grade ArrayList
        int[] allGrades = new int[gradeModelArrayList.size()];

        for (int i = 0; i < allGrades.length; i++) {
            // Add next grades to the array
            allGrades[i] = gradeModelArrayList.get(i).getValue();
        }

        // Save an array with all chosen grades
        outState.putIntArray("gradesValue", allGrades);

        super.onSaveInstanceState(outState);
    }

    public double calculateMean() {
        double sum = 0;
        for (GradeModel element : gradeModelArrayList) {
            int grade = element.getValue();
            sum += grade;
        }
        return sum/(double) gradeModelArrayList.size();
    }
}