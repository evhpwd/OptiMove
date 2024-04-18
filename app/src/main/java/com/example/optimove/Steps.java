package com.example.optimove;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class Steps extends Fragment implements SensorEventListener {
    private SensorManager sensorManager;
    private Sensor stepSensor;
    private TextView stepsText;
    private ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Activity activity = requireActivity();
        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.ACTIVITY_RECOGNITION) == PackageManager.PERMISSION_DENIED) {
            activity.requestPermissions(new String[]{ Manifest.permission.ACTIVITY_RECOGNITION}, 1);
        }
        View view = inflater.inflate(R.layout.fragment_steps, container, false);
        progressBar = view.findViewById(R.id.progress_bar);
        stepsText = view.findViewById(R.id.steps);
        sensorManager = (SensorManager)activity.getSystemService(Context.SENSOR_SERVICE);
        java.util.List<Sensor> x = sensorManager.getSensorList(Sensor.TYPE_STEP_COUNTER);
        stepSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (stepSensor == null) {
            Toast.makeText(getContext(), "No sensor in this device", Toast.LENGTH_SHORT).show();
        } else {
            sensorManager.registerListener(this, stepSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_STEP_COUNTER) {
            int totalSteps = (int) event.values[0];
            stepsText.setText(String.valueOf(totalSteps));
            progressBar.setProgress(totalSteps);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {}
}