package com.inmobi.rendering.mraid;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.ContentObserver;
import android.media.AudioManager;
import android.os.Handler;
import com.google.android.exoplayer2.util.MimeTypes;
import com.inmobi.rendering.RenderView;

@SuppressLint({"ClickableViewAccessibility"})
public final class MraidMediaProcessor {
    /* access modifiers changed from: private */
    public static final String f = "MraidMediaProcessor";
    public RenderView a;
    public MediaRenderView b;
    public RingerModeChangeReceiver c;
    public a d;
    public HeadphonesPluggedChangeReceiver e;

    public final class HeadphonesPluggedChangeReceiver extends BroadcastReceiver {
        private String b;

        public HeadphonesPluggedChangeReceiver(String str) {
            this.b = str;
        }

        public final void onReceive(Context context, Intent intent) {
            if (intent != null && "android.intent.action.HEADSET_PLUG".equals(intent.getAction())) {
                boolean z = false;
                int intExtra = intent.getIntExtra("state", 0);
                MraidMediaProcessor.f;
                MraidMediaProcessor mraidMediaProcessor = MraidMediaProcessor.this;
                String str = this.b;
                if (1 == intExtra) {
                    z = true;
                }
                MraidMediaProcessor.b(mraidMediaProcessor, str, z);
            }
        }
    }

    public final class RingerModeChangeReceiver extends BroadcastReceiver {
        private String b;

        public RingerModeChangeReceiver(String str) {
            this.b = str;
        }

        public final void onReceive(Context context, Intent intent) {
            if (intent != null && "android.media.RINGER_MODE_CHANGED".equals(intent.getAction())) {
                int intExtra = intent.getIntExtra("android.media.EXTRA_RINGER_MODE", 2);
                MraidMediaProcessor.f;
                MraidMediaProcessor.a(MraidMediaProcessor.this, this.b, 2 != intExtra);
            }
        }
    }

    public final class a extends ContentObserver {
        private Context b;
        private int c = -1;
        private String d;

        public a(String str, Context context, Handler handler) {
            super(handler);
            this.d = str;
            this.b = context;
        }

        public final void onChange(boolean z) {
            super.onChange(z);
            Context context = this.b;
            if (context != null) {
                int streamVolume = ((AudioManager) context.getSystemService(MimeTypes.BASE_TYPE_AUDIO)).getStreamVolume(3);
                if (streamVolume != this.c) {
                    this.c = streamVolume;
                    MraidMediaProcessor.a(MraidMediaProcessor.this, this.d, streamVolume);
                }
            }
        }
    }

    public MraidMediaProcessor(RenderView renderView) {
        this.a = renderView;
    }

    public static boolean a() {
        Context b2 = com.inmobi.commons.a.a.b();
        if (b2 == null || 2 == ((AudioManager) b2.getSystemService(MimeTypes.BASE_TYPE_AUDIO)).getRingerMode()) {
            return false;
        }
        return true;
    }

    public final void b() {
        Context b2 = com.inmobi.commons.a.a.b();
        if (b2 != null) {
            RingerModeChangeReceiver ringerModeChangeReceiver = this.c;
            if (ringerModeChangeReceiver != null) {
                b2.unregisterReceiver(ringerModeChangeReceiver);
                this.c = null;
            }
        }
    }

    public final void c() {
        Context b2 = com.inmobi.commons.a.a.b();
        if (!(b2 == null || this.d == null)) {
            b2.getContentResolver().unregisterContentObserver(this.d);
            this.d = null;
        }
    }

    public static boolean d() {
        Context b2 = com.inmobi.commons.a.a.b();
        if (b2 == null) {
            return false;
        }
        return ((AudioManager) b2.getSystemService(MimeTypes.BASE_TYPE_AUDIO)).isWiredHeadsetOn();
    }

    public final void e() {
        Context b2 = com.inmobi.commons.a.a.b();
        if (b2 != null) {
            HeadphonesPluggedChangeReceiver headphonesPluggedChangeReceiver = this.e;
            if (headphonesPluggedChangeReceiver != null) {
                b2.unregisterReceiver(headphonesPluggedChangeReceiver);
                this.e = null;
            }
        }
    }

    static /* synthetic */ void a(MraidMediaProcessor mraidMediaProcessor, String str, boolean z) {
        RenderView renderView = mraidMediaProcessor.a;
        if (renderView != null) {
            StringBuilder sb = new StringBuilder("fireDeviceMuteChangeEvent(");
            sb.append(z);
            sb.append(");");
            renderView.a(str, sb.toString());
        }
    }

    static /* synthetic */ void b(MraidMediaProcessor mraidMediaProcessor, String str, boolean z) {
        RenderView renderView = mraidMediaProcessor.a;
        if (renderView != null) {
            StringBuilder sb = new StringBuilder("fireHeadphonePluggedEvent(");
            sb.append(z);
            sb.append(");");
            renderView.a(str, sb.toString());
        }
    }

    static /* synthetic */ void a(MraidMediaProcessor mraidMediaProcessor, String str, int i) {
        RenderView renderView = mraidMediaProcessor.a;
        if (renderView != null) {
            StringBuilder sb = new StringBuilder("fireDeviceVolumeChangeEvent(");
            sb.append(i);
            sb.append(");");
            renderView.a(str, sb.toString());
        }
    }
}
