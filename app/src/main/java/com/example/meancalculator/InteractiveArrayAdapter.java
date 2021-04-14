package com.example.meancalculator;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class InteractiveArrayAdapter extends RecyclerView.Adapter<InteractiveArrayAdapter.GradesViewHolder> {
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
