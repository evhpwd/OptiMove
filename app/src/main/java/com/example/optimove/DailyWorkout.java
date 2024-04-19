package com.example.optimove;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DailyWorkout extends Fragment {
    CalendarView calendarView;
    TextView myDate;
    TextView dailyExercise;
    CheckBox markComplete;
    LinearLayout layout;
    ArrayList<String> exercises = new ArrayList<String>() {
        {
            add("Your goal for today is to do 30 squats! Squats increase lower body and core strength, as well as flexibility in your lower back and hips. It's time to work your quadriceps, hamstrings, and gluteals.");
            add("Today's goal is to do 20 push-ups! Push-ups build upper body strength, reduce the risk of cardiac events and improve body composition. Not just a chest exercise, this will also test your triceps, anterior deltoids, and core muscles - as well as major and minor pecs.");
            add("The aim for today is to do 30 lunges! Lower body strength and balance can be improved with lunges. All your major lower body muscles will be put to use - quadriceps, hamstrings, and gluteals.");
            add("The aim for today is to do 20 tricep dips! Tricep dips can be done easily from the floor or with a prop and will build tricep, chest and shoulder strength. More than just your triceps are trained, including anterior deltoids, rhomboids, lats, plus major and minor pecs.");
        }
    };
    ArrayList<String> daysCompleted = new ArrayList<String>();
    String selected_day;
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_daily_workout, container, false);
        layout = view.findViewById(R.id.calendar_layout);

        calendarView = layout.findViewById(R.id.calendar_view);
        myDate = view.findViewById(R.id.my_date);
        dailyExercise = view.findViewById(R.id.exercise_description);
        markComplete = view.findViewById(R.id.mark_complete);

        Calendar calendar = Calendar.getInstance();
        Date c = calendar.getTime();
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        dailyExercise.setText(exercises.get(dayOfMonth % 4));
        myDate.setText(df.format(c));

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String date = dayOfMonth + "/" + (month + 1) + "/" + year;
                Date dateObj = null;
                try {
                    dateObj = df.parse(date);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                dailyExercise.setText(exercises.get(dayOfMonth % 4));
                myDate.setText(df.format(dateObj));

                selected_day = dayOfMonth + "/" + (month + 1);
                markComplete.setChecked(daysCompleted.contains(selected_day));
            }
        });

        markComplete.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (markComplete.isChecked()) {
                if (!daysCompleted.contains(selected_day)) {
                    daysCompleted.add(selected_day);
                }
            } else {
                if (daysCompleted.contains(selected_day)) {
                    daysCompleted.remove(daysCompleted.indexOf(selected_day));
                }
            }
        });

        return view;
    }
}