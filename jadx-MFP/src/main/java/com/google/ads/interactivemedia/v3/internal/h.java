package com.google.ads.interactivemedia.v3.internal;

import android.content.Context;
import android.database.ContentObserver;
import android.media.AudioManager;
import android.os.Handler;
import android.provider.Settings.System;
import com.google.android.exoplayer2.util.MimeTypes;

/* compiled from: IMASDK */
public final class h extends ContentObserver {
    private final Context a;
    private final AudioManager b;
    private final ho c;
    private final g d;
    private float e;

    public h(Handler handler, Context context, ho hoVar, g gVar) {
        super(handler);
        this.a = context;
        this.b = (AudioManager) context.getSystemService(MimeTypes.BASE_TYPE_AUDIO);
        this.c = hoVar;
        this.d = gVar;
    }

    public final void onChange(boolean z) {
        super.onChange(z);
        float c2 = c();
        if (c2 != this.e) {
            this.e = c2;
            d();
        }
    }

    public final void a() {
        this.e = c();
        d();
        this.a.getContentResolver().registerContentObserver(System.CONTENT_URI, true, this);
    }

    public final void b() {
        this.a.getContentResolver().unregisterContentObserver(this);
    }

    private final float c() {
        return ho.a(this.b.getStreamVolume(3), this.b.getStreamMaxVolume(3));
    }

    private final void d() {
        this.d.a(this.e);
    }
}
