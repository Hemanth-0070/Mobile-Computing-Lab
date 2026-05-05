package com.hemanth.limitx;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Focus
        findViewById(R.id.btnFocus).setOnClickListener(v ->
                startActivity(new Intent(this, FocusActivity.class)));

        // Usage
        findViewById(R.id.btnUsage).setOnClickListener(v ->
                startActivity(new Intent(this, UsageActivity.class)));

        // Stats
        findViewById(R.id.btnStats).setOnClickListener(v ->
                startActivity(new Intent(this, StatsActivity.class)));

        // Settings
        findViewById(R.id.btnSettings).setOnClickListener(v ->
                startActivity(new Intent(this, SettingsActivity.class)));

        // Help
        findViewById(R.id.btnHelp).setOnClickListener(v ->
                startActivity(new Intent(this, HelpActivity.class)));

        // About
        findViewById(R.id.btnAbout).setOnClickListener(v ->
                startActivity(new Intent(this, AboutActivity.class)));
    }
}