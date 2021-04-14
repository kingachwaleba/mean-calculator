package com.example.meancalculator;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

    @NonNull
    @Override
    public GradesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull GradesViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class GradesViewHolder extends RecyclerView.ViewHolder {
        public GradesViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
