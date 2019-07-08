package com.google.ads.interactivemedia.v3.internal;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.StrictMode;
import android.os.StrictMode.ThreadPolicy;
import android.os.StrictMode.ThreadPolicy.Builder;
import android.util.Log;
import com.brightcove.player.event.EventType;
import com.google.ads.interactivemedia.v3.api.AdDisplayContainer;
import com.google.ads.interactivemedia.v3.api.AdError;
import com.google.ads.interactivemedia.v3.api.AdError.AdErrorCode;
import com.google.ads.interactivemedia.v3.api.AdError.AdErrorType;
import com.google.ads.interactivemedia.v3.api.AdErrorEvent;
import com.google.ads.interactivemedia.v3.api.AdErrorEvent.AdErrorListener;
import com.google.ads.interactivemedia.v3.api.AdsLoader;
import com.google.ads.interactivemedia.v3.api.AdsLoader.AdsLoadedListener;
import com.google.ads.interactivemedia.v3.api.AdsManagerLoadedEvent;
import com.google.ads.interactivemedia.v3.api.AdsRequest;
import com.google.ads.interactivemedia.v3.api.BaseDisplayContainer;
import com.google.ads.interactivemedia.v3.api.ImaSdkFactory;
import com.google.ads.interactivemedia.v3.api.ImaSdkSettings;
import com.google.ads.interactivemedia.v3.api.StreamDisplayContainer;
import com.google.ads.interactivemedia.v3.api.StreamRequest;
import com.google.ads.interactivemedia.v3.impl.data.TestingConfiguration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

/* compiled from: IMASDK */
public final class ach implements AdsLoader {
    private adu a;
    /* access modifiers changed from: private */
    public final aeb b;
    /* access modifiers changed from: private */
    public final Context c;
    /* access modifiers changed from: private */
    public final add d;
    private final List<AdsLoadedListener> e;
    /* access modifiers changed from: private */
    public final Map<String, AdsRequest> f;
    /* access modifiers changed from: private */
    public final Map<String, StreamRequest> g;
    /* access modifiers changed from: private */
    public final aee h;
    /* access modifiers changed from: private */
    public afm i;
    /* access modifiers changed from: private */
    public final Object j;
    /* access modifiers changed from: private */
    public aet k;
    /* access modifiers changed from: private */
    public ImaSdkSettings l;
    /* access modifiers changed from: private */
    public TestingConfiguration m;
    private StreamDisplayContainer n;
    private AdDisplayContainer o;

    @Deprecated
    public ach(Context context, Uri uri, ImaSdkSettings imaSdkSettings) {
        this(context, uri, imaSdkSettings, null);
        this.b.a();
    }

    public ach(Context context, Uri uri, ImaSdkSettings imaSdkSettings, StreamDisplayContainer streamDisplayContainer, TestingConfiguration testingConfiguration) {
        this(context, uri, imaSdkSettings, testingConfiguration);
        this.n = streamDisplayContainer;
        aes.a = true;
        this.b.a();
    }

    public ach(Context context, Uri uri, ImaSdkSettings imaSdkSettings, AdDisplayContainer adDisplayContainer, TestingConfiguration testingConfiguration) {
        this(context, uri, imaSdkSettings, testingConfiguration);
        this.o = adDisplayContainer;
        aes.a = true;
        this.b.a();
    }

    private ach(Context context, Uri uri, ImaSdkSettings imaSdkSettings, TestingConfiguration testingConfiguration) {
        this(new aeb(context, uri, imaSdkSettings, testingConfiguration), context);
        this.l = imaSdkSettings;
        this.m = testingConfiguration;
    }

    private ach(aeb aeb, Context context) {
        this(aeb, context, new aee(aeb, context));
        aeb.a((adx) this.h);
    }

    private ach(aeb aeb, Context context, aee aee) {
        this.a = new adu(this);
        this.d = new add();
        this.e = new ArrayList(1);
        this.f = new HashMap();
        this.g = new HashMap();
        this.j = new Object();
        this.h = aee;
        this.b = aeb;
        this.c = context;
        this.l = ImaSdkFactory.getInstance().createImaSdkSettings();
    }

    public final void a() {
        this.b.a();
    }

    public final void contentComplete() {
        this.b.b(new ado(adq.adsLoader, adr.contentComplete, EventType.ANY));
    }

    public final void requestAds(AdsRequest adsRequest) {
        boolean z;
        String b2 = b();
        if (adsRequest == null) {
            this.d.a((AdErrorEvent) new acc(new AdError(AdErrorType.LOAD, AdErrorCode.INVALID_ARGUMENTS, "AdsRequest cannot be null.")));
            z = false;
        } else if (a(adsRequest) == null) {
            this.d.a((AdErrorEvent) new acc(new AdError(AdErrorType.LOAD, AdErrorCode.INVALID_ARGUMENTS, "Ad display container must be provided.")));
            z = false;
        } else if (a(adsRequest).getAdContainer() == null) {
            this.d.a((AdErrorEvent) new acc(new AdError(AdErrorType.LOAD, AdErrorCode.INVALID_ARGUMENTS, "Ad display container must have a UI container.")));
            z = false;
        } else if (!afx.a(adsRequest.getAdTagUrl()) || !afx.a(adsRequest.getAdsResponse())) {
            z = true;
        } else {
            this.d.a((AdErrorEvent) new acc(new AdError(AdErrorType.LOAD, AdErrorCode.INVALID_ARGUMENTS, "Ad tag url must non-null and non empty.")));
            z = false;
        }
        if (z) {
            this.f.put(b2, adsRequest);
            this.b.a(this.a, b2);
            this.b.a((BaseDisplayContainer) a(adsRequest), b2);
            new aci(this, adsRequest, b2).execute(new String[]{adsRequest.getAdTagUrl()});
        }
    }

