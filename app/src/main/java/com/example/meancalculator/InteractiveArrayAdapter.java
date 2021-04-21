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
        switch (gradesActivityList.get(position).getValue()) {
            case 2:
                holder.radioGroup.check(R.id.twoButton);
                break;
            case 3:
                holder.radioGroup.check(R.id.threeButton);
                break;
            case 4:
                holder.radioGroup.check(R.id.fourButton);
                break;
            case 5:
                holder.radioGroup.check(R.id.fiveButton);
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

            // Read the reference to the row elements
            this.textView = itemView.findViewById(R.id.gradeName);
            this.radioGroup = itemView.findViewById(R.id.radioGroup);

            radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
                // Read an object from the radio button group
                GradeModel element = (GradeModel) group.getTag();
                // Save the changed grade
                radioButton = itemView.findViewById(group.getCheckedRadioButtonId());
                element.setValue(Integer.parseInt(radioButton.getText().toString()));
                gradesActivityList.set(element.getId(), element);
            });
        }

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {

        }
    }
}
