package com.example.optimove;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNav;
    CalorieCount calorieCount = new CalorieCount();
    FitnessGoals fitnessGoals = new FitnessGoals();
    DailyWorkout dailyWorkout = new DailyWorkout();
    Steps steps = new Steps();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNav = findViewById(R.id.bottom_navigation);

        getSupportFragmentManager().beginTransaction().replace(R.id.container, dailyWorkout).commit();

        bottomNav.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.calorie_count) {
                getSupportFragmentManager().beginTransaction().replace(R.id.container, calorieCount).commit();
                return true;
            } else if (itemId == R.id.fitness_goals) {
                getSupportFragmentManager().beginTransaction().replace(R.id.container, fitnessGoals).commit();
                return true;
            } else if (itemId == R.id.daily_workout) {
                getSupportFragmentManager().beginTransaction().replace(R.id.container, dailyWorkout).commit();
                return true;
            } else if (itemId == R.id.steps) {
                getSupportFragmentManager().beginTransaction().replace(R.id.container, steps).commit();
                return true;
            }
            return false;
        });
    }
}