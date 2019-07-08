package com.uacf.sync.engine;

import android.content.Context;

public class UacfScheduleContext {
    private Context context;

    public UacfScheduleContext(Context context2) {
        this.context = context2;
    }

    public Context getContext() {
        return this.context;
    }
}
