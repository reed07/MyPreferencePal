package com.inmobi.commons.core.d;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.inmobi.commons.a.a;
import java.util.HashMap;

/* compiled from: KeyValueStore */
public final class c {
    private static HashMap<String, c> b = new HashMap<>();
    private static final Object c = new Object();
    public SharedPreferences a;

    private c(Context context, String str) {
        this.a = context.getSharedPreferences(str, 0);
    }

    public static String a(String str) {
        StringBuilder sb = new StringBuilder("com.im.keyValueStore.");
        sb.append(str);
        return sb.toString();
    }

    public static c a(Context context, String str) {
        String a2 = a(str);
        c cVar = (c) b.get(a2);
        if (cVar != null) {
            return cVar;
        }
        synchronized (c) {
            c cVar2 = (c) b.get(a2);
            if (cVar2 != null) {
                return cVar2;
            }
            c cVar3 = new c(context, a2);
            b.put(a2, cVar3);
            return cVar3;
        }
    }

    public static c b(String str) {
        return a(a.b(), str);
    }

    public final void a(String str, String str2) {
        Editor edit = this.a.edit();
        edit.putString(str, str2);
        edit.apply();
    }

    public final String c(String str) {
        return this.a.getString(str, null);
    }

    public final void a(String str, int i) {
        Editor edit = this.a.edit();
        edit.putInt(str, i);
        edit.apply();
    }

    public final int d(String str) {
        return this.a.getInt(str, Integer.MIN_VALUE);
    }

    public final void a(String str, long j) {
        Editor edit = this.a.edit();
        edit.putLong(str, j);
        edit.apply();
    }

    public final long b(String str, long j) {
        return this.a.getLong(str, j);
    }

    public final void a(String str, boolean z) {
        Editor edit = this.a.edit();
        edit.putBoolean(str, z);
        edit.apply();
    }

    public final boolean b(String str, boolean z) {
        return this.a.getBoolean(str, z);
    }
}
