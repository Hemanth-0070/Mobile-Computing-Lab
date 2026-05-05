package com.hemanth.limitx;

import android.accessibilityservice.AccessibilityService;
import android.content.SharedPreferences;
import android.view.accessibility.AccessibilityEvent;
import android.widget.Toast;

public class AppAccessibilityService extends AccessibilityService {

    private String currentApp = "";
    private long startTime = 0;
    private boolean isBlocked = false;

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {

        if (event.getEventType() != AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED)
            return;

        String packageName = String.valueOf(event.getPackageName());

        // Ignore system UI (important)
        if (packageName.equals("com.android.systemui")) return;

        SharedPreferences prefs = getSharedPreferences("LimitX", MODE_PRIVATE);
        int limit = prefs.getInt(packageName, -1);

        // No limit → reset everything
        if (limit == -1) {
            isBlocked = false;
            return;
        }

        // App changed → reset timer + unblock
        if (!packageName.equals(currentApp)) {
            currentApp = packageName;
            startTime = System.currentTimeMillis();
            isBlocked = false;
            return;
        }

        long usedSeconds = (System.currentTimeMillis() - startTime) / 1000;

        // Block only once (important fix)
        if (usedSeconds >= (limit * 60L) && !isBlocked) {

            isBlocked = true;

            Toast.makeText(this, "LIMIT REACHED!", Toast.LENGTH_SHORT).show();

            // Go home once
            performGlobalAction(GLOBAL_ACTION_HOME);
        }
    }

    @Override
    public void onInterrupt() {
    }
}