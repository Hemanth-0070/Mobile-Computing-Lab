package com.example.togglebutton;

import android.os.Bundle;
import android.widget.ToggleButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    ToggleButton toggleButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        toggleButton = findViewById(R.id.toggleButton);

        toggleButton.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                Toast.makeText(MainActivity.this, "Status: ON", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "Status: OFF", Toast.LENGTH_SHORT).show();
            }
        });
    }
}