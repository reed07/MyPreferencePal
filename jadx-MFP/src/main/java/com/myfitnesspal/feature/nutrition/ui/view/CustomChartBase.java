package com.myfitnesspal.feature.nutrition.ui.view;

import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import com.myfitnesspal.app.MyFitnessPalApp;
import com.shinobicontrols.charts.ChartView;
import com.uacf.core.util.ViewUtils;

public abstract class CustomChartBase implements ActivityLifecycleCallbacks {
    protected ChartView chartView;
    private final ViewGroup container;
    private final Context context;
    private boolean destroyed;

    public void onActivityCreated(Activity activity, Bundle bundle) {
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    public void onActivityStarted(Activity activity) {
    }

    public void onActivityStopped(Activity activity) {
    }

    public CustomChartBase(Context context2, ViewGroup viewGroup) {
        this.context = context2;
        this.container = viewGroup;
    }

    public Context getContext() {
        return this.context;
    }

    public ViewGroup getContainer() {
        return this.container;
    }

    public Resources getResources() {
        return this.context.getResources();
    }

    public View findViewById(int i) {
        return this.container.findViewById(i);
    }

    /* access modifiers changed from: protected */
    public void init() {
        MyFitnessPalApp.getInstance().registerActivityLifecycleCallbacks(this);
    }

    public void onActivityResumed(Activity activity) {
        if (!this.destroyed && isActivityMine(activity)) {
            ChartView chartView2 = this.chartView;
            if (chartView2 != null) {
                chartView2.onResume();
            }
        }
    }

    public void onActivityPaused(Activity activity) {
        if (!this.destroyed && isActivityMine(activity)) {
            ChartView chartView2 = this.chartView;
            if (chartView2 != null) {
                chartView2.onPause();
            }
        }
    }

    public void onActivityDestroyed(Activity activity) {
        if (isActivityMine(activity)) {
            destroy();
        }
    }

    public final void destroy() {
        if (!this.destroyed) {
            ChartView chartView2 = this.chartView;
            if (chartView2 != null) {
                ViewUtils.removeViewFromParent(chartView2);
                this.chartView.onDestroy();
                this.chartView = null;
            }
            this.destroyed = true;
            MyFitnessPalApp.getInstance().unregisterActivityLifecycleCallbacks(this);
        }
    }

    private boolean isActivityMine(Activity activity) {
        return getContext() == activity;
    }

    /* access modifiers changed from: protected */
    public int getColor(int i) {
        return getResources().getColor(i);
    }
}
