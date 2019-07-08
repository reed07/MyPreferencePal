package com.facebook.ads.internal.view.i.c;

import android.content.Context;
import android.media.AudioManager;
import android.media.AudioManager.OnAudioFocusChangeListener;
import android.os.Handler;
import android.os.Looper;
import com.facebook.ads.internal.o.f;
import com.facebook.ads.internal.view.i.a.c;
import com.facebook.ads.internal.view.i.b.d;
import com.facebook.ads.internal.view.i.b.i;
import com.facebook.ads.internal.view.i.b.j;
import com.facebook.ads.internal.view.i.b.k;
import com.facebook.ads.internal.view.i.b.l;
import com.google.android.exoplayer2.util.MimeTypes;
import java.lang.ref.WeakReference;

public class b extends c {
    /* access modifiers changed from: private */
    public WeakReference<OnAudioFocusChangeListener> a = null;
    private final d b = new d() {
        public void a(com.facebook.ads.internal.view.i.b.c cVar) {
            ((AudioManager) b.this.getContext().getApplicationContext().getSystemService(MimeTypes.BASE_TYPE_AUDIO)).abandonAudioFocus(b.this.a == null ? null : (OnAudioFocusChangeListener) b.this.a.get());
        }
    };
    private final j c = new j() {
        public void a(i iVar) {
            ((AudioManager) b.this.getContext().getApplicationContext().getSystemService(MimeTypes.BASE_TYPE_AUDIO)).abandonAudioFocus(b.this.a == null ? null : (OnAudioFocusChangeListener) b.this.a.get());
        }
    };
    private final l d = new l() {
        public void a(k kVar) {
            if (b.this.a == null || b.this.a.get() == null) {
                b.this.a = new WeakReference(new OnAudioFocusChangeListener() {
                    public void onAudioFocusChange(final int i) {
                        new Handler(Looper.getMainLooper()).post(new Runnable() {
                            public void run() {
                                if (b.this.getVideoView() != null && i <= 0) {
                                    b.this.getVideoView().a(false);
                                }
                            }
                        });
                    }
                });
            }
            ((AudioManager) b.this.getContext().getApplicationContext().getSystemService(MimeTypes.BASE_TYPE_AUDIO)).requestAudioFocus((OnAudioFocusChangeListener) b.this.a.get(), 3, 1);
        }
    };

    public b(Context context) {
        super(context);
    }

    /* access modifiers changed from: protected */
    public void a() {
        super.a();
        if (getVideoView() != null) {
            getVideoView().getEventBus().a((T[]) new f[]{this.d, this.b, this.c});
        }
    }

    /* access modifiers changed from: protected */
    public void b() {
        if (getVideoView() != null) {
            getVideoView().getEventBus().b((T[]) new f[]{this.c, this.b, this.d});
        }
        super.b();
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        AudioManager audioManager = (AudioManager) getContext().getApplicationContext().getSystemService(MimeTypes.BASE_TYPE_AUDIO);
        WeakReference<OnAudioFocusChangeListener> weakReference = this.a;
        audioManager.abandonAudioFocus(weakReference == null ? null : (OnAudioFocusChangeListener) weakReference.get());
        super.onDetachedFromWindow();
    }
}
