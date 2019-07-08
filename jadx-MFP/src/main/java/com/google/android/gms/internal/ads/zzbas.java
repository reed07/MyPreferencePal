package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import android.view.Window;
import com.google.android.gms.ads.internal.zzbv;

@zzark
public final class zzbas {
    private final View mView;
    private Activity zzeoa;
    private boolean zzeob;
    private boolean zzeoc;
    private boolean zzeod;
    private OnGlobalLayoutListener zzeoe;
    private OnScrollChangedListener zzeof;

    public zzbas(Activity activity, View view, OnGlobalLayoutListener onGlobalLayoutListener, OnScrollChangedListener onScrollChangedListener) {
        this.zzeoa = activity;
        this.mView = view;
        this.zzeoe = onGlobalLayoutListener;
        this.zzeof = onScrollChangedListener;
    }

    public final void zzj(Activity activity) {
        this.zzeoa = activity;
    }

    public final void zzaam() {
        this.zzeod = true;
        if (this.zzeoc) {
            zzaao();
        }
    }

    public final void zzaan() {
        this.zzeod = false;
        zzaap();
    }

    public final void onAttachedToWindow() {
        this.zzeoc = true;
        if (this.zzeod) {
            zzaao();
        }
    }

    public final void onDetachedFromWindow() {
        this.zzeoc = false;
        zzaap();
    }

    private final void zzaao() {
        if (!this.zzeob) {
            OnGlobalLayoutListener onGlobalLayoutListener = this.zzeoe;
            if (onGlobalLayoutListener != null) {
                Activity activity = this.zzeoa;
                if (activity != null) {
                    ViewTreeObserver zzk = zzk(activity);
                    if (zzk != null) {
                        zzk.addOnGlobalLayoutListener(onGlobalLayoutListener);
                    }
                }
                zzbv.zzme();
                zzbct.zza(this.mView, this.zzeoe);
            }
            OnScrollChangedListener onScrollChangedListener = this.zzeof;
            if (onScrollChangedListener != null) {
                Activity activity2 = this.zzeoa;
                if (activity2 != null) {
                    ViewTreeObserver zzk2 = zzk(activity2);
                    if (zzk2 != null) {
                        zzk2.addOnScrollChangedListener(onScrollChangedListener);
                    }
                }
                zzbv.zzme();
                zzbct.zza(this.mView, this.zzeof);
            }
            this.zzeob = true;
        }
    }

    private final void zzaap() {
        Activity activity = this.zzeoa;
        if (activity != null && this.zzeob) {
            OnGlobalLayoutListener onGlobalLayoutListener = this.zzeoe;
            if (onGlobalLayoutListener != null) {
                ViewTreeObserver zzk = zzk(activity);
                if (zzk != null) {
                    zzbv.zzlh().zza(zzk, onGlobalLayoutListener);
                }
            }
            OnScrollChangedListener onScrollChangedListener = this.zzeof;
            if (onScrollChangedListener != null) {
                ViewTreeObserver zzk2 = zzk(this.zzeoa);
                if (zzk2 != null) {
                    zzk2.removeOnScrollChangedListener(onScrollChangedListener);
                }
            }
            this.zzeob = false;
        }
    }

    private static ViewTreeObserver zzk(Activity activity) {
        if (activity == null) {
            return null;
        }
        Window window = activity.getWindow();
        if (window == null) {
            return null;
        }
        View decorView = window.getDecorView();
        if (decorView == null) {
            return null;
        }
        return decorView.getViewTreeObserver();
    }
}
