package com.facebook.ads.internal.w.a;

import android.app.Activity;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.Context;
import android.os.Bundle;
import com.facebook.ads.internal.w.h.a;
import java.lang.ref.WeakReference;

public class b implements ActivityLifecycleCallbacks {
    private static Context a;
    private static WeakReference<Activity> b = new WeakReference<>(null);

    /* JADX WARNING: Removed duplicated region for block: B:11:0x001a  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x004f A[DONT_GENERATE] */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0051 A[DONT_GENERATE] */
    @android.support.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized android.app.Activity a() {
        /*
            java.lang.Class<com.facebook.ads.internal.w.a.b> r0 = com.facebook.ads.internal.w.a.b.class
            monitor-enter(r0)
            java.lang.ref.WeakReference<android.app.Activity> r1 = b     // Catch:{ all -> 0x0053 }
            java.lang.Object r1 = r1.get()     // Catch:{ all -> 0x0053 }
            android.app.Activity r1 = (android.app.Activity) r1     // Catch:{ all -> 0x0053 }
            r2 = 0
            if (r1 == 0) goto L_0x0017
            int r3 = android.os.Build.VERSION.SDK_INT     // Catch:{ all -> 0x0053 }
            r4 = 28
            if (r3 >= r4) goto L_0x0015
            goto L_0x0017
        L_0x0015:
            r3 = 0
            goto L_0x0018
        L_0x0017:
            r3 = 1
        L_0x0018:
            if (r3 == 0) goto L_0x001e
            android.app.Activity r2 = com.facebook.ads.internal.w.a.a.a()     // Catch:{ all -> 0x0053 }
        L_0x001e:
            android.content.Context r4 = a     // Catch:{ all -> 0x0053 }
            if (r4 == 0) goto L_0x004d
            if (r3 == 0) goto L_0x004d
            if (r1 == r2) goto L_0x004d
            android.content.Context r3 = a     // Catch:{ all -> 0x0053 }
            java.lang.String r4 = "act_util"
            int r5 = com.facebook.ads.internal.w.h.b.Z     // Catch:{ all -> 0x0053 }
            java.lang.Exception r6 = new java.lang.Exception     // Catch:{ all -> 0x0053 }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ all -> 0x0053 }
            r7.<init>()     // Catch:{ all -> 0x0053 }
            java.lang.String r8 = "Activity discrepancies res: "
            r7.append(r8)     // Catch:{ all -> 0x0053 }
            r7.append(r1)     // Catch:{ all -> 0x0053 }
            java.lang.String r8 = ", ref: "
            r7.append(r8)     // Catch:{ all -> 0x0053 }
            r7.append(r2)     // Catch:{ all -> 0x0053 }
            java.lang.String r7 = r7.toString()     // Catch:{ all -> 0x0053 }
            r6.<init>(r7)     // Catch:{ all -> 0x0053 }
            com.facebook.ads.internal.w.h.a.b(r3, r4, r5, r6)     // Catch:{ all -> 0x0053 }
        L_0x004d:
            if (r1 == 0) goto L_0x0051
            monitor-exit(r0)
            return r1
        L_0x0051:
            monitor-exit(r0)
            return r2
        L_0x0053:
            r1 = move-exception
            monitor-exit(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.ads.internal.w.a.b.a():android.app.Activity");
    }

    public static synchronized void a(Context context) {
        synchronized (b.class) {
            a = context;
            if (a instanceof Application) {
                ((Application) a).registerActivityLifecycleCallbacks(new b());
            } else {
                a.b(a, "api", com.facebook.ads.internal.w.h.b.o, new Exception("AppContext is not Application."));
            }
        }
    }

    public void onActivityCreated(Activity activity, Bundle bundle) {
    }

    public void onActivityDestroyed(Activity activity) {
    }

    public void onActivityPaused(Activity activity) {
        b = new WeakReference<>(null);
    }

    public void onActivityResumed(Activity activity) {
        b = new WeakReference<>(activity);
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    public void onActivityStarted(Activity activity) {
    }

    public void onActivityStopped(Activity activity) {
    }
}
