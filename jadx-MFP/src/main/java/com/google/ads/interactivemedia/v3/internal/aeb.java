package com.google.ads.interactivemedia.v3.internal;

import android.content.Context;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.SystemClock;
import android.support.v4.util.ArrayMap;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import com.google.ads.interactivemedia.v3.api.AdError.AdErrorCode;
import com.google.ads.interactivemedia.v3.api.AdError.AdErrorType;
import com.google.ads.interactivemedia.v3.api.AdEvent.AdEventType;
import com.google.ads.interactivemedia.v3.api.AdsRenderingSettings;
import com.google.ads.interactivemedia.v3.api.BaseDisplayContainer;
import com.google.ads.interactivemedia.v3.api.CompanionAdSlot;
import com.google.ads.interactivemedia.v3.api.CompanionAdSlot.ClickListener;
import com.google.ads.interactivemedia.v3.api.CuePoint;
import com.google.ads.interactivemedia.v3.api.ImaSdkSettings;
import com.google.ads.interactivemedia.v3.impl.data.CompanionData;
import com.google.ads.interactivemedia.v3.impl.data.TestingConfiguration;
import com.google.ads.interactivemedia.v3.impl.data.aa;
import com.google.ads.interactivemedia.v3.impl.data.c;
import com.google.ads.interactivemedia.v3.impl.data.x;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/* compiled from: IMASDK */
public class aeb {
    private Map<String, adw> a = new HashMap();
    private Map<String, adt> b = new HashMap();
    private final Set<String> c = new HashSet();
    private Map<String, adu> d = new HashMap();
    private Map<String, ady> e = new HashMap();
    private Map<String, aep> f = new HashMap();
    private Map<String, BaseDisplayContainer> g = new HashMap();
    /* access modifiers changed from: private */
    public final Context h;
    private final adz i;
    private aec j;
    private boolean k = false;
    private final Queue<ado> l = new ArrayDeque();
    private long m;
    private TestingConfiguration n;
    private String o;
    private adx p;
    private AdsRenderingSettings q;

