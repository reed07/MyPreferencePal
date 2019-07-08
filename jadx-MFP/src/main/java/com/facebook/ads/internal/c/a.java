package com.facebook.ads.internal.c;

import android.content.Context;
import android.support.annotation.UiThread;
import android.util.Log;
import com.facebook.ads.AdSettings.IntegrationErrorMode;
import com.facebook.ads.AudienceNetworkAds;
import com.facebook.ads.internal.b.e;
import com.facebook.ads.internal.protocol.AdErrorType;
import com.facebook.ads.internal.w.h.b;
import java.util.HashMap;
import java.util.Locale;

@UiThread
public final class a {
    private static final HashMap<C0003a, C0003a> d = new HashMap<>();
    C0003a a = C0003a.CREATED;
    private final b b;
    private final Context c;

    /* renamed from: com.facebook.ads.internal.c.a$a reason: collision with other inner class name */
    public enum C0003a {
        CREATED,
        LOADING,
        LOADED,
        SHOWING,
        SHOWN,
        DESTROYED,
        ERROR
    }

    static {
        d.put(C0003a.CREATED, C0003a.LOADING);
        d.put(C0003a.LOADING, C0003a.LOADED);
        d.put(C0003a.LOADED, C0003a.SHOWING);
        d.put(C0003a.SHOWING, C0003a.SHOWN);
        d.put(C0003a.SHOWN, C0003a.LOADING);
        d.put(C0003a.DESTROYED, C0003a.LOADING);
        d.put(C0003a.ERROR, C0003a.LOADING);
    }

    a(Context context, b bVar) {
        this.c = context;
        this.b = bVar;
    }

    public void a(C0003a aVar) {
        if (!com.facebook.ads.internal.r.a.ab(this.c)) {
            this.a = aVar;
        } else if (aVar.equals(C0003a.DESTROYED) || aVar.equals(C0003a.ERROR)) {
            this.a = aVar;
        } else {
            if (!aVar.equals(d.get(this.a))) {
                int i = b.k;
                StringBuilder sb = new StringBuilder();
                sb.append("Wrong internal transition form ");
                sb.append(this.a);
                sb.append(" to ");
                sb.append(aVar);
                com.facebook.ads.internal.w.h.a.b(this.c, "api", i, new Exception(sb.toString()));
            }
            this.a = aVar;
        }
    }

    public boolean a(C0003a aVar, String str) {
        if (aVar.equals(d.get(this.a))) {
            this.a = aVar;
            return false;
        } else if (!com.facebook.ads.internal.r.a.ab(this.c)) {
            return false;
        } else {
            IntegrationErrorMode a2 = e.a(this.c);
            String format = String.format(Locale.US, AdErrorType.INCORRECT_STATE_ERROR.getDefaultErrorMessage(), new Object[]{str, this.a});
            switch (a2) {
                case INTEGRATION_ERROR_CRASH_DEBUG_MODE:
                    StringBuilder sb = new StringBuilder();
                    sb.append(format);
                    sb.append(". You can change Integration Error mode by setting AdSettings.setIntegrationErrorMode()");
                    throw new IllegalStateException(sb.toString());
                case INTEGRATION_ERROR_CALLBACK_MODE:
                    this.b.d();
                    this.b.a(10, AdErrorType.INCORRECT_STATE_ERROR, format);
                    Log.e(AudienceNetworkAds.TAG, format);
                    com.facebook.ads.internal.w.h.a.b(this.c, "api", b.l, new Exception(format));
                    return true;
                default:
                    Log.e(AudienceNetworkAds.TAG, format);
                    return true;
            }
        }
    }
}
