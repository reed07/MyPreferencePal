package com.myfitnesspal.shared.ui.activity.busymanager;

import android.content.Context;

public interface BusyManager extends SupportsBusyStates {
    Context getContext();

    BusyManager setContext(Context context);
}
