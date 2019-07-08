package com.myfitnesspal.feature.debug.util;

import android.widget.TextView;

public interface AdvancedDebuggingUtil {
    String getDisplayableVersionString();

    void setUpVersionHandlerOn(TextView textView);

    boolean shouldShowAdvancedDebuggingMenu();
}
