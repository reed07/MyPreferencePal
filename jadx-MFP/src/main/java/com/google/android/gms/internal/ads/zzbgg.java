package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewParent;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.google.android.gms.ads.internal.gmsg.zzu;
import com.google.android.gms.ads.internal.overlay.zzd;
import com.google.android.gms.ads.internal.zzbo;
import com.google.android.gms.ads.internal.zzv;
import com.google.android.gms.common.util.Predicate;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.dynamic.IObjectWrapper;
import javax.annotation.ParametersAreNonnullByDefault;

@zzark
@ParametersAreNonnullByDefault
@VisibleForTesting
public interface zzbgg extends zzbo, zzahu, zzais, zzbdz, zzbhc, zzbhd, zzbhh, zzbhk, zzbhl, zzbhm, zzsg {
    void destroy();

    Context getContext();

    int getHeight();

    LayoutParams getLayoutParams();

    void getLocationOnScreen(int[] iArr);

    @Nullable
    OnClickListener getOnClickListener();

    ViewParent getParent();

    int getRequestedOrientation();

    View getView();

    WebView getWebView();

    int getWidth();

    boolean isDestroyed();

    void loadData(String str, String str2, String str3);

    void loadDataWithBaseURL(String str, String str2, String str3, String str4, @Nullable String str5);

    void loadUrl(String str);

    void measure(int i, int i2);

    void onPause();

    void onResume();

    void setBackgroundColor(int i);

    void setOnClickListener(OnClickListener onClickListener);

    void setOnTouchListener(OnTouchListener onTouchListener);

    void setRequestedOrientation(int i);

    void setWebChromeClient(WebChromeClient webChromeClient);

    void setWebViewClient(WebViewClient webViewClient);

    void stopLoading();

    void zza(zzd zzd);

    void zza(zzbgw zzbgw);

    void zza(zzbht zzbht);

    void zza(String str, zzu<? super zzbgg> zzu);

    void zza(String str, Predicate<zzu<? super zzbgg>> predicate);

    void zza(String str, zzbfk zzbfk);

    void zzaa(IObjectWrapper iObjectWrapper);

    @Nullable
    zzbgw zzabu();

    Activity zzabw();

    zzaaz zzaby();

    zzbbi zzabz();

    void zzade();

    void zzadf();

    Context zzadg();

    zzd zzadh();

    zzd zzadi();

    zzbht zzadj();

    String zzadk();

    @Nullable
    zzbhn zzadl();

    WebViewClient zzadm();

    boolean zzadn();

    zzcu zzado();

    @Nullable
    IObjectWrapper zzadp();

    boolean zzadq();

    void zzadr();

    boolean zzads();

    boolean zzadt();

    boolean zzadu();

    void zzadv();

    void zzadw();

    zzacb zzadx();

    void zzady();

    void zzadz();

    void zzaf(boolean z);

    void zzav(boolean z);

    void zzaw(boolean z);

    void zzax(boolean z);

    void zzay(boolean z);

    void zzb(zzd zzd);

    void zzb(zzacb zzacb);

    void zzb(String str, zzu<? super zzbgg> zzu);

    void zzbo(Context context);

    void zzc(String str, String str2, @Nullable String str3);

    void zzdh(int i);

    void zzfb(String str);

    zzv zzid();

    void zzvv();
}
