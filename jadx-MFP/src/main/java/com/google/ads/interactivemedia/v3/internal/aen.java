package com.google.ads.interactivemedia.v3.internal;

import android.content.Context;
import android.net.Uri;
import android.net.Uri.Builder;
import android.util.Log;
import com.google.ads.interactivemedia.v3.api.AdError;
import com.google.ads.interactivemedia.v3.api.AdError.AdErrorCode;
import com.google.ads.interactivemedia.v3.api.AdError.AdErrorType;
import com.google.ads.interactivemedia.v3.api.AdErrorEvent;
import com.google.ads.interactivemedia.v3.api.StreamDisplayContainer;
import com.google.ads.interactivemedia.v3.api.player.VideoProgressUpdate;
import com.google.ads.interactivemedia.v3.api.player.VideoStreamPlayer;
import com.google.ads.interactivemedia.v3.api.player.VideoStreamPlayer.VideoStreamPlayerCallback;
import com.google.ads.interactivemedia.v3.impl.data.aa;
import com.google.ads.interactivemedia.v3.impl.data.ah;
import com.google.ads.interactivemedia.v3.impl.data.c;
import java.util.HashMap;
import java.util.Map;

/* compiled from: IMASDK */
public final class aen implements VideoStreamPlayerCallback, aei, aep {
    private VideoStreamPlayer a;
    private aeb b;
    private final ael c;
    private boolean d;
    private adb e;
    private aeq f;
    private final String g;
    private String h;

    public aen(String str, aec aec, aeb aeb, ael ael, StreamDisplayContainer streamDisplayContainer, String str2, Context context) throws AdError {
        this(str, aec, aeb, ael, streamDisplayContainer, str2, null, null);
    }

    public final void onAdError(AdErrorEvent adErrorEvent) {
    }

    private aen(String str, aec aec, aeb aeb, ael ael, StreamDisplayContainer streamDisplayContainer, String str2, adb adb, aeq aeq) throws AdError {
        this.d = false;
        this.a = streamDisplayContainer.getVideoStreamPlayer();
        VideoStreamPlayer videoStreamPlayer = this.a;
        if (videoStreamPlayer != null) {
            this.c = ael;
            this.g = str;
            this.b = aeb;
            this.h = str2;
            this.d = false;
            this.e = null;
            if (this.e == null) {
                this.e = new adb(videoStreamPlayer, aec.a());
            }
            this.f = null;
            if (this.f == null) {
                this.f = new aeq(aeb.b(), streamDisplayContainer.getAdContainer());
                return;
            }
            return;
        }
        throw new AdError(AdErrorType.LOAD, AdErrorCode.INVALID_ARGUMENTS, "Server-side ad insertion player was not provided.");
    }

    public final void a() {
        this.e.a(this);
    }

    public final VideoProgressUpdate getAdProgress() {
        return this.a.getContentProgress();
    }

    public final void a(adr adr, aa aaVar) {
        if (adr.ordinal() == 35) {
            if (aaVar == null || aaVar.streamUrl == null) {
                this.c.a((AdErrorEvent) new acc(new AdError(AdErrorType.LOAD, AdErrorCode.INTERNAL_ERROR, "Load message must contain video url.")));
            } else {
                this.d = false;
                this.e.b();
                String str = aaVar.streamUrl;
                if (str != null) {
                    String str2 = this.h;
                    if (!(str2 == null || str2.length() == 0)) {
                        String replaceAll = this.h.trim().replaceAll("\\s+", "");
                        if (replaceAll.charAt(0) == '?') {
                            replaceAll = replaceAll.substring(1);
                        }
                        if (replaceAll.length() != 0) {
                            Map a2 = qi.a(Uri.parse(str));
                            HashMap hashMap = new HashMap();
                            Builder buildUpon = Uri.parse(str).buildUpon();
                            buildUpon.clearQuery();
                            String str3 = "http://www.dom.com/path?";
                            String valueOf = String.valueOf(replaceAll);
                            Map a3 = qi.a(Uri.parse(valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3)));
                            hashMap.putAll(a3);
                            if (!a2.isEmpty()) {
                                for (String str4 : a2.keySet()) {
                                    if (!a3.containsKey(str4)) {
                                        hashMap.put(str4, (String) a2.get(str4));
                                    }
                                }
                            }
                            buildUpon.encodedQuery(qi.a((Map<String, String>) hashMap));
                            str = buildUpon.build().toString();
                        }
                    }
                }
                this.a.loadUrl(str, aaVar.subtitles);
            }
        }
    }

    public final void b() {
        Log.d("SDK_DEBUG", "Destroying StreamVideoDisplay");
        this.a.removeCallback(this);
        this.a = null;
        this.b = null;
        adb adb = this.e;
        if (adb != null) {
            adb.c();
            this.e.b(this);
        }
        this.e = null;
        if (aes.a) {
            this.f.d();
        } else {
            this.f.b();
        }
        this.f = null;
    }

    public final void onUserTextReceived(String str) {
        a(adr.timedMetadata, (Object) aeo.create(str));
    }

    public final void onVolumeChanged(int i) {
        a(adr.volumeChange, (Object) ah.builder().volumePercentage(i).build());
    }

    public final void a(VideoProgressUpdate videoProgressUpdate) {
        if (!this.d) {
            a(adr.start, (Object) ah.builder().volumePercentage(this.a.getVolume()).build());
            this.d = true;
        }
        a(adr.timeupdate, (Object) videoProgressUpdate);
    }

    public final void c() {
        this.a.onAdBreakStarted();
    }

    public final void d() {
        this.a.onAdBreakEnded();
        h();
    }

    public final void e() {
        this.a.onAdPeriodStarted();
    }

    public final void f() {
        this.a.onAdPeriodEnded();
    }

    public final void a(long j) {
        this.a.seek(j);
    }

    public final void g() {
        this.a.addCallback(this);
    }

    private final void a(adr adr, Object obj) {
        this.b.b(new ado(adq.videoDisplay, adr, this.g, obj));
    }

    /* access modifiers changed from: 0000 */
    public final void a(c cVar) {
        if (!aes.a || !cVar.isLinear()) {
            this.f.a(cVar);
        } else {
            this.f.c();
        }
    }

    /* access modifiers changed from: 0000 */
    public final void h() {
        if (aes.a) {
            this.f.d();
        } else {
            this.f.a();
        }
    }
}
