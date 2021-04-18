package com.example.meancalculator;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class InteractiveArrayAdapter extends RecyclerView.Adapter<InteractiveArrayAdapter.GradesViewHolder> {

    private List<GradeModel> gradesActivityList;
    private LayoutInflater layoutInflater;

    // Save the reference to the data source and create the LayoutInflater object
    public InteractiveArrayAdapter(List<GradeModel> gradesActivityList, Activity activity) {
        this.gradesActivityList = gradesActivityList;
        this.layoutInflater = activity.getLayoutInflater();
    }

    // Create the main layout element (a list row) and create a holder for the given row
    @NonNull
    @Override
    public GradesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Created a row layout based on XML
        @SuppressLint("InflateParams") View line = layoutInflater.inflate(R.layout.list_line, null);
        // Return new holder object
        return new GradesViewHolder(line);
    }

    // Populate a row, stored in a holder, with data for the specified row
    @Override
    public void onBindViewHolder(@NonNull GradesViewHolder holder, int position) {
        // Get the correct grade from the data source
        GradeModel grade = gradesActivityList.get(position);

        // Connect radio buttons group with a list row
        holder.radioGroup.setTag(grade);

        // Set the name of the subject
        holder.textView.setText(grade.getName());

        // Select a radio button
        switch (position) {
            case 2:
                holder.radioGroup.check(R.id.radioButton6);
                break;
            case 3:
                holder.radioGroup.check(R.id.radioButton7);
                break;
            case 4:
                holder.radioGroup.check(R.id.radioButton8);
                break;
            case 5:
                holder.radioGroup.check(R.id.radioButton10);
                break;
        }
    }

    // Return the number of items of the list
    @Override
    public int getItemCount() {
        return gradesActivityList.size();
    }

    public class GradesViewHolder extends RecyclerView.ViewHolder implements RadioGroup.OnCheckedChangeListener {

        private TextView textView;
        private RadioGroup radioGroup;
        private RadioButton radioButton;

        public GradesViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {

        }
    }
}
