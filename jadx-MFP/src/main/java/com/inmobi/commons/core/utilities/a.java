package com.inmobi.commons.core.utilities;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* compiled from: ApplicationFocusChangeObserver */
public class a {
    /* access modifiers changed from: private */
    public static final String a = "a";
    /* access modifiers changed from: private */
    public static List<b> b = new ArrayList();
    private static Object c;
    /* access modifiers changed from: private */
    public static boolean d = false;
    /* access modifiers changed from: private */
    public static HandlerThread e = null;
    private static final Object f = new Object();
    private static volatile a g;

    /* renamed from: com.inmobi.commons.core.utilities.a$a reason: collision with other inner class name */
    /* compiled from: ApplicationFocusChangeObserver */
    static class C0048a extends Handler {
        boolean a = true;

        C0048a(Looper looper) {
            super(looper);
        }

        public final void handleMessage(Message message) {
            if (!a.d) {
                if (message.what != 1001 || !this.a) {
                    if (message.what == 1002 && !this.a) {
                        this.a = true;
                        a.a(true);
                        a.a;
                    }
                    return;
                }
                this.a = false;
                a.a(false);
                a.a;
            }
        }
    }

    /* compiled from: ApplicationFocusChangeObserver */
    public interface b {
        void a(boolean z);
    }

    public static a a() {
        a aVar = g;
        if (aVar == null) {
            synchronized (f) {
                aVar = g;
                if (aVar == null) {
                    aVar = new a();
                    g = aVar;
                }
            }
        }
        return aVar;
    }

    private a() {
    }

    public final void a(b bVar) {
        Class[] declaredClasses;
        b.add(bVar);
        if (b.size() == 1 && com.inmobi.commons.a.a.a()) {
            HandlerThread handlerThread = new HandlerThread("ApplicationFocusChangeObserverHandler");
            e = handlerThread;
            handlerThread.start();
            Class cls = null;
            for (Class cls2 : Application.class.getDeclaredClasses()) {
                if (cls2.getSimpleName().equalsIgnoreCase("ActivityLifecycleCallbacks")) {
                    new Class[1][0] = cls2;
                    cls = cls2;
                }
            }
            c = Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, new InvocationHandler() {
                private final Handler b = new C0048a(a.e.getLooper());
                private WeakReference<Activity> c;

                public final Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
                    if (objArr != null) {
                        String name = method.getName();
                        char c2 = 65535;
                        int hashCode = name.hashCode();
                        if (hashCode != 195654633) {
                            if (hashCode != 1495889555) {
                                if (hashCode == 1508755423 && name.equals("onActivityStopped")) {
                                    c2 = 2;
                                }
                            } else if (name.equals("onActivityStarted")) {
                                c2 = 0;
                            }
                        } else if (name.equals("onActivityResumed")) {
                            c2 = 1;
                        }
                        switch (c2) {
                            case 0:
                            case 1:
                                Activity activity = objArr[0];
                                WeakReference<Activity> weakReference = this.c;
                                if (weakReference == null || weakReference.get() != activity) {
                                    this.c = new WeakReference<>(activity);
                                }
                                this.b.removeMessages(1001);
                                this.b.sendEmptyMessage(1002);
                                break;
                            case 2:
                                Activity activity2 = objArr[0];
                                WeakReference<Activity> weakReference2 = this.c;
                                if (weakReference2 != null && weakReference2.get() == activity2) {
                                    this.b.sendEmptyMessageDelayed(1001, 3000);
                                    break;
                                }
                        }
                    }
                    return null;
                }
            });
            Application application = (Application) com.inmobi.commons.a.a.b();
            if (c != null) {
                try {
                    Application.class.getMethod("registerActivityLifecycleCallbacks", new Class[]{cls}).invoke(application, new Object[]{c});
                } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException unused) {
                } catch (Exception e2) {
                    try {
                        HashMap hashMap = new HashMap();
                        hashMap.put("type", "GenericException");
                        StringBuilder sb = new StringBuilder();
                        sb.append(e2.getMessage());
                        hashMap.put("message", sb.toString());
                        com.inmobi.commons.core.e.b.a();
                        com.inmobi.commons.core.e.b.a("root", "ExceptionCaught", hashMap);
                    } catch (Exception unused2) {
                        StringBuilder sb2 = new StringBuilder("Error in submitting telemetry event : (");
                        sb2.append(e2.getMessage());
                        sb2.append(")");
                    }
                }
            }
        }
    }

    public static void b() {
        d = true;
    }

    public static void c() {
        d = false;
    }

    static /* synthetic */ void a(final boolean z) {
        Context b2 = com.inmobi.commons.a.a.b();
        if (b2 != null) {
            new Handler(b2.getMainLooper()).post(new Runnable() {
                public final void run() {
                    for (b a2 : a.b) {
                        try {
                            a2.a(z);
                        } catch (Exception e) {
                            a.a;
                            new StringBuilder("SDK encountered an unexpected error in handling focus change event; ").append(e.getMessage());
                        }
                    }
                }
            });
        }
    }
}
