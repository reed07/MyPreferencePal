package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.GradientDrawable;
import android.os.Build.VERSION;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.TextView;
import com.google.android.gms.ads.impl.R;
import com.google.android.gms.ads.internal.gmsg.zzu;
import com.google.android.gms.ads.internal.overlay.zzc;
import com.google.android.gms.ads.internal.overlay.zzd;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.ads.internal.zzv;
import com.google.android.gms.common.util.Predicate;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.Map;
import org.json.JSONObject;

@zzark
public final class zzbgr extends FrameLayout implements zzbgg {
    /* access modifiers changed from: private */
    public final zzbgg zzezc;
    private final zzbdq zzezd;

    public zzbgr(zzbgg zzbgg) {
        super(zzbgg.getContext());
        this.zzezc = zzbgg;
        this.zzezd = new zzbdq(zzbgg.zzadg(), this, this);
        addView(this.zzezc.getView());
    }

    public final View getView() {
        return this;
    }

    public final zzbdq zzabt() {
        return this.zzezd;
    }

    public final void onPause() {
        this.zzezd.onPause();
        this.zzezc.onPause();
    }

    public final void zzadr() {
        this.zzezd.onDestroy();
        this.zzezc.zzadr();
    }

    public final void zzady() {
        setBackgroundColor(0);
        this.zzezc.setBackgroundColor(0);
    }

    public final int zzaca() {
        return getMeasuredHeight();
    }

    public final int zzacb() {
        return getMeasuredWidth();
    }

    public final void zzacc() {
        this.zzezc.zzacc();
    }

    public final WebView getWebView() {
        return this.zzezc.getWebView();
    }

    public final void zza(String str, Map<String, ?> map) {
        this.zzezc.zza(str, map);
    }

    public final void zza(String str, JSONObject jSONObject) {
        this.zzezc.zza(str, jSONObject);
    }

    public final void zza(String str, zzu<? super zzbgg> zzu) {
        this.zzezc.zza(str, zzu);
    }

    public final void zzb(String str, zzu<? super zzbgg> zzu) {
        this.zzezc.zzb(str, zzu);
    }

    public final void zza(String str, Predicate<zzu<? super zzbgg>> predicate) {
        this.zzezc.zza(str, predicate);
    }

    public final void zzade() {
        this.zzezc.zzade();
    }

    public final void zzdh(int i) {
        this.zzezc.zzdh(i);
    }

    public final void zzvv() {
        this.zzezc.zzvv();
    }

    public final void zzadf() {
        this.zzezc.zzadf();
    }

    public final void zza(boolean z, long j) {
        this.zzezc.zza(z, j);
    }

    public final void zzcg(String str) {
        this.zzezc.zzcg(str);
    }

    public final void zzb(String str, JSONObject jSONObject) {
        this.zzezc.zzb(str, jSONObject);
    }

    public final Activity zzabw() {
        return this.zzezc.zzabw();
    }

    public final Context zzadg() {
        return this.zzezc.zzadg();
    }

    public final zzv zzid() {
        return this.zzezc.zzid();
    }

    public final zzd zzadh() {
        return this.zzezc.zzadh();
    }

    public final IObjectWrapper zzadp() {
        return this.zzezc.zzadp();
    }

    public final zzd zzadi() {
        return this.zzezc.zzadi();
    }

    public final zzbht zzadj() {
        return this.zzezc.zzadj();
    }

    public final String zzadk() {
        return this.zzezc.zzadk();
    }

    public final zzbhn zzadl() {
        return this.zzezc.zzadl();
    }

    public final WebViewClient zzadm() {
        return this.zzezc.zzadm();
    }

    public final boolean zzadn() {
        return this.zzezc.zzadn();
    }

    public final zzcu zzado() {
        return this.zzezc.zzado();
    }

    public final zzbbi zzabz() {
        return this.zzezc.zzabz();
    }

    public final boolean zzadq() {
        return this.zzezc.zzadq();
    }

    public final int getRequestedOrientation() {
        return this.zzezc.getRequestedOrientation();
    }

    public final boolean isDestroyed() {
        return this.zzezc.isDestroyed();
    }

    public final boolean zzads() {
        return this.zzezc.zzads();
    }

    public final boolean zzadt() {
        return this.zzezc.zzadt();
    }

    public final void zzjg() {
        this.zzezc.zzjg();
    }

    public final void zzjf() {
        this.zzezc.zzjf();
    }

    public final String zzabx() {
        return this.zzezc.zzabx();
    }

    public final zzaay zzabv() {
        return this.zzezc.zzabv();
    }

    public final zzaaz zzaby() {
        return this.zzezc.zzaby();
    }

    public final zzbgw zzabu() {
        return this.zzezc.zzabu();
    }

    public final void zza(zzd zzd) {
        this.zzezc.zza(zzd);
    }

