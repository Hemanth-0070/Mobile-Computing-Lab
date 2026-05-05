package com.hemanth.limitx;

import android.app.AlertDialog;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

import java.util.*;

public class UsageActivity extends AppCompatActivity {

    ListView listView;
    Button btnReset;

    List<String> appList = new ArrayList<>();
    List<String> packageList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usage);

        listView = findViewById(R.id.listView);
        btnReset = findViewById(R.id.btnReset);

        loadApps();

        btnReset.setOnClickListener(v -> {
            getSharedPreferences("LimitX", MODE_PRIVATE).edit().clear().apply();
            Toast.makeText(this, "All limits reset!", Toast.LENGTH_SHORT).show();
        });
    }

    private void loadApps() {

        UsageStatsManager usm = (UsageStatsManager) getSystemService(USAGE_STATS_SERVICE);

        long end = System.currentTimeMillis();
        long start = end - (1000 * 60 * 60 * 24);

        List<UsageStats> stats = usm.queryUsageStats(
                UsageStatsManager.INTERVAL_DAILY, start, end);

        if (stats == null) return;

        for (UsageStats u : stats) {

            long time = u.getTotalTimeInForeground() / 1000;

            if (time > 60) {

                String name;
                try {
                    name = getPackageManager()
                            .getApplicationLabel(
                                    getPackageManager().getApplicationInfo(u.getPackageName(), 0)
                            ).toString();
                } catch (Exception e) {
                    name = u.getPackageName();
                }

                appList.add(name);
                packageList.add(u.getPackageName());
            }
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                R.layout.item_app,
                R.id.appName,
                appList
        );

        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, position, id) ->
                showLimitDialog(appList.get(position), packageList.get(position))
        );
    }

    private void showLimitDialog(String name, String pkg) {

        EditText input = new EditText(this);
        input.setHint("Enter minutes");

        new AlertDialog.Builder(this)
                .setTitle("Set Limit")
                .setMessage(name)
                .setView(input)
                .setPositiveButton("Save", (d, w) -> {

                    String val = input.getText().toString().trim();

                    if (!val.isEmpty()) {
                        int limit = Integer.parseInt(val);

                        SharedPreferences prefs = getSharedPreferences("LimitX", MODE_PRIVATE);
                        prefs.edit().putInt(pkg, limit).apply();

                        Toast.makeText(this, "Limit Set!", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
    }
}