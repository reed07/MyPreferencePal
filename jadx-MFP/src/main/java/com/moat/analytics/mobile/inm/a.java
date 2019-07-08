package com.moat.analytics.mobile.inm;

import android.app.Activity;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.os.Bundle;
import java.lang.ref.WeakReference;

class a {
    static WeakReference<Activity> a = null;
    private static boolean b = false;
    private static Application c = null;
    /* access modifiers changed from: private */
    public static int d = 0;
    /* access modifiers changed from: private */
    public static boolean e = false;

    /* renamed from: com.moat.analytics.mobile.inm.a$a reason: collision with other inner class name */
    private static class C0050a implements ActivityLifecycleCallbacks {
        C0050a() {
        }

        private static void a(boolean z) {
            if (z) {
                p.a(3, "ActivityState", (Object) null, "App became visible");
                if (w.a().a == d.ON && !((k) MoatAnalytics.getInstance()).c) {
                    o.a().c();
                }
            } else {
                p.a(3, "ActivityState", (Object) null, "App became invisible");
                if (w.a().a == d.ON && !((k) MoatAnalytics.getInstance()).c) {
                    o.a().d();
                }
            }
        }

        public void onActivityCreated(Activity activity, Bundle bundle) {
            a.d = 1;
        }

        public void onActivityDestroyed(Activity activity) {
            try {
                if (!(a.d == 3 || a.d == 5)) {
                    if (a.e) {
                        a(false);
                    }
                    a.e = false;
                }
                a.d = 6;
                StringBuilder sb = new StringBuilder("Activity destroyed: ");
                sb.append(activity.getClass());
                sb.append("@");
                sb.append(activity.hashCode());
                p.a(3, "ActivityState", (Object) this, sb.toString());
                if (a.b(activity)) {
                    a.a = new WeakReference<>(null);
                }
            } catch (Exception e) {
                m.a(e);
            }
        }

        public void onActivityPaused(Activity activity) {
            try {
                a.d = 4;
                if (a.b(activity)) {
                    a.a = new WeakReference<>(null);
                }
                StringBuilder sb = new StringBuilder("Activity paused: ");
                sb.append(activity.getClass());
                sb.append("@");
                sb.append(activity.hashCode());
                p.a(3, "ActivityState", (Object) this, sb.toString());
            } catch (Exception e) {
                m.a(e);
            }
        }

        public void onActivityResumed(Activity activity) {
            try {
                a.a = new WeakReference<>(activity);
                a.d = 3;
                w.a().b();
                StringBuilder sb = new StringBuilder("Activity resumed: ");
                sb.append(activity.getClass());
                sb.append("@");
                sb.append(activity.hashCode());
                p.a(3, "ActivityState", (Object) this, sb.toString());
                if (((k) MoatAnalytics.getInstance()).b) {
                    f.a(activity);
                }
            } catch (Exception e) {
                m.a(e);
            }
        }

        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        public void onActivityStarted(Activity activity) {
            try {
                a.a = new WeakReference<>(activity);
                a.d = 2;
                if (!a.e) {
                    a(true);
                }
                a.e = true;
                StringBuilder sb = new StringBuilder("Activity started: ");
                sb.append(activity.getClass());
                sb.append("@");
                sb.append(activity.hashCode());
                p.a(3, "ActivityState", (Object) this, sb.toString());
            } catch (Exception e) {
                m.a(e);
            }
        }

        public void onActivityStopped(Activity activity) {
            try {
                if (a.d != 3) {
                    a.e = false;
                    a(false);
                }
                a.d = 5;
                if (a.b(activity)) {
                    a.a = new WeakReference<>(null);
                }
                StringBuilder sb = new StringBuilder("Activity stopped: ");
                sb.append(activity.getClass());
                sb.append("@");
                sb.append(activity.hashCode());
                p.a(3, "ActivityState", (Object) this, sb.toString());
            } catch (Exception e) {
                m.a(e);
            }
        }
    }

    a() {
    }

    static Application a() {
        return c;
    }

    static void a(Application application) {
        c = application;
        if (!b) {
            b = true;
            c.registerActivityLifecycleCallbacks(new C0050a());
        }
    }

    /* access modifiers changed from: private */
    public static boolean b(Activity activity) {
        WeakReference<Activity> weakReference = a;
        return weakReference != null && weakReference.get() == activity;
    }
}
