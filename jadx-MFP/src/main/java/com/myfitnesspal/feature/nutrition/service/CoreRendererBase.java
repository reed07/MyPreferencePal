package com.myfitnesspal.feature.nutrition.service;

import android.app.Activity;
import android.content.Context;
import com.myfitnesspal.shared.util.RequiresActivityContext;

public abstract class CoreRendererBase implements RequiresActivityContext {
    /* access modifiers changed from: protected */
    public Activity activity;
    protected final Context context;

    protected CoreRendererBase(Context context2) {
        this.context = context2;
    }

    public void setActivityContext(Activity activity2) {
        this.activity = activity2;
    }
}
