package com.bumptech.glide.request.target;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.RemoteViews;
import com.bumptech.glide.request.transition.Transition;

public class AppWidgetTarget extends SimpleTarget<Bitmap> {
    private final ComponentName componentName;
    private final Context context;
    private final RemoteViews remoteViews;
    private final int viewId;
    private final int[] widgetIds;

    private void update() {
        AppWidgetManager instance = AppWidgetManager.getInstance(this.context);
        ComponentName componentName2 = this.componentName;
        if (componentName2 != null) {
            instance.updateAppWidget(componentName2, this.remoteViews);
        } else {
            instance.updateAppWidget(this.widgetIds, this.remoteViews);
        }
    }

    public void onResourceReady(@NonNull Bitmap bitmap, @Nullable Transition<? super Bitmap> transition) {
        this.remoteViews.setImageViewBitmap(this.viewId, bitmap);
        update();
    }
}
