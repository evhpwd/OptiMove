package com.example.optimove;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CalorieCount extends Fragment {

    private int totalCalories = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calorie_count, container, false);
        ProgressBar progressBar = view.findViewById(R.id.progress_bar);
        TextView progressText = view.findViewById(R.id.progress_text);

        EditText foodName = view.findViewById(R.id.food_name_input);
        EditText foodCalories = view.findViewById(R.id.calorie_count_input);

        ArrayList<FoodItem> foodItems = new ArrayList<>();
        FoodAdapter adapter = new FoodAdapter(foodItems);
        RecyclerView foodItemsView = view.findViewById(R.id.food_items);
        foodItemsView.setAdapter(adapter);
        foodItemsView.setLayoutManager(new LinearLayoutManager(getActivity()));

        view.findViewById(R.id.start_progess).setOnClickListener(v -> {
            String name = foodName.getText().toString();
            String calorieInput = foodCalories.getText().toString();
            if (name.isEmpty() || calorieInput.isEmpty()) {
                Toast.makeText(getContext(), "Please enter both name and calories.", Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                int calories = Integer.parseInt(calorieInput);
                FoodItem item = new FoodItem(name, calories);
                foodItems.add(item);
                adapter.notifyItemInserted(foodItems.size() - 1);
                totalCalories += calories;
                progressBar.setProgress(totalCalories);
                progressText.setText(totalCalories + "/2500");
            } catch (NumberFormatException e) {
                Toast.makeText(getContext(), "Please enter a valid number for calories.", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}