    public final void zzaa(IObjectWrapper iObjectWrapper) {
        this.zzezc.zzaa(iObjectWrapper);
    }

    public final void zza(zzbht zzbht) {
        this.zzezc.zza(zzbht);
    }

    public final void zzav(boolean z) {
        this.zzezc.zzav(z);
    }

    public final void zzadv() {
        this.zzezc.zzadv();
    }

    public final void zzbo(Context context) {
        this.zzezc.zzbo(context);
    }

    public final void zzaf(boolean z) {
        this.zzezc.zzaf(z);
    }

    public final void setRequestedOrientation(int i) {
        this.zzezc.setRequestedOrientation(i);
    }

    public final void zzb(zzd zzd) {
        this.zzezc.zzb(zzd);
    }

    public final void zzaw(boolean z) {
        this.zzezc.zzaw(z);
    }

    public final void zzfb(String str) {
        this.zzezc.zzfb(str);
    }

    public final void zza(String str, zzbfk zzbfk) {
        this.zzezc.zza(str, zzbfk);
    }

    public final zzbfk zzet(String str) {
        return this.zzezc.zzet(str);
    }

    public final void zzadw() {
        this.zzezc.zzadw();
    }

    public final void destroy() {
        IObjectWrapper zzadp = zzadp();
        if (zzadp != null) {
            zzbv.zzlw().zzp(zzadp);
            zzayh.zzelc.postDelayed(new zzbgs(this), (long) ((Integer) zzwu.zzpz().zzd(zzaan.zzcwz)).intValue());
            return;
        }
        this.zzezc.destroy();
    }

    public final void loadData(String str, String str2, String str3) {
        this.zzezc.loadData(str, str2, str3);
    }

    public final void loadDataWithBaseURL(String str, String str2, String str3, String str4, String str5) {
        this.zzezc.loadDataWithBaseURL(str, str2, str3, str4, str5);
    }

    public final void loadUrl(String str) {
        this.zzezc.loadUrl(str);
    }

    public final void zzc(String str, String str2, @Nullable String str3) {
        this.zzezc.zzc(str, str2, str3);
    }

    public final void setOnClickListener(OnClickListener onClickListener) {
        this.zzezc.setOnClickListener(onClickListener);
    }

    public final void setOnTouchListener(OnTouchListener onTouchListener) {
        this.zzezc.setOnTouchListener(onTouchListener);
    }

    public final void setWebChromeClient(WebChromeClient webChromeClient) {
        this.zzezc.setWebChromeClient(webChromeClient);
    }

    public final void setWebViewClient(WebViewClient webViewClient) {
        this.zzezc.setWebViewClient(webViewClient);
    }

    public final void stopLoading() {
        this.zzezc.stopLoading();
    }

    public final void onResume() {
        this.zzezc.onResume();
    }

    public final void zzadz() {
        TextView textView = new TextView(getContext());
        Resources resources = zzbv.zzlj().getResources();
        textView.setText(resources != null ? resources.getString(R.string.s7) : "Test Ad");
        textView.setTextSize(15.0f);
        textView.setTextColor(-1);
        textView.setPadding(5, 0, 5, 0);
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(0);
        gradientDrawable.setColor(-12303292);
        gradientDrawable.setCornerRadius(8.0f);
        if (VERSION.SDK_INT >= 16) {
            textView.setBackground(gradientDrawable);
        } else {
            textView.setBackgroundDrawable(gradientDrawable);
        }
        addView(textView, new LayoutParams(-2, -2, 49));
        bringChildToFront(textView);
    }

    public final void zzay(boolean z) {
        this.zzezc.zzay(z);
    }

    public final void zza(zzsf zzsf) {
        this.zzezc.zza(zzsf);
    }

    public final OnClickListener getOnClickListener() {
        return this.zzezc.getOnClickListener();
    }

    public final void zzb(@Nullable zzacb zzacb) {
        this.zzezc.zzb(zzacb);
    }

    @Nullable
    public final zzacb zzadx() {
        return this.zzezc.zzadx();
    }

    public final void zza(zzbgw zzbgw) {
        this.zzezc.zza(zzbgw);
    }

    public final boolean zzadu() {
        return this.zzezc.zzadu();
    }

    public final void zzax(boolean z) {
        this.zzezc.zzax(z);
    }

    public final void zzat(boolean z) {
        this.zzezc.zzat(z);
    }

    public final void zzvw() {
        this.zzezc.zzvw();
    }

    public final void zza(zzc zzc) {
        this.zzezc.zza(zzc);
    }

    public final void zzb(boolean z, int i) {
        this.zzezc.zzb(z, i);
    }

    public final void zza(boolean z, int i, String str) {
        this.zzezc.zza(z, i, str);
    }

    public final void zza(boolean z, int i, String str, String str2) {
        this.zzezc.zza(z, i, str, str2);
    }
}
