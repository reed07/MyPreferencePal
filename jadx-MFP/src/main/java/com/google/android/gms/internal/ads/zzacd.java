package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import java.lang.ref.WeakReference;
import java.util.Map;

public interface zzacd {
    void cancelUnconfirmedClick();

    Context getContext();

    void performClick(Bundle bundle);

    void recordCustomClickGesture();

    boolean recordImpression(Bundle bundle);

    void reportTouchEvent(Bundle bundle);

    void setClickConfirmingView(View view);

    View zza(OnClickListener onClickListener, boolean z);

    void zza(View view, zzacb zzacb);

    void zza(View view, String str, Bundle bundle, Map<String, WeakReference<View>> map, View view2, boolean z);

    void zza(View view, Map<String, WeakReference<View>> map);

    void zza(View view, Map<String, WeakReference<View>> map, Bundle bundle, View view2);

    void zza(zzaet zzaet);

    void zzb(View view, Map<String, WeakReference<View>> map);

    void zzc(View view, Map<String, WeakReference<View>> map);

    void zzd(MotionEvent motionEvent);

    void zzj(View view);

    void zzjl();

    void zzjm();

    void zzm(View view);

    void zzsi();

    boolean zzsj();

    boolean zzsk();

    boolean zzsl();

    void zzsm();

    void zzsq();

    void zzsr();

    View zzss();
}
