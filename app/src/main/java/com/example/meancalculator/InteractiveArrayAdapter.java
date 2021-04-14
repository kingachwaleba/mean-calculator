package com.example.meancalculator;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class InteractiveArrayAdapter extends RecyclerView.Adapter<InteractiveArrayAdapter.GradesViewHolder> {

    private List<GradesActivity> gradesActivityList;
    private LayoutInflater layoutInflater;

    public InteractiveArrayAdapter(List<GradesActivity> gradesActivityList, LayoutInflater layoutInflater) {
        this.gradesActivityList = gradesActivityList;
        this.layoutInflater = layoutInflater;
    }

    // Create the main layout element and create a holder for the given row
    @NonNull
    @Override
    public GradesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View line = layoutInflater.inflate(R.layout.list_line, null);
        return new GradesViewHolder(line);
    }

    // Populate a row, stored in a holder, with data for the specified row
    @Override
    public void onBindViewHolder(@NonNull GradesViewHolder holder, int position) {
        GradesActivity grade = gradesActivityList.get(position);
    }

    // Return the number of items of the list
    @Override
    public int getItemCount() {
        return gradesActivityList.size();
    }

    public class GradesViewHolder extends RecyclerView.ViewHolder implements RadioGroup.OnCheckedChangeListener {

        private EditText editText;
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
