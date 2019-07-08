package com.facebook.ads.internal.e;

import android.os.Bundle;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import android.util.Log;
import com.facebook.ads.internal.c.a.c;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

@UiThread
public class a implements c {
    private static final String a = "a";
    private static a b;
    private final LinkedHashMap<String, C0004a> c = new LinkedHashMap<>();

    /* renamed from: com.facebook.ads.internal.e.a$a reason: collision with other inner class name */
    public static class C0004a {
        public final String a;
        public final Messenger b;
        @Nullable
        public com.facebook.ads.internal.c.c c;

        C0004a(String str, Messenger messenger) {
            this.a = str;
            this.b = messenger;
        }
    }

    private a() {
    }

    public static a a() {
        if (b == null) {
            b = new a();
        }
        return b;
    }

    private static void f(String str) {
        Log.d(a, str);
    }

    @Nullable
    public com.facebook.ads.internal.c.c a(String str) {
        C0004a aVar = (C0004a) this.c.get(str);
        if (aVar != null) {
            return aVar.c;
        }
        return null;
    }

    public void a(int i, String str) {
        a(i, str, null);
    }

    public void a(int i, String str, @Nullable Bundle bundle) {
        C0004a e = e(str);
        if (e != null) {
            try {
                Message obtain = Message.obtain(null, i);
                obtain.getData().putString("STR_AD_ID_KEY", str);
                if (bundle != null) {
                    obtain.getData().putBundle("BUNDLE_EXTRAS_KEY", bundle);
                }
                e.b.send(obtain);
            } catch (RemoteException unused) {
                b(str);
            }
        }
        for (Entry value : this.c.entrySet()) {
            C0004a aVar = (C0004a) value.getValue();
            try {
                aVar.b.send(Message.obtain(null, 3));
            } catch (RemoteException unused2) {
                b(aVar.a);
            }
        }
    }

    public void a(String str, Messenger messenger) {
        this.c.put(str, new C0004a(str, messenger));
    }

    public void b() {
        Iterator it = this.c.entrySet().iterator();
        while (it.hasNext()) {
            com.facebook.ads.internal.c.c cVar = ((C0004a) ((Entry) it.next()).getValue()).c;
            if (cVar != null) {
                cVar.a();
            }
            it.remove();
        }
    }

    public void b(String str) {
        C0004a aVar = (C0004a) this.c.get(str);
        if (aVar != null && aVar.c != null) {
            StringBuilder sb = new StringBuilder();
            sb.append("Destroyed Ad ");
            sb.append(str);
            f(sb.toString());
            aVar.c.a();
            this.c.remove(str);
        }
    }

    public void c(String str) {
        if (((C0004a) this.c.get(str)) != null) {
            StringBuilder sb = new StringBuilder();
            sb.append("Removed Ad ");
            sb.append(str);
            f(sb.toString());
            this.c.remove(str);
        }
    }

    public void d(String str) {
        this.c.remove(str);
    }

    @Nullable
    public C0004a e(String str) {
        return (C0004a) this.c.get(str);
    }
}
