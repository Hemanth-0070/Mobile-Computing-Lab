package com.hemanth.limitx;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class BlockActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView tv = new TextView(this);
        tv.setText("🚫 Limit Reached\n\nClose the app and stay focused!");
        tv.setTextSize(24);
        tv.setPadding(50, 200, 50, 50);

        setContentView(tv);
    }

    @Override
    public void onBackPressed() {
        // Disable back button
    }
}