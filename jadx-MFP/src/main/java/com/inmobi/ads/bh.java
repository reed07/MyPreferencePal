package com.inmobi.ads;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.Context;
import android.os.Bundle;
import com.inmobi.commons.a.a;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Picasso.Builder;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

@TargetApi(14)
/* compiled from: PicassoWrapper */
public class bh {
    /* access modifiers changed from: private */
    public static final String a = "bh";
    /* access modifiers changed from: private */
    @SuppressLint({"StaticFieldLeak"})
    public static volatile Picasso b;
    /* access modifiers changed from: private */
    public static final Object c = new Object();
    /* access modifiers changed from: private */
    public static List<WeakReference<Context>> d = new ArrayList();
    /* access modifiers changed from: private */
    public static ActivityLifecycleCallbacks e = new ActivityLifecycleCallbacks() {
        public final void onActivityCreated(Activity activity, Bundle bundle) {
        }

        public final void onActivityPaused(Activity activity) {
        }

        public final void onActivityResumed(Activity activity) {
        }

        public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        public final void onActivityStarted(Activity activity) {
        }

        public final void onActivityStopped(Activity activity) {
        }

        public final void onActivityDestroyed(Activity activity) {
            synchronized (bh.c) {
                if (bh.b != null && bh.c(activity)) {
                    activity.getApplication().unregisterActivityLifecycleCallbacks(bh.e);
                    bh.d.remove(activity);
                    if (bh.d.isEmpty()) {
                        bh.a;
                        StringBuilder sb = new StringBuilder("Picasso instance ");
                        sb.append(bh.b.toString());
                        sb.append(" shutdown");
                        bh.b.shutdown();
                        bh.b = null;
                    }
                }
            }
        }
    };

    public static Picasso a(Context context) {
        synchronized (c) {
            if (!c(context)) {
                d.add(new WeakReference(context));
            }
            if (b == null) {
                b = new Builder(context).build();
                a.a(context, e);
            }
        }
        return b;
    }

    /* access modifiers changed from: private */
    public static boolean c(Context context) {
        for (int i = 0; i < d.size(); i++) {
            Context context2 = (Context) ((WeakReference) d.get(i)).get();
            if (context2 != null && context2.equals(context)) {
                return true;
            }
        }
        return false;
    }
}