    public void a(ado ado) {
        aa aaVar = (aa) ado.c();
        String d2 = ado.d();
        adr b2 = ado.b();
        switch (ado.a().ordinal()) {
            case 0:
                f(b2, d2, aaVar);
                return;
            case 1:
                e(b2, d2, aaVar);
                return;
            case 2:
                d(b2, d2, aaVar);
                return;
            case 4:
                b(b2, d2, aaVar);
                return;
            case 5:
                c(b2, d2, aaVar);
                return;
            case 6:
            case 11:
                a(b2, d2, aaVar);
                return;
            case 7:
                a(b2);
                return;
            case 8:
            case 9:
                a(adq.videoDisplay1, b2, d2, aaVar);
                return;
            case 10:
                a(adq.videoDisplay2, b2, d2, aaVar);
                return;
            default:
                String valueOf = String.valueOf(ado.a());
                StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 25);
                sb.append("Unknown message channel: ");
                sb.append(valueOf);
                Log.e("IMASDK", sb.toString());
                return;
        }
    }

    /* access modifiers changed from: protected */
    public Uri a(Uri uri, ImaSdkSettings imaSdkSettings) {
        new aef();
        Builder appendQueryParameter = uri.buildUpon().appendQueryParameter("sdk_version", "a.3.11.2").appendQueryParameter("hl", imaSdkSettings.getLanguage()).appendQueryParameter("omv", a.a()).appendQueryParameter("app", this.h.getApplicationContext().getPackageName());
        if (this.n != null) {
            appendQueryParameter.appendQueryParameter(TestingConfiguration.PARAMETER_KEY, new wv().a((xl) new aft()).a(new we()).a().a((Object) this.n));
        }
        return appendQueryParameter.build();
    }

    public aeb(Context context, Uri uri, ImaSdkSettings imaSdkSettings, TestingConfiguration testingConfiguration) {
        this.h = context;
        this.n = testingConfiguration;
        this.i = new adz(context, this);
        this.o = a(uri, imaSdkSettings).toString();
    }

    public void a() {
        this.m = SystemClock.elapsedRealtime();
        this.i.a(this.o);
    }

    public void a(AdsRenderingSettings adsRenderingSettings) {
        this.q = adsRenderingSettings;
    }

    public WebView b() {
        return this.i.a();
    }

    /* access modifiers changed from: 0000 */
    public TestingConfiguration c() {
        return this.n;
    }

    private void a(adr adr) {
        switch (adr.ordinal()) {
            case 39:
                this.p.b();
                return;
            case 40:
                this.p.c();
                break;
        }
    }

    private void a(adr adr, String str, aa aaVar) {
        int ordinal = adr.ordinal();
        if (ordinal == 32) {
            this.j = new aec(aaVar.adTimeUpdateMs);
            this.k = true;
            a(SystemClock.elapsedRealtime() - this.m, str);
            d();
        } else if (ordinal != 36) {
            a("other", adr);
        } else if (aaVar.ln == null || aaVar.n == null || aaVar.m == null) {
            String valueOf = String.valueOf(aaVar);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 30);
            sb.append("Invalid logging message data: ");
            sb.append(valueOf);
            Log.e("IMASDK", sb.toString());
        } else {
            String str2 = "SDK_LOG:";
            String valueOf2 = String.valueOf(aaVar.n);
            String concat = valueOf2.length() != 0 ? str2.concat(valueOf2) : new String(str2);
            switch (aaVar.ln.charAt(0)) {
                case 'D':
                    Log.d(concat, aaVar.m);
                    return;
                case 'E':
                case 'S':
                    Log.e(concat, aaVar.m);
                    return;
                case 'I':
                    Log.i(concat, aaVar.m);
                    return;
                case 'V':
                    Log.v(concat, aaVar.m);
                    return;
                case 'W':
                    Log.w(concat, aaVar.m);
                    return;
                default:
                    String str3 = "IMASDK";
                    String str4 = "Unrecognized log level: ";
                    String valueOf3 = String.valueOf(aaVar.ln);
                    Log.w(str3, valueOf3.length() != 0 ? str4.concat(valueOf3) : new String(str4));
                    Log.w(concat, aaVar.m);
                    return;
            }
        }
    }

    private void a(long j2, String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("webViewLoadingTime", Long.valueOf(j2));
        b(new ado(adq.webViewLoaded, adr.csi, str, hashMap));
    }

    private void b(adr adr, String str, aa aaVar) {
        act act = (act) this.g.get(str);
        adw adw = (adw) this.a.get(str);
        aep aep = (aep) this.f.get(str);
        if (act == null || adw == null || aep == null) {
            String valueOf = String.valueOf(adr);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 60 + String.valueOf(str).length());
            sb.append("Received displayContainer message: ");
            sb.append(valueOf);
            sb.append(" for invalid session id: ");
            sb.append(str);
            Log.e("IMASDK", sb.toString());
            return;
        }
        int ordinal = adr.ordinal();
        if (ordinal != 21) {
            if (ordinal != 29 && ordinal != 49) {
                a(adq.displayContainer.toString(), adr);
            }
        } else if (aaVar == null || aaVar.companions == null) {
            adw.a(AdErrorType.LOAD, AdErrorCode.INTERNAL_ERROR, "Display companions message requires companions in data.");
        } else {
            adw.a(aaVar.companions);
            Map a2 = a(adw, act, aaVar.companions.keySet());
            AdsRenderingSettings adsRenderingSettings = this.q;
            if (adsRenderingSettings == null || adsRenderingSettings.isRenderCompanions()) {
                for (String str2 : a2.keySet()) {
                    a((ViewGroup) a2.get(str2), (CompanionData) aaVar.companions.get(str2), str, (CompanionAdSlot) act.a().get(str2));
                }
            }
        }
    }

    private void c(adr adr, String str, aa aaVar) {
        ady ady = (ady) this.e.get(str);
        if (ady != null) {
            ady.a();
        }
    }

    private static Map<String, ViewGroup> a(adw adw, act act, Set<String> set) {
        HashMap hashMap = new HashMap(set.size());
        for (String str : set) {
            CompanionAdSlot companionAdSlot = (CompanionAdSlot) act.a().get(str);
            if (companionAdSlot.getContainer() != null) {
                hashMap.put(str, companionAdSlot.getContainer());
            } else {
                adw.a(AdErrorType.LOAD, AdErrorCode.INTERNAL_ERROR, "Display requested for invalid companion slot.");
            }
        }
        return hashMap;
    }

    private void d(adr adr, String str, aa aaVar) {
        adu adu = (adu) this.d.get(str);
        if (adu == null) {
            String valueOf = String.valueOf(adr);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 51 + String.valueOf(str).length());
            sb.append("Received request message: ");
            sb.append(valueOf);
            sb.append(" for invalid session id: ");
            sb.append(str);
            Log.e("IMASDK", sb.toString());
            return;
        }
        int ordinal = adr.ordinal();
        if (ordinal != 8) {
            if (ordinal == 24) {
                adu.a(str, AdErrorType.LOAD, aaVar.errorCode, b(aaVar.errorMessage, aaVar.innerError));
            } else if (ordinal != 57) {
                a(adq.adsLoader.toString(), adr);
            } else {
                adu.a(str, this.j, aaVar.streamId, aaVar.monitorAppLifecycle);
                String str2 = "IMASDK";
                String str3 = "Stream initialized with streamId: ";
                String valueOf2 = String.valueOf(aaVar.streamId);
                Log.i(str2, valueOf2.length() != 0 ? str3.concat(valueOf2) : new String(str3));
            }
        } else if (aaVar == null) {
            adu.a(str, AdErrorType.LOAD, AdErrorCode.INTERNAL_ERROR, "adsLoaded message did not contain cue points.");
        } else {
            adu.a(str, this.j, aaVar.adCuePoints, aaVar.internalCuePoints, aaVar.monitorAppLifecycle);
        }
    }

    private void a(adq adq, adr adr, String str, aa aaVar) {
        aep aep = (aep) this.f.get(str);
        if (aep == null) {
            String valueOf = String.valueOf(adq);
            String valueOf2 = String.valueOf(adr);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 44 + String.valueOf(valueOf2).length() + String.valueOf(str).length());
            sb.append("Received ");
            sb.append(valueOf);
            sb.append(" message: ");
            sb.append(valueOf2);
            sb.append(" for invalid session id: ");
            sb.append(str);
            Log.w("IMASDK", sb.toString());
            return;
        }
        aep.a(adr, aaVar);
    }

    private void e(adr adr, String str, aa aaVar) {
        adw adw = (adw) this.a.get(str);
        if (adw == null) {
            String valueOf = String.valueOf(adr);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 51 + String.valueOf(str).length());
            sb.append("Received manager message: ");
            sb.append(valueOf);
            sb.append(" for invalid session id: ");
            sb.append(str);
            Log.e("IMASDK", sb.toString());
            return;
        }
        c cVar = (aaVar == null || aaVar.adData == null) ? null : aaVar.adData;
        switch (adr.ordinal()) {
            case 0:
                adw.a(new adv(AdEventType.AD_BREAK_ENDED, cVar));
                return;
            case 1:
                adv adv = new adv(AdEventType.AD_BREAK_READY, null);
                adv.c = new ArrayMap(1);
                adv.c.put("adBreakTime", aaVar.adBreakTime);
                adw.a(adv);
                return;
            case 2:
                adw.a(new adv(AdEventType.AD_BREAK_STARTED, cVar));
                return;
            case 3:
                adw.a(new adv(AdEventType.AD_BUFFERING, null));
                return;
            case 4:
                return;
            case 5:
                adw.a(new adv(AdEventType.AD_PERIOD_ENDED, null));
                return;
            case 6:
                adw.a(new adv(AdEventType.AD_PERIOD_STARTED, null));
                return;
            case 7:
                adv adv2 = new adv(AdEventType.AD_PROGRESS, cVar);
                acf acf = new acf(aaVar.currentTime, aaVar.duration, aaVar.adPosition, aaVar.totalAds, aaVar.adBreakDuration);
                adv2.e = acf;
                adw.a(adv2);
                return;
            case 9:
                adw.a(new adv(AdEventType.ALL_ADS_COMPLETED, null));
                return;
            case 11:
                adw.a(new adv(AdEventType.CLICKED, cVar));
                return;
            case 12:
                adw.a(new adv(AdEventType.COMPLETED, cVar));
                return;
            case 15:
                adw.a(new adv(AdEventType.CONTENT_PAUSE_REQUESTED, null));
                return;
            case 16:
                adw.a(new adv(AdEventType.CONTENT_RESUME_REQUESTED, null));
                return;
            case 19:
                adv adv3 = new adv(AdEventType.CUEPOINTS_CHANGED, null);
                adv3.d = new ArrayList();
                for (x xVar : aaVar.cuepoints) {
                    List<CuePoint> list = adv3.d;
                    adc adc = new adc(xVar.start(), xVar.end(), xVar.played());
                    list.add(adc);
                }
                adw.a(adv3);
                return;
            case 24:
                adw.a(AdErrorType.PLAY, aaVar.errorCode, b(aaVar.errorMessage, aaVar.innerError));
                return;
            case 25:
                adw.a(new adv(AdEventType.FIRST_QUARTILE, cVar));
                return;
            case 30:
            case 38:
            case 62:
            case 66:
                return;
            case 34:
                if (cVar != null) {
                    adw.a(new adv(AdEventType.LOADED, cVar));
                    return;
                }
                Log.e("IMASDK", "Ad loaded message requires adData");
                adw.a(AdErrorType.LOAD, AdErrorCode.INTERNAL_ERROR, "Ad loaded message did not contain adData.");
                return;
            case 36:
                adv adv4 = new adv(AdEventType.LOG, cVar);
                adv4.c = aaVar.logData.constructMap();
                adw.a(adv4);
                return;
            case 37:
                adw.a(new adv(AdEventType.MIDPOINT, cVar));
                return;
            case 41:
                adw.a(new adv(AdEventType.PAUSED, cVar));
                return;
            case 45:
                adw.a(new adv(AdEventType.RESUMED, cVar));
                return;
            case 50:
                adv adv5 = new adv(AdEventType.SKIPPED, null);
                adv5.g = aaVar.seekTime;
                adw.a(adv5);
                return;
            case 51:
                adw.a(new adv(AdEventType.SKIPPABLE_STATE_CHANGED, cVar));
                return;
            case 53:
                adw.a(new adv(AdEventType.STARTED, cVar));
                return;
            case 58:
                adw.a(new adv(AdEventType.THIRD_QUARTILE, cVar));
                return;
            case 64:
                adw.a(new adv(AdEventType.TAPPED, cVar));
                return;
            case 65:
                adv adv6 = new adv(AdEventType.ICON_TAPPED, null);
                adv6.f = aaVar.clickThroughUrl;
                adw.a(adv6);
                return;
            default:
                a(adq.adsManager.toString(), adr);
                return;
        }
    }

    private void f(adr adr, String str, aa aaVar) {
        if (!this.c.contains(str)) {
            adt adt = (adt) this.b.get(str);
            if (adt == null) {
                String valueOf = String.valueOf(adr);
                StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 51 + String.valueOf(str).length());
                sb.append("Received monitor message: ");
                sb.append(valueOf);
                sb.append(" for invalid session id: ");
                sb.append(str);
                Log.e("IMASDK", sb.toString());
            } else if (aaVar == null) {
                String valueOf2 = String.valueOf(adr);
                StringBuilder sb2 = new StringBuilder(String.valueOf(valueOf2).length() + 56 + String.valueOf(str).length());
                sb2.append("Received monitor message: ");
                sb2.append(valueOf2);
                sb2.append(" for session id: ");
                sb2.append(str);
                sb2.append(" with no data");
                Log.e("IMASDK", sb2.toString());
            } else {
                int ordinal = adr.ordinal();
                if (ordinal == 28) {
                    adt.a(aaVar.queryId, aaVar.eventId);
                } else if (ordinal != 44) {
                    a(adq.activityMonitor.toString(), adr);
                } else {
                    adt.a(aaVar.queryId, aaVar.eventId, aaVar.vastEvent);
                }
            }
        }
    }

    private static void a(String str, adr adr) {
        String valueOf = String.valueOf(adr);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 43 + String.valueOf(str).length());
        sb.append("Illegal message type ");
        sb.append(valueOf);
        sb.append(" received for ");
        sb.append(str);
        sb.append(" channel");
        Log.i("IMASDK", sb.toString());
    }

    private static String b(String str, String str2) {
        if (str2 == null || str2.length() == 0) {
            return str;
        }
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 12 + String.valueOf(str2).length());
        sb.append(str);
        sb.append(" Caused by: ");
        sb.append(str2);
        return sb.toString();
    }

    public void a(adu adu, String str) {
        this.d.put(str, adu);
    }

    public void a(adw adw, String str) {
        this.a.put(str, adw);
    }

    public void a(adx adx) {
        this.p = adx;
    }

    public void a(adt adt, String str) {
        this.b.put(str, adt);
    }

    public void a(String str) {
        this.b.remove(str);
        this.c.add(str);
    }

    public void a(aep aep, String str) {
        this.f.put(str, aep);
    }

    public void a(BaseDisplayContainer baseDisplayContainer, String str) {
        this.g.put(str, baseDisplayContainer);
    }

    public void b(String str) {
        this.a.remove(str);
        this.g.remove(str);
        this.f.remove(str);
    }

    public void b(ado ado) {
        this.l.add(ado);
        d();
    }

    private void d() {
        while (this.k && !this.l.isEmpty()) {
            this.i.a((ado) this.l.remove());
        }
    }

    public void c(String str) {
        if (str != null && str.length() > 0) {
            new ads(this, str).execute(new Void[0]);
        }
    }

    private void a(ViewGroup viewGroup, CompanionData companionData, String str, CompanionAdSlot companionAdSlot) {
        View view;
        viewGroup.removeAllViews();
        acw acw = (acw) companionAdSlot;
        List a2 = acw.a();
        switch (companionData.type().ordinal()) {
            case 0:
            case 2:
                view = a(viewGroup.getContext(), companionData, a2);
                break;
            case 1:
                view = a(viewGroup.getContext(), companionData, str, a2);
                break;
            default:
                view = null;
                break;
        }
        view.setTag(str);
        acw.a(str);
        viewGroup.addView(view);
    }

    public void a(String str, String str2) {
        if (!afx.a(str) && !afx.a(str2)) {
            HashMap hashMap = new HashMap();
            hashMap.put("companionId", str);
            b(new ado(adq.displayContainer, adr.companionView, str2, hashMap));
        }
    }

    /* access modifiers changed from: protected */
    public View a(Context context, CompanionData companionData, List<ClickListener> list) {
        return new acx(context, this, companionData, list);
    }

    /* access modifiers changed from: protected */
    public View a(Context context, CompanionData companionData, String str, List<ClickListener> list) {
        adm adm = new adm(context, this, companionData, str, list);
        new adn(adm).execute(new Void[0]);
        return adm;
    }
}
