package com.crashlytics.android.core;

import android.content.Context;
import android.os.Bundle;
import com.google.android.gms.measurement.AppMeasurement;
import io.fabric.sdk.android.Fabric;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import org.json.JSONException;
import org.json.JSONObject;

class DefaultAppMeasurementEventListenerRegistrar implements AppMeasurementEventListenerRegistrar {
    /* access modifiers changed from: private */
    public final CrashlyticsCore crashlyticsCore;

    static AppMeasurementEventListenerRegistrar instanceFrom(CrashlyticsCore crashlyticsCore2) {
        return new DefaultAppMeasurementEventListenerRegistrar(crashlyticsCore2);
    }

    private DefaultAppMeasurementEventListenerRegistrar(CrashlyticsCore crashlyticsCore2) {
        this.crashlyticsCore = crashlyticsCore2;
    }

    public boolean register() {
        Class cls = getClass("com.google.android.gms.measurement.AppMeasurement");
        if (cls == null) {
            Fabric.getLogger().w("CrashlyticsCore", "Firebase Analytics is not present; you will not see automatic logging of events before a crash occurs.");
            return false;
        }
        Object instance = getInstance(cls);
        if (instance != null) {
            return invoke(cls, instance, "registerOnMeasurementEventListener");
        }
        Fabric.getLogger().w("CrashlyticsCore", "Could not create an instance of Firebase Analytics.");
        return false;
    }

    private Class<?> getClass(String str) {
        try {
            return this.crashlyticsCore.getContext().getClassLoader().loadClass(str);
        } catch (Exception unused) {
            return null;
        }
    }

    private Object getInstance(Class<?> cls) {
        try {
            return cls.getDeclaredMethod("getInstance", new Class[]{Context.class}).invoke(cls, new Object[]{this.crashlyticsCore.getContext()});
        } catch (Exception unused) {
            return null;
        }
    }

    private boolean invoke(Class<?> cls, Object obj, String str) {
        Class cls2 = getClass("com.google.android.gms.measurement.AppMeasurement$OnEventListener");
        try {
            cls.getDeclaredMethod(str, new Class[]{cls2}).invoke(obj, new Object[]{onEventListenerProxy(cls2)});
            return true;
        } catch (NoSuchMethodException e) {
            StringBuilder sb = new StringBuilder();
            sb.append("Expected method missing: ");
            sb.append(str);
            Fabric.getLogger().w("CrashlyticsCore", sb.toString(), e);
            return false;
        } catch (InvocationTargetException e2) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Cannot invoke method: ");
            sb2.append(str);
            Fabric.getLogger().w("CrashlyticsCore", sb2.toString(), e2);
            return false;
        } catch (IllegalAccessException e3) {
            StringBuilder sb3 = new StringBuilder();
            sb3.append("Cannot access method: ");
            sb3.append(str);
            Fabric.getLogger().w("CrashlyticsCore", sb3.toString(), e3);
            return false;
        }
    }

    private Object onEventListenerProxy(Class cls) {
        return Proxy.newProxyInstance(this.crashlyticsCore.getContext().getClassLoader(), new Class[]{cls}, new InvocationHandler() {
            public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
                if (objArr.length == 4) {
                    String str = objArr[0];
                    String str2 = objArr[1];
                    Bundle bundle = objArr[2];
                    if (str != null && !str.equals(AppMeasurement.CRASH_ORIGIN)) {
                        DefaultAppMeasurementEventListenerRegistrar.writeEventToUserLog(DefaultAppMeasurementEventListenerRegistrar.this.crashlyticsCore, str2, bundle);
                    }
                    return null;
                }
                throw new RuntimeException("Unexpected AppMeasurement.OnEventListener signature");
            }
        });
    }

    /* access modifiers changed from: private */
    public static void writeEventToUserLog(CrashlyticsCore crashlyticsCore2, String str, Bundle bundle) {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("$A$:");
            sb.append(serializeEvent(str, bundle));
            crashlyticsCore2.log(sb.toString());
        } catch (JSONException unused) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Unable to serialize Firebase Analytics event; ");
            sb2.append(str);
            Fabric.getLogger().w("CrashlyticsCore", sb2.toString());
        }
    }

    private static String serializeEvent(String str, Bundle bundle) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        for (String str2 : bundle.keySet()) {
            jSONObject2.put(str2, bundle.get(str2));
        }
        jSONObject.put("name", str);
        jSONObject.put("parameters", jSONObject2);
        return jSONObject.toString();
    }
}