    public final String requestStream(StreamRequest streamRequest) {
        boolean z;
        String b2 = b();
        if (streamRequest == null) {
            this.d.a((AdErrorEvent) new acc(new AdError(AdErrorType.LOAD, AdErrorCode.INVALID_ARGUMENTS, "StreamRequest cannot be null.")));
            z = false;
        } else if (a(streamRequest) == null) {
            this.d.a((AdErrorEvent) new acc(new AdError(AdErrorType.LOAD, AdErrorCode.INVALID_ARGUMENTS, "Stream display container must be provided.")));
            z = false;
        } else if (a(streamRequest).getVideoStreamPlayer() == null) {
            this.d.a((AdErrorEvent) new acc(new AdError(AdErrorType.LOAD, AdErrorCode.INVALID_ARGUMENTS, "Stream requests must specify a player.")));
            z = false;
        } else {
            z = true;
        }
        if (z) {
            this.g.put(b2, streamRequest);
            this.b.a(this.a, b2);
            this.b.a((BaseDisplayContainer) a(streamRequest), b2);
            new ack(this, streamRequest, b2).execute(new Void[0]);
        }
        return b2;
    }

    private final String b() {
        TestingConfiguration testingConfiguration = this.m;
        if (testingConfiguration == null || !testingConfiguration.ignoreStrictModeFalsePositives()) {
            return UUID.randomUUID().toString();
        }
        ThreadPolicy threadPolicy = StrictMode.getThreadPolicy();
        StrictMode.setThreadPolicy(new Builder(threadPolicy).permitDiskReads().build());
        String uuid = UUID.randomUUID().toString();
        StrictMode.setThreadPolicy(threadPolicy);
        return uuid;
    }

    public final ImaSdkSettings getSettings() {
        return this.l;
    }

    public final void addAdsLoadedListener(AdsLoadedListener adsLoadedListener) {
        this.e.add(adsLoadedListener);
    }

    public final void removeAdsLoadedListener(AdsLoadedListener adsLoadedListener) {
        this.e.remove(adsLoadedListener);
    }

    public final void addAdErrorListener(AdErrorListener adErrorListener) {
        this.d.a(adErrorListener);
    }

    public final void removeAdErrorListener(AdErrorListener adErrorListener) {
        this.d.b(adErrorListener);
    }

    /* access modifiers changed from: 0000 */
    public final void a(AdsManagerLoadedEvent adsManagerLoadedEvent) {
        for (AdsLoadedListener onAdsManagerLoaded : this.e) {
            onAdsManagerLoaded.onAdsManagerLoaded(adsManagerLoadedEvent);
        }
    }

    /* access modifiers changed from: private */
    public final String c() {
        return String.format("android%s:%s:%s", new Object[]{VERSION.RELEASE, "3.11.2", this.c.getPackageName()});
    }

    /* access modifiers changed from: private */
    public final String d() {
        if (this.c.checkCallingOrSelfPermission("android.permission.ACCESS_NETWORK_STATE") != 0) {
            Log.w("IMASDK", "Host application doesn't have ACCESS_NETWORK_STATE permission");
            return "android:0";
        }
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.c.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null) {
            return "android:0";
        }
        return String.format(Locale.US, "android:%d:%d", new Object[]{Integer.valueOf(activeNetworkInfo.getType()), Integer.valueOf(activeNetworkInfo.getSubtype())});
    }

    /* access modifiers changed from: private */
    public final acj e() {
        PackageManager packageManager = this.c.getPackageManager();
        ResolveInfo resolveActivity = packageManager.resolveActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=com.google.ads.interactivemedia.v3")), 65536);
        if (resolveActivity == null) {
            return null;
        }
        ActivityInfo activityInfo = resolveActivity.activityInfo;
        if (activityInfo == null) {
            return null;
        }
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(activityInfo.packageName, 0);
            if (packageInfo == null) {
                return null;
            }
            return acj.create(packageInfo.versionCode, activityInfo.packageName);
        } catch (NameNotFoundException unused) {
            return null;
        }
    }

    /* access modifiers changed from: private */
    public final AdDisplayContainer a(AdsRequest adsRequest) {
        return aes.a ? this.o : adsRequest.getAdDisplayContainer();
    }

    /* access modifiers changed from: private */
    public final StreamDisplayContainer a(StreamRequest streamRequest) {
        return aes.a ? this.n : streamRequest.getStreamDisplayContainer();
    }
}
