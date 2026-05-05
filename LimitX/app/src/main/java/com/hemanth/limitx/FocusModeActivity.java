package com.hemanth.limitx;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class FocusModeActivity extends AppCompatActivity {

    Button startFocus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_focus);

        startFocus = findViewById(R.id.startFocus);

        startFocus.setOnClickListener(v -> {
            Toast.makeText(this, "Focus Mode Started!", Toast.LENGTH_SHORT).show();

            new CountDownTimer(60000, 1000) {
                public void onTick(long millisUntilFinished) {}

                public void onFinish() {
                    Toast.makeText(FocusModeActivity.this, "Focus Mode Ended!", Toast.LENGTH_LONG).show();
                }
            }.start();
        });
    }
}