package com.integralads.avid.library.mopub.activity;

import android.app.Activity;
import android.os.Build.VERSION;
import android.support.annotation.VisibleForTesting;
import android.view.View;
import android.view.Window;
import com.integralads.avid.library.mopub.weakreference.AvidActivity;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AvidActivityStack {
    private static AvidActivityStack avidActivityStackInstance = new AvidActivityStack();
    private final ArrayList<AvidActivity> activities = new ArrayList<>();

    public static AvidActivityStack getInstance() {
        return avidActivityStackInstance;
    }

    public void addActivity(Activity activity) {
        if (find(activity) == null) {
            this.activities.add(new AvidActivity(activity));
        }
    }

    public List<View> getRootViews() {
        ArrayList arrayList = new ArrayList();
        Iterator it = this.activities.iterator();
        Object obj = null;
        while (it.hasNext()) {
            AvidActivity avidActivity = (AvidActivity) it.next();
            if (isFinished(avidActivity)) {
                it.remove();
            } else {
                View rootView = getRootView(avidActivity);
                if (rootView != null) {
                    obj = rootView;
                }
            }
        }
        if (obj != null) {
            arrayList.add(obj);
        }
        return arrayList;
    }

    public void cleanup() {
        this.activities.clear();
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public AvidActivity find(Activity activity) {
        Iterator it = this.activities.iterator();
        while (it.hasNext()) {
            AvidActivity avidActivity = (AvidActivity) it.next();
            if (avidActivity.contains(activity)) {
                return avidActivity;
            }
        }
        return null;
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public boolean isFinished(AvidActivity avidActivity) {
        Activity activity = (Activity) avidActivity.get();
        if (activity == null) {
            return true;
        }
        if (VERSION.SDK_INT >= 17) {
            return activity.isDestroyed();
        }
        return activity.isFinishing();
    }

    @VisibleForTesting
    private View getRootView(AvidActivity avidActivity) {
        Activity activity = (Activity) avidActivity.get();
        if (activity == null) {
            return null;
        }
        Window window = activity.getWindow();
        if (window == null || !activity.hasWindowFocus()) {
            return null;
        }
        View decorView = window.getDecorView();
        if (decorView == null || !decorView.isShown()) {
            decorView = null;
        }
        return decorView;
    }
}
