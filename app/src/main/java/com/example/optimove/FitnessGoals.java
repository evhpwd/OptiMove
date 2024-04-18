package com.example.optimove;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class FitnessGoals extends Fragment {

    LinearLayout layout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fitness_goals, container, false);
        layout = view.findViewById(R.id.container);
        AlertDialog dialog = buildDialog();

        view.findViewById(R.id.add_fitness_goal).setOnClickListener(v -> dialog.show());
        return view;
    }

    private AlertDialog buildDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        View dialogView = getLayoutInflater().inflate(R.layout.add_goal_dialog, null);

        final EditText name= dialogView.findViewById(R.id.nameEdit);

        builder.setView(dialogView);
        builder.setTitle("Enter your Goal")
                .setPositiveButton("SAVE", (dialog, which) -> addCard(name.getText().toString()))
                .setNegativeButton("Cancel", null);

        return builder.create();
    }

    private void addCard(String name) {
        @SuppressLint("InflateParams")
        View view = getLayoutInflater().inflate(R.layout.goal_card, null);
        TextView nameView = view.findViewById(R.id.name);
        Button delete = view.findViewById(R.id.delete);
        nameView.setText(name);
        delete.setOnClickListener(v -> layout.removeView(view));
        layout.addView(view);
    }
}